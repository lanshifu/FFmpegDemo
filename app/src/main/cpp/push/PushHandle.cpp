#include <jni.h>
#include "PushJniCall.h"
#include "PushStatus.h"
#include "log.h"

//ffmpeg 是c写的，要用c的include
extern "C" {
#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
//引入时间
#include "libavutil/time.h"
};

#include <iostream>

using namespace std;


//JNI回调处理
PushJniCall *pushJniCall;
//状态处理
PushStatus *pushStatus;


int avError(int errNum) {
    char buf[1024];
    //获取错误信息
    av_strerror(errNum, buf, sizeof(buf));

    LOGE("发生异常：%s",buf);
    if (pushJniCall != NULL) {
        pushJniCall->callOnError(errNum, buf);
    }
    return errNum;
}


extern "C"
JNIEXPORT jint JNICALL
Java_com_lanshifu_ffmpegdemo_push_PushHandle_nPushRtmpFile(JNIEnv *env, jobject instance,
                                                           jstring path_, jstring rtmp_url) {

    pushJniCall = new PushJniCall(env, instance);
    pushStatus = new PushStatus();

    const char *inUrl = env->GetStringUTFChars(path_, 0);
    const char *outUrl = env->GetStringUTFChars(rtmp_url, 0);

    LOGW("nPushRtmpFile,inUrl = %s", inUrl);
    LOGW("nPushRtmpFile,outUrl = %s", outUrl);

    int videoindex = -1;
    /// 1.使用FFmpeg之前要调用av_register_all和avformat_network_init
    //初始化所有的封装和解封装 flv mp4 mp3 mov。不包含编码和解码
    av_register_all();
    //初始化网络库
    avformat_network_init();


    //////////////////////////////////////////////////////////////////
    //                   输入流处理部分
    /////////////////////////////////////////////////////////////////


    //输入封装的上下文。包含所有的格式内容和所有的IO。如果是文件就是文件IO，网络就对应网络IO
    AVFormatContext *ictx = NULL;
    AVFormatContext *octx = NULL;

    AVPacket pkt;
    int ret = 0;

    try {
        ///2.打开文件，解封文件头
        ret = avformat_open_input(&ictx, inUrl, 0, NULL);
        if (ret < 0) {
            avError(ret);
            throw ret;
        }
        LOGD("avformat_open_input success!");
        ///3.获取音频视频的信息 .h264 flv 没有头信息
        ret = avformat_find_stream_info(ictx, 0);
        if (ret != 0) {
            avError(ret);
            throw ret;
        }
        LOGD("avformat_find_stream_info success!");

        av_dump_format(ictx, 0, inUrl, 0);

        //////////////////////////////////////////////////////////////////
        //                   输出流处理部分
        /////////////////////////////////////////////////////////////////

        /// 4.创建输出上下文,如果是输入文件 flv可以不传，可以从文件中判断。如果是流则必须传
        ret = avformat_alloc_output_context2(&octx, NULL, "flv", outUrl);
        if (ret < 0) {
            avError(ret);
            throw ret;
        }
        LOGD("avformat_alloc_output_context2 success!");

        ///遍历输入流列表，一般有音频流和视频流，为输出内容添加音视频流
        for (int i = 0; i < ictx->nb_streams; i++) {
            //获取输入视频流，可能是音频流，视频流？
            AVStream *in_stream = ictx->streams[i];
            ///为输出内容添加一个音视频流，格式什么的跟输入流保持一致。
            AVStream *out_stream = avformat_new_stream(octx, in_stream->codec->codec);
            LOGD("avformat_new_stream %d, success!", i);
            if (!out_stream) {
                LOGE("未能成功添加音视频流 %d", i);
                ret = AVERROR_UNKNOWN;
                throw ret;
            }
            //这个不知道有什么用，注释掉吧，不影响结果
//            if (octx->oformat->flags & AVFMT_GLOBALHEADER) {
//                out_stream->codec->flags |= CODEC_FLAG_GLOBAL_HEADER;
//            }
            /// 输入流参数拷贝到输出流
            ret = avcodec_parameters_copy(out_stream->codecpar, in_stream->codecpar);
            if (ret < 0) {
                LOGD("copy 编解码器上下文失败\n");
            }
            out_stream->codecpar->codec_tag = 0;

            ///记录视频流的位置
            if (videoindex == -1 && ictx->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
                videoindex = i;
                LOGD("找到视频流的位置 %d,", videoindex);
            }
        }


        //打印输出的格式信息
        av_dump_format(octx, 0, outUrl, 1);
        LOGD("准备推流...");

        //////////////////////////////////////////////////////////////////
        //                   准备推流
        /////////////////////////////////////////////////////////////////

        ///打开IO
        ret = avio_open(&octx->pb, outUrl, AVIO_FLAG_READ_WRITE);
        // todo :Linux服务器地址报错 IO error,本地服务器不会
        if (ret < 0) {
            avError(ret);
            throw ret;
        }
        LOGD("打开IO avio_open success!");
        ///写入头部信息
        ret = avformat_write_header(octx, 0);
        if (ret < 0) {
            avError(ret);
            throw ret;
        }
        LOGD("写入头部信息 avformat_write_header Success!");
        //推流每一帧数据
        //int64_t pts  [ pts*(num/den)  第几秒显示]
        //int64_t dts  解码时间 [P帧(相对于上一帧的变化) I帧(关键帧，完整的数据) B帧(上一帧和下一帧的变化)]  有了B帧压缩率更高。
        //获取当前的时间戳  微妙
        long long start_time = av_gettime();
        long long frame_index = 0;
        LOGD("开始推流 >>>>>>>>>>>>>>>");
        while (!pushStatus->isExit) {
            //输入输出视频流
            AVStream *in_stream, *out_stream;
            ///不断读取每一帧数据
            ret = av_read_frame(ictx, &pkt);
            if (ret < 0) {
                //数据读完，播放完成
                break;
            }

            /*
            PTS（Presentation Time Stamp）显示播放时间
            DTS（Decoding Time Stamp）解码时间
            */
            //没有显示时间（比如未解码的 H.264 ）
            if (pkt.pts == AV_NOPTS_VALUE) {
                //AVRational time_base：时基。通过该值可以把PTS，DTS转化为真正的时间。
                AVRational time_base1 = ictx->streams[videoindex]->time_base;

                //计算两帧之间的时间
                /*
                r_frame_rate 基流帧速率  （不是太懂，先知道流程就行）
                av_q2d 转化为double类型
                */
                int64_t calc_duration = (double) AV_TIME_BASE / av_q2d(ictx->streams[videoindex]->r_frame_rate);

                //配置参数
                pkt.pts = (double) (frame_index * calc_duration) / (double) (av_q2d(time_base1) * AV_TIME_BASE);
                pkt.dts = pkt.pts;
                pkt.duration = (double) calc_duration / (double) (av_q2d(time_base1) * AV_TIME_BASE);
            }

            ///通过睡眠的方式保持播放时间同步
            if (pkt.stream_index == videoindex) {
                AVRational time_base = ictx->streams[videoindex]->time_base;
                AVRational time_base_q = {1, AV_TIME_BASE};
                //计算视频播放时间，比如在11s播放
                int64_t pts_time = av_rescale_q(pkt.dts, time_base, time_base_q);
                //计算实际视频的播放时间，比如已经播放了10s
                int64_t now_time = av_gettime() - start_time;
                if (pts_time > now_time) {
                    //睡眠一段时间（目的是让当前视频记录的播放时间与实际时间同步）
                    av_usleep((unsigned int) (pts_time - now_time));
                }
            }

            //输入输出视频流赋值
            in_stream = ictx->streams[pkt.stream_index];
            out_stream = octx->streams[pkt.stream_index];

            //计算延时后，重新指定时间戳，调函数即可
            pkt.pts = av_rescale_q_rnd(pkt.pts, in_stream->time_base, out_stream->time_base,
                    (AVRounding) (AV_ROUND_NEAR_INF | AV_ROUND_PASS_MINMAX));
            pkt.dts = av_rescale_q_rnd(pkt.dts, in_stream->time_base, out_stream->time_base,
                    (AVRounding) (AV_ROUND_NEAR_INF | AV_ROUND_PASS_MINMAX));
            pkt.duration = (int) av_rescale_q(pkt.duration, in_stream->time_base,out_stream->time_base);
            //字节流的位置，-1 表示不知道字节流位置
            pkt.pos = -1;

            if (pkt.stream_index == videoindex) {
                //当前视频帧数
                frame_index++;
            }
            //回调数据
            if (pushJniCall != NULL) {
                pushJniCall->callOnInfo(pkt.pts, pkt.dts, pkt.duration, frame_index);
            }

            ///把一帧数据写到输出流（推流）
            ret = av_interleaved_write_frame(octx, &pkt);

            if (ret < 0) {
                LOGE("推流失败 ret=%d",ret);
                break;
            }

        }
        ret = 0;
    } catch (int errNum) {
        if (pushJniCall != NULL) {
            pushJniCall->callOnError(errNum, const_cast<char *>("出错了"));
        }
    }

    if (pushJniCall != NULL) {
        pushJniCall->callOnPushComplete();
    }

    LOGD("推流结束》》》");
    //关闭资源
    if (octx != NULL){
        avio_close(octx->pb);
        octx = NULL;
    }
    //释放输出封装上下文
    if (octx != NULL) {
        avformat_free_context(octx);
    }
    //关闭输入上下文
    if (ictx != NULL) {
        avformat_close_input(&ictx);
        ictx = NULL;
    }
    //释放
    av_packet_unref(&pkt);

    env->ReleaseStringUTFChars(path_, outUrl);
    return ret;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_lanshifu_ffmpegdemo_push_PushHandle_nStopPush(JNIEnv *env, jobject instance) {
    LOGD("nStopPush");
    if (pushStatus != NULL){
        pushStatus->isExit = true;
    }
}
#include <jni.h>
#include <string>
#include "android/native_window.h"
#include "android/native_window_jni.h"

//ffmpeg 是c写的，要用c的include
extern "C" {
#include "libavformat/avformat.h"
#include "libswresample/swresample.h"
#include "libavutil/avutil.h"
#include "libswscale/swscale.h"
#include "libavutil/imgutils.h"
};

#include <android/log.h>

#define TAG "JNI_TAG"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

#define AUDIO_SAMPLE_RATE 44100


extern "C"
JNIEXPORT void JNICALL
Java_com_lanshifu_ffmpegdemo_player_VideoPlayer_nativePlay(JNIEnv *env, jobject instance,
                                                           jstring url_, jobject surface) {
    const char *url = env->GetStringUTFChars(url_, 0);


    AVFormatContext *pFormatContext = NULL;
    AVCodecParameters *pCodecParameters = NULL;
    AVCodec *pCodec = NULL;
    int formatFindStreamInfoRes = 0;
    int videoStramIndex = 0;
    AVCodecContext *pCodecContext = NULL;
    int codecParametersToContextRes = -1;
    int codecOpenRes = -1;
    AVPacket *pPacket = NULL;
    AVFrame *pFrame = NULL;
    AVFrame *pRgbaFrame = NULL;
    int index = 0;


    ANativeWindow *pNativeWindow = NULL;
    SwsContext *pSwsContext;
    int frameSize;
    uint8_t *frameBuffer;

    int videoWidth;
    int videoHeight;

    ///1、初始化所有组件，只有调用了该函数，才能使用复用器和编解码器（源码）
    av_register_all();
    ///2、打开文件
    int open_input_result = avformat_open_input(&pFormatContext, url, NULL, NULL);
    if (open_input_result != 0) {
        LOGE("format open input error: %s", av_err2str(open_input_result));
        goto _av_resource_destry;
    }

    ///3.填充流信息到 pFormatContext
    formatFindStreamInfoRes = avformat_find_stream_info(pFormatContext, NULL);
    if (formatFindStreamInfoRes < 0) {
        LOGE("format find stream info error: %s", av_err2str(formatFindStreamInfoRes));
        goto _av_resource_destry;
    }

    ///4、查找视频流的 index，后面根据这个index处理视频
    videoStramIndex = av_find_best_stream(pFormatContext, AVMediaType::AVMEDIA_TYPE_VIDEO, -1, -1,
                                          NULL, 0);
    if (videoStramIndex < 0) {
        LOGE("format audio stream error:");
        goto _av_resource_destry;
    }


    ///5、查找解码器
    //videoStramIndex 上一步已经获取了，通过音频流的index，可以从pFormatContext中拿到音频解码器的一些参数
    pCodecParameters = pFormatContext->streams[videoStramIndex]->codecpar;
    pCodec = avcodec_find_decoder(pCodecParameters->codec_id);

    LOGE("采样率：%d", pCodecParameters->sample_rate);
    LOGE("通道数: %d", pCodecParameters->channels);
    LOGE("format: %d", pCodecParameters->format);

    if (pCodec == NULL) {
        LOGE("codec find audio decoder error");
        goto _av_resource_destry;
    }

    ///6、打开解码器，avcodec_open2 需要两个参数
    //分配AVCodecContext，默认值
    pCodecContext = avcodec_alloc_context3(pCodec);
    if (pCodecContext == NULL) {
        LOGE("avcodec_alloc_context3 error");
        goto _av_resource_destry;
    }
    //pCodecParameters 转 context
    codecParametersToContextRes = avcodec_parameters_to_context(pCodecContext, pCodecParameters);
    if (codecParametersToContextRes < 0) {
        LOGE("avcodec_parameters_to_context error");
        goto _av_resource_destry;
    }
    //
    codecOpenRes = avcodec_open2(pCodecContext, pCodec, NULL);
    if (codecOpenRes != 0) {
        LOGE("codec audio open error: %s", av_err2str(codecOpenRes));
        goto _av_resource_destry;
    }


    ///视频处理开始
    ///7、获取窗体
    pNativeWindow = ANativeWindow_fromSurface(env, surface);

    // 获取视频宽高
    videoWidth = pCodecContext->width;
    videoHeight = pCodecContext->height;

    ///8、/ 设置native window的buffer大小,可自动拉伸
    ANativeWindow_setBuffersGeometry(
            pNativeWindow,
            videoWidth,
            videoHeight,
            WINDOW_FORMAT_RGBA_8888);

    ///9、由于解码出来的帧格式不是RGBA的,在渲染之前需要进行格式转换
    pSwsContext = sws_getContext(
            videoWidth,
            videoHeight,
            pCodecContext->pix_fmt,
            pCodecContext->width,
            pCodecContext->height,
            AV_PIX_FMT_ARGB,
            SWS_BILINEAR,
            NULL,
            NULL,
            NULL);
    ///10、转换后的Frame
    pRgbaFrame = av_frame_alloc();

    /// Frame大小,buffer中数据就是用于渲染的,且格式为RGBA
    frameSize = av_image_get_buffer_size(
            AV_PIX_FMT_ARGB,
            pCodecContext->width,
            pCodecContext->height,
            1);

    ///buffer大小
    frameBuffer = (uint8_t *) malloc(frameSize);
    ///11、填充内容，可以理解为先分配多大的内存，里面还没有真正的数据
    av_image_fill_arrays(pRgbaFrame->data, pRgbaFrame->linesize,
                         frameBuffer, AV_PIX_FMT_ARGB, pCodecContext->width, pCodecContext->height,
                         1);

    //window 缓冲区的buffer
    ANativeWindow_Buffer outBuffer;

    pPacket = av_packet_alloc();
    pFrame = av_frame_alloc();

    //一帧一帧读，wile循环
    while (av_read_frame(pFormatContext, pPacket) >= 0) {
        // Packet 包，压缩的数据，解码成 pcm 数据
        if (pPacket->stream_index != videoStramIndex) {
            continue;
        }

        // Decode video frame
//        avcodec_decode_video2(pCodecCtx, pFrame, &frameFinished, &packet);

        //输入原数据到解码器
        int codecSendPacketRes = avcodec_send_packet(pCodecContext, pPacket);
        if (codecSendPacketRes == 0) {
            //解码器输出解码后的数据 pFrame
            int codecReceiveFrameRes = avcodec_receive_frame(pCodecContext, pFrame);
            if (codecReceiveFrameRes == 0) {
                index++;

                LOGE("解码第 %d 帧 ", index);
                ///12 将解码出来的pFrame转换成目标frame
                sws_scale(pSwsContext, pFrame->data, pFrame->linesize, 0,
                          pCodecContext->height, pRgbaFrame->data, pRgbaFrame->linesize);
                ///13、把数据推到缓冲区，先ANativeWindow_lock，然后推，然后unlockAndPost

                ANativeWindow_lock(pNativeWindow, &outBuffer, NULL);

                memcpy(outBuffer.bits, frameBuffer, frameSize);

                ANativeWindow_unlockAndPost(pNativeWindow);

            }
        }

        //解引用
        av_packet_unref(pPacket);
        av_frame_unref(pFrame);
    }


    /// 解引用数据 data；
    /// 销毁 pPacket 结构体内存
    av_frame_free(&pFrame);
    av_packet_free(&pPacket);

    _av_resource_destry:
    if (pFormatContext != NULL) {
        avformat_close_input(&pFormatContext);
        avformat_free_context(pFormatContext);
        pFormatContext = NULL;
    }

    //NativeWindow 用完要释放
    if (pNativeWindow != NULL) {
        ANativeWindow_release(pNativeWindow);
    }

    env->ReleaseStringUTFChars(url_, url);
}


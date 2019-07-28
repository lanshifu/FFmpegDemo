//
// Created by 蓝晓彬 on 2019-06-30.
//
#include "ConstantDefind.h"
#include "FFmpeg.h"
#include "AudioManager.h"

/***
 * 封装了ffmpeg api
 * @param pJniCall
 * @param url
 */

FFmpeg::FFmpeg(PlayerJNICall *pJniCall, const char *url) {
    this->pJniCall = pJniCall;
    // 赋值一份 url ，因为怕外面方法结束销毁了 url
    this->url = (char *) malloc(strlen(url) + 1);
    memcpy((void *) this->url, url, strlen(url) + 1);
}

FFmpeg::~FFmpeg() {
    release();
}


void FFmpeg::play() {
    pAudioManager->play();
}

void FFmpeg::pause() {
    pAudioManager->pause();
}

void FFmpeg::prepare(ThreadMode threadMode) {
    AVCodecParameters *pCodecParameters = NULL;
    AVCodec *pCodec = NULL;
    int formatFindStreamInfoRes = 0;
    int audioStramIndex = 0;

    ///1、初始化所有组件，只有调用了该函数，才能使用复用器和编解码器（源码）
    av_register_all();
    ///2、打开文件
    int open_input_result = avformat_open_input(&pFormatContext, url, NULL, NULL);
    if (open_input_result != 0) {
        LOGE("format open input error: %s", av_err2str(open_input_result));
        callPlayerJniError(threadMode, open_input_result, av_err2str(open_input_result));
        return;
    }

    ///3.填充流信息到 pFormatContext
    formatFindStreamInfoRes = avformat_find_stream_info(pFormatContext, NULL);
    if (formatFindStreamInfoRes < 0) {
        LOGE("format find stream info error: %s", av_err2str(formatFindStreamInfoRes));
        callPlayerJniError(threadMode, formatFindStreamInfoRes,
                           av_err2str(formatFindStreamInfoRes));
        return;
    }

    ///4.、查找音频流的 index，后面根据这个index处理音频
    audioStramIndex = av_find_best_stream(pFormatContext, AVMediaType::AVMEDIA_TYPE_AUDIO, -1, -1,
                                          NULL, 0);
    if (audioStramIndex < 0) {
        LOGE("format audio stream error:");
        callPlayerJniError(threadMode, audioStramIndex, av_err2str(audioStramIndex));
        return;
    }


    pAudioManager = new AudioManager(audioStramIndex, pJniCall, pFormatContext);
    LOGE("new AudioManager");
    pAudioManager->analysisStream(threadMode);
    LOGE("analysisStream");
    // 回调到 Java 告诉他准备好了
    pJniCall->callPlayerPrepared(threadMode);
    LOGE("callPlayerPrepared");

    ///5、打开解码器
//    //分配AVCodecContext，默认值
//    pCodecContext = avcodec_alloc_context3(pCodec);
//    if (pCodecContext == NULL){
//        LOGE("avcodec_alloc_context3 error");
//        callPlayerJniError(CODEC_ALLOC_CONTEXT_ERROR_CODE, "codec alloc context error");
//        return;
//    }
//    //pCodecParameters 转 context
//    codecParametersToContextRes = avcodec_parameters_to_context(pCodecContext,pCodecParameters);
//    if(codecParametersToContextRes <0){
//        LOGE("avcodec_parameters_to_context error");
//        callPlayerJniError(codecParametersToContextRes, av_err2str(codecParametersToContextRes));
//        return;
//    }
//    //
//    codecOpenRes = avcodec_open2(pCodecContext,pCodec,NULL);
//    if (codecOpenRes != 0) {
//        LOGE("codec audio open error: %s", av_err2str(codecOpenRes));
//        callPlayerJniError(codecOpenRes, av_err2str(codecOpenRes));
//        return;
//    }
//
//    ///到此，pCodecContext 已经初始化完毕，下面可以用来获取每一帧数据
//
//    pPacket = av_packet_alloc();
//    pFrame = av_frame_alloc();
//
//
//    // ---------- 重采样 构造 swrContext 参数 start----------
//    out_ch_layout = AV_CH_LAYOUT_STEREO;
//    out_sample_fmt = AVSampleFormat::AV_SAMPLE_FMT_S16;
//    out_sample_rate = AUDIO_SAMPLE_RATE;
//    in_ch_layout = pCodecContext->channel_layout;
//    in_sample_fmt = pCodecContext->sample_fmt;
//    in_sample_rate = pCodecContext->sample_rate;
//    swrContext = swr_alloc_set_opts(NULL, out_ch_layout, out_sample_fmt,
//                                    out_sample_rate, in_ch_layout, in_sample_fmt, in_sample_rate, 0, NULL);
//    if (swrContext == NULL) {
//        // 提示错误
//        LOGE("swr_alloc_set_opts error");
//        callPlayerJniError(SWR_ALLOC_SET_OPTS_ERROR_CODE, "swr alloc set opts error");
//        return;
//    }
//    swrInitRes = swr_init(swrContext);
//    if (swrInitRes < 0) {
//        LOGE("swr_init error");
//        callPlayerJniError(SWR_CONTEXT_INIT_ERROR_CODE, "swr context swr init error");
//        return;
//    }
//    // ---------- 重采样 构造 swrContext 参数 end----------
//
//
//    // size 是播放指定的大小，是最终输出的大小
//    outChannels = av_get_channel_layout_nb_channels(out_ch_layout); //通道数
//    dataSize = av_samples_get_buffer_size(NULL, outChannels, pCodecParameters->frame_size,out_sample_fmt, 0);
//    resampleOutBuffer = (uint8_t *) malloc(dataSize);
//
//    jbyteArray jPcmByteArray = pJniCall->jniEnv->NewByteArray(dataSize);
//    // native 创建 c 数组
//    jbyte *jPcmData = pJniCall->jniEnv->GetByteArrayElements(jPcmByteArray, NULL);
//
//    //一帧一帧播放，wile循环
//    while (av_read_frame(pFormatContext,pPacket) >=0){
//        // Packet 包，压缩的数据，解码成 pcm 数据
//        //判断是音频帧
//        if (pPacket->stream_index != audioStramIndex) {
//            continue;
//        }
//
//        //输入原数据到解码器
//        int codecSendPacketRes = avcodec_send_packet(pCodecContext,pPacket);
//        if (codecSendPacketRes == 0){
//            //解码器输出解码后的数据 pFrame
//            int codecReceiveFrameRes = avcodec_receive_frame(pCodecContext,pFrame);
//            if(codecReceiveFrameRes == 0){
//                index++;
//
//                //数据转换成Buffer,需要导入 libswresample/swresample.h
//                swr_convert(swrContext, &resampleOutBuffer, pFrame->nb_samples,
//                            (const uint8_t **) pFrame->data, pFrame->nb_samples);
//
//                //内存拷贝
//                // write 写到缓冲区 pFrame.data -> javabyte
//                // size 是多大，装 pcm 的数据
//                // 1s 44100 点  2通道 ，2字节    44100*2*2
//                // 1帧不是一秒，pFrame->nb_samples点
//                memcpy(jPcmData, resampleOutBuffer, dataSize);
//
//                // 同步刷新到 jbyteArray ，并释放 C/C++ 数组，
//                // 注意：NI_COMMIT的意思是--copy content, do not free buffer
//                pJniCall->jniEnv->ReleaseByteArrayElements(jPcmByteArray, jPcmData, JNI_COMMIT);
//
//                pJniCall->callAudioTrackWrite(jPcmByteArray, 0, dataSize);
//
//                LOGE("解码第 %d 帧dataSize =%d ", index , dataSize);
//
//            }
//        }
//
//        //解引用
//        av_packet_unref(pPacket);
//        av_frame_unref(pFrame);
//    }
//
//    // 1. 解引用数据 data ， 2. 销毁 pPacket 结构体内存  3. pPacket = NULL
//    av_packet_free(&pPacket);
//    av_frame_free(&pFrame);
//    // 解除 jPcmDataArray 的持有，让 javaGC 回收
//    pJniCall->jniEnv->ReleaseByteArrayElements(jPcmByteArray, jPcmData, 0);
//    pJniCall->jniEnv->DeleteLocalRef(jPcmByteArray);
}

void *threadPrepare(void *context) {
    FFmpeg *pFFmpeg = (FFmpeg *) context;
    pFFmpeg->prepare(THREAD_CHILD);
    return 0;
}

void FFmpeg::prepareAsync() {
    // 创建一个线程去播放，多线程编解码边播放
    pthread_t prepareThreadT;
    pthread_create(&prepareThreadT, NULL, threadPrepare, this);
    pthread_detach(prepareThreadT);
}

void FFmpeg::callPlayerJniError(ThreadMode threadMode, int code, char *msg) {
    release();
    pJniCall->callPlayerError(threadMode, code, msg);
}

void FFmpeg::release() {
    if (pCodecContext != NULL) {
        avcodec_close(pCodecContext);
        avcodec_free_context(&pCodecContext);
        pCodecContext = NULL;
    }

    if (pFormatContext != NULL) {
        avformat_close_input(&pFormatContext);
        avformat_free_context(pFormatContext);
        pFormatContext = NULL;
    }

    if (swrContext != NULL) {
        swr_free(&swrContext);
        free(swrContext);
        swrContext = NULL;
    }

    if (resampleOutBuffer != NULL) {
        free(resampleOutBuffer);
        resampleOutBuffer = NULL;
    }

}



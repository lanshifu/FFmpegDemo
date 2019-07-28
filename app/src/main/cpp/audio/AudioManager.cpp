//
// Created by 蓝晓彬 on 2019-07-06.
//

#include "AudioManager.h"
#include "ConstantDefind.h"

#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_Android.h>

AudioManager::AudioManager(int audioStreamIndex, PlayerJNICall *pJniCall,
                           AVFormatContext *pFormatContext) {
    this->audioStreamIndex = audioStreamIndex;
    this->pJniCall = pJniCall;
    this->pFormatContext = pFormatContext;
    pPacketQueue = new PacketQueue();
    pPlayerStatus = new PlayerStatus();

}

AudioManager::~AudioManager() {
    release();
}

void *threadPlay(void *context) {
    AudioManager *pAudio = (AudioManager *) context;
    pAudio->initCrateOpenSLES();
//    pAudio->playByAudioTrack();
    return 0;
}

void *threadReadPacket(void *context) {
    LOGE("threadReadPacket");
    int count = 0;
    AudioManager *pAudio = (AudioManager *) context;
    while (true) {
        AVPacket *pPacket = av_packet_alloc();
        if (av_read_frame(pAudio->pFormatContext, pPacket) >= 0) {

            if (pAudio->pPacketQueue->size() > 50) {
                LOGD("队列任务太多，睡眠等待唤醒...:%d", pAudio->pPacketQueue->size());
                pthread_mutex_lock(&pAudio->mutex);
                pthread_cond_wait(&pAudio->not_full, &pAudio->mutex);
                LOGD("线程唤醒，继续添加到队列...");
            }

            if (pPacket->stream_index == pAudio->audioStreamIndex) {
                count++;
                pAudio->pPacketQueue->push(pPacket);
                ///通知队列不为空
                pthread_cond_signal(&pAudio->not_empty);
                LOGD("通知队列不为空");
            } else {
                // 1. 解引用数据 data ， 2. 销毁 pPacket 结构体内存  3. pPacket = NULL
                av_packet_free(&pPacket);
            }
            pthread_mutex_unlock(&pAudio->mutex);
        } else {
            // 1. 解引用数据 data ， 2. 销毁 pPacket 结构体内存  3. pPacket = NULL
            av_packet_free(&pPacket);
            // 睡眠一下，尽量不去消耗 cpu 的资源，也可以退出销毁这个线程
            break;
        }
    }
    LOGE("threadReadPacket end count= %d", count);
    return 0;
}

void AudioManager::play() {
    LOGE("play");
    // 一个线程去读取 Packet
    pthread_create(&decodeId, NULL, threadReadPacket, this);
    pthread_detach(decodeId);

    // 一个线程去解码播放
    pthread_create(&playId, NULL, threadPlay, this);
    pthread_detach(playId);

}

void AudioManager::pause() {

}

void playerCallback(SLAndroidSimpleBufferQueueItf caller, void *pContext) {
    AudioManager *pAudio = (AudioManager *) pContext;
    int dataSize = pAudio->resampleAudio(pAudio->resampleOutBuffer);
    // 这里为什么报错，留在后面再去解决
    (*caller)->Enqueue(caller, pAudio->resampleOutBuffer, dataSize);
}


void AudioManager::initCrateOpenSLES() {
    /*OpenSLES OpenGLES 都是自带的
    XXXES 与 XXX 之间可以说是基本没有区别，区别就是 XXXES 是 XXX 的精简
    而且他们都有一定规则，命名规则 slXXX() , glXXX3f*/
    // 3.1 创建引擎接口对象
    SLObjectItf engineObject = NULL;
    SLEngineItf engineEngine;
    slCreateEngine(&engineObject, 0, NULL, 0, NULL, NULL);
    // realize the engine
    (*engineObject)->Realize(engineObject, SL_BOOLEAN_FALSE);
    // get the engine interface, which is needed in order to create other objects
    (*engineObject)->GetInterface(engineObject, SL_IID_ENGINE, &engineEngine);
    // 3.2 设置混音器
    static SLObjectItf outputMixObject = NULL;
    const SLInterfaceID ids[1] = {SL_IID_ENVIRONMENTALREVERB};
    const SLboolean req[1] = {SL_BOOLEAN_FALSE};
    (*engineEngine)->CreateOutputMix(engineEngine, &outputMixObject, 1, ids, req);
    (*outputMixObject)->Realize(outputMixObject, SL_BOOLEAN_FALSE);
    SLEnvironmentalReverbItf outputMixEnvironmentalReverb = NULL;
    (*outputMixObject)->GetInterface(outputMixObject, SL_IID_ENVIRONMENTALREVERB,
                                     &outputMixEnvironmentalReverb);
    SLEnvironmentalReverbSettings reverbSettings = SL_I3DL2_ENVIRONMENT_PRESET_STONECORRIDOR;
    (*outputMixEnvironmentalReverb)->SetEnvironmentalReverbProperties(outputMixEnvironmentalReverb,
                                                                      &reverbSettings);
    // 3.3 创建播放器
    SLObjectItf pPlayer = NULL;
    SLPlayItf pPlayItf = NULL;
    SLDataLocator_AndroidSimpleBufferQueue simpleBufferQueue = {
            SL_DATALOCATOR_ANDROIDSIMPLEBUFFERQUEUE, 2};
    SLDataFormat_PCM formatPcm = {
            SL_DATAFORMAT_PCM,
            2,
            SL_SAMPLINGRATE_44_1,
            SL_PCMSAMPLEFORMAT_FIXED_16,
            SL_PCMSAMPLEFORMAT_FIXED_16,
            SL_SPEAKER_FRONT_LEFT | SL_SPEAKER_FRONT_RIGHT,
            SL_BYTEORDER_LITTLEENDIAN};
    SLDataSource audioSrc = {&simpleBufferQueue, &formatPcm};
    SLDataLocator_OutputMix outputMix = {SL_DATALOCATOR_OUTPUTMIX, outputMixObject};
    SLDataSink audioSnk = {&outputMix, NULL};
    SLInterfaceID interfaceIds[3] = {SL_IID_BUFFERQUEUE, SL_IID_VOLUME, SL_IID_PLAYBACKRATE};
    SLboolean interfaceRequired[3] = {SL_BOOLEAN_TRUE, SL_BOOLEAN_TRUE, SL_BOOLEAN_TRUE};
    (*engineEngine)->CreateAudioPlayer(engineEngine, &pPlayer, &audioSrc, &audioSnk, 3,
                                       interfaceIds, interfaceRequired);
    (*pPlayer)->Realize(pPlayer, SL_BOOLEAN_FALSE);
    (*pPlayer)->GetInterface(pPlayer, SL_IID_PLAY, &pPlayItf);
    // 3.4 设置缓存队列和回调函数
    SLAndroidSimpleBufferQueueItf playerBufferQueue;
    (*pPlayer)->GetInterface(pPlayer, SL_IID_BUFFERQUEUE, &playerBufferQueue);
    // 每次回调 this 会被带给 playerCallback 里面的 context
    (*playerBufferQueue)->RegisterCallback(playerBufferQueue, playerCallback, this);
    // 3.5 设置播放状态
    (*pPlayItf)->SetPlayState(pPlayItf, SL_PLAYSTATE_PLAYING);
    // 3.6 调用回调函数
    playerCallback(playerBufferQueue, this);
}


int AudioManager::resampleAudio(uint8_t *outBuffer) {

    //LOGE("resampleAudio ，会一直调,从队列取出数据放到resampleOutBuffer，然后返回，给GLSL播放");
    int dataSize = 0;
    AVPacket *pPacket = NULL;
    AVFrame *pFrame = av_frame_alloc();

    while (pPlayerStatus != NULL && !pPlayerStatus->isExit) {

        if (pPacketQueue->empty()) {
            LOGD("消费线程，队列空，等待...");
//            pthread_mutex_lock(&mutex);
            pthread_cond_wait(&not_empty, &mutex);
            LOGD("消费线程，队列不空，唤醒继续...");
        }
        pPacket = pPacketQueue->pop();
        // Packet 包，压缩的数据，解码成 pcm 数据
        int codecSendPacketRes = avcodec_send_packet(pCodecContext, pPacket);
        if (codecSendPacketRes == 0) {
            int codecReceiveFrameRes = avcodec_receive_frame(pCodecContext, pFrame);
            if (codecReceiveFrameRes == 0) {
                // AVPacket -> AVFrame
                // 调用重采样的方法，返回值是返回重采样的个数，也就是 pFrame->nb_samples
                dataSize = swr_convert(pSwrContext, &outBuffer, pFrame->nb_samples,
                                       (const uint8_t **) pFrame->data, pFrame->nb_samples);
                dataSize = dataSize * 2 * 2;
                // write 写到缓冲区 pFrame.data -> javabyte
                // size 是多大，装 pcm 的数据
                // 1s 44100 点  2通道 ，2字节    44100*2*2
                // 1帧不是一秒，pFrame->nb_samples点
                break;
            }
        }
        // 解引用
        av_packet_unref(pPacket);
        av_frame_unref(pFrame);
    }
    // 1. 解引用数据 data ， 2. 销毁 pPacket 结构体内存  3. pPacket = NULL
    av_packet_free(&pPacket);
    av_frame_free(&pFrame);
//    pthread_mutex_unlock(&mutex);
    return dataSize;
}

void AudioManager::analysisStream(ThreadMode threadMode) {

    ///4、查找解码器
    //audioStramIndex 上一步已经获取了，通过音频流的index，可以从pFormatContext中拿到音频解码器的一些参数
    pCodecParameters = pFormatContext->streams[audioStreamIndex]->codecpar;
    AVCodec *pCodec = avcodec_find_decoder(pCodecParameters->codec_id);

    LOGE("采样率：%d", pCodecParameters->sample_rate);
    LOGE("通道数: %d", pCodecParameters->channels);
    LOGE("format: %d", pCodecParameters->format);

    if (pCodec == NULL) {
        LOGE("codec find audio decoder error");
        callPlayerJniError(threadMode, CODEC_FIND_DECODER_ERROR_CODE,
                           "codec find audio decoder error");
        return;
    }

    ///5、打开解码器
    //分配AVCodecContext，默认值
    pCodecContext = avcodec_alloc_context3(pCodec);
    if (pCodecContext == NULL) {
        LOGE("avcodec_alloc_context3 error");
        callPlayerJniError(threadMode, CODEC_ALLOC_CONTEXT_ERROR_CODE, "codec alloc context error");
        return;
    }
    //pCodecParameters 转 context
    int codecParametersToContextRes = avcodec_parameters_to_context(pCodecContext,
                                                                    pCodecParameters);
    if (codecParametersToContextRes < 0) {
        LOGE("avcodec_parameters_to_context error");
        callPlayerJniError(threadMode, codecParametersToContextRes,
                           av_err2str(codecParametersToContextRes));
        return;
    }
    //
    int codecOpenRes = avcodec_open2(pCodecContext, pCodec, NULL);
    if (codecOpenRes != 0) {
        LOGE("codec audio open error: %s", av_err2str(codecOpenRes));
        callPlayerJniError(threadMode, codecOpenRes, av_err2str(codecOpenRes));
        return;
    }

    ///到此，pCodecContext 已经初始化完毕，下面可以用来获取每一帧数据

    // ---------- 重采样 start ----------
    int64_t out_ch_layout = AV_CH_LAYOUT_STEREO;
    enum AVSampleFormat out_sample_fmt = AVSampleFormat::AV_SAMPLE_FMT_S16;
    int out_sample_rate = AUDIO_SAMPLE_RATE;
    int64_t in_ch_layout = pCodecContext->channel_layout;
    enum AVSampleFormat in_sample_fmt = pCodecContext->sample_fmt;
    int in_sample_rate = pCodecContext->sample_rate;
    pSwrContext = swr_alloc_set_opts(NULL, out_ch_layout, out_sample_fmt,
                                     out_sample_rate, in_ch_layout, in_sample_fmt, in_sample_rate,
                                     0, NULL);
    if (pSwrContext == NULL) {
        // 提示错误
        callPlayerJniError(threadMode, SWR_ALLOC_SET_OPTS_ERROR_CODE, "swr alloc set opts error");
        return;
    }
    int swrInitRes = swr_init(pSwrContext);
    if (swrInitRes < 0) {
        callPlayerJniError(threadMode, SWR_CONTEXT_INIT_ERROR_CODE, "swr context swr init error");
        return;
    }

    resampleOutBuffer = (uint8_t *) malloc(pCodecContext->frame_size * 2 * 2);
    // ---------- 重采样 end ----------
}

void AudioManager::callPlayerJniError(ThreadMode threadMode, int code, char *msg) {
    // 释放资源
    release();
    // 回调给 java 层调用
    pJniCall->callPlayerError(threadMode, code, msg);
}

void AudioManager::release() {
    if (pPacketQueue) {
        delete (pPacketQueue);
        pPacketQueue = NULL;
    }

    if (resampleOutBuffer) {
        free(resampleOutBuffer);
        resampleOutBuffer = NULL;
    }

    if (pCodecContext != NULL) {
        avcodec_close(pCodecContext);
        avcodec_free_context(&pCodecContext);
        pCodecContext = NULL;
    }

    if (pSwrContext != NULL) {
        swr_free(&pSwrContext);
        free(pSwrContext);
        pSwrContext = NULL;
    }

    if (pPlayerStatus) {
        delete (pPlayerStatus);
        pPlayerStatus = NULL;
    }
}


/**
 * 通过audiotrack播放
 */
void AudioManager::playByAudioTrack() {
    LOGE("playByAudioTrack");
    int64_t out_ch_layout = AV_CH_LAYOUT_STEREO;
    enum AVSampleFormat out_sample_fmt;
    // size 是播放指定的大小，是最终输出的大小
    int outChannels = av_get_channel_layout_nb_channels(out_ch_layout); //通道数
    int dataSize = av_samples_get_buffer_size(NULL, outChannels, pCodecParameters->frame_size,
                                              out_sample_fmt, 0);
    resampleOutBuffer = (uint8_t *) malloc(dataSize);

    // 获取当前线程的 JNIEnv， 通过 JavaVM
    JNIEnv *env;
    if (pJniCall->javaVM->AttachCurrentThread(&env, 0) != JNI_OK) {
        LOGE("get child thread jniEnv error!");
        return;
    }

    jbyteArray jPcmByteArray = env->NewByteArray(dataSize);
//    // native 创建 c 数组
    jbyte *jPcmData = env->GetByteArrayElements(jPcmByteArray, NULL);

    AVPacket *pPacket = NULL;
    AVFrame *pFrame = av_frame_alloc();

    int index = 0;
    while (!pPacketQueue->empty()) {
        pPacket = pPacketQueue->pop();
        //判断是音频帧
        if (pPacket->stream_index != audioStreamIndex) {
            continue;
        }
        //输入原数据到解码器
        int codecSendPacketRes = avcodec_send_packet(pCodecContext, pPacket);
        if (codecSendPacketRes == 0) {
            //解码器输出解码后的数据 pFrame
            int codecReceiveFrameRes = avcodec_receive_frame(pCodecContext, pFrame);
            if (codecReceiveFrameRes == 0) {
                index++;
                LOGE("解码第 %d 帧dataSize =%d ", index, dataSize);

                //数据转换成Buffer,需要导入 libswresample/swresample.h
                swr_convert(pSwrContext, &resampleOutBuffer, pFrame->nb_samples,
                            (const uint8_t **) pFrame->data, pFrame->nb_samples);

                //内存拷贝
                // write 写到缓冲区 pFrame.data -> javabyte
                // size 是多大，装 pcm 的数据
                // 1s 44100 点  2通道 ，2字节    44100*2*2
                // 1帧不是一秒，pFrame->nb_samples点
                memcpy(jPcmData, resampleOutBuffer, dataSize);

                // 同步刷新到 jbyteArray ，并释放 C/C++ 数组，
                // 注意：NI_COMMIT的意思是--copy content, do not free buffer
                env->ReleaseByteArrayElements(jPcmByteArray, jPcmData, JNI_COMMIT);

                pJniCall->callAudioTrackWrite(jPcmByteArray, 0, dataSize);

            }
        }

    }

    LOGE("playByAudioTrack end");

    //解引用
    av_packet_unref(pPacket);
    av_frame_unref(pFrame);

    pJniCall->javaVM->DetachCurrentThread();
}





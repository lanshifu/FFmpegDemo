//
// Created by 蓝晓彬 on 2019-06-30.
//

#ifndef FFMPEGDEMO_FFMPEG_H
#define FFMPEGDEMO_FFMPEG_H


#include <pthread.h>
#include "PlayerJNICall.h"
#include "AudioManager.h"

extern "C" {
#include "libavformat/avformat.h"
#include "libswresample/swresample.h"
};

class FFmpeg {

public:
    AVFormatContext *pFormatContext = NULL;
    AVCodecContext *pCodecContext = NULL;
    SwrContext *swrContext = NULL;
    uint8_t *resampleOutBuffer = NULL;
    const char *url = NULL;
    PlayerJNICall *pJniCall = NULL;
    AudioManager *pAudioManager = NULL;

public:
    FFmpeg(PlayerJNICall *pJniCall, const char *url);
    ~FFmpeg();


public:
    void play();

    void pause();

    void prepare(ThreadMode threadMode);

    void prepareAsync();

    void callPlayerJniError(ThreadMode threadMode,int code, char *msg);

    void release();

};


#endif //FFMPEGDEMO_FFMPEG_H

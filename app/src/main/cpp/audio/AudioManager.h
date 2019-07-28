//
// Created by 蓝晓彬 on 2019-07-06.
//

#ifndef FFMPEGDEMO_AUDIOMANAGER_H
#define FFMPEGDEMO_AUDIOMANAGER_H

#include <pthread.h>
#include "PacketQueue.h"
#include "PlayerJNICall.h"
#include "PlayerStatus.h"

extern "C" {
#include <libavformat/avformat.h>
#include <libswresample/swresample.h>
};

class AudioManager {

public:
    AVFormatContext *pFormatContext = NULL;
    AVCodecContext *pCodecContext = NULL;
    SwrContext *pSwrContext = NULL;
    uint8_t *resampleOutBuffer = NULL;
    PlayerJNICall *pJniCall = NULL;
    int audioStreamIndex = -1;
    PacketQueue *pPacketQueue = NULL;

    AVCodecParameters *pCodecParameters = NULL;
    PlayerStatus *pPlayerStatus = NULL;

    //多线程
    pthread_t decodeId;             //解码线程id
    pthread_t playId;               //播放线程id
    pthread_mutex_t mutex;          //同步锁
    pthread_cond_t not_full;        //不为满条件，生产AVFrame时使用
    pthread_cond_t not_empty;       //不为空条件，消费AVFrame时使用

public:
    AudioManager(int audioStreamIndex, PlayerJNICall *pJniCall, AVFormatContext *pFormatContext);

    ~AudioManager();

    void play();

    void pause();

    void initCrateOpenSLES();

    void playByAudioTrack();

    int resampleAudio(uint8_t *outBuffer);

    void analysisStream(ThreadMode threadMode);

    void callPlayerJniError(ThreadMode threadMode, int code, char *msg);

    void release();

};


#endif //FFMPEGDEMO_AUDIOMANAGER_H

//
// Created by 蓝晓彬 on 2019-07-06.
//

#ifndef FFMPEGDEMO_PACKETQUEUE_H
#define FFMPEGDEMO_PACKETQUEUE_H

#include <queue>
#include <pthread.h>
extern "C"{
#include <libavcodec/avcodec.h>
};

class PacketQueue {

public:
    std::queue<AVPacket *> *pPacketQueue;
    pthread_mutex_t packetMutex;
    pthread_cond_t packetCond;

public:
    PacketQueue();

    ~PacketQueue();

public:
    void push(AVPacket *pPacket);

    AVPacket *pop();

    /**
     * 请求整个队列
     */
    void clear();

    bool empty();

    int size();
};


#endif //FFMPEGDEMO_PACKETQUEUE_H

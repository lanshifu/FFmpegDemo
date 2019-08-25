#ifndef MUSICPLAYER_DZPACKETQUEUE_H
#define MUSICPLAYER_DZPACKETQUEUE_H

#include <queue>
#include <pthread.h>
#include <malloc.h>

extern "C"{
#include "librtmp/rtmp.h"
};

class PacketQueue {
public:
    std::queue<RTMPPacket *> *pPacketQueue;
    pthread_mutex_t packetMutex;
    pthread_cond_t packetCond;
public:
    PacketQueue();

    ~PacketQueue();

public:
    void push(RTMPPacket *pPacket);

    RTMPPacket *pop();

    /**
     * 请求整个队列
     */
    void clear();

    void notify();
};


#endif //MUSICPLAYER_DZPACKETQUEUE_H

//
// Created by hcDarren on 2019/6/23.
//
#include "PacketQueue.h"

PacketQueue::PacketQueue() {
    pPacketQueue = new std::queue<RTMPPacket *>();
    pthread_mutex_init(&packetMutex, NULL);
    pthread_cond_init(&packetCond, NULL);
}

void PacketQueue::clear() {
    // 需要清除队列，还需要清除每个 AVPacket* 的内存数据
    pthread_mutex_lock(&packetMutex);

    while (!pPacketQueue->empty()) {
        RTMPPacket *pPacket = pPacketQueue->front();
        pPacketQueue->pop();
        RTMPPacket_Free(pPacket);
        free(pPacket);
    }

    pthread_mutex_unlock(&packetMutex);
}

PacketQueue::~PacketQueue() {
    if (pPacketQueue != NULL) {
        clear();
        delete (pPacketQueue);
        pPacketQueue = NULL;
    }
    pthread_mutex_destroy(&packetMutex);
    pthread_cond_destroy(&packetCond);
}

void PacketQueue::push(RTMPPacket *pPacket) {
    pthread_mutex_lock(&packetMutex);
    pPacketQueue->push(pPacket);
    pthread_cond_signal(&packetCond);
    pthread_mutex_unlock(&packetMutex);
}

RTMPPacket *PacketQueue::pop() {
    RTMPPacket *pPacket = NULL;
    pthread_mutex_lock(&packetMutex);
    if (pPacketQueue->empty()) {
        pthread_cond_wait(&packetCond, &packetMutex);
    } else {
        pPacket = pPacketQueue->front();
        pPacketQueue->pop();
    }
    pthread_mutex_unlock(&packetMutex);
    return pPacket;
}

void PacketQueue::notify() {
    pthread_mutex_lock(&packetMutex);
    pthread_cond_signal(&packetCond);
    pthread_mutex_unlock(&packetMutex);
}


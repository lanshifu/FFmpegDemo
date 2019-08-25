//
// Created by 蓝晓彬 on 2019-07-06.
//

#include "PacketQueue.h"

/**
 * 队列里有两个线程，主要处理同步问题
 *
 */
PacketQueue::PacketQueue(){
    pPacketQueue = new std::queue<AVPacket *>();
    pthread_mutex_init(&packetMutex, NULL);
    pthread_cond_init(&packetCond, NULL);

}

PacketQueue::~PacketQueue(){
    if (pPacketQueue != NULL) {
        clear();
        delete (pPacketQueue);
        pPacketQueue = NULL;
    }
    pthread_mutex_destroy(&packetMutex);
    pthread_cond_destroy(&packetCond);
}



void PacketQueue::push(AVPacket *pPacket){
    pthread_mutex_lock(&packetMutex);
    pPacketQueue->push(pPacket);
    pthread_cond_signal(&packetCond);
    pthread_mutex_unlock(&packetMutex);
}

AVPacket *PacketQueue::pop(){
    AVPacket *pPacket;
    pthread_mutex_lock(&packetMutex);
    //队列空就线程等待，然后有数据添加到队列的时候唤醒线程，生产者消费者模式
    if (pPacketQueue->empty()) {
        pthread_cond_wait(&packetCond, &packetMutex);
    }
    pPacket = pPacketQueue->front();
    pPacketQueue->pop();
    pthread_mutex_unlock(&packetMutex);
    return pPacket;
}

/**
 * 清除整个队列
 */
void PacketQueue::clear(){

}

bool PacketQueue::empty() {
    return pPacketQueue->empty();
}

int PacketQueue::size() {
    return pPacketQueue->size();
}

#ifndef _LIVEPUSH_H
#define _LIVEPUSH_H

#include "PushJniCall.h"
#include "PacketQueue.h"
#include <malloc.h>
#include <string.h>

extern "C" {
#include "librtmp/rtmp.h"
}

class LivePush {
public:
    PushJniCall *pJniCall = NULL;
    char *liveUrl = NULL;
    PacketQueue *pPacketQueue;
    RTMP *pRtmp = NULL;
    bool isPushing = true;
    uint32_t startTime;
    pthread_t initConnectTid; //初始化连接的线程id
public:
    LivePush(const char *liveUrl, PushJniCall *pJniCall);

    ~LivePush();

    void initConnect();

    void pushSpsPps(jbyte *spsData, jint spsLen, jbyte *ppsData, jint ppsLen);


    void pushVideo(jbyte *videoData, jint dataLen, jboolean keyFrame);


    void pushAudio(jbyte *audioData, jint dataLen);

    void stop();
};


#endif //_LIVEPUSH_H

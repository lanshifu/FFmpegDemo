//
// Created by 蓝晓彬 on 2019-08-18.
//

#ifndef FFMPEGDEMO_PUSHJNICALL_H
#define FFMPEGDEMO_PUSHJNICALL_H

#include <jni.h>
#include "log.h"
enum ThreadMode{
    THREAD_CHILD,THREAD_MAIN
};

class PushJniCall {
public:
    JavaVM *javaVM;
    JNIEnv *jniEnv;
    jmethodID jConnectErrorMid;
    jmethodID jConnectSuccessMid;
    jmethodID jOnInfoMid;
    jobject jLiveObj;

public:
    PushJniCall(JavaVM *javaVM,JNIEnv *jniEnv,jobject jPushCallbackObj);
    ~PushJniCall();

public:


    void callConnectError(ThreadMode threadMode,int code, char *msg);

    void callConnectSuccess(ThreadMode mode);

    void callOnInfo(int64_t pts, int64_t dts, int64_t duration, long long index);


};

#endif //FFMPEGDEMO_PUSHJNICALL_H

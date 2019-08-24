//
// Created by 蓝晓彬 on 2019-08-18.
//

#ifndef FFMPEGDEMO_PUSHJNICALL_H
#define FFMPEGDEMO_PUSHJNICALL_H

#include <jni.h>

class PushJniCall {
public:
    JNIEnv *jniEnv;

    //定义java的对象和方法id，在cpp构造函数赋值
    jobject jPushCallbackObj;
    jmethodID jOnErrorMid;
    jmethodID jOnInfoMid;
    jmethodID jOnPushCompleteMid;

public:
    PushJniCall(JNIEnv *jniEnv,jobject jPushCallbackObj);
    ~PushJniCall();

public:

    //定义回调方法，在cpp实现
    void callOnError(int code, char *msg);

    void callOnPushComplete();

    void callOnInfo(int64_t pts, int64_t dts, int64_t duration, long long index);


};

#endif //FFMPEGDEMO_PUSHJNICALL_H

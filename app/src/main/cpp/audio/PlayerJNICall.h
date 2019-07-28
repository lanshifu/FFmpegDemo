//
// Created by 蓝晓彬 on 2019-06-30.
// JNI回调封装
//

#ifndef FFMPEGDEMO_JNICALL_H
#define FFMPEGDEMO_JNICALL_H

#include "jni.h"

enum ThreadMode{
    THREAD_CHILD,THREAD_MAIN
};

class PlayerJNICall {
    
public:
    PlayerJNICall(JavaVM *javaVM, JNIEnv *jniEnv, jobject jPlayerObj);

    ~PlayerJNICall();

public:

    JavaVM *javaVM;
    JNIEnv *jniEnv;

    jobject jAudioTrackObj;
    jmethodID jAudioTrackWriteMid;

    jobject jPlayerObj;
    jmethodID jPlayerErrorMid;
    jmethodID jPlayerPreparedMid;


private:
    void initCrateAudioTrack();

public:
    void callAudioTrackWrite(jbyteArray audioData, int offsetInBytes, int sizeInBytes);

    void callPlayerError(ThreadMode threadMode,int code, char *msg);

    void callPlayerPrepared(ThreadMode threadMode);
};


#endif //FFMPEGDEMO_JNICALL_H

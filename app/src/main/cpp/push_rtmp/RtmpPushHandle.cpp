#include <jni.h>
#include "PushJniCall.h"
#include "PushStatus.h"
#include "LivePush.h"

//ffmpeg 是c写的，要用c的include
extern "C" {
#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
//引入时间
#include "libavutil/time.h"
};

#include <iostream>

using namespace std;

//JNI回调处理，跟上一篇差不多，可以自己按需修改
PushJniCall *pJniCall;
//推流的几个方法封装
LivePush *pLivePush;
//状态处理，跟上一篇一样
PushStatus *pushStatus;

JavaVM *pJavaVM = NULL;


// 重写 so 被加载时会调用的一个方法,动态注册了解一下
extern "C" JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *javaVM, void *reserved) {
    pJavaVM = javaVM;
    JNIEnv *env;
    if (javaVM->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }
    return JNI_VERSION_1_6;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lanshifu_ffmpegdemo_push_1live_LivePushHandle_nInitConnect(JNIEnv *env, jobject instance,
                                                                    jstring liveUrl_) {
    const char *liveUrl = env->GetStringUTFChars(liveUrl_, 0);
    LOGD("开始连接...");

    pJniCall = new PushJniCall(pJavaVM, env, instance);
    pLivePush = new LivePush(liveUrl, pJniCall);
    pLivePush->initConnect();

    env->ReleaseStringUTFChars(liveUrl_, liveUrl);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lanshifu_ffmpegdemo_push_1live_LivePushHandle_pushSpsPps(JNIEnv *env, jobject instance,
                                                                  jbyteArray spsData_, jint spsLen,
                                                                  jbyteArray ppsData_,
                                                                  jint ppsLen) {
    jbyte *spsData = env->GetByteArrayElements(spsData_, NULL);
    jbyte *ppsData = env->GetByteArrayElements(ppsData_, NULL);

    LOGD("推sps和pps");
    if (pLivePush != NULL) {
        pLivePush->pushSpsPps(spsData, spsLen, ppsData, ppsLen);
    }

    env->ReleaseByteArrayElements(spsData_, spsData, 0);
    env->ReleaseByteArrayElements(ppsData_, ppsData, 0);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lanshifu_ffmpegdemo_push_1live_LivePushHandle_pushVideo(JNIEnv *env, jobject instance,
                                                                 jbyteArray videoData_,
                                                                 jint dataLen, jboolean keyFrame) {
    jbyte *videoData = env->GetByteArrayElements(videoData_, NULL);

    //调用推视频函数
    if (pLivePush != NULL) {
        pLivePush->pushVideo(videoData, dataLen, keyFrame);
    }

    env->ReleaseByteArrayElements(videoData_, videoData, 0);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lanshifu_ffmpegdemo_push_1live_LivePushHandle_pushAudio(JNIEnv *env, jobject instance,
                                                                 jbyteArray audioData_,
                                                                 jint dataLen) {
    jbyte *audioData = env->GetByteArrayElements(audioData_, NULL);

    //调用推音频函数
    if (pLivePush != NULL) {
        pLivePush->pushAudio(audioData, dataLen);
    }

    env->ReleaseByteArrayElements(audioData_, audioData, 0);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lanshifu_ffmpegdemo_push_1live_LivePushHandle_nStop(JNIEnv *env, jobject instance) {

    LOGD("停止推流");
    if (pLivePush != NULL) {
        pLivePush->stop();
        delete (pLivePush);
        pLivePush = NULL;
    }

    if (pJniCall != NULL) {
        delete (pJniCall);
        pJniCall = NULL;
    }

}
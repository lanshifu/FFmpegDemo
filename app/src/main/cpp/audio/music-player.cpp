#include <jni.h>
#include <string>
#include "ConstantDefind.h"
#include "PlayerJNICall.h"
#include "FFmpeg.h"

JavaVM *pJavaVM = NULL;
FFmpeg *pFFmpeg= NULL;
PlayerJNICall *pJniCall = NULL;

// 重写 so 被加载时会调用的一个方法
// 小作业，去了解动态注册
extern "C" JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *javaVM, void *reserved) {
    LOGE("JNI_OnLoad 被调用");
    pJavaVM = javaVM;
    JNIEnv *env;
    if (javaVM->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) {
        return -1;
    }

    return JNI_VERSION_1_4; //必须返回这个值
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lanshifu_ffmpegdemo_player_MusicPlayer_nativePlay(JNIEnv *env, jobject instance,
                                                           jstring url_) {
    if (pFFmpeg != NULL){
        pFFmpeg->play();
    }

}


/***
 * 同步，pFFmpeg->prepare()
 */
extern "C"
JNIEXPORT void JNICALL
Java_com_lanshifu_ffmpegdemo_player_MusicPlayer_nativePrepare(JNIEnv *env, jobject instance,
                                                              jstring url_) {
    const char *url = env->GetStringUTFChars(url_, 0);
    if (pFFmpeg == NULL){
        pJniCall = new PlayerJNICall(pJavaVM,env,url_);
        pFFmpeg = new FFmpeg(pJniCall,url);
        pFFmpeg->prepare(THREAD_MAIN);
    }

    env->ReleaseStringUTFChars(url_, url);
}

/**
 * 异步，pFFmpeg->prepareAsync();
 */
extern "C"
JNIEXPORT void JNICALL
Java_com_lanshifu_ffmpegdemo_player_MusicPlayer_nativePrepareAsync(JNIEnv *env, jobject instance,
                                                                   jstring url_) {
    const char *url = env->GetStringUTFChars(url_, 0);

    if (pFFmpeg == NULL) {
        pJniCall = new PlayerJNICall(pJavaVM, env, instance);
        pFFmpeg = new FFmpeg(pJniCall, url);
        pFFmpeg->prepareAsync();
    }

    env->ReleaseStringUTFChars(url_, url);
}
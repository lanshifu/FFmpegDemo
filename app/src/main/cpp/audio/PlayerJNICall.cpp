//
// Created by 蓝晓彬 on 2019-06-30.
//

#include "PlayerJNICall.h"
#include "ConstantDefind.h"

/***
 * 封装了jni回调java的方法
 *
 * @param javaVM
 * @param jniEnv
 * @param jPlayerObj
 */
PlayerJNICall::PlayerJNICall(JavaVM *javaVM, JNIEnv *jniEnv, jobject jPlayerObj) {
    this->javaVM = javaVM;
    this->jniEnv = jniEnv;
    this->jPlayerObj = jniEnv->NewGlobalRef(jPlayerObj);
    initCrateAudioTrack();

    jclass jPlayerClass = jniEnv->GetObjectClass(jPlayerObj);
    jPlayerErrorMid = jniEnv->GetMethodID(jPlayerClass, "onError", "(ILjava/lang/String;)V");
    jPlayerPreparedMid = jniEnv->GetMethodID(jPlayerClass, "onPrepared", "()V");
}



PlayerJNICall::~PlayerJNICall(){
    jniEnv->DeleteLocalRef(jAudioTrackObj);
}

void PlayerJNICall::initCrateAudioTrack(){

    jclass jAudioTrackClass = jniEnv->FindClass("android/media/AudioTrack");
    jmethodID jAudioTrackCMid = jniEnv->GetMethodID(jAudioTrackClass,"<init>","(IIIIII)V"); //构造

    //  public static final int STREAM_MUSIC = 3;
    int streamType = 3;
    int sampleRateInHz = 44100;
    // public static final int CHANNEL_OUT_STEREO = (CHANNEL_OUT_FRONT_LEFT | CHANNEL_OUT_FRONT_RIGHT);
    int channelConfig = (0x4 | 0x8);
    // public static final int ENCODING_PCM_16BIT = 2;
    int audioFormat = 2;
    // getMinBufferSize(int sampleRateInHz, int channelConfig, int audioFormat)
    jmethodID jGetMinBufferSizeMid = jniEnv->GetStaticMethodID(jAudioTrackClass, "getMinBufferSize", "(III)I");
    int bufferSizeInBytes = jniEnv->CallStaticIntMethod(jAudioTrackClass, jGetMinBufferSizeMid, sampleRateInHz, channelConfig, audioFormat);
    // public static final int MODE_STREAM = 1;
    int mode = 1;

    //创建了AudioTrack
    jAudioTrackObj = jniEnv->NewObject(jAudioTrackClass,jAudioTrackCMid, streamType, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes, mode);

    //要先调用AudioTrack的play方法
    jmethodID jPlayMid = jniEnv->GetMethodID(jAudioTrackClass,"play","()V");
    jniEnv->CallVoidMethod(jAudioTrackObj,jPlayMid);

    // write method
    jAudioTrackWriteMid = jniEnv->GetMethodID(jAudioTrackClass, "write", "([BII)I");

}

void PlayerJNICall::callAudioTrackWrite(jbyteArray audioData, int offsetInBytes, int sizeInBytes){
    //不断将一帧音频数据写入
//    JNIEnv *env;
//    if (javaVM->AttachCurrentThread(&env, 0) != JNI_OK) {
//        LOGE("get child thread jniEnv error!");
//        return;
//    }
    jniEnv->CallIntMethod(jAudioTrackObj, jAudioTrackWriteMid, audioData, offsetInBytes, sizeInBytes);
}


void PlayerJNICall::callPlayerError(ThreadMode threadMode, int code, char *msg){
    //回调错误信息给到java层
    if (threadMode == THREAD_MAIN) {
        jstring jMsg = jniEnv->NewStringUTF(msg);
        jniEnv->CallVoidMethod(jPlayerObj, jPlayerErrorMid, code, jMsg);
        jniEnv->DeleteLocalRef(jMsg);
    } else if(threadMode == THREAD_CHILD){
        // 获取当前线程的 JNIEnv， 通过 JavaVM
        JNIEnv *env;
        if (javaVM->AttachCurrentThread(&env, 0) != JNI_OK) {
            LOGE("get child thread jniEnv error!");
            return;
        }
        jstring jMsg = env->NewStringUTF(msg);
        env->CallVoidMethod(jPlayerObj, jPlayerErrorMid, code, jMsg);
        env->DeleteLocalRef(jMsg);
        javaVM->DetachCurrentThread();
    }


    LOGE("PlayerJNICall::callPlayerError,%d,%s",code,msg);
}


void PlayerJNICall::callPlayerPrepared(ThreadMode threadMode){
    LOGE("callPlayerPrepared threadMode=");
    // 子线程用不了主线程 jniEnv （native 线程）
    // 子线程是不共享 jniEnv ，他们有自己所独有的
    if (threadMode == THREAD_MAIN) {
        jniEnv->CallVoidMethod(jPlayerObj, jPlayerPreparedMid);
    } else if (threadMode == THREAD_CHILD) {
        // 获取当前线程的 JNIEnv， 通过 JavaVM
        JNIEnv *env;
        if (javaVM->AttachCurrentThread(&env, 0) != JNI_OK) {
            LOGE("get child thread jniEnv error!");
            return;
        }

        env->CallVoidMethod(jPlayerObj, jPlayerPreparedMid);
        javaVM->DetachCurrentThread();
    }
}

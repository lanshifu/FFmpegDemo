//
// Created by 蓝晓彬 on 2019-08-18.
//

#include "PushJniCall.h"

PushJniCall::PushJniCall(JavaVM *javaVM, JNIEnv *jniEnv, jobject jLiveObj) {

    this->javaVM = javaVM;
    this->jniEnv = jniEnv;
    this->jLiveObj = jniEnv->NewGlobalRef(jLiveObj);

    jclass jPlayerClass = jniEnv->GetObjectClass(jLiveObj);
    jConnectErrorMid = jniEnv->GetMethodID(jPlayerClass, "onConnectError",
                                           "(ILjava/lang/String;)V");
    jConnectSuccessMid = jniEnv->GetMethodID(jPlayerClass, "onConnectSuccess", "()V");

    jOnInfoMid = jniEnv->GetMethodID(jPlayerClass, "onInfo", "(JJJJ)V");

}

PushJniCall::~PushJniCall() {
    jniEnv->DeleteGlobalRef(jLiveObj);
}


void PushJniCall::callConnectError(ThreadMode threadMode, int code, char *msg) {
    // 子线程用不了主线程 jniEnv （native 线程）
    // 子线程是不共享 jniEnv ，他们有自己所独有的
    if (threadMode == THREAD_MAIN) {
        jstring jMsg = jniEnv->NewStringUTF(msg);
        jniEnv->CallVoidMethod(jLiveObj, jConnectErrorMid, code, jMsg);
        jniEnv->DeleteLocalRef(jMsg);
    } else if (threadMode == THREAD_CHILD) {
        // 获取当前线程的 JNIEnv， 通过 JavaVM
        JNIEnv *env;
        if (javaVM->AttachCurrentThread(&env, 0) != JNI_OK) {
            LOGE("get child thread jniEnv error!");
            return;
        }

        jstring jMsg = env->NewStringUTF(msg);
        env->CallVoidMethod(jLiveObj, jConnectErrorMid, code, jMsg);
        env->DeleteLocalRef(jMsg);

        javaVM->DetachCurrentThread();
    }
}

/**
 * 回调到 java 层告诉准备好了
 * @param threadMode
 */
void PushJniCall::callConnectSuccess(ThreadMode threadMode) {
    // 子线程用不了主线程 jniEnv （native 线程）
    // 子线程是不共享 jniEnv ，他们有自己所独有的
    if (threadMode == THREAD_MAIN) {
        jniEnv->CallVoidMethod(jLiveObj, jConnectSuccessMid);
    } else if (threadMode == THREAD_CHILD) {
        // 获取当前线程的 JNIEnv， 通过 JavaVM
        JNIEnv *env;
        if (javaVM->AttachCurrentThread(&env, 0) != JNI_OK) {
            LOGE("get child thread jniEnv error!");
            return;
        }
        env->CallVoidMethod(jLiveObj, jConnectSuccessMid);
        javaVM->DetachCurrentThread();
    }

}


void PushJniCall::callOnInfo(int64_t pts, int64_t dts, int64_t duration, long long index) {
    jniEnv->CallVoidMethod(jLiveObj, jOnInfoMid, (jlong) pts, (jlong) dts,
                           (jlong) duration, (jlong) index);


}
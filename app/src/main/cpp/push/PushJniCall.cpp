//
// Created by 蓝晓彬 on 2019-08-18.
//

#include "PushJniCall.h"

PushJniCall::PushJniCall(JNIEnv *jniEnv, jobject jPushCallbackObj) {

    this->jniEnv = jniEnv;
    //需要创建一个全局应用
    this->jPushCallbackObj = jniEnv->NewGlobalRef(jPushCallbackObj);
    jclass cls = jniEnv->GetObjectClass(jPushCallbackObj);

    jOnInfoMid = jniEnv->GetMethodID(cls, "onInfo", "(JJJJ)V");
    jOnErrorMid = jniEnv->GetMethodID(cls, "onError", "(ILjava/lang/String;)V");
    jOnPushCompleteMid = jniEnv->GetMethodID(cls, "onPushComplete", "()V");

}

PushJniCall::~PushJniCall() {
    jniEnv->DeleteGlobalRef(jPushCallbackObj);
}

void PushJniCall::callOnError(int code, char *msg) {
    //C中的char*转化为JNI中的jstring
    jniEnv->CallVoidMethod(jPushCallbackObj, jOnErrorMid, code, msg);

}

void PushJniCall::callOnPushComplete() {
    jniEnv->CallVoidMethod(jPushCallbackObj, jOnPushCompleteMid);

}

void PushJniCall::callOnInfo(int64_t pts, int64_t dts, int64_t duration, long long index) {
    jniEnv->CallVoidMethod(jPushCallbackObj, jOnInfoMid, (jlong) pts, (jlong) dts,
                           (jlong) duration, (jlong) index);

}

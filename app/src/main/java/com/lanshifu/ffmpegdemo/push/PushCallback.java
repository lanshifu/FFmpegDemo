package com.lanshifu.ffmpegdemo.push;

public interface PushCallback {

    //回调推流每一帧信息
    void onInfo(long pts, long dts, long duration, long index);

    //错误回调
    void onError(int code, String msg);

    //推流完成
    void onPushComplete();
}


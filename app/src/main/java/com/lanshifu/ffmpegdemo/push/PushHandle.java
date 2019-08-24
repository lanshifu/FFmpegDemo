package com.lanshifu.ffmpegdemo.push;

public class PushHandle {

    static {
        System.loadLibrary("push_handle");
    }

    private PushCallback pushCallback;

    public void setCallback(PushCallback pushCallback) {
        this.pushCallback = pushCallback;
    }


    public native int nPushRtmpFile(String filePath, String rtmp_url);

    public native int nStopPush();


    /**native回调*/
    private void onInfo(long pts, long dts, long duration, long index) {
        if (pushCallback != null) {
            pushCallback.onInfo(pts, dts, duration, index);
        }
    }

    private void onError(int code, String msg) {
        if (pushCallback != null) {
            pushCallback.onError(code, msg);
        }
    }

    private void onPushComplete() {
        if (pushCallback != null) {
            pushCallback.onPushComplete();
        }
    }

}

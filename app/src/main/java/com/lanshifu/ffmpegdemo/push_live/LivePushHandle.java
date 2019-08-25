package com.lanshifu.ffmpegdemo.push_live;

import android.os.Handler;
import android.os.Looper;


/**
 * 直播推流管理类
 */
public class LivePushHandle {

    static {
        System.loadLibrary("push_rtmp_handle");
    }

    /**
     * 主线程的 handler
     */
    private static Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    //默认推流地址
    private String mLiveUrl = "rtmp://192.168.43.144:1935/test/live";

    public LivePushHandle() {
    }
    public LivePushHandle(String liveUrl) {
        this.mLiveUrl = liveUrl;
    }


    /**
     * 初始化連接
     */
    public void initConnect(){
        nInitConnect(mLiveUrl);
    }

    public void stop() {
        MAIN_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                nStop();
            }
        });
    }

    //1.初始化连接
    private native void nInitConnect(String liveUrl);

    //2.推sps和pps，关键帧中的数据
    public native void pushSpsPps(byte[] spsData, int spsLen, byte[] ppsData, int ppsLen);

    //3.推送每一帧视频
    public native void pushVideo(byte[] videoData, int dataLen, boolean keyFrame);

    //4.推送每一帧音频
    public native void pushAudio(byte[] audioData, int dataLen);

    //5.停止推送
    private native void nStop();


    /**回调*/
    private ConnectListener mConnectListener;

    public void setOnConnectListener(ConnectListener connectListener) {
        this.mConnectListener = connectListener;
    }

    public interface ConnectListener{
        void connectError(int errorCode, String errorMsg);
        void connectSuccess();
        void onInfo(long pts, long dts, long duration, long index);
    }

    // 連接的回調 called from jni
    private void onConnectError(int errorCode, String errorMsg){
        stop();
        if(mConnectListener != null){
            mConnectListener.connectError(errorCode,errorMsg);
        }
    }
    // 連接的回調 called from jni
    private void onConnectSuccess(){
        if(mConnectListener != null){
            mConnectListener.connectSuccess();
        }
    }

    // 推流每一帧信息回调 called from jni
    private void onInfo(long pts, long dts, long duration, long index) {
        if (mConnectListener != null) {
            mConnectListener.onInfo(pts, dts, duration, index);
        }
    }

}

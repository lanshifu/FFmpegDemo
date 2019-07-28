package com.tencent.liteav.qos;

import android.os.Bundle;
import android.os.Handler;
import com.tencent.liteav.basic.d.a;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.basic.util.b;
import com.tencent.rtmp.TXLiveConstants;
import org.slf4j.Marker;

public class TXCQoS {
    public static final int AUTO_ADJUST_LIVEPUSH_RESOLUTION_STRATEGY = 1;
    public static final int AUTO_ADJUST_REALTIME_VIDEOCHAT_STRATEGY = 5;
    static final String TAG = "TXCQos";
    private int mAutoStrategy = -1;
    private int mBitrate = 0;
    private Handler mHandler = new Handler();
    private int mHeight = 0;
    private long mInstance;
    private long mInterval = 1000;
    private boolean mIsEnableDrop = false;
    private a mListener;
    private a mNotifyListener;
    private Runnable mRunnable = new Runnable() {
        public void run() {
            if (TXCQoS.this.mListener != null) {
                int onGetEncoderRealBitrate = TXCQoS.this.mListener.onGetEncoderRealBitrate();
                int onGetQueueInputSize = TXCQoS.this.mListener.onGetQueueInputSize();
                int onGetQueueOutputSize = TXCQoS.this.mListener.onGetQueueOutputSize();
                int onGetVideoQueueMaxCount = TXCQoS.this.mListener.onGetVideoQueueMaxCount();
                int onGetVideoQueueCurrentCount = TXCQoS.this.mListener.onGetVideoQueueCurrentCount();
                int onGetVideoDropCount = TXCQoS.this.mListener.onGetVideoDropCount();
                TXCQoS.this.nativeSetVideoRealBitrate(TXCQoS.this.mInstance, onGetEncoderRealBitrate);
                TXCQoS.this.nativeAdjustBitrate(TXCQoS.this.mInstance, onGetVideoQueueMaxCount, onGetVideoQueueCurrentCount, onGetVideoDropCount, onGetQueueOutputSize, onGetQueueInputSize);
                boolean access$400 = TXCQoS.this.nativeIsEnableDrop(TXCQoS.this.mInstance);
                if (TXCQoS.this.mIsEnableDrop != access$400) {
                    TXCQoS.this.mIsEnableDrop = access$400;
                    TXCQoS.this.mListener.onEnableDropStatusChanged(access$400);
                }
                onGetEncoderRealBitrate = TXCQoS.this.nativeGetBitrate(TXCQoS.this.mInstance);
                int access$700 = TXCQoS.this.nativeGetWidth(TXCQoS.this.mInstance);
                int access$800 = TXCQoS.this.nativeGetHeight(TXCQoS.this.mInstance);
                Bundle bundle;
                String str;
                StringBuilder stringBuilder;
                if (access$700 == TXCQoS.this.mWidth && access$800 == TXCQoS.this.mHeight) {
                    if (onGetEncoderRealBitrate != TXCQoS.this.mBitrate) {
                        TXCQoS.this.mListener.onEncoderParamsChanged(onGetEncoderRealBitrate, 0, 0);
                        if (TXCQoS.this.mNotifyListener != null) {
                            bundle = new Bundle();
                            str = TXLiveConstants.EVT_DESCRIPTION;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("调整编码码率:new bitrate:");
                            stringBuilder.append(onGetEncoderRealBitrate);
                            bundle.putCharSequence(str, stringBuilder.toString());
                            bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
                            bundle.putLong("EVT_USERID", TXCQoS.this.mUserID);
                            TXCQoS.this.mNotifyListener.onNotifyEvent(TXLiveConstants.PUSH_EVT_CHANGE_BITRATE, bundle);
                        }
                    }
                } else if (TXCQoS.this.mAutoStrategy == 1 || TXCQoS.this.mAutoStrategy == 5) {
                    TXCQoS.this.mListener.onEncoderParamsChanged(onGetEncoderRealBitrate, access$700, access$800);
                    if (TXCQoS.this.mNotifyListener != null) {
                        bundle = new Bundle();
                        str = TXLiveConstants.EVT_DESCRIPTION;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("调整分辨率:new bitrate:");
                        stringBuilder.append(onGetEncoderRealBitrate);
                        stringBuilder.append(" new resolution:");
                        stringBuilder.append(access$700);
                        stringBuilder.append(Marker.ANY_MARKER);
                        stringBuilder.append(access$800);
                        bundle.putCharSequence(str, stringBuilder.toString());
                        bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
                        TXCQoS.this.mNotifyListener.onNotifyEvent(TXLiveConstants.PUSH_EVT_CHANGE_RESOLUTION, bundle);
                    }
                }
                TXCQoS.this.mBitrate = onGetEncoderRealBitrate;
                TXCQoS.this.mWidth = access$700;
                TXCQoS.this.mHeight = access$800;
            }
            TXCQoS.this.mHandler.postDelayed(this, TXCQoS.this.mInterval);
        }
    };
    private long mUserID = 0;
    private int mWidth = 0;

    private native void nativeAddQueueInputSize(long j, int i);

    private native void nativeAddQueueOutputSize(long j, int i);

    private native void nativeAdjustBitrate(long j, int i, int i2, int i3, int i4, int i5);

    private native void nativeDeinit(long j);

    private native int nativeGetBitrate(long j);

    private native int nativeGetHeight(long j);

    public static native int nativeGetProperResolutionByVideoBitrate(boolean z, int i, int i2);

    private native int nativeGetWidth(long j);

    private native long nativeInit(boolean z);

    private native boolean nativeIsEnableDrop(long j);

    private native void nativeReset(long j, boolean z);

    private native void nativeSetAutoAdjustBitrate(long j, boolean z);

    private native void nativeSetAutoAdjustStrategy(long j, int i);

    private native void nativeSetHasVideo(long j, boolean z);

    private native void nativeSetVideoDefaultResolution(long j, int i);

    private native void nativeSetVideoEncBitrate(long j, int i, int i2, int i3);

    private native void nativeSetVideoExpectBitrate(long j, int i);

    private native void nativeSetVideoRealBitrate(long j, int i);

    public TXCQoS(boolean z) {
        this.mInstance = nativeInit(z);
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        try {
            nativeDeinit(this.mInstance);
        } finally {
            super.finalize();
        }
    }

    public void start(long j) {
        this.mInterval = j;
        this.mHandler.postDelayed(this.mRunnable, this.mInterval);
    }

    public void stop() {
        this.mHandler.removeCallbacks(this.mRunnable);
        this.mAutoStrategy = -1;
    }

    public long getUserID() {
        return this.mUserID;
    }

    public void setUserID(long j) {
        this.mUserID = j;
    }

    public void setNotifyListener(a aVar) {
        this.mNotifyListener = aVar;
    }

    public void setListener(a aVar) {
        this.mListener = aVar;
    }

    public void reset(boolean z) {
        nativeReset(this.mInstance, z);
    }

    public boolean isEnableDrop() {
        return nativeIsEnableDrop(this.mInstance);
    }

    public void setHasVideo(boolean z) {
        nativeSetHasVideo(this.mInstance, z);
    }

    public void setAutoAdjustBitrate(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("autoAdjustBitrate is ");
        stringBuilder.append(z ? "yes" : "no");
        TXCLog.d(str, stringBuilder.toString());
        nativeSetAutoAdjustBitrate(this.mInstance, z);
    }

    public void setAutoAdjustStrategy(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("autoAdjustStrategy is ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        nativeSetAutoAdjustStrategy(this.mInstance, i);
        this.mAutoStrategy = i;
    }

    public void setDefaultVideoResolution(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DefaultVideoResolution is ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        this.mWidth = 0;
        this.mHeight = 0;
        nativeSetVideoDefaultResolution(this.mInstance, i);
    }

    public void setVideoEncBitrate(int i, int i2, int i3) {
        this.mBitrate = 0;
        nativeSetVideoEncBitrate(this.mInstance, i, i2, i3);
    }

    public void setVideoExpectBitrate(int i) {
        nativeSetVideoExpectBitrate(this.mInstance, i);
    }

    static {
        b.e();
    }
}

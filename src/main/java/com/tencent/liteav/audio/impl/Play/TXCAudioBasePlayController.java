package com.tencent.liteav.audio.impl.Play;

import android.content.Context;
import android.os.Environment;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.audio.a;
import com.tencent.liteav.audio.c;
import com.tencent.liteav.basic.log.TXCLog;

public class TXCAudioBasePlayController implements c {
    private static final String TAG;
    protected float mAutoAdjustMaxCache = a.d;
    protected float mAutoAdjustMinCache = a.e;
    protected float mCacheTime = a.b;
    protected Context mContext;
    protected boolean mIsAutoAdjustCache = a.c;
    protected boolean mIsHWAcceleration = false;
    protected boolean mIsMute = a.f;
    protected volatile boolean mIsPlaying = false;
    protected boolean mIsRealTimePlay = false;
    protected long mJitterBuffer = 0;
    protected c mListener;
    protected int mSmoothMode = 0;

    public static native byte[] nativeGetMixedTracksData(int i);

    public static native boolean nativeIsTracksEmpty();

    public static native void nativeSetTempPath(String str);

    public native void nativeAddData(long j, byte[] bArr, int i, long j2);

    public native long nativeCreateJitterBuffer(boolean z, TXCAudioBasePlayController tXCAudioBasePlayController);

    public native void nativeDestoryJitterBuffer(long j);

    public native void nativeEnableAutoAdjustCache(long j, boolean z);

    public native void nativeEnableRealTimePlay(long j, boolean z);

    public native long nativeGetCacheDuration(long j);

    public native float nativeGetCacheThreshold(long j);

    public native int nativeGetChannel(long j);

    public native long nativeGetCurPts(long j);

    public native TXAudioJitterBufferReportInfo nativeGetLoadingInfo(long j);

    public native long nativeGetNetRecvTS(long j);

    public native int nativeGetRecvJitter(long j);

    public native int nativeGetSamplerate(long j);

    public native void nativeSetAutoAdjustMaxCache(long j, float f);

    public native void nativeSetAutoAdjustMinCache(long j, float f);

    public native void nativeSetCacheTime(long j, float f);

    public native void nativeSetJitterCycle(long j, long j2);

    public native void nativeSetLoadingThreshold(long j, long j2);

    public native void nativeSetMute(long j, boolean z);

    public native void nativeSetRTCPlayHungryTimeThreshold(long j, int i);

    public native void nativeSetRealtimeJitterCycle(long j, long j2);

    public native void nativeSetSmoothAdjust(long j, long j2);

    public native void nativeSetSmoothMode(long j, long j2);

    public native void nativeSetSmoothSpeed(long j, float f);

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(TXCAudioBasePlayController.class.getSimpleName());
        TAG = stringBuilder.toString();
        nativeSetTempPath(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    public TXCAudioBasePlayController(Context context) {
        this.mContext = context;
    }

    public static void setAudioMode(Context context, int i) {
        b.a(context, i);
        c.a(context, i);
    }

    public synchronized void setListener(c cVar) {
        this.mListener = cVar;
    }

    public void setCacheTime(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("set cache time to ");
        stringBuilder.append(f);
        TXCLog.i(str, stringBuilder.toString());
        nativeSetCacheTime(this.mJitterBuffer, f);
        this.mCacheTime = f;
    }

    public void enableAutojustCache(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("set auto adjust cache to ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        nativeEnableAutoAdjustCache(this.mJitterBuffer, z);
        this.mIsAutoAdjustCache = z;
    }

    public void setAutoAdjustMaxCache(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("set auto adjust max cache to ");
        stringBuilder.append(f);
        TXCLog.i(str, stringBuilder.toString());
        nativeSetAutoAdjustMaxCache(this.mJitterBuffer, f);
        this.mAutoAdjustMaxCache = f;
    }

    public void setAutoAdjustMinCache(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("set auto adjust min cache to ");
        stringBuilder.append(f);
        TXCLog.i(str, stringBuilder.toString());
        nativeSetAutoAdjustMinCache(this.mJitterBuffer, f);
        this.mAutoAdjustMinCache = f;
    }

    public void enableHWAcceleration(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("set hw acceleration to ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        this.mIsHWAcceleration = z;
    }

    public void setSmootheMode(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("set careful mode to ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mSmoothMode = i;
    }

    public long getCacheDuration() {
        return nativeGetCacheDuration(this.mJitterBuffer);
    }

    public void enableRealTimePlay(boolean z) {
        if (z != this.mIsRealTimePlay) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("set real-time play to ");
            stringBuilder.append(z);
            TXCLog.i(str, stringBuilder.toString());
            nativeEnableRealTimePlay(this.mJitterBuffer, z);
            this.mIsRealTimePlay = z;
        }
    }

    public TXAudioJitterBufferReportInfo getReportInfo() {
        return (!this.mIsPlaying || this.mJitterBuffer == 0) ? null : nativeGetLoadingInfo(this.mJitterBuffer);
    }

    public long getCurPts() {
        return nativeGetCurPts(this.mJitterBuffer);
    }

    public int getRecvJitter() {
        return nativeGetRecvJitter(this.mJitterBuffer);
    }

    public long getCurRecvTS() {
        return nativeGetNetRecvTS(this.mJitterBuffer);
    }

    public float getCacheThreshold() {
        return nativeGetCacheThreshold(this.mJitterBuffer);
    }

    public boolean isPlaying() {
        return this.mIsPlaying;
    }

    public int startPlay() {
        this.mIsPlaying = true;
        return TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK;
    }

    public int playData(com.tencent.liteav.basic.g.a aVar) {
        return TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK;
    }

    public int stopPlay() {
        this.mIsPlaying = false;
        this.mCacheTime = a.b;
        this.mIsAutoAdjustCache = a.c;
        this.mAutoAdjustMaxCache = a.d;
        this.mAutoAdjustMinCache = a.e;
        this.mIsRealTimePlay = false;
        this.mIsHWAcceleration = false;
        this.mIsMute = a.f;
        this.mSmoothMode = 0;
        return TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK;
    }

    public void setMute(boolean z) {
        if (z != this.mIsMute) {
            nativeSetMute(this.mJitterBuffer, z);
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("set mute to ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        this.mIsMute = z;
    }

    public synchronized void onPlayPcmData(byte[] bArr, long j) {
        if (this.mListener != null) {
            this.mListener.onPlayPcmData(bArr, j);
        }
    }

    public synchronized void onPlayError(int i, String str) {
        if (this.mListener != null) {
            this.mListener.onPlayError(i, str);
        }
    }

    public synchronized void onPlayJitterStateNotify(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onPlayJitterStateNotify  cur state ");
        stringBuilder.append(i);
        TXCLog.e(str, stringBuilder.toString());
        if (this.mListener != null) {
            this.mListener.onPlayJitterStateNotify(i);
        }
    }

    public synchronized void onPlayAudioInfoChanged(com.tencent.liteav.basic.g.a aVar, com.tencent.liteav.basic.g.a aVar2) {
        if (this.mListener != null) {
            this.mListener.onPlayAudioInfoChanged(aVar, aVar2);
        }
    }
}

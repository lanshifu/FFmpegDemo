package com.tencent.liteav.audio;

import android.content.Context;
import com.tencent.liteav.audio.impl.Record.a;
import com.tencent.liteav.audio.impl.Record.f;
import com.tencent.liteav.audio.impl.Record.h;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.b;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class TXCAudioUGCRecorder implements h {
    private static final String TAG = "AudioCenter:TXCAudioUGCRecorder";
    static TXCAudioUGCRecorder instance = new TXCAudioUGCRecorder();
    protected int mAECType = TXEAudioDef.TXE_AEC_NONE;
    private a mBGMRecorder = null;
    protected int mBits = com.tencent.liteav.basic.a.a.h;
    protected int mChannels = com.tencent.liteav.basic.a.a.f;
    protected Context mContext;
    private boolean mCurBGMRecordFlag = false;
    private long mEffectorObj = 0;
    private boolean mEnableBGMRecord = false;
    protected boolean mIsEarphoneOn = false;
    protected boolean mIsMute = false;
    private boolean mIsPause = false;
    private long mLastPTS = 0;
    protected int mReverbType = TXEAudioDef.TXE_REVERB_TYPE_0;
    protected int mSampleRate = com.tencent.liteav.basic.a.a.e;
    private float mSpeedRate = 1.0f;
    private int mVoiceEnvironment = -1;
    private int mVoiceKind = -1;
    private float mVolume = 1.0f;
    private WeakReference<d> mWeakRecordListener;

    private native void nativeClearCache(long j);

    private native long nativeCreateEffector(int i, int i2, int i3);

    private native void nativeDestroyEffector(long j);

    private native void nativeEnableEncoder(long j, boolean z);

    private native long nativeGetPcmCacheLen(long j);

    private native void nativeMixBGM(long j, boolean z);

    private native void nativeProcess(long j, byte[] bArr, int i);

    private native byte[] nativeReadOneFrame(long j);

    private native void nativeSetChangerType(long j, int i, int i2);

    private native void nativeSetReverbType(long j, int i);

    private native void nativeSetSpeedRate(long j, float f);

    private native void nativeSetVolume(long j, float f);

    public void sendCustomPCMData(byte[] bArr) {
    }

    static {
        b.e();
    }

    public static TXCAudioUGCRecorder getInstance() {
        return instance;
    }

    private TXCAudioUGCRecorder() {
    }

    public int startRecord(Context context) {
        TXCLog.i(TAG, "startRecord");
        if (isRecording()) {
            if (this.mEnableBGMRecord == this.mCurBGMRecordFlag) {
                TXCLog.e(TAG, "startRecord failed! recorder is still running!");
                return -1;
            }
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("recorder is still running. will restart record! bgm record flag = ");
            stringBuilder.append(this.mEnableBGMRecord);
            TXCLog.e(str, stringBuilder.toString());
            stopRecord();
            enableBGMRecord(this.mCurBGMRecordFlag ^ 1);
        }
        if (context != null) {
            this.mContext = context.getApplicationContext();
        }
        initEffector();
        if (this.mEnableBGMRecord) {
            TXCLog.i(TAG, "录制BGM");
            this.mCurBGMRecordFlag = true;
            this.mBGMRecorder = new a();
            this.mBGMRecorder.a(this);
            setEarphoneOn(true);
            this.mBGMRecorder.a(this.mContext, this.mSampleRate, this.mChannels, this.mBits);
        } else {
            TXCLog.i(TAG, "录制人声");
            this.mCurBGMRecordFlag = false;
            setEarphoneOn(false);
            f.a().a(this);
            f.a().a(this.mContext, this.mSampleRate, this.mChannels, this.mBits, this.mAECType);
        }
        return 0;
    }

    public int stopRecord() {
        TXCLog.i(TAG, "stopRecord");
        if (this.mBGMRecorder != null) {
            this.mBGMRecorder.a();
            this.mBGMRecorder = null;
        }
        f.a().b();
        enableBGMRecord(false);
        this.mIsPause = false;
        uninitEffector();
        return 0;
    }

    public void pause() {
        TXCLog.i(TAG, "pause");
        this.mIsPause = true;
        if (!this.mEnableBGMRecord && isRecording()) {
            TXCLog.i(TAG, "停止系统录音");
            f.a().b();
        }
    }

    public void resume() {
        TXCLog.i(TAG, "resume");
        this.mIsPause = false;
        if (!this.mEnableBGMRecord && !isRecording()) {
            TXCLog.i(TAG, "恢复系统录音");
            this.mCurBGMRecordFlag = false;
            setEarphoneOn(false);
            f.a().a(this);
            f.a().a(this.mContext, this.mSampleRate, this.mChannels, this.mBits, this.mAECType);
        }
    }

    public synchronized void setListener(d dVar) {
        if (dVar == null) {
            try {
                this.mWeakRecordListener = null;
            } catch (Throwable th) {
            }
        } else {
            this.mWeakRecordListener = new WeakReference(dVar);
        }
    }

    public synchronized d getListener() {
        if (this.mWeakRecordListener == null) {
            return null;
        }
        return (d) this.mWeakRecordListener.get();
    }

    public void setChannels(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setChannels: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mChannels = i;
    }

    public int getChannels() {
        return this.mChannels;
    }

    public void setSampleRate(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setSampleRate: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mSampleRate = i;
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public synchronized void setReverbType(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setReverbType: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mReverbType = i;
        if (this.mEffectorObj > 0) {
            nativeSetReverbType(this.mEffectorObj, i);
        }
    }

    public void setAECType(int i, Context context) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setAECType: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mAECType = i;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        }
    }

    public void setMute(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setMute: ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        this.mIsMute = z;
    }

    public void enableBGMRecord(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("enableBGMRecord: ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        this.mEnableBGMRecord = z;
    }

    public synchronized void setEarphoneOn(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setEarphoneOn: ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        this.mIsEarphoneOn = z;
        if (this.mEffectorObj > 0) {
            boolean z2 = false;
            if (z || this.mAECType == TXEAudioDef.TXE_AEC_SYSTEM) {
                z2 = true;
            }
            nativeMixBGM(this.mEffectorObj, z2);
        }
    }

    public boolean isRecording() {
        if (this.mBGMRecorder != null) {
            return this.mBGMRecorder.b();
        }
        return f.a().c();
    }

    public synchronized long getPcmCacheLen() {
        if (this.mEffectorObj <= 0) {
            return 0;
        }
        return nativeGetPcmCacheLen(this.mEffectorObj);
    }

    public synchronized void setVolume(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setVolume: ");
        stringBuilder.append(f);
        TXCLog.i(str, stringBuilder.toString());
        this.mVolume = f;
        if (this.mEffectorObj > 0) {
            nativeSetVolume(this.mEffectorObj, f);
        }
    }

    public synchronized void setSpeedRate(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setSpeedRate: ");
        stringBuilder.append(f);
        TXCLog.i(str, stringBuilder.toString());
        this.mSpeedRate = f;
        if (this.mEffectorObj > 0) {
            nativeSetSpeedRate(this.mEffectorObj, this.mSpeedRate);
        }
    }

    public synchronized void clearCache() {
        TXCLog.i(TAG, "clearCache");
        if (this.mEffectorObj > 0) {
            nativeClearCache(this.mEffectorObj);
        }
    }

    public void onAudioRecordStart() {
        TXCLog.i(TAG, "sys audio record start");
    }

    public void onAudioRecordStop() {
        TXCLog.i(TAG, "sys audio record stop");
        f.a().a(null);
    }

    public void onAudioRecordError(int i, String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sys audio record error: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        TXCLog.e(str2, stringBuilder.toString());
        f.a().a(null);
        d listener = getListener();
        if (listener != null) {
            listener.onRecordError(i, str);
        }
    }

    public void onAudioRecordPCM(byte[] bArr, int i, long j) {
        if (!this.mIsPause) {
            if (this.mEffectorObj > 0) {
                if (this.mIsMute) {
                    Arrays.fill(bArr, (byte) 0);
                }
                if (this.mLastPTS >= j) {
                    j = this.mLastPTS + 2;
                }
                synchronized (this) {
                    nativeProcess(this.mEffectorObj, bArr, i);
                }
                do {
                    synchronized (this) {
                        bArr = nativeReadOneFrame(this.mEffectorObj);
                    }
                    if (bArr != null) {
                        this.mLastPTS = j;
                        d listener = getListener();
                        if (listener != null) {
                            listener.onRecordEncData(bArr, j, this.mSampleRate, this.mChannels, this.mBits);
                        }
                    }
                    synchronized (this) {
                        j += (long) ((1024000.0f / ((float) this.mSampleRate)) * this.mSpeedRate);
                    }
                } while (bArr != null);
            } else {
                TXCLog.e(TAG, "effectorObj is null");
            }
        }
    }

    private synchronized void initEffector() {
        uninitEffector();
        this.mEffectorObj = nativeCreateEffector(this.mSampleRate, this.mChannels, this.mBits);
        boolean z = false;
        if (this.mIsEarphoneOn || this.mAECType == TXEAudioDef.TXE_AEC_SYSTEM) {
            z = true;
        }
        nativeSetReverbType(this.mEffectorObj, this.mReverbType);
        nativeSetChangerType(this.mEffectorObj, this.mVoiceKind, this.mVoiceEnvironment);
        nativeMixBGM(this.mEffectorObj, z);
        nativeSetVolume(this.mEffectorObj, this.mVolume);
        nativeSetSpeedRate(this.mEffectorObj, this.mSpeedRate);
        nativeEnableEncoder(this.mEffectorObj, true);
        this.mLastPTS = 0;
    }

    private synchronized void uninitEffector() {
        if (this.mEffectorObj > 0) {
            nativeDestroyEffector(this.mEffectorObj);
            this.mEffectorObj = 0;
        }
    }

    public synchronized void setChangerType(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setChangerType: ");
        stringBuilder.append(i);
        stringBuilder.append(" ");
        stringBuilder.append(i2);
        TXCLog.i(str, stringBuilder.toString());
        this.mVoiceKind = i;
        this.mVoiceEnvironment = i2;
        if (this.mEffectorObj > 0) {
            nativeSetChangerType(this.mEffectorObj, i, i2);
        }
    }
}

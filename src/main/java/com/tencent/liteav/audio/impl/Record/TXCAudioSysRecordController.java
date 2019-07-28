package com.tencent.liteav.audio.impl.Record;

import android.content.Context;
import com.tencent.liteav.audio.TXCLiveBGMPlayer;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.audio.impl.d;
import com.tencent.liteav.basic.a.a;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.basic.util.b;
import com.tencent.ugc.TXRecordCommon;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class TXCAudioSysRecordController extends c implements h, d {
    private static final String TAG;
    private byte[] mBuf = null;
    private d mCustomRecord = null;
    private long mEffectorObj = 0;
    private e mHWEcoder = null;
    private long mLastPTS = 0;
    private boolean mNeedMix = false;
    private int mPosition = 0;
    private int mRecordSampleRate = a.e;
    private long mSoftEncObj = 0;

    private native void nativeAddEffect(long j, byte[] bArr);

    private native void nativeAddEffectAndSoftEnc(long j, long j2, byte[] bArr);

    private native long nativeCreateEffector(int i, int i2, int i3);

    private native long nativeCreateSoftEncoder(int i, int i2, int i3);

    private native void nativeDestorySoftEncoder(long j);

    private native void nativeDestroyEffector(long j);

    private native void nativeEnableEncoder(long j, boolean z);

    private native void nativeMixBGM(long j, boolean z);

    private native byte[] nativeReadOneEncFrame();

    private native byte[] nativeReadOneFrame(long j, int i);

    private native void nativeSetChangerType(long j, int i, int i2);

    private native void nativeSetInputInfo(long j, int i, int i2, int i3);

    private native void nativeSetNoiseSuppression(long j, int i);

    private native void nativeSetReverbParam(long j, int i, float f);

    private native void nativeSetReverbType(long j, int i);

    private native void nativeSetVolume(long j, float f);

    static {
        b.e();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(TXCAudioSysRecordController.class.getSimpleName());
        TAG = stringBuilder.toString();
    }

    public TXCAudioSysRecordController() {
        com.tencent.liteav.audio.impl.a.a().a((d) this);
    }

    public int startRecord(Context context) {
        TXCLog.i(TAG, "startRecord");
        super.startRecord(context);
        if (this.mIsCustomRecord) {
            setNoiseSuppression(-1);
            this.mRecordSampleRate = this.mSampleRate;
            this.mCustomRecord = new d();
            this.mCustomRecord.a(this);
            this.mCustomRecord.a(this.mContext, this.mRecordSampleRate, this.mChannels, this.mBits);
        } else {
            if (this.mAECType == TXEAudioDef.TXE_AEC_SYSTEM) {
                if (this.mSampleRate > com.tencent.liteav.basic.f.b.a().h()) {
                    this.mRecordSampleRate = com.tencent.liteav.basic.f.b.a().i();
                } else {
                    this.mRecordSampleRate = com.tencent.liteav.basic.f.b.a().h();
                }
                if (this.mRecordSampleRate == 0) {
                    this.mRecordSampleRate = TXRecordCommon.AUDIO_SAMPLERATE_16000;
                }
            } else {
                this.mRecordSampleRate = this.mSampleRate;
            }
            f.a().a(this);
            f.a().a(this.mContext, this.mRecordSampleRate, this.mChannels, this.mBits, this.mAECType);
        }
        if (!(this.mWeakRecordListener == null || this.mWeakRecordListener.get() == null)) {
            String str = this.mAECType == TXEAudioDef.TXE_AEC_SYSTEM ? "SYSTEM-AEC," : "NO-AEC,";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("采样率(");
            stringBuilder.append(this.mRecordSampleRate);
            stringBuilder.append("|");
            stringBuilder.append(this.mSampleRate);
            stringBuilder.append("),声道数");
            stringBuilder.append(this.mChannels);
            ((com.tencent.liteav.audio.d) this.mWeakRecordListener.get()).onRecordError(TXEAudioDef.TXE_AUDIO_NOTIFY_AUDIO_INFO, stringBuilder.toString());
        }
        return 0;
    }

    private String getProperty(String str) {
        String str2 = IjkMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{str, str2});
        } catch (Exception unused) {
            return str2;
        }
    }

    public int stopRecord() {
        TXCLog.i(TAG, "stopRecord");
        if (this.mCustomRecord != null) {
            this.mCustomRecord.c();
            this.mCustomRecord = null;
        }
        f.a().b();
        com.tencent.liteav.audio.impl.a.a().b((d) this);
        this.mNeedMix = false;
        return 0;
    }

    public void sendCustomPCMData(byte[] bArr) {
        if (this.mCustomRecord != null) {
            this.mCustomRecord.a(bArr);
        }
    }

    public boolean isRecording() {
        if (this.mCustomRecord != null) {
            return this.mCustomRecord.d();
        }
        return f.a().c();
    }

    public synchronized void setEarphoneOn(boolean z) {
        super.setEarphoneOn(z);
        if (this.mEffectorObj > 0) {
            if (!z) {
                if (this.mAECType != TXEAudioDef.TXE_AEC_SYSTEM) {
                    this.mNeedMix = false;
                    nativeMixBGM(this.mEffectorObj, this.mNeedMix);
                }
            }
            this.mNeedMix = true;
            nativeMixBGM(this.mEffectorObj, this.mNeedMix);
        }
    }

    public synchronized void setReverbType(int i) {
        super.setReverbType(i);
        if (this.mEffectorObj > 0) {
            nativeSetReverbType(this.mEffectorObj, i);
        }
    }

    public synchronized void setNoiseSuppression(int i) {
        super.setNoiseSuppression(i);
        if (this.mEffectorObj > 0) {
            nativeSetNoiseSuppression(this.mEffectorObj, i);
        }
    }

    public void setVolume(float f) {
        super.setVolume(f);
        if (this.mEffectorObj != 0) {
            nativeSetVolume(this.mEffectorObj, f);
        }
    }

    public synchronized void setReverbParam(int i, float f) {
        super.setReverbParam(i, f);
        if (this.mEffectorObj != 0) {
            nativeSetReverbParam(this.mEffectorObj, i, f);
        }
    }

    public void setChangerType(int i, int i2) {
        super.setChangerType(i, i2);
        if (this.mEffectorObj != 0) {
            nativeSetChangerType(this.mEffectorObj, i, i2);
        }
    }

    public synchronized void onAudioRecordStart() {
        TXCLog.i(TAG, "sys audio record start");
        uninitEffectAndEnc();
        initEffectAndEnc();
    }

    public synchronized void onAudioRecordStop() {
        TXCLog.i(TAG, "sys audio record stop");
        f.a().a(null);
        uninitEffectAndEnc();
    }

    public synchronized void onAudioRecordError(int i, String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sys audio record error: ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        TXCLog.e(str2, stringBuilder.toString());
        f.a().a(null);
        uninitEffectAndEnc();
        if (this.mWeakRecordListener != null) {
            com.tencent.liteav.audio.d dVar = (com.tencent.liteav.audio.d) this.mWeakRecordListener.get();
            if (dVar != null) {
                dVar.onRecordError(i, str);
            }
        }
    }

    public synchronized void onAudioRecordPCM(byte[] bArr, int i, long j) {
        byte[] bArr2 = bArr;
        synchronized (this) {
            if (this.mEffectorObj > 0) {
                if (this.mIsMute) {
                    Arrays.fill(bArr2, (byte) 0);
                }
                long j2 = this.mLastPTS >= j ? this.mLastPTS + 2 : j;
                com.tencent.liteav.audio.d listener;
                if (this.mHWEcoder == null) {
                    nativeAddEffectAndSoftEnc(this.mEffectorObj, this.mSoftEncObj, bArr);
                    while (true) {
                        byte[] nativeReadOneEncFrame = nativeReadOneEncFrame();
                        if (nativeReadOneEncFrame == null) {
                            break;
                        }
                        this.mLastPTS = j2;
                        listener = getListener();
                        if (listener != null) {
                            listener.onRecordEncData(nativeReadOneEncFrame, j2, this.mSampleRate, this.mChannels, this.mBits);
                        }
                        j2 += (long) (1024000 / this.mSampleRate);
                    }
                } else {
                    nativeAddEffect(this.mEffectorObj, bArr2);
                    do {
                        bArr2 = nativeReadOneFrame(this.mEffectorObj, ((this.mChannels * Filter.K) * this.mBits) / 8);
                        if (bArr2 != null) {
                            this.mLastPTS = j2;
                            if (this.mHWEcoder != null) {
                                doHWEncode(bArr2, j2);
                            } else {
                                listener = getListener();
                                if (listener != null) {
                                    listener.onRecordEncData(bArr2, j2, this.mSampleRate, this.mChannels, this.mBits);
                                }
                            }
                        }
                        j2 += (long) (1024000 / this.mSampleRate);
                    } while (bArr2 != null);
                }
            } else {
                TXCLog.e(TAG, "effectorObj is null");
            }
        }
    }

    private void initEffectAndEnc() {
        this.mEffectorObj = nativeCreateEffector(this.mSampleRate, this.mChannels, this.mBits);
        if (this.mSampleRate != this.mRecordSampleRate) {
            nativeSetInputInfo(this.mEffectorObj, this.mRecordSampleRate, this.mChannels, this.mBits);
        }
        boolean z = this.mIsEarphoneOn || this.mAECType == TXEAudioDef.TXE_AEC_SYSTEM;
        nativeSetReverbType(this.mEffectorObj, this.mReverbType);
        nativeMixBGM(this.mEffectorObj, z);
        nativeSetNoiseSuppression(this.mEffectorObj, this.mNSMode);
        nativeSetChangerType(this.mEffectorObj, this.mVoiceKind, this.mVoiceEnvironment);
        if (this.mEnableHWEncoder) {
            this.mHWEcoder = new e();
            com.tencent.liteav.audio.d listener = getListener();
            this.mHWEcoder.a(TXEAudioDef.TXE_AUDIO_TYPE_AAC, this.mSampleRate, this.mChannels, this.mBits, listener != null ? new WeakReference(listener) : null);
            this.mBuf = new byte[(((this.mChannels * Filter.K) * this.mBits) / 8)];
            this.mPosition = 0;
        } else {
            this.mSoftEncObj = nativeCreateSoftEncoder(this.mSampleRate, this.mChannels, this.mBits);
        }
        this.mLastPTS = 0;
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("初始化直播录制:录制模式 = ");
        stringBuilder.append(this.mCustomRecord == null ? "麦克风录制" : "用户自定义录制");
        stringBuilder.append(", 录制采样率 = ");
        stringBuilder.append(this.mRecordSampleRate);
        stringBuilder.append(", 输出采样率 = ");
        stringBuilder.append(this.mSampleRate);
        stringBuilder.append(", 是否混音 = ");
        stringBuilder.append(z);
        stringBuilder.append(", 混响模式 = ");
        stringBuilder.append(this.mReverbType);
        stringBuilder.append(", 是否启动硬编码 = ");
        stringBuilder.append(this.mEnableHWEncoder);
        stringBuilder.append(", 噪声抑制mode = ");
        stringBuilder.append(this.mNSMode);
        TXCLog.i(str, stringBuilder.toString());
    }

    private void uninitEffectAndEnc() {
        if (this.mEffectorObj != 0) {
            nativeDestroyEffector(this.mEffectorObj);
            this.mEffectorObj = 0;
        }
        if (this.mSoftEncObj != 0) {
            nativeDestorySoftEncoder(this.mSoftEncObj);
            this.mSoftEncObj = 0;
        }
        if (this.mHWEcoder != null) {
            this.mHWEcoder.a();
            this.mHWEcoder = null;
        }
    }

    private void doHWEncode(byte[] bArr, long j) {
        if (bArr == null || bArr.length == 0 || this.mBuf == null || this.mHWEcoder == null) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("doHWEncode failed! data = ");
            stringBuilder.append(bArr);
            stringBuilder.append(", buf = ");
            stringBuilder.append(this.mBuf);
            stringBuilder.append(", encoder = ");
            stringBuilder.append(this.mHWEcoder);
            TXCLog.e(str, stringBuilder.toString());
            return;
        }
        int length = this.mBuf.length - this.mPosition;
        if (length > bArr.length) {
            length = bArr.length;
        }
        System.arraycopy(bArr, 0, this.mBuf, this.mPosition, length);
        this.mPosition += length;
        if (this.mPosition == this.mBuf.length) {
            this.mPosition = 0;
            this.mHWEcoder.a(this.mBuf, j);
        }
    }

    private void onRecordRawPcmData(byte[] bArr, int i, int i2) {
        com.tencent.liteav.audio.d listener = getListener();
        if (listener != null) {
            long timeTick = TXCTimeUtil.getTimeTick();
            int i3 = this.mBits;
            boolean z = TXCLiveBGMPlayer.getInstance().isRunning() && !this.mNeedMix;
            listener.onRecordRawPcmData(bArr, timeTick, i, i2, i3, z);
        }
    }

    private void onRecordPcmData(byte[] bArr) {
        com.tencent.liteav.audio.d listener = getListener();
        if (listener != null) {
            listener.onRecordPcmData(bArr, TXCTimeUtil.getTimeTick(), this.mSampleRate, this.mChannels, this.mBits);
        }
    }

    public void OnHeadsetState(boolean z) {
        setEarphoneOn(z);
    }
}

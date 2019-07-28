package com.tencent.liteav.audio.impl.Record;

import android.content.Context;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.audio.d;
import com.tencent.liteav.basic.a.a;
import com.tencent.liteav.basic.log.TXCLog;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;

/* compiled from: TXCAudioBaseRecordController */
public abstract class c {
    private static final String TAG;
    protected int mAECType = TXEAudioDef.TXE_AEC_NONE;
    protected int mBits = a.h;
    protected int mChannels = a.f;
    protected Context mContext;
    protected boolean mEnableHWEncoder = false;
    protected boolean mIsCustomRecord = false;
    protected boolean mIsEarphoneOn = false;
    protected boolean mIsMute = false;
    protected int mNSMode = -1;
    protected int mReverbType = TXEAudioDef.TXE_REVERB_TYPE_0;
    protected int mSampleRate = a.e;
    protected int mVoiceEnvironment = -1;
    protected int mVoiceKind = -1;
    protected float mVolume = 1.0f;
    protected WeakReference<d> mWeakRecordListener;

    public abstract boolean isRecording();

    public abstract void sendCustomPCMData(byte[] bArr);

    public void setReverbParam(int i, float f) {
    }

    public abstract int stopRecord();

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(c.class.getSimpleName());
        TAG = stringBuilder.toString();
    }

    public synchronized void setListener(d dVar) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setListener:");
        stringBuilder.append(dVar);
        TXCLog.i(str, stringBuilder.toString());
        if (dVar == null) {
            this.mWeakRecordListener = null;
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

    public void setSamplerate(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setSampleRate: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mSampleRate = i;
    }

    public void setChannels(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setChannels: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mChannels = i;
    }

    public void setReverbType(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setReverbType: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mReverbType = i;
    }

    public void setAECType(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setAECType: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mAECType = i;
    }

    public void enableHWEncoder(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("enableHWEncoder: ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        this.mEnableHWEncoder = z;
    }

    public void setMute(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setMute: ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        this.mIsMute = z;
    }

    public void setVolume(float f) {
        if (f <= 0.2f) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setVolume, warning volume too low : ");
            stringBuilder.append(f);
            TXCLog.w(str, stringBuilder.toString());
        }
        if (f < CropImageView.DEFAULT_ASPECT_RATIO) {
            f = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        this.mVolume = f;
    }

    public void setEarphoneOn(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setEarphoneOn: ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        this.mIsEarphoneOn = z;
    }

    public void setNoiseSuppression(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setNoiseSuppression: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.mNSMode = i;
    }

    public void setChangerType(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setChangerType: ");
        stringBuilder.append(i);
        stringBuilder.append(" ");
        stringBuilder.append(i2);
        TXCLog.i(str, stringBuilder.toString());
        this.mVoiceKind = i;
        this.mVoiceEnvironment = i2;
    }

    public int startRecord(Context context) {
        if (context != null) {
            this.mContext = context.getApplicationContext();
        }
        return 0;
    }

    public void setIsCustomRecord(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setIsCustomRecord: ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        this.mIsCustomRecord = z;
    }
}

package com.tencent.liteav.audio.impl.Record;

import android.content.Context;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.audio.d;
import com.tencent.liteav.audio.impl.TXCTraeJNI;
import com.tencent.liteav.basic.log.TXCLog;

/* compiled from: TXCAudioTraeRecordController */
public class g extends c {
    public void sendCustomPCMData(byte[] bArr) {
    }

    public int startRecord(Context context) {
        TXCLog.i("AudioCenter:TXCAudioTraeRecordController", "trae startRecord");
        super.startRecord(context);
        TXCTraeJNI.InitTraeEngineLibrary(this.mContext);
        TXCTraeJNI.setTraeRecordListener(this.mWeakRecordListener);
        TXCTraeJNI.nativeTraeStartRecord(context, this.mSampleRate, this.mChannels, this.mBits);
        TXCTraeJNI.nativeTraeSetChangerType(this.mVoiceKind, this.mVoiceEnvironment);
        if (!(this.mWeakRecordListener == null || this.mWeakRecordListener.get() == null)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("TRAE-AEC,采样率(");
            stringBuilder.append(this.mSampleRate);
            stringBuilder.append("|");
            stringBuilder.append(this.mSampleRate);
            stringBuilder.append("),声道数");
            stringBuilder.append(this.mChannels);
            ((d) this.mWeakRecordListener.get()).onRecordError(TXEAudioDef.TXE_AUDIO_NOTIFY_AUDIO_INFO, stringBuilder.toString());
        }
        return 0;
    }

    public int stopRecord() {
        TXCLog.i("AudioCenter:TXCAudioTraeRecordController", "trae stopRecord");
        TXCTraeJNI.nativeTraeStopRecord(true);
        TXCTraeJNI.setTraeRecordListener(null);
        return 0;
    }

    public boolean isRecording() {
        return TXCTraeJNI.nativeTraeIsRecording();
    }

    public void setReverbType(int i) {
        super.setReverbType(i);
        TXCTraeJNI.nativeTraeSetRecordReverb(i);
    }

    public void setChangerType(int i, int i2) {
        super.setChangerType(i, i2);
        TXCTraeJNI.nativeTraeSetChangerType(i, i2);
    }

    public void setVolume(float f) {
        super.setVolume(f);
        TXCTraeJNI.nativeTraeSetVolume(f);
    }

    public void setMute(boolean z) {
        super.setMute(z);
        TXCTraeJNI.nativeTraeSetRecordMute(z);
        this.mIsMute = z;
    }
}

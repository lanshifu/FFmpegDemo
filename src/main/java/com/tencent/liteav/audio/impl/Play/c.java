package com.tencent.liteav.audio.impl.Play;

import android.content.Context;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.audio.impl.TXCTraeJNI;
import com.tencent.liteav.basic.f.b;
import com.tencent.liteav.basic.g.a;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.ugc.TXRecordCommon;

/* compiled from: TXCAudioTraePlayController */
public class c extends TXCAudioBasePlayController implements com.tencent.liteav.audio.c {
    private static final String a;
    private a b;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(c.class.getSimpleName());
        a = stringBuilder.toString();
    }

    public c(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected */
    public void finalize() {
        if (this.mJitterBuffer != 0) {
            nativeDestoryJitterBuffer(this.mJitterBuffer);
            this.mJitterBuffer = 0;
        }
    }

    public static void a(Context context, int i) {
        TXCTraeJNI.setContext(context);
        TXCTraeJNI.nativeSetAudioMode(i);
    }

    public int startPlay() {
        TXCLog.i(a, "start play audio!");
        if (this.mIsPlaying) {
            TXCLog.e(a, "repeat start play audio, ignore it!");
            return TXEAudioDef.TXE_AUDIO_PLAY_ERR_REPEAT_OPTION;
        }
        super.startPlay();
        TXCLog.i(a, "finish start play audio!");
        return TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK;
    }

    public int stopPlay() {
        TXCLog.i(a, "stop play audio!");
        if (this.mIsPlaying) {
            super.stopPlay();
            if (this.mJitterBuffer != 0) {
                nativeDestoryJitterBuffer(this.mJitterBuffer);
                this.mJitterBuffer = 0;
            }
            TXCTraeJNI.traeStopPlay();
            this.b = null;
            TXCLog.i(a, "finish stop play audio!");
            return TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK;
        }
        TXCLog.e(a, "repeat stop play audio, ignore it!");
        return TXEAudioDef.TXE_AUDIO_PLAY_ERR_REPEAT_OPTION;
    }

    public int playData(a aVar) {
        if (aVar == null) {
            return TXEAudioDef.TXE_AUDIO_COMMON_ERR_INVALID_PARAMS;
        }
        String str;
        StringBuilder stringBuilder;
        if (com.tencent.liteav.basic.a.a.k == aVar.packetType || com.tencent.liteav.basic.a.a.l == aVar.packetType || com.tencent.liteav.basic.a.a.m == aVar.packetType) {
            if (com.tencent.liteav.basic.a.a.k == aVar.packetType) {
                str = a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("soft dec, recv aac seq ");
                stringBuilder.append(aVar.audioData);
                TXCLog.i(str, stringBuilder.toString());
            }
            if (this.mIsPlaying) {
                if (this.mJitterBuffer == 0) {
                    this.mJitterBuffer = nativeCreateJitterBuffer(false, this);
                    if (this.mJitterBuffer != 0) {
                        nativeSetSmoothMode(this.mJitterBuffer, (long) this.mSmoothMode);
                        nativeSetSmoothAdjust(this.mJitterBuffer, b.a().a("Audio", "SmoothAdjust"));
                        nativeSetCacheTime(this.mJitterBuffer, this.mCacheTime);
                        nativeSetMute(this.mJitterBuffer, this.mIsMute);
                        nativeEnableRealTimePlay(this.mJitterBuffer, this.mIsRealTimePlay);
                        nativeEnableAutoAdjustCache(this.mJitterBuffer, this.mIsAutoAdjustCache);
                        nativeSetAutoAdjustMaxCache(this.mJitterBuffer, this.mAutoAdjustMaxCache);
                        nativeSetAutoAdjustMinCache(this.mJitterBuffer, this.mAutoAdjustMinCache);
                        nativeSetSmoothSpeed(this.mJitterBuffer, b.a().b("Audio", "SmoothSpeed"));
                        nativeSetJitterCycle(this.mJitterBuffer, b.a().a("Audio", "LIVE_JitterCycle"));
                        nativeSetRealtimeJitterCycle(this.mJitterBuffer, b.a().a("Audio", "RTC_JitterCycle"));
                        nativeSetLoadingThreshold(this.mJitterBuffer, b.a().a("Audio", "LoadingThreshold"));
                        nativeSetRTCPlayHungryTimeThreshold(this.mJitterBuffer, (int) b.a().a("Audio", "RtcPlayHungryTimeThreshold"));
                    } else {
                        TXCLog.e(a, "soft dec, create jitterbuffer failed!!");
                    }
                    str = a;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("soft dec, create jitterbuffer with id ");
                    stringBuilder.append(this.mJitterBuffer);
                    TXCLog.e(str, stringBuilder.toString());
                }
                if (this.mJitterBuffer != 0) {
                    nativeAddData(this.mJitterBuffer, aVar.audioData, aVar.packetType, aVar.timestamp);
                    if (com.tencent.liteav.basic.a.a.k == aVar.packetType) {
                        this.b = new a();
                        this.b.sampleRate = nativeGetSamplerate(this.mJitterBuffer);
                        this.b.channelsPerSample = nativeGetChannel(this.mJitterBuffer);
                        this.b.bitsPerChannel = com.tencent.liteav.audio.b.c;
                        this.b.packetType = com.tencent.liteav.basic.a.a.k;
                        aVar = new a();
                        aVar.sampleRate = TXRecordCommon.AUDIO_SAMPLERATE_48000;
                        aVar.channelsPerSample = 2;
                        aVar.bitsPerChannel = 16;
                        aVar.packetType = com.tencent.liteav.basic.a.a.k;
                        onPlayAudioInfoChanged(this.b, aVar);
                    } else if (com.tencent.liteav.basic.a.a.m == aVar.packetType && this.b == null) {
                        this.b = new a();
                        this.b.sampleRate = nativeGetSamplerate(this.mJitterBuffer);
                        this.b.channelsPerSample = nativeGetChannel(this.mJitterBuffer);
                        this.b.bitsPerChannel = com.tencent.liteav.audio.b.c;
                        this.b.packetType = com.tencent.liteav.basic.a.a.k;
                        aVar = new a();
                        aVar.sampleRate = TXRecordCommon.AUDIO_SAMPLERATE_48000;
                        aVar.channelsPerSample = 2;
                        aVar.bitsPerChannel = 16;
                        aVar.packetType = com.tencent.liteav.basic.a.a.k;
                        onPlayAudioInfoChanged(this.b, aVar);
                    }
                    return TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK;
                }
                onPlayError(TXEAudioDef.TXE_AUDIO_PLAY_ERR_NOT_CREATE_JIT, "jitterbuf 还未创建");
                TXCLog.e(a, "soft dec, jitterbuffer not created yet!!");
                return TXEAudioDef.TXE_AUDIO_PLAY_ERR_NOT_CREATE_JIT;
            }
            onPlayError(TXEAudioDef.TXE_AUDIO_PLAY_ERR_INVALID_STATE, "播放器还没有启动");
            TXCLog.w(a, "sotf dec, invalid state. player not started yet!");
            return TXEAudioDef.TXE_AUDIO_PLAY_ERR_INVALID_STATE;
        }
        str = a;
        stringBuilder = new StringBuilder();
        stringBuilder.append("soft dec, not support audio type , packet type : ");
        stringBuilder.append(aVar.packetType);
        TXCLog.e(str, stringBuilder.toString());
        int i = TXEAudioDef.TXE_AUDIO_PLAY_ERR_AUDIO_TYPE_NOT_SUPPORT;
        stringBuilder = new StringBuilder();
        stringBuilder.append("解码器不支持当前音频格式，包类型:");
        stringBuilder.append(aVar.packetType);
        onPlayError(i, stringBuilder.toString());
        return TXEAudioDef.TXE_AUDIO_PLAY_ERR_AUDIO_TYPE_NOT_SUPPORT;
    }

    public void onPlayAudioInfoChanged(a aVar, a aVar2) {
        if (this.b == null) {
            this.b = aVar;
        }
        if (this.mListener != null) {
            this.mListener.onPlayAudioInfoChanged(aVar, aVar2);
        }
        TXCTraeJNI.traeStartPlay(this.mContext);
    }
}

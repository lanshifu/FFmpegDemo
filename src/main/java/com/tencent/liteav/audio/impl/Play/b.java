package com.tencent.liteav.audio.impl.Play;

import android.content.Context;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.basic.g.a;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.ugc.TXRecordCommon;
import java.lang.ref.WeakReference;

/* compiled from: TXCAudioSysPlayController */
public class b extends TXCAudioBasePlayController {
    public static final String a;
    private a b;
    private a c;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(b.class.getSimpleName());
        a = stringBuilder.toString();
    }

    public b(Context context) {
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
        d.a().a(context, i);
    }

    public long getCacheDuration() {
        if (this.mIsHWAcceleration) {
            return this.b.a() + nativeGetCacheDuration(this.mJitterBuffer);
        }
        return nativeGetCacheDuration(this.mJitterBuffer);
    }

    public int startPlay() {
        TXCLog.i(a, "start play audio!");
        if (this.mIsPlaying) {
            TXCLog.e(a, "repeat start play audio, ignore it!");
            return TXEAudioDef.TXE_AUDIO_PLAY_ERR_REPEAT_OPTION;
        }
        if (this.mIsHWAcceleration) {
            this.b = new a();
            this.b.a(new WeakReference(this));
        }
        this.mIsPlaying = true;
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
            if (TXCAudioBasePlayController.nativeIsTracksEmpty()) {
                d.a().c();
            }
            if (this.b != null) {
                this.b.b();
                this.b = null;
            }
            this.c = null;
            TXCLog.i(a, "finish stop play audio!");
            return TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK;
        }
        TXCLog.e(a, "repeat stop play audio, ignore it!");
        return TXEAudioDef.TXE_AUDIO_PLAY_ERR_REPEAT_OPTION;
    }

    public int playData(a aVar) {
        if (this.mIsHWAcceleration) {
            return b(aVar);
        }
        return a(aVar);
    }

    private int a(a aVar) {
        if (aVar == null) {
            return TXEAudioDef.TXE_AUDIO_COMMON_ERR_INVALID_PARAMS;
        }
        String str;
        StringBuilder stringBuilder;
        int i = TXEAudioDef.TXE_AUDIO_TYPE_INVALID;
        if (com.tencent.liteav.basic.a.a.k == aVar.packetType || com.tencent.liteav.basic.a.a.l == aVar.packetType) {
            i = TXEAudioDef.TXE_AUDIO_TYPE_AAC;
        } else if (com.tencent.liteav.basic.a.a.m == aVar.packetType) {
            i = TXEAudioDef.TXE_AUDIO_TYPE_MP3;
        } else {
            str = a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("soft dec, not support audio type , packet type : ");
            stringBuilder.append(aVar.packetType);
            TXCLog.e(str, stringBuilder.toString());
            i = TXEAudioDef.TXE_AUDIO_PLAY_ERR_AUDIO_TYPE_NOT_SUPPORT;
            stringBuilder = new StringBuilder();
            stringBuilder.append("解码器不支持当前音频格式，包类型:");
            stringBuilder.append(aVar.packetType);
            onPlayError(i, stringBuilder.toString());
            return TXEAudioDef.TXE_AUDIO_PLAY_ERR_AUDIO_TYPE_NOT_SUPPORT;
        }
        if (this.mIsPlaying) {
            if (this.mJitterBuffer == 0) {
                this.mJitterBuffer = nativeCreateJitterBuffer(false, this);
                if (this.mJitterBuffer != 0) {
                    nativeSetSmoothMode(this.mJitterBuffer, (long) this.mSmoothMode);
                    nativeSetSmoothAdjust(this.mJitterBuffer, com.tencent.liteav.basic.f.b.a().a("Audio", "SmoothAdjust"));
                    nativeSetCacheTime(this.mJitterBuffer, this.mCacheTime);
                    nativeSetMute(this.mJitterBuffer, this.mIsMute);
                    nativeEnableRealTimePlay(this.mJitterBuffer, this.mIsRealTimePlay);
                    nativeEnableAutoAdjustCache(this.mJitterBuffer, this.mIsAutoAdjustCache);
                    nativeSetAutoAdjustMaxCache(this.mJitterBuffer, this.mAutoAdjustMaxCache);
                    nativeSetAutoAdjustMinCache(this.mJitterBuffer, this.mAutoAdjustMinCache);
                    nativeSetSmoothSpeed(this.mJitterBuffer, com.tencent.liteav.basic.f.b.a().b("Audio", "SmoothSpeed"));
                    nativeSetJitterCycle(this.mJitterBuffer, com.tencent.liteav.basic.f.b.a().a("Audio", "LIVE_JitterCycle"));
                    nativeSetRealtimeJitterCycle(this.mJitterBuffer, com.tencent.liteav.basic.f.b.a().a("Audio", "RTC_JitterCycle"));
                    nativeSetLoadingThreshold(this.mJitterBuffer, com.tencent.liteav.basic.f.b.a().a("Audio", "LoadingThreshold"));
                    nativeSetRTCPlayHungryTimeThreshold(this.mJitterBuffer, (int) com.tencent.liteav.basic.f.b.a().a("Audio", "RtcPlayHungryTimeThreshold"));
                } else {
                    TXCLog.e(a, "soft dec, create jitterbuffer failed!!");
                }
                str = a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("soft dec, create jitterbuffer with id ");
                stringBuilder.append(this.mJitterBuffer);
                TXCLog.e(str, stringBuilder.toString());
            }
            if (com.tencent.liteav.basic.a.a.k == aVar.packetType) {
                str = a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("soft dec, recv aac seq ");
                stringBuilder.append(com.tencent.liteav.audio.impl.b.a(aVar.audioData));
                TXCLog.i(str, stringBuilder.toString());
            }
            if (this.mJitterBuffer != 0) {
                nativeAddData(this.mJitterBuffer, aVar.audioData, aVar.packetType, aVar.timestamp);
                if (com.tencent.liteav.basic.a.a.k == aVar.packetType) {
                    this.c = new a();
                    this.c.sampleRate = nativeGetSamplerate(this.mJitterBuffer);
                    this.c.channelsPerSample = nativeGetChannel(this.mJitterBuffer);
                    this.c.bitsPerChannel = com.tencent.liteav.audio.b.c;
                    this.c.packetType = com.tencent.liteav.basic.a.a.k;
                    aVar = new a();
                    aVar.sampleRate = TXRecordCommon.AUDIO_SAMPLERATE_48000;
                    aVar.channelsPerSample = 2;
                    aVar.bitsPerChannel = 16;
                    aVar.packetType = com.tencent.liteav.basic.a.a.k;
                    onPlayAudioInfoChanged(this.c, aVar);
                } else if (com.tencent.liteav.basic.a.a.m == aVar.packetType && this.c == null) {
                    this.c = new a();
                    this.c.sampleRate = nativeGetSamplerate(this.mJitterBuffer);
                    this.c.channelsPerSample = nativeGetChannel(this.mJitterBuffer);
                    this.c.bitsPerChannel = com.tencent.liteav.audio.b.c;
                    this.c.packetType = com.tencent.liteav.basic.a.a.k;
                    aVar = new a();
                    aVar.sampleRate = TXRecordCommon.AUDIO_SAMPLERATE_48000;
                    aVar.channelsPerSample = 2;
                    aVar.bitsPerChannel = 16;
                    aVar.packetType = com.tencent.liteav.basic.a.a.k;
                    onPlayAudioInfoChanged(this.c, aVar);
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

    private int b(a aVar) {
        if (aVar == null) {
            return TXEAudioDef.TXE_AUDIO_COMMON_ERR_INVALID_PARAMS;
        }
        String str;
        StringBuilder stringBuilder;
        int i = TXEAudioDef.TXE_AUDIO_TYPE_INVALID;
        if (com.tencent.liteav.basic.a.a.k == aVar.packetType || com.tencent.liteav.basic.a.a.l == aVar.packetType) {
            i = TXEAudioDef.TXE_AUDIO_TYPE_AAC;
        } else if (com.tencent.liteav.basic.a.a.m == aVar.packetType) {
            i = TXEAudioDef.TXE_AUDIO_TYPE_MP3;
        } else {
            str = a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("hw dec, not support audio type , packet type : ");
            stringBuilder.append(aVar.packetType);
            TXCLog.e(str, stringBuilder.toString());
            i = TXEAudioDef.TXE_AUDIO_PLAY_ERR_AUDIO_TYPE_NOT_SUPPORT;
            stringBuilder = new StringBuilder();
            stringBuilder.append("解码器不支持当前音频格式，包类型:");
            stringBuilder.append(aVar.packetType);
            onPlayError(i, stringBuilder.toString());
            return TXEAudioDef.TXE_AUDIO_PLAY_ERR_AUDIO_TYPE_NOT_SUPPORT;
        }
        if (this.mIsPlaying) {
            if (this.mJitterBuffer == 0) {
                this.mJitterBuffer = nativeCreateJitterBuffer(true, this);
                if (this.mJitterBuffer != 0) {
                    nativeSetSmoothMode(this.mJitterBuffer, (long) this.mSmoothMode);
                    nativeSetSmoothAdjust(this.mJitterBuffer, com.tencent.liteav.basic.f.b.a().a("Audio", "SmoothAdjust"));
                    nativeSetCacheTime(this.mJitterBuffer, this.mCacheTime);
                    nativeSetMute(this.mJitterBuffer, this.mIsMute);
                    nativeEnableRealTimePlay(this.mJitterBuffer, this.mIsRealTimePlay);
                    nativeEnableAutoAdjustCache(this.mJitterBuffer, this.mIsAutoAdjustCache);
                    nativeSetAutoAdjustMaxCache(this.mJitterBuffer, this.mAutoAdjustMaxCache);
                    nativeSetAutoAdjustMinCache(this.mJitterBuffer, this.mAutoAdjustMinCache);
                    nativeSetSmoothSpeed(this.mJitterBuffer, com.tencent.liteav.basic.f.b.a().b("Audio", "SmoothSpeed"));
                    nativeSetJitterCycle(this.mJitterBuffer, com.tencent.liteav.basic.f.b.a().a("Audio", "LIVE_JitterCycle"));
                    nativeSetRealtimeJitterCycle(this.mJitterBuffer, com.tencent.liteav.basic.f.b.a().a("Audio", "RTC_JitterCycle"));
                    nativeSetLoadingThreshold(this.mJitterBuffer, com.tencent.liteav.basic.f.b.a().a("Audio", "LoadingThreshold"));
                    nativeSetRTCPlayHungryTimeThreshold(this.mJitterBuffer, (int) com.tencent.liteav.basic.f.b.a().a("Audio", "RtcPlayHungryTimeThreshold"));
                } else {
                    TXCLog.e(a, "hw dec, create jitterbuffer failed!!");
                }
                str = a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("hw dec, create jitterbuffer with id ");
                stringBuilder.append(this.mJitterBuffer);
                TXCLog.e(str, stringBuilder.toString());
            }
            if (com.tencent.liteav.basic.a.a.k == aVar.packetType) {
                str = a;
                stringBuilder = new StringBuilder();
                stringBuilder.append("soft dec, recv aac seq ");
                stringBuilder.append(com.tencent.liteav.audio.impl.b.a(aVar.audioData));
                TXCLog.i(str, stringBuilder.toString());
            }
            if (this.b != null) {
                this.b.a(aVar);
            }
            return TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK;
        }
        onPlayError(TXEAudioDef.TXE_AUDIO_PLAY_ERR_INVALID_STATE, "播放器还没有启动");
        TXCLog.w(a, "hw dec, invalid state. player not started yet!");
        return TXEAudioDef.TXE_AUDIO_PLAY_ERR_INVALID_STATE;
    }

    public void onPlayAudioInfoChanged(a aVar, a aVar2) {
        if (this.c == null) {
            this.c = aVar;
        }
        if (this.mListener != null) {
            this.mListener.onPlayAudioInfoChanged(aVar, aVar2);
        }
        if (!TXCAudioBasePlayController.nativeIsTracksEmpty()) {
            d.a().b();
        }
    }

    public void onPlayPcmData(byte[] bArr, long j) {
        super.onPlayPcmData(bArr, j);
        if (this.mJitterBuffer != 0 && this.mIsHWAcceleration) {
            nativeAddData(this.mJitterBuffer, bArr, com.tencent.liteav.basic.a.a.n, j);
        }
    }
}

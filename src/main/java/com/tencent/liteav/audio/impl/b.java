package com.tencent.liteav.audio.impl;

import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.audio.impl.Play.d;
import com.tencent.liteav.audio.impl.Record.f;
import com.tencent.ugc.TXRecordCommon;

/* compiled from: TXCAudioUtil */
public class b {
    private static int[] a = new int[]{96000, 88200, 64000, TXRecordCommon.AUDIO_SAMPLERATE_48000, TXRecordCommon.AUDIO_SAMPLERATE_44100, TXRecordCommon.AUDIO_SAMPLERATE_32000, 24000, 22050, TXRecordCommon.AUDIO_SAMPLERATE_16000, 12000, 11025, TXRecordCommon.AUDIO_SAMPLERATE_8000, 7350};

    public static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append("0x");
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    public static int a(int i) {
        return (i >= a.length || i < 0) ? 0 : a[i];
    }

    public static int b(int i) {
        if (i == TXEAudioDef.TXE_AEC_TRAE) {
            if (d.a().d()) {
                return TXEAudioDef.TXE_AUDIO_RECORD_ERR_CUR_PLAYER_INVALID;
            }
        } else if (TXCTraeJNI.nativeTraeIsPlaying()) {
            return TXEAudioDef.TXE_AUDIO_RECORD_ERR_CUR_PLAYER_INVALID;
        }
        return TXEAudioDef.TXE_AUDIO_COMMON_ERR_OK;
    }

    public static int c(int i) {
        if (i == TXEAudioDef.TXE_AEC_TRAE) {
            if (f.a().c()) {
                return TXEAudioDef.TXE_AUDIO_RECORD_ERR_CUR_RECORDER_INVALID;
            }
        } else if (TXCTraeJNI.nativeTraeIsRecording()) {
            return TXEAudioDef.TXE_AUDIO_RECORD_ERR_CUR_RECORDER_INVALID;
        }
        return TXEAudioDef.TXE_AUDIO_COMMON_ERR_OK;
    }
}

package com.youdao.sdk.ydonlinetranslate.other;

import android.content.Context;
import com.tencent.rtmp.TXLiveConstants;
import com.youdao.sdk.app.b;
import com.youdao.sdk.app.c;
import com.youdao.sdk.app.i;
import com.youdao.sdk.ydonlinetranslate.SpeechTranslateParameters;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import java.util.HashMap;

public class e {
    public static void a(String str, TranslateListener translateListener, SpeechTranslateParameters speechTranslateParameters, Context context, String str2) {
        HashMap hashMap = new HashMap();
        String[] a = b.a(speechTranslateParameters.paramString(context, str));
        hashMap.put("s", a[0]);
        hashMap.put("et", a[1]);
        String str3 = i.a() ? "http://openapi-sg.youdao.com" : "http://openapi.youdao.com";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str3);
        stringBuilder.append("/speechtransopenapi");
        c.a(stringBuilder.toString(), hashMap, new f(translateListener, str, str2), speechTranslateParameters.getTimeout());
    }

    private static TranslateErrorCode b(int i) {
        TranslateErrorCode a = g.a(i);
        if (a != TranslateErrorCode.UN_SPECIFIC_ERROR) {
            return a;
        }
        if (i == TXLiveConstants.PLAY_EVT_PLAY_PROGRESS) {
            return TranslateErrorCode.TRANS_SPEECH_FORMAT_ERROR;
        }
        if (i == TXLiveConstants.PLAY_EVT_PLAY_END) {
            return TranslateErrorCode.TRANS_SPEECH_SOUND_ERROR;
        }
        if (i == 2201) {
            return TranslateErrorCode.TRANS_SPEECH_DECRYPT_ERROR;
        }
        if (i == 2301) {
            return TranslateErrorCode.TRANS_SPEECH_SERVER_ERROR;
        }
        if (i == 2411) {
            return TranslateErrorCode.TRANS_SPEECH_FREQUENCY_ERROR;
        }
        if (i == 2412) {
            return TranslateErrorCode.TRANS_SPEECH_MAX_QUERY_LENGTH_ERROR;
        }
        if (i == 3001) {
            return TranslateErrorCode.TRANS_SPEECH_SOUND_FORMAT_ERROR;
        }
        if (i == 3002) {
            return TranslateErrorCode.TRANS_SPEECH_SOUND_RATE_ERROR;
        }
        if (i == 3003) {
            return TranslateErrorCode.TRANS_SPEECH_SOUND_CHANNEL_ERROR;
        }
        if (i == TXLiveConstants.PUSH_WARNING_SERVER_DISCONNECT) {
            return TranslateErrorCode.TRANS_SPEECH_SOUND_UPLOADTYPE_ERROR;
        }
        if (i == 3005) {
            return TranslateErrorCode.TRANS_SPEECH_SOUND_LANGUAGE_ERROR;
        }
        if (i == 3006) {
            return TranslateErrorCode.TRANS_SPEECH_SOUND_RECOGNIZE_ERROR;
        }
        if (i == 3007) {
            return TranslateErrorCode.TRANS_SPEECH_SOUND_LARGE_ERROR;
        }
        if (i == 3008) {
            return TranslateErrorCode.TRANS_SPEECH_SOUND_LONG_ERROR;
        }
        if (i == 3009) {
            return TranslateErrorCode.TRANS_SPEECH_SOUND_VOICE_TYPE_ERROR;
        }
        if (i == 3201) {
            return TranslateErrorCode.TRANS_SPEECH_SOUND_DECRYPT_ERROR;
        }
        if (i == 3301) {
            return TranslateErrorCode.TRANS_SPEECH_RECOGNIZE_ERROR;
        }
        if (i == 3302) {
            return TranslateErrorCode.TRANS_SPEECH_TRANSLATE_ERROR;
        }
        if (i == 3303) {
            return TranslateErrorCode.TRANS_SPEECH_TRANSLATE_SERVER_ERROR;
        }
        if (i == 3411) {
            return TranslateErrorCode.TRANS_SPEECH_TRANSLATE_FREQUENCY_ERROR;
        }
        if (i == 3412) {
            return TranslateErrorCode.TRANS_SPEECH_TRANSLATE_MAX_QUERY_LENGTH_ERROR;
        }
        return TranslateErrorCode.UN_SPECIFIC_ERROR;
    }
}

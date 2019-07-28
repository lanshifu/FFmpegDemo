package com.youdao.sdk.ydonlinetranslate;

import android.content.Context;
import com.youdao.sdk.app.i;
import com.youdao.sdk.ydonlinetranslate.other.e;
import com.youdao.sdk.ydtranslate.TranslateListener;
import defpackage.sy;

public class SpeechTranslate {
    SpeechTranslateParameters parameters;

    private SpeechTranslate(SpeechTranslateParameters speechTranslateParameters) {
        this.parameters = speechTranslateParameters;
    }

    public static SpeechTranslate getInstance(SpeechTranslateParameters speechTranslateParameters) {
        return new SpeechTranslate(speechTranslateParameters);
    }

    public void lookup(String str, String str2, TranslateListener translateListener) {
        if (translateListener == null) {
            sy.c("translate result callback is null listener!");
            return;
        }
        Context b = i.b();
        if (b == null) {
            sy.c("This application may be not init,please use YouDaoApplication init");
        }
        e.a(str, translateListener, this.parameters, b, str2);
    }
}

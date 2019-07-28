package com.youdao.sdk.ydonlinetranslate;

import android.content.Context;
import com.youdao.sdk.app.i;
import com.youdao.sdk.ydonlinetranslate.other.g;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import com.youdao.sdk.ydtranslate.TranslateParameters;
import defpackage.sy;

public class Translator {
    TranslateParameters parameters;

    private Translator(TranslateParameters translateParameters) {
        this.parameters = translateParameters;
    }

    public static Translator getInstance(TranslateParameters translateParameters) {
        return new Translator(translateParameters);
    }

    public synchronized void lookup(String str, String str2, TranslateListener translateListener) {
        if (translateListener == null) {
            sy.c("translate result callback is null listener!");
        } else if (translateListener == null) {
            sy.c("translate result callback is null listener!");
            translateListener.onError(TranslateErrorCode.INPUT_PARAM_ILLEGAL, str2);
        } else {
            Context b = i.b();
            if (b == null) {
                sy.c("This application may be not init,please use YouDaoApplication init");
                return;
            }
            this.parameters.getFrom();
            this.parameters.getTo();
            g.a(str, translateListener, this.parameters, b, str2);
        }
    }
}

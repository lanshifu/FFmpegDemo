package com.youdao.sdk.ydonlinetranslate;

import android.content.Context;
import com.youdao.sdk.app.i;
import com.youdao.sdk.ydonlinetranslate.other.b;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import defpackage.sy;

public class OcrTranslate {
    OcrTranslateParameters parameters;

    private OcrTranslate(OcrTranslateParameters ocrTranslateParameters) {
        this.parameters = ocrTranslateParameters;
    }

    public static OcrTranslate getInstance(OcrTranslateParameters ocrTranslateParameters) {
        return new OcrTranslate(ocrTranslateParameters);
    }

    public void lookup(String str, String str2, OcrTranslateListener ocrTranslateListener) {
        if (ocrTranslateListener == null) {
            sy.c("translate result callback is null listener!");
            return;
        }
        Context b = i.b();
        if (b == null) {
            sy.c("This application may be not init,please use YouDaoApplication init");
            ocrTranslateListener.onError(TranslateErrorCode.CONTEXT_ERROR, str2);
        } else if (this.parameters.getTo() == LanguageOcrTranslate.AUTO) {
            ocrTranslateListener.onError(TranslateErrorCode.TRANS_LANGUAGE_ERROR, str2);
        } else {
            b.a(str, ocrTranslateListener, this.parameters, b, str2);
        }
    }
}

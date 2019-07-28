package com.youdao.sdk.ydonlinetranslate.other;

import com.youdao.sdk.app.HttpErrorCode;
import com.youdao.sdk.app.c.a;
import com.youdao.sdk.ydonlinetranslate.OCRTranslateResult;
import com.youdao.sdk.ydonlinetranslate.OcrTranslateListener;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import defpackage.sy;

final class c implements a {
    final /* synthetic */ OcrTranslateListener a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    c(OcrTranslateListener ocrTranslateListener, String str, String str2) {
        this.a = ocrTranslateListener;
        this.b = str;
        this.c = str2;
    }

    public void onResult(String str) {
        if (this.a != null) {
            OCRTranslateResult oCRTranslateResult = new OCRTranslateResult(str);
            a.a(oCRTranslateResult);
            if (oCRTranslateResult.success()) {
                this.a.onResult(oCRTranslateResult, this.b, this.c);
            } else {
                this.a.onError(b.b(oCRTranslateResult.getErrorCode()), this.c);
            }
        }
    }

    public void onError(HttpErrorCode httpErrorCode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("recogniz voice  http error:");
        stringBuilder.append(httpErrorCode.name());
        sy.b(stringBuilder.toString());
        if (this.a != null) {
            this.a.onError(TranslateErrorCode.HTTP_REQUEST_ERROR, this.c);
        }
    }
}

package com.youdao.sdk.ydonlinetranslate.other;

import com.youdao.sdk.app.HttpErrorCode;
import com.youdao.sdk.app.c.a;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import defpackage.sy;

final class f implements a {
    final /* synthetic */ TranslateListener a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    f(TranslateListener translateListener, String str, String str2) {
        this.a = translateListener;
        this.b = str;
        this.c = str2;
    }

    public void onResult(String str) {
        if (this.a != null) {
            Translate a = g.a(str, null);
            if (a.success()) {
                this.a.onResult(a, this.b, this.c);
            } else {
                this.a.onError(e.b(a.getErrorCode()), this.c);
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

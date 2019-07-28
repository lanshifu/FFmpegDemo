package com.youdao.sdk.ydonlinetranslate.other;

import com.youdao.sdk.app.HttpErrorCode;
import com.youdao.sdk.app.c.a;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import defpackage.sy;

final class h implements a {
    final /* synthetic */ String a;
    final /* synthetic */ TranslateListener b;
    final /* synthetic */ String c;

    h(String str, TranslateListener translateListener, String str2) {
        this.a = str;
        this.b = translateListener;
        this.c = str2;
    }

    public void onResult(String str) {
        Translate a = g.a(str, this.a);
        if (a == null || !a.success()) {
            int i;
            if (a == null) {
                i = 1;
            } else {
                i = a.getErrorCode();
            }
            this.b.onError(g.a(i), this.c);
            return;
        }
        this.b.onResult(a, this.a, this.c);
    }

    public void onError(HttpErrorCode httpErrorCode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("query word ");
        stringBuilder.append(this.a);
        stringBuilder.append(" http error:");
        stringBuilder.append(httpErrorCode.name());
        sy.c(stringBuilder.toString());
        if (this.b != null) {
            this.b.onError(TranslateErrorCode.HTTP_REQUEST_ERROR, this.c);
        }
    }
}

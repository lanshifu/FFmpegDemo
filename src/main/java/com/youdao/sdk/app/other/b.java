package com.youdao.sdk.app.other;

import android.content.Context;
import com.youdao.sdk.app.HttpErrorCode;
import com.youdao.sdk.app.c.a;
import defpackage.sy;

public final class b implements a {
    final /* synthetic */ Context a;

    public b(Context context) {
        this.a = context;
    }

    public void onResult(String str) {
        com.youdao.sdk.app.a.b(str, this.a);
    }

    public void onError(HttpErrorCode httpErrorCode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("check auth error:");
        stringBuilder.append(httpErrorCode.name());
        sy.b(stringBuilder.toString());
    }
}

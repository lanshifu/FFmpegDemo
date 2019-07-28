package com.youdao.sdk.app;

import android.util.Base64;
import com.youdao.sdk.app.other.i;
import com.youdao.sdk.ydtranslate.TranslateSdk;
import defpackage.sy;

public class b {
    public static String[] a(String str) {
        try {
            return new String[]{a(i.a(a(), str)), "0"};
        } catch (Exception unused) {
            return new String[]{a(str.getBytes()), "1"};
        }
    }

    public static String b(String str) {
        try {
            return i.b(a(), str);
        } catch (Exception e) {
            sy.c("decrypt error", e);
            return null;
        }
    }

    public static String c(String str) {
        try {
            return a(i.a(a(), str));
        } catch (Exception e) {
            sy.c("encrypt error", e);
            return null;
        }
    }

    public static String a() {
        return new TranslateSdk().signKey();
    }

    public static String a(byte[] bArr) {
        return new StringBuffer(Base64.encodeToString(bArr, 2)).toString();
    }
}

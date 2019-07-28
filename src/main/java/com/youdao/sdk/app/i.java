package com.youdao.sdk.app;

import android.content.Context;
import android.text.TextUtils;

public class i {
    public static String a = null;
    private static i b = null;
    private static boolean c = false;
    private static Context d;

    public static boolean a() {
        return c;
    }

    static {
        System.loadLibrary("dict-parser");
    }

    private i(Context context, String str) {
        d = context.getApplicationContext();
        a = str;
        if (!e.a.equals("oppo")) {
            c();
        }
    }

    private void c() {
        f.a(d);
        f.a();
        a.a(d);
    }

    public static void a(Context context, String str) {
        if (b == null || d == null || TextUtils.isEmpty(str)) {
            b = new i(context, str);
        }
    }

    public static Context b() {
        return d;
    }
}

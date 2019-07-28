package org.xutils.common.util;

import android.text.TextUtils;
import android.util.Log;
import org.xutils.x;

public class LogUtil {
    public static String customTagPrefix = "x_log";

    private LogUtil() {
    }

    private static String a() {
        String className = new Throwable().getStackTrace()[2].getClassName();
        className = className.substring(className.lastIndexOf(".") + 1);
        String format = String.format("%s.%s(L:%d)", new Object[]{className, r0.getMethodName(), Integer.valueOf(r0.getLineNumber())});
        if (TextUtils.isEmpty(customTagPrefix)) {
            return format;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(customTagPrefix);
        stringBuilder.append(":");
        stringBuilder.append(format);
        return stringBuilder.toString();
    }

    public static void d(String str) {
        if (x.isDebug()) {
            Log.d(a(), str);
        }
    }

    public static void d(String str, Throwable th) {
        if (x.isDebug()) {
            Log.d(a(), str, th);
        }
    }

    public static void e(String str) {
        if (x.isDebug()) {
            Log.e(a(), str);
        }
    }

    public static void e(String str, Throwable th) {
        if (x.isDebug()) {
            Log.e(a(), str, th);
        }
    }

    public static void i(String str) {
        if (x.isDebug()) {
            Log.i(a(), str);
        }
    }

    public static void i(String str, Throwable th) {
        if (x.isDebug()) {
            Log.i(a(), str, th);
        }
    }

    public static void v(String str) {
        if (x.isDebug()) {
            Log.v(a(), str);
        }
    }

    public static void v(String str, Throwable th) {
        if (x.isDebug()) {
            Log.v(a(), str, th);
        }
    }

    public static void w(String str) {
        if (x.isDebug()) {
            Log.w(a(), str);
        }
    }

    public static void w(String str, Throwable th) {
        if (x.isDebug()) {
            Log.w(a(), str, th);
        }
    }

    public static void w(Throwable th) {
        if (x.isDebug()) {
            Log.w(a(), th);
        }
    }

    public static void wtf(String str) {
        if (x.isDebug()) {
            Log.wtf(a(), str);
        }
    }

    public static void wtf(String str, Throwable th) {
        if (x.isDebug()) {
            Log.wtf(a(), str, th);
        }
    }

    public static void wtf(Throwable th) {
        if (x.isDebug()) {
            Log.wtf(a(), th);
        }
    }
}

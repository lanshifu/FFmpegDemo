package org.litepal.util;

import android.util.Log;

public final class LitePalLog {
    public static final int DEBUG = 2;
    public static final int ERROR = 5;
    public static int level = 5;

    public static void d(String str, String str2) {
        if (level <= 2) {
            Log.d(str, str2);
        }
    }

    public static void e(String str, Exception exception) {
        if (level <= 5) {
            Log.e(str, exception.getMessage(), exception);
        }
    }
}
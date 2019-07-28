package com.tomatolive.library.utils;

import android.util.Log;

/* compiled from: LogManager */
public class m {
    static boolean a = false;

    public static void a(String str) {
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("");
            Log.i("debug_info", stringBuilder.toString());
        }
    }

    public static void b(String str) {
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("");
            Log.i("debug_push", stringBuilder.toString());
        }
    }
}

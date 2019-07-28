package com.youdao.sdk.app.other;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class k {
    public static SharedPreferences a(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }

    public static void a(Context context, String str, String str2) {
        Editor edit = a(context, "youdaoSdkCache").edit();
        edit.putString(str, str2);
        edit.commit();
    }
}

package com.youdao.sdk.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;
import java.util.regex.Pattern;

public class g {
    static boolean a(String str) {
        return Pattern.compile("[\\u4e00-\\u9fa5]").matcher(str).find();
    }

    static int a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.youdao.dict", 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (NameNotFoundException unused) {
            return -1;
        }
    }

    public static boolean a(String str, Context context) {
        boolean z = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(str, true);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(str, false).commit();
        return z;
    }

    public static boolean b(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }
}

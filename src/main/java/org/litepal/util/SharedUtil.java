package org.litepal.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import org.litepal.LitePalApplication;

public class SharedUtil {
    private static final String LITEPAL_PREPS = "litepal_prefs";
    private static final String VERSION = "litepal_version";

    public static void updateVersion(String str, int i) {
        Editor edit = LitePalApplication.getContext().getSharedPreferences(LITEPAL_PREPS, 0).edit();
        if (TextUtils.isEmpty(str)) {
            edit.putInt(VERSION, i);
        } else {
            if (str.endsWith(".db")) {
                str = str.replace(".db", "");
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("litepal_version_");
            stringBuilder.append(str);
            edit.putInt(stringBuilder.toString(), i);
        }
        edit.apply();
    }

    public static int getLastVersion(String str) {
        SharedPreferences sharedPreferences = LitePalApplication.getContext().getSharedPreferences(LITEPAL_PREPS, 0);
        if (TextUtils.isEmpty(str)) {
            return sharedPreferences.getInt(VERSION, 0);
        }
        if (str.endsWith(".db")) {
            str = str.replace(".db", "");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("litepal_version_");
        stringBuilder.append(str);
        return sharedPreferences.getInt(stringBuilder.toString(), 0);
    }

    public static void removeVersion(String str) {
        Editor edit = LitePalApplication.getContext().getSharedPreferences(LITEPAL_PREPS, 0).edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove(VERSION);
        } else {
            if (str.endsWith(".db")) {
                str = str.replace(".db", "");
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("litepal_version_");
            stringBuilder.append(str);
            edit.remove(stringBuilder.toString());
        }
        edit.apply();
    }
}

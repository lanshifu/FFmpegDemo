package defpackage;

import android.text.TextUtils;

/* compiled from: TextKit */
/* renamed from: ul */
public class ul {
    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("/");
    }

    public static boolean b(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("file:///android_asset/");
    }
}

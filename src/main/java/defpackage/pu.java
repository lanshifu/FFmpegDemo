package defpackage;

import android.text.TextUtils;
import android.util.Base64;

/* compiled from: Base64Util */
/* renamed from: pu */
public class pu {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return Base64.encodeToString(str.getBytes(), 2);
    }

    public static String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return Base64.encodeToString(str.getBytes(), i);
    }

    public static String a(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Base64.decode(str, 2);
    }
}

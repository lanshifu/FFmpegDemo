package defpackage;

import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Checker */
/* renamed from: mm */
class mm {
    private static List<String> a = new ArrayList();

    static {
        a.add("jpg");
        a.add("jpeg");
        a.add("png");
        a.add("webp");
        a.add("gif");
        a.add("bmp");
    }

    static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return a.contains(str.substring(str.lastIndexOf(".") + 1, str.length()).toLowerCase());
    }

    static boolean b(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str = str.substring(str.lastIndexOf("."), str.length()).toLowerCase();
        if (str.contains("jpg") || str.contains("jpeg")) {
            z = true;
        }
        return z;
    }

    static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return ".jpg";
        }
        return str.substring(str.lastIndexOf("."), str.length());
    }

    static boolean a(int i, String str) {
        if (i > 0) {
            File file = new File(str);
            if (!file.exists() || file.length() <= ((long) (i << 10))) {
                return false;
            }
        }
        return true;
    }
}

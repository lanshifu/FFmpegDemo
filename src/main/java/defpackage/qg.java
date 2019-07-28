package defpackage;

import android.text.TextUtils;
import org.slf4j.Marker;

/* compiled from: AlipayUtils */
/* renamed from: qg */
public class qg {
    private static final qg a = new qg();
    private final String b = "****";

    private qg() {
    }

    public static qg a() {
        return a;
    }

    public String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        int i = 0;
        if (str.length() == 2) {
            stringBuilder.append(str.charAt(0));
            stringBuilder.append(Marker.ANY_MARKER);
        } else if (str.length() > 2) {
            while (i < str.length()) {
                if (i == 0 || i == str.length() - 1) {
                    stringBuilder.append(str.charAt(i));
                } else {
                    stringBuilder.append(Marker.ANY_MARKER);
                }
                i++;
            }
        } else {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public String b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        int i = 1;
        if (str.split("\\@").length >= 2) {
            str2 = str.split("\\@")[str.split("\\@").length - 1];
        } else {
            i = 0;
        }
        str = str.split("\\@")[0];
        int length = (str.length() - 4) / 2;
        int length2 = "****".length() + length;
        int i2 = length2 + 1;
        if (str.length() >= i2) {
            stringBuilder.append(str.substring(0, length));
            stringBuilder.append("****");
        } else {
            stringBuilder.append(str);
        }
        if (str.length() >= i2) {
            stringBuilder.append(str.substring(length2, str.length()));
        }
        if (i != 0) {
            stringBuilder.append("@");
            stringBuilder.append(str2);
        }
        return stringBuilder.toString();
    }
}

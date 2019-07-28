package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.danikula.videocache.l;
import java.net.URLDecoder;
import java.net.URLEncoder;

/* compiled from: FileNameUtil */
/* renamed from: el */
public class el {
    public static String a(String str, String str2) {
        str = el.d(str);
        str2 = el.c(str2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("/");
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }

    public static String a(String str, String str2, String str3) {
        str = el.d(str);
        str2 = el.c(str2);
        if (TextUtils.isEmpty(str3)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("/");
            stringBuilder.append(str2);
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append("/");
        stringBuilder2.append(str3);
        stringBuilder2.append("/");
        stringBuilder2.append(str2);
        return stringBuilder2.toString();
    }

    public static String a(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(47);
        return (lastIndexOf == -1 || lastIndexOf <= lastIndexOf2 || (lastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(lastIndexOf2 + 1, lastIndexOf);
    }

    public static String b(String str) {
        return str.substring(0, str.lastIndexOf(47));
    }

    public static String c(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(47);
        int lastIndexOf3 = str.lastIndexOf(63);
        if (lastIndexOf3 != -1) {
            str = (lastIndexOf == -1 || lastIndexOf <= lastIndexOf2) ? "" : str.substring(lastIndexOf2 + 1, lastIndexOf3);
            return str;
        }
        str = (lastIndexOf == -1 || lastIndexOf <= lastIndexOf2 || (lastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(lastIndexOf2 + 1, str.length());
        return str;
    }

    public static String d(String str) {
        if (str.contains("_s3")) {
            str = el.e(str.substring(str.indexOf("_s3")));
        } else {
            str = el.e(str);
        }
        return l.d(str);
    }

    public static String e(String str) {
        int lastIndexOf = str.lastIndexOf(63);
        return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
    }

    public static boolean f(String str) {
        return el.e(str).trim().toLowerCase().endsWith("m3u8");
    }

    public static String g(String str) {
        StringBuilder stringBuilder;
        String str2 = "token=";
        int lastIndexOf = str.lastIndexOf(str2);
        String str3 = "";
        String str4 = "";
        String e = el.e(str);
        if (lastIndexOf != -1) {
            str4 = str.substring(lastIndexOf + str2.length(), str.length());
            stringBuilder = new StringBuilder();
            stringBuilder.append(e);
            stringBuilder.append("?");
            stringBuilder.append(str2);
            e = stringBuilder.toString();
        }
        try {
            stringBuilder = new StringBuilder();
            stringBuilder.append(URLEncoder.encode(e, "utf-8"));
            stringBuilder.append(str4);
            return stringBuilder.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return str3;
        }
    }

    public static String h(String str) {
        String str2 = "token=";
        String str3 = "";
        try {
            str3 = URLDecoder.decode(str, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (str3.lastIndexOf(str2) != -1) {
            str2 = "token%3D";
            String substring = str.substring(str.lastIndexOf(str2) + str2.length(), str.length());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(el.e(str3));
            stringBuilder.append("?token=");
            stringBuilder.append(substring);
            str3 = stringBuilder.toString();
            if (str3.split("token=").length > 1) {
                str3 = str3.substring(0, str.lastIndexOf(str2) - 1);
            }
        }
        b a = aat.a("TTT");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("decodeUrl:");
        stringBuilder2.append(str3);
        a.a(stringBuilder2.toString(), new Object[0]);
        return str3;
    }

    public static boolean a(Context context, String str) {
        String packageName = context.getPackageName();
        if (!TextUtils.isEmpty(str) && str.contains(packageName)) {
            return true;
        }
        return false;
    }
}

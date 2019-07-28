package com.youdao.sdk.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

public class h {
    public static boolean a(Context context) {
        return g.a(context) >= 6060100;
    }

    public static void a(Context context, String str, String str2, Language language, Language language2) {
        if (!TextUtils.isEmpty(str)) {
            if (a(context)) {
                c(context, str, str2, language, language2);
            } else {
                a(context, a(str, str2, language, language2));
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5) {
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                a(context, str2);
            } else if (g.a(context) >= 6060100) {
                c(context, str, str5, e.b(str3), e.b(str4));
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("http://www.u-dictionary.com/home/word/%s/from/");
                stringBuilder.append(str3);
                stringBuilder.append("/to/");
                stringBuilder.append(str4);
                str2 = String.format(stringBuilder.toString(), new Object[]{str});
                f.a("querysdk_jump_to_dict", str, "web", e.b(str3), e.b(str4));
                a(context, str2);
            }
        }
    }

    public static boolean b(Context context, String str, String str2, Language language, Language language2) {
        if (TextUtils.isEmpty(str) || !a(context)) {
            return false;
        }
        c(context, str, str2, language, language2);
        return true;
    }

    public static String a(String str, String str2, Language language, Language language2) {
        f.a("querysdk_jump_to_dict", str, "web", language, language2);
        return String.format("http://m.youdao.com/dict?le=%s&q=%s", new Object[]{str2, str});
    }

    private static void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
        }
    }

    private static void c(Context context, String str, String str2, Language language, Language language2) {
        if (a(context)) {
            f.a("querysdk_jump_to_dict", str, "app", language, language2);
            str = String.format("yddict://m.youdao.com/dict?q=%s&le=%s", new Object[]{str, str2});
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
        }
    }

    private static void a(Context context, String str, String str2, Language language, Language language2, String str3) {
        if (a(context)) {
            f.a("querysdk_jump_to_dict", str, "app", language, language2);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str3));
            context.startActivity(intent);
        }
    }

    public static void a(Context context, String str, String str2, Language language, Language language2, String str3, String str4) {
        if (!TextUtils.isEmpty(str)) {
            if (a(context)) {
                a(context, str, str2, language, language2, str4);
            } else {
                a(context, str3);
            }
        }
    }
}

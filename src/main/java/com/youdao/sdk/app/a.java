package com.youdao.sdk.app;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.youdao.sdk.app.other.b;
import com.youdao.sdk.app.other.k;
import com.youdao.sdk.ydtranslate.TranslateSdk;
import defpackage.sx;
import defpackage.sy;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import org.json.JSONObject;

public class a {
    public static void a(Context context) {
        HashMap hashMap = new HashMap();
        String[] a = b.a(b(context, ""));
        hashMap.put("s", a[0]);
        hashMap.put("et", a[1]);
        c.a("http://updateinfo.youdao.com/update?", hashMap, new b(context), 10000);
    }

    private static long b(Context context) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(6, 30);
        long time = instance.getTime().getTime();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(time);
        stringBuilder.append("");
        k.a(context, "offline_end_use_day", stringBuilder.toString());
        return time;
    }

    private static void b(String str, Context context) {
        try {
            str = b.b(str);
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                str = jSONObject.getString("errorCode");
                long j = jSONObject.getLong("timestamp");
                Object obj = Math.abs(System.currentTimeMillis() - j) < 600000 ? 1 : null;
                StringBuilder stringBuilder;
                if (!"0".equalsIgnoreCase(str) || obj == null) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("bad");
                    stringBuilder.append(j);
                    k.a(context, "offline_auth", b.c(stringBuilder.toString()));
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("good");
                    stringBuilder.append(j);
                    k.a(context, "offline_auth", b.c(stringBuilder.toString()));
                    b(context);
                }
            }
        } catch (Exception e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("check auth exception:");
            stringBuilder2.append(e.getMessage());
            sy.b(stringBuilder2.toString(), e);
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("good");
            stringBuilder2.append(System.currentTimeMillis());
            k.a(context, "offline_auth", b.c(stringBuilder2.toString()));
        }
    }

    private static Map<String, String> a(Context context, String str) {
        String a = a();
        sx sxVar = new sx(context);
        sxVar.p(a);
        Map c = sxVar.c();
        int nextInt = new Random().nextInt(1000);
        String sign = new TranslateSdk().sign(context, a, str, String.valueOf(nextInt));
        c.put("q", str);
        c.put("salt", String.valueOf(nextInt));
        c.put("signType", "v1");
        c.put("docType", "json");
        c.put("sign", sign);
        c.put("offline", "0");
        c.put("version", "v2");
        return c;
    }

    private static String b(Context context, String str) {
        Map a = a(context, str);
        StringBuilder stringBuilder = new StringBuilder("");
        for (Entry entry : a.entrySet()) {
            String str2 = (String) entry.getKey();
            String str3 = (String) entry.getValue();
            if (!TextUtils.isEmpty(str3)) {
                stringBuilder.append(str2);
                stringBuilder.append("=");
                stringBuilder.append(Uri.encode(str3));
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }

    private static final String a() {
        return i.a;
    }
}

package com.youdao.sdk.app;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.youdao.sdk.app.other.e;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class f {
    private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    private static e b;
    private static com.youdao.sdk.app.other.a c;

    static class a {
        private Map<String, Object> a;

        a() {
            this.a = new HashMap();
            this.a = new HashMap();
        }

        private a a(String str, Object obj) {
            if (obj instanceof String) {
                if (!TextUtils.isEmpty((String) obj)) {
                    this.a.put(str, obj);
                }
            } else if (obj != null) {
                this.a.put(str, obj);
            }
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public a a(String str, String str2) {
            return a(str, (Object) str2);
        }

        public Map<String, Object> a() {
            return this.a;
        }
    }

    static void a(Context context) {
        try {
            if (b == null) {
                b = new e(context, "statistics");
            }
            if (c == null) {
                c = new com.youdao.sdk.app.other.a(context.getApplicationContext());
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("init");
            stringBuilder.append(new com.youdao.sdk.app.other.a(context).a());
            stringBuilder.append("stats");
            if (g.a(stringBuilder.toString(), context)) {
                b.a();
            }
        } catch (Exception unused) {
        }
    }

    public static void a(String str, String str2, String str3, Language language, Language language2) {
        if (str2 != null) {
            if (language == null || language2 == null) {
                if (g.a(str2)) {
                    language = Language.CHINESE;
                    language2 = Language.ENGLISH;
                } else {
                    language2 = Language.CHINESE;
                    language = Language.ENGLISH;
                }
            }
            String[] split = str.split("querysdk_");
            String str4 = "";
            if (split.length == 2) {
                str4 = split[1];
            }
            b(new a().a("action", str4).a("query", str2).a("msg", str3).a("from", language.getCode()).a("to", language2.getCode()).a());
        }
    }

    private static String a(Map<String, ?> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : map.entrySet()) {
                if (!(entry.getKey() == null || entry.getValue() == null)) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
            }
            if (!TextUtils.isEmpty(c.c())) {
                jSONObject.put("ssid", c.c());
            }
            String d = c.d();
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put("network", d);
            }
            jSONObject.put("date", a.format(new Date()));
            return jSONObject.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private static void b(Map<String, ? extends Object> map) {
        if (b != null) {
            String a = a((Map) map);
            if (a != null) {
                b.a(a);
                Log.i("Stats", String.format("doStatistics ----> %s", new Object[]{a}));
            }
        }
    }

    static void a() {
        if (b != null) {
            b.b();
        }
    }
}

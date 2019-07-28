package defpackage;

import android.text.TextUtils;
import com.zzbwuhan.beard.BCrypto;
import java.util.HashMap;
import java.util.Map;

/* compiled from: HlsPlayConstant */
/* renamed from: eh */
public class eh {
    private static Map<String, Object> a = new HashMap();

    public static void a(Map<String, Object> map) {
        if (map != null) {
            Object obj = map.get("hls_play_m3u8_load");
            if (obj == null) {
                map.put("hls_play_m3u8_load", new Integer(1));
            } else {
                map.put("hls_play_m3u8_load", Integer.valueOf(((Integer) obj).intValue() + 1));
            }
        }
    }

    public static boolean b(Map<String, Object> map) {
        if (map == null) {
            return false;
        }
        Object obj = map.get("hls_play_m3u8_load");
        if (obj != null && ((Integer) obj).intValue() > 1) {
            return true;
        }
        return false;
    }

    public static void a(Map<String, Object> map, int i) {
        if (map != null) {
            map.put("hls_play_ts_count", new Integer(i));
        }
    }

    public static int c(Map<String, Object> map) {
        Object obj = map.get("hls_play_ts_count");
        if (obj == null) {
            return 0;
        }
        return ((Integer) obj).intValue();
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return el.a(str);
    }

    private static eg d(Map<String, Object> map, String str) {
        str = eh.a(str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj == null) {
            return null;
        }
        return (eg) obj;
    }

    private static int e(Map<String, Object> map, String str) {
        eg d = eh.d(map, str);
        if (d == null) {
            return 0;
        }
        return d.a;
    }

    public static int d(Map<String, Object> map) {
        Object obj = map.get("hls_play_current_num");
        if (obj == null) {
            return 0;
        }
        return ((Integer) obj).intValue();
    }

    private static void b(Map<String, Object> map, int i) {
        if (map != null) {
            map.put("hls_play_current_num", Integer.valueOf(i));
        }
    }

    public static void a(Map<String, Object> map, String str) {
        eh.b((Map) map, eh.e(map, str));
    }

    public static boolean b(Map<String, Object> map, String str) {
        eg d = eh.d(map, str);
        if (d == null) {
            return false;
        }
        int i = d.a;
        int d2 = eh.d(map);
        if (d2 != 0 && d2 + 1 == i) {
            return true;
        }
        return false;
    }

    public static eg c(Map<String, Object> map, String str) {
        if (map == null || TextUtils.isEmpty(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj == null) {
            return null;
        }
        return (eg) obj;
    }

    public static void b(String str) {
        a.put("hls_play_m3u8_key", str);
    }

    public static void a(int i) {
        a.put("hls_play_m3u8_key_index", Integer.valueOf(i));
    }

    public static String a() {
        Object obj = a.get("hls_play_m3u8_key");
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static int b() {
        Object obj = a.get("hls_play_m3u8_key_index");
        if (obj == null) {
            return -1;
        }
        return ((Integer) obj).intValue();
    }

    public static void a(Long l) {
        a.put("hls_play_key_ptr", l);
    }

    public static Long c() {
        Object obj = a.get("hls_play_key_ptr");
        if (obj != null) {
            return Long.valueOf(((Long) obj).longValue());
        }
        String a = eh.a();
        if (TextUtils.isEmpty(a)) {
            return Long.valueOf(0);
        }
        long decodeKey = BCrypto.decodeKey(a, eh.b());
        eh.a(Long.valueOf(decodeKey));
        return Long.valueOf(decodeKey);
    }

    public static void a(boolean z) {
        a.put("hls_play_is_error", Boolean.valueOf(z));
    }

    public static boolean d() {
        Object obj = a.get("hls_play_is_error");
        return obj != null ? ((Boolean) obj).booleanValue() : false;
    }

    public static void e() {
        if (eh.c().longValue() != 0) {
            BCrypto.releaseKey(eh.c().longValue());
        }
        a.clear();
    }
}

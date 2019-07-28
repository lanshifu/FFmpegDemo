package defpackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SpringConfigRegistry */
/* renamed from: so */
public class so {
    private static final so a = new so(true);
    private final Map<sn, String> b = new HashMap();

    public static so a() {
        return a;
    }

    so(boolean z) {
        if (z) {
            a(sn.c, "default config");
        }
    }

    public boolean a(sn snVar, String str) {
        if (snVar == null) {
            throw new IllegalArgumentException("springConfig is required");
        } else if (str == null) {
            throw new IllegalArgumentException("configName is required");
        } else if (this.b.containsKey(snVar)) {
            return false;
        } else {
            this.b.put(snVar, str);
            return true;
        }
    }

    public Map<sn, String> b() {
        return Collections.unmodifiableMap(this.b);
    }
}

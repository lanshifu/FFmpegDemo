package com.youdao.sdk.app.other;

import java.util.HashMap;
import java.util.Map;

public abstract class g {
    protected Map<String, String> a = new HashMap();

    /* Access modifiers changed, original: protected */
    public void a(String str, String str2) {
        if (str2 != null && !p.a(str2)) {
            this.a.put(str, str2);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(String str) {
        a("version", str);
    }

    /* Access modifiers changed, original: protected */
    public void b(String str) {
        a("av", str);
    }

    /* Access modifiers changed, original: protected|varargs */
    public void a(String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (strArr != null && strArr.length >= 1) {
            for (int i = 0; i < strArr.length - 1; i++) {
                stringBuilder.append(strArr[i]);
                stringBuilder.append(",");
            }
            stringBuilder.append(strArr[strArr.length - 1]);
            a("dn", stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: protected */
    public void c(String str) {
        a("udid", str);
    }

    /* Access modifiers changed, original: protected */
    public void d(String str) {
        a("auid", str);
    }
}

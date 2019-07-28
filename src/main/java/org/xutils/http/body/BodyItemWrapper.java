package org.xutils.http.body;

import android.text.TextUtils;

public final class BodyItemWrapper {
    private final Object a;
    private final String b;
    private final String c;

    public BodyItemWrapper(Object obj, String str) {
        this(obj, str, null);
    }

    public BodyItemWrapper(Object obj, String str, String str2) {
        this.a = obj;
        if (TextUtils.isEmpty(str)) {
            this.c = "application/octet-stream";
        } else {
            this.c = str;
        }
        this.b = str2;
    }

    public Object getValue() {
        return this.a;
    }

    public String getFileName() {
        return this.b;
    }

    public String getContentType() {
        return this.c;
    }
}

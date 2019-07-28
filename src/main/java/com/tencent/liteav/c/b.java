package com.tencent.liteav.c;

import android.text.TextUtils;

/* compiled from: BgmConfig */
public class b {
    private static b m;
    public String a;
    public long b;
    public long c;
    public long d;
    public boolean e;
    public float f;
    public float g;
    public boolean h;
    public long i;
    public boolean j;
    public long k;
    public long l;

    public static b a() {
        if (m == null) {
            m = new b();
        }
        return m;
    }

    private b() {
        b();
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            b();
            return;
        }
        if (this.a == null || !this.a.equals(str)) {
            b(str);
        }
    }

    private void b(String str) {
        this.a = str;
    }

    public void b() {
        this.a = null;
        this.b = -1;
        this.c = -1;
        this.h = false;
        this.f = 1.0f;
        this.e = false;
        this.j = false;
        this.k = 0;
        this.l = 0;
        this.i = 0;
    }
}

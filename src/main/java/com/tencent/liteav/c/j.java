package com.tencent.liteav.c;

import com.tencent.liteav.d.c;
import com.tencent.liteav.d.d;
import com.tencent.liteav.d.h;

/* compiled from: VideoPreProcessConfig */
public class j {
    private static j b;
    public float a;
    private com.tencent.liteav.d.j c;
    private h d;
    private c e;
    private d f;
    private int g;

    public static j a() {
        if (b == null) {
            b = new j();
        }
        return b;
    }

    private j() {
        g();
    }

    private void g() {
        f();
    }

    public void a(com.tencent.liteav.d.j jVar) {
        this.c = jVar;
    }

    public com.tencent.liteav.d.j b() {
        return this.c;
    }

    public void a(c cVar) {
        this.e = cVar;
    }

    public c c() {
        return this.e;
    }

    public d d() {
        return this.f;
    }

    public void a(d dVar) {
        this.f = dVar;
    }

    public int e() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public void f() {
        this.a = 1.0f;
        if (this.c != null) {
            this.c.b();
        }
        this.c = null;
        if (this.d != null) {
            this.d.a();
        }
        if (this.f != null) {
            this.f.a();
        }
        this.d = null;
        this.e = null;
        this.g = 0;
    }
}

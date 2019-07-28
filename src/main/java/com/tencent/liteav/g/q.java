package com.tencent.liteav.g;

import android.content.Context;
import com.tencent.liteav.beauty.d;
import com.tencent.liteav.d.e;
import com.tencent.liteav.d.g;
import com.tencent.liteav.f.l;

/* compiled from: VideoJoinPreprocessChain */
public class q {
    private Context a;
    private d b;
    private l c;
    private l d;
    private com.tencent.liteav.e.l e;
    private g f;

    public q(Context context) {
        this.a = context;
    }

    public void a() {
        this.b = new d(this.a, true);
    }

    public void b() {
        this.c = new l(Boolean.valueOf(false));
        this.c.a();
        this.d = new l(Boolean.valueOf(true));
        this.d.a();
    }

    public void c() {
        if (this.c != null) {
            this.c.b();
            this.c = null;
        }
        if (this.d != null) {
            this.d.b();
            this.d = null;
        }
    }

    public void d() {
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
    }

    public void a(com.tencent.liteav.e.l lVar) {
        this.e = lVar;
    }

    public void a(g gVar) {
        this.f = gVar;
    }

    public void a(float[] fArr) {
        if (this.b != null) {
            this.b.a(fArr);
        }
    }

    public void a(int i, e eVar) {
        if (this.b != null && eVar != null) {
            int c = c(i, eVar);
            e a = a(eVar);
            this.b.a(0);
            int a2 = this.b.a(c, a.m(), a.n(), 0, 0, 0);
            if (this.e != null) {
                a2 = this.e.b(a2, a);
            }
            a2 = b(a2, a);
            if (this.e != null) {
                this.e.a(a2, a);
            }
        }
    }

    private e a(e eVar) {
        int h = 360 - eVar.h();
        if (h == 90 || h == 270) {
            h = eVar.n();
            eVar.k(eVar.m());
            eVar.j(h);
        }
        return eVar;
    }

    private int b(int i, e eVar) {
        if (this.c == null || eVar.m() == 0 || eVar.n() == 0) {
            return i;
        }
        this.c.a(s.r().t);
        this.c.b(eVar.m(), eVar.n());
        this.c.a(this.f.a, this.f.b);
        return this.c.d(i);
    }

    private int c(int i, e eVar) {
        if (this.d == null || eVar.m() == 0 || eVar.n() == 0) {
            return i;
        }
        this.d.a(s.r().t);
        int h = 360 - eVar.h();
        this.d.b(h);
        this.d.b(eVar.m(), eVar.n());
        if (h == 90 || h == 270) {
            this.d.a(eVar.n(), eVar.m());
        } else {
            this.d.a(eVar.m(), eVar.n());
        }
        return this.d.d(i);
    }
}

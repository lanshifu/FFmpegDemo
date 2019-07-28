package com.tencent.liteav.f;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.tencent.liteav.beauty.d;
import com.tencent.liteav.c.h;
import com.tencent.liteav.c.i;
import com.tencent.liteav.c.j;
import com.tencent.liteav.d.c;
import com.tencent.liteav.d.e;
import com.tencent.liteav.d.g;
import com.tencent.liteav.e.l;
import com.tencent.liteav.e.s;
import com.tencent.liteav.i.a;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: VideoPreprocessChain */
public class k {
    private Context a;
    private j b;
    private d c;
    private s d;
    private l e;
    private g f;
    private i g;
    private l h;
    private e i;
    private h j;
    private f k;
    private a l;
    private j m;
    private ArrayList<d.d> n;
    private e o;
    private boolean p;
    private int q;
    private l r;
    private boolean s = false;

    public k(Context context) {
        this.a = context;
    }

    public void a() {
        this.b = j.a();
        this.c = new d(this.a, true);
        this.i = new e(this.a);
        this.j = h.a();
        this.k = f.a();
        this.l = a.a();
        this.m = j.a();
    }

    public void b() {
        if (!h.a().e()) {
            this.g = new i(Boolean.valueOf(false));
            this.g.a();
        }
        this.h = new l(Boolean.valueOf(false));
        this.h.a();
        this.r = new l(Boolean.valueOf(true));
        this.r.a();
    }

    public void c() {
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
        if (this.h != null) {
            this.h.b();
            this.h = null;
        }
        if (this.r != null) {
            this.r.b();
            this.r = null;
        }
    }

    public void d() {
        if (this.i != null) {
            this.i.a();
        }
        if (this.c != null) {
            this.c.a();
            this.c = null;
        }
        if (this.n != null) {
            this.n.clear();
        }
        this.o = null;
    }

    public void a(g gVar) {
        this.f = gVar;
    }

    public void a(l lVar) {
        this.e = lVar;
    }

    public void a(s sVar) {
        this.d = sVar;
    }

    public void a(float[] fArr) {
        if (this.c != null) {
            this.c.a(fArr);
        }
    }

    public void a(boolean z) {
        this.p = z;
        if (z) {
            a(this.q, this.o);
        }
    }

    public void a(int i, e eVar) {
        if (this.c != null && eVar != null) {
            int c;
            if (this.s) {
                c = c(i, eVar);
                eVar = b(eVar);
                e(c, eVar);
                this.o = eVar;
                this.q = i;
                return;
            }
            int i2;
            this.n = new ArrayList();
            if (com.tencent.liteav.c.k.a().d() == 1) {
                c = c(i, eVar);
                eVar = b(eVar);
                i2 = c;
            } else {
                i2 = i;
            }
            this.l.c(eVar);
            this.k.c(eVar);
            this.j.c(eVar);
            e();
            g();
            f();
            if (!this.p) {
                e(eVar);
                d(eVar);
                c(eVar);
            }
            a(eVar);
            this.c.a(0);
            this.c.a(this.n);
            this.c.b(eVar.s());
            c = d(this.c.a(i2, eVar.m(), eVar.n(), 0, 0, 0), eVar);
            if (this.e != null) {
                c = this.e.b(c, eVar);
            }
            c = b(c, eVar);
            if (this.e != null) {
                this.e.a(c, eVar);
            }
            f(c, eVar);
            this.o = eVar;
            this.q = i;
        }
    }

    private void a(e eVar) {
        if (this.m.b()) {
            List<a.j> h = this.m.h();
            if (h != null && h.size() != 0) {
                long a = com.tencent.liteav.j.e.a(eVar) / 1000;
                for (a.j jVar : h) {
                    if (a <= jVar.c) {
                        break;
                    } else if (a > jVar.c && a <= jVar.d) {
                        this.n.add(a(jVar.a, jVar.b));
                    }
                }
            }
        }
    }

    private e b(e eVar) {
        int h = (360 - eVar.h()) - j.a().e();
        if (h == 90 || h == 270) {
            int n = eVar.n();
            eVar.k(eVar.m());
            eVar.j(n);
            eVar.e(0);
        }
        return eVar;
    }

    private int b(int i, e eVar) {
        if (this.h == null || eVar.m() == 0 || eVar.n() == 0) {
            return i;
        }
        this.h.a(i.a().s);
        this.h.b(eVar.m(), eVar.n());
        this.h.a(this.f.a, this.f.b);
        return this.h.d(i);
    }

    private int c(int i, e eVar) {
        if (this.r == null || eVar.m() == 0 || eVar.n() == 0) {
            return i;
        }
        this.r.a(i.a().s);
        int h = (360 - eVar.h()) - j.a().e();
        this.r.b(h);
        this.r.b(eVar.m(), eVar.n());
        if (h == 90 || h == 270) {
            this.r.a(eVar.n(), eVar.m());
        } else {
            this.r.a(eVar.m(), eVar.n());
        }
        return this.r.d(i);
    }

    private int d(int i, e eVar) {
        if (this.i == null) {
            return i;
        }
        this.i.a(eVar);
        return this.i.a(eVar, i);
    }

    private void c(e eVar) {
        List b = this.l.b();
        if (b == null || b.size() == 0) {
            this.l.a(this.f);
            this.l.a(eVar);
            b = this.l.b();
        }
        for (com.tencent.liteav.d.a aVar : b) {
            long e = eVar.e() / 1000;
            if (e > aVar.c && e <= aVar.d) {
                Bitmap decodeFile = BitmapFactory.decodeFile(aVar.a);
                if (decodeFile != null) {
                    if (aVar.e == CropImageView.DEFAULT_ASPECT_RATIO) {
                        this.n.add(a(decodeFile, aVar.b));
                    } else {
                        this.n.add(a(com.tencent.liteav.j.a.a(aVar.e, decodeFile), aVar.b));
                    }
                }
            }
        }
    }

    private void d(e eVar) {
        List b = this.k.b();
        if (b == null || b.size() == 0) {
            this.k.a(this.f);
            this.k.a(eVar);
            b = this.k.b();
        }
        for (a.e eVar2 : b) {
            long e = eVar.e() / 1000;
            if (e >= eVar2.c && e <= eVar2.d) {
                this.n.add(a(eVar2.a, eVar2.b));
            }
        }
    }

    private void e(e eVar) {
        List b = this.j.b();
        if (b == null || b.size() == 0) {
            this.j.a(this.f);
            this.j.a(eVar);
            b = this.j.b();
        }
        for (a.j jVar : b) {
            long e = eVar.e() / 1000;
            if (e >= jVar.c && e <= jVar.d) {
                this.n.add(a(jVar.a, jVar.b));
            }
        }
    }

    private void e(int i, e eVar) {
        if (this.d != null) {
            h a = h.a();
            if (!a.e()) {
                if (eVar.p()) {
                    do {
                        int h = a.h();
                        if (this.o != null) {
                            long e = this.o.e();
                            g d = a.d();
                            if (this.g != null) {
                                this.g.b(this.o.m(), this.o.n());
                                this.g.a(d.a, d.b);
                                Bitmap a2 = com.tencent.liteav.j.d.a(this.g.b(i), d.a, d.b);
                                if (this.d != null) {
                                    this.d.a(h, e, a2);
                                }
                            }
                        }
                    } while (!a.e());
                    return;
                }
                int h2 = a.h();
                long g = a.g();
                g d2 = a.d();
                if (this.g != null) {
                    this.g.b(eVar.m(), eVar.n());
                    this.g.a(d2.a, d2.b);
                    this.d.a(h2, g, com.tencent.liteav.j.d.a(this.g.b(i), d2.a, d2.b));
                }
            }
        }
    }

    private void f(int i, e eVar) {
        if (this.d != null) {
            h a = h.a();
            if (!a.e()) {
                long e;
                if (eVar.p()) {
                    do {
                        int h = a.h();
                        a.g();
                        if (this.o != null) {
                            e = this.o.e();
                            g d = a.d();
                            if (this.g != null) {
                                this.g.b(this.o.m(), this.o.n());
                                this.g.a(d.a, d.b);
                                Bitmap a2 = com.tencent.liteav.j.d.a(this.g.b(i), d.a, d.b);
                                if (this.d != null) {
                                    this.d.a(h, e, a2);
                                }
                            }
                        }
                    } while (!a.e());
                    return;
                }
                e = eVar.e();
                if (i.a().r || a.k() || e >= a.f()) {
                    int h2 = a.h();
                    long g = a.g();
                    g d2 = a.d();
                    if (this.g != null) {
                        this.g.b(eVar.m(), eVar.n());
                        this.g.a(d2.a, d2.b);
                        this.d.a(h2, g, com.tencent.liteav.j.d.a(this.g.b(i), d2.a, d2.b));
                    }
                }
            }
        }
    }

    private void e() {
        c c = this.b.c();
        if (c != null && c.a()) {
            this.c.c(c.a);
            this.c.d(c.b);
        }
    }

    private void f() {
        com.tencent.liteav.d.d d = this.b.d();
        if (d != null) {
            float d2 = d.d();
            Bitmap e = d.e();
            Bitmap f = d.f();
            this.c.a(d2, e, d.b(), f, d.c());
        }
    }

    private void g() {
        com.tencent.liteav.d.j b = this.b.b();
        if (b != null) {
            this.n.add(a(b.c(), b.d()));
        }
    }

    private d.d a(Bitmap bitmap, a.g gVar) {
        d.d dVar = new d.d();
        dVar.a = bitmap;
        dVar.b = gVar.a;
        dVar.c = gVar.b;
        dVar.d = gVar.c;
        return dVar;
    }

    public void b(boolean z) {
        this.s = z;
    }
}

package master.flame.danmaku.danmaku.model.android;

import com.yalantis.ucrop.view.CropImageView;
import defpackage.zf;
import defpackage.zi;
import defpackage.zj;
import defpackage.zk;
import defpackage.zo;
import defpackage.zq;
import defpackage.zr;
import defpackage.zs;
import defpackage.zs.c;

/* compiled from: DanmakuFactory */
public class d {
    public int a = 0;
    public int b = 0;
    public long c = 3800;
    public long d = 4000;
    public zi e;
    public zi f;
    public zi g;
    public zo h;
    private c i = null;
    private float j = 1.0f;
    private DanmakuContext k;

    public static d a() {
        return new d();
    }

    protected d() {
    }

    public void b() {
        this.h = null;
        this.b = 0;
        this.a = 0;
        this.e = null;
        this.f = null;
        this.g = null;
        this.d = 4000;
    }

    public void a(DanmakuContext danmakuContext) {
        this.k = danmakuContext;
        this.h = danmakuContext.b();
        a(1, danmakuContext);
    }

    public zf a(int i) {
        return a(i, this.k);
    }

    public zf a(int i, DanmakuContext danmakuContext) {
        if (danmakuContext == null) {
            return null;
        }
        this.k = danmakuContext;
        this.h = danmakuContext.b();
        return a(i, this.h.e(), this.h.f(), this.j, danmakuContext.l);
    }

    public zf a(int i, int i2, int i3, float f, float f2) {
        return a(i, (float) i2, (float) i3, f, f2);
    }

    public zf a(int i, float f, float f2, float f3, float f4) {
        float f5;
        int i2 = this.a;
        int i3 = this.b;
        boolean a = a(f, f2, f3);
        if (this.e == null) {
            this.e = new zi(this.c);
            this.e.a(f4);
        } else if (a) {
            this.e.a(this.c);
        }
        if (this.f == null) {
            this.f = new zi(3800);
        }
        f4 = 1.0f;
        if (!a || f <= CropImageView.DEFAULT_ASPECT_RATIO) {
            f5 = 1.0f;
        } else {
            c();
            if (i2 <= 0 || i3 <= 0) {
                f5 = 1.0f;
            } else {
                f4 = f / ((float) i2);
                f5 = f2 / ((float) i3);
            }
            i3 = (int) f;
            int i4 = (int) f2;
            a(i3, i4, f4, f5);
            if (f2 > CropImageView.DEFAULT_ASPECT_RATIO) {
                b(i3, i4, f4, f5);
            }
        }
        if (i == 1) {
            return new zr(this.e);
        }
        switch (i) {
            case 4:
                return new zj(this.f);
            case 5:
                return new zk(this.f);
            case 6:
                return new zq(this.e);
            case 7:
                zf zsVar = new zs();
                a((int) f, (int) f2, f4, f5);
                ((zs) zsVar).a(this.i);
                return zsVar;
            default:
                return null;
        }
    }

    private void a(int i, int i2, float f, float f2) {
        if (this.i == null) {
            this.i = new c(i, i2, f, f2);
        }
        this.i.a(i, i2, f, f2);
    }

    public boolean a(float f, float f2, float f3) {
        int i = (int) f;
        if (this.a == i && this.b == ((int) f2) && this.j == f3) {
            return false;
        }
        this.c = (long) (((f * f3) / 682.0f) * 3800.0f);
        this.c = Math.min(9000, this.c);
        this.c = Math.max(4000, this.c);
        this.a = i;
        this.b = (int) f2;
        this.j = f3;
        return true;
    }

    private synchronized void b(int i, int i2, float f, float f2) {
        if (this.i != null) {
            this.i.a(i, i2, f, f2);
        }
    }

    public void c() {
        long j = 0;
        long j2 = this.e == null ? 0 : this.e.a;
        long j3 = this.f == null ? 0 : this.f.a;
        if (this.g != null) {
            j = this.g.a;
        }
        this.d = Math.max(j2, j3);
        this.d = Math.max(this.d, j);
        this.d = Math.max(3800, this.d);
        this.d = Math.max(this.c, this.d);
    }

    public void a(float f) {
        if (this.e != null && this.f != null) {
            this.e.a(f);
            c();
        }
    }

    public void a(zf zfVar, float f, float f2, float f3, float f4, long j, long j2, float f5, float f6) {
        if (zfVar.o() == 7) {
            ((zs) zfVar).a(f * f5, f2 * f6, f3 * f5, f4 * f6, j, j2);
            a(zfVar);
        }
    }

    public void a(zf zfVar, int i, int i2, long j) {
        if (zfVar.o() == 7) {
            ((zs) zfVar).a(i, i2, j);
            a(zfVar);
        }
    }

    private void a(zf zfVar) {
        if (this.g == null || (zfVar.p != null && zfVar.p.a > this.g.a)) {
            this.g = zfVar.p;
            c();
        }
    }
}

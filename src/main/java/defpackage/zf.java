package defpackage;

import android.util.SparseArray;

/* compiled from: BaseDanmaku */
/* renamed from: zf */
public abstract class zf {
    public boolean A;
    protected zh B;
    protected int C = ze.a;
    public int D = 0;
    public int E = -1;
    public zl F = null;
    public int G = 0;
    public int H = -1;
    private long I;
    private int J = 0;
    private SparseArray<Object> K = new SparseArray();
    public long a;
    public CharSequence b;
    public String[] c;
    public Object d;
    public int e;
    public float f;
    public float g;
    public int h;
    public int i = 0;
    public float j = -1.0f;
    public int k = 0;
    public int l = 0;
    public byte m = (byte) 0;
    public float n = -1.0f;
    public float o = -1.0f;
    public zi p;
    public int q;
    public int r;
    public int s = 0;
    public int t = 0;
    public int u = -1;
    public zp<?> v;
    public boolean w;
    public boolean x;
    public int y = 0;
    public String z;

    public abstract void a(zo zoVar, float f, float f2);

    public abstract float[] a(zo zoVar, long j);

    public abstract float k();

    public abstract float l();

    public abstract float m();

    public abstract float n();

    public abstract int o();

    public long a() {
        return this.p.a;
    }

    public int a(zo zoVar) {
        return zoVar.a(this);
    }

    public boolean b() {
        return this.n > -1.0f && this.o > -1.0f && this.s == this.F.a;
    }

    public void a(zo zoVar, boolean z) {
        zoVar.b(this, z);
        this.s = this.F.a;
    }

    public boolean c() {
        return this.u == this.F.f;
    }

    public void b(zo zoVar, boolean z) {
        zoVar.a(this, z);
        this.u = this.F.f;
    }

    public zp<?> d() {
        return this.v;
    }

    public boolean e() {
        return this.r == 1 && this.J == this.F.b;
    }

    public boolean f() {
        return this.B == null || a(this.B.a);
    }

    public boolean a(long j) {
        return j - s() >= this.p.a;
    }

    public boolean g() {
        return this.B == null || b(this.B.a);
    }

    public boolean b(long j) {
        j -= s();
        return j <= 0 || j >= this.p.a;
    }

    public boolean h() {
        return this.B == null || this.B.a < s();
    }

    public boolean i() {
        if (this.E == this.F.c) {
            return true;
        }
        this.D = 0;
        return false;
    }

    public boolean j() {
        return this.E == this.F.c && this.D != 0;
    }

    public void a(boolean z) {
        if (z) {
            this.J = this.F.b;
            this.r = 1;
            return;
        }
        this.r = 0;
    }

    public zh p() {
        return this.B;
    }

    public void a(zh zhVar) {
        this.B = zhVar;
    }

    public int q() {
        return this.C;
    }

    public void a(Object obj) {
        this.d = obj;
    }

    public void c(long j) {
        this.a = j;
        this.t = this.F.e;
    }

    public void d(long j) {
        this.I = j;
        this.a = 0;
    }

    public long r() {
        return this.I;
    }

    public long s() {
        if (this.F != null && this.F.e == this.t) {
            return this.I + this.a;
        }
        this.a = 0;
        return this.I;
    }

    public boolean t() {
        boolean z = false;
        if (this.F == null || this.F.e != this.t) {
            this.a = 0;
            return false;
        }
        if (this.a != 0) {
            z = true;
        }
        return z;
    }
}

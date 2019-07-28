package defpackage;

import android.text.Layout.Alignment;
import com.google.android.exoplayer2.util.a;

/* compiled from: TtmlStyle */
/* renamed from: lg */
final class lg {
    private String a;
    private int b;
    private boolean c;
    private int d;
    private boolean e;
    private int f = -1;
    private int g = -1;
    private int h = -1;
    private int i = -1;
    private int j = -1;
    private float k;
    private String l;
    private lg m;
    private Alignment n;

    public int a() {
        if (this.h == -1 && this.i == -1) {
            return -1;
        }
        int i = 0;
        int i2 = this.h == 1 ? 1 : 0;
        if (this.i == 1) {
            i = 2;
        }
        return i2 | i;
    }

    public boolean b() {
        return this.f == 1;
    }

    public lg a(boolean z) {
        a.b(this.m == null);
        this.f = z;
        return this;
    }

    public boolean c() {
        return this.g == 1;
    }

    public lg b(boolean z) {
        a.b(this.m == null);
        this.g = z;
        return this;
    }

    public lg c(boolean z) {
        a.b(this.m == null);
        this.h = z;
        return this;
    }

    public lg d(boolean z) {
        a.b(this.m == null);
        this.i = z;
        return this;
    }

    public String d() {
        return this.a;
    }

    public lg a(String str) {
        a.b(this.m == null);
        this.a = str;
        return this;
    }

    public int e() {
        if (this.c) {
            return this.b;
        }
        throw new IllegalStateException("Font color has not been defined.");
    }

    public lg a(int i) {
        a.b(this.m == null);
        this.b = i;
        this.c = true;
        return this;
    }

    public boolean f() {
        return this.c;
    }

    public int g() {
        if (this.e) {
            return this.d;
        }
        throw new IllegalStateException("Background color has not been defined.");
    }

    public lg b(int i) {
        this.d = i;
        this.e = true;
        return this;
    }

    public boolean h() {
        return this.e;
    }

    public lg a(lg lgVar) {
        return a(lgVar, true);
    }

    private lg a(lg lgVar, boolean z) {
        if (lgVar != null) {
            if (!this.c && lgVar.c) {
                a(lgVar.b);
            }
            if (this.h == -1) {
                this.h = lgVar.h;
            }
            if (this.i == -1) {
                this.i = lgVar.i;
            }
            if (this.a == null) {
                this.a = lgVar.a;
            }
            if (this.f == -1) {
                this.f = lgVar.f;
            }
            if (this.g == -1) {
                this.g = lgVar.g;
            }
            if (this.n == null) {
                this.n = lgVar.n;
            }
            if (this.j == -1) {
                this.j = lgVar.j;
                this.k = lgVar.k;
            }
            if (z && !this.e && lgVar.e) {
                b(lgVar.d);
            }
        }
        return this;
    }

    public lg b(String str) {
        this.l = str;
        return this;
    }

    public String i() {
        return this.l;
    }

    public Alignment j() {
        return this.n;
    }

    public lg a(Alignment alignment) {
        this.n = alignment;
        return this;
    }

    public lg a(float f) {
        this.k = f;
        return this;
    }

    public lg c(int i) {
        this.j = i;
        return this;
    }

    public int k() {
        return this.j;
    }

    public float l() {
        return this.k;
    }
}

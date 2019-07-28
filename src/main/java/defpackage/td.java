package defpackage;

import android.text.SpannableStringBuilder;

/* compiled from: Line */
/* renamed from: td */
public class td {
    private td a;
    private td b;
    private td c;
    private td d;
    private String e;
    private CharSequence f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    public td h() {
        return this;
    }

    public td(String str) {
        this.e = str;
        this.h = 1;
        this.g = 0;
    }

    private td(td tdVar) {
        this.e = tdVar.e;
        this.h = tdVar.h;
        this.i = tdVar.i;
        if (tdVar.f != null) {
            this.f = new SpannableStringBuilder(tdVar.f);
        }
        this.g = tdVar.g;
    }

    public void a(String str) {
        this.e = str;
    }

    public String a() {
        return this.e;
    }

    public void a(CharSequence charSequence) {
        this.f = charSequence;
    }

    public CharSequence b() {
        return this.f;
    }

    public void a(int i) {
        this.g = i;
    }

    public int c() {
        return this.g;
    }

    public void b(int i) {
        this.h = i;
    }

    public int d() {
        return this.h;
    }

    public int e() {
        return this.j;
    }

    public void c(int i) {
        this.j = i;
    }

    public int f() {
        return this.k;
    }

    public void d(int i) {
        this.k = i;
    }

    public void e(int i) {
        this.i = i;
    }

    public int g() {
        return this.i;
    }

    public td i() {
        return this.b;
    }

    public td j() {
        return this.a;
    }

    public td k() {
        return this.d;
    }

    public td l() {
        return this.c;
    }

    public td a(td tdVar) {
        if (tdVar == null) {
            this.b = null;
        } else {
            if (tdVar.b != null) {
                tdVar.b.a = null;
            }
            tdVar.b = this.b;
            if (this.b != null) {
                this.b.a = tdVar;
            }
            if (tdVar.a != null) {
                tdVar.a.b = null;
            }
            tdVar.a = this;
            this.b = tdVar;
            if (this.d != null) {
                this.d.a(tdVar.d);
            }
        }
        return tdVar;
    }

    public td b(td tdVar) {
        return a(tdVar);
    }

    private void t() {
        if (this.d != null) {
            this.d.t();
        }
        if (this.a != null) {
            this.a.b = null;
        }
        this.a = null;
        if (this.b != null) {
            this.b.a = null;
        }
        this.b = null;
    }

    private void u() {
        if (this.d != null) {
            this.d.u();
        }
        if (this.a != null) {
            this.a.b = this.b;
        }
        if (this.b != null) {
            this.b.a = this.a;
        }
        this.b = null;
        this.a = null;
    }

    public void m() {
        if (this.c == null) {
            u();
        } else {
            t();
        }
    }

    public td n() {
        if (this.b != null) {
            this.b.m();
        }
        return this;
    }

    public td o() {
        if (this.a != null) {
            this.a.m();
        }
        return this;
    }

    public void c(td tdVar) {
        if (this.d != null) {
            this.d.c = null;
        }
        this.d = tdVar;
        if (tdVar.c != null) {
            tdVar.c.d = null;
        }
        tdVar.c = this;
        p();
        q();
    }

    public void p() {
        if (this.d != null && this.b != null) {
            if (this.d.b != null) {
                this.d.b.a = null;
            }
            this.d.b = this.b.d;
            if (this.b.d != null) {
                if (this.b.d.a != null) {
                    this.b.d.a.b = null;
                }
                this.b.d.a = this.d;
            }
            this.d.p();
        }
    }

    public void q() {
        if (this.d != null && this.a != null) {
            if (this.d.a != null) {
                this.d.a.b = null;
            }
            this.d.a = this.a.d;
            if (this.a.d != null) {
                if (this.a.d.b != null) {
                    this.a.d.b.a = null;
                }
                this.a.d.b = this.d;
            }
            this.d.q();
        }
    }

    public void r() {
        if (this.c != null) {
            t();
            this.c.d = null;
        }
        this.c = null;
    }

    public td b(String str) {
        td tdVar = new td(str);
        c(tdVar);
        return tdVar;
    }

    public td s() {
        td s = this.c != null ? this.c.s() : null;
        td tdVar = new td(this);
        if (s == null) {
            tdVar.b = this.b;
            if (this.b != null) {
                this.b.a = tdVar;
            }
            tdVar.a = this;
            this.b = tdVar;
        } else {
            s.c(tdVar);
        }
        return tdVar;
    }

    public String toString() {
        return this.e;
    }
}

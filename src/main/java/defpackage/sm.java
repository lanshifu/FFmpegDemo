package defpackage;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: Spring */
/* renamed from: sm */
public class sm {
    private static int a;
    private sn b;
    private boolean c;
    private final String d;
    private final a e = new a();
    private final a f = new a();
    private final a g = new a();
    private double h;
    private double i;
    private boolean j = true;
    private double k = 0.005d;
    private double l = 0.005d;
    private double m = 0.0d;
    private final CopyOnWriteArraySet<sp> n = new CopyOnWriteArraySet();
    private final sj o;

    /* compiled from: Spring */
    /* renamed from: sm$a */
    private static class a {
        double a;
        double b;

        private a() {
        }
    }

    sm(sj sjVar) {
        if (sjVar != null) {
            this.o = sjVar;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("spring:");
            int i = a;
            a = i + 1;
            stringBuilder.append(i);
            this.d = stringBuilder.toString();
            a(sn.c);
            return;
        }
        throw new IllegalArgumentException("Spring cannot be created outside of a BaseSpringSystem");
    }

    public String a() {
        return this.d;
    }

    public sm a(sn snVar) {
        if (snVar != null) {
            this.b = snVar;
            return this;
        }
        throw new IllegalArgumentException("springConfig is required");
    }

    public sm a(double d) {
        return a(d, true);
    }

    public sm a(double d, boolean z) {
        this.h = d;
        this.e.a = d;
        this.o.a(a());
        Iterator it = this.n.iterator();
        while (it.hasNext()) {
            ((sp) it.next()).a(this);
        }
        if (z) {
            h();
        }
        return this;
    }

    public double b() {
        return this.e.a;
    }

    private double a(a aVar) {
        return Math.abs(this.i - aVar.a);
    }

    public sm b(double d) {
        if (this.i == d && g()) {
            return this;
        }
        this.h = b();
        this.i = d;
        this.o.a(a());
        Iterator it = this.n.iterator();
        while (it.hasNext()) {
            ((sp) it.next()).d(this);
        }
        return this;
    }

    public double c() {
        return this.i;
    }

    public sm c(double d) {
        if (d == this.e.b) {
            return this;
        }
        this.e.b = d;
        this.o.a(a());
        return this;
    }

    public boolean d() {
        return this.b.b > 0.0d && ((this.h < this.i && b() > this.i) || (this.h > this.i && b() < this.i));
    }

    /* Access modifiers changed, original: 0000 */
    public void d(double d) {
        boolean g = g();
        if (!g || !this.j) {
            Object obj;
            double d2 = 0.064d;
            if (d <= 0.064d) {
                d2 = d;
            }
            this.m += d2;
            d2 = this.b.b;
            double d3 = this.b.a;
            double d4 = this.e.a;
            double d5 = this.e.b;
            double d6 = this.g.a;
            double d7 = this.g.b;
            while (this.m >= 0.001d) {
                this.m -= 0.001d;
                if (this.m < 0.001d) {
                    this.f.a = d4;
                    this.f.b = d5;
                }
                d7 = ((this.i - d6) * d2) - (d3 * d5);
                double d8 = d5 + ((d7 * 0.001d) * 0.5d);
                double d9 = ((this.i - (((d5 * 0.001d) * 0.5d) + d4)) * d2) - (d3 * d8);
                double d10 = d5 + ((d9 * 0.001d) * 0.5d);
                double d11 = d7;
                d7 = ((this.i - (((d8 * 0.001d) * 0.5d) + d4)) * d2) - (d3 * d10);
                d6 = (d10 * 0.001d) + d4;
                double d12 = d5 + (d7 * 0.001d);
                d4 += (((d5 + ((d8 + d10) * 2.0d)) + d12) * 0.16666666666666666d) * 0.001d;
                d5 += (((d11 + ((d9 + d7) * 2.0d)) + (((this.i - d6) * d2) - (d3 * d12))) * 0.16666666666666666d) * 0.001d;
                d7 = d12;
            }
            double d13 = d4;
            this.g.a = d6;
            this.g.b = d7;
            this.e.a = d4;
            this.e.b = d5;
            if (this.m > 0.0d) {
                e(this.m / 0.001d);
            }
            if (g() || (this.c && d())) {
                if (d2 > 0.0d) {
                    this.h = this.i;
                    this.e.a = this.i;
                } else {
                    this.i = this.e.a;
                    this.h = this.i;
                }
                c(0.0d);
                g = true;
            }
            boolean z = false;
            if (this.j) {
                this.j = false;
                obj = 1;
            } else {
                obj = null;
            }
            if (g) {
                this.j = true;
                z = true;
            }
            Iterator it = this.n.iterator();
            while (it.hasNext()) {
                sp spVar = (sp) it.next();
                if (obj != null) {
                    spVar.c(this);
                }
                spVar.a(this);
                if (z) {
                    spVar.b(this);
                }
            }
        }
    }

    public boolean e() {
        return (g() && f()) ? false : true;
    }

    public boolean f() {
        return this.j;
    }

    public boolean g() {
        return Math.abs(this.e.b) <= this.k && (a(this.e) <= this.l || this.b.b == 0.0d);
    }

    public sm h() {
        this.i = this.e.a;
        this.g.a = this.e.a;
        this.e.b = 0.0d;
        return this;
    }

    private void e(double d) {
        double d2 = 1.0d - d;
        this.e.a = (this.e.a * d) + (this.f.a * d2);
        this.e.b = (this.e.b * d) + (this.f.b * d2);
    }

    public sm a(sp spVar) {
        if (spVar != null) {
            this.n.add(spVar);
            return this;
        }
        throw new IllegalArgumentException("newListener is required");
    }
}

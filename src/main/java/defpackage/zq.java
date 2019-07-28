package defpackage;

/* compiled from: L2RDanmaku */
/* renamed from: zq */
public class zq extends zr {
    public int o() {
        return 6;
    }

    public zq(zi ziVar) {
        super(ziVar);
    }

    public void a(zo zoVar, float f, float f2) {
        if (this.B != null) {
            long j = this.B.a;
            long s = j - s();
            if (s <= 0 || s >= this.p.a) {
                this.N = j;
            } else {
                this.I = b(zoVar, j);
                if (!e()) {
                    this.J = f2;
                    a(true);
                }
                this.N = j;
                return;
            }
        }
        a(false);
    }

    public float[] a(zo zoVar, long j) {
        if (!b()) {
            return null;
        }
        float b = b(zoVar, j);
        if (this.L == null) {
            this.L = new float[4];
        }
        this.L[0] = b;
        this.L[1] = this.J;
        this.L[2] = b + this.n;
        this.L[3] = this.J + this.o;
        return this.L;
    }

    /* Access modifiers changed, original: protected */
    public float b(zo zoVar, long j) {
        j -= s();
        if (j >= this.p.a) {
            return (float) zoVar.e();
        }
        return (this.M * ((float) j)) - this.n;
    }

    public float k() {
        return this.I;
    }

    public float l() {
        return this.J;
    }

    public float m() {
        return this.I + this.n;
    }

    public float n() {
        return this.J + this.o;
    }
}

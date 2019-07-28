package defpackage;

import com.yalantis.ucrop.view.CropImageView;

/* compiled from: R2LDanmaku */
/* renamed from: zr */
public class zr extends zf {
    protected float I = CropImageView.DEFAULT_ASPECT_RATIO;
    protected float J = -1.0f;
    protected int K;
    protected float[] L = null;
    protected float M;
    protected long N;

    public int o() {
        return 1;
    }

    public zr(zi ziVar) {
        this.p = ziVar;
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

    /* Access modifiers changed, original: protected */
    public float b(zo zoVar, long j) {
        j -= s();
        if (j >= this.p.a) {
            return -this.n;
        }
        return ((float) zoVar.e()) - (((float) j) * this.M);
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

    public void a(zo zoVar, boolean z) {
        super.a(zoVar, z);
        this.K = (int) (((float) zoVar.e()) + this.n);
        this.M = ((float) this.K) / ((float) this.p.a);
    }
}

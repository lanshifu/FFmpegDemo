package defpackage;

import com.yalantis.ucrop.view.CropImageView;

/* compiled from: FTDanmaku */
/* renamed from: zk */
public class zk extends zf {
    protected float I = -1.0f;
    private float J = CropImageView.DEFAULT_ASPECT_RATIO;
    private float[] K = null;
    private float L;
    private float M;
    private int N;

    public int o() {
        return 5;
    }

    public zk(zi ziVar) {
        this.p = ziVar;
    }

    public void a(zo zoVar, float f, float f2) {
        if (this.B != null) {
            long s = this.B.a - s();
            if (s <= 0 || s >= this.p.a) {
                a(false);
                this.I = -1.0f;
                this.J = (float) zoVar.e();
            } else {
                if (!e()) {
                    this.J = b(zoVar);
                    this.I = f2;
                    a(true);
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public float b(zo zoVar) {
        if (this.N == zoVar.e() && this.M == this.n) {
            return this.L;
        }
        float e = (((float) zoVar.e()) - this.n) / 2.0f;
        this.N = zoVar.e();
        this.M = this.n;
        this.L = e;
        return e;
    }

    public float[] a(zo zoVar, long j) {
        if (!b()) {
            return null;
        }
        float b = b(zoVar);
        if (this.K == null) {
            this.K = new float[4];
        }
        this.K[0] = b;
        this.K[1] = this.I;
        this.K[2] = b + this.n;
        this.K[3] = this.I + this.o;
        return this.K;
    }

    public float k() {
        return this.J;
    }

    public float l() {
        return this.I;
    }

    public float m() {
        return this.J + this.n;
    }

    public float n() {
        return this.I + this.o;
    }
}

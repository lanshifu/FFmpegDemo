package defpackage;

import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.Array;

/* compiled from: SpecialDanmaku */
/* renamed from: zs */
public class zs extends zf {
    public float I;
    public float J;
    public float K;
    public float L;
    public float M;
    public float N;
    public long O;
    public long P;
    public boolean Q = false;
    public int R;
    public int S;
    public int T;
    public long U;
    public float V;
    public a[] W;
    private c X;
    private int Y;
    private int Z = 0;
    private int aa = 0;
    private float[] ab = new float[4];

    /* compiled from: SpecialDanmaku */
    /* renamed from: zs$a */
    public class a {
        b a;
        b b;
        public long c;
        public long d;
        public long e;
        float f;
        float g;

        public void a(b bVar, b bVar2) {
            this.a = bVar;
            this.b = bVar2;
            this.f = bVar2.a - bVar.a;
            this.g = bVar2.b - bVar.b;
        }

        public float a() {
            return this.b.a(this.a);
        }

        public float[] b() {
            return new float[]{this.a.a, this.a.b};
        }

        public float[] c() {
            return new float[]{this.b.a, this.b.b};
        }
    }

    /* compiled from: SpecialDanmaku */
    /* renamed from: zs$b */
    private class b {
        float a;
        float b;

        public b(float f, float f2) {
            this.a = f;
            this.b = f2;
        }

        public float a(b bVar) {
            float abs = Math.abs(this.a - bVar.a);
            float abs2 = Math.abs(this.b - bVar.b);
            return (float) Math.sqrt((double) ((abs * abs) + (abs2 * abs2)));
        }
    }

    /* compiled from: SpecialDanmaku */
    /* renamed from: zs$c */
    public static class c {
        int a = 0;
        float b;
        float c;
        int d;
        int e;

        public c(int i, int i2, float f, float f2) {
            a(i, i2, f, f2);
        }

        public void a(int i, int i2, float f, float f2) {
            if (!(Float.compare(this.b, f) == 0 && Float.compare(this.c, f2) == 0)) {
                this.a++;
            }
            this.d = i;
            this.e = i2;
            this.b = f;
            this.c = f2;
        }

        public boolean a(int i, int i2, int i3) {
            return (this.a == i || (this.d == i2 && this.e == i3)) ? false : true;
        }
    }

    private static final float a(long j, long j2) {
        float f = ((float) j) / ((float) j2);
        return (-1.0f * f) * (f - 2.0f);
    }

    public int o() {
        return 7;
    }

    public void a(zo zoVar, boolean z) {
        super.a(zoVar, z);
        if (this.Z == 0 || this.aa == 0) {
            this.Z = zoVar.e();
            this.aa = zoVar.f();
        }
    }

    public void a(zo zoVar, float f, float f2) {
        a(zoVar, this.B.a);
    }

    public float[] a(zo zoVar, long j) {
        if (!b()) {
            return null;
        }
        float f;
        int i;
        if (this.X.a(this.Y, this.Z, this.aa)) {
            f = this.X.b;
            float f2 = this.X.c;
            a(this.I * f, this.J * f2, this.K * f, this.L * f2, this.O, this.P);
            if (this.W != null && this.W.length > 0) {
                int length = this.W.length;
                float[][] fArr = (float[][]) Array.newInstance(float.class, new int[]{length + 1, 2});
                i = 0;
                while (i < length) {
                    fArr[i] = this.W[i].b();
                    int i2 = i + 1;
                    fArr[i2] = this.W[i].c();
                    i = i2;
                }
                for (length = 0; length < fArr.length; length++) {
                    float[] fArr2 = fArr[length];
                    fArr2[0] = fArr2[0] * f;
                    fArr2 = fArr[length];
                    fArr2[1] = fArr2[1] * f2;
                }
                a(fArr);
            }
            this.Y = this.X.a;
            this.Z = this.X.d;
            this.aa = this.X.e;
        }
        long s = j - s();
        if (this.U > 0 && this.T != 0) {
            if (s >= this.U) {
                this.C = this.S;
            } else {
                this.C = this.R + ((int) (((float) this.T) * (((float) s) / ((float) this.U))));
            }
        }
        float f3 = this.I;
        float f4 = this.J;
        long j2 = s - this.P;
        float f5;
        if (this.O <= 0 || j2 < 0 || j2 > this.O) {
            if (j2 > this.O) {
                f3 = this.K;
                f4 = this.L;
            }
        } else if (this.W != null) {
            f = f4;
            f4 = f3;
            for (a aVar : this.W) {
                if (j2 >= aVar.d && j2 < aVar.e) {
                    break;
                }
                f4 = aVar.b.a;
                f = aVar.b.b;
            }
            a aVar2 = null;
            if (aVar2 != null) {
                f3 = aVar2.f;
                float f6 = aVar2.g;
                f5 = ((float) (s - aVar2.d)) / ((float) aVar2.c);
                float f7 = aVar2.a.a;
                float f8 = aVar2.a.b;
                if (f3 != CropImageView.DEFAULT_ASPECT_RATIO) {
                    f4 = f7 + (f3 * f5);
                }
                if (f6 != CropImageView.DEFAULT_ASPECT_RATIO) {
                    f = f8 + (f6 * f5);
                }
            }
            f3 = f4;
            f4 = f;
        } else {
            f5 = this.Q ? zs.a(j2, this.O) : ((float) j2) / ((float) this.O);
            if (this.M != CropImageView.DEFAULT_ASPECT_RATIO) {
                f3 = this.I + (this.M * f5);
            }
            if (this.N != CropImageView.DEFAULT_ASPECT_RATIO) {
                f4 = this.J + (this.N * f5);
            }
        }
        this.ab[0] = f3;
        this.ab[1] = f4;
        this.ab[2] = f3 + this.n;
        this.ab[3] = f4 + this.o;
        a((boolean) g() ^ 1);
        return this.ab;
    }

    public float k() {
        return this.ab[0];
    }

    public float l() {
        return this.ab[1];
    }

    public float m() {
        return this.ab[2];
    }

    public float n() {
        return this.ab[3];
    }

    public void a(float f, float f2, float f3, float f4, long j, long j2) {
        this.I = f;
        this.J = f2;
        this.K = f3;
        this.L = f4;
        this.M = f3 - f;
        this.N = f4 - f2;
        this.O = j;
        this.P = j2;
    }

    public void a(int i, int i2, long j) {
        this.R = i;
        this.S = i2;
        this.T = i2 - i;
        this.U = j;
        if (i != ze.a) {
            this.C = i;
        }
    }

    public void a(float[][] fArr) {
        if (fArr != null) {
            int length = fArr.length;
            int i = 0;
            this.I = fArr[0][0];
            this.J = fArr[0][1];
            length--;
            this.K = fArr[length][0];
            this.L = fArr[length][1];
            if (fArr.length > 1) {
                a aVar;
                this.W = new a[(fArr.length - 1)];
                length = 0;
                while (length < this.W.length) {
                    this.W[length] = new a();
                    length++;
                    this.W[length].a(new b(fArr[length][0], fArr[length][1]), new b(fArr[length][0], fArr[length][1]));
                }
                float f = CropImageView.DEFAULT_ASPECT_RATIO;
                for (a aVar2 : this.W) {
                    f += aVar2.a();
                }
                a aVar3 = null;
                a[] aVarArr = this.W;
                int length2 = aVarArr.length;
                while (i < length2) {
                    long j;
                    aVar2 = aVarArr[i];
                    aVar2.c = (long) ((aVar2.a() / f) * ((float) this.O));
                    if (aVar3 == null) {
                        j = 0;
                    } else {
                        j = aVar3.e;
                    }
                    aVar2.d = j;
                    aVar2.e = aVar2.d + aVar2.c;
                    i++;
                    aVar3 = aVar2;
                }
            }
        }
    }

    public void a(c cVar) {
        this.X = cVar;
        this.Y = cVar.a;
    }
}

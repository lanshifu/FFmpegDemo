package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import defpackage.hs.a;

/* compiled from: XingSeeker */
/* renamed from: hu */
final class hu implements a {
    private final long a;
    private final int b;
    private final long c;
    private final long d;
    private final long[] e;

    public static hu a(long j, long j2, hf hfVar, n nVar) {
        long j3 = j;
        hf hfVar2 = hfVar;
        int i = hfVar2.g;
        int i2 = hfVar2.d;
        int o = nVar.o();
        if ((o & 1) == 1) {
            int u = nVar.u();
            if (u != 0) {
                long d = z.d((long) u, ((long) i) * 1000000, (long) i2);
                if ((o & 6) != 6) {
                    return new hu(j2, hfVar2.c, d);
                }
                long u2 = (long) nVar.u();
                long[] jArr = new long[100];
                for (int i3 = 0; i3 < 100; i3++) {
                    jArr[i3] = (long) nVar.g();
                }
                if (j3 != -1) {
                    long j4 = j2 + u2;
                    if (j3 != j4) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("XING data size mismatch: ");
                        stringBuilder.append(j3);
                        stringBuilder.append(", ");
                        stringBuilder.append(j4);
                        Log.w("XingSeeker", stringBuilder.toString());
                    }
                }
                return new hu(j2, hfVar2.c, d, u2, jArr);
            }
        }
        return null;
    }

    private hu(long j, int i, long j2) {
        this(j, i, j2, -1, null);
    }

    private hu(long j, int i, long j2, long j3, long[] jArr) {
        this.a = j;
        this.b = i;
        this.c = j2;
        this.d = j3;
        this.e = jArr;
    }

    public boolean a() {
        return this.e != null;
    }

    public hh.a b(long j) {
        if (!a()) {
            return new hh.a(new hi(0, this.a + ((long) this.b)));
        }
        j = z.a(j, 0, this.c);
        double d = (double) j;
        Double.isNaN(d);
        d *= 100.0d;
        double d2 = (double) this.c;
        Double.isNaN(d2);
        d /= d2;
        d2 = 0.0d;
        if (d > 0.0d) {
            if (d >= 100.0d) {
                d2 = 256.0d;
            } else {
                double d3;
                int i = (int) d;
                double d4 = (double) this.e[i];
                if (i == 99) {
                    d3 = 256.0d;
                } else {
                    d3 = (double) this.e[i + 1];
                }
                double d5 = (double) i;
                Double.isNaN(d5);
                d -= d5;
                Double.isNaN(d4);
                d *= d3 - d4;
                Double.isNaN(d4);
                d2 = d4 + d;
            }
        }
        d2 /= 256.0d;
        d = (double) this.d;
        Double.isNaN(d);
        return new hh.a(new hi(j, this.a + z.a(Math.round(d2 * d), (long) this.b, this.d - 1)));
    }

    public long a(long j) {
        j -= this.a;
        if (!a() || j <= ((long) this.b)) {
            return 0;
        }
        long j2;
        double d;
        double d2 = (double) j;
        Double.isNaN(d2);
        d2 *= 256.0d;
        double d3 = (double) this.d;
        Double.isNaN(d3);
        d2 /= d3;
        int a = z.a(this.e, (long) d2, true, true);
        long a2 = a(a);
        long j3 = this.e[a];
        int i = a + 1;
        long a3 = a(i);
        if (a == 99) {
            j2 = 256;
        } else {
            j2 = this.e[i];
        }
        if (j3 == j2) {
            d2 = 0.0d;
        } else {
            double d4 = (double) j3;
            Double.isNaN(d4);
            d2 -= d4;
            d = (double) (j2 - j3);
            Double.isNaN(d);
            d2 /= d;
        }
        d = (double) (a3 - a2);
        Double.isNaN(d);
        return a2 + Math.round(d2 * d);
    }

    public long b() {
        return this.c;
    }

    private long a(int i) {
        return (this.c * ((long) i)) / 100;
    }
}

package defpackage;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.l;
import com.google.android.exoplayer2.util.n;
import defpackage.jd.d;
import java.util.Arrays;
import java.util.Collections;

/* compiled from: H262Reader */
/* renamed from: ip */
public final class ip implements io {
    private static final double[] c = new double[]{23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private String a;
    private hj b;
    private boolean d;
    private long e;
    private final boolean[] f = new boolean[4];
    private final a g = new a(128);
    private long h;
    private boolean i;
    private long j;
    private long k;
    private long l;
    private boolean m;
    private boolean n;

    /* compiled from: H262Reader */
    /* renamed from: ip$a */
    private static final class a {
        private static final byte[] d = new byte[]{(byte) 0, (byte) 0, (byte) 1};
        public int a;
        public int b;
        public byte[] c;
        private boolean e;

        public a(int i) {
            this.c = new byte[i];
        }

        public void a() {
            this.e = false;
            this.a = 0;
            this.b = 0;
        }

        public boolean a(int i, int i2) {
            if (this.e) {
                this.a -= i2;
                if (this.b == 0 && i == 181) {
                    this.b = this.a;
                } else {
                    this.e = false;
                    return true;
                }
            } else if (i == 179) {
                this.e = true;
            }
            a(d, 0, d.length);
            return false;
        }

        public void a(byte[] bArr, int i, int i2) {
            if (this.e) {
                i2 -= i;
                if (this.c.length < this.a + i2) {
                    this.c = Arrays.copyOf(this.c, (this.a + i2) * 2);
                }
                System.arraycopy(bArr, i, this.c, this.a, i2);
                this.a += i2;
            }
        }
    }

    public void b() {
    }

    public void a() {
        l.a(this.f);
        this.g.a();
        this.h = 0;
        this.i = false;
    }

    public void a(hb hbVar, d dVar) {
        dVar.a();
        this.a = dVar.c();
        this.b = hbVar.a(dVar.b(), 2);
    }

    public void a(long j, boolean z) {
        this.j = j;
    }

    public void a(n nVar) {
        n nVar2 = nVar;
        int d = nVar.d();
        int c = nVar.c();
        byte[] bArr = nVar2.a;
        this.h += (long) nVar.b();
        this.b.a(nVar2, nVar.b());
        while (true) {
            int a = l.a(bArr, d, c, this.f);
            if (a == c) {
                break;
            }
            int i = a + 3;
            int i2 = nVar2.a[i] & 255;
            if (!this.d) {
                int i3 = a - d;
                if (i3 > 0) {
                    this.g.a(bArr, d, a);
                }
                if (this.g.a(i2, i3 < 0 ? -i3 : 0)) {
                    Pair a2 = ip.a(this.g, this.a);
                    this.b.a((Format) a2.first);
                    this.e = ((Long) a2.second).longValue();
                    this.d = true;
                }
            }
            if (i2 == 0 || i2 == 179) {
                boolean z;
                d = c - a;
                if (this.i && this.n && this.d) {
                    this.b.a(this.l, this.m, ((int) (this.h - this.k)) - d, d, null);
                }
                if (!this.i || this.n) {
                    this.k = this.h - ((long) d);
                    long j = this.j != -9223372036854775807L ? this.j : this.i ? this.l + this.e : 0;
                    this.l = j;
                    z = false;
                    this.m = false;
                    this.j = -9223372036854775807L;
                    this.i = true;
                } else {
                    z = false;
                }
                if (i2 == 0) {
                    z = true;
                }
                this.n = z;
            } else if (i2 == 184) {
                this.m = true;
            }
            d = i;
        }
        if (!this.d) {
            this.g.a(bArr, d, c);
        }
    }

    private static Pair<Format, Long> a(a aVar, String str) {
        float f;
        float f2;
        a aVar2 = aVar;
        byte[] copyOf = Arrays.copyOf(aVar2.c, aVar2.a);
        int i = copyOf[5] & 255;
        int i2 = ((copyOf[4] & 255) << 4) | (i >> 4);
        int i3 = ((i & 15) << 8) | (copyOf[6] & 255);
        switch ((copyOf[7] & 240) >> 4) {
            case 2:
                f = ((float) (i3 * 4)) / ((float) (i2 * 3));
                break;
            case 3:
                f = ((float) (i3 * 16)) / ((float) (i2 * 9));
                break;
            case 4:
                f = ((float) (i3 * 121)) / ((float) (i2 * 100));
                break;
            default:
                f2 = 1.0f;
                break;
        }
        f2 = f;
        Format a = Format.a(str, "video/mpeg2", null, -1, -1, i2, i3, -1.0f, Collections.singletonList(copyOf), -1, f2, null);
        long j = 0;
        int i4 = (copyOf[7] & 15) - 1;
        if (i4 >= 0 && i4 < c.length) {
            double d = c[i4];
            int i5 = aVar2.b + 9;
            i4 = (copyOf[i5] & 96) >> 5;
            i5 = copyOf[i5] & 31;
            if (i4 != i5) {
                double d2 = (double) i4;
                Double.isNaN(d2);
                d2 += 1.0d;
                double d3 = (double) (i5 + 1);
                Double.isNaN(d3);
                d *= d2 / d3;
            }
            j = (long) (1000000.0d / d);
        }
        return Pair.create(a, Long.valueOf(j));
    }
}

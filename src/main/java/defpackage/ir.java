package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.l;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.o;
import defpackage.jd.d;
import java.util.Collections;

/* compiled from: H265Reader */
/* renamed from: ir */
public final class ir implements io {
    private final ja a;
    private String b;
    private hj c;
    private a d;
    private boolean e;
    private final boolean[] f = new boolean[3];
    private final iv g = new iv(32, 128);
    private final iv h = new iv(33, 128);
    private final iv i = new iv(34, 128);
    private final iv j = new iv(39, 128);
    private final iv k = new iv(40, 128);
    private long l;
    private long m;
    private final n n = new n();

    /* compiled from: H265Reader */
    /* renamed from: ir$a */
    private static final class a {
        private final hj a;
        private long b;
        private boolean c;
        private int d;
        private long e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private long k;
        private long l;
        private boolean m;

        public a(hj hjVar) {
            this.a = hjVar;
        }

        public void a() {
            this.f = false;
            this.g = false;
            this.h = false;
            this.i = false;
            this.j = false;
        }

        public void a(long j, int i, int i2, long j2) {
            this.g = false;
            this.h = false;
            this.e = j2;
            this.d = 0;
            this.b = j;
            boolean z = true;
            if (i2 >= 32) {
                if (!this.j && this.i) {
                    a(i);
                    this.i = false;
                }
                if (i2 <= 34) {
                    this.h = this.j ^ 1;
                    this.j = true;
                }
            }
            boolean z2 = i2 >= 16 && i2 <= 21;
            this.c = z2;
            if (!this.c && i2 > 9) {
                z = false;
            }
            this.f = z;
        }

        public void a(byte[] bArr, int i, int i2) {
            if (this.f) {
                int i3 = (i + 2) - this.d;
                if (i3 < i2) {
                    this.g = (bArr[i3] & 128) != 0;
                    this.f = false;
                    return;
                }
                this.d += i2 - i;
            }
        }

        public void a(long j, int i) {
            if (this.j && this.g) {
                this.m = this.c;
                this.j = false;
            } else if (this.h || this.g) {
                if (this.i) {
                    a(i + ((int) (j - this.b)));
                }
                this.k = this.b;
                this.l = this.e;
                this.i = true;
                this.m = this.c;
            }
        }

        private void a(int i) {
            this.a.a(this.l, this.m, (int) (this.b - this.k), i, null);
        }
    }

    public void b() {
    }

    public ir(ja jaVar) {
        this.a = jaVar;
    }

    public void a() {
        l.a(this.f);
        this.g.a();
        this.h.a();
        this.i.a();
        this.j.a();
        this.k.a();
        this.d.a();
        this.l = 0;
    }

    public void a(hb hbVar, d dVar) {
        dVar.a();
        this.b = dVar.c();
        this.c = hbVar.a(dVar.b(), 2);
        this.d = new a(this.c);
        this.a.a(hbVar, dVar);
    }

    public void a(long j, boolean z) {
        this.m = j;
    }

    public void a(n nVar) {
        n nVar2 = nVar;
        while (nVar.b() > 0) {
            int d = nVar.d();
            int c = nVar.c();
            byte[] bArr = nVar2.a;
            this.l += (long) nVar.b();
            this.c.a(nVar2, nVar.b());
            while (d < c) {
                int a = l.a(bArr, d, c, this.f);
                if (a == c) {
                    a(bArr, d, c);
                    return;
                }
                int c2 = l.c(bArr, a);
                int i = a - d;
                if (i > 0) {
                    a(bArr, d, a);
                }
                int i2 = c - a;
                long j = this.l - ((long) i2);
                int i3 = i < 0 ? -i : 0;
                long j2 = j;
                int i4 = i2;
                b(j2, i4, i3, this.m);
                a(j2, i4, c2, this.m);
                d = a + 3;
            }
        }
    }

    private void a(long j, int i, int i2, long j2) {
        if (this.e) {
            this.d.a(j, i, i2, j2);
        } else {
            this.g.a(i2);
            this.h.a(i2);
            this.i.a(i2);
        }
        this.j.a(i2);
        this.k.a(i2);
    }

    private void a(byte[] bArr, int i, int i2) {
        if (this.e) {
            this.d.a(bArr, i, i2);
        } else {
            this.g.a(bArr, i, i2);
            this.h.a(bArr, i, i2);
            this.i.a(bArr, i, i2);
        }
        this.j.a(bArr, i, i2);
        this.k.a(bArr, i, i2);
    }

    private void b(long j, int i, int i2, long j2) {
        if (this.e) {
            this.d.a(j, i);
        } else {
            this.g.b(i2);
            this.h.b(i2);
            this.i.b(i2);
            if (this.g.b() && this.h.b() && this.i.b()) {
                this.c.a(ir.a(this.b, this.g, this.h, this.i));
                this.e = true;
            }
        }
        if (this.j.b(i2)) {
            this.n.a(this.j.a, l.a(this.j.a, this.j.b));
            this.n.d(5);
            this.a.a(j2, this.n);
        }
        if (this.k.b(i2)) {
            this.n.a(this.k.a, l.a(this.k.a, this.k.b));
            this.n.d(5);
            this.a.a(j2, this.n);
        }
    }

    private static Format a(String str, iv ivVar, iv ivVar2, iv ivVar3) {
        float f;
        iv ivVar4 = ivVar;
        iv ivVar5 = ivVar2;
        iv ivVar6 = ivVar3;
        byte[] bArr = new byte[((ivVar4.b + ivVar5.b) + ivVar6.b)];
        int i = 0;
        System.arraycopy(ivVar4.a, 0, bArr, 0, ivVar4.b);
        System.arraycopy(ivVar5.a, 0, bArr, ivVar4.b, ivVar5.b);
        System.arraycopy(ivVar6.a, 0, bArr, ivVar4.b + ivVar5.b, ivVar6.b);
        o oVar = new o(ivVar5.a, 0, ivVar5.b);
        oVar.a(44);
        int c = oVar.c(3);
        oVar.a();
        oVar.a(88);
        oVar.a(8);
        int i2 = 0;
        for (int i3 = 0; i3 < c; i3++) {
            if (oVar.b()) {
                i2 += 89;
            }
            if (oVar.b()) {
                i2 += 8;
            }
        }
        oVar.a(i2);
        if (c > 0) {
            oVar.a((8 - c) * 2);
        }
        oVar.d();
        i2 = oVar.d();
        if (i2 == 3) {
            oVar.a();
        }
        int d = oVar.d();
        int d2 = oVar.d();
        if (oVar.b()) {
            int d3 = oVar.d();
            int d4 = oVar.d();
            int d5 = oVar.d();
            int d6 = oVar.d();
            int i4 = (i2 == 1 || i2 == 2) ? 2 : 1;
            d -= i4 * (d3 + d4);
            d2 -= (i2 == 1 ? 2 : 1) * (d5 + d6);
        }
        int i5 = d;
        int i6 = d2;
        oVar.d();
        oVar.d();
        d = oVar.d();
        i2 = oVar.b() ? 0 : c;
        while (i2 <= c) {
            oVar.d();
            oVar.d();
            oVar.d();
            i2++;
        }
        oVar.d();
        oVar.d();
        oVar.d();
        oVar.d();
        oVar.d();
        oVar.d();
        if (oVar.b() && oVar.b()) {
            ir.a(oVar);
        }
        oVar.a(2);
        if (oVar.b()) {
            oVar.a(8);
            oVar.d();
            oVar.d();
            oVar.a();
        }
        ir.b(oVar);
        if (oVar.b()) {
            while (i < oVar.d()) {
                oVar.a((d + 4) + 1);
                i++;
            }
        }
        oVar.a(2);
        float f2 = 1.0f;
        if (oVar.b() && oVar.b()) {
            c = oVar.c(8);
            if (c == 255) {
                int c2 = oVar.c(16);
                int c3 = oVar.c(16);
                if (!(c2 == 0 || c3 == 0)) {
                    f2 = ((float) c2) / ((float) c3);
                }
                f = f2;
            } else if (c < l.b.length) {
                f = l.b[c];
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected aspect_ratio_idc value: ");
                stringBuilder.append(c);
                Log.w("H265Reader", stringBuilder.toString());
            }
            return Format.a(str, "video/hevc", null, -1, -1, i5, i6, -1.0f, Collections.singletonList(bArr), -1, f, null);
        }
        f = 1.0f;
        return Format.a(str, "video/hevc", null, -1, -1, i5, i6, -1.0f, Collections.singletonList(bArr), -1, f, null);
    }

    private static void a(o oVar) {
        for (int i = 0; i < 4; i++) {
            int i2 = 0;
            while (i2 < 6) {
                int min;
                if (oVar.b()) {
                    min = Math.min(64, 1 << ((i << 1) + 4));
                    if (i > 1) {
                        oVar.e();
                    }
                    for (int i3 = 0; i3 < min; i3++) {
                        oVar.e();
                    }
                } else {
                    oVar.d();
                }
                min = 3;
                if (i != 3) {
                    min = 1;
                }
                i2 += min;
            }
        }
    }

    private static void b(o oVar) {
        int d = oVar.d();
        boolean z = false;
        int i = 0;
        for (int i2 = 0; i2 < d; i2++) {
            if (i2 != 0) {
                z = oVar.b();
            }
            int i3;
            if (z) {
                oVar.a();
                oVar.d();
                for (i3 = 0; i3 <= i; i3++) {
                    if (oVar.b()) {
                        oVar.a();
                    }
                }
            } else {
                i = oVar.d();
                i3 = oVar.d();
                int i4 = i + i3;
                for (int i5 = 0; i5 < i; i5++) {
                    oVar.d();
                    oVar.a();
                }
                for (i = 0; i < i3; i++) {
                    oVar.d();
                    oVar.a();
                }
                i = i4;
            }
        }
    }
}

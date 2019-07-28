package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import java.io.EOFException;
import java.io.IOException;

/* compiled from: Mp3Extractor */
/* renamed from: hs */
public final class hs implements gz {
    public static final hc a = new 1();
    private static final int b = z.g("Xing");
    private static final int c = z.g("Info");
    private static final int d = z.g("VBRI");
    private final int e;
    private final long f;
    private final n g;
    private final hf h;
    private final hd i;
    private final he j;
    private hb k;
    private hj l;
    private int m;
    private Metadata n;
    private a o;
    private long p;
    private long q;
    private int r;

    /* compiled from: Mp3Extractor */
    /* renamed from: hs$1 */
    static class 1 implements hc {
        1() {
        }

        public gz[] a() {
            return new gz[]{new hs()};
        }
    }

    /* compiled from: Mp3Extractor */
    /* renamed from: hs$a */
    interface a extends hh {
        long a(long j);
    }

    private static boolean a(int i, long j) {
        return ((long) (i & -128000)) == (j & -128000);
    }

    public void c() {
    }

    public hs() {
        this(0);
    }

    public hs(int i) {
        this(i, -9223372036854775807L);
    }

    public hs(int i, long j) {
        this.e = i;
        this.f = j;
        this.g = new n(10);
        this.h = new hf();
        this.i = new hd();
        this.p = -9223372036854775807L;
        this.j = new he();
    }

    public boolean a(ha haVar) throws IOException, InterruptedException {
        return a(haVar, true);
    }

    public void a(hb hbVar) {
        this.k = hbVar;
        this.l = this.k.a(0, 1);
        this.k.a();
    }

    public void a(long j, long j2) {
        this.m = 0;
        this.p = -9223372036854775807L;
        this.q = 0;
        this.r = 0;
    }

    public int a(ha haVar, hg hgVar) throws IOException, InterruptedException {
        if (this.m == 0) {
            try {
                a(haVar, false);
            } catch (EOFException unused) {
                return -1;
            }
        }
        ha haVar2 = haVar;
        if (this.o == null) {
            this.o = c(haVar);
            if (this.o == null || !(this.o.a() || (this.e & 1) == 0)) {
                this.o = d(haVar);
            }
            this.k.a(this.o);
            this.l.a(Format.a(null, this.h.b, null, -1, 4096, this.h.e, this.h.d, -1, this.i.b, this.i.c, null, null, 0, null, (this.e & 2) != 0 ? null : this.n));
        }
        return b(haVar);
    }

    private int b(ha haVar) throws IOException, InterruptedException {
        if (this.r == 0) {
            haVar.a();
            if (!haVar.b(this.g.a, 0, 4, true)) {
                return -1;
            }
            this.g.c(0);
            int o = this.g.o();
            if (!hs.a(o, (long) this.m) || hf.a(o) == -1) {
                haVar.b(1);
                this.m = 0;
                return 0;
            }
            hf.a(o, this.h);
            if (this.p == -9223372036854775807L) {
                this.p = this.o.a(haVar.c());
                if (this.f != -9223372036854775807L) {
                    this.p += this.f - this.o.a(0);
                }
            }
            this.r = this.h.c;
        }
        int a = this.l.a(haVar, this.r, true);
        if (a == -1) {
            return -1;
        }
        this.r -= a;
        if (this.r > 0) {
            return 0;
        }
        this.l.a(this.p + ((this.q * 1000000) / ((long) this.h.d)), 1, this.h.c, 0, null);
        this.q += (long) this.h.g;
        this.r = 0;
        return 0;
    }

    private boolean a(ha haVar, boolean z) throws IOException, InterruptedException {
        int b;
        int i;
        int i2;
        int i3;
        int i4 = z ? 16384 : 131072;
        haVar.a();
        if (haVar.c() == 0) {
            this.n = this.j.a(haVar, ((this.e & 2) != 0 ? 1 : null) != null ? hd.a : null);
            if (this.n != null) {
                this.i.a(this.n);
            }
            b = (int) haVar.b();
            if (!z) {
                haVar.b(b);
            }
            i = b;
            b = 0;
            i2 = 0;
            i3 = 0;
        } else {
            b = 0;
            i2 = 0;
            i3 = 0;
            i = 0;
        }
        while (true) {
            if (!haVar.b(this.g.a, 0, 4, b > 0)) {
                break;
            }
            this.g.c(0);
            int o = this.g.o();
            if (i2 == 0 || hs.a(o, (long) i2)) {
                int a = hf.a(o);
                if (a != -1) {
                    b++;
                    if (b != 1) {
                        if (b == 4) {
                            break;
                        }
                    }
                    hf.a(o, this.h);
                    i2 = o;
                    haVar.c(a - 4);
                }
            }
            b = i3 + 1;
            if (i3 != i4) {
                if (z) {
                    haVar.a();
                    haVar.c(i + b);
                } else {
                    haVar.b(1);
                }
                i3 = b;
                b = 0;
                i2 = 0;
            } else if (z) {
                return false;
            } else {
                throw new ParserException("Searched too many bytes.");
            }
        }
        if (z) {
            haVar.b(i + i3);
        } else {
            haVar.a();
        }
        this.m = i2;
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003e  */
    private defpackage.hs.a c(defpackage.ha r10) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r9 = this;
        r5 = new com.google.android.exoplayer2.util.n;
        r0 = r9.h;
        r0 = r0.c;
        r5.<init>(r0);
        r0 = r5.a;
        r1 = r9.h;
        r1 = r1.c;
        r6 = 0;
        r10.c(r0, r6, r1);
        r0 = r9.h;
        r0 = r0.a;
        r1 = 1;
        r0 = r0 & r1;
        r2 = 21;
        if (r0 == 0) goto L_0x002b;
    L_0x001d:
        r0 = r9.h;
        r0 = r0.e;
        if (r0 == r1) goto L_0x0028;
    L_0x0023:
        r2 = 36;
        r7 = 36;
        goto L_0x0036;
    L_0x0028:
        r7 = 21;
        goto L_0x0036;
    L_0x002b:
        r0 = r9.h;
        r0 = r0.e;
        if (r0 == r1) goto L_0x0032;
    L_0x0031:
        goto L_0x0028;
    L_0x0032:
        r2 = 13;
        r7 = 13;
    L_0x0036:
        r8 = defpackage.hs.a(r5, r7);
        r0 = b;
        if (r8 == r0) goto L_0x0062;
    L_0x003e:
        r0 = c;
        if (r8 != r0) goto L_0x0043;
    L_0x0042:
        goto L_0x0062;
    L_0x0043:
        r0 = d;
        if (r8 != r0) goto L_0x005d;
    L_0x0047:
        r0 = r10.d();
        r2 = r10.c();
        r4 = r9.h;
        r0 = defpackage.ht.a(r0, r2, r4, r5);
        r1 = r9.h;
        r1 = r1.c;
        r10.b(r1);
        goto L_0x00b2;
    L_0x005d:
        r0 = 0;
        r10.a();
        goto L_0x00b2;
    L_0x0062:
        r0 = r10.d();
        r2 = r10.c();
        r4 = r9.h;
        r0 = defpackage.hu.a(r0, r2, r4, r5);
        if (r0 == 0) goto L_0x009a;
    L_0x0072:
        r1 = r9.i;
        r1 = r1.a();
        if (r1 != 0) goto L_0x009a;
    L_0x007a:
        r10.a();
        r7 = r7 + 141;
        r10.c(r7);
        r1 = r9.g;
        r1 = r1.a;
        r2 = 3;
        r10.c(r1, r6, r2);
        r1 = r9.g;
        r1.c(r6);
        r1 = r9.i;
        r2 = r9.g;
        r2 = r2.k();
        r1.a(r2);
    L_0x009a:
        r1 = r9.h;
        r1 = r1.c;
        r10.b(r1);
        if (r0 == 0) goto L_0x00b2;
    L_0x00a3:
        r1 = r0.a();
        if (r1 != 0) goto L_0x00b2;
    L_0x00a9:
        r1 = c;
        if (r8 != r1) goto L_0x00b2;
    L_0x00ad:
        r10 = r9.d(r10);
        return r10;
    L_0x00b2:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hs.c(ha):hs$a");
    }

    private a d(ha haVar) throws IOException, InterruptedException {
        haVar.c(this.g.a, 0, 4);
        this.g.c(0);
        hf.a(this.g.o(), this.h);
        return new hr(haVar.d(), haVar.c(), this.h);
    }

    private static int a(n nVar, int i) {
        if (nVar.c() >= i + 4) {
            nVar.c(i);
            i = nVar.o();
            if (i == b || i == c) {
                return i;
            }
        }
        if (nVar.c() >= 40) {
            nVar.c(36);
            if (nVar.o() == d) {
                return d;
            }
        }
        return 0;
    }
}

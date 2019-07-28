package defpackage;

import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import defpackage.hh.b;
import defpackage.jd.d;
import java.io.IOException;

/* compiled from: AdtsExtractor */
/* renamed from: ij */
public final class ij implements gz {
    public static final hc a = new 1();
    private static final int b = z.g("ID3");
    private final long c;
    private final ik d;
    private final n e;
    private boolean f;

    /* compiled from: AdtsExtractor */
    /* renamed from: ij$1 */
    static class 1 implements hc {
        1() {
        }

        public gz[] a() {
            return new gz[]{new ij()};
        }
    }

    public void c() {
    }

    public ij() {
        this(0);
    }

    public ij(long j) {
        this.c = j;
        this.d = new ik(true);
        this.e = new n(200);
    }

    /* JADX WARNING: Missing block: B:7:0x003f, code skipped:
            r11.a();
            r1 = r1 + 1;
     */
    /* JADX WARNING: Missing block: B:8:0x0048, code skipped:
            if ((r1 - r4) < 8192) goto L_0x004b;
     */
    /* JADX WARNING: Missing block: B:9:0x004a, code skipped:
            return false;
     */
    public boolean a(defpackage.ha r11) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r10 = this;
        r0 = new com.google.android.exoplayer2.util.n;
        r1 = 10;
        r0.<init>(r1);
        r2 = new com.google.android.exoplayer2.util.m;
        r3 = r0.a;
        r2.<init>(r3);
        r3 = 0;
        r4 = 0;
    L_0x0010:
        r5 = r0.a;
        r11.c(r5, r3, r1);
        r0.c(r3);
        r5 = r0.k();
        r6 = b;
        if (r5 == r6) goto L_0x0074;
    L_0x0020:
        r11.a();
        r11.c(r4);
        r1 = r4;
    L_0x0027:
        r5 = 0;
        r6 = 0;
    L_0x0029:
        r7 = r0.a;
        r8 = 2;
        r11.c(r7, r3, r8);
        r0.c(r3);
        r7 = r0.h();
        r8 = 65526; // 0xfff6 float:9.1821E-41 double:3.2374E-319;
        r7 = r7 & r8;
        r8 = 65520; // 0xfff0 float:9.1813E-41 double:3.2371E-319;
        if (r7 == r8) goto L_0x004f;
    L_0x003f:
        r11.a();
        r1 = r1 + 1;
        r5 = r1 - r4;
        r6 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        if (r5 < r6) goto L_0x004b;
    L_0x004a:
        return r3;
    L_0x004b:
        r11.c(r1);
        goto L_0x0027;
    L_0x004f:
        r7 = 1;
        r5 = r5 + r7;
        r8 = 4;
        if (r5 < r8) goto L_0x0059;
    L_0x0054:
        r9 = 188; // 0xbc float:2.63E-43 double:9.3E-322;
        if (r6 <= r9) goto L_0x0059;
    L_0x0058:
        return r7;
    L_0x0059:
        r7 = r0.a;
        r11.c(r7, r3, r8);
        r7 = 14;
        r2.a(r7);
        r7 = 13;
        r7 = r2.c(r7);
        r8 = 6;
        if (r7 > r8) goto L_0x006d;
    L_0x006c:
        return r3;
    L_0x006d:
        r8 = r7 + -6;
        r11.c(r8);
        r6 = r6 + r7;
        goto L_0x0029;
    L_0x0074:
        r5 = 3;
        r0.d(r5);
        r5 = r0.t();
        r6 = r5 + 10;
        r4 = r4 + r6;
        r11.c(r5);
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ij.a(ha):boolean");
    }

    public void a(hb hbVar) {
        this.d.a(hbVar, new d(0, 1));
        hbVar.a();
        hbVar.a(new b(-9223372036854775807L));
    }

    public void a(long j, long j2) {
        this.f = false;
        this.d.a();
    }

    public int a(ha haVar, hg hgVar) throws IOException, InterruptedException {
        int a = haVar.a(this.e.a, 0, 200);
        if (a == -1) {
            return -1;
        }
        this.e.c(0);
        this.e.b(a);
        if (!this.f) {
            this.d.a(this.c, true);
            this.f = true;
        }
        this.d.a(this.e);
        return 0;
    }
}

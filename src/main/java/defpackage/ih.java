package defpackage;

import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import defpackage.hh.b;
import defpackage.jd.d;
import java.io.IOException;

/* compiled from: Ac3Extractor */
/* renamed from: ih */
public final class ih implements gz {
    public static final hc a = new 1();
    private static final int b = z.g("ID3");
    private final long c;
    private final ii d;
    private final n e;
    private boolean f;

    /* compiled from: Ac3Extractor */
    /* renamed from: ih$1 */
    static class 1 implements hc {
        1() {
        }

        public gz[] a() {
            return new gz[]{new ih()};
        }
    }

    public void c() {
    }

    public ih() {
        this(0);
    }

    public ih(long j) {
        this.c = j;
        this.d = new ii();
        this.e = new n(2786);
    }

    /* JADX WARNING: Missing block: B:7:0x0032, code skipped:
            r8.a();
            r1 = r1 + 1;
     */
    /* JADX WARNING: Missing block: B:8:0x003b, code skipped:
            if ((r1 - r3) < 8192) goto L_0x003e;
     */
    /* JADX WARNING: Missing block: B:9:0x003d, code skipped:
            return false;
     */
    public boolean a(defpackage.ha r8) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r7 = this;
        r0 = new com.google.android.exoplayer2.util.n;
        r1 = 10;
        r0.<init>(r1);
        r2 = 0;
        r3 = 0;
    L_0x0009:
        r4 = r0.a;
        r8.c(r4, r2, r1);
        r0.c(r2);
        r4 = r0.k();
        r5 = b;
        if (r4 == r5) goto L_0x0058;
    L_0x0019:
        r8.a();
        r8.c(r3);
        r1 = r3;
    L_0x0020:
        r4 = 0;
    L_0x0021:
        r5 = r0.a;
        r6 = 5;
        r8.c(r5, r2, r6);
        r0.c(r2);
        r5 = r0.h();
        r6 = 2935; // 0xb77 float:4.113E-42 double:1.45E-320;
        if (r5 == r6) goto L_0x0042;
    L_0x0032:
        r8.a();
        r1 = r1 + 1;
        r4 = r1 - r3;
        r5 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        if (r4 < r5) goto L_0x003e;
    L_0x003d:
        return r2;
    L_0x003e:
        r8.c(r1);
        goto L_0x0020;
    L_0x0042:
        r5 = 1;
        r4 = r4 + r5;
        r6 = 4;
        if (r4 < r6) goto L_0x0048;
    L_0x0047:
        return r5;
    L_0x0048:
        r5 = r0.a;
        r5 = com.google.android.exoplayer2.audio.a.a(r5);
        r6 = -1;
        if (r5 != r6) goto L_0x0052;
    L_0x0051:
        return r2;
    L_0x0052:
        r5 = r5 + -5;
        r8.c(r5);
        goto L_0x0021;
    L_0x0058:
        r4 = 3;
        r0.d(r4);
        r4 = r0.t();
        r5 = r4 + 10;
        r3 = r3 + r5;
        r8.c(r4);
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ih.a(ha):boolean");
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
        int a = haVar.a(this.e.a, 0, 2786);
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

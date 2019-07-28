package defpackage;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.l;
import com.google.android.exoplayer2.util.l.a;
import com.google.android.exoplayer2.util.l.b;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.o;
import defpackage.jd.d;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: H264Reader */
/* renamed from: iq */
public final class iq implements io {
    private final ja a;
    private final boolean b;
    private final boolean c;
    private final iv d = new iv(7, 128);
    private final iv e = new iv(8, 128);
    private final iv f = new iv(6, 128);
    private long g;
    private final boolean[] h = new boolean[3];
    private String i;
    private hj j;
    private a k;
    private boolean l;
    private long m;
    private final n n = new n();

    /* compiled from: H264Reader */
    /* renamed from: iq$a */
    private static final class a {
        private final hj a;
        private final boolean b;
        private final boolean c;
        private final SparseArray<b> d = new SparseArray();
        private final SparseArray<com.google.android.exoplayer2.util.l.a> e = new SparseArray();
        private final o f = new o(this.g, 0, 0);
        private byte[] g = new byte[128];
        private int h;
        private int i;
        private long j;
        private boolean k;
        private long l;
        private a m = new a();
        private a n = new a();
        private boolean o;
        private long p;
        private long q;
        private boolean r;

        /* compiled from: H264Reader */
        /* renamed from: iq$a$a */
        private static final class a {
            private boolean a;
            private boolean b;
            private b c;
            private int d;
            private int e;
            private int f;
            private int g;
            private boolean h;
            private boolean i;
            private boolean j;
            private boolean k;
            private int l;
            private int m;
            private int n;
            private int o;
            private int p;

            private a() {
            }

            public void a() {
                this.b = false;
                this.a = false;
            }

            public void a(int i) {
                this.e = i;
                this.b = true;
            }

            public void a(b bVar, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4, int i5, int i6, int i7, int i8, int i9) {
                this.c = bVar;
                this.d = i;
                this.e = i2;
                this.f = i3;
                this.g = i4;
                this.h = z;
                this.i = z2;
                this.j = z3;
                this.k = z4;
                this.l = i5;
                this.m = i6;
                this.n = i7;
                this.o = i8;
                this.p = i9;
                this.a = true;
                this.b = true;
            }

            public boolean b() {
                return this.b && (this.e == 7 || this.e == 2);
            }

            private boolean a(a aVar) {
                if (this.a) {
                    if (!aVar.a || this.f != aVar.f || this.g != aVar.g || this.h != aVar.h) {
                        return true;
                    }
                    if (this.i && aVar.i && this.j != aVar.j) {
                        return true;
                    }
                    if (this.d != aVar.d && (this.d == 0 || aVar.d == 0)) {
                        return true;
                    }
                    if (this.c.h == 0 && aVar.c.h == 0 && (this.m != aVar.m || this.n != aVar.n)) {
                        return true;
                    }
                    if ((this.c.h == 1 && aVar.c.h == 1 && (this.o != aVar.o || this.p != aVar.p)) || this.k != aVar.k) {
                        return true;
                    }
                    if (this.k && aVar.k && this.l != aVar.l) {
                        return true;
                    }
                }
                return false;
            }
        }

        public a(hj hjVar, boolean z, boolean z2) {
            this.a = hjVar;
            this.b = z;
            this.c = z2;
            b();
        }

        public boolean a() {
            return this.c;
        }

        public void a(b bVar) {
            this.d.append(bVar.a, bVar);
        }

        public void a(com.google.android.exoplayer2.util.l.a aVar) {
            this.e.append(aVar.a, aVar);
        }

        public void b() {
            this.k = false;
            this.o = false;
            this.n.a();
        }

        public void a(long j, int i, long j2) {
            this.i = i;
            this.l = j2;
            this.j = j;
            if (!(this.b && this.i == 1)) {
                if (!this.c) {
                    return;
                }
                if (!(this.i == 5 || this.i == 1 || this.i == 2)) {
                    return;
                }
            }
            a aVar = this.m;
            this.m = this.n;
            this.n = aVar;
            this.n.a();
            this.h = 0;
            this.k = true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:52:0x0104  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x0102  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x0119  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x014f  */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x011f  */
        public void a(byte[] r22, int r23, int r24) {
            /*
            r21 = this;
            r0 = r21;
            r1 = r23;
            r2 = r0.k;
            if (r2 != 0) goto L_0x0009;
        L_0x0008:
            return;
        L_0x0009:
            r2 = r24 - r1;
            r3 = r0.g;
            r3 = r3.length;
            r4 = r0.h;
            r4 = r4 + r2;
            r5 = 2;
            if (r3 >= r4) goto L_0x0021;
        L_0x0014:
            r3 = r0.g;
            r4 = r0.h;
            r4 = r4 + r2;
            r4 = r4 * 2;
            r3 = java.util.Arrays.copyOf(r3, r4);
            r0.g = r3;
        L_0x0021:
            r3 = r0.g;
            r4 = r0.h;
            r6 = r22;
            java.lang.System.arraycopy(r6, r1, r3, r4, r2);
            r1 = r0.h;
            r1 = r1 + r2;
            r0.h = r1;
            r1 = r0.f;
            r2 = r0.g;
            r3 = r0.h;
            r4 = 0;
            r1.a(r2, r4, r3);
            r1 = r0.f;
            r2 = 8;
            r1 = r1.b(r2);
            if (r1 != 0) goto L_0x0044;
        L_0x0043:
            return;
        L_0x0044:
            r1 = r0.f;
            r1.a();
            r1 = r0.f;
            r8 = r1.c(r5);
            r1 = r0.f;
            r2 = 5;
            r1.a(r2);
            r1 = r0.f;
            r1 = r1.c();
            if (r1 != 0) goto L_0x005e;
        L_0x005d:
            return;
        L_0x005e:
            r1 = r0.f;
            r1.d();
            r1 = r0.f;
            r1 = r1.c();
            if (r1 != 0) goto L_0x006c;
        L_0x006b:
            return;
        L_0x006c:
            r1 = r0.f;
            r9 = r1.d();
            r1 = r0.c;
            if (r1 != 0) goto L_0x007e;
        L_0x0076:
            r0.k = r4;
            r1 = r0.n;
            r1.a(r9);
            return;
        L_0x007e:
            r1 = r0.f;
            r1 = r1.c();
            if (r1 != 0) goto L_0x0087;
        L_0x0086:
            return;
        L_0x0087:
            r1 = r0.f;
            r11 = r1.d();
            r1 = r0.e;
            r1 = r1.indexOfKey(r11);
            if (r1 >= 0) goto L_0x0098;
        L_0x0095:
            r0.k = r4;
            return;
        L_0x0098:
            r1 = r0.e;
            r1 = r1.get(r11);
            r1 = (com.google.android.exoplayer2.util.l.a) r1;
            r3 = r0.d;
            r6 = r1.b;
            r3 = r3.get(r6);
            r7 = r3;
            r7 = (com.google.android.exoplayer2.util.l.b) r7;
            r3 = r7.e;
            if (r3 == 0) goto L_0x00bd;
        L_0x00af:
            r3 = r0.f;
            r3 = r3.b(r5);
            if (r3 != 0) goto L_0x00b8;
        L_0x00b7:
            return;
        L_0x00b8:
            r3 = r0.f;
            r3.a(r5);
        L_0x00bd:
            r3 = r0.f;
            r5 = r7.g;
            r3 = r3.b(r5);
            if (r3 != 0) goto L_0x00c8;
        L_0x00c7:
            return;
        L_0x00c8:
            r3 = r0.f;
            r5 = r7.g;
            r10 = r3.c(r5);
            r3 = r7.f;
            r5 = 1;
            if (r3 != 0) goto L_0x00fb;
        L_0x00d5:
            r3 = r0.f;
            r3 = r3.b(r5);
            if (r3 != 0) goto L_0x00de;
        L_0x00dd:
            return;
        L_0x00de:
            r3 = r0.f;
            r3 = r3.b();
            if (r3 == 0) goto L_0x00f9;
        L_0x00e6:
            r6 = r0.f;
            r6 = r6.b(r5);
            if (r6 != 0) goto L_0x00ef;
        L_0x00ee:
            return;
        L_0x00ef:
            r6 = r0.f;
            r6 = r6.b();
            r12 = r3;
            r14 = r6;
            r13 = 1;
            goto L_0x00fe;
        L_0x00f9:
            r12 = r3;
            goto L_0x00fc;
        L_0x00fb:
            r12 = 0;
        L_0x00fc:
            r13 = 0;
            r14 = 0;
        L_0x00fe:
            r3 = r0.i;
            if (r3 != r2) goto L_0x0104;
        L_0x0102:
            r15 = 1;
            goto L_0x0105;
        L_0x0104:
            r15 = 0;
        L_0x0105:
            if (r15 == 0) goto L_0x0119;
        L_0x0107:
            r2 = r0.f;
            r2 = r2.c();
            if (r2 != 0) goto L_0x0110;
        L_0x010f:
            return;
        L_0x0110:
            r2 = r0.f;
            r2 = r2.d();
            r16 = r2;
            goto L_0x011b;
        L_0x0119:
            r16 = 0;
        L_0x011b:
            r2 = r7.h;
            if (r2 != 0) goto L_0x014f;
        L_0x011f:
            r2 = r0.f;
            r3 = r7.i;
            r2 = r2.b(r3);
            if (r2 != 0) goto L_0x012a;
        L_0x0129:
            return;
        L_0x012a:
            r2 = r0.f;
            r3 = r7.i;
            r2 = r2.c(r3);
            r1 = r1.c;
            if (r1 == 0) goto L_0x014c;
        L_0x0136:
            if (r12 != 0) goto L_0x014c;
        L_0x0138:
            r1 = r0.f;
            r1 = r1.c();
            if (r1 != 0) goto L_0x0141;
        L_0x0140:
            return;
        L_0x0141:
            r1 = r0.f;
            r1 = r1.e();
            r18 = r1;
            r17 = r2;
            goto L_0x018f;
        L_0x014c:
            r17 = r2;
            goto L_0x018d;
        L_0x014f:
            r2 = r7.h;
            if (r2 != r5) goto L_0x018b;
        L_0x0153:
            r2 = r7.j;
            if (r2 != 0) goto L_0x018b;
        L_0x0157:
            r2 = r0.f;
            r2 = r2.c();
            if (r2 != 0) goto L_0x0160;
        L_0x015f:
            return;
        L_0x0160:
            r2 = r0.f;
            r2 = r2.e();
            r1 = r1.c;
            if (r1 == 0) goto L_0x0184;
        L_0x016a:
            if (r12 != 0) goto L_0x0184;
        L_0x016c:
            r1 = r0.f;
            r1 = r1.c();
            if (r1 != 0) goto L_0x0175;
        L_0x0174:
            return;
        L_0x0175:
            r1 = r0.f;
            r1 = r1.e();
            r20 = r1;
            r19 = r2;
            r17 = 0;
            r18 = 0;
            goto L_0x0193;
        L_0x0184:
            r19 = r2;
            r17 = 0;
            r18 = 0;
            goto L_0x0191;
        L_0x018b:
            r17 = 0;
        L_0x018d:
            r18 = 0;
        L_0x018f:
            r19 = 0;
        L_0x0191:
            r20 = 0;
        L_0x0193:
            r6 = r0.n;
            r6.a(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
            r0.k = r4;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.iq$a.a(byte[], int, int):void");
        }

        public void a(long j, int i) {
            int i2 = 0;
            if (this.i == 9 || (this.c && this.n.a(this.m))) {
                if (this.o) {
                    a(i + ((int) (j - this.j)));
                }
                this.p = this.j;
                this.q = this.l;
                this.r = false;
                this.o = true;
            }
            boolean z = this.r;
            if (this.i == 5 || (this.b && this.i == 1 && this.n.b())) {
                i2 = 1;
            }
            this.r = z | i2;
        }

        private void a(int i) {
            this.a.a(this.q, this.r, (int) (this.j - this.p), i, null);
        }
    }

    public void b() {
    }

    public iq(ja jaVar, boolean z, boolean z2) {
        this.a = jaVar;
        this.b = z;
        this.c = z2;
    }

    public void a() {
        l.a(this.h);
        this.d.a();
        this.e.a();
        this.f.a();
        this.k.b();
        this.g = 0;
    }

    public void a(hb hbVar, d dVar) {
        dVar.a();
        this.i = dVar.c();
        this.j = hbVar.a(dVar.b(), 2);
        this.k = new a(this.j, this.b, this.c);
        this.a.a(hbVar, dVar);
    }

    public void a(long j, boolean z) {
        this.m = j;
    }

    public void a(n nVar) {
        int d = nVar.d();
        int c = nVar.c();
        byte[] bArr = nVar.a;
        this.g += (long) nVar.b();
        this.j.a(nVar, nVar.b());
        while (true) {
            int a = l.a(bArr, d, c, this.h);
            if (a == c) {
                a(bArr, d, c);
                return;
            }
            int b = l.b(bArr, a);
            int i = a - d;
            if (i > 0) {
                a(bArr, d, a);
            }
            int i2 = c - a;
            long j = this.g - ((long) i2);
            a(j, i2, i < 0 ? -i : 0, this.m);
            a(j, b, this.m);
            d = a + 3;
        }
    }

    private void a(long j, int i, long j2) {
        if (!this.l || this.k.a()) {
            this.d.a(i);
            this.e.a(i);
        }
        this.f.a(i);
        this.k.a(j, i, j2);
    }

    private void a(byte[] bArr, int i, int i2) {
        if (!this.l || this.k.a()) {
            this.d.a(bArr, i, i2);
            this.e.a(bArr, i, i2);
        }
        this.f.a(bArr, i, i2);
        this.k.a(bArr, i, i2);
    }

    private void a(long j, int i, int i2, long j2) {
        int i3 = i2;
        if (!this.l || this.k.a()) {
            this.d.b(i3);
            this.e.b(i3);
            if (this.l) {
                if (this.d.b()) {
                    this.k.a(l.a(this.d.a, 3, this.d.b));
                    this.d.a();
                } else if (this.e.b()) {
                    this.k.a(l.b(this.e.a, 3, this.e.b));
                    this.e.a();
                }
            } else if (this.d.b() && this.e.b()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(Arrays.copyOf(this.d.a, this.d.b));
                arrayList.add(Arrays.copyOf(this.e.a, this.e.b));
                b a = l.a(this.d.a, 3, this.d.b);
                a b = l.b(this.e.a, 3, this.e.b);
                this.j.a(Format.a(this.i, "video/avc", null, -1, -1, a.b, a.c, -1.0f, arrayList, -1, a.d, null));
                this.l = true;
                this.k.a(a);
                this.k.a(b);
                this.d.a();
                this.e.a();
            }
        }
        if (this.f.b(i2)) {
            this.n.a(this.f.a, l.a(this.f.a, this.f.b));
            this.n.c(4);
            this.a.a(j2, this.n);
        }
        this.k.a(j, i);
    }
}

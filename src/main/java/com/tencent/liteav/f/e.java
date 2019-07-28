package com.tencent.liteav.f;

import android.content.Context;
import com.tencent.liteav.c.d;
import com.tencent.liteav.c.g;
import com.tencent.liteav.d.f;
import com.tencent.liteav.k.n;
import com.tencent.liteav.k.n.a;
import com.tencent.liteav.k.n.b;
import com.tencent.liteav.k.n.h;
import com.tencent.liteav.k.n.i;
import com.tencent.liteav.k.n.j;
import com.tencent.liteav.k.n.k;
import com.tencent.liteav.k.n.l;
import com.tencent.liteav.k.n.m;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

/* compiled from: MotionFilterChain */
public class e {
    private final int A = 274000;
    private final int B = 318000;
    private final int C = 362000;
    private final int D = 406000;
    private final int E = 450000;
    private final int F = 494000;
    private final int G = 538000;
    private final int H = 582000;
    private final int I = 560000;
    private final int J = 1120000;
    private final int K = 1000000;
    private final int L = 120000;
    private final int M = 70000;
    private final int N = 300000;
    private final int O = 350000;
    private final int P = 400000;
    private final int Q = 500000;
    private final int R = 600000;
    private final int S = 650000;
    private final int T = 700000;
    private final int U = 800000;
    private final int V = 900000;
    private final int W = 1000000;
    private final int X = 1050000;
    private final int Y = 1100000;
    private final int Z = 1200000;
    private g a;
    private final int aA = 50000;
    private final int aB = 150000;
    private final int aC = 250000;
    private final int aD = 300000;
    private final int aE = 400000;
    private final int aF = 580000;
    private final int aG = 1000000;
    private final int aH = 2000000;
    private final int aa = 1500000;
    private final int ab = 2500000;
    private final int ac = 120000;
    private final int ad = 240000;
    private final int ae = 360000;
    private final int af = 480000;
    private final int ag = 600000;
    private final int ah = 720000;
    private final int ai = 840000;
    private final int aj = 960000;
    private final int ak = 1080000;
    private final int al = 1200000;
    private final int am = 1320000;
    private final int an = 1440000;
    private final int ao = 1560000;
    private final int ap = 1680000;
    private final int aq = 1800000;
    private final int ar = 100000;
    private final int as = 200000;
    private final int at = 300000;
    private final int au = 400000;
    private final int av = 500000;
    private final int aw = 600000;
    private final int ax = 700000;
    private final int ay = 800000;
    private final int az = 850000;
    private d b = d.a();
    private n c;
    private f d;
    private boolean e;
    private long f = -1;
    private long g = -1;
    private long h = -1;
    private long i = -1;
    private long j = -1;
    private long k = -1;
    private long l = -1;
    private long m = -1;
    private l n;
    private m o;
    private a p;
    private n.d q;
    private i r;
    private n.f s;
    private k t;
    private com.tencent.liteav.k.n.e u;
    private h v;
    private n.g w;
    private j x;
    private final int y = 120000;
    private final int z = 230000;

    public e(Context context) {
        this.c = new n(context);
        this.a = g.a();
    }

    /* JADX WARNING: Missing block: B:12:0x0025, code skipped:
            return;
     */
    public void a(com.tencent.liteav.d.e r4) {
        /*
        r3 = this;
        r0 = r4.e();
        r4 = 0;
        r3.d = r4;
        r4 = r3.b();
        if (r4 != 0) goto L_0x000e;
    L_0x000d:
        return;
    L_0x000e:
        r4 = r3.a(r0);
        r2 = -1;
        if (r4 == r2) goto L_0x0025;
    L_0x0015:
        r2 = r3.d;
        if (r2 != 0) goto L_0x001a;
    L_0x0019:
        goto L_0x0025;
    L_0x001a:
        r2 = r3.b(r0);
        if (r2 != 0) goto L_0x0021;
    L_0x0020:
        return;
    L_0x0021:
        r3.a(r4, r0);
        return;
    L_0x0025:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.f.e.a(com.tencent.liteav.d.e):void");
    }

    public int a(com.tencent.liteav.d.e eVar, int i) {
        if (this.d == null) {
            return i;
        }
        b bVar = new b();
        bVar.a = i;
        if (eVar.h() == 90 || eVar.h() == 270) {
            bVar.b = eVar.n();
            bVar.c = eVar.m();
        } else {
            bVar.b = eVar.m();
            bVar.c = eVar.n();
        }
        switch (this.d.a) {
            case 0:
                this.c.a(0, this.p);
                break;
            case 1:
                this.c.a(1, this.q);
                break;
            case 2:
                this.c.a(2, this.n);
                break;
            case 3:
                this.c.a(3, this.o);
                break;
            case 4:
                this.c.a(4, this.r);
                break;
            case 5:
                this.c.a(5, this.s);
                break;
            case 6:
                this.c.a(6, this.t);
                break;
            case 7:
                this.c.a(7, this.u);
                break;
            case 8:
                this.c.a(8, this.v);
                break;
            case 10:
                this.c.a(10, this.w);
                break;
            case 11:
                this.c.a(11, this.x);
                break;
        }
        return this.c.a(bVar);
    }

    private boolean b() {
        List d = this.b.d();
        return (d == null || d.size() == 0) ? false : true;
    }

    private int a(long j) {
        List d = this.b.d();
        int i = -1;
        if (d == null || d.size() == 0) {
            return -1;
        }
        for (int size = d.size() - 1; size >= 0; size--) {
            f fVar = (f) d.get(size);
            if (j >= fVar.b && j <= fVar.c) {
                i = fVar.a;
                this.d = fVar;
                break;
            }
        }
        f b = this.b.b();
        if (b.c == -1 || b.c == b.b) {
            i = b.a;
            this.d = b;
        }
        return i;
    }

    private boolean b(long j) {
        long j2 = this.d.b;
        long j3 = this.d.c;
        boolean z = j2 != -1 && j3 != -1 && j > j2 && j < j3;
        if (j2 != -1 && j > j2) {
            z = true;
        }
        return (j3 == -1 || j >= j3) ? z : true;
    }

    private void a(int i, long j) {
        switch (i) {
            case 0:
                if (this.p == null) {
                    this.p = new a();
                }
                c();
                return;
            case 1:
                j(j);
                return;
            case 2:
                l(j);
                return;
            case 3:
                k(j);
                return;
            case 4:
                i(j);
                return;
            case 5:
                h(j);
                return;
            case 6:
                g(j);
                return;
            case 7:
                f(j);
                return;
            case 8:
                e(j);
                return;
            case 10:
                d(j);
                return;
            case 11:
                c(j);
                return;
            default:
                return;
        }
    }

    private void c(long j) {
        if (this.x == null) {
            this.x = new j();
        }
        if (this.m == -1) {
            this.m = j;
        }
        j = Math.abs(j - this.m);
        if (j < 1000000) {
            this.x.a = CropImageView.DEFAULT_ASPECT_RATIO;
        } else if (j < 2000000) {
            this.x.a = 1.0f;
        } else {
            this.m = -1;
        }
    }

    private void d(long j) {
        if (this.w == null) {
            this.w = new n.g();
        }
    }

    private void e(long j) {
        if (this.l == -1) {
            this.l = j;
        }
        if (this.v == null) {
            this.v = new h();
            this.v.a = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        j = Math.abs(j - this.l);
        if (j < 50000) {
            this.v.a = 0.7f;
        } else if (j < 150000) {
            this.v.a = 0.5f;
        } else if (j < 250000) {
            this.v.a = 0.4f;
        } else if (j < 300000) {
            this.v.a = 1.0f;
        } else if (j < 400000) {
            this.v.a = 0.3f;
        } else if (j < 580000) {
            this.v.a = CropImageView.DEFAULT_ASPECT_RATIO;
        } else {
            this.l = -1;
        }
    }

    private void f(long j) {
        if (this.k == -1) {
            this.k = j;
        }
        if (this.u == null) {
            this.u = new com.tencent.liteav.k.n.e();
            this.u.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.u.a = CropImageView.DEFAULT_ASPECT_RATIO;
            this.u.c = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        j = Math.abs(j - this.k);
        if (j < 100000) {
            this.u.b = 10.0f;
            this.u.a = 0.01f;
            this.u.c = CropImageView.DEFAULT_ASPECT_RATIO;
        } else if (j < 200000) {
            this.u.b = 20.0f;
            this.u.a = -0.02f;
            this.u.c = CropImageView.DEFAULT_ASPECT_RATIO;
        } else if (j < 300000) {
            this.u.b = 30.0f;
            this.u.a = 0.02f;
            this.u.c = CropImageView.DEFAULT_ASPECT_RATIO;
        } else if (j < 400000) {
            this.u.b = 20.0f;
            this.u.a = -0.03f;
            this.u.c = CropImageView.DEFAULT_ASPECT_RATIO;
        } else if (j < 500000) {
            this.u.b = 10.0f;
            this.u.a = 0.01f;
            this.u.c = CropImageView.DEFAULT_ASPECT_RATIO;
        } else if (j < 600000) {
            this.u.b = 20.0f;
            this.u.a = -0.02f;
            this.u.c = CropImageView.DEFAULT_ASPECT_RATIO;
        } else if (j < 700000) {
            this.u.b = 30.0f;
            this.u.a = -0.03f;
            this.u.c = CropImageView.DEFAULT_ASPECT_RATIO;
        } else if (j < 800000) {
            this.u.b = 20.0f;
            this.u.a = 0.02f;
            this.u.c = CropImageView.DEFAULT_ASPECT_RATIO;
        } else if (j < 850000) {
            this.u.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.u.a = CropImageView.DEFAULT_ASPECT_RATIO;
            this.u.c = CropImageView.DEFAULT_ASPECT_RATIO;
        } else {
            this.k = -1;
        }
    }

    private void g(long j) {
        if (this.j == -1) {
            this.j = j;
        }
        if (this.t == null) {
            this.t = new k();
            this.t.f = 1;
            this.t.h = 0.3f;
        }
        this.t.a = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO};
        this.t.b = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.1f};
        j = Math.abs(j - this.j);
        if (j < 120000) {
            this.t.g = 0;
            this.t.a = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO};
            this.t.b = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO};
        } else if (j < 120000) {
            this.t.g = 1;
        } else if (j < 240000) {
            this.t.g = 2;
        } else if (j < 360000) {
            this.t.g = 3;
        } else if (j < 480000) {
            this.t.g = 4;
        } else if (j < 600000) {
            this.t.g = 5;
        } else if (j < 720000) {
            this.t.g = 6;
        } else if (j < 840000) {
            this.t.g = 7;
        } else if (j < 960000) {
            this.t.g = 8;
        } else if (j < 1080000) {
            this.t.g = 9;
        } else if (j < 1200000) {
            this.t.g = 10;
        } else if (j < 1320000) {
            this.t.g = 11;
        } else if (j < 1440000) {
            this.t.g = 12;
        } else if (j < 1560000) {
            this.t.g = 13;
        } else if (j < 1680000) {
            this.t.g = 14;
        } else if (j < 1800000) {
            this.t.g = 15;
        } else {
            this.j = -1;
        }
    }

    private void h(long j) {
        if (this.s == null) {
            this.s = new n.f();
        }
        this.s.a = 5;
        this.s.b = 1;
        this.s.c = 0.5f;
    }

    private void i(long j) {
        if (this.i == -1) {
            if (this.e) {
                this.i = this.d.b;
            } else {
                this.i = j;
            }
        }
        if (this.r == null) {
            this.r = new i();
        }
        j = Math.abs(j - this.i);
        if (j < 300000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.003f;
        } else if (j < 350000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.015f;
        } else if (j < 400000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.024f;
        } else if (j < 500000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.015f;
        } else if (j < 600000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.003f;
        } else if (j < 650000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.03f;
        } else if (j < 700000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.015f;
        } else if (j < 800000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.024f;
        } else if (j < 900000) {
            this.r.b = 1.0f;
        } else if (j < 1000000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.015f;
        } else if (j < 1050000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.024f;
        } else if (j < 1100000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.015f;
        } else if (j < 1200000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.009f;
        } else if (j < 1500000) {
            this.r.b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.r.e = 0.03f;
            this.r.d = 0.003f;
        } else if (j < 2500000) {
            this.r.b = 1.0f;
        } else {
            this.i = -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00da  */
    private void j(long r10) {
        /*
        r9 = this;
        r0 = r9.h;
        r2 = -1;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 != 0) goto L_0x0015;
    L_0x0008:
        r0 = r9.e;
        if (r0 == 0) goto L_0x0013;
    L_0x000c:
        r0 = r9.d;
        r0 = r0.b;
        r9.h = r0;
        goto L_0x0015;
    L_0x0013:
        r9.h = r10;
    L_0x0015:
        r0 = r9.q;
        r1 = 1090519040; // 0x41000000 float:8.0 double:5.38787994E-315;
        r4 = 0;
        r5 = 2;
        if (r0 != 0) goto L_0x003c;
    L_0x001d:
        r0 = new com.tencent.liteav.k.n$d;
        r0.<init>();
        r9.q = r0;
        r0 = r9.q;
        r0.e = r1;
        r0 = r9.q;
        r6 = new float[r5];
        r6 = {1056964608, 1056964608};
        r0.c = r6;
        r0 = r9.q;
        r0.a = r4;
        r0 = r9.q;
        r6 = 1045220557; // 0x3e4ccccd float:0.2 double:5.164075695E-315;
        r0.b = r6;
    L_0x003c:
        r6 = r9.h;
        r10 = r10 - r6;
        r10 = java.lang.Math.abs(r10);
        r6 = 120000; // 0x1d4c0 float:1.68156E-40 double:5.9288E-319;
        r0 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1));
        if (r0 >= 0) goto L_0x0077;
    L_0x004a:
        r10 = r9.q;
        r10.d = r4;
        r10 = r9.q;
        r10.e = r1;
        r10 = r9.q;
        r11 = new float[r5];
        r11 = {0, 0};
        r10.c = r11;
        r10 = r9.q;
        r10.a = r4;
        r10 = r9.q;
        r10.b = r4;
        r10 = r9.q;
        r11 = new float[r5];
        r11 = {0, 0};
        r10.f = r11;
        r10 = r9.q;
        r11 = new float[r5];
        r11 = {0, 0};
        r10.g = r11;
        goto L_0x010f;
    L_0x0077:
        r0 = 1;
    L_0x0078:
        r6 = 8;
        if (r0 > r6) goto L_0x00d3;
    L_0x007c:
        r6 = 120000; // 0x1d4c0 float:1.68156E-40 double:5.9288E-319;
        r7 = 70000; // 0x11170 float:9.8091E-41 double:3.45846E-319;
        r7 = r7 * r0;
        r7 = r7 + r6;
        r6 = (long) r7;
        r8 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1));
        if (r8 >= 0) goto L_0x00d0;
    L_0x008a:
        r6 = r9.q;
        r7 = (float) r0;
        r6.d = r7;
        r6 = r9.q;
        r6.e = r1;
        r6 = r9.q;
        r7 = new float[r5];
        r7 = {1056964608, 1056964608};
        r6.c = r7;
        r6 = r9.q;
        r6.a = r4;
        r6 = r9.q;
        r7 = 1050253722; // 0x3e99999a float:0.3 double:5.188942835E-315;
        r6.b = r7;
        r6 = 3;
        if (r0 < r6) goto L_0x00bd;
    L_0x00aa:
        r0 = r9.q;
        r6 = new float[r5];
        r6 = {-1110651699, 0};
        r0.f = r6;
        r0 = r9.q;
        r6 = new float[r5];
        r6 = {0, 1036831949};
        r0.g = r6;
        goto L_0x00d3;
    L_0x00bd:
        r0 = r9.q;
        r6 = new float[r5];
        r6 = {0, 0};
        r0.f = r6;
        r0 = r9.q;
        r6 = new float[r5];
        r6 = {0, 0};
        r0.g = r6;
        goto L_0x00d3;
    L_0x00d0:
        r0 = r0 + 1;
        goto L_0x0078;
    L_0x00d3:
        r6 = 680000; // 0xa6040 float:9.52883E-40 double:3.359646E-318;
        r0 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x010f;
    L_0x00da:
        r6 = 1080000; // 0x107ac0 float:1.513402E-39 double:5.33591E-318;
        r0 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1));
        if (r0 > 0) goto L_0x010d;
    L_0x00e1:
        r10 = r9.q;
        r10.d = r4;
        r10 = r9.q;
        r10.e = r1;
        r10 = r9.q;
        r11 = new float[r5];
        r11 = {0, 0};
        r10.c = r11;
        r10 = r9.q;
        r10.a = r4;
        r10 = r9.q;
        r10.b = r4;
        r10 = r9.q;
        r11 = new float[r5];
        r11 = {0, 0};
        r10.f = r11;
        r10 = r9.q;
        r11 = new float[r5];
        r11 = {0, 0};
        r10.g = r11;
        goto L_0x010f;
    L_0x010d:
        r9.h = r2;
    L_0x010f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.f.e.j(long):void");
    }

    private void c() {
        if (this.p == null) {
            this.p = new a();
        }
    }

    private void k(long j) {
        if (this.g == -1) {
            if (this.e) {
                this.g = this.d.b;
            } else {
                this.g = j;
            }
        }
        if (this.o == null) {
            this.o = new m();
        }
        j = Math.abs(j - this.g);
        if (j <= 1000000) {
            this.o.a = 4;
        } else if (j <= 2000000) {
            this.o.a = 9;
        } else {
            this.g = -1;
        }
    }

    private void l(long j) {
        if (this.f == -1) {
            if (this.e) {
                this.f = this.d.b;
            } else {
                this.f = j;
            }
        }
        if (this.n == null) {
            this.n = new l();
            this.n.f = 1;
            this.n.h = 0.3f;
        }
        j = Math.abs(j - this.f);
        if (j < 120000) {
            this.n.g = 0;
        } else if (j < 230000) {
            this.n.g = 1;
        } else if (j < 274000) {
            this.n.g = 2;
        } else if (j < 318000) {
            this.n.g = 3;
        } else if (j < 362000) {
            this.n.g = 4;
        } else if (j < 406000) {
            this.n.g = 5;
        } else if (j < 450000) {
            this.n.g = 6;
        } else if (j < 494000) {
            this.n.g = 7;
        } else if (j < 538000) {
            this.n.g = 8;
        } else if (j < 582000) {
            this.n.g = 9;
        } else if (j < 1120000) {
            this.n.g = 0;
        } else {
            this.f = -1;
        }
    }

    public void a() {
        if (this.c != null) {
            this.c.a();
        }
    }
}

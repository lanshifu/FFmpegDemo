package com.tencent.liteav.f;

import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.g;
import com.tencent.liteav.d.e;
import com.tencent.liteav.d.i;
import com.tencent.liteav.e.t;
import com.yalantis.ucrop.view.CropImageView;
import java.nio.ByteBuffer;
import java.util.List;

/* compiled from: TailWaterMarkChain */
public class j {
    private static j c;
    public e a;
    public e b;
    private i d;
    private float e;
    private List<com.tencent.liteav.i.a.j> f;
    private t g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;

    public static j a() {
        if (c == null) {
            c = new j();
        }
        return c;
    }

    private j() {
        i();
    }

    public void a(i iVar) {
        this.d = iVar;
    }

    public boolean b() {
        return this.d != null;
    }

    public void a(t tVar) {
        this.g = tVar;
    }

    public long c() {
        return (long) ((this.d.a() * 1000) * 1000);
    }

    public void d() {
        this.o = com.tencent.liteav.c.i.a().l();
        if (this.d != null && this.a != null) {
            int a = this.d.a();
            if (a != 0) {
                this.k = this.a.i() * a;
                int i = 0;
                this.l = 0;
                this.e = CropImageView.DEFAULT_ASPECT_RATIO;
                e();
                if (this.o) {
                    if (this.b != null) {
                        this.i = (this.b.g() * 1000) / ((this.b.k() * 2) * this.b.j());
                        this.h = (a * 1000) / this.i;
                        this.j = 0;
                        while (i < this.h) {
                            g();
                            i++;
                        }
                    } else {
                        return;
                    }
                }
                f();
            }
        }
    }

    /* JADX WARNING: Missing block: B:16:0x006c, code skipped:
            return;
     */
    public void e() {
        /*
        r14 = this;
        r0 = r14.d;
        r0 = r0.c();
        r1 = r14.d;
        r1 = r1.d();
        r2 = r14.d;
        r2 = r2.a();
        if (r0 == 0) goto L_0x006c;
    L_0x0014:
        r3 = r0.isRecycled();
        if (r3 == 0) goto L_0x001b;
    L_0x001a:
        goto L_0x006c;
    L_0x001b:
        if (r1 != 0) goto L_0x001e;
    L_0x001d:
        return;
    L_0x001e:
        if (r2 != 0) goto L_0x0021;
    L_0x0020:
        return;
    L_0x0021:
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = r14.a;
        r4 = r4.i();
        r2 = r2 * r4;
        r4 = r14.a;
        r4 = com.tencent.liteav.j.e.a(r4);
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r4 / r6;
        r6 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r7 = r6 / r2;
        r8 = 100;
        r9 = 0;
    L_0x003e:
        if (r9 >= r2) goto L_0x0069;
    L_0x0040:
        r8 = r8 + r7;
        if (r8 < r6) goto L_0x0045;
    L_0x0043:
        r8 = 255; // 0xff float:3.57E-43 double:1.26E-321;
    L_0x0045:
        r10 = com.tencent.liteav.j.a.a(r0, r8);
        r11 = new com.tencent.liteav.i.a$j;
        r11.<init>();
        r11.b = r1;
        r11.a = r10;
        r11.c = r4;
        r10 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r12 = r14.a;
        r12 = r12.i();
        r10 = r10 / r12;
        r12 = (long) r10;
        r4 = r4 + r12;
        r11.d = r4;
        r3.add(r11);
        r4 = r11.d;
        r9 = r9 + 1;
        goto L_0x003e;
    L_0x0069:
        r14.f = r3;
        return;
    L_0x006c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.f.j.e():void");
    }

    public void f() {
        if (!this.n) {
            if (this.l >= this.k - 1) {
                this.n = true;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("===insertTailVideoFrame===mEndAudio:");
                stringBuilder.append(this.m);
                stringBuilder.append(",mHasAudioTrack:");
                stringBuilder.append(this.o);
                TXCLog.d("TailWaterMarkChain", stringBuilder.toString());
                if (this.o) {
                    if (this.m) {
                        k();
                    }
                    return;
                }
                l();
                return;
            }
            long u;
            e eVar = new e(this.a.a(), this.a.b(), this.a.o());
            eVar.a(this.a.c());
            eVar.b(this.a.d());
            eVar.e(this.a.h());
            eVar.f(this.a.i());
            eVar.g(this.a.j());
            if (eVar.h() == 90 || eVar.h() == 270) {
                eVar.j(this.a.n());
                eVar.k(this.a.m());
            } else {
                eVar.j(this.a.m());
                eVar.k(this.a.n());
            }
            if (g.a().b()) {
                u = this.a.u() + ((long) ((((this.l + 1) * 1000) / this.a.i()) * 1000));
            } else {
                u = this.a.t() + ((long) ((((this.l + 1) * 1000) / this.a.i()) * 1000));
            }
            eVar.a(u);
            eVar.b(u);
            eVar.c(u);
            eVar.a(true);
            this.e += 10.0f / ((float) this.k);
            eVar.a(this.e);
            eVar.c(this.a.f());
            eVar.m(this.a.y());
            eVar.a(this.a.w());
            this.l++;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("===insertTailVideoFrame===mVideoIndex:");
            stringBuilder2.append(this.l);
            stringBuilder2.append(",time:");
            stringBuilder2.append(eVar.t());
            TXCLog.d("TailWaterMarkChain", stringBuilder2.toString());
            if (this.g != null) {
                this.g.b(eVar);
            }
        }
    }

    public void g() {
        if (!this.m) {
            if (this.j >= this.h - 1) {
                this.m = true;
                if (this.n) {
                    k();
                }
                return;
            }
            this.b.a(ByteBuffer.allocate(this.b.g()));
            e eVar = new e(this.b.a(), this.b.b(), this.b.o());
            eVar.a(this.b.c());
            eVar.b(this.b.d());
            eVar.g(this.b.j());
            eVar.h(this.b.k());
            long e = this.b.e() + ((long) ((this.i * (this.j + 1)) * 1000));
            eVar.a(e);
            eVar.b(e);
            eVar.c(e);
            eVar.a(true);
            eVar.c(this.b.f());
            this.j++;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("===insertTailAudioFrame===mAudioIndex:");
            stringBuilder.append(this.j);
            stringBuilder.append(",time:");
            stringBuilder.append(eVar.e());
            TXCLog.d("TailWaterMarkChain", stringBuilder.toString());
            if (this.g != null) {
                this.g.a(eVar);
            }
        }
    }

    private void k() {
        TXCLog.d("TailWaterMarkChain", "===insertAudioTailFrame===");
        this.b.a(ByteBuffer.allocate(this.b.g()));
        e eVar = new e(this.b.a(), this.b.b(), this.b.o());
        eVar.a(this.b.c());
        eVar.b(this.b.d());
        eVar.g(this.b.j());
        eVar.h(this.b.k());
        long e = this.b.e() + ((long) ((this.i * (this.j + 1)) * 1000));
        eVar.a(e);
        eVar.b(e);
        eVar.c(e);
        eVar.a(true);
        eVar.c(4);
        this.j++;
        if (this.g != null) {
            this.g.a(eVar);
        }
    }

    private void l() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("===insertVideoTailVFrame===, lastVideoFrame = ");
        stringBuilder.append(this.a);
        TXCLog.d("TailWaterMarkChain", stringBuilder.toString());
        if (this.a != null) {
            long u;
            e eVar = new e(this.a.a(), this.a.b(), this.a.o());
            eVar.a(this.a.c());
            eVar.b(this.a.d());
            eVar.e(this.a.h());
            eVar.f(this.a.i());
            eVar.g(this.a.j());
            if (eVar.h() == 90 || eVar.h() == 270) {
                eVar.j(this.a.n());
                eVar.k(this.a.m());
            } else {
                eVar.j(this.a.m());
                eVar.k(this.a.n());
            }
            if (g.a().b()) {
                u = this.a.u() + ((long) ((((this.l + 1) * 1000) / this.a.i()) * 1000));
            } else {
                u = this.a.t() + ((long) ((((this.l + 1) * 1000) / this.a.i()) * 1000));
            }
            eVar.a(u);
            eVar.b(u);
            eVar.c(u);
            eVar.a(true);
            eVar.c(4);
            eVar.m(this.a.y());
            this.e += 10.0f / ((float) this.k);
            eVar.a(this.e);
            this.l++;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("===insertVideoTailVFrame===mVideoIndex:");
            stringBuilder2.append(this.l);
            stringBuilder2.append(",time:");
            stringBuilder2.append(eVar.t());
            stringBuilder2.append(",flag:");
            stringBuilder2.append(eVar.f());
            TXCLog.d("TailWaterMarkChain", stringBuilder2.toString());
            if (this.g != null) {
                this.g.b(eVar);
            }
        }
    }

    public List<com.tencent.liteav.i.a.j> h() {
        return this.f;
    }

    public void i() {
        if (this.f != null) {
            for (com.tencent.liteav.i.a.j jVar : this.f) {
                if (!(jVar == null || jVar.a == null || jVar.a.isRecycled())) {
                    jVar.a.recycle();
                    jVar.a = null;
                }
            }
            this.f.clear();
        }
        this.f = null;
        if (this.d != null) {
            this.d.b();
        }
        this.d = null;
        this.a = null;
        this.b = null;
        this.e = CropImageView.DEFAULT_ASPECT_RATIO;
        this.j = 0;
        this.l = 0;
        this.h = 0;
        this.k = 0;
        this.m = false;
        this.n = false;
    }

    public boolean j() {
        if (!this.o) {
            return this.n;
        }
        boolean z = this.n && this.m;
        return z;
    }
}

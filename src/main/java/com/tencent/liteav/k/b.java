package com.tencent.liteav.k;

import android.opengl.GLES20;
import android.util.Log;
import com.tencent.liteav.basic.e.g;
import com.tencent.liteav.basic.e.i;
import com.tencent.liteav.beauty.b.f;
import com.tencent.liteav.beauty.b.n;
import com.tencent.liteav.beauty.b.o;
import com.tencent.liteav.beauty.c;
import com.tencent.liteav.k.n.c.a;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: TXCGPUDiffuseFilter */
public class b {
    private static String j = "Diffuse";
    private c a = null;
    private o b = null;
    private m c = null;
    private m d = null;
    private n e = null;
    private g f = null;
    private f g = null;
    private int h = 0;
    private int i = 0;
    private int k = 1;
    private n.c l = null;
    private a m = a.MODE_NONE;
    private a n = a.MODE_NONE;
    private int[] o = null;
    private i.a p = null;
    private i.a q = null;
    private float r = CropImageView.DEFAULT_ASPECT_RATIO;

    public boolean a(int i, int i2) {
        this.h = i;
        this.i = i2;
        if (!(this.p != null && i == this.p.c && i2 == this.p.d)) {
            this.p = i.a(this.p);
            this.p = i.a(this.p, i, i2);
        }
        if (!(this.q != null && i == this.q.c && i2 == this.q.d)) {
            this.q = i.a(this.q);
            this.q = i.a(this.q, i, i2);
        }
        return c(i, i2);
    }

    private boolean c(int i, int i2) {
        if (this.a == null) {
            this.a = new c();
            if (!this.a.a(i, i2)) {
                Log.e(j, "mDissolveBlendFilter init Failed!");
                return false;
            }
        }
        if (this.a != null) {
            this.a.b(this.k);
            this.a.b(i, i2);
        }
        if (this.b == null) {
            this.b = new o();
            if (!this.b.a(i, i2)) {
                Log.e(j, "mGridShapeFilter init Failed!");
                return false;
            }
        }
        if (this.b != null) {
            this.b.b(i, i2);
        }
        if (this.c == null) {
            this.c = new m();
            this.c.a(true);
            if (!this.c.c()) {
                Log.e(j, "mScaleFilter init Failed!");
                return false;
            }
        }
        if (this.c != null) {
            this.c.a(i, i2);
        }
        if (this.d == null) {
            this.d = new m();
            this.d.a(true);
            if (!this.d.c()) {
                Log.e(j, "mScaleFilter2 init Failed!");
                return false;
            }
        }
        if (this.d != null) {
            this.d.a(i, i2);
        }
        if (this.e == null) {
            this.e = new n();
            this.e.a(true);
            if (!this.e.c()) {
                Log.e(j, "mGridShapeFilter init Failed!");
                return false;
            }
        }
        if (this.e != null) {
            this.e.a(i, i2);
        }
        if (this.f == null) {
            this.f = new g();
            if (!this.f.c()) {
                Log.e(j, "mDrawFilter init Failed!");
                return false;
            }
        }
        if (this.f != null) {
            this.f.a(i, i2);
        }
        if (this.g == null) {
            this.g = new f();
            if (!this.g.c()) {
                Log.e(j, "mColorBrushFilter init Failed!");
                return false;
            }
        }
        if (this.g != null) {
            this.g.a(i, i2);
        }
        return true;
    }

    public void b(int i, int i2) {
        if (i != this.h || i2 != this.i) {
            a(i, i2);
        }
    }

    private void b() {
        if (this.a != null) {
            this.a.b();
            this.a = null;
        }
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
        if (this.c != null) {
            this.c.e();
            this.c = null;
        }
        if (this.e != null) {
            this.e.e();
            this.e = null;
        }
        if (this.f != null) {
            this.f.e();
            this.f = null;
        }
        if (this.g != null) {
            this.g.e();
            this.g = null;
        }
    }

    private void c() {
        if (this.q != null) {
            i.a(this.q);
            this.q = null;
        }
        if (this.p != null) {
            i.a(this.p);
            this.p = null;
        }
        if (this.o != null) {
            GLES20.glDeleteTextures(1, this.o, 0);
            this.o = null;
        }
    }

    public void a(n.c cVar) {
        this.l = cVar;
        if (this.l != null) {
            if (this.b != null) {
                this.b.a(this.l);
            }
            if (this.c != null) {
                this.c.a(this.l.j);
            }
            if (a.MODE_ZOOM_IN == cVar.g) {
                this.r = cVar.j;
            }
        }
    }

    /* JADX WARNING: Missing block: B:22:0x005d, code skipped:
            if (com.tencent.liteav.k.n.c.a.MODE_ZOOM_IN == r7.m) goto L_0x0071;
     */
    /* JADX WARNING: Missing block: B:27:0x006e, code skipped:
            if (com.tencent.liteav.k.n.c.a.MODE_ZOOM_OUT == r7.m) goto L_0x0072;
     */
    public int a(int r8) {
        /*
        r7 = this;
        r0 = r7.l;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return r8;
    L_0x0005:
        r0 = r7.o;
        r1 = 1;
        r2 = 0;
        if (r0 != 0) goto L_0x001f;
    L_0x000b:
        r0 = new int[r1];
        r7.o = r0;
        r0 = r7.o;
        r3 = r7.h;
        r4 = r7.i;
        r5 = r7.o;
        r6 = 6408; // 0x1908 float:8.98E-42 double:3.166E-320;
        r3 = com.tencent.liteav.basic.e.i.a(r3, r4, r6, r6, r5);
        r0[r2] = r3;
    L_0x001f:
        r0 = r7.b;
        if (r0 == 0) goto L_0x002a;
    L_0x0023:
        r0 = r7.b;
        r0 = r0.a(r8);
        goto L_0x002b;
    L_0x002a:
        r0 = r8;
    L_0x002b:
        r3 = r7.c;
        if (r3 == 0) goto L_0x0036;
    L_0x002f:
        r3 = r7.c;
        r3 = r3.a(r8);
        goto L_0x0037;
    L_0x0036:
        r3 = r8;
    L_0x0037:
        r4 = r7.a;
        if (r4 == 0) goto L_0x0078;
    L_0x003b:
        r4 = com.tencent.liteav.k.n.c.a.MODE_ZOOM_OUT;
        r5 = r7.l;
        r5 = r5.g;
        if (r4 != r5) goto L_0x0060;
    L_0x0043:
        r4 = r7.d;
        r5 = r7.r;
        r4.a(r5);
        r4 = r7.d;
        r4 = r4.a(r8);
        r5 = r7.a;
        r5 = r5.a(r4);
        if (r5 <= 0) goto L_0x0059;
    L_0x0058:
        r8 = r5;
    L_0x0059:
        r5 = com.tencent.liteav.k.n.c.a.MODE_ZOOM_IN;
        r6 = r7.m;
        if (r5 != r6) goto L_0x0072;
    L_0x005f:
        goto L_0x0071;
    L_0x0060:
        r4 = r7.a;
        r4 = r4.a(r8);
        if (r4 <= 0) goto L_0x0069;
    L_0x0068:
        goto L_0x006a;
    L_0x0069:
        r4 = r8;
    L_0x006a:
        r5 = com.tencent.liteav.k.n.c.a.MODE_ZOOM_OUT;
        r6 = r7.m;
        if (r5 != r6) goto L_0x0071;
    L_0x0070:
        goto L_0x0072;
    L_0x0071:
        r8 = r4;
    L_0x0072:
        r4 = r7.l;
        r4 = r4.g;
        r7.m = r4;
    L_0x0078:
        r4 = r7.q;
        r5 = 36160; // 0x8d40 float:5.0671E-41 double:1.78654E-319;
        if (r4 == 0) goto L_0x00a6;
    L_0x007f:
        r4 = r7.q;
        r4 = r4.a;
        r4 = r4[r2];
        android.opengl.GLES20.glBindFramebuffer(r5, r4);
        r4 = r7.l;
        r4 = r4.k;
        if (r1 != r4) goto L_0x0098;
    L_0x008e:
        r1 = r7.g;
        r4 = r7.o;
        r4 = r4[r2];
        r1.d(r4, r0);
        goto L_0x00a3;
    L_0x0098:
        r1 = r7.g;
        r4 = r7.q;
        r4 = r4.b;
        r4 = r4[r2];
        r1.d(r4, r0);
    L_0x00a3:
        android.opengl.GLES20.glBindFramebuffer(r5, r2);
    L_0x00a6:
        r0 = r7.p;
        r0 = r0.a;
        r0 = r0[r2];
        android.opengl.GLES20.glBindFramebuffer(r5, r0);
        r0 = r7.e;
        if (r0 == 0) goto L_0x00c2;
    L_0x00b3:
        r0 = r7.q;
        if (r0 == 0) goto L_0x00c2;
    L_0x00b7:
        r0 = r7.e;
        r1 = r7.q;
        r1 = r1.b;
        r1 = r1[r2];
        r0.b(r1, r8, r3);
    L_0x00c2:
        android.opengl.GLES20.glBindFramebuffer(r5, r2);
        r8 = r7.p;
        r8 = r8.b;
        r8 = r8[r2];
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.k.b.a(int):int");
    }

    public void a() {
        b();
        c();
    }
}

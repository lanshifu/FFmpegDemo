package com.tencent.liteav.renderer;

import android.os.Handler;
import android.view.TextureView;
import com.tencent.liteav.basic.log.TXCLog;

/* compiled from: TXCTextureViewWrapper */
public class e {
    private TextureView a;
    private Handler b;
    private int c = 0;
    private int d = 0;
    private int e = 640;
    private int f = 480;
    private int g = 0;
    private int h = 0;
    private int i = 1;
    private int j = 0;
    private float k = 1.0f;
    private int l = 0;

    public e(TextureView textureView) {
        this.a = textureView;
        this.c = textureView.getWidth();
        this.d = textureView.getHeight();
        this.b = new Handler(textureView.getContext().getMainLooper());
    }

    public void a(final int i) {
        try {
            this.b.post(new Runnable() {
                public void run() {
                    e.this.b(i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x008d  */
    /* JADX WARNING: Missing block: B:17:0x003c, code skipped:
            if (r6 > r0) goto L_0x003e;
     */
    /* JADX WARNING: Missing block: B:19:0x0040, code skipped:
            r4 = r6;
     */
    /* JADX WARNING: Missing block: B:35:0x006f, code skipped:
            if (r6 < r0) goto L_0x003e;
     */
    /* JADX WARNING: Missing block: B:37:0x0082, code skipped:
            if (r6 < r0) goto L_0x003e;
     */
    public void b(int r6) {
        /*
        r5 = this;
        r5.i = r6;
        r0 = r5.a;
        if (r0 == 0) goto L_0x009e;
    L_0x0006:
        r0 = 1;
        r1 = 90;
        r2 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        r3 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        r4 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        if (r6 != r0) goto L_0x0043;
    L_0x0011:
        r6 = r5.j;
        if (r6 == 0) goto L_0x0086;
    L_0x0015:
        r6 = r5.j;
        if (r6 != r3) goto L_0x001b;
    L_0x0019:
        goto L_0x0086;
    L_0x001b:
        r6 = r5.j;
        if (r6 == r2) goto L_0x0023;
    L_0x001f:
        r6 = r5.j;
        if (r6 != r1) goto L_0x0086;
    L_0x0023:
        r6 = r5.g;
        if (r6 == 0) goto L_0x0042;
    L_0x0027:
        r6 = r5.h;
        if (r6 != 0) goto L_0x002c;
    L_0x002b:
        goto L_0x0042;
    L_0x002c:
        r6 = r5.d;
        r6 = (float) r6;
        r0 = r5.g;
        r0 = (float) r0;
        r6 = r6 / r0;
        r0 = r5.c;
        r0 = (float) r0;
        r1 = r5.h;
        r1 = (float) r1;
        r0 = r0 / r1;
        r1 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
        if (r1 <= 0) goto L_0x0040;
    L_0x003e:
        r4 = r0;
        goto L_0x0086;
    L_0x0040:
        r4 = r6;
        goto L_0x0086;
    L_0x0042:
        return;
    L_0x0043:
        if (r6 != 0) goto L_0x0086;
    L_0x0045:
        r6 = r5.g;
        if (r6 == 0) goto L_0x0085;
    L_0x0049:
        r6 = r5.h;
        if (r6 != 0) goto L_0x004e;
    L_0x004d:
        goto L_0x0085;
    L_0x004e:
        r6 = r5.j;
        if (r6 == 0) goto L_0x0072;
    L_0x0052:
        r6 = r5.j;
        if (r6 != r3) goto L_0x0057;
    L_0x0056:
        goto L_0x0072;
    L_0x0057:
        r6 = r5.j;
        if (r6 == r2) goto L_0x005f;
    L_0x005b:
        r6 = r5.j;
        if (r6 != r1) goto L_0x0086;
    L_0x005f:
        r6 = r5.d;
        r6 = (float) r6;
        r0 = r5.g;
        r0 = (float) r0;
        r6 = r6 / r0;
        r0 = r5.c;
        r0 = (float) r0;
        r1 = r5.h;
        r1 = (float) r1;
        r0 = r0 / r1;
        r1 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
        if (r1 >= 0) goto L_0x0040;
    L_0x0071:
        goto L_0x003e;
    L_0x0072:
        r6 = r5.d;
        r6 = (float) r6;
        r0 = r5.h;
        r0 = (float) r0;
        r6 = r6 / r0;
        r0 = r5.c;
        r0 = (float) r0;
        r1 = r5.g;
        r1 = (float) r1;
        r0 = r0 / r1;
        r1 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
        if (r1 >= 0) goto L_0x0040;
    L_0x0084:
        goto L_0x003e;
    L_0x0085:
        return;
    L_0x0086:
        r6 = r5.k;
        r0 = 0;
        r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
        if (r6 >= 0) goto L_0x008e;
    L_0x008d:
        r4 = -r4;
    L_0x008e:
        r6 = r5.a;
        r6.setScaleX(r4);
        r6 = r5.a;
        r0 = java.lang.Math.abs(r4);
        r6.setScaleY(r0);
        r5.k = r4;
    L_0x009e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.e.b(int):void");
    }

    public void c(final int i) {
        try {
            this.b.post(new Runnable() {
                public void run() {
                    e.this.d(i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0085  */
    /* JADX WARNING: Missing block: B:17:0x003f, code skipped:
            if (r5 > r2) goto L_0x0041;
     */
    /* JADX WARNING: Missing block: B:23:0x004b, code skipped:
            if (r5 < r2) goto L_0x0041;
     */
    public void d(int r5) {
        /*
        r4 = this;
        r5 = r5 % 360;
        r4.j = r5;
        r0 = r4.a;
        if (r0 == 0) goto L_0x0096;
    L_0x0008:
        r0 = 1;
        r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        if (r5 == 0) goto L_0x004f;
    L_0x000d:
        r2 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        if (r5 != r2) goto L_0x0012;
    L_0x0011:
        goto L_0x004f;
    L_0x0012:
        r2 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        if (r5 == r2) goto L_0x001a;
    L_0x0016:
        r2 = 90;
        if (r5 != r2) goto L_0x007e;
    L_0x001a:
        r2 = r4.g;
        if (r2 == 0) goto L_0x004e;
    L_0x001e:
        r2 = r4.h;
        if (r2 != 0) goto L_0x0023;
    L_0x0022:
        goto L_0x004e;
    L_0x0023:
        r2 = r4.a;
        r5 = 360 - r5;
        r5 = (float) r5;
        r2.setRotation(r5);
        r5 = r4.d;
        r5 = (float) r5;
        r2 = r4.g;
        r2 = (float) r2;
        r5 = r5 / r2;
        r2 = r4.c;
        r2 = (float) r2;
        r3 = r4.h;
        r3 = (float) r3;
        r2 = r2 / r3;
        r3 = r4.i;
        if (r3 != r0) goto L_0x0045;
    L_0x003d:
        r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x0043;
    L_0x0041:
        r1 = r2;
        goto L_0x007e;
    L_0x0043:
        r1 = r5;
        goto L_0x007e;
    L_0x0045:
        r0 = r4.i;
        if (r0 != 0) goto L_0x007e;
    L_0x0049:
        r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x0043;
    L_0x004d:
        goto L_0x0041;
    L_0x004e:
        return;
    L_0x004f:
        r2 = r4.a;
        r5 = 360 - r5;
        r5 = (float) r5;
        r2.setRotation(r5);
        r5 = r4.i;
        if (r5 != r0) goto L_0x005c;
    L_0x005b:
        goto L_0x007e;
    L_0x005c:
        r5 = r4.i;
        if (r5 != 0) goto L_0x007e;
    L_0x0060:
        r5 = r4.g;
        if (r5 == 0) goto L_0x007d;
    L_0x0064:
        r5 = r4.h;
        if (r5 != 0) goto L_0x0069;
    L_0x0068:
        goto L_0x007d;
    L_0x0069:
        r5 = r4.d;
        r5 = (float) r5;
        r0 = r4.h;
        r0 = (float) r0;
        r5 = r5 / r0;
        r0 = r4.c;
        r0 = (float) r0;
        r1 = r4.g;
        r1 = (float) r1;
        r0 = r0 / r1;
        r1 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1));
        if (r1 >= 0) goto L_0x0043;
    L_0x007b:
        r1 = r0;
        goto L_0x007e;
    L_0x007d:
        return;
    L_0x007e:
        r5 = r4.k;
        r0 = 0;
        r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1));
        if (r5 >= 0) goto L_0x0086;
    L_0x0085:
        r1 = -r1;
    L_0x0086:
        r5 = r4.a;
        r5.setScaleX(r1);
        r5 = r4.a;
        r0 = java.lang.Math.abs(r1);
        r5.setScaleY(r0);
        r4.k = r1;
    L_0x0096:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.e.d(int):void");
    }

    private void a() {
        try {
            this.b.post(new Runnable() {
                public void run() {
                    e.this.c(e.this.e, e.this.f);
                    e.this.b(e.this.i);
                    e.this.d(e.this.j);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Missing block: B:15:0x0081, code skipped:
            return;
     */
    private void c(int r5, int r6) {
        /*
        r4 = this;
        r0 = r4.a;
        if (r0 == 0) goto L_0x0081;
    L_0x0004:
        if (r5 == 0) goto L_0x0081;
    L_0x0006:
        if (r6 != 0) goto L_0x000a;
    L_0x0008:
        goto L_0x0081;
    L_0x000a:
        r0 = r4.c;
        if (r0 == 0) goto L_0x0080;
    L_0x000e:
        r0 = r4.d;
        if (r0 != 0) goto L_0x0013;
    L_0x0012:
        goto L_0x0080;
    L_0x0013:
        r0 = (double) r6;
        r5 = (double) r5;
        java.lang.Double.isNaN(r0);
        java.lang.Double.isNaN(r5);
        r0 = r0 / r5;
        r5 = r4.d;
        r6 = r4.c;
        r2 = (double) r6;
        java.lang.Double.isNaN(r2);
        r2 = r2 * r0;
        r6 = (int) r2;
        if (r5 <= r6) goto L_0x0039;
    L_0x0029:
        r5 = r4.c;
        r4.g = r5;
        r5 = r4.c;
        r5 = (double) r5;
        java.lang.Double.isNaN(r5);
        r5 = r5 * r0;
        r5 = (int) r5;
        r4.h = r5;
        goto L_0x0047;
    L_0x0039:
        r5 = r4.d;
        r5 = (double) r5;
        java.lang.Double.isNaN(r5);
        r5 = r5 / r0;
        r5 = (int) r5;
        r4.g = r5;
        r5 = r4.d;
        r4.h = r5;
    L_0x0047:
        r5 = r4.c;
        r6 = r4.g;
        r5 = r5 - r6;
        r5 = (float) r5;
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5 = r5 / r6;
        r0 = r4.d;
        r1 = r4.h;
        r0 = r0 - r1;
        r0 = (float) r0;
        r0 = r0 / r6;
        r6 = r4.g;
        r6 = (float) r6;
        r1 = r4.c;
        r1 = (float) r1;
        r6 = r6 / r1;
        r1 = r4.h;
        r1 = (float) r1;
        r2 = r4.d;
        r2 = (float) r2;
        r1 = r1 / r2;
        r2 = new android.graphics.Matrix;
        r2.<init>();
        r3 = r4.a;
        r3.getTransform(r2);
        r2.setScale(r6, r1);
        r2.postTranslate(r5, r0);
        r5 = r4.a;
        r5.setTransform(r2);
        r5 = r4.a;
        r5.requestLayout();
        return;
    L_0x0080:
        return;
    L_0x0081:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.e.c(int, int):void");
    }

    public void a(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("vrender: set view size:");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        TXCLog.w("TXCTextureViewWrapper", stringBuilder.toString());
        this.c = i;
        this.d = i2;
        a();
    }

    public void b(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("vrender: set video size:");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        TXCLog.w("TXCTextureViewWrapper", stringBuilder.toString());
        this.e = i;
        this.f = i2;
        a();
    }
}

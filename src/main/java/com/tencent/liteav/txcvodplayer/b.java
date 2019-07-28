package com.tencent.liteav.txcvodplayer;

import android.view.View;
import java.lang.ref.WeakReference;

/* compiled from: MeasureHelper */
public final class b {
    private WeakReference<View> a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i = 0;

    public b(View view) {
        this.a = new WeakReference(view);
    }

    public void a(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public void b(int i, int i2) {
        this.d = i;
        this.e = i2;
    }

    public void a(int i) {
        this.f = i;
    }

    /* JADX WARNING: Missing block: B:63:0x0101, code skipped:
            if (r1 > r9) goto L_0x0125;
     */
    public void c(int r9, int r10) {
        /*
        r8 = this;
        r0 = r8.f;
        r1 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        r2 = 90;
        if (r0 == r2) goto L_0x000c;
    L_0x0008:
        r0 = r8.f;
        if (r0 != r1) goto L_0x000f;
    L_0x000c:
        r7 = r10;
        r10 = r9;
        r9 = r7;
    L_0x000f:
        r0 = r8.b;
        r0 = android.view.View.getDefaultSize(r0, r9);
        r3 = r8.c;
        r3 = android.view.View.getDefaultSize(r3, r10);
        r4 = r8.i;
        r5 = 3;
        if (r4 != r5) goto L_0x0022;
    L_0x0020:
        goto L_0x0125;
    L_0x0022:
        r4 = r8.b;
        if (r4 <= 0) goto L_0x0123;
    L_0x0026:
        r4 = r8.c;
        if (r4 <= 0) goto L_0x0123;
    L_0x002a:
        r0 = android.view.View.MeasureSpec.getMode(r9);
        r9 = android.view.View.MeasureSpec.getSize(r9);
        r3 = android.view.View.MeasureSpec.getMode(r10);
        r10 = android.view.View.MeasureSpec.getSize(r10);
        r4 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r0 != r4) goto L_0x00bc;
    L_0x003e:
        if (r3 != r4) goto L_0x00bc;
    L_0x0040:
        r0 = (float) r9;
        r3 = (float) r10;
        r4 = r0 / r3;
        r5 = r8.i;
        switch(r5) {
            case 4: goto L_0x0071;
            case 5: goto L_0x0063;
            default: goto L_0x0049;
        };
    L_0x0049:
        r1 = r8.b;
        r1 = (float) r1;
        r2 = r8.c;
        r2 = (float) r2;
        r5 = r1 / r2;
        r1 = r8.d;
        if (r1 <= 0) goto L_0x007e;
    L_0x0055:
        r1 = r8.e;
        if (r1 <= 0) goto L_0x007e;
    L_0x0059:
        r1 = r8.d;
        r1 = (float) r1;
        r5 = r5 * r1;
        r1 = r8.e;
        r1 = (float) r1;
        r5 = r5 / r1;
        goto L_0x007e;
    L_0x0063:
        r5 = 1068149419; // 0x3faaaaab float:1.3333334 double:5.277359326E-315;
        r6 = r8.f;
        if (r6 == r2) goto L_0x006e;
    L_0x006a:
        r2 = r8.f;
        if (r2 != r1) goto L_0x007e;
    L_0x006e:
        r5 = 1061158912; // 0x3f400000 float:0.75 double:5.24282163E-315;
        goto L_0x007e;
    L_0x0071:
        r5 = 1071877689; // 0x3fe38e39 float:1.7777778 double:5.295779427E-315;
        r6 = r8.f;
        if (r6 == r2) goto L_0x007c;
    L_0x0078:
        r2 = r8.f;
        if (r2 != r1) goto L_0x007e;
    L_0x007c:
        r5 = 1058013184; // 0x3f100000 float:0.5625 double:5.22727967E-315;
    L_0x007e:
        r1 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1));
        if (r1 <= 0) goto L_0x0084;
    L_0x0082:
        r1 = 1;
        goto L_0x0085;
    L_0x0084:
        r1 = 0;
    L_0x0085:
        r2 = r8.i;
        switch(r2) {
            case 0: goto L_0x00a2;
            case 1: goto L_0x0097;
            case 2: goto L_0x008a;
            case 3: goto L_0x008a;
            case 4: goto L_0x00a2;
            case 5: goto L_0x00a2;
            default: goto L_0x008a;
        };
    L_0x008a:
        if (r1 == 0) goto L_0x00ad;
    L_0x008c:
        r10 = r8.b;
        r9 = java.lang.Math.min(r10, r9);
        r10 = (float) r9;
        r10 = r10 / r5;
        r10 = (int) r10;
        goto L_0x0125;
    L_0x0097:
        if (r1 == 0) goto L_0x009e;
    L_0x0099:
        r3 = r3 * r5;
        r9 = (int) r3;
        goto L_0x0125;
    L_0x009e:
        r0 = r0 / r5;
        r10 = (int) r0;
        goto L_0x0125;
    L_0x00a2:
        if (r1 == 0) goto L_0x00a8;
    L_0x00a4:
        r0 = r0 / r5;
        r10 = (int) r0;
        goto L_0x0125;
    L_0x00a8:
        r3 = r3 * r5;
        r9 = (int) r3;
        goto L_0x0125;
    L_0x00ad:
        r9 = r8.c;
        r9 = java.lang.Math.min(r9, r10);
        r10 = (float) r9;
        r10 = r10 * r5;
        r10 = (int) r10;
        r7 = r10;
        r10 = r9;
        r9 = r7;
        goto L_0x0125;
    L_0x00bc:
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r0 != r1) goto L_0x00e6;
    L_0x00c0:
        if (r3 != r1) goto L_0x00e6;
    L_0x00c2:
        r0 = r8.b;
        r0 = r0 * r10;
        r1 = r8.c;
        r1 = r1 * r9;
        if (r0 >= r1) goto L_0x00d4;
    L_0x00cc:
        r9 = r8.b;
        r9 = r9 * r10;
        r0 = r8.c;
        r9 = r9 / r0;
        goto L_0x0125;
    L_0x00d4:
        r0 = r8.b;
        r0 = r0 * r10;
        r1 = r8.c;
        r1 = r1 * r9;
        if (r0 <= r1) goto L_0x0125;
    L_0x00de:
        r10 = r8.c;
        r10 = r10 * r9;
        r0 = r8.b;
        r10 = r10 / r0;
        goto L_0x0125;
    L_0x00e6:
        if (r0 != r1) goto L_0x00f6;
    L_0x00e8:
        r0 = r8.c;
        r0 = r0 * r9;
        r1 = r8.b;
        r0 = r0 / r1;
        if (r3 != r4) goto L_0x00f4;
    L_0x00f1:
        if (r0 <= r10) goto L_0x00f4;
    L_0x00f3:
        goto L_0x0125;
    L_0x00f4:
        r10 = r0;
        goto L_0x0125;
    L_0x00f6:
        if (r3 != r1) goto L_0x0106;
    L_0x00f8:
        r1 = r8.b;
        r1 = r1 * r10;
        r2 = r8.c;
        r1 = r1 / r2;
        if (r0 != r4) goto L_0x0104;
    L_0x0101:
        if (r1 <= r9) goto L_0x0104;
    L_0x0103:
        goto L_0x0125;
    L_0x0104:
        r9 = r1;
        goto L_0x0125;
    L_0x0106:
        r1 = r8.b;
        r2 = r8.c;
        if (r3 != r4) goto L_0x0116;
    L_0x010c:
        if (r2 <= r10) goto L_0x0116;
    L_0x010e:
        r1 = r8.b;
        r1 = r1 * r10;
        r2 = r8.c;
        r1 = r1 / r2;
        goto L_0x0117;
    L_0x0116:
        r10 = r2;
    L_0x0117:
        if (r0 != r4) goto L_0x0104;
    L_0x0119:
        if (r1 <= r9) goto L_0x0104;
    L_0x011b:
        r10 = r8.c;
        r10 = r10 * r9;
        r0 = r8.b;
        r10 = r10 / r0;
        goto L_0x0125;
    L_0x0123:
        r9 = r0;
        r10 = r3;
    L_0x0125:
        r8.g = r9;
        r8.h = r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.txcvodplayer.b.c(int, int):void");
    }

    public int a() {
        return this.g;
    }

    public int b() {
        return this.h;
    }

    public void b(int i) {
        this.i = i;
    }
}

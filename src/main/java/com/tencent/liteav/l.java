package com.tencent.liteav;

import android.content.Context;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.b;
import com.tencent.liteav.screencapture.a;
import com.tencent.liteav.screencapture.c;
import java.util.LinkedList;
import java.util.Queue;
import javax.microedition.khronos.egl.EGLContext;

/* compiled from: TXCScreenCaptureSource */
public class l implements p, c {
    private static final String a = "l";
    private q b;
    private a c = null;
    private EGLContext d = null;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private final Queue<Runnable> j = new LinkedList();

    public void a(float f) {
    }

    public void a(float f, float f2) {
    }

    public void a(com.tencent.liteav.basic.g.c cVar) {
    }

    public boolean a(int i) {
        return false;
    }

    public void b(int i) {
    }

    public void b(boolean z) {
    }

    public void c(int i) {
    }

    public void c(boolean z) {
    }

    public void d(int i) {
    }

    public boolean d() {
        return true;
    }

    public boolean d(boolean z) {
        return false;
    }

    public int e() {
        return 0;
    }

    public void e(boolean z) {
    }

    public l(Context context, h hVar) {
        this.c = new a(context, hVar.N);
        this.c.a((c) this);
        boolean a = hVar.a();
        this.e = hVar.h;
        if (hVar.a > 1280 || hVar.b > 1280) {
            this.f = a ? Math.max(hVar.a, hVar.b) : Math.min(hVar.a, hVar.b);
            this.g = a ? Math.min(hVar.a, hVar.b) : Math.max(hVar.a, hVar.b);
        } else {
            int i = 720;
            this.f = a ? 1280 : 720;
            if (!a) {
                i = 1280;
            }
            this.g = i;
        }
        this.h = hVar.a;
        this.i = hVar.b;
    }

    public void a() {
        this.c.a(this.f, this.g, this.e);
        this.c.a(true);
    }

    public void a(boolean z) {
        this.c.a(null);
        this.c.a(false);
    }

    public void c() {
        this.c.a(false);
    }

    public void b() {
        this.c.a(true);
    }

    public EGLContext f() {
        return this.d;
    }

    public void a(q qVar) {
        this.b = qVar;
    }

    public void a(Runnable runnable) {
        if (this.c != null) {
            this.c.a(runnable);
        }
    }

    public void a(com.tencent.liteav.basic.d.a aVar) {
        if (this.c != null) {
            this.c.a(aVar);
        }
    }

    public void a(int i, int i2) {
        this.h = i;
        this.i = i2;
    }

    /* JADX WARNING: Missing block: B:8:0x0011, code skipped:
            if (r0 != null) goto L_0x0014;
     */
    /* JADX WARNING: Missing block: B:9:0x0013, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:10:0x0014, code skipped:
            r0.run();
     */
    /* JADX WARNING: Missing block: B:11:0x0018, code skipped:
            return true;
     */
    private boolean a(java.util.Queue<java.lang.Runnable> r3) {
        /*
        r2 = this;
        monitor-enter(r3);
        r0 = r3.isEmpty();	 Catch:{ all -> 0x0019 }
        r1 = 0;
        if (r0 == 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r3);	 Catch:{ all -> 0x0019 }
        return r1;
    L_0x000a:
        r0 = r3.poll();	 Catch:{ all -> 0x0019 }
        r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x0019 }
        monitor-exit(r3);	 Catch:{ all -> 0x0019 }
        if (r0 != 0) goto L_0x0014;
    L_0x0013:
        return r1;
    L_0x0014:
        r0.run();
        r3 = 1;
        return r3;
    L_0x0019:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0019 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.l.a(java.util.Queue):boolean");
    }

    public void a(int i, EGLContext eGLContext) {
        if (i == 0) {
            this.d = eGLContext;
            return;
        }
        this.d = null;
        TXCLog.e(a, "Start screen capture failed");
    }

    public void a(int i, int i2, int i3, int i4, long j) {
        while (a(this.j)) {
        }
        if (i != 0) {
            TXCLog.e(a, "onScreenCaptureFrame failed");
        } else if (this.b != null) {
            f(i3 < i4);
            com.tencent.liteav.basic.g.c cVar = new com.tencent.liteav.basic.g.c();
            cVar.d = i3;
            cVar.e = i4;
            cVar.f = this.h;
            cVar.g = this.i;
            cVar.a = i2;
            cVar.b = 0;
            cVar.i = 0;
            cVar.k = b.a(cVar.d, cVar.e, this.h, this.i);
            this.b.b(cVar);
        }
    }

    public void a(Object obj) {
        while (a(this.j)) {
        }
        if (this.b != null) {
            this.b.r();
        }
    }

    private void f(boolean z) {
        if (z) {
            if (this.h > this.i) {
                a(this.i, this.h);
            }
        } else if (this.h < this.i) {
            a(this.i, this.h);
        }
    }
}

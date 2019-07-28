package com.tencent.liteav.e;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import com.tencent.liteav.basic.e.c;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.d.g;

/* compiled from: VideoGLGenerate */
public class aa {
    private final String a = "VideoGLGenerate";
    private float[] b = new float[16];
    private Handler c;
    private HandlerThread d = new HandlerThread("VideoGLGenerate");
    private int e;
    private int f;
    private c g;
    private com.tencent.liteav.renderer.c h;
    private com.tencent.liteav.renderer.c i;
    private o j;
    private m k;
    private SurfaceTexture l;
    private Surface m;
    private boolean n;
    private boolean o;
    private e p;
    private OnFrameAvailableListener q = new OnFrameAvailableListener() {
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            aa.this.n = true;
            if (aa.this.p != null) {
                aa.this.c(aa.this.p);
                aa.this.p = null;
            }
        }
    };

    public aa() {
        this.d.start();
        this.c = new Handler(this.d.getLooper());
    }

    public void a(g gVar) {
        this.e = gVar.a;
        this.f = gVar.b;
    }

    public void a(m mVar) {
        this.k = mVar;
    }

    public void a(o oVar) {
        this.j = oVar;
    }

    public void a() {
        TXCLog.d("VideoGLGenerate", "start");
        if (this.c != null) {
            this.c.post(new Runnable() {
                public void run() {
                    aa.this.f();
                    aa.this.d();
                }
            });
        }
    }

    public void b() {
        TXCLog.d("VideoGLGenerate", "stop");
        if (this.c != null) {
            this.c.post(new Runnable() {
                public void run() {
                    aa.this.e();
                    aa.this.g();
                }
            });
        }
    }

    public void c() {
        if (this.c != null) {
            if (this.d != null) {
                if (VERSION.SDK_INT >= 18) {
                    this.d.quitSafely();
                } else {
                    this.d.quit();
                }
                this.d = null;
            }
            this.k = null;
            this.j = null;
            this.q = null;
            this.c = null;
        }
    }

    private void d() {
        TXCLog.d("VideoGLGenerate", "initTextureRender");
        this.h = new com.tencent.liteav.renderer.c(true);
        this.h.b();
        this.i = new com.tencent.liteav.renderer.c(false);
        this.i.b();
        this.l = new SurfaceTexture(this.h.a());
        this.m = new Surface(this.l);
        this.l.setOnFrameAvailableListener(this.q);
        this.o = true;
        if (this.k != null) {
            this.k.a(this.m);
        }
        if (this.j != null) {
            this.j.a(this.g.d());
        }
    }

    private void e() {
        TXCLog.d("VideoGLGenerate", "destroyTextureRender");
        this.o = false;
        if (this.h != null) {
            this.h.c();
        }
        this.h = null;
        if (this.i != null) {
            this.i.c();
        }
        this.i = null;
    }

    private void f() {
        TXCLog.d("VideoGLGenerate", "initEGL");
        this.g = c.a(null, null, null, this.e, this.f);
    }

    private void g() {
        TXCLog.d("VideoGLGenerate", "destroyEGL");
        if (this.k != null) {
            this.k.b(this.m);
        }
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
    }

    public synchronized void a(final e eVar) {
        if (this.c != null) {
            this.c.post(new Runnable() {
                public void run() {
                    aa.this.c(eVar);
                }
            });
        }
    }

    public void b(final e eVar) {
        if (this.c != null) {
            this.c.post(new Runnable() {
                public void run() {
                    aa.this.n = true;
                    aa.this.c(eVar);
                }
            });
        }
    }

    /* JADX WARNING: Missing block: B:13:0x001d, code skipped:
            android.opengl.GLES20.glViewport(0, 0, r4.e, r4.f);
     */
    /* JADX WARNING: Missing block: B:14:0x0024, code skipped:
            if (r0 == false) goto L_0x0065;
     */
    /* JADX WARNING: Missing block: B:17:0x0028, code skipped:
            if (r4.l == null) goto L_0x0036;
     */
    /* JADX WARNING: Missing block: B:18:0x002a, code skipped:
            r4.l.updateTexImage();
            r4.l.getTransformMatrix(r4.b);
     */
    private boolean c(com.tencent.liteav.d.e r5) {
        /*
        r4 = this;
        r0 = r4.o;
        r1 = 0;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = r5.p();
        if (r0 != 0) goto L_0x006e;
    L_0x000c:
        r0 = r5.r();
        if (r0 == 0) goto L_0x0013;
    L_0x0012:
        goto L_0x006e;
    L_0x0013:
        monitor-enter(r4);
        r0 = r4.n;	 Catch:{ all -> 0x006b }
        if (r0 == 0) goto L_0x0067;
    L_0x0018:
        r0 = r4.n;	 Catch:{ all -> 0x006b }
        r4.n = r1;	 Catch:{ all -> 0x006b }
        monitor-exit(r4);	 Catch:{ all -> 0x006b }
        r2 = r4.e;
        r3 = r4.f;
        android.opengl.GLES20.glViewport(r1, r1, r2, r3);
        if (r0 == 0) goto L_0x0065;
    L_0x0026:
        r0 = r4.l;	 Catch:{ Exception -> 0x0036 }
        if (r0 == 0) goto L_0x0036;
    L_0x002a:
        r0 = r4.l;	 Catch:{ Exception -> 0x0036 }
        r0.updateTexImage();	 Catch:{ Exception -> 0x0036 }
        r0 = r4.l;	 Catch:{ Exception -> 0x0036 }
        r1 = r4.b;	 Catch:{ Exception -> 0x0036 }
        r0.getTransformMatrix(r1);	 Catch:{ Exception -> 0x0036 }
    L_0x0036:
        r0 = r4.k;
        if (r0 == 0) goto L_0x005a;
    L_0x003a:
        r0 = r5.y();
        if (r0 != 0) goto L_0x004c;
    L_0x0040:
        r0 = r4.k;
        r1 = r5.x();
        r2 = r4.b;
        r0.a(r1, r2, r5);
        goto L_0x0065;
    L_0x004c:
        r0 = r4.k;
        r1 = r4.h;
        r1 = r1.a();
        r2 = r4.b;
        r0.a(r1, r2, r5);
        goto L_0x0065;
    L_0x005a:
        r5 = r4.i;
        if (r5 == 0) goto L_0x0065;
    L_0x005e:
        r5 = r4.i;
        r0 = r4.l;
        r5.a(r0);
    L_0x0065:
        r5 = 1;
        return r5;
    L_0x0067:
        r4.p = r5;	 Catch:{ all -> 0x006b }
        monitor-exit(r4);	 Catch:{ all -> 0x006b }
        return r1;
    L_0x006b:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x006b }
        throw r5;
    L_0x006e:
        r0 = r4.k;
        if (r0 == 0) goto L_0x0091;
    L_0x0072:
        r0 = r5.y();
        if (r0 != 0) goto L_0x0084;
    L_0x0078:
        r0 = r4.k;
        r2 = r5.x();
        r3 = r4.b;
        r0.a(r2, r3, r5);
        goto L_0x0091;
    L_0x0084:
        r0 = r4.k;
        r2 = r4.h;
        r2 = r2.a();
        r3 = r4.b;
        r0.a(r2, r3, r5);
    L_0x0091:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.e.aa.c(com.tencent.liteav.d.e):boolean");
    }
}

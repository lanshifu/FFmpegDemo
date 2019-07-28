package com.tencent.liteav.b;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import com.tencent.liteav.basic.e.c;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.d.g;
import com.tencent.liteav.e.m;
import com.tencent.liteav.e.o;
import java.util.ArrayList;
import java.util.List;

/* compiled from: VideoGLMultiGenerate */
public class i {
    public List<a> a = new ArrayList();
    private final String b = "VideoGLMultiGenerate";
    private Handler c;
    private HandlerThread d;
    private int e;
    private int f;
    private c g;
    private com.tencent.liteav.renderer.c h;
    private o i;
    private boolean j;

    /* compiled from: VideoGLMultiGenerate */
    public class a {
        private int b;
        private float[] c;
        private int d;
        private int e;
        private com.tencent.liteav.renderer.c f;
        private m g;
        private SurfaceTexture h;
        private Surface i;
        private boolean j;
        private e k;
        private OnFrameAvailableListener l = new OnFrameAvailableListener() {
            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onFrameAvailable, index = ");
                stringBuilder.append(a.this.b);
                stringBuilder.append(", mFrame = ");
                stringBuilder.append(a.this.k);
                TXCLog.d("VideoGLMultiGenerate", stringBuilder.toString());
                a.this.j = true;
                if (a.this.k != null) {
                    i.this.b(a.this.k, a.this.b);
                    a.this.k = null;
                }
            }
        };
    }

    public i(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            a aVar = new a();
            aVar.b = i2;
            aVar.c = new float[16];
            this.a.add(aVar);
        }
        this.d = new HandlerThread("VideoGLMultiGenerate");
        this.d.start();
        this.c = new Handler(this.d.getLooper());
    }

    public void a(g gVar, int i) {
        if (this.a == null || this.a.size() == 0 || i >= this.a.size()) {
            TXCLog.e("VideoGLMultiGenerate", "setRenderResolution, mVideoGLInfoList is empty or mIndex is larger than size");
            return;
        }
        a aVar = (a) this.a.get(i);
        aVar.d = gVar.a;
        aVar.e = gVar.b;
        this.e = gVar.a > this.e ? gVar.a : this.e;
        this.f = gVar.b > this.f ? gVar.b : this.f;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setRenderResolution, mSurfaceWidth = ");
        stringBuilder.append(this.e);
        stringBuilder.append(", mSurfaceHeight = ");
        stringBuilder.append(this.f);
        TXCLog.i("VideoGLMultiGenerate", stringBuilder.toString());
    }

    public void a(m mVar, int i) {
        if (this.a == null || this.a.size() == 0 || i >= this.a.size()) {
            TXCLog.e("VideoGLMultiGenerate", "setListener, mVideoGLInfoList is empty or mIndex is larger than size");
        } else {
            ((a) this.a.get(i)).g = mVar;
        }
    }

    public void a(o oVar) {
        this.i = oVar;
    }

    public void a() {
        TXCLog.d("VideoGLMultiGenerate", "start");
        if (this.c != null) {
            this.c.post(new Runnable() {
                public void run() {
                    i.this.e();
                    i.this.c();
                }
            });
        }
    }

    public void b() {
        TXCLog.d("VideoGLMultiGenerate", "stop");
        if (this.c != null) {
            this.c.post(new Runnable() {
                public void run() {
                    i.this.d();
                    i.this.f();
                }
            });
        }
    }

    private void c() {
        TXCLog.d("VideoGLMultiGenerate", "initTextureRender");
        int i = 0;
        this.h = new com.tencent.liteav.renderer.c(false);
        this.h.b();
        while (i < this.a.size()) {
            a aVar = (a) this.a.get(i);
            aVar.f = new com.tencent.liteav.renderer.c(true);
            aVar.f.b();
            aVar.h = new SurfaceTexture(aVar.f.a());
            aVar.i = new Surface(aVar.h);
            aVar.h.setOnFrameAvailableListener(aVar.l);
            if (aVar.g != null) {
                aVar.g.a(aVar.i);
            }
            if (i == this.a.size() - 1) {
                this.j = true;
            }
            i++;
        }
        if (this.i != null) {
            this.i.a(this.g.d());
        }
    }

    private void d() {
        TXCLog.d("VideoGLMultiGenerate", "destroyTextureRender");
        this.j = false;
        for (int i = 0; i < this.a.size(); i++) {
            a aVar = (a) this.a.get(i);
            if (aVar.f != null) {
                aVar.f.c();
                aVar.f = null;
                if (aVar.h != null) {
                    aVar.h.setOnFrameAvailableListener(null);
                    aVar.h.release();
                    aVar.h = null;
                }
                if (aVar.i != null) {
                    aVar.i.release();
                    aVar.i = null;
                }
                aVar.h = null;
                aVar.k = null;
                aVar.j = false;
                aVar.c = new float[16];
            }
        }
        if (this.h != null) {
            this.h.c();
        }
        this.h = null;
    }

    private void e() {
        TXCLog.d("VideoGLMultiGenerate", "initEGL");
        this.g = c.a(null, null, null, this.e, this.f);
    }

    private void f() {
        TXCLog.d("VideoGLMultiGenerate", "destroyEGL");
        for (int i = 0; i < this.a.size(); i++) {
            a aVar = (a) this.a.get(i);
            if (aVar.g != null) {
                aVar.g.b(aVar.i);
            }
        }
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
    }

    /* JADX WARNING: Missing block: B:13:0x0025, code skipped:
            return;
     */
    public synchronized void a(final com.tencent.liteav.d.e r3, final int r4) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.a;	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x0026;
    L_0x0005:
        r0 = r2.a;	 Catch:{ all -> 0x002f }
        r0 = r0.size();	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x0026;
    L_0x000d:
        r0 = r2.a;	 Catch:{ all -> 0x002f }
        r0 = r0.size();	 Catch:{ all -> 0x002f }
        if (r4 < r0) goto L_0x0016;
    L_0x0015:
        goto L_0x0026;
    L_0x0016:
        r0 = r2.c;	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x0024;
    L_0x001a:
        r0 = r2.c;	 Catch:{ all -> 0x002f }
        r1 = new com.tencent.liteav.b.i$3;	 Catch:{ all -> 0x002f }
        r1.<init>(r3, r4);	 Catch:{ all -> 0x002f }
        r0.post(r1);	 Catch:{ all -> 0x002f }
    L_0x0024:
        monitor-exit(r2);
        return;
    L_0x0026:
        r3 = "VideoGLMultiGenerate";
        r4 = "setListener, mVideoGLInfoList is empty or mIndex is larger than size";
        com.tencent.liteav.basic.log.TXCLog.e(r3, r4);	 Catch:{ all -> 0x002f }
        monitor-exit(r2);
        return;
    L_0x002f:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.b.i.a(com.tencent.liteav.d.e, int):void");
    }

    /* JADX WARNING: Missing block: B:13:0x004d, code skipped:
            android.opengl.GLES20.glViewport(0, 0, com.tencent.liteav.b.i.a.j(r0), com.tencent.liteav.b.i.a.k(r0));
     */
    /* JADX WARNING: Missing block: B:14:0x0058, code skipped:
            if (r7 == false) goto L_0x00af;
     */
    /* JADX WARNING: Missing block: B:17:0x005e, code skipped:
            if (com.tencent.liteav.b.i.a.d(r0) == null) goto L_0x0072;
     */
    /* JADX WARNING: Missing block: B:18:0x0060, code skipped:
            com.tencent.liteav.b.i.a.d(r0).updateTexImage();
            com.tencent.liteav.b.i.a.d(r0).getTransformMatrix(com.tencent.liteav.b.i.a.i(r0));
     */
    private boolean b(com.tencent.liteav.d.e r6, int r7) {
        /*
        r5 = this;
        r0 = r5.j;
        r1 = 0;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = r5.a;
        r0 = r0.get(r7);
        r0 = (com.tencent.liteav.b.i.a) r0;
        r2 = "VideoGLMultiGenerate";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "onDrawFrame, mTextureAvailable = ";
        r3.append(r4);
        r4 = r0.j;
        r3.append(r4);
        r4 = ", mIndex = ";
        r3.append(r4);
        r3.append(r7);
        r7 = r3.toString();
        com.tencent.liteav.basic.log.TXCLog.d(r2, r7);
        r7 = r6.p();
        if (r7 != 0) goto L_0x00b9;
    L_0x0036:
        r7 = r6.r();
        if (r7 == 0) goto L_0x003e;
    L_0x003c:
        goto L_0x00b9;
    L_0x003e:
        monitor-enter(r5);
        r7 = r0.j;	 Catch:{ all -> 0x00b6 }
        if (r7 == 0) goto L_0x00b1;
    L_0x0045:
        r7 = r0.j;	 Catch:{ all -> 0x00b6 }
        r0.j = r1;	 Catch:{ all -> 0x00b6 }
        monitor-exit(r5);	 Catch:{ all -> 0x00b6 }
        r2 = r0.d;
        r3 = r0.e;
        android.opengl.GLES20.glViewport(r1, r1, r2, r3);
        if (r7 == 0) goto L_0x00af;
    L_0x005a:
        r7 = r0.h;	 Catch:{ Exception -> 0x0072 }
        if (r7 == 0) goto L_0x0072;
    L_0x0060:
        r7 = r0.h;	 Catch:{ Exception -> 0x0072 }
        r7.updateTexImage();	 Catch:{ Exception -> 0x0072 }
        r7 = r0.h;	 Catch:{ Exception -> 0x0072 }
        r1 = r0.c;	 Catch:{ Exception -> 0x0072 }
        r7.getTransformMatrix(r1);	 Catch:{ Exception -> 0x0072 }
    L_0x0072:
        r7 = r0.g;
        if (r7 == 0) goto L_0x00a2;
    L_0x0078:
        r7 = r6.y();
        if (r7 != 0) goto L_0x008e;
    L_0x007e:
        r7 = r0.g;
        r1 = r6.x();
        r0 = r0.c;
        r7.a(r1, r0, r6);
        goto L_0x00af;
    L_0x008e:
        r7 = r0.g;
        r1 = r0.f;
        r1 = r1.a();
        r0 = r0.c;
        r7.a(r1, r0, r6);
        goto L_0x00af;
    L_0x00a2:
        r6 = r5.h;
        if (r6 == 0) goto L_0x00af;
    L_0x00a6:
        r6 = r5.h;
        r7 = r0.h;
        r6.a(r7);
    L_0x00af:
        r6 = 1;
        return r6;
    L_0x00b1:
        r0.k = r6;	 Catch:{ all -> 0x00b6 }
        monitor-exit(r5);	 Catch:{ all -> 0x00b6 }
        return r1;
    L_0x00b6:
        r6 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x00b6 }
        throw r6;
    L_0x00b9:
        r7 = r0.g;
        if (r7 == 0) goto L_0x00e8;
    L_0x00bf:
        r7 = r6.y();
        if (r7 != 0) goto L_0x00d5;
    L_0x00c5:
        r7 = r0.g;
        r2 = r6.x();
        r0 = r0.c;
        r7.a(r2, r0, r6);
        goto L_0x00e8;
    L_0x00d5:
        r7 = r0.g;
        r2 = r0.f;
        r2 = r2.a();
        r0 = r0.c;
        r7.a(r2, r0, r6);
    L_0x00e8:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.b.i.b(com.tencent.liteav.d.e, int):boolean");
    }
}

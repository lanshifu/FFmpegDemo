package com.tencent.liteav.g;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import com.tencent.liteav.basic.e.c;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.d.g;
import com.tencent.liteav.e.o;
import java.util.ArrayList;
import java.util.List;

/* compiled from: VideoJoinGLGenerate */
public class n {
    private final String a = "VideoJoinGLGenerate";
    private ArrayList<Surface> b = new ArrayList();
    private Handler c;
    private HandlerThread d = new HandlerThread("VideoJoinGLGenerate");
    private int e;
    private int f;
    private c g;
    private com.tencent.liteav.renderer.c h;
    private o i;
    private d j;
    private boolean k;
    private j l;

    public n() {
        this.d.start();
        this.c = new Handler(this.d.getLooper());
    }

    public void a(g gVar) {
        this.e = gVar.a;
        this.f = gVar.b;
    }

    public void a(j jVar) {
        this.l = jVar;
    }

    public void a(d dVar) {
        this.j = dVar;
    }

    public void a(o oVar) {
        this.i = oVar;
    }

    public void a() {
        TXCLog.d("VideoJoinGLGenerate", "start");
        if (this.c != null) {
            this.c.post(new Runnable() {
                public void run() {
                    n.this.e();
                    n.this.c();
                }
            });
        }
    }

    public void b() {
        TXCLog.d("VideoJoinGLGenerate", "stop");
        if (this.c != null) {
            this.c.post(new Runnable() {
                public void run() {
                    n.this.d();
                    n.this.f();
                }
            });
        }
    }

    private void c() {
        TXCLog.d("VideoJoinGLGenerate", "initTextureRender");
        int i = 0;
        this.h = new com.tencent.liteav.renderer.c(false);
        this.h.b();
        List a = this.l.a();
        while (i < a.size()) {
            final i iVar = (i) a.get(i);
            final k kVar = new k();
            kVar.e = new float[16];
            kVar.a = new com.tencent.liteav.renderer.c(true);
            kVar.a.b();
            kVar.b = new SurfaceTexture(kVar.a.a());
            kVar.c = new Surface(kVar.b);
            kVar.b.setOnFrameAvailableListener(new OnFrameAvailableListener() {
                public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                    kVar.d = true;
                    if (kVar.f != null) {
                        n.this.b(kVar.f, iVar);
                        kVar.f = null;
                    }
                }
            });
            iVar.b = kVar;
            this.b.add(kVar.c);
            i++;
        }
        this.k = true;
        if (this.j != null) {
            this.j.a(this.b);
        }
        if (this.i != null) {
            this.i.a(this.g.d());
        }
    }

    private void d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("destroyTextureRender mVideoExtractListConfig:");
        stringBuilder.append(this.l);
        TXCLog.i("VideoJoinGLGenerate", stringBuilder.toString());
        int i = 0;
        this.k = false;
        if (this.l != null) {
            List a = this.l.a();
            while (i < a.size()) {
                k kVar = ((i) a.get(i)).b;
                if (kVar.a != null) {
                    kVar.a.c();
                }
                kVar.a = null;
                if (kVar.b != null) {
                    kVar.b.setOnFrameAvailableListener(null);
                    kVar.b.release();
                }
                kVar.b = null;
                if (kVar.c != null) {
                    kVar.c.release();
                }
                kVar.c = null;
                i++;
            }
        }
        if (this.h != null) {
            this.h.c();
        }
        this.h = null;
    }

    private void e() {
        TXCLog.d("VideoJoinGLGenerate", "initEGL");
        this.g = c.a(null, null, null, this.e, this.f);
    }

    private void f() {
        TXCLog.d("VideoJoinGLGenerate", "destroyEGL");
        if (this.j != null) {
            this.j.b(this.b);
        }
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
    }

    public synchronized void a(final e eVar, final i iVar) {
        if (this.c != null) {
            this.c.post(new Runnable() {
                public void run() {
                    n.this.b(eVar, iVar);
                }
            });
        }
    }

    /* JADX WARNING: Missing block: B:18:0x003c, code skipped:
            android.opengl.GLES20.glViewport(0, 0, r4.e, r4.f);
     */
    /* JADX WARNING: Missing block: B:19:0x0043, code skipped:
            if (r0 == false) goto L_0x0084;
     */
    /* JADX WARNING: Missing block: B:22:0x0047, code skipped:
            if (r6.b == null) goto L_0x0055;
     */
    /* JADX WARNING: Missing block: B:23:0x0049, code skipped:
            r6.b.updateTexImage();
            r6.b.getTransformMatrix(r6.e);
     */
    private boolean b(com.tencent.liteav.d.e r5, com.tencent.liteav.g.i r6) {
        /*
        r4 = this;
        r0 = r4.k;
        r1 = 0;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r6 = r6.b;
        r0 = r5.p();
        if (r0 == 0) goto L_0x0032;
    L_0x000e:
        r0 = r4.j;
        if (r0 == 0) goto L_0x0031;
    L_0x0012:
        r0 = r5.y();
        if (r0 != 0) goto L_0x0024;
    L_0x0018:
        r0 = r4.j;
        r2 = r5.x();
        r6 = r6.e;
        r0.a(r2, r6, r5);
        goto L_0x0031;
    L_0x0024:
        r0 = r4.j;
        r2 = r6.a;
        r2 = r2.a();
        r6 = r6.e;
        r0.a(r2, r6, r5);
    L_0x0031:
        return r1;
    L_0x0032:
        monitor-enter(r4);
        r0 = r6.d;	 Catch:{ all -> 0x008a }
        if (r0 == 0) goto L_0x0086;
    L_0x0037:
        r0 = r6.d;	 Catch:{ all -> 0x008a }
        r6.d = r1;	 Catch:{ all -> 0x008a }
        monitor-exit(r4);	 Catch:{ all -> 0x008a }
        r2 = r4.e;
        r3 = r4.f;
        android.opengl.GLES20.glViewport(r1, r1, r2, r3);
        if (r0 == 0) goto L_0x0084;
    L_0x0045:
        r0 = r6.b;	 Catch:{ Exception -> 0x0055 }
        if (r0 == 0) goto L_0x0055;
    L_0x0049:
        r0 = r6.b;	 Catch:{ Exception -> 0x0055 }
        r0.updateTexImage();	 Catch:{ Exception -> 0x0055 }
        r0 = r6.b;	 Catch:{ Exception -> 0x0055 }
        r1 = r6.e;	 Catch:{ Exception -> 0x0055 }
        r0.getTransformMatrix(r1);	 Catch:{ Exception -> 0x0055 }
    L_0x0055:
        r0 = r4.j;
        if (r0 == 0) goto L_0x0079;
    L_0x0059:
        r0 = r5.y();
        if (r0 != 0) goto L_0x006b;
    L_0x005f:
        r0 = r4.j;
        r1 = r5.x();
        r6 = r6.e;
        r0.a(r1, r6, r5);
        goto L_0x0084;
    L_0x006b:
        r0 = r4.j;
        r1 = r6.a;
        r1 = r1.a();
        r6 = r6.e;
        r0.a(r1, r6, r5);
        goto L_0x0084;
    L_0x0079:
        r5 = r4.h;
        if (r5 == 0) goto L_0x0084;
    L_0x007d:
        r5 = r4.h;
        r6 = r6.b;
        r5.a(r6);
    L_0x0084:
        r5 = 1;
        return r5;
    L_0x0086:
        r6.f = r5;	 Catch:{ all -> 0x008a }
        monitor-exit(r4);	 Catch:{ all -> 0x008a }
        return r1;
    L_0x008a:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x008a }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.g.n.b(com.tencent.liteav.d.e, com.tencent.liteav.g.i):boolean");
    }
}

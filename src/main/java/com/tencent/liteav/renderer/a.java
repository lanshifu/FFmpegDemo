package com.tencent.liteav.renderer;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import com.tencent.liteav.basic.log.TXCLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.microedition.khronos.egl.EGLContext;

/* compiled from: TXCGLRender */
public class a extends f implements OnFrameAvailableListener {
    private final Queue<Runnable> A = new LinkedList();
    h a;
    a b;
    private final int n = 0;
    private final int o = 0;
    private final int p = 0;
    private final int q = 0;
    private Object r = new Object();
    private b s;
    private SurfaceTexture t;
    private c u;
    private boolean v;
    private float[] w = new float[16];
    private c x;
    private ArrayList<Long> y = new ArrayList();
    private TXCYuvTextureRender z;

    /* compiled from: TXCGLRender */
    public interface a {
        void d(int i);
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void a(h hVar) {
        this.a = hVar;
    }

    public void a(a aVar) {
        this.b = aVar;
        if (aVar != null && this.z != null) {
            this.z.setHasFrameBuffer(this.g, this.h);
        }
    }

    public void a(long j, int i, int i2) {
        synchronized (this) {
            this.y.add(Long.valueOf(j));
        }
        super.a(j, i, i2);
    }

    public void a(int i, int i2, int i3, boolean z, int i4) {
        GLES20.glViewport(0, 0, k(), l());
        if (this.x != null) {
            this.x.a(i, z, i4);
        }
        super.a(i, i2, i3, z, i4);
    }

    public SurfaceTexture a() {
        return this.t;
    }

    public EGLContext b() {
        EGLContext a;
        synchronized (this.r) {
            a = this.s != null ? this.s.a() : null;
        }
        return a;
    }

    /* Access modifiers changed, original: protected */
    public void a(SurfaceTexture surfaceTexture) {
        g();
    }

    /* Access modifiers changed, original: protected */
    public void b(SurfaceTexture surfaceTexture) {
        h();
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, int i2) {
        super.a(i, i2);
        if (this.z != null) {
            this.z.setVideoSize(i, i2);
        }
        if (this.u != null) {
            this.u.a(i, i2);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        s();
        if (this.d != null) {
            this.d.a(this.e, this.f);
            this.d.b(this.g, this.h);
        }
        if (this.u != null) {
            this.u.b();
            this.t = new SurfaceTexture(this.u.a());
            this.t.setOnFrameAvailableListener(this);
        }
        if (this.z != null) {
            this.z.createTexture();
        }
        if (!(this.b == null || this.z == null)) {
            this.z.setHasFrameBuffer(this.g, this.h);
        }
        if (this.x != null) {
            this.x.b();
        }
        if (this.l != null) {
            this.l.a(this.t);
        }
    }

    private void s() {
        this.u = new c(true);
        this.z = new TXCYuvTextureRender();
        this.x = new c(false);
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        try {
            if (this.l != null) {
                this.l.b(this.t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.u != null) {
            this.u.c();
            this.u = null;
        }
        if (this.z != null) {
            this.z.onSurfaceDestroy();
            this.z = null;
        }
        if (this.x != null) {
            this.x.c();
            this.x = null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean e() {
        while (a(this.A)) {
        }
        return t();
    }

    /* Access modifiers changed, original: 0000 */
    public SurfaceTexture f() {
        return this.c != null ? this.c.getSurfaceTexture() : null;
    }

    /* Access modifiers changed, original: protected */
    public void g() {
        synchronized (this.r) {
            if (this.s == null) {
                this.s = new b(new WeakReference(this));
                this.s.start();
                TXCLog.w("TXCVideoRender", "play:vrender: start render thread");
            } else {
                TXCLog.w("TXCVideoRender", "play:vrender: render thread is running");
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void h() {
        synchronized (this.r) {
            if (this.s != null) {
                this.s.b();
                this.s = null;
                TXCLog.w("TXCVideoRender", "play:vrender: quit render thread");
            }
        }
        this.t = null;
    }

    /* JADX WARNING: Missing block: B:9:0x0029, code skipped:
            android.opengl.GLES20.glViewport(0, 0, k(), l());
     */
    /* JADX WARNING: Missing block: B:10:0x0038, code skipped:
            if (r10.i != 1) goto L_0x003f;
     */
    /* JADX WARNING: Missing block: B:11:0x003a, code skipped:
            r6 = b();
     */
    /* JADX WARNING: Missing block: B:12:0x003f, code skipped:
            r6 = null;
     */
    /* JADX WARNING: Missing block: B:13:0x0040, code skipped:
            if (r0 == false) goto L_0x007f;
     */
    /* JADX WARNING: Missing block: B:15:0x0044, code skipped:
            if (r10.t == null) goto L_0x0052;
     */
    /* JADX WARNING: Missing block: B:16:0x0046, code skipped:
            r10.t.updateTexImage();
            r10.t.getTransformMatrix(r10.w);
     */
    /* JADX WARNING: Missing block: B:18:0x0054, code skipped:
            if (r10.a == null) goto L_0x0064;
     */
    /* JADX WARNING: Missing block: B:19:0x0056, code skipped:
            r10.a.a(r10.u.a(), r10.w);
     */
    /* JADX WARNING: Missing block: B:21:0x0066, code skipped:
            if (r10.u == null) goto L_0x006f;
     */
    /* JADX WARNING: Missing block: B:22:0x0068, code skipped:
            r10.u.a(r10.t);
     */
    /* JADX WARNING: Missing block: B:24:0x0071, code skipped:
            if (r10.i != 1) goto L_0x00c3;
     */
    /* JADX WARNING: Missing block: B:25:0x0073, code skipped:
            a(r6, r10.u.a(), r10.w, true);
     */
    /* JADX WARNING: Missing block: B:27:0x0081, code skipped:
            if (r4 == 0) goto L_0x00c3;
     */
    /* JADX WARNING: Missing block: B:29:0x0085, code skipped:
            if (r10.z == null) goto L_0x00c3;
     */
    /* JADX WARNING: Missing block: B:31:0x008a, code skipped:
            if (r10.b == null) goto L_0x00a1;
     */
    /* JADX WARNING: Missing block: B:32:0x008c, code skipped:
            r10.z.setHasFrameBuffer(r10.g, r10.h);
            r0 = r10.z.drawToTexture(r4);
            r10.b.d(r0);
     */
    /* JADX WARNING: Missing block: B:34:0x00a3, code skipped:
            if (r10.i != 0) goto L_0x00aa;
     */
    /* JADX WARNING: Missing block: B:35:0x00a5, code skipped:
            r10.z.drawFrame(r4);
     */
    /* JADX WARNING: Missing block: B:36:0x00aa, code skipped:
            r0 = -1;
     */
    /* JADX WARNING: Missing block: B:38:0x00ad, code skipped:
            if (r10.i != 1) goto L_0x00c3;
     */
    /* JADX WARNING: Missing block: B:39:0x00af, code skipped:
            if (r0 != -1) goto L_0x00c0;
     */
    /* JADX WARNING: Missing block: B:40:0x00b1, code skipped:
            r10.z.setHasFrameBuffer(r10.g, r10.h);
            r0 = r10.z.drawToTexture(r4);
     */
    /* JADX WARNING: Missing block: B:41:0x00c0, code skipped:
            a(r6, r0, null, false);
     */
    /* JADX WARNING: Missing block: B:42:0x00c3, code skipped:
            return true;
     */
    private boolean t() {
        /*
        r10 = this;
        monitor-enter(r10);
        r0 = r10.v;	 Catch:{ all -> 0x00c6 }
        r1 = 0;
        r3 = 0;
        if (r0 == 0) goto L_0x000e;
    L_0x0008:
        r0 = r10.v;	 Catch:{ all -> 0x00c6 }
        r10.v = r3;	 Catch:{ all -> 0x00c6 }
        r4 = r1;
        goto L_0x0028;
    L_0x000e:
        r0 = r10.y;	 Catch:{ all -> 0x00c6 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x00c6 }
        if (r0 != 0) goto L_0x00c4;
    L_0x0016:
        r0 = r10.y;	 Catch:{ all -> 0x00c6 }
        r0 = r0.get(r3);	 Catch:{ all -> 0x00c6 }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x00c6 }
        r4 = r0.longValue();	 Catch:{ all -> 0x00c6 }
        r0 = r10.y;	 Catch:{ all -> 0x00c6 }
        r0.remove(r3);	 Catch:{ all -> 0x00c6 }
        r0 = 0;
    L_0x0028:
        monitor-exit(r10);	 Catch:{ all -> 0x00c6 }
        r6 = r10.k();
        r7 = r10.l();
        android.opengl.GLES20.glViewport(r3, r3, r6, r7);
        r6 = r10.i;
        r7 = 0;
        r8 = 1;
        if (r6 != r8) goto L_0x003f;
    L_0x003a:
        r6 = r10.b();
        goto L_0x0040;
    L_0x003f:
        r6 = r7;
    L_0x0040:
        if (r0 == 0) goto L_0x007f;
    L_0x0042:
        r0 = r10.t;
        if (r0 == 0) goto L_0x0052;
    L_0x0046:
        r0 = r10.t;
        r0.updateTexImage();
        r0 = r10.t;
        r1 = r10.w;
        r0.getTransformMatrix(r1);
    L_0x0052:
        r0 = r10.a;
        if (r0 == 0) goto L_0x0064;
    L_0x0056:
        r0 = r10.a;
        r1 = r10.u;
        r1 = r1.a();
        r2 = r10.w;
        r0.a(r1, r2);
        goto L_0x006f;
    L_0x0064:
        r0 = r10.u;
        if (r0 == 0) goto L_0x006f;
    L_0x0068:
        r0 = r10.u;
        r1 = r10.t;
        r0.a(r1);
    L_0x006f:
        r0 = r10.i;
        if (r0 != r8) goto L_0x00c3;
    L_0x0073:
        r0 = r10.u;
        r0 = r0.a();
        r1 = r10.w;
        r10.a(r6, r0, r1, r8);
        goto L_0x00c3;
    L_0x007f:
        r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1));
        if (r0 == 0) goto L_0x00c3;
    L_0x0083:
        r0 = r10.z;
        if (r0 == 0) goto L_0x00c3;
    L_0x0087:
        r0 = r10.b;
        r1 = -1;
        if (r0 == 0) goto L_0x00a1;
    L_0x008c:
        r0 = r10.z;
        r2 = r10.g;
        r9 = r10.h;
        r0.setHasFrameBuffer(r2, r9);
        r0 = r10.z;
        r0 = r0.drawToTexture(r4);
        r2 = r10.b;
        r2.d(r0);
        goto L_0x00ab;
    L_0x00a1:
        r0 = r10.i;
        if (r0 != 0) goto L_0x00aa;
    L_0x00a5:
        r0 = r10.z;
        r0.drawFrame(r4);
    L_0x00aa:
        r0 = -1;
    L_0x00ab:
        r2 = r10.i;
        if (r2 != r8) goto L_0x00c3;
    L_0x00af:
        if (r0 != r1) goto L_0x00c0;
    L_0x00b1:
        r0 = r10.z;
        r1 = r10.g;
        r2 = r10.h;
        r0.setHasFrameBuffer(r1, r2);
        r0 = r10.z;
        r0 = r0.drawToTexture(r4);
    L_0x00c0:
        r10.a(r6, r0, r7, r3);
    L_0x00c3:
        return r8;
    L_0x00c4:
        monitor-exit(r10);	 Catch:{ all -> 0x00c6 }
        return r3;
    L_0x00c6:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x00c6 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.a.t():boolean");
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
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.a.a(java.util.Queue):boolean");
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        synchronized (this) {
            this.v = true;
        }
    }

    public void finalize() throws Throwable {
        super.finalize();
        TXCLog.w("TXCVideoRender", "play:vrender: quit render thread when finalize");
        try {
            h();
        } catch (Exception unused) {
        }
    }
}

package com.tencent.liteav.e;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.widget.FrameLayout;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.f.d;
import com.tencent.liteav.i.a.f;
import com.tencent.liteav.renderer.c;

/* compiled from: VideoGLRender */
public class ab {
    private final Context a;
    private float[] b;
    private d c;
    private m d;
    private FrameLayout e;
    private TextureView f;
    private int g;
    private int h;
    private Handler i;
    private HandlerThread j;
    private c k;
    private c l;
    private SurfaceTexture m;
    private SurfaceTexture n;
    private Surface o;
    private boolean p;
    private e q;
    private boolean r;
    private boolean s;
    private SurfaceTextureListener t = new SurfaceTextureListener() {
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onSurfaceTextureAvailable surface:");
            stringBuilder.append(surfaceTexture);
            stringBuilder.append(",width:");
            stringBuilder.append(i);
            stringBuilder.append(",height:");
            stringBuilder.append(i2);
            stringBuilder.append(", mSaveSurfaceTexture = ");
            stringBuilder.append(ab.this.n);
            TXCLog.d("VideoGLRender", stringBuilder.toString());
            ab.this.g = i;
            ab.this.h = i2;
            if (ab.this.n == null) {
                ab.this.n = surfaceTexture;
                ab.this.a(surfaceTexture);
            } else if (VERSION.SDK_INT >= 16) {
                ab.this.f.setSurfaceTexture(ab.this.n);
            }
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("wonSurfaceTextureSizeChanged surface:");
            stringBuilder.append(surfaceTexture);
            stringBuilder.append(",width:");
            stringBuilder.append(i);
            stringBuilder.append(",height:");
            stringBuilder.append(i2);
            TXCLog.d("VideoGLRender", stringBuilder.toString());
            ab.this.g = i;
            ab.this.h = i2;
            if (ab.this.d != null) {
                ab.this.d.a(i, i2);
            }
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onSurfaceTextureDestroyed surface:");
            stringBuilder.append(surfaceTexture);
            TXCLog.d("VideoGLRender", stringBuilder.toString());
            if (!ab.this.r) {
                ab.this.n = null;
                ab.this.b(false);
            }
            return false;
        }
    };
    private OnFrameAvailableListener u = new OnFrameAvailableListener() {
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            synchronized (this) {
                ab.this.p = true;
            }
        }
    };

    public ab(Context context) {
        this.a = context;
        this.b = new float[16];
        this.c = new d();
        this.j = new HandlerThread("VideoGLRender");
        this.j.start();
        this.i = new Handler(this.j.getLooper());
    }

    public void a(m mVar) {
        this.d = mVar;
    }

    public void a(f fVar) {
        if (this.e != null) {
            this.e.removeAllViews();
        }
        FrameLayout frameLayout = fVar.a;
        if (frameLayout == null) {
            TXCLog.w("VideoGLRender", "initWithPreview param.videoView is NULL!!!");
            return;
        }
        if (this.e == null || !frameLayout.equals(this.e)) {
            this.f = new TextureView(this.a);
            this.f.setSurfaceTextureListener(this.t);
        }
        this.e = frameLayout;
        this.e.addView(this.f);
    }

    public int a() {
        return this.g;
    }

    public int b() {
        return this.h;
    }

    public void c() {
        this.r = true;
    }

    public void d() {
        this.r = false;
    }

    public void e() {
        this.r = false;
        b(true);
        if (this.f != null) {
            this.f.setSurfaceTextureListener(null);
            this.f = null;
        }
        if (this.e != null) {
            this.e.removeAllViews();
            this.e = null;
        }
        if (this.d != null) {
            this.d = null;
        }
        this.t = null;
        this.u = null;
    }

    private void a(final SurfaceTexture surfaceTexture) {
        if (this.i != null) {
            this.i.post(new Runnable() {
                public void run() {
                    ab.this.c.a(surfaceTexture);
                    ab.this.f();
                    if (ab.this.d != null) {
                        ab.this.d.a(ab.this.o);
                    }
                }
            });
        }
    }

    private void b(final boolean z) {
        if (this.i != null) {
            this.i.post(new Runnable() {
                public void run() {
                    try {
                        if (ab.this.i != null) {
                            if (ab.this.d != null) {
                                ab.this.d.b(ab.this.o);
                            }
                            ab.this.g();
                            ab.this.c.a();
                            if (z) {
                                ab.this.i = null;
                                if (ab.this.j != null) {
                                    ab.this.j.quit();
                                    ab.this.j = null;
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void f() {
        this.k = new c(true);
        this.k.b();
        this.l = new c(false);
        this.l.b();
        this.m = new SurfaceTexture(this.k.a());
        this.o = new Surface(this.m);
        this.m.setOnFrameAvailableListener(this.u);
        this.s = true;
    }

    private void g() {
        this.s = false;
        if (this.k != null) {
            this.k.c();
        }
        this.k = null;
        if (this.l != null) {
            this.l.c();
        }
        this.l = null;
        if (this.m != null) {
            this.m.setOnFrameAvailableListener(null);
            this.m.release();
            this.m = null;
        }
        if (this.o != null) {
            this.o.release();
            this.o = null;
        }
    }

    public void a(int i, int i2, int i3) {
        GLES20.glViewport(0, 0, i2, i3);
        if (this.l != null) {
            this.l.a(i, false, 0);
        }
    }

    public void a(final e eVar) {
        if (this.i != null) {
            this.i.post(new Runnable() {
                public void run() {
                    if (ab.this.c(eVar)) {
                        ab.this.c.b();
                    }
                }
            });
        }
    }

    public void a(final boolean z) {
        if (this.i != null) {
            this.i.post(new Runnable() {
                public void run() {
                    if (ab.this.d != null) {
                        ab.this.d.a(z);
                        ab.this.c.b();
                    }
                }
            });
        }
    }

    public void b(final e eVar) {
        if (this.i != null) {
            this.i.post(new Runnable() {
                public void run() {
                    ab.this.p = true;
                    ab.this.c(eVar);
                    ab.this.c.b();
                }
            });
        }
    }

    /* JADX WARNING: Missing block: B:19:0x0043, code skipped:
            android.opengl.GLES20.glViewport(0, 0, r4.g, r4.h);
     */
    /* JADX WARNING: Missing block: B:20:0x004a, code skipped:
            if (r0 == false) goto L_0x008b;
     */
    /* JADX WARNING: Missing block: B:22:0x004e, code skipped:
            if (r4.m == null) goto L_0x005c;
     */
    /* JADX WARNING: Missing block: B:23:0x0050, code skipped:
            r4.m.updateTexImage();
            r4.m.getTransformMatrix(r4.b);
     */
    /* JADX WARNING: Missing block: B:25:0x005e, code skipped:
            if (r4.d == null) goto L_0x0080;
     */
    /* JADX WARNING: Missing block: B:27:0x0064, code skipped:
            if (r5.y() != 0) goto L_0x0072;
     */
    /* JADX WARNING: Missing block: B:28:0x0066, code skipped:
            r4.d.a(r5.x(), r4.b, r5);
     */
    /* JADX WARNING: Missing block: B:29:0x0072, code skipped:
            r4.d.a(r4.k.a(), r4.b, r5);
     */
    /* JADX WARNING: Missing block: B:31:0x0082, code skipped:
            if (r4.l == null) goto L_0x008b;
     */
    /* JADX WARNING: Missing block: B:32:0x0084, code skipped:
            r4.l.a(r4.m);
     */
    /* JADX WARNING: Missing block: B:34:0x008c, code skipped:
            return true;
     */
    private boolean c(com.tencent.liteav.d.e r5) {
        /*
        r4 = this;
        r0 = r4.s;
        r1 = 0;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = r5.p();
        if (r0 == 0) goto L_0x0037;
    L_0x000c:
        r0 = "VideoGLRender";
        r2 = "onDrawFrame, frame isEndFrame";
        com.tencent.liteav.basic.log.TXCLog.d(r0, r2);
        r0 = r4.d;
        if (r0 == 0) goto L_0x0036;
    L_0x0017:
        r0 = r5.y();
        if (r0 != 0) goto L_0x0029;
    L_0x001d:
        r0 = r4.d;
        r2 = r5.x();
        r3 = r4.b;
        r0.a(r2, r3, r5);
        goto L_0x0036;
    L_0x0029:
        r0 = r4.d;
        r2 = r4.k;
        r2 = r2.a();
        r3 = r4.b;
        r0.a(r2, r3, r5);
    L_0x0036:
        return r1;
    L_0x0037:
        r4.q = r5;
        monitor-enter(r4);
        r0 = r4.p;	 Catch:{ all -> 0x008f }
        if (r0 == 0) goto L_0x008d;
    L_0x003e:
        r0 = r4.p;	 Catch:{ all -> 0x008f }
        r4.p = r1;	 Catch:{ all -> 0x008f }
        monitor-exit(r4);	 Catch:{ all -> 0x008f }
        r2 = r4.g;
        r3 = r4.h;
        android.opengl.GLES20.glViewport(r1, r1, r2, r3);
        if (r0 == 0) goto L_0x008b;
    L_0x004c:
        r0 = r4.m;
        if (r0 == 0) goto L_0x005c;
    L_0x0050:
        r0 = r4.m;
        r0.updateTexImage();
        r0 = r4.m;
        r1 = r4.b;
        r0.getTransformMatrix(r1);
    L_0x005c:
        r0 = r4.d;
        if (r0 == 0) goto L_0x0080;
    L_0x0060:
        r0 = r5.y();
        if (r0 != 0) goto L_0x0072;
    L_0x0066:
        r0 = r4.d;
        r1 = r5.x();
        r2 = r4.b;
        r0.a(r1, r2, r5);
        goto L_0x008b;
    L_0x0072:
        r0 = r4.d;
        r1 = r4.k;
        r1 = r1.a();
        r2 = r4.b;
        r0.a(r1, r2, r5);
        goto L_0x008b;
    L_0x0080:
        r5 = r4.l;
        if (r5 == 0) goto L_0x008b;
    L_0x0084:
        r5 = r4.l;
        r0 = r4.m;
        r5.a(r0);
    L_0x008b:
        r5 = 1;
        return r5;
    L_0x008d:
        monitor-exit(r4);	 Catch:{ all -> 0x008f }
        return r1;
    L_0x008f:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x008f }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.e.ab.c(com.tencent.liteav.d.e):boolean");
    }
}

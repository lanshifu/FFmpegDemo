package com.tencent.liteav.g;

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
import java.util.ArrayList;
import java.util.List;

/* compiled from: VideoJoinGLRender */
public class o {
    private Context a;
    private d b;
    private d c;
    private FrameLayout d;
    private TextureView e;
    private int f;
    private int g;
    private Handler h;
    private HandlerThread i;
    private c j;
    private SurfaceTexture k;
    private boolean l;
    private boolean m;
    private ArrayList<Surface> n;
    private SurfaceTextureListener o = new SurfaceTextureListener() {
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
            stringBuilder.append(o.this.k);
            TXCLog.d("VideoJoinGLRender", stringBuilder.toString());
            o.this.f = i;
            o.this.g = i2;
            if (o.this.k == null) {
                o.this.k = surfaceTexture;
                o.this.a(surfaceTexture);
            } else if (VERSION.SDK_INT >= 16) {
                o.this.e.setSurfaceTexture(o.this.k);
            }
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onSurfaceTextureSizeChanged surface:");
            stringBuilder.append(surfaceTexture);
            stringBuilder.append(",width:");
            stringBuilder.append(i);
            stringBuilder.append(",height:");
            stringBuilder.append(i2);
            TXCLog.d("VideoJoinGLRender", stringBuilder.toString());
            o.this.f = i;
            o.this.g = i2;
            if (o.this.c != null) {
                o.this.c.a(i, i2);
            }
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onSurfaceTextureDestroyed surface:");
            stringBuilder.append(surfaceTexture);
            TXCLog.d("VideoJoinGLRender", stringBuilder.toString());
            if (!o.this.l) {
                o.this.k = null;
                o.this.a(false);
            }
            return false;
        }
    };

    public o(Context context) {
        this.a = context;
        this.n = new ArrayList();
        this.b = new d();
        this.i = new HandlerThread("VideoJoinGLRender");
        this.i.start();
        this.h = new Handler(this.i.getLooper());
    }

    public void a(d dVar) {
        this.c = dVar;
    }

    public void a(f fVar) {
        if (this.d != null) {
            this.d.removeAllViews();
        }
        FrameLayout frameLayout = fVar.a;
        if (frameLayout == null) {
            TXCLog.w("VideoJoinGLRender", "initWithPreview param.videoView is NULL!!!");
            return;
        }
        if (this.d == null || !frameLayout.equals(this.d)) {
            this.e = new TextureView(this.a);
            this.e.setSurfaceTextureListener(this.o);
        }
        this.d = frameLayout;
        this.d.addView(this.e);
    }

    public int a() {
        return this.f;
    }

    public int b() {
        return this.g;
    }

    public void c() {
        this.l = true;
    }

    public void d() {
        this.l = false;
    }

    private void a(final SurfaceTexture surfaceTexture) {
        if (this.h != null) {
            this.h.post(new Runnable() {
                public void run() {
                    o.this.b.a(surfaceTexture);
                    o.this.e();
                    if (o.this.c != null) {
                        o.this.c.a(o.this.n);
                    }
                }
            });
        }
    }

    private void a(final boolean z) {
        if (this.h != null) {
            this.h.post(new Runnable() {
                public void run() {
                    try {
                        if (o.this.h != null) {
                            if (o.this.c != null) {
                                o.this.c.b(o.this.n);
                            }
                            o.this.f();
                            o.this.b.a();
                            if (z) {
                                o.this.h = null;
                                if (o.this.i != null) {
                                    o.this.i.quit();
                                    o.this.i = null;
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

    private void e() {
        TXCLog.i("VideoJoinGLRender", "initTextureRender");
        int i = 0;
        this.j = new c(false);
        this.j.b();
        List c = t.a().c();
        while (i < c.size()) {
            final i iVar = (i) c.get(i);
            final k kVar = new k();
            kVar.e = new float[16];
            kVar.a = new c(true);
            kVar.a.b();
            kVar.b = new SurfaceTexture(kVar.a.a());
            kVar.c = new Surface(kVar.b);
            kVar.b.setOnFrameAvailableListener(new OnFrameAvailableListener() {
                public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                    kVar.d = true;
                    if (kVar.f != null && o.this.b(kVar.f, iVar)) {
                        kVar.f = null;
                        o.this.b.b();
                    }
                }
            });
            iVar.b = kVar;
            this.n.add(kVar.c);
            i++;
        }
        this.m = true;
    }

    private void f() {
        TXCLog.i("VideoJoinGLRender", "destroyTextureRender");
        int i = 0;
        this.m = false;
        List c = t.a().c();
        while (i < c.size()) {
            k kVar = ((i) c.get(i)).b;
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
        if (this.j != null) {
            this.j.c();
        }
        this.j = null;
    }

    public void a(int i, int i2, int i3) {
        GLES20.glViewport(0, 0, i2, i3);
        if (this.j != null) {
            this.j.a(i, false, 0);
        }
    }

    public void a(final e eVar, final i iVar) {
        if (this.h != null) {
            this.h.post(new Runnable() {
                public void run() {
                    if (o.this.b(eVar, iVar)) {
                        o.this.b.b();
                    }
                }
            });
        }
    }

    /* JADX WARNING: Missing block: B:18:0x0043, code skipped:
            android.opengl.GLES20.glViewport(0, 0, r4.f, r4.g);
     */
    /* JADX WARNING: Missing block: B:19:0x004a, code skipped:
            if (r0 == false) goto L_0x008b;
     */
    /* JADX WARNING: Missing block: B:21:0x004e, code skipped:
            if (r6.b == null) goto L_0x005c;
     */
    /* JADX WARNING: Missing block: B:22:0x0050, code skipped:
            r6.b.updateTexImage();
            r6.b.getTransformMatrix(r6.e);
     */
    /* JADX WARNING: Missing block: B:24:0x005e, code skipped:
            if (r4.c == null) goto L_0x0080;
     */
    /* JADX WARNING: Missing block: B:26:0x0064, code skipped:
            if (r5.y() != 0) goto L_0x0072;
     */
    /* JADX WARNING: Missing block: B:27:0x0066, code skipped:
            r4.c.a(r5.x(), r6.e, r5);
     */
    /* JADX WARNING: Missing block: B:28:0x0072, code skipped:
            r4.c.a(r6.a.a(), r6.e, r5);
     */
    /* JADX WARNING: Missing block: B:30:0x0082, code skipped:
            if (r4.j == null) goto L_0x008b;
     */
    /* JADX WARNING: Missing block: B:31:0x0084, code skipped:
            r4.j.a(r6.b);
     */
    /* JADX WARNING: Missing block: B:33:0x008c, code skipped:
            return true;
     */
    private boolean b(com.tencent.liteav.d.e r5, com.tencent.liteav.g.i r6) {
        /*
        r4 = this;
        r0 = r4.m;
        r1 = 0;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r6 = r6.b;
        r0 = r5.p();
        if (r0 == 0) goto L_0x0039;
    L_0x000e:
        r0 = "VideoJoinGLRender";
        r2 = "onDrawFrame, frame isEndFrame";
        com.tencent.liteav.basic.log.TXCLog.d(r0, r2);
        r0 = r4.c;
        if (r0 == 0) goto L_0x0038;
    L_0x0019:
        r0 = r5.y();
        if (r0 != 0) goto L_0x002b;
    L_0x001f:
        r0 = r4.c;
        r2 = r5.x();
        r6 = r6.e;
        r0.a(r2, r6, r5);
        goto L_0x0038;
    L_0x002b:
        r0 = r4.c;
        r2 = r6.a;
        r2 = r2.a();
        r6 = r6.e;
        r0.a(r2, r6, r5);
    L_0x0038:
        return r1;
    L_0x0039:
        monitor-enter(r4);
        r0 = r6.d;	 Catch:{ all -> 0x0091 }
        if (r0 == 0) goto L_0x008d;
    L_0x003e:
        r0 = r6.d;	 Catch:{ all -> 0x0091 }
        r6.d = r1;	 Catch:{ all -> 0x0091 }
        monitor-exit(r4);	 Catch:{ all -> 0x0091 }
        r2 = r4.f;
        r3 = r4.g;
        android.opengl.GLES20.glViewport(r1, r1, r2, r3);
        if (r0 == 0) goto L_0x008b;
    L_0x004c:
        r0 = r6.b;
        if (r0 == 0) goto L_0x005c;
    L_0x0050:
        r0 = r6.b;
        r0.updateTexImage();
        r0 = r6.b;
        r1 = r6.e;
        r0.getTransformMatrix(r1);
    L_0x005c:
        r0 = r4.c;
        if (r0 == 0) goto L_0x0080;
    L_0x0060:
        r0 = r5.y();
        if (r0 != 0) goto L_0x0072;
    L_0x0066:
        r0 = r4.c;
        r1 = r5.x();
        r6 = r6.e;
        r0.a(r1, r6, r5);
        goto L_0x008b;
    L_0x0072:
        r0 = r4.c;
        r1 = r6.a;
        r1 = r1.a();
        r6 = r6.e;
        r0.a(r1, r6, r5);
        goto L_0x008b;
    L_0x0080:
        r5 = r4.j;
        if (r5 == 0) goto L_0x008b;
    L_0x0084:
        r5 = r4.j;
        r6 = r6.b;
        r5.a(r6);
    L_0x008b:
        r5 = 1;
        return r5;
    L_0x008d:
        r6.f = r5;	 Catch:{ all -> 0x0091 }
        monitor-exit(r4);	 Catch:{ all -> 0x0091 }
        return r1;
    L_0x0091:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0091 }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.g.o.b(com.tencent.liteav.d.e, com.tencent.liteav.g.i):boolean");
    }
}

package com.tencent.liteav.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import com.tencent.liteav.basic.d.a;
import com.tencent.liteav.basic.e.g;
import com.tencent.liteav.basic.e.i;
import com.tencent.liteav.basic.e.j;
import com.tencent.liteav.basic.e.k;
import com.tencent.liteav.basic.e.l;
import com.tencent.liteav.basic.e.m;
import com.tencent.liteav.basic.e.n;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.b;
import com.tencent.rtmp.TXLiveConstants;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

public class TXCGLSurfaceView extends TXCGLSurfaceViewBase implements OnFrameAvailableListener, Renderer, l {
    private boolean A;
    private n B;
    private int C;
    private int D;
    private int E;
    private m F;
    private final Queue<Runnable> G;
    WeakReference<a> a;
    private SurfaceTexture g;
    private EGLContext h;
    private g i;
    private boolean j;
    private int[] k;
    private float[] l;
    private int m;
    private boolean n;
    private float o;
    private float p;
    private int q;
    private long r;
    private long s;
    private int t;
    private boolean u;
    private boolean v;
    private Object w;
    private Handler x;
    private int y;
    private int z;

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
    }

    public TXCGLSurfaceView(Context context) {
        super(context);
        this.j = false;
        this.l = new float[16];
        this.m = 0;
        this.n = false;
        this.o = 1.0f;
        this.p = 1.0f;
        this.q = 20;
        this.r = 0;
        this.s = 0;
        this.t = 12288;
        this.u = true;
        this.v = false;
        this.w = new Object();
        this.y = 0;
        this.z = 0;
        this.A = true;
        this.B = null;
        this.C = 0;
        this.G = new LinkedList();
        setEGLContextClientVersion(2);
        a(8, 8, 8, 8, 16, 0);
        setRenderer(this);
    }

    public TXCGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = false;
        this.l = new float[16];
        this.m = 0;
        this.n = false;
        this.o = 1.0f;
        this.p = 1.0f;
        this.q = 20;
        this.r = 0;
        this.s = 0;
        this.t = 12288;
        this.u = true;
        this.v = false;
        this.w = new Object();
        this.y = 0;
        this.z = 0;
        this.A = true;
        this.B = null;
        this.C = 0;
        this.G = new LinkedList();
        setEGLContextClientVersion(2);
        a(8, 8, 8, 8, 16, 0);
        setRenderer(this);
    }

    public void setFPS(final int i) {
        b(new Runnable() {
            public void run() {
                TXCGLSurfaceView.this.q = i;
                if (TXCGLSurfaceView.this.q <= 0) {
                    TXCGLSurfaceView.this.q = 1;
                } else if (TXCGLSurfaceView.this.q > 60) {
                    TXCGLSurfaceView.this.q = 60;
                }
                TXCGLSurfaceView.this.s = 0;
                TXCGLSurfaceView.this.r = 0;
            }
        });
    }

    public void setRendMode(final int i) {
        b(new Runnable() {
            public void run() {
                TXCGLSurfaceView.this.C = i;
                GLES20.glClearColor(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
                GLES20.glClear(16640);
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        if (this.F != null) {
            this.F.onSurfaceTextureDestroy(null);
        }
    }

    /* Access modifiers changed, original: protected */
    public void setRunInBackground(boolean z) {
        if (z) {
            synchronized (this) {
                TXCLog.d("TXCGLSurfaceView", "background capture enter background");
                this.c = true;
            }
            return;
        }
        b(new Runnable() {
            public void run() {
                synchronized (this) {
                    TXCLog.d("TXCGLSurfaceView", "background capture exit background");
                    TXCGLSurfaceView.this.c = false;
                }
            }
        });
    }

    public void setNotifyListener(a aVar) {
        this.a = new WeakReference(aVar);
    }

    public void a(n nVar) {
        this.B = nVar;
        this.A = true;
    }

    /* JADX WARNING: Missing block: B:10:0x0015, code skipped:
            r5 = getWidth();
            r6 = getHeight();
     */
    /* JADX WARNING: Missing block: B:11:0x0021, code skipped:
            if (r1.C != 0) goto L_0x0028;
     */
    /* JADX WARNING: Missing block: B:12:0x0023, code skipped:
            r1.D = 0;
            r1.E = 0;
     */
    /* JADX WARNING: Missing block: B:14:0x002a, code skipped:
            if (r1.C != 1) goto L_0x0040;
     */
    /* JADX WARNING: Missing block: B:15:0x002c, code skipped:
            r5 = a(r5, r6, r3, r4);
            r6 = r5[0];
            r7 = r5[1];
            r1.D = r5[2];
            r1.E = r5[3];
            r5 = r6;
            r6 = r7;
     */
    /* JADX WARNING: Missing block: B:16:0x0040, code skipped:
            r1.y = r5;
            r1.z = r6;
            android.opengl.GLES20.glViewport(r1.D, r1.E, r5, r6);
            r7 = 1.0f;
     */
    /* JADX WARNING: Missing block: B:17:0x004d, code skipped:
            if (r6 == 0) goto L_0x0053;
     */
    /* JADX WARNING: Missing block: B:18:0x004f, code skipped:
            r9 = ((float) r5) / ((float) r6);
     */
    /* JADX WARNING: Missing block: B:19:0x0053, code skipped:
            r9 = 1.0f;
     */
    /* JADX WARNING: Missing block: B:20:0x0055, code skipped:
            if (r4 == 0) goto L_0x005a;
     */
    /* JADX WARNING: Missing block: B:21:0x0057, code skipped:
            r7 = ((float) r3) / ((float) r4);
     */
    /* JADX WARNING: Missing block: B:23:0x005c, code skipped:
            if (r1.n != r0) goto L_0x006e;
     */
    /* JADX WARNING: Missing block: B:25:0x0060, code skipped:
            if (r1.m != r2) goto L_0x006e;
     */
    /* JADX WARNING: Missing block: B:27:0x0066, code skipped:
            if (r1.o != r9) goto L_0x006e;
     */
    /* JADX WARNING: Missing block: B:29:0x006c, code skipped:
            if (r1.p == r7) goto L_0x00c5;
     */
    /* JADX WARNING: Missing block: B:30:0x006e, code skipped:
            r1.n = r0;
            r1.m = r2;
            r1.o = r9;
            r1.p = r7;
            r0 = (720 - r1.m) % 360;
     */
    /* JADX WARNING: Missing block: B:31:0x007e, code skipped:
            if (r0 == 90) goto L_0x0087;
     */
    /* JADX WARNING: Missing block: B:33:0x0082, code skipped:
            if (r0 != 270) goto L_0x0085;
     */
    /* JADX WARNING: Missing block: B:34:0x0085, code skipped:
            r11 = null;
     */
    /* JADX WARNING: Missing block: B:35:0x0087, code skipped:
            r11 = 1;
     */
    /* JADX WARNING: Missing block: B:36:0x0088, code skipped:
            if (r11 == null) goto L_0x008c;
     */
    /* JADX WARNING: Missing block: B:37:0x008a, code skipped:
            r2 = r6;
     */
    /* JADX WARNING: Missing block: B:38:0x008c, code skipped:
            r2 = r5;
     */
    /* JADX WARNING: Missing block: B:39:0x008d, code skipped:
            if (r11 == null) goto L_0x0090;
     */
    /* JADX WARNING: Missing block: B:40:0x0090, code skipped:
            r5 = r6;
     */
    /* JADX WARNING: Missing block: B:41:0x0091, code skipped:
            r6 = r1.i;
            r7 = com.tencent.liteav.basic.e.k.a(com.tencent.liteav.basic.e.j.NORMAL, false, true);
            r8 = ((float) r2) / ((float) r5);
     */
    /* JADX WARNING: Missing block: B:42:0x009d, code skipped:
            if (r11 == null) goto L_0x00a1;
     */
    /* JADX WARNING: Missing block: B:43:0x009f, code skipped:
            r9 = false;
     */
    /* JADX WARNING: Missing block: B:44:0x00a1, code skipped:
            r9 = r1.n;
     */
    /* JADX WARNING: Missing block: B:45:0x00a4, code skipped:
            if (r11 == null) goto L_0x00aa;
     */
    /* JADX WARNING: Missing block: B:46:0x00a6, code skipped:
            r12 = r1.n;
     */
    /* JADX WARNING: Missing block: B:47:0x00aa, code skipped:
            r12 = false;
     */
    /* JADX WARNING: Missing block: B:48:0x00ab, code skipped:
            r6.a(r17, r18, r0, r7, r8, r9, r12);
     */
    /* JADX WARNING: Missing block: B:49:0x00b8, code skipped:
            if (r11 == null) goto L_0x00c0;
     */
    /* JADX WARNING: Missing block: B:50:0x00ba, code skipped:
            r1.i.g();
     */
    /* JADX WARNING: Missing block: B:51:0x00c0, code skipped:
            r1.i.h();
     */
    /* JADX WARNING: Missing block: B:52:0x00c5, code skipped:
            android.opengl.GLES20.glBindFramebuffer(36160, 0);
            r2 = r14;
            r1.i.b(r14);
     */
    /* JADX WARNING: Missing block: B:53:0x00d1, code skipped:
            return;
     */
    public void a(int r14, boolean r15, int r16, int r17, int r18) {
        /*
        r13 = this;
        r1 = r13;
        r0 = r15;
        r2 = r16;
        r3 = r17;
        r4 = r18;
        r5 = r1.i;
        if (r5 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        monitor-enter(r13);
        r5 = r1.c;	 Catch:{ all -> 0x00d2 }
        if (r5 == 0) goto L_0x0014;
    L_0x0012:
        monitor-exit(r13);	 Catch:{ all -> 0x00d2 }
        return;
    L_0x0014:
        monitor-exit(r13);	 Catch:{ all -> 0x00d2 }
        r5 = r13.getWidth();
        r6 = r13.getHeight();
        r7 = r1.C;
        r8 = 1;
        r10 = 0;
        if (r7 != 0) goto L_0x0028;
    L_0x0023:
        r1.D = r10;
        r1.E = r10;
        goto L_0x0040;
    L_0x0028:
        r7 = r1.C;
        if (r7 != r8) goto L_0x0040;
    L_0x002c:
        r5 = r13.a(r5, r6, r3, r4);
        r6 = r5[r10];
        r7 = r5[r8];
        r9 = 2;
        r9 = r5[r9];
        r1.D = r9;
        r9 = 3;
        r5 = r5[r9];
        r1.E = r5;
        r5 = r6;
        r6 = r7;
    L_0x0040:
        r1.y = r5;
        r1.z = r6;
        r7 = r1.D;
        r9 = r1.E;
        android.opengl.GLES20.glViewport(r7, r9, r5, r6);
        r7 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        if (r6 == 0) goto L_0x0053;
    L_0x004f:
        r9 = (float) r5;
        r11 = (float) r6;
        r9 = r9 / r11;
        goto L_0x0055;
    L_0x0053:
        r9 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
    L_0x0055:
        if (r4 == 0) goto L_0x005a;
    L_0x0057:
        r7 = (float) r3;
        r11 = (float) r4;
        r7 = r7 / r11;
    L_0x005a:
        r11 = r1.n;
        if (r11 != r0) goto L_0x006e;
    L_0x005e:
        r11 = r1.m;
        if (r11 != r2) goto L_0x006e;
    L_0x0062:
        r11 = r1.o;
        r11 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1));
        if (r11 != 0) goto L_0x006e;
    L_0x0068:
        r11 = r1.p;
        r11 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1));
        if (r11 == 0) goto L_0x00c5;
    L_0x006e:
        r1.n = r0;
        r1.m = r2;
        r1.o = r9;
        r1.p = r7;
        r0 = r1.m;
        r0 = 720 - r0;
        r0 = r0 % 360;
        r2 = 90;
        if (r0 == r2) goto L_0x0087;
    L_0x0080:
        r2 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        if (r0 != r2) goto L_0x0085;
    L_0x0084:
        goto L_0x0087;
    L_0x0085:
        r11 = 0;
        goto L_0x0088;
    L_0x0087:
        r11 = 1;
    L_0x0088:
        if (r11 == 0) goto L_0x008c;
    L_0x008a:
        r2 = r6;
        goto L_0x008d;
    L_0x008c:
        r2 = r5;
    L_0x008d:
        if (r11 == 0) goto L_0x0090;
    L_0x008f:
        goto L_0x0091;
    L_0x0090:
        r5 = r6;
    L_0x0091:
        r6 = r1.i;
        r7 = com.tencent.liteav.basic.e.j.NORMAL;
        r7 = com.tencent.liteav.basic.e.k.a(r7, r10, r8);
        r2 = (float) r2;
        r5 = (float) r5;
        r8 = r2 / r5;
        if (r11 == 0) goto L_0x00a1;
    L_0x009f:
        r9 = 0;
        goto L_0x00a4;
    L_0x00a1:
        r2 = r1.n;
        r9 = r2;
    L_0x00a4:
        if (r11 == 0) goto L_0x00aa;
    L_0x00a6:
        r2 = r1.n;
        r12 = r2;
        goto L_0x00ab;
    L_0x00aa:
        r12 = 0;
    L_0x00ab:
        r2 = r6;
        r3 = r17;
        r4 = r18;
        r5 = r0;
        r6 = r7;
        r7 = r8;
        r8 = r9;
        r9 = r12;
        r2.a(r3, r4, r5, r6, r7, r8, r9);
        if (r11 == 0) goto L_0x00c0;
    L_0x00ba:
        r0 = r1.i;
        r0.g();
        goto L_0x00c5;
    L_0x00c0:
        r0 = r1.i;
        r0.h();
    L_0x00c5:
        r0 = 36160; // 0x8d40 float:5.0671E-41 double:1.78654E-319;
        android.opengl.GLES20.glBindFramebuffer(r0, r10);
        r0 = r1.i;
        r2 = r14;
        r0.b(r14);
        return;
    L_0x00d2:
        r0 = move-exception;
        monitor-exit(r13);	 Catch:{ all -> 0x00d2 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.TXCGLSurfaceView.a(int, boolean, int, int, int):void");
    }

    private int[] a(int i, int i2, int i3, int i4) {
        int[] iArr = new int[4];
        float f = (float) i2;
        float f2 = (float) i;
        float f3 = ((float) i4) / ((float) i3);
        if (f / f2 > f3) {
            i3 = (int) (f2 * f3);
            i4 = (i2 - i3) / 2;
            i2 = i3;
            i3 = 0;
        } else {
            i3 = (int) (f / f3);
            i4 = 0;
            int i5 = i3;
            i3 = (i - i3) / 2;
            i = i5;
        }
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = i3;
        iArr[3] = i4;
        return iArr;
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.g;
    }

    public void a(int i) {
        this.q = i;
        this.j = false;
        this.B = null;
        this.A = false;
        c(true);
    }

    public void a() {
        c(false);
    }

    public void setSurfaceTextureListener(m mVar) {
        this.F = mVar;
    }

    public EGLContext getGLContext() {
        return this.h;
    }

    public void a(Runnable runnable) {
        synchronized (this.G) {
            this.G.add(runnable);
        }
    }

    /* Access modifiers changed, original: protected */
    public int c() {
        if (this.t != 12288) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("background capture swapbuffer error : ");
            stringBuilder.append(this.t);
            TXCLog.e("TXCGLSurfaceView", stringBuilder.toString());
        }
        return this.t;
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (VERSION.SDK_INT >= 21 && this.x != null) {
            this.x.getLooper().quitSafely();
        }
    }

    public void b(Runnable runnable) {
        synchronized (this.G) {
            this.G.add(runnable);
        }
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
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.TXCGLSurfaceView.a(java.util.Queue):boolean");
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.h = ((EGL10) EGLContext.getEGL()).eglGetCurrentContext();
        this.k = new int[1];
        this.k[0] = i.b();
        if (this.k[0] <= 0) {
            this.k = null;
            TXCLog.e("TXCGLSurfaceView", "create oes texture error!! at glsurfaceview");
            return;
        }
        this.g = new SurfaceTexture(this.k[0]);
        if (VERSION.SDK_INT >= 21) {
            if (this.x != null) {
                this.x.getLooper().quitSafely();
            }
            HandlerThread handlerThread = new HandlerThread("VideoCaptureThread");
            handlerThread.start();
            this.x = new Handler(handlerThread.getLooper());
            this.g.setOnFrameAvailableListener(this, this.x);
        } else {
            this.g.setOnFrameAvailableListener(this);
        }
        this.i = new g();
        if (this.i.c()) {
            this.i.a(k.e, k.a(j.NORMAL, false, false));
            if (this.F != null) {
                this.F.onSurfaceTextureAvailable(this.g);
            }
        }
    }

    /* JADX WARNING: Missing block: B:27:0x0063, code skipped:
            if (r8.F == null) goto L_0x0070;
     */
    /* JADX WARNING: Missing block: B:28:0x0065, code skipped:
            r8.F.onTextureProcess(r8.k[0], r8.l);
     */
    /* JADX WARNING: Missing block: B:29:0x0070, code skipped:
            f();
     */
    /* JADX WARNING: Missing block: B:30:0x0073, code skipped:
            monitor-enter(r8);
     */
    /* JADX WARNING: Missing block: B:32:?, code skipped:
            r9 = r8.c ^ 1;
     */
    /* JADX WARNING: Missing block: B:33:0x0078, code skipped:
            monitor-exit(r8);
     */
    /* JADX WARNING: Missing block: B:34:0x0079, code skipped:
            if (r9 == 0) goto L_0x008c;
     */
    /* JADX WARNING: Missing block: B:36:?, code skipped:
            r8.t = d();
     */
    public void onDrawFrame(javax.microedition.khronos.opengles.GL10 r9) {
        /*
        r8 = this;
        r9 = r8.G;
        r8.a(r9);
    L_0x0005:
        r0 = java.lang.System.currentTimeMillis();
        r2 = r8.s;
        r4 = 0;
        r9 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r9 != 0) goto L_0x0013;
    L_0x0011:
        r8.s = r0;
    L_0x0013:
        r2 = r8.s;
        r2 = r0 - r2;
        r4 = r8.r;
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r4 * r6;
        r9 = r8.q;
        r6 = (long) r9;
        r4 = r4 / r6;
        r9 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r9 >= 0) goto L_0x0029;
    L_0x0025:
        r8.g();
        goto L_0x0005;
    L_0x0029:
        r2 = r8.r;
        r4 = 1;
        r2 = r2 + r4;
        r8.r = r2;
        r2 = r8.s;
        r0 = r0 - r2;
        r2 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r9 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r9 <= 0) goto L_0x0041;
    L_0x0039:
        r8.r = r4;
        r0 = java.lang.System.currentTimeMillis();
        r8.s = r0;
    L_0x0041:
        r9 = r8.u;
        if (r9 == 0) goto L_0x0046;
    L_0x0045:
        return;
    L_0x0046:
        monitor-enter(r8);	 Catch:{ Exception -> 0x0088 }
        r9 = r8.v;	 Catch:{ all -> 0x0085 }
        if (r9 != 0) goto L_0x004d;
    L_0x004b:
        monitor-exit(r8);	 Catch:{ all -> 0x0085 }
        return;
    L_0x004d:
        r9 = r8.g;	 Catch:{ all -> 0x0085 }
        if (r9 == 0) goto L_0x005d;
    L_0x0051:
        r9 = r8.g;	 Catch:{ all -> 0x0085 }
        r9.updateTexImage();	 Catch:{ all -> 0x0085 }
        r9 = r8.g;	 Catch:{ all -> 0x0085 }
        r0 = r8.l;	 Catch:{ all -> 0x0085 }
        r9.getTransformMatrix(r0);	 Catch:{ all -> 0x0085 }
    L_0x005d:
        r9 = 0;
        r8.v = r9;	 Catch:{ all -> 0x0085 }
        monitor-exit(r8);	 Catch:{ all -> 0x0085 }
        r0 = r8.F;	 Catch:{ Exception -> 0x0088 }
        if (r0 == 0) goto L_0x0070;
    L_0x0065:
        r0 = r8.F;	 Catch:{ Exception -> 0x0088 }
        r1 = r8.k;	 Catch:{ Exception -> 0x0088 }
        r9 = r1[r9];	 Catch:{ Exception -> 0x0088 }
        r1 = r8.l;	 Catch:{ Exception -> 0x0088 }
        r0.onTextureProcess(r9, r1);	 Catch:{ Exception -> 0x0088 }
    L_0x0070:
        r8.f();	 Catch:{ Exception -> 0x0088 }
        monitor-enter(r8);	 Catch:{ Exception -> 0x0088 }
        r9 = r8.c;	 Catch:{ all -> 0x0082 }
        r9 = r9 ^ 1;
        monitor-exit(r8);	 Catch:{ all -> 0x0082 }
        if (r9 == 0) goto L_0x008c;
    L_0x007b:
        r9 = r8.d();	 Catch:{ Exception -> 0x0088 }
        r8.t = r9;	 Catch:{ Exception -> 0x0088 }
        goto L_0x008c;
    L_0x0082:
        r9 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x0082 }
        throw r9;	 Catch:{ Exception -> 0x0088 }
    L_0x0085:
        r9 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x0085 }
        throw r9;	 Catch:{ Exception -> 0x0088 }
    L_0x0088:
        r9 = move-exception;
        r9.printStackTrace();
    L_0x008c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.TXCGLSurfaceView.onDrawFrame(javax.microedition.khronos.opengles.GL10):void");
    }

    private void f() {
        if (this.A) {
            if (!(this.y == 0 || this.z == 0)) {
                Object obj = getWidth() <= getHeight() ? 1 : null;
                int i = this.z >= this.y ? this.z : this.y;
                int i2 = this.z >= this.y ? this.y : this.z;
                if (obj != null) {
                    int i3 = i2;
                    i2 = i;
                    i = i3;
                }
                Buffer allocate = ByteBuffer.allocate((i * i2) * 4);
                Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
                allocate.position(0);
                GLES20.glReadPixels(this.D, this.E, i, i2, 6408, 5121, allocate);
                final Buffer buffer = allocate;
                final Bitmap bitmap = createBitmap;
                final int i4 = i;
                final int i5 = i2;
                new Thread(new Runnable() {
                    public void run() {
                        buffer.position(0);
                        bitmap.copyPixelsFromBuffer(buffer);
                        Matrix matrix = new Matrix();
                        matrix.setScale(1.0f, -1.0f);
                        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, i4, i5, matrix, false);
                        if (TXCGLSurfaceView.this.B != null) {
                            TXCGLSurfaceView.this.B.onTakePhotoComplete(createBitmap);
                            TXCGLSurfaceView.this.B = null;
                        }
                        bitmap.recycle();
                    }
                }).start();
            }
            this.A = false;
        }
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (!this.j) {
            b.a(this.a, (int) TXLiveConstants.PUSH_EVT_FIRST_FRAME_AVAILABLE, "首帧画面采集完成");
            this.j = true;
        }
        this.u = false;
        synchronized (this) {
            this.v = true;
        }
    }

    public void a(boolean z) {
        this.u = true;
        if (z) {
            GLES20.glClearColor(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
            GLES20.glClear(16384);
            this.t = d();
        }
        synchronized (this) {
            if (this.v) {
                this.v = false;
                if (this.g != null) {
                    this.g.updateTexImage();
                }
            }
        }
    }

    public void b(final boolean z) {
        synchronized (this.w) {
            b(new Runnable() {
                public void run() {
                    synchronized (TXCGLSurfaceView.this.w) {
                        TXCGLSurfaceView.this.a(z);
                        TXCGLSurfaceView.this.w.notifyAll();
                    }
                }
            });
            try {
                this.w.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void g() {
        try {
            Thread.sleep(15);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

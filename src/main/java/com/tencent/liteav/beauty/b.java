package com.tencent.liteav.beauty;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.tencent.liteav.basic.e.g;
import com.tencent.liteav.basic.e.h;
import com.tencent.liteav.basic.e.i;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.beauty.b.ah;
import com.tencent.liteav.beauty.b.c;
import com.tencent.liteav.beauty.b.l;
import com.tencent.liteav.beauty.b.m;
import com.tencent.liteav.beauty.b.p;
import com.tencent.liteav.beauty.b.r;
import com.tencent.liteav.beauty.b.t;
import com.tencent.liteav.beauty.b.v;
import com.tencent.liteav.beauty.d.d;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* compiled from: TXCFilterDrawer */
class b extends HandlerThread {
    private int A = 0;
    private int B = 0;
    private boolean C = false;
    private float[] D;
    private int E = 0;
    private int F = 0;
    private com.tencent.liteav.basic.e.a G = null;
    private Bitmap H = null;
    private p I = null;
    private v J = null;
    private com.tencent.liteav.beauty.b.b K = null;
    private com.tencent.liteav.beauty.b.a.a L = null;
    private com.tencent.liteav.beauty.b.b.a M = null;
    private c N = null;
    private Bitmap O;
    private Bitmap P;
    private float Q;
    private float R;
    private float S;
    private r T;
    private t U = null;
    private ah V = null;
    private m W = null;
    private l X = null;
    private g Y = null;
    private g Z = null;
    boolean a = false;
    private byte[] aA = null;
    private int aB = -1;
    private int aC = 0;
    private int aD = 1;
    private int aE = this.aB;
    private e aF = null;
    private WeakReference<com.tencent.liteav.basic.d.a> aG = new WeakReference(null);
    private com.tencent.liteav.basic.e.i.b aH = new com.tencent.liteav.basic.e.i.b() {
    };
    private h aa = null;
    private g ab = null;
    private final Queue<Runnable> ac = new LinkedList();
    private boolean ad;
    private Object ae = new Object();
    private Object af = new Object();
    private Handler ag;
    private a ah;
    private float ai = 0.5f;
    private int aj = 0;
    private int ak = 0;
    private int al = 0;
    private int am = 0;
    private int an = 0;
    private boolean ao = false;
    private com.tencent.liteav.beauty.a.a.c ap = null;
    private com.tencent.liteav.beauty.a.a.a aq = null;
    private Bitmap ar = null;
    private List<d> as = null;
    private long at = 0;
    private int au = 0;
    private final int av = 100;
    private final float aw = 1000.0f;
    private byte[] ax = null;
    private int[] ay = null;
    private boolean az = false;
    protected int[] b = null;
    protected int[] c = null;
    com.tencent.liteav.beauty.b.a d = new com.tencent.liteav.beauty.b.a();
    com.tencent.liteav.beauty.b.a e = new com.tencent.liteav.beauty.b.a();
    com.tencent.liteav.beauty.b.a f = new com.tencent.liteav.beauty.b.a();
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private Context m = null;
    private boolean n = true;
    private d o = null;
    private int p = -1;
    private int q = -1;
    private int r = -1;
    private int s = -1;
    private int t = -1;
    private int u = -1;
    private int v = -1;
    private int w = -1;
    private float x = 1.0f;
    private int y = -1;
    private int z = -1;

    /* compiled from: TXCFilterDrawer */
    private class a extends Handler {
        private String b = "EGLDrawThreadHandler";

        a(Looper looper, Context context) {
            super(looper);
        }

        private void a(Object obj) {
            TXCLog.i(this.b, "come into InitEGL");
            b bVar = (b) obj;
            a();
            b.this.aq = new com.tencent.liteav.beauty.a.a.a();
            b.this.ap = new com.tencent.liteav.beauty.a.a.c(b.this.aq, bVar.g, bVar.f, false);
            b.this.ap.b();
            if (b.this.c(bVar)) {
                TXCLog.i(this.b, "come out InitEGL");
            } else {
                TXCLog.e(this.b, "initInternal failed!");
            }
        }

        public void a() {
            TXCLog.i(this.b, "come into releaseEGL");
            if (b.this.ay != null && b.this.ay[0] > 0) {
                GLES20.glDeleteBuffers(1, b.this.ay, 0);
                b.this.ay = null;
            }
            b.this.b();
            if (b.this.ap != null) {
                b.this.ap.c();
                b.this.ap = null;
            }
            if (b.this.aq != null) {
                b.this.aq.a();
                b.this.aq = null;
            }
            b.this.ao = false;
            NativeLoad.getInstance();
            NativeLoad.nativeDeleteYuv2Yuv();
            TXCLog.i(this.b, "come out releaseEGL");
        }

        /* JADX WARNING: Missing block: B:11:0x0067, code skipped:
            r11 = 1;
     */
        public void handleMessage(android.os.Message r11) {
            /*
            r10 = this;
            super.handleMessage(r11);
            r0 = r11.what;
            r1 = 7;
            r2 = 1;
            if (r0 == r1) goto L_0x0069;
        L_0x0009:
            switch(r0) {
                case 0: goto L_0x005d;
                case 1: goto L_0x0052;
                case 2: goto L_0x0048;
                case 3: goto L_0x0040;
                case 4: goto L_0x0019;
                case 5: goto L_0x000e;
                default: goto L_0x000c;
            };
        L_0x000c:
            goto L_0x0093;
        L_0x000e:
            r11 = r11.obj;
            r11 = (com.tencent.liteav.beauty.d.b) r11;
            r0 = com.tencent.liteav.beauty.b.this;
            r0.d(r11);
            goto L_0x0093;
        L_0x0019:
            r0 = com.tencent.liteav.beauty.b.this;
            r11 = r11.arg1;
            r3 = (double) r11;
            r5 = 4636737291354636288; // 0x4059000000000000 float:0.0 double:100.0;
            java.lang.Double.isNaN(r3);
            r3 = r3 / r5;
            r11 = (float) r3;
            r0.ai = r11;
            r11 = com.tencent.liteav.beauty.b.this;
            r11 = r11.T;
            if (r11 == 0) goto L_0x0093;
        L_0x0030:
            r11 = com.tencent.liteav.beauty.b.this;
            r11 = r11.T;
            r0 = com.tencent.liteav.beauty.b.this;
            r0 = r0.ai;
            r11.a(r0);
            goto L_0x0093;
        L_0x0040:
            r0 = com.tencent.liteav.beauty.b.this;
            r11 = r11.arg1;
            r0.n(r11);
            goto L_0x0067;
        L_0x0048:
            r0 = com.tencent.liteav.beauty.b.this;
            r11 = r11.obj;
            r11 = (byte[]) r11;
            r0.b(r11);
            goto L_0x0093;
        L_0x0052:
            r10.a();
            r11 = com.tencent.liteav.beauty.b.this;
            r11 = r11.d;
            r11.a();
            goto L_0x0093;
        L_0x005d:
            r11 = r11.obj;
            r10.a(r11);
            r11 = com.tencent.liteav.beauty.b.this;
            r11.ao = r2;
        L_0x0067:
            r11 = 1;
            goto L_0x0094;
        L_0x0069:
            r3 = com.tencent.liteav.beauty.b.this;
            r0 = com.tencent.liteav.beauty.b.this;
            r4 = r0.t;
            r0 = com.tencent.liteav.beauty.b.this;
            r5 = r0.u;
            r0 = com.tencent.liteav.beauty.b.this;
            r6 = r0.A;
            r7 = r11.arg1;
            r8 = r11.arg2;
            r11 = r11.obj;
            r11 = (java.lang.Integer) r11;
            r9 = r11.intValue();
            r3.a(r4, r5, r6, r7, r8, r9);
            r11 = com.tencent.liteav.beauty.b.this;
            r11 = r11.f;
            r11.a();
        L_0x0093:
            r11 = 0;
        L_0x0094:
            monitor-enter(r10);
            if (r2 != r11) goto L_0x009d;
        L_0x0097:
            r10.notify();	 Catch:{ all -> 0x009b }
            goto L_0x009d;
        L_0x009b:
            r11 = move-exception;
            goto L_0x009f;
        L_0x009d:
            monitor-exit(r10);	 Catch:{ all -> 0x009b }
            return;
        L_0x009f:
            monitor-exit(r10);	 Catch:{ all -> 0x009b }
            throw r11;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.beauty.b$a.handleMessage(android.os.Message):void");
        }

        /* Access modifiers changed, original: 0000 */
        public void b() {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void a(String str) {
    }

    public void a(String str, boolean z) {
    }

    public void a(boolean z) {
    }

    public void g(int i) {
    }

    public void h(int i) {
    }

    public void i(int i) {
    }

    public void j(int i) {
    }

    public void k(int i) {
    }

    public void l(int i) {
    }

    b(Context context, boolean z) {
        super("TXCFilterDrawer");
        this.m = context;
        this.ag = new Handler(this.m.getMainLooper());
        this.ad = z;
    }

    public synchronized boolean a(b bVar) {
        boolean z;
        z = true;
        if (bVar.j) {
            z = c(bVar);
        } else {
            if (this.ah == null) {
                start();
                this.ah = new a(getLooper(), this.m);
            }
            this.ah.obtainMessage(0, bVar).sendToTarget();
            this.ah.b();
        }
        return z;
    }

    public int a(int i, int i2) {
        a(this.ac);
        Object obj = this.x != 1.0f ? 1 : null;
        GLES20.glViewport(0, 0, this.v, this.w);
        if (this.Z != null) {
            if (4 == i2) {
                this.Z.a(this.D);
            }
            i = this.Z.a(i);
        }
        if (this.K != null && (this.ak > 0 || this.al > 0 || this.an > 0)) {
            i = this.K.a(i);
        }
        if (this.T != null) {
            i = this.T.a(i);
        }
        GLES20.glViewport(0, 0, this.t, this.u);
        if (this.W != null) {
            i = this.W.a(i);
            obj = null;
        }
        if (this.X != null) {
            i = this.X.a(i);
            obj = null;
        }
        if (obj != null) {
            c(this.t, this.u);
            if (this.ab != null) {
                GLES20.glViewport(0, 0, this.t, this.u);
                i = this.ab.a(i);
            }
        }
        if (this.aF != null) {
            i2 = this.aF.willAddWatermark(i, this.t, this.u);
            if (i2 > 0) {
                i = i2;
            }
        }
        GLES20.glViewport(0, 0, this.t, this.u);
        if (this.V != null) {
            i = this.V.a(i);
        }
        if (this.Y != null) {
            GLES20.glViewport(0, 0, this.y, this.z);
            i = this.Y.a(i);
        }
        m(i);
        return i;
    }

    public int a(byte[] bArr, int i) {
        a(bArr);
        if (this.ad) {
            b(bArr);
            return n(i);
        }
        bArr = (byte[]) bArr.clone();
        this.ah.obtainMessage(2, bArr).sendToTarget();
        if (!this.az) {
            TXCLog.i("TXCFilterDrawer", "First Frame, clear queue");
            NativeLoad.getInstance();
            NativeLoad.nativeClearQueue();
        }
        this.ah.obtainMessage(3, i, 0).sendToTarget();
        a(bArr, this.az);
        this.az = true;
        return -1;
    }

    public void a(final float f) {
        this.ai = f;
        a(new Runnable() {
            public void run() {
                if (b.this.T != null) {
                    b.this.T.a(f);
                }
            }
        });
    }

    public void a(final float[] fArr) {
        a(new Runnable() {
            public void run() {
                b.this.D = fArr;
            }
        });
    }

    private void a(com.tencent.liteav.basic.e.a aVar, int i, int i2, int i3, int i4, boolean z, int i5, int i6) {
        if (this.Z == null) {
            TXCLog.i("TXCFilterDrawer", "Create CropFilter");
            if (4 == i6) {
                this.Z = new g("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nuniform mat4 textureTransform;\nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = (textureTransform * inputTextureCoordinate).xy;\n}", "#extension GL_OES_EGL_image_external : require\n\nvarying lowp vec2 textureCoordinate;\n \nuniform samplerExternalOES inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}", true);
            } else {
                this.Z = new g();
            }
            if (true == this.Z.c()) {
                this.Z.a(true);
            } else {
                TXCLog.e("TXCFilterDrawer", "mInputCropFilter init failed!");
            }
        } else {
            int i7 = i6;
        }
        int i8 = i3;
        int i9 = i4;
        this.Z.a(i8, i9);
        float[] a = this.Z.a(this.p, this.q, null, aVar, i6);
        int i10 = (720 - i5) % 360;
        int i11 = (i10 == 90 || i10 == 270) ? i9 : i8;
        if (i10 == 90 || i10 == 270) {
            i9 = i8;
        }
        this.Z.a(i, i2, i10, a, ((float) i11) / ((float) i9), z, false);
    }

    private void a(int i, int i2, int i3, int i4, int i5, int i6) {
        synchronized (this.af) {
            i6 = ((i6 - i3) + 360) % 360;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("real outputAngle ");
            stringBuilder.append(i6);
            TXCLog.i("TXCFilterDrawer", stringBuilder.toString());
            if (this.Y == null) {
                if (i == i4 && i2 == i5 && i6 == 0) {
                    TXCLog.i("TXCFilterDrawer", "Don't need change output Image, don't create out filter!");
                    return;
                }
                this.Y = new g();
                if (true == this.Y.c()) {
                    this.Y.a(true);
                } else {
                    TXCLog.e("TXCFilterDrawer", "mOutputZoomFilter init failed!");
                }
            }
            this.Y.a(i4, i5);
            this.Y.a((720 - i6) % 360, null);
        }
    }

    public void a(Bitmap bitmap, float f, float f2, float f3) {
        if (this.o == null) {
            this.o = new d();
        }
        if (this.o.a != null && bitmap != null && true == this.o.a.equals(bitmap) && f == this.o.b && f2 == this.o.c && f3 == this.o.d) {
            Log.d("TXCFilterDrawer", "Same Water Mark; don't set again");
            return;
        }
        this.o.a = bitmap;
        this.o.b = f;
        this.o.c = f2;
        this.o.d = f3;
        final Bitmap bitmap2 = bitmap;
        final float f4 = f;
        final float f5 = f2;
        final float f6 = f3;
        a(new Runnable() {
            public void run() {
                if (bitmap2 != null) {
                    a.a().e();
                }
                if (bitmap2 == null) {
                    if (b.this.V != null) {
                        b.this.V.e();
                        b.this.V = null;
                    }
                    return;
                }
                if (b.this.V == null) {
                    if (b.this.t <= 0 || b.this.u <= 0) {
                        TXCLog.e("TXCFilterDrawer", "output Width and Height is error!");
                        return;
                    }
                    b.this.V = new ah();
                    b.this.V.a(true);
                    if (b.this.V.c()) {
                        b.this.V.a(b.this.t, b.this.u);
                    } else {
                        TXCLog.e("TXCFilterDrawer", "mWatermarkFilter.init failed!");
                        b.this.V.e();
                        b.this.V = null;
                        return;
                    }
                }
                b.this.V.d(true);
                b.this.V.a(bitmap2, f4, f5, f6);
            }
        });
    }

    public void a(final List<d> list) {
        a(new Runnable() {
            public void run() {
                b.this.as = list;
                if ((list == null || list.size() == 0) && b.this.ar == null && b.this.V != null) {
                    b.this.V.e();
                    b.this.V = null;
                    return;
                }
                if (!(list == null || list.size() == 0)) {
                    if (b.this.V == null) {
                        if (b.this.t <= 0 || b.this.u <= 0) {
                            Log.e("TXCFilterDrawer", "output Width and Height is error!");
                            return;
                        }
                        b.this.V = new ah();
                        b.this.V.a(true);
                        if (b.this.V.c()) {
                            b.this.V.a(b.this.t, b.this.u);
                        } else {
                            Log.e("TXCFilterDrawer", "mWatermarkFilter.init failed!");
                            b.this.V.e();
                            b.this.V = null;
                            return;
                        }
                    }
                    b.this.V.d(true);
                    b.this.V.a(list);
                }
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public void a(e eVar) {
        TXCLog.i("TXCFilterDrawer", "set listener");
        this.aF = eVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(com.tencent.liteav.basic.d.a aVar) {
        TXCLog.i("TXCFilterDrawer", "set notify");
        this.aG = new WeakReference(aVar);
    }

    private int m(int i) {
        if (this.F == 0) {
            if (this.aF != null) {
                this.aF.didProcessFrame(i, this.y, this.z, TXCTimeUtil.getTimeTick());
            }
            return i;
        } else if (1 == this.F || 3 == this.F || 2 == this.F) {
            GLES20.glViewport(0, 0, this.y, this.z);
            if (this.J == null) {
                TXCLog.e("TXCFilterDrawer", "mRGBA2I420Filter is null!");
                return i;
            }
            GLES20.glBindFramebuffer(36160, this.b[0]);
            this.J.b(i);
            if (2 == this.F) {
                b(this.y, this.z);
            } else {
                b(this.y, (this.z * 3) / 8);
            }
            GLES20.glBindFramebuffer(36160, 0);
            return i;
        } else {
            TXCLog.e("TXCFilterDrawer", "Don't support format!");
            return -1;
        }
    }

    private int b(int i, int i2) {
        if (true == this.ad) {
            if (this.aF != null) {
                NativeLoad.getInstance();
                NativeLoad.nativeGlReadPixs(i, i2, this.ax);
                this.aF.didProcessFrame(this.ax, this.y, this.z, this.F, TXCTimeUtil.getTimeTick());
            } else if (this.aA != null) {
                NativeLoad.getInstance();
                NativeLoad.nativeGlReadPixs(i, i2, this.aA);
            }
        } else if (3 == i.a()) {
            if (0 == this.at) {
                this.at = TXCTimeUtil.getTimeTick();
            }
            int i3 = this.au + 1;
            this.au = i3;
            if (i3 >= 100) {
                float timeTick = 100.0f / (((float) (TXCTimeUtil.getTimeTick() - this.at)) / 1000.0f);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Real fps ");
                stringBuilder.append(timeTick);
                TXCLog.i("TXCFilterDrawer", stringBuilder.toString());
                this.au = 0;
                this.at = TXCTimeUtil.getTimeTick();
            }
            GLES30.glPixelStorei(3333, 1);
            if (VERSION.SDK_INT >= 18) {
                GLES30.glReadBuffer(1029);
            }
            GLES30.glBindBuffer(35051, this.ay[0]);
            NativeLoad.getInstance();
            ByteBuffer byteBuffer = null;
            NativeLoad.nativeGlReadPixs(i, i2, null);
            if (VERSION.SDK_INT >= 18) {
                byteBuffer = (ByteBuffer) GLES30.glMapBufferRange(35051, 0, (i * i2) * 4, 1);
                if (byteBuffer == null) {
                    TXCLog.e("TXCFilterDrawer", "glMapBufferRange is null");
                    return -1;
                }
            }
            NativeLoad.getInstance();
            NativeLoad.nativeGlMapBufferToQueue(i, i2, byteBuffer);
            if (VERSION.SDK_INT >= 18) {
                GLES30.glUnmapBuffer(35051);
            }
            GLES30.glBindBuffer(35051, 0);
        } else {
            NativeLoad.getInstance();
            NativeLoad.nativeGlReadPixsToQueue(i, i2);
        }
        return 0;
    }

    public void a(final int i) {
        a(new Runnable() {
            public void run() {
                b.this.F = i;
            }
        });
    }

    private void a(byte[] bArr, boolean z) {
        if (z) {
            int i = (this.z * 3) / 8;
            if (2 == this.F) {
                i = this.z;
            }
            if (this.aF != null) {
                NativeLoad.getInstance();
                if (true == NativeLoad.nativeGlReadPixsFromQueue(this.y, i, this.ax)) {
                    this.aF.didProcessFrame(this.ax, this.y, this.z, this.F, TXCTimeUtil.getTimeTick());
                    return;
                }
                TXCLog.d("TXCFilterDrawer", "nativeGlReadPixsFromQueue Failed");
                this.aF.didProcessFrame(bArr, this.y, this.z, this.F, TXCTimeUtil.getTimeTick());
                return;
            }
            NativeLoad.getInstance();
            if (!NativeLoad.nativeGlReadPixsFromQueue(this.y, i, this.aA)) {
                TXCLog.d("TXCFilterDrawer", "nativeGlReadPixsFromQueue Failed");
            }
        } else if (this.aF != null) {
            this.aF.didProcessFrame(bArr, this.y, this.z, this.F, TXCTimeUtil.getTimeTick());
        } else {
            TXCLog.i("TXCFilterDrawer", "First Frame, don't process!");
        }
    }

    private int n(int i) {
        GLES20.glViewport(0, 0, this.p, this.q);
        return a(this.I.r(), i);
    }

    public void a(byte[] bArr) {
        this.aA = bArr;
    }

    private void b(byte[] bArr) {
        if (this.I == null) {
            TXCLog.e("TXCFilterDrawer", "mI4202RGBAFilter is null!");
        } else {
            this.I.a(bArr);
        }
    }

    public void a() {
        if (this.ad) {
            b();
        } else if (this.ah != null) {
            this.ah.obtainMessage(1).sendToTarget();
            try {
                this.d.b();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void b() {
        TXCLog.i("TXCFilterDrawer", "come into releaseInternal");
        this.az = false;
        if (this.I != null) {
            this.I.e();
            this.I = null;
        }
        if (this.J != null) {
            this.J.e();
            this.J = null;
        }
        c();
        if (this.T != null) {
            this.T.e();
            this.T = null;
        }
        if (this.U != null) {
            this.U.a();
            this.U = null;
        }
        if (this.aa != null) {
            this.aa.e();
            this.aa = null;
        }
        if (this.Z != null) {
            this.Z.e();
            this.Z = null;
        }
        if (this.Y != null) {
            this.Y.e();
            this.Y = null;
        }
        if (this.V != null) {
            this.V.e();
            this.V = null;
        }
        if (this.W != null) {
            this.W.a();
            this.W = null;
        }
        if (this.X != null) {
            this.X.e();
            this.X = null;
        }
        if (this.ab != null) {
            this.ab.e();
            this.ab = null;
        }
        if (this.b != null) {
            GLES20.glDeleteFramebuffers(1, this.b, 0);
            this.b = null;
        }
        if (this.c != null) {
            GLES20.glDeleteTextures(1, this.c, 0);
            this.c = null;
        }
        this.o = null;
        TXCLog.i("TXCFilterDrawer", "come out releaseInternal");
    }

    private boolean c(b bVar) {
        TXCLog.i("TXCFilterDrawer", "come into initInternal");
        b();
        this.ad = bVar.j;
        this.p = bVar.d;
        this.q = bVar.e;
        this.G = bVar.m;
        this.r = bVar.g;
        this.s = bVar.f;
        this.A = bVar.h;
        this.C = bVar.i;
        this.y = bVar.b;
        this.z = bVar.c;
        this.B = bVar.a;
        this.t = bVar.g;
        this.u = bVar.f;
        if (this.A == 90 || this.A == 270) {
            this.t = bVar.f;
            this.u = bVar.g;
        }
        this.F = bVar.l;
        this.E = bVar.k;
        this.ax = new byte[((this.y * this.z) * 4)];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processWidth mPituScaleRatio is ");
        stringBuilder.append(this.x);
        TXCLog.i("TXCFilterDrawer", stringBuilder.toString());
        if (this.x != 1.0f) {
            int i = this.t < this.u ? this.t : this.u;
            if (i > 368) {
                this.x = 432.0f / ((float) i);
            }
            if (this.x > 1.0f) {
                this.x = 1.0f;
            }
        }
        this.v = (int) (((float) this.t) * this.x);
        this.w = (int) (((float) this.u) * this.x);
        a(this.v, this.w, this.aj);
        if (!(this.o == null || this.o.a == null || this.V != null)) {
            TXCLog.i("TXCFilterDrawer", "reset water mark!");
            a(this.o.a, this.o.b, this.o.c, this.o.d);
        }
        if (!(this.O == null && this.P == null) && this.T == null) {
            a(this.v, this.w, this.Q, this.O, this.R, this.P, this.S);
        }
        a(this.G, this.r, this.s, this.v, this.w, this.C, this.A, this.E);
        a(this.t, this.u, this.A, this.y, this.z, this.B);
        if (this.b == null) {
            this.b = new int[1];
        } else {
            GLES20.glDeleteFramebuffers(1, this.b, 0);
        }
        if (this.c == null) {
            this.c = new int[1];
        } else {
            GLES20.glDeleteTextures(1, this.c, 0);
        }
        a(this.b, this.c, this.y, this.z);
        if (3 == i.a()) {
            if (this.ay == null) {
                this.ay = new int[1];
            } else {
                TXCLog.i("TXCFilterDrawer", "m_pbo0 is not null, delete Buffers, and recreate");
                GLES30.glDeleteBuffers(1, this.ay, 0);
            }
            TXCLog.i("TXCFilterDrawer", "opengl es 3.0, use PBO");
            i.a(this.r, this.s, this.ay);
        }
        TXCLog.i("TXCFilterDrawer", "come out initInternal");
        return true;
    }

    public boolean b(b bVar) {
        if (this.ad) {
            d(bVar);
        } else if (this.ah == null) {
            TXCLog.e("TXCFilterDrawer", "mThreadHandler is null!");
            return false;
        } else {
            this.ah.obtainMessage(5, 0, 0, bVar).sendToTarget();
        }
        return true;
    }

    private void a(int[] iArr, int[] iArr2, int i, int i2) {
        GLES20.glGenFramebuffers(1, iArr, 0);
        iArr2[0] = i.a(i, i2, 6408, 6408, iArr2);
        GLES20.glBindFramebuffer(36160, iArr[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr2[0], 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    private boolean d(b bVar) {
        if ((1 == bVar.k || 3 == bVar.k || 2 == bVar.k) && this.I == null) {
            this.I = new p(bVar.k);
            this.I.a(true);
            if (this.I.c()) {
                this.I.a(bVar.d, bVar.e);
            } else {
                TXCLog.e("TXCFilterDrawer", "mI4202RGBAFilter init failed!!, break init");
                return false;
            }
        }
        if ((1 == bVar.l || 3 == bVar.l || 2 == bVar.l) && this.J == null) {
            this.J = new v(bVar.l);
            if (this.J.c()) {
                this.J.a(bVar.b, bVar.c);
            } else {
                TXCLog.e("TXCFilterDrawer", "mRGBA2I420Filter init failed!!, break init");
                return false;
            }
        }
        return true;
    }

    public void b(final int i) {
        this.ak = i;
        a(new Runnable() {
            public void run() {
                if (i > 0) {
                    a.a().b();
                }
                if (b.this.K != null && i >= 0) {
                    b.this.K.c(i);
                }
            }
        });
    }

    public void c(final int i) {
        if (this.aj != i && i <= 2 && i >= 0) {
            this.aj = i;
            a(new Runnable() {
                public void run() {
                    b.this.a(b.this.v, b.this.w, i);
                }
            });
        }
    }

    public void d(final int i) {
        this.al = i;
        a(new Runnable() {
            public void run() {
                if (i > 0) {
                    a.a().c();
                }
                if (b.this.K != null && i >= 0) {
                    b.this.K.d(i);
                }
            }
        });
    }

    public void e(final int i) {
        this.am = i;
        a(new Runnable() {
            public void run() {
                if (i > 0) {
                    a.a().c();
                }
                if (b.this.K != null && i >= 0) {
                    b.this.K.f(i);
                }
            }
        });
    }

    public void f(final int i) {
        this.an = i;
        a(new Runnable() {
            public void run() {
                if (i > 0) {
                    a.a().c();
                }
                if (b.this.K != null && i >= 0) {
                    b.this.K.e(i);
                }
            }
        });
    }

    public void a(Bitmap bitmap) {
        a(1.0f, bitmap, this.ai, null, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public void a(final float f, Bitmap bitmap, final float f2, Bitmap bitmap2, final float f3) {
        if (this.O != bitmap || this.P != bitmap2) {
            this.O = bitmap;
            this.P = bitmap2;
            this.Q = f;
            this.R = f2;
            this.S = f3;
            final float f4 = f;
            final Bitmap bitmap3 = bitmap;
            final float f5 = f2;
            final Bitmap bitmap4 = bitmap2;
            final float f6 = f3;
            a(new Runnable() {
                public void run() {
                    if (b.this.T != null) {
                        a.a().d();
                    }
                    if (b.this.O == null && b.this.P == null) {
                        if (b.this.T != null) {
                            b.this.T.e();
                            b.this.T = null;
                        }
                    } else if (b.this.T == null) {
                        b.this.a(b.this.v, b.this.w, b.this.Q, b.this.O, b.this.R, b.this.P, b.this.S);
                    } else {
                        b.this.T.a(f4, bitmap3, f5, bitmap4, f6);
                    }
                }
            });
        } else if (this.T == null) {
        } else {
            if (this.Q != f || this.R != f2 || this.S != f3) {
                this.Q = f;
                this.R = f2;
                this.S = f3;
                a(new Runnable() {
                    public void run() {
                        b.this.T.a(f, f2, f3);
                    }
                });
            }
        }
    }

    public void b(final float f) {
        a(new Runnable() {
            public void run() {
                if (f <= CropImageView.DEFAULT_ASPECT_RATIO) {
                    if (b.this.X != null) {
                        b.this.X.e();
                        b.this.X = null;
                        return;
                    }
                } else if (b.this.X == null) {
                    b.this.X = new l();
                    b.this.X.a(true);
                    if (b.this.X.c()) {
                        b.this.X.a(b.this.t, b.this.u);
                    } else {
                        TXCLog.e("TXCFilterDrawer", "Gaussian Filter init failed!");
                        return;
                    }
                }
                if (b.this.X != null) {
                    b.this.X.a(f / 4.0f);
                }
            }
        });
    }

    private void a(int i, int i2, int i3) {
        TXCLog.i("TXCFilterDrawer", "create Beauty Filter!");
        if (i3 == 0) {
            if (this.L == null) {
                this.L = new com.tencent.liteav.beauty.b.a.a();
            }
            this.K = this.L;
            Log.i("TXCFilterDrawer", "0 BeautyFilter");
        } else if (1 == i3) {
            if (this.M == null) {
                this.M = new com.tencent.liteav.beauty.b.b.a();
            }
            this.K = this.M;
            Log.i("TXCFilterDrawer", "1 BeautyFilter");
        } else if (2 == i3) {
            if (this.N == null) {
                this.N = new c();
            }
            this.K = this.N;
            Log.i("TXCFilterDrawer", "2 BeautyFilter");
        }
        if (this.K == null) {
            TXCLog.e("TXCFilterDrawer", "mBeautyFilter set error!");
            return;
        }
        this.K.a(true);
        if (true == this.K.c(i, i2)) {
            if (this.ak > 0) {
                this.K.c(this.ak);
            }
            if (this.al > 0) {
                this.K.d(this.al);
            }
            if (this.an > 0) {
                this.K.e(this.an);
            }
            if (this.am > 0) {
                this.K.f(this.am);
            }
        } else {
            TXCLog.e("TXCFilterDrawer", "mBeautyFilter init failed!");
        }
    }

    private void c() {
        if (this.L != null) {
            this.L.e();
            this.L = null;
        }
        if (this.M != null) {
            this.M.e();
            this.M = null;
        }
        if (this.N != null) {
            this.N.e();
            this.N = null;
        }
        this.K = null;
    }

    private void a(int i, int i2, float f, Bitmap bitmap, float f2, Bitmap bitmap2, float f3) {
        if (this.T == null) {
            TXCLog.i("TXCFilterDrawer", "createComLooKupFilter");
            this.T = new r(f, bitmap, f2, bitmap2, f3);
            if (true == this.T.c()) {
                this.T.a(true);
                this.T.a(i, i2);
                return;
            }
            TXCLog.e("TXCFilterDrawer", "mLookupFilterGroup init failed!");
        }
    }

    private void c(int i, int i2) {
        if (this.ab == null) {
            TXCLog.i("TXCFilterDrawer", "createRecoverScaleFilter");
            this.ab = new g();
            if (true == this.ab.c()) {
                this.ab.a(true);
            } else {
                TXCLog.e("TXCFilterDrawer", "mRecoverScaleFilter init failed!");
            }
        }
        if (this.ab != null) {
            this.ab.a(i, i2);
        }
    }

    private void a(Runnable runnable) {
        synchronized (this.ac) {
            this.ac.add(runnable);
        }
    }

    private void a(Queue<Runnable> queue) {
        while (true) {
            Runnable runnable = null;
            synchronized (queue) {
                if (!queue.isEmpty()) {
                    runnable = (Runnable) queue.poll();
                }
            }
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
        while (true) {
        }
    }
}

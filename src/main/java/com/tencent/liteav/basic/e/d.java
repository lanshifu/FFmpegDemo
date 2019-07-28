package com.tencent.liteav.basic.e;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Matrix;
import android.opengl.GLES20;
import android.os.HandlerThread;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.yalantis.ucrop.view.CropImageView;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import javax.microedition.khronos.egl.EGLContext;

/* compiled from: TXCGLSurfaceRenderThread */
public class d implements a {
    private volatile HandlerThread a = null;
    private volatile f b = null;
    private g c = null;
    private int d = 0;
    private boolean e = false;
    private float f = 1.0f;
    private float g = 1.0f;
    private int h = 0;
    private int i = 0;
    private boolean j = false;
    private n k = null;
    private boolean l = false;

    public void d() {
    }

    public void a(EGLContext eGLContext, Surface surface) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("surface-render: surface render start ");
        stringBuilder.append(surface);
        TXCLog.i("TXGLSurfaceRenderThread", stringBuilder.toString());
        b(eGLContext, surface);
    }

    public void a() {
        TXCLog.i("TXGLSurfaceRenderThread", "surface-render: surface render stop ");
        f();
    }

    public Surface b() {
        Surface b;
        synchronized (this) {
            b = this.b != null ? this.b.b() : null;
        }
        return b;
    }

    public void a(Runnable runnable) {
        synchronized (this) {
            if (this.b != null) {
                this.b.post(runnable);
            }
        }
    }

    public void a(int i, boolean z, int i2, int i3, int i4, int i5, int i6, boolean z2) {
        GLES20.glFinish();
        synchronized (this) {
            if (this.b != null) {
                final int i7 = i;
                final boolean z3 = z;
                final int i8 = i2;
                final int i9 = i3;
                final int i10 = i4;
                final int i11 = i5;
                final int i12 = i6;
                final boolean z4 = z2;
                this.b.post(new Runnable() {
                    public void run() {
                        try {
                            d.this.b(i7, z3, i8, i9, i10, i11, i12, z4);
                        } catch (Exception unused) {
                            TXCLog.e("TXGLSurfaceRenderThread", "surface-render: render texture error occurred!");
                        }
                    }
                });
            }
        }
    }

    public void a(n nVar) {
        this.k = nVar;
        this.j = true;
    }

    public void c() {
        this.c = new g();
        if (this.c.c()) {
            this.c.a(k.e, k.a(j.NORMAL, false, false));
        }
    }

    public void e() {
        if (this.c != null) {
            this.c.e();
            this.c = null;
        }
    }

    private void b(EGLContext eGLContext, Surface surface) {
        f();
        synchronized (this) {
            this.a = new HandlerThread("TXGLSurfaceRenderThread");
            this.a.start();
            this.b = new f(this.a.getLooper());
            this.b.a((a) this);
            this.b.d = eGLContext;
            this.b.c = surface;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("surface-render: create gl thread ");
            stringBuilder.append(this.a.getName());
            TXCLog.w("TXGLSurfaceRenderThread", stringBuilder.toString());
        }
        a(100);
    }

    private void f() {
        synchronized (this) {
            if (this.b != null) {
                f.a(this.b, this.a);
                TXCLog.w("TXGLSurfaceRenderThread", "surface-render: destroy gl thread");
            }
            this.b = null;
            this.a = null;
        }
    }

    private void a(int i) {
        synchronized (this) {
            if (this.b != null) {
                this.b.sendEmptyMessage(i);
            }
        }
    }

    private void b(int i, boolean z, int i2, int i3, int i4, int i5, int i6, boolean z2) {
        boolean z3 = z;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        int i10 = i5;
        int i11 = i6;
        if (i9 != 0 && i8 != 0 && i10 != 0 && i11 != 0 && this.c != null) {
            if (this.l) {
                this.l = false;
                return;
            }
            if (z2) {
                GLES20.glClearColor(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
                GLES20.glClear(16640);
                GLES20.glBindFramebuffer(36160, 0);
                if (this.b != null) {
                    this.b.c();
                }
                this.l = true;
            }
            this.h = i8;
            this.i = i9;
            GLES20.glViewport(0, 0, i8, i9);
            float f = i9 != 0 ? ((float) i8) / ((float) i9) : 1.0f;
            float f2 = i11 != 0 ? ((float) i10) / ((float) i11) : 1.0f;
            if (!(this.e == z3 && this.d == i7 && this.f == f && this.g == f2)) {
                this.e = z3;
                this.d = i7;
                this.f = f;
                this.g = f2;
                int i12 = (720 - this.d) % 360;
                Object obj = (i12 == 90 || i12 == 270) ? 1 : null;
                int i13 = obj != null ? i9 : i8;
                if (obj == null) {
                    i8 = i9;
                }
                this.c.a(i5, i6, i12, k.a(j.NORMAL, false, true), ((float) i13) / ((float) i8), obj != null ? false : this.e, obj != null ? this.e : false);
                if (obj != null) {
                    this.c.g();
                } else {
                    this.c.h();
                }
            }
            GLES20.glClearColor(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
            GLES20.glClear(16640);
            GLES20.glBindFramebuffer(36160, 0);
            this.c.b(i);
            g();
            if (this.b != null) {
                this.b.c();
            }
        }
    }

    private void g() {
        if (this.j) {
            if (!(this.h == 0 || this.i == 0)) {
                Object obj = this.h <= this.i ? 1 : null;
                int i = this.i >= this.h ? this.i : this.h;
                int i2 = this.i >= this.h ? this.h : this.i;
                if (obj != null) {
                    int i3 = i2;
                    i2 = i;
                    i = i3;
                }
                Buffer allocate = ByteBuffer.allocate((i * i2) * 4);
                Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
                allocate.position(0);
                GLES20.glReadPixels(0, 0, i, i2, 6408, 5121, allocate);
                final n nVar = this.k;
                if (nVar != null) {
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
                            nVar.onTakePhotoComplete(Bitmap.createBitmap(bitmap, 0, 0, i4, i5, matrix, false));
                            bitmap.recycle();
                        }
                    }).start();
                }
            }
            this.k = null;
            this.j = false;
        }
    }
}

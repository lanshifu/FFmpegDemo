package com.tencent.liteav.basic.e;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.os.HandlerThread;
import android.os.Message;
import com.tencent.liteav.basic.log.TXCLog;
import javax.microedition.khronos.egl.EGLContext;

/* compiled from: TXCGLSurfaceTextureThread */
public class e implements a, l {
    public int a = 25;
    private volatile HandlerThread b = null;
    private volatile f c = null;
    private m d;
    private int[] e = null;
    private SurfaceTexture f = null;
    private boolean g = false;
    private long h = 0;
    private long i = 0;
    private float[] j = new float[16];

    public void a(int i, boolean z, int i2, int i3, int i4) {
    }

    public void a(int i) {
        this.a = i;
        b();
    }

    public void a() {
        f();
    }

    public void setSurfaceTextureListener(m mVar) {
        this.d = mVar;
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.f;
    }

    public EGLContext getGLContext() {
        EGLContext a;
        synchronized (this) {
            a = this.c != null ? this.c.a() : null;
        }
        return a;
    }

    public void a(Runnable runnable) {
        synchronized (this) {
            if (this.c != null) {
                this.c.post(runnable);
            }
        }
    }

    public void a(boolean z) {
        synchronized (this) {
            try {
                if (this.c != null) {
                    this.c.removeCallbacksAndMessages(null);
                }
                this.g = false;
                if (this.f != null) {
                    if (this.e != null) {
                        this.f.updateTexImage();
                        this.f.setOnFrameAvailableListener(new OnFrameAvailableListener() {
                            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                                e.this.a(103, (Runnable) new Runnable() {
                                    public void run() {
                                        e.this.g = true;
                                        e.this.b(102);
                                    }
                                });
                                surfaceTexture.setOnFrameAvailableListener(null);
                            }
                        });
                        return;
                    }
                }
                try {
                } catch (Throwable th) {
                }
            } catch (Exception unused) {
            }
        }
    }

    public void c() {
        h();
    }

    public void d() {
        a(102, 5);
        if (i() && this.f != null && this.e != null) {
            try {
                this.f.updateTexImage();
                this.f.getTransformMatrix(this.j);
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onMsgRend Exception ");
                stringBuilder.append(e.getMessage());
                TXCLog.e("TXGLSurfaceTextureThread", stringBuilder.toString());
                e.printStackTrace();
            }
            m mVar = this.d;
            if (mVar != null) {
                mVar.onTextureProcess(this.e[0], this.j);
            }
        }
    }

    public void e() {
        g();
    }

    private void b() {
        f();
        synchronized (this) {
            this.b = new HandlerThread("TXGLSurfaceTextureThread");
            this.b.start();
            this.c = new f(this.b.getLooper());
            this.c.a((a) this);
            this.c.a = 1280;
            this.c.b = 720;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("create gl thread ");
            stringBuilder.append(this.b.getName());
            TXCLog.w("TXGLSurfaceTextureThread", stringBuilder.toString());
        }
        b(100);
    }

    private void f() {
        synchronized (this) {
            if (this.c != null) {
                f.a(this.c, this.b);
                TXCLog.w("TXGLSurfaceTextureThread", "destroy gl thread");
            }
            this.c = null;
            this.b = null;
        }
    }

    private void a(int i, long j) {
        synchronized (this) {
            if (this.c != null) {
                this.c.sendEmptyMessageDelayed(i, j);
            }
        }
    }

    private void b(int i) {
        synchronized (this) {
            if (this.c != null) {
                this.c.sendEmptyMessage(i);
            }
        }
    }

    private void a(int i, Runnable runnable) {
        synchronized (this) {
            if (this.c != null) {
                Message message = new Message();
                message.what = i;
                message.obj = runnable;
                this.c.sendMessage(message);
            }
        }
    }

    private void g() {
        TXCLog.w("TXGLSurfaceTextureThread", "destroy surface texture ");
        m mVar = this.d;
        if (mVar != null) {
            mVar.onSurfaceTextureDestroy(this.f);
        }
        if (this.f != null) {
            this.f.setOnFrameAvailableListener(null);
            this.f.release();
            this.g = false;
            this.f = null;
        }
        if (this.e != null) {
            GLES20.glDeleteTextures(1, this.e, 0);
            this.e = null;
        }
    }

    private void h() {
        TXCLog.w("TXGLSurfaceTextureThread", "init surface texture ");
        this.e = new int[1];
        this.e[0] = i.b();
        if (this.e[0] <= 0) {
            this.e = null;
            return;
        }
        this.f = new SurfaceTexture(this.e[0]);
        this.f.setDefaultBufferSize(1280, 720);
        this.f.setOnFrameAvailableListener(new OnFrameAvailableListener() {
            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                e.this.a(103, (Runnable) new Runnable() {
                    public void run() {
                        e.this.g = true;
                        e.this.b(102);
                    }
                });
                surfaceTexture.setOnFrameAvailableListener(null);
            }
        });
        m mVar = this.d;
        if (mVar != null) {
            mVar.onSurfaceTextureAvailable(this.f);
        }
    }

    private boolean i() {
        if (this.g) {
            long nanoTime = System.nanoTime();
            if (nanoTime < this.i + ((((this.h * 1000) * 1000) * 1000) / ((long) this.a))) {
                return false;
            }
            if (this.i == 0) {
                this.i = nanoTime;
            } else if (nanoTime > this.i + 1000000000) {
                this.h = 0;
                this.i = nanoTime;
            }
            this.h++;
            return true;
        }
        this.h = 0;
        this.i = System.nanoTime();
        return false;
    }
}

package com.tencent.liteav.screencapture;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import android.view.WindowManager;
import com.tencent.liteav.basic.e.b;
import com.tencent.liteav.basic.e.h;
import com.tencent.liteav.basic.e.i;
import com.tencent.liteav.basic.e.j;
import com.tencent.liteav.basic.e.k;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGLContext;

/* compiled from: TXCScreenCapture */
public class a {
    protected Handler a = null;
    protected volatile HandlerThread b = null;
    protected volatile a c = null;
    protected volatile WeakReference<c> d = null;
    protected volatile int e = 0;
    protected int f = 720;
    protected int g = 1280;
    protected int h = 20;
    protected boolean i = false;
    private Object j = null;
    private boolean k = false;
    private int l = this.f;
    private int m = this.g;
    private Context n = null;
    private com.tencent.liteav.screencapture.b.a o = new com.tencent.liteav.screencapture.b.a() {
        public void a(int i) {
            a.this.b(i);
            a.this.b(105, a.this.l, a.this.m);
        }
    };

    /* compiled from: TXCScreenCapture */
    protected class a extends Handler {
        public int a = 0;
        public int[] b = null;
        public Surface c = null;
        public SurfaceTexture d = null;
        public int e = 720;
        public int f = 1280;
        public int g = 25;
        protected boolean h = false;
        protected long i = 0;
        protected long j = 0;
        protected b k = null;
        protected h l = null;
        float[] m = new float[16];

        public a(Looper looper, a aVar) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message != null) {
                if (this.a == a.this.e || 101 == message.what) {
                    switch (message.what) {
                        case 100:
                            a(message);
                            break;
                        case 101:
                            b(message);
                            break;
                        case 102:
                            try {
                                c(message);
                                break;
                            } catch (Exception unused) {
                                break;
                            }
                        case 103:
                            d(message);
                            break;
                        case 105:
                            e(message);
                            break;
                    }
                    if (!(message == null || message.obj == null)) {
                        ((Runnable) message.obj).run();
                    }
                }
            }
        }

        /* Access modifiers changed, original: protected */
        public void a(Message message) {
            this.i = 0;
            this.j = 0;
            if (a()) {
                a.this.a(0, this.k.c());
                return;
            }
            b();
            a.this.b();
            a.this.a(20000003, null);
        }

        /* Access modifiers changed, original: protected */
        public void b(Message message) {
            c c = a.this.c();
            if (c != null) {
                c.a(a.this.j);
            }
            b();
        }

        /* Access modifiers changed, original: protected */
        public void c(Message message) {
            a.this.a(102, 5);
            if (!a.this.i) {
                return;
            }
            if (this.h) {
                long nanoTime = System.nanoTime();
                if (nanoTime >= this.j + ((((this.i * 1000) * 1000) * 1000) / ((long) this.g))) {
                    if (this.j == 0) {
                        this.j = nanoTime;
                    } else if (nanoTime > this.j + 1000000000) {
                        this.i = 0;
                        this.j = nanoTime;
                    }
                    this.i++;
                    if (this.d != null && this.b != null) {
                        this.d.getTransformMatrix(this.m);
                        try {
                            this.d.updateTexImage();
                        } catch (Exception e) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("onMsgRend Exception ");
                            stringBuilder.append(e.getMessage());
                            TXCLog.e("ScreenCapture", stringBuilder.toString());
                            e.printStackTrace();
                        }
                        this.l.a(this.m);
                        GLES20.glViewport(0, 0, this.e, this.f);
                        a.this.a(0, this.l.a(this.b[0]), this.e, this.f, TXCTimeUtil.getTimeTick());
                        return;
                    }
                    return;
                }
                return;
            }
            this.i = 0;
            this.j = System.nanoTime();
        }

        /* Access modifiers changed, original: protected */
        public void d(Message message) {
            if (message != null) {
                int i = 1;
                if (message.arg1 >= 1) {
                    i = message.arg1;
                }
                this.g = i;
                this.i = 0;
                this.j = 0;
            }
        }

        /* Access modifiers changed, original: protected */
        public void e(Message message) {
            if (message != null) {
                this.e = message.arg1;
                this.f = message.arg2;
                c();
                this.l.a(this.e, this.f);
                d();
                TXCLog.d("ScreenCapture", String.format("set screen capture size[%d/%d]", new Object[]{Integer.valueOf(a.this.l), Integer.valueOf(a.this.m)}));
            }
        }

        /* Access modifiers changed, original: protected */
        public boolean a() {
            TXCLog.d("ScreenCapture", String.format("init egl size[%d/%d]", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.f)}));
            this.k = b.a(null, null, null, this.e, this.f);
            if (this.k == null) {
                return false;
            }
            this.l = new h();
            if (!this.l.c()) {
                return false;
            }
            this.l.a(true);
            this.l.a(this.e, this.f);
            this.l.a(k.e, k.a(j.NORMAL, false, false));
            d();
            return true;
        }

        /* Access modifiers changed, original: protected */
        public void b() {
            c();
            if (this.l != null) {
                this.l.e();
                this.l = null;
            }
            if (this.k != null) {
                this.k.b();
                this.k = null;
            }
        }

        /* Access modifiers changed, original: protected */
        public void c() {
            if (this.d != null) {
                this.d.setOnFrameAvailableListener(null);
                this.d.release();
                this.h = false;
                this.d = null;
            }
            b.a().a(this.c);
            if (this.c != null) {
                this.c.release();
                this.c = null;
            }
            if (this.b != null) {
                GLES20.glDeleteTextures(1, this.b, 0);
                this.b = null;
            }
        }

        /* Access modifiers changed, original: protected */
        public void d() {
            this.b = new int[1];
            this.b[0] = i.b();
            if (this.b[0] <= 0) {
                this.b = null;
                return;
            }
            this.d = new SurfaceTexture(this.b[0]);
            this.c = new Surface(this.d);
            this.d.setDefaultBufferSize(this.e, this.f);
            this.d.setOnFrameAvailableListener(new OnFrameAvailableListener() {
                public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                    a.this.a(104, new Runnable() {
                        public void run() {
                            a.this.h = true;
                            a.this.a(102);
                        }
                    });
                    surfaceTexture.setOnFrameAvailableListener(null);
                }
            });
            b.a().a(this.c, this.e, this.f);
        }
    }

    public a(Context context, boolean z) {
        this.n = context;
        this.a = new Handler(Looper.getMainLooper());
        this.k = z;
        if (VERSION.SDK_INT >= 21) {
            b.a().a(context);
        }
    }

    public int a(int i, int i2, int i3) {
        int rotation;
        if (this.k) {
            rotation = ((WindowManager) this.n.getSystemService("window")).getDefaultDisplay().getRotation();
            if (rotation == 0 || rotation == 2) {
                if (i > i2) {
                    this.f = i2;
                    this.g = i;
                } else {
                    this.f = i;
                    this.g = i2;
                }
            } else if (i < i2) {
                this.f = i2;
                this.g = i;
            } else {
                this.f = i;
                this.g = i2;
            }
        } else {
            this.f = i;
            this.g = i2;
            rotation = 0;
        }
        TXCLog.d("ScreenCapture", String.format("start screen capture orientation[%d] input size[%d/%d] output size[%d/%d]", new Object[]{Integer.valueOf(rotation), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.f), Integer.valueOf(this.g)}));
        this.h = i3;
        if (VERSION.SDK_INT < 21) {
            a(20000004, null);
            return 20000004;
        }
        this.l = this.f;
        this.m = this.g;
        a();
        if (this.k) {
            b.a().a(this.o);
        }
        return 0;
    }

    public void a(Object obj) {
        b.a().b(this.o);
        this.j = obj;
        b();
    }

    public void a(final boolean z) {
        synchronized (this) {
            if (this.c != null) {
                this.c.post(new Runnable() {
                    public void run() {
                        a.this.i = z;
                    }
                });
            } else {
                this.i = z;
            }
        }
    }

    public void a(c cVar) {
        this.d = new WeakReference(cVar);
    }

    public void a(com.tencent.liteav.basic.d.a aVar) {
        b.a().a(aVar);
    }

    public synchronized void a(Runnable runnable) {
        if (this.c != null) {
            this.c.post(runnable);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        b();
        synchronized (this) {
            this.b = new HandlerThread("ScreenCaptureGLThread");
            this.b.start();
            this.c = new a(this.b.getLooper(), this);
            int i = 1;
            this.e++;
            this.c.a = this.e;
            this.c.e = this.l;
            this.c.f = this.m;
            a aVar = this.c;
            if (this.h >= 1) {
                i = this.h;
            }
            aVar.g = i;
        }
        a(100);
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        synchronized (this) {
            this.e++;
            if (this.c != null) {
                final HandlerThread handlerThread = this.b;
                final a aVar = this.c;
                a(101, new Runnable() {
                    public void run() {
                        a.this.a.post(new Runnable() {
                            public void run() {
                                if (aVar != null) {
                                    aVar.removeCallbacksAndMessages(null);
                                }
                                if (handlerThread == null) {
                                    return;
                                }
                                if (VERSION.SDK_INT >= 18) {
                                    handlerThread.quitSafely();
                                } else {
                                    handlerThread.quit();
                                }
                            }
                        });
                    }
                });
            }
            this.c = null;
            this.b = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public c c() {
        return this.d == null ? null : (c) this.d.get();
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, long j) {
        synchronized (this) {
            if (this.c != null) {
                this.c.sendEmptyMessageDelayed(i, j);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(int i) {
        synchronized (this) {
            if (this.c != null) {
                this.c.sendEmptyMessage(i);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(int i, int i2, int i3) {
        synchronized (this) {
            if (this.c != null) {
                Message message = new Message();
                message.what = i;
                message.arg1 = i2;
                message.arg2 = i3;
                this.c.sendMessage(message);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, Runnable runnable) {
        synchronized (this) {
            if (this.c != null) {
                Message message = new Message();
                message.what = i;
                message.obj = runnable;
                this.c.sendMessage(message);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, EGLContext eGLContext) {
        c c = c();
        if (c != null) {
            c.a(i, eGLContext);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, int i2, int i3, int i4, long j) {
        c c = c();
        if (c != null) {
            c.a(i, i2, i3, i4, j);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(int i) {
        if (i == 0) {
            this.l = this.f < this.g ? this.f : this.g;
            this.m = this.f < this.g ? this.g : this.f;
        } else {
            this.l = this.f < this.g ? this.g : this.f;
            this.m = this.f < this.g ? this.f : this.g;
        }
        TXCLog.d("ScreenCapture", String.format("reset screen capture angle[%d] output size[%d/%d]", new Object[]{Integer.valueOf(i), Integer.valueOf(this.l), Integer.valueOf(this.m)}));
    }
}

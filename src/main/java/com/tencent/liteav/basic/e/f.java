package com.tencent.liteav.basic.e;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import javax.microedition.khronos.egl.EGLContext;

/* compiled from: TXCGLThreadHandler */
public class f extends Handler {
    public int a = 720;
    public int b = 1280;
    public Surface c = null;
    public EGLContext d = null;
    protected b e = null;
    private a f = null;

    /* compiled from: TXCGLThreadHandler */
    interface a {
        void c();

        void d();

        void e();
    }

    public static void a(final Handler handler, final HandlerThread handlerThread) {
        if (handler != null && handlerThread != null) {
            Message message = new Message();
            message.what = 101;
            message.obj = new Runnable() {
                public void run() {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            if (handler != null) {
                                handler.removeCallbacksAndMessages(null);
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
            };
            handler.sendMessage(message);
        }
    }

    public f(Looper looper) {
        super(looper);
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public EGLContext a() {
        return this.e != null ? this.e.c() : null;
    }

    public Surface b() {
        return this.c;
    }

    public void c() {
        if (this.e != null) {
            this.e.a();
        }
    }

    public void handleMessage(Message message) {
        if (message != null) {
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
            }
            if (!(message == null || message.obj == null)) {
                ((Runnable) message.obj).run();
            }
        }
    }

    private void a(Message message) {
        try {
            d();
        } catch (Exception unused) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("surface-render: init egl context exception ");
            stringBuilder.append(this.c);
            TXCLog.e("TXGLThreadHandler", stringBuilder.toString());
            this.c = null;
        }
    }

    private void b(Message message) {
        e();
    }

    private void c(Message message) {
        try {
            if (this.f != null) {
                this.f.d();
            }
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onMsgRend Exception ");
            stringBuilder.append(e.getMessage());
            TXCLog.e("TXGLThreadHandler", stringBuilder.toString());
            e.printStackTrace();
        }
    }

    private boolean d() {
        TXCLog.d("TXGLThreadHandler", String.format("init egl size[%d/%d]", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b)}));
        this.e = b.a(null, this.d, this.c, this.a, this.b);
        if (this.e == null) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("surface-render: create egl context ");
        stringBuilder.append(this.c);
        TXCLog.w("TXGLThreadHandler", stringBuilder.toString());
        if (this.f != null) {
            this.f.c();
        }
        return true;
    }

    private void e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("surface-render: destroy egl context ");
        stringBuilder.append(this.c);
        TXCLog.w("TXGLThreadHandler", stringBuilder.toString());
        if (this.f != null) {
            this.f.e();
        }
        if (this.e != null) {
            this.e.b();
            this.e = null;
        }
        this.c = null;
    }
}

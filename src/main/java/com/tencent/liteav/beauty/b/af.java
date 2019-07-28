package com.tencent.liteav.beauty.b;

import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.os.Handler;
import android.os.Looper;

/* compiled from: TXCGPUVideoPlayerFilter */
public class af {
    private static final String b = "af";
    OnFrameAvailableListener a;
    private SurfaceTexture c;
    private int d = -1;
    private boolean e = false;
    private MediaExtractor f;
    private AssetFileDescriptor g;
    private int h = -1;
    private int i = -1;
    private int j = -1;
    private int k = -1;
    private long l;
    private MediaCodec m;
    private boolean n = false;
    private boolean o;
    private Handler p;
    private Object q = new Object();

    af() {
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void a() {
        synchronized (this.q) {
            if (this.p != null) {
                if (Looper.myLooper() == this.p.getLooper()) {
                    c();
                } else {
                    AnonymousClass1 anonymousClass1 = new Runnable() {
                        public void run() {
                            synchronized (af.this.q) {
                                af.this.c();
                                af.this.q.notify();
                            }
                        }
                    };
                    this.p.removeCallbacksAndMessages(null);
                    this.p.post(anonymousClass1);
                    this.p.getLooper().quitSafely();
                    while (true) {
                        try {
                            this.q.wait();
                            break;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    while (true) {
                    }
                }
            }
        }
    }

    private void b() {
        if (this.e) {
            this.e = false;
            if (this.f != null) {
                this.f.release();
                this.f = null;
            }
            try {
                this.m.stop();
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    this.m = null;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                try {
                } catch (Exception e22) {
                    e22.printStackTrace();
                } catch (Throwable th2) {
                    this.m = null;
                }
            } finally {
                try {
                    this.m.release();
                } catch (Exception e3) {
                    e3.printStackTrace();
                } catch (Throwable th3) {
                    this.m = null;
                }
                this.m = null;
            }
        }
    }

    private void c() {
        b();
        this.a = null;
        this.l = 0;
        this.o = false;
        if (this.c != null) {
            this.c.release();
            this.c = null;
        }
        synchronized (this.q) {
            if (this.p != null) {
                this.p.removeCallbacksAndMessages(null);
                this.p.getLooper().quit();
                this.p = null;
                this.q.notify();
            }
        }
        if (this.g != null) {
            try {
                this.g.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.g = null;
        }
    }
}

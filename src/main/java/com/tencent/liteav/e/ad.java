package com.tencent.liteav.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.h;
import com.tencent.liteav.c.i;
import com.tencent.liteav.c.j;
import com.tencent.liteav.c.k;
import com.tencent.liteav.i.b.a;
import com.tencent.liteav.i.b.e;
import com.tencent.liteav.muxer.c;
import com.tencent.liteav.videoencoder.b;

/* compiled from: VideoProcessGenerate */
public class ad extends e {
    private e n;
    private a o;
    private Handler p;
    private s q;

    /* Access modifiers changed, original: protected */
    public int a(int i, int i2, int i3, long j) {
        return i;
    }

    /* Access modifiers changed, original: protected */
    public void e() {
    }

    public ad(Context context) {
        super(context);
        this.p = new Handler(Looper.getMainLooper());
        this.q = new s() {
            public void a(int i, long j, Bitmap bitmap) {
                if (ad.this.o != null) {
                    ad.this.o.a(i, j / 1000, bitmap);
                }
                if (i.a().r) {
                    int c = h.a().c();
                    if (c == 0) {
                        ad.this.b();
                        if (ad.this.n != null) {
                            ad.this.g();
                        }
                        return;
                    }
                    final float f = (((float) (i + 1)) * 1.0f) / ((float) c);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("index:");
                    stringBuilder.append(i);
                    stringBuilder.append(",count= ");
                    stringBuilder.append(c);
                    stringBuilder.append(",progress:");
                    stringBuilder.append(f);
                    TXCLog.i("VideoProcessGenerate", stringBuilder.toString());
                    ad.this.p.post(new Runnable() {
                        public void run() {
                            if (ad.this.n != null) {
                                ad.this.n.a(f);
                                if (f >= 1.0f) {
                                    ad.this.g();
                                    ad.this.b();
                                }
                            }
                        }
                    });
                }
            }
        };
        this.c = new v();
        this.f.a(this.q);
    }

    public void a(e eVar) {
        this.n = eVar;
    }

    public void a(a aVar) {
        this.o = aVar;
    }

    public void a() {
        a(k.a().a);
        f();
        super.a();
        int i = 1;
        int i2 = 0;
        boolean z = this.l.h.a < 1280 && this.l.h.b < 1280;
        this.b = z;
        if (this.b) {
            i = 2;
        }
        this.h = new b(i);
        Context context = this.a;
        if (!this.b) {
            i2 = 2;
        }
        this.i = new c(context, i2);
        if (!this.l.r) {
            this.l.f();
            this.i.a(this.l.o);
        }
    }

    /* Access modifiers changed, original: protected */
    public void d() {
        k.a().a = i.a().o;
        j.a().a(0);
        this.p.post(new Runnable() {
            public void run() {
                if (ad.this.n != null) {
                    com.tencent.liteav.i.a.c cVar = new com.tencent.liteav.i.a.c();
                    cVar.a = 0;
                    cVar.b = "Generate Complete";
                    ad.this.n.a(cVar);
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void a(final long j) {
        this.p.post(new Runnable() {
            public void run() {
                if (ad.this.n != null) {
                    long j = ad.this.l.k;
                    if (j > 0) {
                        ad.this.n.a((((float) (j - com.tencent.liteav.c.c.a().f())) * 1.0f) / ((float) j));
                    }
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void f() {
        long g = com.tencent.liteav.c.c.a().g() - com.tencent.liteav.c.c.a().f();
        if (g <= 0) {
            g = this.c.c();
        }
        h.a().a(g);
    }

    private void g() {
        com.tencent.liteav.i.a.c cVar = new com.tencent.liteav.i.a.c();
        cVar.a = 0;
        cVar.b = "Generate Complete";
        this.n.a(cVar);
        TXCLog.i("VideoProcessGenerate", "===onProcessComplete===");
    }

    public void c() {
        super.c();
        this.q = null;
    }
}

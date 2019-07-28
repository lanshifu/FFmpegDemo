package com.tencent.liteav.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.h;
import com.tencent.liteav.c.i;
import com.tencent.liteav.c.k;
import com.tencent.liteav.i.a.c;
import com.tencent.liteav.i.b.a;
import com.tencent.liteav.i.b.e;

/* compiled from: VideoAverageThumbnailGenerate */
public class u extends e {
    private e n;
    private a o;
    private Handler p;
    private s q;

    /* Access modifiers changed, original: protected */
    public int a(int i, int i2, int i3, long j) {
        return i;
    }

    /* Access modifiers changed, original: protected */
    public void a(long j) {
    }

    /* Access modifiers changed, original: protected */
    public void d() {
    }

    /* Access modifiers changed, original: protected */
    public void e() {
    }

    public u(Context context) {
        super(context);
        this.p = new Handler(Looper.getMainLooper());
        this.q = new s() {
            public void a(int i, long j, Bitmap bitmap) {
                u.this.c.p();
                if (u.this.o != null) {
                    u.this.o.a(i, j / 1000, bitmap);
                }
                int c = h.a().c();
                if (c == 0) {
                    u.this.b();
                    if (u.this.n != null) {
                        u.this.g();
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
                TXCLog.i("VideoAverageThumbnailGenerate", stringBuilder.toString());
                u.this.p.post(new Runnable() {
                    public void run() {
                        if (f >= 1.0f) {
                            u.this.b();
                        }
                        if (u.this.n != null) {
                            u.this.n.a(f);
                            if (f >= 1.0f) {
                                u.this.g();
                                TXCLog.i("VideoAverageThumbnailGenerate", "===onProcessComplete===");
                            }
                        }
                    }
                });
            }
        };
        this.c = new af();
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
    }

    /* Access modifiers changed, original: protected */
    public void f() {
        h.a().a(this.c.c());
        this.c.a(h.a().b());
    }

    private void g() {
        i.a().n = true;
        c cVar = new c();
        cVar.a = 0;
        cVar.b = "Generate Complete";
        this.n.a(cVar);
    }

    public void c() {
        if (this.f != null) {
            this.f.a(null);
        }
        this.q = null;
        super.c();
    }

    public void b(boolean z) {
        if (this.c != null) {
            this.c.a(z);
        }
    }
}

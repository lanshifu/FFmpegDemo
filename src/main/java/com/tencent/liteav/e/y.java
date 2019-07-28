package com.tencent.liteav.e;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.k;
import com.tencent.liteav.i.a;
import com.tencent.liteav.i.b.b;
import com.tencent.liteav.i.b.c;

/* compiled from: VideoEditGenerate */
public class y extends e {
    private c n;
    private b o;
    private Handler p = new Handler(Looper.getMainLooper());

    public y(Context context) {
        super(context);
    }

    public void a(c cVar) {
        this.n = cVar;
    }

    public void a(b bVar) {
        this.o = bVar;
    }

    public void a() {
        int i = 0;
        int i2 = 1;
        if (k.a().d() == 1) {
            a(k.a().a);
            if (k.a().e() != 0) {
                if (this.n != null) {
                    a.c cVar = new a.c();
                    cVar.a = 0;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Generate Fail,Cause: Video Source Path illegal : ");
                    stringBuilder.append(k.a().a);
                    cVar.b = stringBuilder.toString();
                    TXCLog.d("VideoEditGenerate", "onGenerateComplete");
                    this.n.a(cVar);
                }
                return;
            }
        } else if (k.a().d() == 2) {
            a(k.a().b(), k.a().c());
        }
        if (!this.l.b()) {
            this.l.g();
            super.a();
            boolean z = this.l.h.a < 1280 && this.l.h.b < 1280;
            this.b = z;
            if (this.b) {
                i2 = 2;
            }
            this.h = new com.tencent.liteav.videoencoder.b(i2);
            Context context = this.a;
            if (!this.b) {
                i = 2;
            }
            this.i = new com.tencent.liteav.muxer.c(context, i);
            this.i.a(this.l.i);
            this.k.a(this.m);
        }
    }

    public void b() {
        super.b();
        this.k.a(null);
    }

    /* Access modifiers changed, original: protected */
    public void d() {
        n.a().b();
        this.p.post(new Runnable() {
            public void run() {
                if (y.this.n != null) {
                    a.c cVar = new a.c();
                    cVar.a = 0;
                    cVar.b = "Generate Complete";
                    TXCLog.d("VideoEditGenerate", "===onGenerateComplete===");
                    y.this.n.a(cVar);
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void a(final long j) {
        this.p.post(new Runnable() {
            public void run() {
                if (y.this.n != null) {
                    long j = y.this.l.k;
                    if (j > 0) {
                        float f = (((float) (j - com.tencent.liteav.c.c.a().f())) * 1.0f) / ((float) j);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onGenerateProgress timestamp:");
                        stringBuilder.append(j);
                        stringBuilder.append(",progress:");
                        stringBuilder.append(f);
                        stringBuilder.append(",duration:");
                        stringBuilder.append(j);
                        TXCLog.d("VideoEditGenerate", stringBuilder.toString());
                        y.this.n.a(f);
                    }
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public int a(int i, int i2, int i3, long j) {
        return this.o != null ? this.o.a(i, i2, i3, j) : i;
    }

    /* Access modifiers changed, original: protected */
    public void e() {
        if (this.o != null) {
            this.o.a();
        }
    }
}

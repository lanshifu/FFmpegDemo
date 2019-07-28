package com.tencent.liteav.b;

import android.content.Context;
import android.opengl.EGLContext;
import android.os.Handler;
import android.os.Looper;
import com.tencent.liteav.b.a.b;
import com.tencent.liteav.b.a.c;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.g.t;
import com.tencent.liteav.i.a.d;
import com.tencent.liteav.i.c.a;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* compiled from: TXCombineVideo */
public class f {
    private t a;
    private Context b;
    private a c;
    private d d;
    private c e;
    private c f;
    private b g;
    private List<String> h;
    private Handler i = new Handler(Looper.getMainLooper());

    static {
        com.tencent.liteav.basic.util.b.e();
    }

    public f(Context context) {
        this.b = context;
        this.e = new c(this.b);
        this.d = new d(this.b);
        this.a = t.a();
        c();
    }

    private void c() {
        this.f = new c() {
            public void a(EGLContext eGLContext) {
                f.this.d.a(f.this.e.b());
                f.this.d.b(f.this.e.a());
                f.this.d.a(eGLContext);
            }

            public void a(int i, int i2, int i3, e eVar) {
                f.this.d.a(i, i2, i3, eVar);
            }

            public void a(e eVar) {
                f.this.d.a(eVar);
            }

            public void b(e eVar) {
                f.this.d.b(eVar);
            }

            public void c(e eVar) {
                f.this.d.c(eVar);
            }
        };
        this.g = new b() {
            public void a(final float f) {
                f.this.i.post(new Runnable() {
                    public void run() {
                        if (f.this.c != null) {
                            f.this.c.a(f);
                        }
                    }
                });
            }

            public void a(final int i, final String str) {
                TXCLog.e("TXCombineVideo", "===onEncodedComplete===");
                f.this.i.post(new Runnable() {
                    public void run() {
                        if (f.this.c != null) {
                            d dVar = new d();
                            dVar.a = i;
                            dVar.b = str;
                            if (i == 0) {
                                f.this.c.a(1.0f);
                            }
                            f.this.c.a(dVar);
                        }
                    }
                });
            }
        };
        this.e.a(this.f);
        this.d.a(this.g);
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(List<String> list) {
        this.h = list;
    }

    public void a(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.d.a(str);
    }

    public void a() {
        this.e.a(this.h);
        this.d.a(this.a.n());
        this.e.c();
    }

    public void b() {
        this.e.d();
        this.d.a();
    }

    public void a(List<com.tencent.liteav.i.a.a> list, int i, int i2) {
        i = ((i + 15) / 16) * 16;
        i2 = ((i2 + 15) / 16) * 16;
        this.d.a(i, i2);
        this.e.a((List) list, i, i2);
    }
}

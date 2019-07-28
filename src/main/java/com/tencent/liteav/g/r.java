package com.tencent.liteav.g;

import android.content.Context;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.d.g;
import com.tencent.liteav.e.b;
import com.tencent.liteav.e.b.a;
import com.tencent.liteav.e.i;
import com.tencent.liteav.e.l;
import com.tencent.liteav.i.a.f;
import com.tencent.liteav.i.c;
import java.util.List;

/* compiled from: VideoJoinPreview */
public class r {
    private final String a = "VideoJoinPreview";
    private o b;
    private m c;
    private q d;
    private b e;
    private com.tencent.liteav.f.b f;
    private boolean g;
    private List<Surface> h;
    private c.b i;
    private c j = new c() {
        public void a(e eVar, i iVar) {
            if (r.this.b != null) {
                r.this.b.a(eVar, iVar);
            }
        }
    };
    private a k = new a() {
        public void a(e eVar, i iVar) {
            if (r.this.f != null) {
                r.this.f.a(eVar);
            }
        }
    };
    private d l = new d() {
        public void a(List<Surface> list) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onSurfaceTextureAvailable, mStartPlay = ");
            stringBuilder.append(r.this.g);
            TXCLog.i("VideoJoinPreview", stringBuilder.toString());
            r.this.h = list;
            if (r.this.d != null) {
                r.this.d.a();
                r.this.d.b();
                r.this.d.a(r.this.m);
            }
            if (r.this.g) {
                r.this.e();
            }
        }

        public void a(int i, int i2) {
            if (r.this.d != null) {
                g gVar = new g();
                gVar.a = i;
                gVar.b = i2;
                r.this.d.a(gVar);
            }
        }

        public void b(List<Surface> list) {
            TXCLog.i("VideoJoinPreview", "onSurfaceTextureDestroy");
            r.this.h = null;
            if (r.this.d != null) {
                r.this.d.c();
                r.this.d.d();
                r.this.d.a(null);
            }
        }

        public int a(int i, float[] fArr, e eVar) {
            if (eVar.p()) {
                r.this.f();
                return 0;
            }
            if (r.this.d != null) {
                r.this.d.a(fArr);
                r.this.d.a(i, eVar);
                r.this.a(eVar.e());
            }
            return 0;
        }
    };
    private l m = new l() {
        public int b(int i, e eVar) {
            return i;
        }

        public void a(int i, e eVar) {
            if (r.this.b != null) {
                r.this.b.a(i, r.this.b.a(), r.this.b.b());
            }
        }
    };
    private i n = new i() {
        public void a(e eVar) {
            if (eVar != null && eVar.b() != null) {
                if (eVar.p()) {
                    r.this.f();
                    return;
                }
                if (r.this.e != null) {
                    r.this.e.a(eVar);
                }
                if (r.this.f != null) {
                    r.this.f.i();
                }
            }
        }
    };
    private a o = new a() {
        public void a(int i) {
            r.this.c.a(i <= 5);
        }
    };
    private Handler p = new Handler(Looper.getMainLooper());

    public r(Context context) {
        this.b = new o(context);
        this.b.a(this.l);
        this.d = new q(context);
        this.c = new m();
        this.e = new b();
    }

    public void a(c.b bVar) {
        this.i = bVar;
    }

    public void a(f fVar) {
        this.b.a(fVar);
    }

    public void a() {
        this.g = true;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("startPlay mStartPlay:");
        stringBuilder.append(this.g);
        TXCLog.i("VideoJoinPreview", stringBuilder.toString());
        if (this.h != null) {
            e();
        }
    }

    public void b() {
        this.g = false;
        TXCLog.i("VideoJoinPreview", "stopPlay mStartPlay false");
        if (this.c != null) {
            this.c.b();
            this.c.a(null);
            this.c.a(null);
        }
        if (this.f != null) {
            this.f.d();
            this.f.a(null);
            this.f.b();
            this.f = null;
        }
        if (this.e != null) {
            this.e.a(null);
            this.e.d();
        }
        if (this.b != null) {
            this.b.d();
        }
    }

    public void c() {
        this.g = false;
        if (this.c != null) {
            this.c.c();
        }
        if (this.e != null) {
            this.e.a();
        }
    }

    public void d() {
        this.g = true;
        if (this.c != null) {
            this.c.d();
        }
        if (this.e != null) {
            this.e.b();
        }
    }

    private void e() {
        TXCLog.i("VideoJoinPreview", "startPlayInternal");
        g gVar = new g();
        gVar.a = this.b.a();
        gVar.b = this.b.b();
        this.d.a(gVar);
        this.f = new com.tencent.liteav.f.b();
        this.f.a();
        this.f.a(this.n);
        MediaFormat g = t.a().g();
        this.f.a(g);
        this.e.a(g);
        this.e.a(this.o);
        this.e.c();
        this.c.a(t.a().c());
        this.c.a(this.j);
        this.c.a(this.k);
        this.c.a();
        this.b.c();
    }

    private void f() {
        this.p.post(new Runnable() {
            public void run() {
                if (r.this.i != null) {
                    r.this.i.a();
                }
            }
        });
    }

    private void a(final long j) {
        this.p.post(new Runnable() {
            public void run() {
                if (r.this.i != null) {
                    r.this.i.a((int) j);
                }
            }
        });
    }
}

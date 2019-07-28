package com.tencent.liteav.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.c;
import com.tencent.liteav.c.i;
import com.tencent.liteav.c.k;
import com.tencent.liteav.d.e;
import com.tencent.liteav.d.g;
import com.tencent.liteav.e.b.a;
import com.tencent.liteav.f.b;
import com.tencent.liteav.i.a.f;
import com.tencent.liteav.i.b.d;
import java.io.IOException;
import java.util.List;

/* compiled from: VideoEditerPreview */
public class z {
    private final String a = "VideoEditerPreview";
    private Context b;
    private x c;
    private ab d;
    private b e;
    private k f;
    private i g;
    private com.tencent.liteav.f.k h;
    private b i;
    private Surface j;
    private boolean k;
    private d l;
    private com.tencent.liteav.i.b.b m;
    private p n;
    private f o;
    private m p = new m() {
        public void a(Surface surface) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onSurfaceTextureAvailable surface:");
            stringBuilder.append(surface);
            stringBuilder.append(", mStartPlay = ");
            stringBuilder.append(z.this.k);
            TXCLog.i("VideoEditerPreview", stringBuilder.toString());
            synchronized (this) {
                z.this.j = surface;
            }
            if (z.this.h != null) {
                z.this.h.a();
                z.this.h.b();
                z.this.h.a(z.this.q);
            }
            if (z.this.k) {
                z.this.g();
            }
        }

        public void a(int i, int i2) {
            if (z.this.h != null) {
                g gVar = new g();
                gVar.a = i;
                gVar.b = i2;
                z.this.h.a(gVar);
            }
        }

        public void b(Surface surface) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onSurfaceTextureDestroy surface:");
            stringBuilder.append(surface);
            TXCLog.i("VideoEditerPreview", stringBuilder.toString());
            synchronized (this) {
                if (z.this.j == surface) {
                    z.this.j = null;
                }
            }
            if (z.this.h != null) {
                z.this.h.c();
                z.this.h.d();
                z.this.h.a(null);
            }
            if (z.this.o != null) {
                z.this.o.a();
            }
        }

        public int a(int i, float[] fArr, e eVar) {
            if (eVar.p()) {
                z.this.i();
                return 0;
            }
            if (z.this.o != null) {
                i = z.this.o.a(eVar, com.tencent.liteav.c.e.a().b(), false);
                eVar.l(i);
                eVar.m(0);
            }
            if (z.this.h != null) {
                z.this.h.a(fArr);
                z.this.h.a(i, eVar);
                z.this.c(eVar.e());
            }
            return 0;
        }

        public void a(boolean z) {
            if (z.this.h != null) {
                z.this.h.a(z);
            }
        }
    };
    private l q = new l() {
        public void a(int i, e eVar) {
            com.tencent.liteav.j.b.c();
            if (z.this.d != null) {
                z.this.d.a(i, z.this.d.a(), z.this.d.b());
            }
        }

        public int b(int i, e eVar) {
            return z.this.a(i, eVar.m(), eVar.n(), eVar.e());
        }
    };
    private i r = new i() {
        public void a(e eVar) {
            if (eVar != null && eVar.b() != null) {
                com.tencent.liteav.j.b.d();
                if (eVar.p() && ((k.a().d() == 2 && z.this.n.c()) || (k.a().d() == 1 && z.this.c.q()))) {
                    z.this.i();
                    return;
                }
                if (z.this.e != null) {
                    z.this.e.a(eVar);
                }
                if (z.this.i != null) {
                    z.this.i.i();
                }
            }
        }
    };
    private k s = new k() {
        public void a(e eVar) {
            com.tencent.liteav.j.b.a();
            if (z.this.d != null) {
                z.this.d.a(eVar);
            }
        }
    };
    private h t = new h() {
        public void a(e eVar) {
            com.tencent.liteav.j.b.b();
            if (z.this.i != null) {
                z.this.i.a(eVar);
            }
        }
    };
    private j u = new j() {
        public void a(e eVar) {
            if (z.this.d != null) {
                z.this.d.b(eVar);
            }
        }
    };
    private Handler v = new Handler(Looper.getMainLooper());
    private a w = new a() {
        public void a(int i) {
            boolean z = false;
            if (k.a().d() == 1 && z.this.c.h()) {
                x j = z.this.c;
                if (i <= 5) {
                    z = true;
                }
                j.a(z);
            } else if (z.this.i != null) {
                b l = z.this.i;
                if (i <= 5) {
                    z = true;
                }
                l.c(z);
            }
        }
    };

    public z(Context context) {
        this.b = context;
        this.d = new ab(context);
        this.d.a(this.p);
        this.e = new b();
        this.g = i.a();
        this.h = new com.tencent.liteav.f.k(context);
        this.g = i.a();
        this.f = k.a();
    }

    public void a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setVideoPath videoPath:");
        stringBuilder.append(str);
        TXCLog.i("VideoEditerPreview", stringBuilder.toString());
        if (this.c == null) {
            this.c = new x();
        }
        try {
            this.c.a(str);
            if (this.c.h()) {
                this.g.a(this.c.f());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(List<Bitmap> list, int i) {
        this.n = new p();
        this.n.a(true);
        this.n.a((List) list, i);
        this.o = new f(this.b, this.n.a(), this.n.b());
    }

    public long a(int i) {
        com.tencent.liteav.c.e.a().a(i);
        return this.n != null ? this.n.a(i) : 0;
    }

    public void b(String str) {
        if (this.i == null) {
            this.i = new b();
            this.i.a();
        }
        this.i.a(str);
        this.g.c(this.i.h());
        this.i.a(this.g.n());
        boolean z = false;
        if (k.a().d() == 1) {
            z = this.c.h();
        }
        if (!z) {
            this.i.b(z);
            this.i.c();
        }
    }

    public void a(long j, long j2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setBGMStartTime startTime:");
        stringBuilder.append(j);
        stringBuilder.append(",endTime:");
        stringBuilder.append(j2);
        TXCLog.i("VideoEditerPreview", stringBuilder.toString());
        if (this.i != null) {
            this.i.a(j, j2);
        }
    }

    public void a(float f) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setVideoVolume volume:");
        stringBuilder.append(f);
        TXCLog.i("VideoEditerPreview", stringBuilder.toString());
        if (this.i != null) {
            this.i.a(f);
        }
    }

    public void b(float f) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setBGMVolume volume:");
        stringBuilder.append(f);
        TXCLog.i("VideoEditerPreview", stringBuilder.toString());
        if (this.i != null) {
            this.i.b(f);
        }
    }

    public void a(boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setBGMLoop looping:");
        stringBuilder.append(z);
        TXCLog.i("VideoEditerPreview", stringBuilder.toString());
        if (this.i != null) {
            this.i.a(z);
        }
    }

    public void a(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setBGMAtVideoTime videoStartTime:");
        stringBuilder.append(j);
        TXCLog.i("VideoEditerPreview", stringBuilder.toString());
        if (this.i != null) {
            this.i.a(j);
        }
    }

    public void a() {
        if (this.d != null) {
            this.d.a(true);
        }
    }

    public void b(long j, long j2) {
        if (this.f.d() == 1 && this.c != null) {
            this.c.a(j * 1000, j2 * 1000);
        } else if (this.f.d() == 2 && this.n != null) {
            this.n.a(j, j2);
        }
    }

    public void c(long j, long j2) {
        if (this.f.d() == 2) {
            TXCLog.e("VideoEditerPreview", "setRepeateFromTimeToTime, source is picture, not support yet!");
        } else {
            this.c.b(j, j2);
        }
    }

    public void a(d dVar) {
        this.l = dVar;
    }

    public void a(com.tencent.liteav.i.b.b bVar) {
        this.m = bVar;
    }

    public void a(f fVar) {
        synchronized (this) {
            this.j = null;
        }
        if (this.f.d() == 1) {
            a(this.f.a);
            if (k.a().e() != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("initWithPreview Video Source illegal : ");
                stringBuilder.append(this.f.a);
                TXCLog.e("VideoEditerPreview", stringBuilder.toString());
                return;
            }
        }
        if (this.d != null) {
            this.d.a(fVar);
        }
    }

    public void b(long j) {
        if (this.f.d() == 1 && this.c != null) {
            this.c.a(j);
        } else if (this.f.d() == 2 && this.n != null) {
            this.n.a(j);
        }
    }

    public void b() {
        this.k = true;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("startPlay mStartPlay true,mSurface:");
        stringBuilder.append(this.j);
        TXCLog.i("VideoEditerPreview", stringBuilder.toString());
        if (this.j != null) {
            g();
        }
    }

    public void c() {
        this.k = false;
        TXCLog.i("VideoEditerPreview", "stopPlay mStartPlay false");
        if (this.f.d() == 1 && this.c != null) {
            this.c.a(null);
            this.c.a(null);
            this.c.m();
        } else if (this.f.d() == 2 && this.n != null) {
            this.n.e();
            this.n.a(null);
        }
        if (this.e != null) {
            this.e.a(null);
            this.e.d();
        }
        if (this.i != null) {
            this.i.d();
            this.i.a(null);
            this.i.b();
            this.i = null;
        }
        if (this.d != null) {
            this.d.d();
        }
    }

    /* JADX WARNING: Missing block: B:12:0x0022, code skipped:
            if (r3.f.d() != 1) goto L_0x002e;
     */
    /* JADX WARNING: Missing block: B:14:0x0026, code skipped:
            if (r3.c == null) goto L_0x002e;
     */
    /* JADX WARNING: Missing block: B:15:0x0028, code skipped:
            r3.c.o();
     */
    /* JADX WARNING: Missing block: B:17:0x0035, code skipped:
            if (r3.f.d() != 2) goto L_0x0040;
     */
    /* JADX WARNING: Missing block: B:19:0x0039, code skipped:
            if (r3.n == null) goto L_0x0040;
     */
    /* JADX WARNING: Missing block: B:20:0x003b, code skipped:
            r3.n.g();
     */
    /* JADX WARNING: Missing block: B:22:0x0042, code skipped:
            if (r3.e == null) goto L_0x0049;
     */
    /* JADX WARNING: Missing block: B:23:0x0044, code skipped:
            r3.e.b();
     */
    /* JADX WARNING: Missing block: B:25:0x004b, code skipped:
            if (r3.c == null) goto L_0x005e;
     */
    /* JADX WARNING: Missing block: B:27:0x0053, code skipped:
            if (r3.c.h() != false) goto L_0x005e;
     */
    /* JADX WARNING: Missing block: B:29:0x0057, code skipped:
            if (r3.i == null) goto L_0x005e;
     */
    /* JADX WARNING: Missing block: B:30:0x0059, code skipped:
            r3.i.g();
     */
    /* JADX WARNING: Missing block: B:31:0x005e, code skipped:
            return;
     */
    public void d() {
        /*
        r3 = this;
        r0 = 1;
        r3.k = r0;
        r1 = r3.d;
        if (r1 == 0) goto L_0x000d;
    L_0x0007:
        r1 = r3.d;
        r2 = 0;
        r1.a(r2);
    L_0x000d:
        monitor-enter(r3);
        r1 = r3.j;	 Catch:{ all -> 0x005f }
        if (r1 != 0) goto L_0x001b;
    L_0x0012:
        r0 = "VideoEditerPreview";
        r1 = "resumePlay, mSurface is null, ignore!";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);	 Catch:{ all -> 0x005f }
        monitor-exit(r3);	 Catch:{ all -> 0x005f }
        return;
    L_0x001b:
        monitor-exit(r3);	 Catch:{ all -> 0x005f }
        r1 = r3.f;
        r1 = r1.d();
        if (r1 != r0) goto L_0x002e;
    L_0x0024:
        r0 = r3.c;
        if (r0 == 0) goto L_0x002e;
    L_0x0028:
        r0 = r3.c;
        r0.o();
        goto L_0x0040;
    L_0x002e:
        r0 = r3.f;
        r0 = r0.d();
        r1 = 2;
        if (r0 != r1) goto L_0x0040;
    L_0x0037:
        r0 = r3.n;
        if (r0 == 0) goto L_0x0040;
    L_0x003b:
        r0 = r3.n;
        r0.g();
    L_0x0040:
        r0 = r3.e;
        if (r0 == 0) goto L_0x0049;
    L_0x0044:
        r0 = r3.e;
        r0.b();
    L_0x0049:
        r0 = r3.c;
        if (r0 == 0) goto L_0x005e;
    L_0x004d:
        r0 = r3.c;
        r0 = r0.h();
        if (r0 != 0) goto L_0x005e;
    L_0x0055:
        r0 = r3.i;
        if (r0 == 0) goto L_0x005e;
    L_0x0059:
        r0 = r3.i;
        r0.g();
    L_0x005e:
        return;
    L_0x005f:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x005f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.e.z.d():void");
    }

    public void e() {
        this.k = false;
        if (this.f.d() == 1 && this.c != null) {
            this.c.n();
        } else if (this.f.d() == 2 && this.n != null) {
            this.n.f();
        }
        if (this.e != null) {
            this.e.a();
        }
        if (this.c != null && !this.c.h() && this.i != null) {
            this.i.f();
        }
    }

    public void f() {
        TXCLog.i("VideoEditerPreview", "release");
        if (this.c != null) {
            this.c.k();
        }
        if (this.n != null) {
            this.n.i();
        }
        if (this.d != null) {
            this.d.a(null);
            this.d.e();
        }
        this.d = null;
        this.h = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.w = null;
        this.j = null;
    }

    private void g() {
        TXCLog.i("VideoEditerPreview", "startPlayInternal");
        if (this.i == null) {
            this.i = new b();
            this.i.a();
        }
        this.i.a(this.r);
        this.i.b(h());
        if (this.g.l()) {
            MediaFormat n = this.g.n();
            this.i.a(n);
            if (this.f.d() == 1) {
                this.i.b(this.c.h());
            } else {
                this.i.b(false);
            }
            this.i.c();
            this.i.e();
            this.e.a(n);
        }
        g gVar = new g();
        if (this.d != null) {
            gVar.a = this.d.a();
            gVar.b = this.d.b();
        }
        this.h.a(gVar);
        if (this.f.d() == 1 && this.c != null) {
            synchronized (this) {
                this.c.a(this.j);
            }
            this.c.a(this.s);
            this.c.a(this.t);
            this.c.l();
        } else if (this.f.d() == 2 && this.n != null) {
            this.n.a(this.u);
            this.n.d();
        }
        this.e.a(this.w);
        this.e.c();
        if (this.d != null) {
            this.d.a(false);
            this.d.c();
        }
        com.tencent.liteav.j.b.h();
    }

    private long h() {
        c a = c.a();
        long e = a.e() - a.d();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("calculatePlayDuration playDurationUs : ");
        stringBuilder.append(e);
        TXCLog.d("VideoEditerPreview", stringBuilder.toString());
        if (!com.tencent.liteav.f.g.a().c()) {
            return e;
        }
        e = com.tencent.liteav.f.g.a().b(e);
        stringBuilder = new StringBuilder();
        stringBuilder.append("calculatePlayDuration after Speed playDurationUs : ");
        stringBuilder.append(e);
        TXCLog.d("VideoEditerPreview", stringBuilder.toString());
        return e;
    }

    private int a(int i, int i2, int i3, long j) {
        return this.m != null ? this.m.a(i, i2, i3, j) : i;
    }

    private void i() {
        this.v.post(new Runnable() {
            public void run() {
                if (z.this.l != null) {
                    z.this.l.a();
                }
            }
        });
    }

    private void c(final long j) {
        this.v.post(new Runnable() {
            public void run() {
                if (z.this.l != null) {
                    z.this.l.a((int) j);
                }
            }
        });
    }
}

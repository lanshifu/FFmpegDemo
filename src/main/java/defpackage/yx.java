package defpackage;

import android.graphics.Canvas;
import defpackage.za.a;
import defpackage.zn.c;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.DanmakuContext.DanmakuConfigTag;
import master.flame.danmaku.danmaku.model.android.e;

/* compiled from: DrawTask */
/* renamed from: yx */
public class yx implements za {
    protected final DanmakuContext a;
    protected final zd b;
    protected zn c;
    protected zy d;
    a e;
    final aaa f;
    zh g;
    protected boolean h;
    protected boolean i;
    protected int j;
    private zn k = new e(4);
    private long l = 0;
    private final b m = new b();
    private long n;
    private long o;
    private boolean p;
    private zf q;
    private e r = new e(4);
    private zn s;
    private boolean t;
    private DanmakuContext.a u = new 1();

    /* compiled from: DrawTask */
    /* renamed from: yx$1 */
    class 1 implements DanmakuContext.a {
        1() {
        }

        public boolean a(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr) {
            return yx.this.a(danmakuContext, danmakuConfigTag, objArr);
        }
    }

    /* compiled from: DrawTask */
    /* renamed from: yx$2 */
    class 2 implements a {
        2() {
        }

        public void a(zf zfVar) {
            if (yx.this.e != null) {
                yx.this.e.b(zfVar);
            }
        }
    }

    /* compiled from: DrawTask */
    /* renamed from: yx$5 */
    class 5 implements zy.a {
        5() {
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(zf zfVar) {
    }

    public yx(zh zhVar, DanmakuContext danmakuContext, a aVar) {
        if (danmakuContext != null) {
            this.a = danmakuContext;
            this.b = danmakuContext.b();
            this.e = aVar;
            this.f = new aac(danmakuContext);
            this.f.setOnDanmakuShownListener(new 2());
            aaa aaa = this.f;
            boolean z = this.a.f() || this.a.e();
            aaa.a(z);
            a(zhVar);
            Boolean valueOf = Boolean.valueOf(this.a.c());
            if (valueOf == null) {
                return;
            }
            if (valueOf.booleanValue()) {
                this.a.s.a("1017_Filter");
                return;
            } else {
                this.a.s.b("1017_Filter");
                return;
            }
        }
        throw new IllegalArgumentException("context is null");
    }

    /* Access modifiers changed, original: protected */
    public void a(zh zhVar) {
        this.g = zhVar;
    }

    /* JADX WARNING: Missing block: B:50:0x0080, code skipped:
            return;
     */
    public synchronized void a(defpackage.zf r7) {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.c;	 Catch:{ all -> 0x0084 }
        if (r0 != 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r6);
        return;
    L_0x0007:
        r0 = r7.w;	 Catch:{ all -> 0x0084 }
        if (r0 == 0) goto L_0x0015;
    L_0x000b:
        r0 = r6.r;	 Catch:{ all -> 0x0084 }
        r0.a(r7);	 Catch:{ all -> 0x0084 }
        r0 = 10;
        r6.b(r0);	 Catch:{ all -> 0x0084 }
    L_0x0015:
        r0 = r6.c;	 Catch:{ all -> 0x0084 }
        r0 = r0.a();	 Catch:{ all -> 0x0084 }
        r7.q = r0;	 Catch:{ all -> 0x0084 }
        r0 = 1;
        r1 = r6.n;	 Catch:{ all -> 0x0084 }
        r3 = r7.s();	 Catch:{ all -> 0x0084 }
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 > 0) goto L_0x0041;
    L_0x0028:
        r1 = r7.s();	 Catch:{ all -> 0x0084 }
        r3 = r6.o;	 Catch:{ all -> 0x0084 }
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 > 0) goto L_0x0041;
    L_0x0032:
        r0 = r6.k;	 Catch:{ all -> 0x0084 }
        monitor-enter(r0);	 Catch:{ all -> 0x0084 }
        r1 = r6.k;	 Catch:{ all -> 0x003e }
        r1 = r1.a(r7);	 Catch:{ all -> 0x003e }
        monitor-exit(r0);	 Catch:{ all -> 0x003e }
        r0 = r1;
        goto L_0x0046;
    L_0x003e:
        r7 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x003e }
        throw r7;	 Catch:{ all -> 0x0084 }
    L_0x0041:
        r1 = r7.w;	 Catch:{ all -> 0x0084 }
        if (r1 == 0) goto L_0x0046;
    L_0x0045:
        r0 = 0;
    L_0x0046:
        r1 = r6.c;	 Catch:{ all -> 0x0084 }
        monitor-enter(r1);	 Catch:{ all -> 0x0084 }
        r2 = r6.c;	 Catch:{ all -> 0x0081 }
        r2 = r2.a(r7);	 Catch:{ all -> 0x0081 }
        monitor-exit(r1);	 Catch:{ all -> 0x0081 }
        if (r0 == 0) goto L_0x0054;
    L_0x0052:
        if (r2 != 0) goto L_0x005a;
    L_0x0054:
        r0 = 0;
        r6.o = r0;	 Catch:{ all -> 0x0084 }
        r6.n = r0;	 Catch:{ all -> 0x0084 }
    L_0x005a:
        if (r2 == 0) goto L_0x0065;
    L_0x005c:
        r0 = r6.e;	 Catch:{ all -> 0x0084 }
        if (r0 == 0) goto L_0x0065;
    L_0x0060:
        r0 = r6.e;	 Catch:{ all -> 0x0084 }
        r0.a(r7);	 Catch:{ all -> 0x0084 }
    L_0x0065:
        r0 = r6.q;	 Catch:{ all -> 0x0084 }
        if (r0 == 0) goto L_0x007d;
    L_0x0069:
        if (r7 == 0) goto L_0x007f;
    L_0x006b:
        r0 = r6.q;	 Catch:{ all -> 0x0084 }
        if (r0 == 0) goto L_0x007f;
    L_0x006f:
        r0 = r7.s();	 Catch:{ all -> 0x0084 }
        r2 = r6.q;	 Catch:{ all -> 0x0084 }
        r2 = r2.s();	 Catch:{ all -> 0x0084 }
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 <= 0) goto L_0x007f;
    L_0x007d:
        r6.q = r7;	 Catch:{ all -> 0x0084 }
    L_0x007f:
        monitor-exit(r6);
        return;
    L_0x0081:
        r7 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0081 }
        throw r7;	 Catch:{ all -> 0x0084 }
    L_0x0084:
        r7 = move-exception;
        monitor-exit(r6);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yx.a(zf):void");
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* JADX WARNING: Missing block: B:13:0x0023, code skipped:
            return;
     */
    public synchronized void b(final int r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.c;	 Catch:{ all -> 0x0024 }
        if (r0 == 0) goto L_0x0022;
    L_0x0005:
        r0 = r2.c;	 Catch:{ all -> 0x0024 }
        r0 = r0.e();	 Catch:{ all -> 0x0024 }
        if (r0 != 0) goto L_0x0022;
    L_0x000d:
        r0 = r2.r;	 Catch:{ all -> 0x0024 }
        r0 = r0.e();	 Catch:{ all -> 0x0024 }
        if (r0 == 0) goto L_0x0016;
    L_0x0015:
        goto L_0x0022;
    L_0x0016:
        r0 = r2.r;	 Catch:{ all -> 0x0024 }
        r1 = new yx$3;	 Catch:{ all -> 0x0024 }
        r1.<init>(r3);	 Catch:{ all -> 0x0024 }
        r0.a(r1);	 Catch:{ all -> 0x0024 }
        monitor-exit(r2);
        return;
    L_0x0022:
        monitor-exit(r2);
        return;
    L_0x0024:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yx.b(int):void");
    }

    public zn b(long j) {
        zn znVar;
        long j2 = (j - this.a.t.d) - 100;
        j += this.a.t.d;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 3) {
                znVar = null;
                break;
            }
            try {
                znVar = this.c.a(j2, j);
                break;
            } catch (Exception unused) {
                i = i2;
            }
        }
        final e eVar = new e();
        if (!(znVar == null || znVar.e())) {
            znVar.a(new c<zf>() {
                public int a(zf zfVar) {
                    if (zfVar.e() && !zfVar.g()) {
                        eVar.a(zfVar);
                    }
                    return 0;
                }
            });
        }
        return eVar;
    }

    public synchronized b a(zd zdVar) {
        return a(zdVar, this.g);
    }

    public void d() {
        if (this.k != null) {
            this.k = new e();
        }
        if (this.f != null) {
            this.f.a();
        }
    }

    public void a(long j) {
        d();
        this.a.r.c();
        this.a.r.f();
        this.a.r.g();
        this.a.r.h();
        this.s = new e(4);
        if (j < 1000) {
            j = 0;
        }
        this.l = j;
        this.m.a();
        this.m.o = this.l;
        this.o = 0;
        this.n = 0;
        if (this.c != null) {
            zf d = this.c.d();
            if (d != null && !d.f()) {
                this.q = d;
            }
        }
    }

    public void c(long j) {
        d();
        this.a.r.c();
        this.a.r.f();
        this.l = j;
    }

    public void a() {
        this.a.a(this.u);
    }

    public void b() {
        this.a.g();
        if (this.f != null) {
            this.f.c();
        }
    }

    public void c() {
        if (this.d != null) {
            a(this.d);
            this.o = 0;
            this.n = 0;
            if (this.e != null) {
                this.e.a();
                this.i = true;
            }
        }
    }

    public void a(int i) {
        this.j = i;
    }

    /* Access modifiers changed, original: protected */
    public void a(zy zyVar) {
        this.c = zyVar.setConfig(this.a).setDisplayer(this.b).setTimer(this.g).setListener(new 5()).getDanmakus();
        this.a.r.a();
        if (this.c != null) {
            this.q = this.c.d();
        }
    }

    public void b(zy zyVar) {
        this.d = zyVar;
        this.i = false;
    }

    /* Access modifiers changed, original: protected */
    public b a(zd zdVar, zh zhVar) {
        zh zhVar2 = zhVar;
        if (this.h) {
            this.f.b();
            this.h = false;
        }
        if (this.c == null) {
            return null;
        }
        yw.a((Canvas) zdVar.a());
        if (this.p && !this.t) {
            return this.m;
        }
        zn b;
        long j;
        long j2;
        zn znVar;
        this.t = false;
        b bVar = this.m;
        long j3 = (zhVar2.a - this.a.t.d) - 100;
        long j4 = zhVar2.a + this.a.t.d;
        zn znVar2 = this.k;
        if (this.n > j3 || zhVar2.a > this.o) {
            b = this.c.b(j3, j4);
            if (b != null) {
                this.k = b;
            }
            this.n = j3;
            this.o = j4;
            j = j3;
            j2 = j4;
            znVar = b;
        } else {
            j = this.n;
            j2 = this.o;
            znVar = znVar2;
        }
        b = this.s;
        a(bVar, b, znVar);
        if (!(b == null || b.e())) {
            this.m.a = true;
            this.f.a(zdVar, b, 0, this.m);
        }
        this.m.a = false;
        if (znVar == null || znVar.e()) {
            bVar.p = true;
            bVar.n = j;
            bVar.o = j2;
            return bVar;
        }
        this.f.a(this.b, znVar, this.l, bVar);
        a(bVar);
        if (bVar.p) {
            if (this.q != null && this.q.f()) {
                this.q = null;
                if (this.e != null) {
                    this.e.b();
                }
            }
            if (bVar.n == -1) {
                bVar.n = j;
            }
            if (bVar.o == -1) {
                bVar.o = j2;
            }
        }
        return bVar;
    }

    public void e() {
        this.o = 0;
        this.n = 0;
        this.p = false;
    }

    public void f() {
        this.h = true;
    }

    public void a(long j, long j2, final long j3) {
        zn b = this.m.b();
        this.s = b;
        b.a(new c<zf>() {
            public int a(zf zfVar) {
                if (zfVar.g()) {
                    return 2;
                }
                zfVar.c(j3 + zfVar.a);
                if (zfVar.a == 0) {
                    return 2;
                }
                return 0;
            }
        });
        this.l = j2;
    }

    public boolean a(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        boolean b = b(danmakuContext, danmakuConfigTag, objArr);
        if (this.e != null) {
            this.e.c();
        }
        return b;
    }

    /* Access modifiers changed, original: protected */
    public boolean b(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object[] objArr) {
        boolean z = false;
        if (danmakuConfigTag == null || DanmakuConfigTag.MAXIMUM_NUMS_IN_SCREEN.equals(danmakuConfigTag)) {
            return true;
        }
        Boolean bool;
        if (DanmakuConfigTag.DUPLICATE_MERGING_ENABLED.equals(danmakuConfigTag)) {
            bool = (Boolean) objArr[0];
            if (bool != null) {
                if (bool.booleanValue()) {
                    this.a.s.a("1017_Filter");
                    return true;
                }
                this.a.s.b("1017_Filter");
                return true;
            }
        } else if (DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag) || DanmakuConfigTag.DANMAKU_MARGIN.equals(danmakuConfigTag)) {
            f();
        } else if (DanmakuConfigTag.MAXIMUN_LINES.equals(danmakuConfigTag) || DanmakuConfigTag.OVERLAPPING_ENABLE.equals(danmakuConfigTag)) {
            if (this.f == null) {
                return true;
            }
            aaa aaa = this.f;
            if (this.a.f() || this.a.e()) {
                z = true;
            }
            aaa.a(z);
            return true;
        } else if (DanmakuConfigTag.ALIGN_BOTTOM.equals(danmakuConfigTag)) {
            bool = (Boolean) objArr[0];
            if (bool != null) {
                if (this.f == null) {
                    return true;
                }
                this.f.b(bool.booleanValue());
                return true;
            }
        }
        return false;
    }

    public void g() {
        this.p = true;
    }

    public void h() {
        this.t = true;
    }

    private void a(b bVar, zn znVar, zn znVar2) {
        bVar.a();
        bVar.b.a(aaf.a());
        int i = 0;
        bVar.c = 0;
        int a = znVar != null ? znVar.a() : 0;
        if (znVar2 != null) {
            i = znVar2.a();
        }
        bVar.d = a + i;
    }

    private void a(b bVar) {
        bVar.p = bVar.k == 0;
        long j = -1;
        if (bVar.p) {
            bVar.n = -1;
        }
        zf zfVar = bVar.e;
        bVar.e = null;
        if (zfVar != null) {
            j = zfVar.s();
        }
        bVar.o = j;
        bVar.m = bVar.b.a(aaf.a());
    }
}

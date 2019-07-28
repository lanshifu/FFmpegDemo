package defpackage;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.za.a;
import defpackage.zn.b;
import defpackage.zn.c;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.DanmakuContext.DanmakuConfigTag;
import master.flame.danmaku.danmaku.model.android.e;
import master.flame.danmaku.danmaku.model.android.f;
import master.flame.danmaku.danmaku.model.android.h;
import tv.cjump.jni.NativeBitmapFactory;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* compiled from: CacheManagingDrawTask */
/* renamed from: yt */
public class yt extends yx {
    private int k = 2;
    private a l;
    private zh m;
    private final Object n = new Object();
    private int o;

    /* compiled from: CacheManagingDrawTask */
    /* renamed from: yt$1 */
    class 1 implements Runnable {
        1() {
        }

        public void run() {
            yt.this.e.c();
        }
    }

    /* compiled from: CacheManagingDrawTask */
    /* renamed from: yt$a */
    public class a implements zm {
        public HandlerThread a;
        e b = new e();
        h c = new h();
        zu<f> d = zx.a(this.c, 800);
        private int f;
        private int g = 0;
        private int h = 3;
        private a i;
        private boolean j = false;

        /* compiled from: CacheManagingDrawTask */
        /* renamed from: yt$a$a */
        public class a extends Handler {
            private boolean b;
            private boolean c;
            private boolean d;
            private boolean e;

            /* compiled from: CacheManagingDrawTask */
            /* renamed from: yt$a$a$1 */
            class 1 extends c<zf> {
                1() {
                }

                public int a(zf zfVar) {
                    if (a.this.b || a.this.e) {
                        return 1;
                    }
                    if (!zfVar.i()) {
                        yt.this.a.s.a(zfVar, 0, 0, null, true, yt.this.a);
                    }
                    if (zfVar.j()) {
                        return 0;
                    }
                    if (!zfVar.b()) {
                        zfVar.a(yt.this.b, true);
                    }
                    if (!zfVar.c()) {
                        zfVar.b(yt.this.b, true);
                    }
                    return 0;
                }
            }

            public a(Looper looper) {
                super(looper);
            }

            public void a() {
                this.e = true;
            }

            public void handleMessage(Message message) {
                long longValue;
                int i = message.what;
                int i2 = 0;
                switch (i) {
                    case 1:
                        a.this.j();
                        while (i2 < IjkMediaCodecInfo.RANK_SECURE) {
                            a.this.d.a(new f());
                            i2++;
                        }
                        break;
                    case 2:
                        b((zf) message.obj);
                        break;
                    case 3:
                        removeMessages(3);
                        boolean z = !(yt.this.e == null || yt.this.i) || this.d;
                        b(z);
                        if (z) {
                            this.d = false;
                        }
                        if (!(yt.this.e == null || yt.this.i)) {
                            yt.this.e.a();
                            yt.this.i = true;
                            break;
                        }
                        break;
                    case 4:
                        a.this.l();
                        break;
                    case 5:
                        Long l = (Long) message.obj;
                        if (l != null) {
                            longValue = l.longValue();
                            long j = yt.this.m.a;
                            yt.this.m.a(longValue);
                            this.d = true;
                            long e = a.this.e();
                            if (longValue > j || e - longValue > yt.this.a.t.d) {
                                a.this.j();
                            } else {
                                a.this.l();
                            }
                            b(true);
                            d();
                            break;
                        }
                        break;
                    case 6:
                        removeCallbacksAndMessages(null);
                        this.b = true;
                        a.this.i();
                        a.this.k();
                        getLooper().quit();
                        break;
                    case 7:
                        a.this.i();
                        yt.this.m.a(yt.this.g.a - yt.this.a.t.d);
                        this.d = true;
                        break;
                    case 8:
                        a.this.j();
                        yt.this.m.a(yt.this.g.a);
                        break;
                    case 9:
                        a.this.j();
                        yt.this.m.a(yt.this.g.a);
                        yt.this.e();
                        break;
                    default:
                        switch (i) {
                            case 16:
                                break;
                            case 17:
                                zf zfVar = (zf) message.obj;
                                if (zfVar != null) {
                                    zp d = zfVar.d();
                                    if (((zfVar.G & 1) != 0 ? 1 : null) != null || d == null || d.a() == null || d.f()) {
                                        if (!zfVar.w) {
                                            if (d != null && d.f()) {
                                                d.b();
                                            }
                                            a.this.a(true, zfVar, null);
                                            b(zfVar);
                                            break;
                                        }
                                        a.this.c(zfVar);
                                        a(zfVar);
                                        break;
                                    }
                                    zfVar.v = aae.a(zfVar, yt.this.b, (f) zfVar.v, yt.this.a.u.d);
                                    a.this.a(zfVar, 0, true);
                                    return;
                                }
                                break;
                            case 18:
                                this.e = false;
                                break;
                        }
                        break;
                }
                longValue = e();
                if (longValue <= 0) {
                    longValue = yt.this.a.t.d / 2;
                }
                sendEmptyMessageDelayed(16, longValue);
            }

            private long e() {
                if (yt.this.m.a <= yt.this.g.a - yt.this.a.t.d) {
                    if (yt.this.a.u.f != -1) {
                        a.this.j();
                    }
                    yt.this.m.a(yt.this.g.a);
                    sendEmptyMessage(3);
                    return 0;
                }
                float d = a.this.d();
                zf c = a.this.b.c();
                long s = c != null ? c.s() - yt.this.g.a : 0;
                long j = yt.this.a.t.d * 2;
                if (d < 0.6f && s > yt.this.a.t.d) {
                    yt.this.m.a(yt.this.g.a);
                    removeMessages(3);
                    sendEmptyMessage(3);
                    return 0;
                } else if (d > 0.4f && s < (-j)) {
                    removeMessages(4);
                    sendEmptyMessage(4);
                    return 0;
                } else if (d >= 0.9f) {
                    return 0;
                } else {
                    s = yt.this.m.a - yt.this.g.a;
                    if (c != null && c.f() && s < (-yt.this.a.t.d)) {
                        yt.this.m.a(yt.this.g.a);
                        sendEmptyMessage(8);
                        sendEmptyMessage(3);
                        return 0;
                    } else if (s > j) {
                        return 0;
                    } else {
                        removeMessages(3);
                        sendEmptyMessage(3);
                        return 0;
                    }
                }
            }

            private void a(zf zfVar, f fVar) {
                if (fVar == null) {
                    fVar = (f) zfVar.v;
                }
                zfVar.v = null;
                if (fVar != null) {
                    fVar.b();
                    a.this.d.a(fVar);
                }
            }

            private void f() {
                zn a;
                try {
                    long j = yt.this.g.a;
                    a = yt.this.c.a(j - yt.this.a.t.d, (yt.this.a.t.d * 2) + j);
                } catch (Exception unused) {
                    a = null;
                }
                if (a != null && !a.e()) {
                    a.b(new 1());
                }
            }

            /* JADX WARNING: Removed duplicated region for block: B:18:0x0074  */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x0068  */
            private long b(boolean r25) {
                /*
                r24 = this;
                r11 = r24;
                r24.f();
                r0 = defpackage.yt.a.this;
                r0 = defpackage.yt.this;
                r0 = r0.m;
                r0 = r0.a;
                r2 = 30;
                r5 = r0 - r2;
                r0 = defpackage.yt.a.this;
                r0 = defpackage.yt.this;
                r0 = r0.a;
                r0 = r0.t;
                r0 = r0.d;
                r4 = defpackage.yt.a.this;
                r4 = r4.h;
                r7 = (long) r4;
                r0 = r0 * r7;
                r12 = r5 + r0;
                r0 = defpackage.yt.a.this;
                r0 = defpackage.yt.this;
                r0 = r0.g;
                r0 = r0.a;
                r4 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1));
                r0 = 0;
                if (r4 >= 0) goto L_0x0037;
            L_0x0036:
                return r0;
            L_0x0037:
                r14 = aaf.a();
                r4 = 0;
                r7 = 0;
                r8 = 0;
            L_0x003e:
                r9 = 10;
                r16 = 1;
                r2 = defpackage.yt.a.this;	 Catch:{ Exception -> 0x0052 }
                r2 = defpackage.yt.this;	 Catch:{ Exception -> 0x0052 }
                r2 = r2.c;	 Catch:{ Exception -> 0x0052 }
                r2 = r2.a(r5, r12);	 Catch:{ Exception -> 0x0052 }
                r23 = r8;
                r8 = r2;
                r2 = r23;
                goto L_0x0057;
            L_0x0052:
                aaf.a(r9);
                r8 = r4;
                r2 = 1;
            L_0x0057:
                r7 = r7 + 1;
                r3 = 3;
                if (r7 >= r3) goto L_0x0066;
            L_0x005c:
                if (r8 != 0) goto L_0x0066;
            L_0x005e:
                if (r2 != 0) goto L_0x0061;
            L_0x0060:
                goto L_0x0066;
            L_0x0061:
                r4 = r8;
                r8 = r2;
                r2 = 30;
                goto L_0x003e;
            L_0x0066:
                if (r8 != 0) goto L_0x0074;
            L_0x0068:
                r2 = defpackage.yt.a.this;
                r2 = defpackage.yt.this;
                r2 = r2.m;
                r2.a(r12);
                return r0;
            L_0x0074:
                r2 = r8.c();
                r3 = r8.d();
                if (r2 == 0) goto L_0x00e6;
            L_0x007e:
                if (r3 != 0) goto L_0x0082;
            L_0x0080:
                goto L_0x00e6;
            L_0x0082:
                r17 = r2.s();
                r2 = defpackage.yt.a.this;
                r2 = defpackage.yt.this;
                r2 = r2.g;
                r9 = r2.a;
                r17 = r17 - r9;
                r2 = (r17 > r0 ? 1 : (r17 == r0 ? 0 : -1));
                if (r2 >= 0) goto L_0x0097;
            L_0x0094:
                r9 = 30;
                goto L_0x00ab;
            L_0x0097:
                r9 = 10;
                r17 = r17 * r9;
                r2 = defpackage.yt.a.this;
                r2 = defpackage.yt.this;
                r2 = r2.a;
                r2 = r2.t;
                r9 = r2.d;
                r17 = r17 / r9;
                r9 = 30;
                r9 = r17 + r9;
            L_0x00ab:
                r0 = 100;
                r0 = java.lang.Math.min(r0, r9);
                if (r25 == 0) goto L_0x00b6;
            L_0x00b3:
                r19 = 0;
                goto L_0x00b8;
            L_0x00b6:
                r19 = r0;
            L_0x00b8:
                r7 = r8.a();
                r9 = new yt$a$a$2;
                r0 = r9;
                r1 = r24;
                r2 = r3;
                r3 = r25;
                r4 = r7;
                r10 = r8;
                r7 = r19;
                r21 = r12;
                r13 = r9;
                r12 = r10;
                r9 = r14;
                r0.<init>(r2, r3, r4, r5, r7, r9);
                r12.b(r13);
                r0 = aaf.a();
                r0 = r0 - r14;
                r2 = defpackage.yt.a.this;
                r2 = defpackage.yt.this;
                r2 = r2.m;
                r5 = r21;
                r2.a(r5);
                return r0;
            L_0x00e6:
                r5 = r12;
                r0 = defpackage.yt.a.this;
                r0 = defpackage.yt.this;
                r0 = r0.m;
                r0.a(r5);
                r0 = 0;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.yt$a$a.b(boolean):long");
            }

            /* JADX WARNING: Removed duplicated region for block: B:26:0x0047  */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x003a  */
            /* JADX WARNING: Removed duplicated region for block: B:26:0x0047  */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x003a  */
            public boolean a(defpackage.zf r7) {
                /*
                r6 = this;
                r0 = r7.b();
                r1 = 1;
                if (r0 != 0) goto L_0x0010;
            L_0x0007:
                r0 = defpackage.yt.a.this;
                r0 = defpackage.yt.this;
                r0 = r0.b;
                r7.a(r0, r1);
            L_0x0010:
                r0 = 0;
                r2 = 0;
                r3 = defpackage.yt.a.this;	 Catch:{ OutOfMemoryError -> 0x0044, Exception -> 0x0037 }
                r3 = r3.d;	 Catch:{ OutOfMemoryError -> 0x0044, Exception -> 0x0037 }
                r3 = r3.a();	 Catch:{ OutOfMemoryError -> 0x0044, Exception -> 0x0037 }
                r3 = (master.flame.danmaku.danmaku.model.android.f) r3;	 Catch:{ OutOfMemoryError -> 0x0044, Exception -> 0x0037 }
                r4 = defpackage.yt.a.this;	 Catch:{ OutOfMemoryError -> 0x0045, Exception -> 0x0038 }
                r4 = defpackage.yt.this;	 Catch:{ OutOfMemoryError -> 0x0045, Exception -> 0x0038 }
                r4 = r4.b;	 Catch:{ OutOfMemoryError -> 0x0045, Exception -> 0x0038 }
                r5 = defpackage.yt.a.this;	 Catch:{ OutOfMemoryError -> 0x0045, Exception -> 0x0038 }
                r5 = defpackage.yt.this;	 Catch:{ OutOfMemoryError -> 0x0045, Exception -> 0x0038 }
                r5 = r5.a;	 Catch:{ OutOfMemoryError -> 0x0045, Exception -> 0x0038 }
                r5 = r5.u;	 Catch:{ OutOfMemoryError -> 0x0045, Exception -> 0x0038 }
                r5 = r5.d;	 Catch:{ OutOfMemoryError -> 0x0045, Exception -> 0x0038 }
                r4 = aae.a(r7, r4, r3, r5);	 Catch:{ OutOfMemoryError -> 0x0045, Exception -> 0x0038 }
                r7.v = r4;	 Catch:{ OutOfMemoryError -> 0x0035, Exception -> 0x0033 }
                return r1;
            L_0x0033:
                r3 = r4;
                goto L_0x0038;
            L_0x0035:
                r3 = r4;
                goto L_0x0045;
            L_0x0037:
                r3 = r2;
            L_0x0038:
                if (r3 == 0) goto L_0x0041;
            L_0x003a:
                r1 = defpackage.yt.a.this;
                r1 = r1.d;
                r1.a(r3);
            L_0x0041:
                r7.v = r2;
                return r0;
            L_0x0044:
                r3 = r2;
            L_0x0045:
                if (r3 == 0) goto L_0x004e;
            L_0x0047:
                r1 = defpackage.yt.a.this;
                r1 = r1.d;
                r1.a(r3);
            L_0x004e:
                r7.v = r2;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.yt$a$a.a(zf):boolean");
            }

            private byte a(zf zfVar, boolean z) {
                if (!zfVar.b()) {
                    zfVar.a(yt.this.b, true);
                }
                f fVar = null;
                f fVar2;
                try {
                    zf a = a.this.a(zfVar, true, yt.this.a.u.i);
                    fVar2 = a != null ? (f) a.v : null;
                    if (fVar2 != null) {
                        try {
                            fVar2.k();
                            zfVar.v = fVar2;
                            yt.this.l.a(zfVar, 0, z);
                            return (byte) 0;
                        } catch (OutOfMemoryError unused) {
                            a(zfVar, fVar2);
                            return (byte) 1;
                        } catch (Exception unused2) {
                            a(zfVar, fVar2);
                            return (byte) 1;
                        }
                    }
                    zf a2 = a.this.a(zfVar, false, yt.this.a.u.j);
                    if (a2 != null) {
                        fVar2 = (f) a2.v;
                    }
                    if (fVar2 != null) {
                        a2.v = null;
                        fVar = aae.a(zfVar, yt.this.b, fVar2, yt.this.a.u.d);
                        zfVar.v = fVar;
                        yt.this.l.a(zfVar, 0, z);
                        return (byte) 0;
                    }
                    int a3 = aae.a((int) zfVar.n, (int) zfVar.o, yt.this.a.u.d / 8);
                    if (a3 * 2 > yt.this.k) {
                        return (byte) 1;
                    }
                    if (z || a.this.g + a3 <= a.this.f) {
                        fVar2 = aae.a(zfVar, yt.this.b, (f) a.this.d.a(), yt.this.a.u.d);
                        zfVar.v = fVar2;
                        z = yt.this.l.a(zfVar, a.this.b(zfVar), z);
                        if (!z) {
                            a(zfVar, fVar2);
                        }
                        return z ^ 1;
                    }
                    yt.this.l.a(a3, false);
                    return (byte) 1;
                } catch (OutOfMemoryError unused3) {
                    fVar2 = fVar;
                    a(zfVar, fVar2);
                    return (byte) 1;
                } catch (Exception unused4) {
                    fVar2 = r0;
                    a(zfVar, fVar2);
                    return (byte) 1;
                }
            }

            private final void b(zf zfVar) {
                if (!zfVar.f() && (zfVar.s() <= yt.this.m.a + yt.this.a.t.d || zfVar.w)) {
                    if (zfVar.m != (byte) 0 || !zfVar.j()) {
                        zp d = zfVar.d();
                        if (d == null || d.a() == null) {
                            a(zfVar, true);
                        }
                    }
                }
            }

            public void b() {
                sendEmptyMessage(1);
                sendEmptyMessageDelayed(4, yt.this.a.t.d);
            }

            public void c() {
                this.b = true;
                sendEmptyMessage(6);
            }

            public void d() {
                sendEmptyMessage(18);
                this.b = false;
                removeMessages(16);
                sendEmptyMessage(16);
                sendEmptyMessageDelayed(4, yt.this.a.t.d);
            }

            public void a(long j) {
                removeMessages(3);
                this.d = true;
                sendEmptyMessage(18);
                yt.this.m.a(yt.this.g.a + j);
                sendEmptyMessage(3);
            }

            public void a(boolean z) {
                this.c = z ^ 1;
            }
        }

        /* compiled from: CacheManagingDrawTask */
        /* renamed from: yt$a$1 */
        class 1 extends c<zf> {
            1() {
            }

            public int a(zf zfVar) {
                a.this.a(true, zfVar, null);
                return 0;
            }
        }

        /* compiled from: CacheManagingDrawTask */
        /* renamed from: yt$a$2 */
        class 2 extends c<zf> {
            2() {
            }

            public int a(zf zfVar) {
                if (!zfVar.g()) {
                    return 0;
                }
                a.this.a(true, zfVar, null);
                return 2;
            }
        }

        /* compiled from: CacheManagingDrawTask */
        /* renamed from: yt$a$3 */
        class 3 extends c<zf> {
            3() {
            }

            public int a(zf zfVar) {
                if (!zfVar.f()) {
                    return 1;
                }
                zp zpVar = zfVar.v;
                if (yt.this.a.u.f == -1 && zpVar != null && !zpVar.f() && ((float) zpVar.c()) / ((float) yt.this.k) < yt.this.a.u.g) {
                    return 0;
                }
                if (!a.this.j) {
                    synchronized (yt.this.n) {
                        try {
                            yt.this.n.wait(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return 1;
                        } catch (Throwable th) {
                        }
                    }
                }
                a.this.a(false, zfVar, null);
                return 2;
            }
        }

        public a(int i, int i2) {
            this.f = i;
            this.h = i2;
        }

        public void a(long j) {
            if (this.i != null) {
                this.i.a();
                this.i.removeMessages(3);
                this.i.obtainMessage(5, Long.valueOf(j)).sendToTarget();
            }
        }

        public void a(zf zfVar) {
            if (this.i != null) {
                if (zfVar.w && zfVar.x) {
                    if (!zfVar.f()) {
                        this.i.a(zfVar);
                    }
                    return;
                }
                this.i.obtainMessage(2, zfVar).sendToTarget();
            }
        }

        public void a() {
            this.j = false;
            if (this.a == null) {
                this.a = new HandlerThread("DFM Cache-Building Thread");
                this.a.start();
            }
            if (this.i == null) {
                this.i = new a(this.a.getLooper());
            }
            this.i.b();
        }

        public void b() {
            this.j = true;
            synchronized (yt.this.n) {
                yt.this.n.notifyAll();
            }
            if (this.i != null) {
                this.i.removeCallbacksAndMessages(null);
                this.i.c();
                this.i = null;
            }
            if (this.a != null) {
                try {
                    this.a.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.a.quit();
                this.a = null;
            }
        }

        public void c() {
            if (this.i != null) {
                this.i.d();
            } else {
                a();
            }
        }

        public void a(int i) {
            if (this.i != null) {
                a aVar = this.i;
                boolean z = true;
                if (i != 1) {
                    z = false;
                }
                aVar.a(z);
            }
        }

        public float d() {
            if (this.f == 0) {
                return CropImageView.DEFAULT_ASPECT_RATIO;
            }
            return ((float) this.g) / ((float) this.f);
        }

        private void i() {
            if (this.b != null) {
                this.b.b(new 1());
                this.b.b();
            }
            this.g = 0;
        }

        private void j() {
            if (this.b != null) {
                this.b.b(new 2());
            }
        }

        /* Access modifiers changed, original: protected */
        public void a(boolean z, zf zfVar, zf zfVar2) {
            zp d = zfVar.d();
            if (d != null) {
                long c = c(zfVar);
                if (zfVar.f()) {
                    yt.this.a.b().d().releaseResource(zfVar);
                }
                if (c > 0) {
                    this.g = (int) (((long) this.g) - c);
                    this.d.a((f) d);
                }
            }
        }

        private long c(zf zfVar) {
            zp zpVar = zfVar.v;
            if (zpVar == null) {
                return 0;
            }
            if (zpVar.f()) {
                zpVar.g();
                zfVar.v = null;
                return 0;
            }
            long b = (long) b(zfVar);
            zpVar.b();
            zfVar.v = null;
            return b;
        }

        /* Access modifiers changed, original: protected */
        public int b(zf zfVar) {
            return (zfVar.v == null || zfVar.v.f()) ? 0 : zfVar.v.c();
        }

        private void k() {
            while (true) {
                f fVar = (f) this.d.a();
                if (fVar != null) {
                    fVar.b();
                } else {
                    return;
                }
            }
        }

        private boolean a(zf zfVar, int i, boolean z) {
            if (i > 0) {
                a(i, z);
            }
            this.b.a(zfVar);
            this.g += i;
            return true;
        }

        private void l() {
            this.b.b(new 3());
        }

        private zf a(zf zfVar, boolean z, int i) {
            final int j = (!z ? yt.this.b.j() * 2 : 0) + yt.this.a.u.h;
            final int i2 = i;
            final zf zfVar2 = zfVar;
            final boolean z2 = z;
            b 4 = new b<zf, zf>() {
                int a = 0;
                zf b;

                /* renamed from: a */
                public zf b() {
                    return this.b;
                }

                public int a(zf zfVar) {
                    int i = this.a;
                    this.a = i + 1;
                    if (i >= i2) {
                        return 1;
                    }
                    zp d = zfVar.d();
                    if (d == null || d.a() == null) {
                        return 0;
                    }
                    if (zfVar.n == zfVar2.n && zfVar.o == zfVar2.o && zfVar.i == zfVar2.i && zfVar.k == zfVar2.k && zfVar.e == zfVar2.e && zfVar.b.equals(zfVar2.b) && zfVar.d == zfVar2.d) {
                        this.b = zfVar;
                        return 1;
                    } else if (z2) {
                        return 0;
                    } else {
                        if (!zfVar.f()) {
                            return 1;
                        }
                        if (d.f()) {
                            return 0;
                        }
                        float d2 = ((float) d.d()) - zfVar2.n;
                        float e = ((float) d.e()) - zfVar2.o;
                        if (d2 < CropImageView.DEFAULT_ASPECT_RATIO || d2 > ((float) j) || e < CropImageView.DEFAULT_ASPECT_RATIO || e > ((float) j)) {
                            return 0;
                        }
                        this.b = zfVar;
                        return 1;
                    }
                }
            };
            this.b.b(4);
            return (zf) 4.b();
        }

        private void a(final int i, final boolean z) {
            this.b.b(new c<zf>() {
                public int a(zf zfVar) {
                    if (a.this.j || a.this.g + i <= a.this.f) {
                        return 1;
                    }
                    if (zfVar.f() || zfVar.j()) {
                        a.this.a(false, zfVar, null);
                        return 2;
                    } else if (z) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        }

        public long e() {
            if (this.b == null || this.b.a() <= 0) {
                return 0;
            }
            zf c = this.b.c();
            if (c == null) {
                return 0;
            }
            return c.s();
        }

        public void b(long j) {
            if (this.i != null) {
                this.i.a(j);
            }
        }

        public void f() {
            if (this.i != null) {
                this.i.removeMessages(3);
                this.i.removeMessages(18);
                this.i.a();
                this.i.removeMessages(7);
                this.i.sendEmptyMessage(7);
            }
        }

        public void g() {
            if (this.i != null) {
                this.i.removeMessages(9);
                this.i.sendEmptyMessage(9);
            }
        }

        public void h() {
            if (this.i != null) {
                this.i.removeMessages(4);
                this.i.sendEmptyMessage(4);
            }
        }

        public void a(Runnable runnable) {
            if (this.i != null) {
                this.i.post(runnable);
            }
        }
    }

    public yt(zh zhVar, DanmakuContext danmakuContext, a aVar) {
        super(zhVar, danmakuContext, aVar);
        NativeBitmapFactory.a();
        this.k = (int) Math.max(4194304.0f, ((float) Runtime.getRuntime().maxMemory()) * danmakuContext.u.e);
        this.l = new a(this.k, 3);
        this.f.a(this.l);
    }

    /* Access modifiers changed, original: protected */
    public void a(zh zhVar) {
        this.g = zhVar;
        this.m = new zh();
        this.m.a(zhVar.a);
    }

    public void a(zf zfVar) {
        super.a(zfVar);
        if (this.l != null) {
            this.l.a(zfVar);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(zf zfVar) {
        super.b(zfVar);
        if (this.l != null) {
            int i = this.o + 1;
            this.o = i;
            if (i > 5) {
                this.l.h();
                this.o = 0;
                return;
            }
            return;
        }
        zp d = zfVar.d();
        if (d != null) {
            if (d.f()) {
                d.g();
            } else {
                d.b();
            }
            zfVar.v = null;
        }
    }

    public b a(zd zdVar) {
        b a = super.a(zdVar);
        synchronized (this.n) {
            this.n.notify();
        }
        if (!(a == null || this.l == null || a.k - a.l >= -20)) {
            this.l.h();
            this.l.b(-this.a.t.d);
        }
        return a;
    }

    public void a(long j) {
        super.a(j);
        if (this.l == null) {
            a();
        }
        this.l.a(j);
    }

    public void a() {
        super.a();
        NativeBitmapFactory.a();
        if (this.l == null) {
            this.l = new a(this.k, 3);
            this.l.a();
            this.f.a(this.l);
            return;
        }
        this.l.c();
    }

    public void b() {
        super.b();
        d();
        this.f.a(null);
        if (this.l != null) {
            this.l.b();
            this.l = null;
        }
        NativeBitmapFactory.b();
    }

    public void c() {
        if (this.d != null) {
            a(this.d);
            this.l.a();
        }
    }

    public void a(int i) {
        super.a(i);
        if (this.l != null) {
            this.l.a(i);
        }
    }

    public void a(long j, long j2, long j3) {
        super.a(j, j2, j3);
        if (this.l != null) {
            this.l.a(j2);
        }
    }

    public boolean a(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        if (!super.b(danmakuContext, danmakuConfigTag, objArr)) {
            if (DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag)) {
                this.b.b(this.a.c);
                e();
            } else if (danmakuConfigTag.isVisibilityRelatedTag()) {
                if (objArr != null && objArr.length > 0 && objArr[0] != null && ((!(objArr[0] instanceof Boolean) || ((Boolean) objArr[0]).booleanValue()) && this.l != null)) {
                    this.l.b(0);
                }
                e();
            } else if (DanmakuConfigTag.TRANSPARENCY.equals(danmakuConfigTag) || DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuConfigTag.DANMAKU_STYLE.equals(danmakuConfigTag)) {
                if (DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag)) {
                    this.b.b(this.a.c);
                }
                if (this.l != null) {
                    this.l.f();
                    this.l.b(-this.a.t.d);
                }
            } else if (this.l != null) {
                this.l.g();
                this.l.b(0);
            }
        }
        if (!(this.e == null || this.l == null)) {
            this.l.a(new 1());
        }
        return true;
    }
}

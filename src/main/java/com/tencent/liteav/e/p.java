package com.tencent.liteav.e;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PicDec */
public class p {
    private int A;
    private int B = -1;
    private final String a = "PicDec";
    private int b = 1;
    private Handler c;
    private HandlerThread d = new HandlerThread("picDec_handler_thread");
    private List<Bitmap> e;
    private j f;
    private int g = 20;
    private long h;
    private List<Long> i;
    private long j = 1000;
    private long k = 500;
    private long l;
    private long m;
    private boolean n;
    private long o;
    private long p;
    private long q = -1;
    private long r = -1;
    private long s = -1;
    private long t = -1;
    private e u;
    private AtomicBoolean v;
    private AtomicBoolean w;
    private long x;
    private int y = 0;
    private int z;

    /* compiled from: PicDec */
    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    p.this.o();
                    p.this.c(p.this.o);
                    p.this.c.sendEmptyMessage(2);
                    return;
                case 2:
                    p.this.m();
                    return;
                case 3:
                    p.this.k();
                    return;
                case 4:
                    TXCLog.i("PicDec", "stopDecode");
                    p.this.l();
                    return;
                case 5:
                    p.this.b(p.this.x);
                    return;
                default:
                    return;
            }
        }
    }

    public p() {
        this.d.start();
        this.c = new a(this.d.getLooper());
        this.e = new ArrayList();
        this.i = new ArrayList();
        this.v = new AtomicBoolean(false);
        this.w = new AtomicBoolean(false);
    }

    public void a(boolean z) {
        this.n = z;
    }

    public void a(List<Bitmap> list, int i) {
        if (list == null || list.size() == 0) {
            TXCLog.e("PicDec", "setBitmapList, bitmapList is empty");
            return;
        }
        if (i <= 0) {
            this.g = 20;
        } else {
            this.g = i;
        }
        a((List) list);
        this.h = (long) (1000 / this.g);
    }

    public long a(int i) {
        synchronized (this) {
            if (this.B != i) {
                this.i.clear();
            }
        }
        this.B = i;
        this.j = com.tencent.liteav.j.a.a(i);
        this.k = com.tencent.liteav.j.a.b(i);
        if (i == 5 || i == 4) {
            this.l = ((long) this.e.size()) * (this.j + this.k);
        } else {
            this.l = (((long) this.e.size()) * (this.j + this.k)) - this.k;
        }
        this.m = this.h * ((long) (((int) ((this.l / 1000) * ((long) this.g))) - 1));
        return this.m;
    }

    public void a(j jVar) {
        this.f = jVar;
    }

    private void j() {
        this.q = -1;
        this.r = -1;
        this.s = -1;
        this.t = -1;
        this.v.compareAndSet(true, false);
    }

    public void a(long j, long j2) {
        this.o = j;
        this.p = j2;
    }

    public int a() {
        if (this.e.size() == 0) {
            return 0;
        }
        this.z = 720;
        return this.z;
    }

    public int b() {
        if (this.e.size() == 0) {
            return 0;
        }
        this.A = 1280;
        return this.A;
    }

    public boolean c() {
        return this.w.get();
    }

    public synchronized void d() {
        if (this.b == 2) {
            TXCLog.e("PicDec", "start(), mState is play, ignore");
            return;
        }
        this.b = 2;
        this.w.compareAndSet(true, false);
        j();
        this.c.sendEmptyMessage(1);
    }

    public synchronized void e() {
        if (this.b == 1) {
            TXCLog.e("PicDec", "stop(), mState is init, ignore");
            return;
        }
        this.b = 1;
        this.c.sendEmptyMessage(4);
    }

    public void f() {
        if (this.b == 1 || this.b == 3) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("pause(), mState = ");
            stringBuilder.append(this.b);
            stringBuilder.append(", ignore");
            TXCLog.e("PicDec", stringBuilder.toString());
            return;
        }
        this.b = 3;
        this.c.sendEmptyMessage(3);
    }

    public void g() {
        if (this.b == 1 || this.b == 2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("resume(), mState = ");
            stringBuilder.append(this.b);
            stringBuilder.append(", ignore");
            TXCLog.e("PicDec", stringBuilder.toString());
            return;
        }
        this.b = 2;
        this.c.sendEmptyMessage(2);
    }

    public void a(long j) {
        this.b = 4;
        this.x = j;
        this.c.sendEmptyMessage(5);
    }

    public synchronized void h() {
        if (this.b == 1) {
            TXCLog.e("PicDec", "getNextBitmapFrame, current state is init, ignore");
        } else {
            this.c.sendEmptyMessage(2);
        }
    }

    private void k() {
        this.c.removeMessages(2);
        j();
    }

    private void l() {
        this.c.removeMessages(2);
        j();
    }

    /* JADX WARNING: Missing block: B:24:0x0060, code skipped:
            if (r6 != -1) goto L_0x0094;
     */
    /* JADX WARNING: Missing block: B:25:0x0062, code skipped:
            r12.u.c(4);
            r12.u.a(0);
            r12.u.j(a());
            r12.u.k(b());
            r12.u.m(0);
            b(r12.u);
            r12.c.sendEmptyMessage(4);
            r12.w.set(true);
     */
    /* JADX WARNING: Missing block: B:26:0x0093, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:27:0x0094, code skipped:
            monitor-enter(r12);
     */
    /* JADX WARNING: Missing block: B:29:?, code skipped:
            r12.y++;
     */
    /* JADX WARNING: Missing block: B:30:0x009a, code skipped:
            monitor-exit(r12);
     */
    /* JADX WARNING: Missing block: B:31:0x009b, code skipped:
            r12.s = r6 / 1000;
            r12.u.a(r6);
            r12.u.b(r6);
            r12.u.f(r12.g);
            r12.u.m(0);
            a(r12.u);
     */
    /* JADX WARNING: Missing block: B:32:0x00bc, code skipped:
            if (r12.n != false) goto L_0x00c4;
     */
    /* JADX WARNING: Missing block: B:33:0x00be, code skipped:
            b(r12.u);
     */
    /* JADX WARNING: Missing block: B:34:0x00c3, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:36:0x00c8, code skipped:
            if (r12.q >= 0) goto L_0x00e1;
     */
    /* JADX WARNING: Missing block: B:37:0x00ca, code skipped:
            r12.q = r12.s;
            r12.v.set(true);
            r12.s = r6;
            r12.r = java.lang.System.currentTimeMillis();
            r12.c.sendEmptyMessage(2);
     */
    /* JADX WARNING: Missing block: B:38:0x00e0, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:39:0x00e1, code skipped:
            r12.v.compareAndSet(true, false);
            r12.c.sendEmptyMessageDelayed(2, 5);
     */
    /* JADX WARNING: Missing block: B:40:0x00eb, code skipped:
            return;
     */
    private void m() {
        /*
        r12 = this;
        r0 = r12.n;
        r1 = 5;
        r3 = 2;
        r4 = 0;
        if (r0 == 0) goto L_0x002e;
    L_0x0009:
        r6 = r12.s;
        r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r0 < 0) goto L_0x002e;
    L_0x000f:
        r0 = r12.v;
        r0 = r0.get();
        if (r0 == 0) goto L_0x001d;
    L_0x0017:
        r0 = r12.u;
        r12.b(r0);
        goto L_0x002e;
    L_0x001d:
        r0 = r12.n();
        if (r0 != 0) goto L_0x0029;
    L_0x0023:
        r0 = r12.c;
        r0.sendEmptyMessageDelayed(r3, r1);
        return;
    L_0x0029:
        r0 = r12.u;
        r12.b(r0);
    L_0x002e:
        r0 = new com.tencent.liteav.d.e;
        r0.<init>();
        r12.u = r0;
        monitor-enter(r12);
        r0 = r12.i;	 Catch:{ all -> 0x00ef }
        r0 = r0.size();	 Catch:{ all -> 0x00ef }
        if (r0 > 0) goto L_0x0040;
    L_0x003e:
        monitor-exit(r12);	 Catch:{ all -> 0x00ef }
        return;
    L_0x0040:
        r0 = r12.i;	 Catch:{ all -> 0x00ef }
        r6 = r12.y;	 Catch:{ all -> 0x00ef }
        r0 = r0.get(r6);	 Catch:{ all -> 0x00ef }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x00ef }
        r6 = r0.longValue();	 Catch:{ all -> 0x00ef }
        r8 = r12.p;	 Catch:{ all -> 0x00ef }
        r10 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r8 = r8 * r10;
        r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        r8 = -1;
        if (r0 <= 0) goto L_0x005b;
    L_0x005a:
        r6 = r8;
    L_0x005b:
        monitor-exit(r12);	 Catch:{ all -> 0x00ef }
        r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        r8 = 0;
        r9 = 1;
        if (r0 != 0) goto L_0x0094;
    L_0x0062:
        r0 = r12.u;
        r1 = 4;
        r0.c(r1);
        r0 = r12.u;
        r0.a(r4);
        r0 = r12.u;
        r2 = r12.a();
        r0.j(r2);
        r0 = r12.u;
        r2 = r12.b();
        r0.k(r2);
        r0 = r12.u;
        r0.m(r8);
        r0 = r12.u;
        r12.b(r0);
        r0 = r12.c;
        r0.sendEmptyMessage(r1);
        r0 = r12.w;
        r0.set(r9);
        return;
    L_0x0094:
        monitor-enter(r12);
        r0 = r12.y;	 Catch:{ all -> 0x00ec }
        r0 = r0 + r9;
        r12.y = r0;	 Catch:{ all -> 0x00ec }
        monitor-exit(r12);	 Catch:{ all -> 0x00ec }
        r10 = r6 / r10;
        r12.s = r10;
        r0 = r12.u;
        r0.a(r6);
        r0 = r12.u;
        r0.b(r6);
        r0 = r12.u;
        r10 = r12.g;
        r0.f(r10);
        r0 = r12.u;
        r0.m(r8);
        r0 = r12.u;
        r12.a(r0);
        r0 = r12.n;
        if (r0 != 0) goto L_0x00c4;
    L_0x00be:
        r0 = r12.u;
        r12.b(r0);
        return;
    L_0x00c4:
        r10 = r12.q;
        r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x00e1;
    L_0x00ca:
        r0 = r12.s;
        r12.q = r0;
        r0 = r12.v;
        r0.set(r9);
        r12.s = r6;
        r0 = java.lang.System.currentTimeMillis();
        r12.r = r0;
        r0 = r12.c;
        r0.sendEmptyMessage(r3);
        return;
    L_0x00e1:
        r0 = r12.v;
        r0.compareAndSet(r9, r8);
        r0 = r12.c;
        r0.sendEmptyMessageDelayed(r3, r1);
        return;
    L_0x00ec:
        r0 = move-exception;
        monitor-exit(r12);	 Catch:{ all -> 0x00ec }
        throw r0;
    L_0x00ef:
        r0 = move-exception;
        monitor-exit(r12);	 Catch:{ all -> 0x00ef }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.e.p.m():void");
    }

    private void b(long j) {
        c(j);
        this.u = new e();
        synchronized (this) {
            if (this.i.size() <= 0) {
                return;
            }
            j = ((Long) this.i.get(this.y)).longValue();
            this.u.a(j);
            this.u.m(0);
            a(this.u);
            b(this.u);
        }
    }

    private void a(e eVar) {
        Object obj;
        long e = eVar.e() / 1000;
        int i = (int) (e / (this.j + this.k));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setBitmapsAndDisplayRatio, frameTimeMs = ");
        stringBuilder.append(e);
        stringBuilder.append(", picIndex = ");
        stringBuilder.append(i);
        stringBuilder.append(", loopTime = ");
        stringBuilder.append(this.j + this.k);
        TXCLog.i("PicDec", stringBuilder.toString());
        if (i >= this.e.size()) {
            obj = (Bitmap) this.e.get(this.e.size() - 1);
        } else {
            obj = (Bitmap) this.e.get(i);
        }
        Object obj2 = null;
        if (i < this.e.size() - 1) {
            obj2 = (Bitmap) this.e.get(i + 1);
        }
        List arrayList = new ArrayList();
        arrayList.add(obj);
        if (obj2 != null) {
            arrayList.add(obj2);
        }
        eVar.a(arrayList);
        eVar.j(a());
        eVar.k(b());
    }

    private boolean n() {
        this.t = System.currentTimeMillis();
        this.s = this.u.e() / 1000;
        return Math.abs(this.s - this.q) < this.t - this.r;
    }

    private void b(e eVar) {
        if (this.f != null) {
            this.f.a(eVar);
        }
    }

    private synchronized void c(long j) {
        if (this.i.size() > 0) {
            for (int i = 0; i < this.i.size(); i++) {
                if (((Long) this.i.get(i)).longValue() / 1000 >= j) {
                    this.y = i;
                    return;
                }
            }
        }
    }

    private void a(List<Bitmap> list) {
        for (int i = 0; i < list.size(); i++) {
            this.e.add(a((Bitmap) list.get(i), 720, 1280));
        }
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = (float) width;
        float f2 = (float) height;
        float f3 = (float) i;
        float f4 = (float) i2;
        f3 = f / f2 >= f3 / f4 ? f3 / f : f4 / f2;
        Matrix matrix = new Matrix();
        matrix.postScale(f3, f3);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    private synchronized void o() {
        if (this.i.size() <= 0) {
            int i = (int) ((this.l / 1000) * ((long) this.g));
            for (int i2 = 0; i2 < i; i2++) {
                if (i2 == i - 1) {
                    this.i.add(Long.valueOf((((long) i2) * this.h) * 1000));
                    this.i.add(Long.valueOf(-1));
                } else {
                    this.i.add(Long.valueOf((((long) i2) * this.h) * 1000));
                }
            }
        }
    }

    public void i() {
        for (int i = 0; i < this.e.size(); i++) {
            ((Bitmap) this.e.get(i)).recycle();
        }
        this.e.clear();
        if (this.d != null) {
            this.d.quit();
        }
    }
}

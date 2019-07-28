package defpackage;

import com.google.android.exoplayer2.util.a;
import java.util.ArrayDeque;

/* compiled from: SimpleDecoder */
/* renamed from: gu */
public abstract class gu<I extends gs, O extends gt, E extends Exception> implements gq<I, O, E> {
    private final Thread a;
    private final Object b = new Object();
    private final ArrayDeque<I> c = new ArrayDeque();
    private final ArrayDeque<O> d = new ArrayDeque();
    private final I[] e;
    private final O[] f;
    private int g;
    private int h;
    private I i;
    private E j;
    private boolean k;
    private boolean l;
    private int m;

    /* compiled from: SimpleDecoder */
    /* renamed from: gu$1 */
    class 1 extends Thread {
        1() {
        }

        public void run() {
            gu.this.k();
        }
    }

    public abstract E a(I i, O o, boolean z);

    public abstract E a(Throwable th);

    public abstract I g();

    public abstract O h();

    protected gu(I[] iArr, O[] oArr) {
        this.e = iArr;
        this.g = iArr.length;
        for (int i = 0; i < this.g; i++) {
            this.e[i] = g();
        }
        this.f = oArr;
        this.h = oArr.length;
        for (int i2 = 0; i2 < this.h; i2++) {
            this.f[i2] = h();
        }
        this.a = new 1();
        this.a.start();
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(int i) {
        a.b(this.g == this.e.length);
        for (gs e : this.e) {
            e.e(i);
        }
    }

    /* renamed from: e */
    public final I a() throws Exception {
        gs gsVar;
        synchronized (this.b) {
            i();
            a.b(this.i == null);
            if (this.g == 0) {
                gsVar = null;
            } else {
                gs[] gsVarArr = this.e;
                int i = this.g - 1;
                this.g = i;
                gsVar = gsVarArr[i];
            }
            this.i = gsVar;
            gsVar = this.i;
        }
        return gsVar;
    }

    public final void a(I i) throws Exception {
        synchronized (this.b) {
            i();
            a.a(i == this.i);
            this.c.addLast(i);
            j();
            this.i = null;
        }
    }

    /* renamed from: f */
    public final O b() throws Exception {
        synchronized (this.b) {
            i();
            if (this.d.isEmpty()) {
                return null;
            }
            gt gtVar = (gt) this.d.removeFirst();
            return gtVar;
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(O o) {
        synchronized (this.b) {
            b((gt) o);
            j();
        }
    }

    public final void c() {
        synchronized (this.b) {
            this.k = true;
            this.m = 0;
            if (this.i != null) {
                b(this.i);
                this.i = null;
            }
            while (!this.c.isEmpty()) {
                b((gs) this.c.removeFirst());
            }
            while (!this.d.isEmpty()) {
                b((gt) this.d.removeFirst());
            }
        }
    }

    public void d() {
        synchronized (this.b) {
            this.l = true;
            this.b.notify();
        }
        try {
            this.a.join();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    private void i() throws Exception {
        if (this.j != null) {
            throw this.j;
        }
    }

    private void j() {
        if (m()) {
            this.b.notify();
        }
    }

    private void k() {
        while (l()) {
            try {
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /* JADX WARNING: Missing block: B:15:0x0035, code skipped:
            if (r1.c() == false) goto L_0x003c;
     */
    /* JADX WARNING: Missing block: B:16:0x0037, code skipped:
            r3.b(4);
     */
    /* JADX WARNING: Missing block: B:18:0x0040, code skipped:
            if (r1.e_() == false) goto L_0x0047;
     */
    /* JADX WARNING: Missing block: B:19:0x0042, code skipped:
            r3.b(com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView.UNCHECKED);
     */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            r6.j = a(r1, r3, r4);
     */
    /* JADX WARNING: Missing block: B:22:0x004e, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:23:0x004f, code skipped:
            r6.j = a(r0);
     */
    /* JADX WARNING: Missing block: B:24:0x0056, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:25:0x0057, code skipped:
            r6.j = a(r0);
     */
    private boolean l() throws java.lang.InterruptedException {
        /*
        r6 = this;
        r0 = r6.b;
        monitor-enter(r0);
    L_0x0003:
        r1 = r6.l;	 Catch:{ all -> 0x0096 }
        if (r1 != 0) goto L_0x0013;
    L_0x0007:
        r1 = r6.m();	 Catch:{ all -> 0x0096 }
        if (r1 != 0) goto L_0x0013;
    L_0x000d:
        r1 = r6.b;	 Catch:{ all -> 0x0096 }
        r1.wait();	 Catch:{ all -> 0x0096 }
        goto L_0x0003;
    L_0x0013:
        r1 = r6.l;	 Catch:{ all -> 0x0096 }
        r2 = 0;
        if (r1 == 0) goto L_0x001a;
    L_0x0018:
        monitor-exit(r0);	 Catch:{ all -> 0x0096 }
        return r2;
    L_0x001a:
        r1 = r6.c;	 Catch:{ all -> 0x0096 }
        r1 = r1.removeFirst();	 Catch:{ all -> 0x0096 }
        r1 = (defpackage.gs) r1;	 Catch:{ all -> 0x0096 }
        r3 = r6.f;	 Catch:{ all -> 0x0096 }
        r4 = r6.h;	 Catch:{ all -> 0x0096 }
        r5 = 1;
        r4 = r4 - r5;
        r6.h = r4;	 Catch:{ all -> 0x0096 }
        r3 = r3[r4];	 Catch:{ all -> 0x0096 }
        r4 = r6.k;	 Catch:{ all -> 0x0096 }
        r6.k = r2;	 Catch:{ all -> 0x0096 }
        monitor-exit(r0);	 Catch:{ all -> 0x0096 }
        r0 = r1.c();
        if (r0 == 0) goto L_0x003c;
    L_0x0037:
        r0 = 4;
        r3.b(r0);
        goto L_0x0069;
    L_0x003c:
        r0 = r1.e_();
        if (r0 == 0) goto L_0x0047;
    L_0x0042:
        r0 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r3.b(r0);
    L_0x0047:
        r0 = r6.a(r1, r3, r4);	 Catch:{ RuntimeException -> 0x0056, OutOfMemoryError -> 0x004e }
        r6.j = r0;	 Catch:{ RuntimeException -> 0x0056, OutOfMemoryError -> 0x004e }
        goto L_0x005d;
    L_0x004e:
        r0 = move-exception;
        r0 = r6.a(r0);
        r6.j = r0;
        goto L_0x005d;
    L_0x0056:
        r0 = move-exception;
        r0 = r6.a(r0);
        r6.j = r0;
    L_0x005d:
        r0 = r6.j;
        if (r0 == 0) goto L_0x0069;
    L_0x0061:
        r0 = r6.b;
        monitor-enter(r0);
        monitor-exit(r0);	 Catch:{ all -> 0x0066 }
        return r2;
    L_0x0066:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0066 }
        throw r1;
    L_0x0069:
        r4 = r6.b;
        monitor-enter(r4);
        r0 = r6.k;	 Catch:{ all -> 0x0093 }
        if (r0 == 0) goto L_0x0074;
    L_0x0070:
        r6.b(r3);	 Catch:{ all -> 0x0093 }
        goto L_0x008e;
    L_0x0074:
        r0 = r3.e_();	 Catch:{ all -> 0x0093 }
        if (r0 == 0) goto L_0x0083;
    L_0x007a:
        r0 = r6.m;	 Catch:{ all -> 0x0093 }
        r0 = r0 + r5;
        r6.m = r0;	 Catch:{ all -> 0x0093 }
        r6.b(r3);	 Catch:{ all -> 0x0093 }
        goto L_0x008e;
    L_0x0083:
        r0 = r6.m;	 Catch:{ all -> 0x0093 }
        r3.b = r0;	 Catch:{ all -> 0x0093 }
        r6.m = r2;	 Catch:{ all -> 0x0093 }
        r0 = r6.d;	 Catch:{ all -> 0x0093 }
        r0.addLast(r3);	 Catch:{ all -> 0x0093 }
    L_0x008e:
        r6.b(r1);	 Catch:{ all -> 0x0093 }
        monitor-exit(r4);	 Catch:{ all -> 0x0093 }
        return r5;
    L_0x0093:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0093 }
        throw r0;
    L_0x0096:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0096 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gu.l():boolean");
    }

    private boolean m() {
        return !this.c.isEmpty() && this.h > 0;
    }

    private void b(I i) {
        i.a();
        gs[] gsVarArr = this.e;
        int i2 = this.g;
        this.g = i2 + 1;
        gsVarArr[i2] = i;
    }

    private void b(O o) {
        o.a();
        gt[] gtVarArr = this.f;
        int i = this.h;
        this.h = i + 1;
        gtVarArr[i] = o;
    }
}

package io.reactivex.internal.observers;

import defpackage.xc;
import io.reactivex.disposables.b;
import io.reactivex.internal.util.h;
import io.reactivex.internal.util.k;
import io.reactivex.r;

/* compiled from: QueueDrainObserver */
public abstract class j<T, U, V> extends l implements h<U, V>, r<T> {
    protected final r<? super V> a;
    protected final xc<U> b;
    protected volatile boolean c;
    protected volatile boolean d;
    protected Throwable e;

    public void a(r<? super V> rVar, U u) {
    }

    public j(r<? super V> rVar, xc<U> xcVar) {
        this.a = rVar;
        this.b = xcVar;
    }

    public final boolean a() {
        return this.c;
    }

    public final boolean b() {
        return this.d;
    }

    public final boolean c() {
        return this.f.getAndIncrement() == 0;
    }

    public final boolean d() {
        return this.f.get() == 0 && this.f.compareAndSet(0, 1);
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(U u, boolean z, b bVar) {
        r rVar = this.a;
        xc xcVar = this.b;
        if (this.f.get() == 0 && this.f.compareAndSet(0, 1)) {
            a(rVar, u);
            if (a(-1) == 0) {
                return;
            }
        }
        xcVar.offer(u);
        if (!c()) {
            return;
        }
        k.a(xcVar, rVar, z, bVar, this);
    }

    /* Access modifiers changed, original: protected|final */
    public final void b(U u, boolean z, b bVar) {
        r rVar = this.a;
        xc xcVar = this.b;
        if (this.f.get() != 0 || !this.f.compareAndSet(0, 1)) {
            xcVar.offer(u);
            if (!c()) {
                return;
            }
        } else if (xcVar.isEmpty()) {
            a(rVar, u);
            if (a(-1) == 0) {
                return;
            }
        } else {
            xcVar.offer(u);
        }
        k.a(xcVar, rVar, z, bVar, this);
    }

    public final Throwable e() {
        return this.e;
    }

    public final int a(int i) {
        return this.f.addAndGet(i);
    }
}

package io.reactivex.subjects;

import defpackage.xd;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.queue.a;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class UnicastSubject<T> extends c<T> {
    final a<T> a;
    final AtomicReference<r<? super T>> b;
    final AtomicReference<Runnable> c;
    final boolean d;
    volatile boolean e;
    volatile boolean f;
    Throwable g;
    final AtomicBoolean h;
    final BasicIntQueueDisposable<T> i;
    boolean j;

    final class UnicastQueueDisposable extends BasicIntQueueDisposable<T> {
        private static final long serialVersionUID = 7926949470189395511L;

        UnicastQueueDisposable() {
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            UnicastSubject.this.j = true;
            return 2;
        }

        public T poll() throws Exception {
            return UnicastSubject.this.a.poll();
        }

        public boolean isEmpty() {
            return UnicastSubject.this.a.isEmpty();
        }

        public void clear() {
            UnicastSubject.this.a.clear();
        }

        public void dispose() {
            if (!UnicastSubject.this.e) {
                UnicastSubject.this.e = true;
                UnicastSubject.this.c();
                UnicastSubject.this.b.lazySet(null);
                if (UnicastSubject.this.i.getAndIncrement() == 0) {
                    UnicastSubject.this.b.lazySet(null);
                    UnicastSubject.this.a.clear();
                }
            }
        }

        public boolean isDisposed() {
            return UnicastSubject.this.e;
        }
    }

    public static <T> UnicastSubject<T> a() {
        return new UnicastSubject(k.bufferSize(), true);
    }

    public static <T> UnicastSubject<T> a(int i) {
        return new UnicastSubject(i, true);
    }

    public static <T> UnicastSubject<T> a(int i, Runnable runnable) {
        return new UnicastSubject(i, runnable, true);
    }

    UnicastSubject(int i, boolean z) {
        this.a = new a(io.reactivex.internal.functions.a.a(i, "capacityHint"));
        this.c = new AtomicReference();
        this.d = z;
        this.b = new AtomicReference();
        this.h = new AtomicBoolean();
        this.i = new UnicastQueueDisposable();
    }

    UnicastSubject(int i, Runnable runnable, boolean z) {
        this.a = new a(io.reactivex.internal.functions.a.a(i, "capacityHint"));
        this.c = new AtomicReference(io.reactivex.internal.functions.a.a((Object) runnable, "onTerminate"));
        this.d = z;
        this.b = new AtomicReference();
        this.h = new AtomicBoolean();
        this.i = new UnicastQueueDisposable();
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        if (this.h.get() || !this.h.compareAndSet(false, true)) {
            EmptyDisposable.error(new IllegalStateException("Only a single observer allowed."), (r) rVar);
        } else {
            rVar.onSubscribe(this.i);
            this.b.lazySet(rVar);
            if (this.e) {
                this.b.lazySet(null);
                return;
            }
            d();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        Runnable runnable = (Runnable) this.c.get();
        if (runnable != null && this.c.compareAndSet(runnable, null)) {
            runnable.run();
        }
    }

    public void onSubscribe(b bVar) {
        if (this.f || this.e) {
            bVar.dispose();
        }
    }

    public void onNext(T t) {
        io.reactivex.internal.functions.a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.f && !this.e) {
            this.a.offer(t);
            d();
        }
    }

    public void onError(Throwable th) {
        io.reactivex.internal.functions.a.a((Object) th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f || this.e) {
            xk.a(th);
            return;
        }
        this.g = th;
        this.f = true;
        c();
        d();
    }

    public void onComplete() {
        if (!this.f && !this.e) {
            this.f = true;
            c();
            d();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(r<? super T> rVar) {
        xd xdVar = this.a;
        int i = this.d ^ 1;
        Object obj = 1;
        int i2 = 1;
        while (!this.e) {
            boolean z = this.f;
            Object poll = this.a.poll();
            Object obj2 = poll == null ? 1 : null;
            if (z) {
                if (!(i == 0 || obj == null)) {
                    if (!a(xdVar, (r) rVar)) {
                        obj = null;
                    } else {
                        return;
                    }
                }
                if (obj2 != null) {
                    c(rVar);
                    return;
                }
            }
            if (obj2 != null) {
                i2 = this.i.addAndGet(-i2);
                if (i2 == 0) {
                    return;
                }
            } else {
                rVar.onNext(poll);
            }
        }
        this.b.lazySet(null);
        xdVar.clear();
    }

    /* Access modifiers changed, original: 0000 */
    public void b(r<? super T> rVar) {
        xd xdVar = this.a;
        int i = 1;
        int i2 = this.d ^ 1;
        while (!this.e) {
            boolean z = this.f;
            if (i2 == 0 || !z || !a(xdVar, (r) rVar)) {
                rVar.onNext(null);
                if (z) {
                    c(rVar);
                    return;
                }
                i = this.i.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
            return;
        }
        this.b.lazySet(null);
        xdVar.clear();
    }

    /* Access modifiers changed, original: 0000 */
    public void c(r<? super T> rVar) {
        this.b.lazySet(null);
        Throwable th = this.g;
        if (th != null) {
            rVar.onError(th);
        } else {
            rVar.onComplete();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(xd<T> xdVar, r<? super T> rVar) {
        Throwable th = this.g;
        if (th == null) {
            return false;
        }
        this.b.lazySet(null);
        xdVar.clear();
        rVar.onError(th);
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        if (this.i.getAndIncrement() == 0) {
            r rVar = (r) this.b.get();
            int i = 1;
            while (rVar == null) {
                i = this.i.addAndGet(-i);
                if (i != 0) {
                    rVar = (r) this.b.get();
                } else {
                    return;
                }
            }
            if (this.j) {
                b(rVar);
            } else {
                a(rVar);
            }
        }
    }
}

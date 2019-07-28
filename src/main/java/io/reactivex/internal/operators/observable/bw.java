package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.j;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.k;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.subjects.UnicastSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: ObservableWindowBoundarySelector */
public final class bw<T, B, V> extends a<T, k<T>> {
    final p<B> b;
    final wm<? super B, ? extends p<V>> c;
    final int d;

    /* compiled from: ObservableWindowBoundarySelector */
    static final class d<T, B> {
        final UnicastSubject<T> a;
        final B b;

        d(UnicastSubject<T> unicastSubject, B b) {
            this.a = unicastSubject;
            this.b = b;
        }
    }

    /* compiled from: ObservableWindowBoundarySelector */
    static final class a<T, V> extends io.reactivex.observers.c<V> {
        final c<T, ?, V> a;
        final UnicastSubject<T> b;
        boolean c;

        a(c<T, ?, V> cVar, UnicastSubject<T> unicastSubject) {
            this.a = cVar;
            this.b = unicastSubject;
        }

        public void onNext(V v) {
            dispose();
            onComplete();
        }

        public void onError(Throwable th) {
            if (this.c) {
                xk.a(th);
                return;
            }
            this.c = true;
            this.a.a(th);
        }

        public void onComplete() {
            if (!this.c) {
                this.c = true;
                this.a.a(this);
            }
        }
    }

    /* compiled from: ObservableWindowBoundarySelector */
    static final class b<T, B> extends io.reactivex.observers.c<B> {
        final c<T, B, ?> a;

        b(c<T, B, ?> cVar) {
            this.a = cVar;
        }

        public void onNext(B b) {
            this.a.a((Object) b);
        }

        public void onError(Throwable th) {
            this.a.a(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    /* compiled from: ObservableWindowBoundarySelector */
    static final class c<T, B, V> extends j<T, Object, k<T>> implements io.reactivex.disposables.b {
        final p<B> g;
        final wm<? super B, ? extends p<V>> h;
        final int i;
        final io.reactivex.disposables.a j;
        io.reactivex.disposables.b k;
        final AtomicReference<io.reactivex.disposables.b> l = new AtomicReference();
        final List<UnicastSubject<T>> m;
        final AtomicLong n = new AtomicLong();

        public void a(r<? super k<T>> rVar, Object obj) {
        }

        c(r<? super k<T>> rVar, p<B> pVar, wm<? super B, ? extends p<V>> wmVar, int i) {
            super(rVar, new MpscLinkedQueue());
            this.g = pVar;
            this.h = wmVar;
            this.i = i;
            this.j = new io.reactivex.disposables.a();
            this.m = new ArrayList();
            this.n.lazySet(1);
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.k, bVar)) {
                this.k = bVar;
                this.a.onSubscribe(this);
                if (!this.c) {
                    b bVar2 = new b(this);
                    if (this.l.compareAndSet(null, bVar2)) {
                        this.n.getAndIncrement();
                        this.g.subscribe(bVar2);
                    }
                }
            }
        }

        public void onNext(T t) {
            if (d()) {
                for (UnicastSubject onNext : this.m) {
                    onNext.onNext(t);
                }
                if (a(-1) == 0) {
                    return;
                }
            }
            this.b.offer(NotificationLite.next(t));
            if (!c()) {
                return;
            }
            g();
        }

        public void onError(Throwable th) {
            if (this.d) {
                xk.a(th);
                return;
            }
            this.e = th;
            this.d = true;
            if (c()) {
                g();
            }
            if (this.n.decrementAndGet() == 0) {
                this.j.dispose();
            }
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.d) {
                this.d = true;
                if (c()) {
                    g();
                }
                if (this.n.decrementAndGet() == 0) {
                    this.j.dispose();
                }
                this.a.onComplete();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void a(Throwable th) {
            this.k.dispose();
            this.j.dispose();
            onError(th);
        }

        public void dispose() {
            this.c = true;
        }

        public boolean isDisposed() {
            return this.c;
        }

        /* Access modifiers changed, original: 0000 */
        public void f() {
            this.j.dispose();
            DisposableHelper.dispose(this.l);
        }

        /* Access modifiers changed, original: 0000 */
        public void g() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) this.b;
            r rVar = this.a;
            List<UnicastSubject> list = this.m;
            int i = 1;
            while (true) {
                boolean z = this.d;
                Object poll = mpscLinkedQueue.poll();
                Object obj = poll == null ? 1 : null;
                if (z && obj != null) {
                    f();
                    Throwable th = this.e;
                    if (th != null) {
                        for (UnicastSubject onError : list) {
                            onError.onError(th);
                        }
                    } else {
                        for (UnicastSubject onComplete : list) {
                            onComplete.onComplete();
                        }
                    }
                    list.clear();
                    return;
                } else if (obj != null) {
                    i = a(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (poll instanceof d) {
                    d dVar = (d) poll;
                    if (dVar.a != null) {
                        if (list.remove(dVar.a)) {
                            dVar.a.onComplete();
                            if (this.n.decrementAndGet() == 0) {
                                f();
                                return;
                            }
                        } else {
                            continue;
                        }
                    } else if (!this.c) {
                        UnicastSubject a = UnicastSubject.a(this.i);
                        list.add(a);
                        rVar.onNext(a);
                        try {
                            p pVar = (p) io.reactivex.internal.functions.a.a(this.h.apply(dVar.b), "The ObservableSource supplied is null");
                            io.reactivex.disposables.b aVar = new a(this, a);
                            if (this.j.a(aVar)) {
                                this.n.getAndIncrement();
                                pVar.subscribe(aVar);
                            }
                        } catch (Throwable th2) {
                            io.reactivex.exceptions.a.b(th2);
                            this.c = true;
                            rVar.onError(th2);
                        }
                    }
                } else {
                    for (UnicastSubject onNext : list) {
                        onNext.onNext(NotificationLite.getValue(poll));
                    }
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void a(B b) {
            this.b.offer(new d(null, b));
            if (c()) {
                g();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void a(a<T, V> aVar) {
            this.j.c(aVar);
            this.b.offer(new d(aVar.b, null));
            if (c()) {
                g();
            }
        }
    }

    public bw(p<T> pVar, p<B> pVar2, wm<? super B, ? extends p<V>> wmVar, int i) {
        super(pVar);
        this.b = pVar2;
        this.c = wmVar;
        this.d = i;
    }

    public void subscribeActual(r<? super k<T>> rVar) {
        this.a.subscribe(new c(new e(rVar), this.b, this.c, this.d));
    }
}

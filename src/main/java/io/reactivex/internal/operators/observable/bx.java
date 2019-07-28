package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.j;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.k;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import io.reactivex.subjects.UnicastSubject;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: ObservableWindowTimed */
public final class bx<T> extends a<T, k<T>> {
    final long b;
    final long c;
    final TimeUnit d;
    final s e;
    final long f;
    final int g;
    final boolean h;

    /* compiled from: ObservableWindowTimed */
    static final class a<T> extends j<T, Object, k<T>> implements io.reactivex.disposables.b {
        final long g;
        final TimeUnit h;
        final s i;
        final int j;
        final boolean k;
        final long l;
        final io.reactivex.s.c m;
        long n;
        long o;
        io.reactivex.disposables.b p;
        UnicastSubject<T> q;
        volatile boolean r;
        final AtomicReference<io.reactivex.disposables.b> s = new AtomicReference();

        /* compiled from: ObservableWindowTimed */
        static final class a implements Runnable {
            final long a;
            final a<?> b;

            a(long j, a<?> aVar) {
                this.a = j;
                this.b = aVar;
            }

            public void run() {
                a aVar = this.b;
                if (aVar.c) {
                    aVar.r = true;
                    aVar.f();
                } else {
                    aVar.b.offer(this);
                }
                if (aVar.c()) {
                    aVar.g();
                }
            }
        }

        a(r<? super k<T>> rVar, long j, TimeUnit timeUnit, s sVar, int i, long j2, boolean z) {
            super(rVar, new MpscLinkedQueue());
            this.g = j;
            this.h = timeUnit;
            this.i = sVar;
            this.j = i;
            this.l = j2;
            this.k = z;
            if (z) {
                this.m = sVar.a();
            } else {
                this.m = null;
            }
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.p, bVar)) {
                this.p = bVar;
                r rVar = this.a;
                rVar.onSubscribe(this);
                if (!this.c) {
                    UnicastSubject a = UnicastSubject.a(this.j);
                    this.q = a;
                    rVar.onNext(a);
                    a aVar = new a(this.o, this);
                    if (this.k) {
                        bVar = this.m.a(aVar, this.g, this.g, this.h);
                    } else {
                        bVar = this.i.a(aVar, this.g, this.g, this.h);
                    }
                    DisposableHelper.replace(this.s, bVar);
                }
            }
        }

        public void onNext(T t) {
            if (!this.r) {
                if (d()) {
                    UnicastSubject unicastSubject = this.q;
                    unicastSubject.onNext(t);
                    long j = this.n + 1;
                    if (j >= this.l) {
                        this.o++;
                        this.n = 0;
                        unicastSubject.onComplete();
                        UnicastSubject a = UnicastSubject.a(this.j);
                        this.q = a;
                        this.a.onNext(a);
                        if (this.k) {
                            ((io.reactivex.disposables.b) this.s.get()).dispose();
                            DisposableHelper.replace(this.s, this.m.a(new a(this.o, this), this.g, this.g, this.h));
                        }
                    } else {
                        this.n = j;
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
        }

        public void onError(Throwable th) {
            this.e = th;
            this.d = true;
            if (c()) {
                g();
            }
            this.a.onError(th);
            f();
        }

        public void onComplete() {
            this.d = true;
            if (c()) {
                g();
            }
            this.a.onComplete();
            f();
        }

        public void dispose() {
            this.c = true;
        }

        public boolean isDisposed() {
            return this.c;
        }

        /* Access modifiers changed, original: 0000 */
        public void f() {
            DisposableHelper.dispose(this.s);
            io.reactivex.s.c cVar = this.m;
            if (cVar != null) {
                cVar.dispose();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void g() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) this.b;
            r rVar = this.a;
            UnicastSubject unicastSubject = this.q;
            int i = 1;
            while (!this.r) {
                boolean z = this.d;
                Object poll = mpscLinkedQueue.poll();
                Object obj = poll == null ? 1 : null;
                boolean z2 = poll instanceof a;
                if (z && (obj != null || z2)) {
                    this.q = null;
                    mpscLinkedQueue.clear();
                    f();
                    Throwable th = this.e;
                    if (th != null) {
                        unicastSubject.onError(th);
                    } else {
                        unicastSubject.onComplete();
                    }
                    return;
                } else if (obj != null) {
                    i = a(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (z2) {
                    a aVar = (a) poll;
                    if (this.k || this.o == aVar.a) {
                        unicastSubject.onComplete();
                        this.n = 0;
                        unicastSubject = UnicastSubject.a(this.j);
                        this.q = unicastSubject;
                        rVar.onNext(unicastSubject);
                    }
                } else {
                    unicastSubject.onNext(NotificationLite.getValue(poll));
                    long j = this.n + 1;
                    if (j >= this.l) {
                        this.o++;
                        this.n = 0;
                        unicastSubject.onComplete();
                        unicastSubject = UnicastSubject.a(this.j);
                        this.q = unicastSubject;
                        this.a.onNext(unicastSubject);
                        if (this.k) {
                            io.reactivex.disposables.b bVar = (io.reactivex.disposables.b) this.s.get();
                            bVar.dispose();
                            io.reactivex.disposables.b a = this.m.a(new a(this.o, this), this.g, this.g, this.h);
                            if (!this.s.compareAndSet(bVar, a)) {
                                a.dispose();
                            }
                        }
                    } else {
                        this.n = j;
                    }
                }
            }
            this.p.dispose();
            mpscLinkedQueue.clear();
            f();
        }
    }

    /* compiled from: ObservableWindowTimed */
    static final class b<T> extends j<T, Object, k<T>> implements io.reactivex.disposables.b, r<T>, Runnable {
        static final Object n = new Object();
        final long g;
        final TimeUnit h;
        final s i;
        final int j;
        io.reactivex.disposables.b k;
        UnicastSubject<T> l;
        final AtomicReference<io.reactivex.disposables.b> m = new AtomicReference();
        volatile boolean o;

        b(r<? super k<T>> rVar, long j, TimeUnit timeUnit, s sVar, int i) {
            super(rVar, new MpscLinkedQueue());
            this.g = j;
            this.h = timeUnit;
            this.i = sVar;
            this.j = i;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.k, bVar)) {
                this.k = bVar;
                this.l = UnicastSubject.a(this.j);
                r rVar = this.a;
                rVar.onSubscribe(this);
                rVar.onNext(this.l);
                if (!this.c) {
                    DisposableHelper.replace(this.m, this.i.a(this, this.g, this.g, this.h));
                }
            }
        }

        public void onNext(T t) {
            if (!this.o) {
                if (d()) {
                    this.l.onNext(t);
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
        }

        public void onError(Throwable th) {
            this.e = th;
            this.d = true;
            if (c()) {
                g();
            }
            f();
            this.a.onError(th);
        }

        public void onComplete() {
            this.d = true;
            if (c()) {
                g();
            }
            f();
            this.a.onComplete();
        }

        public void dispose() {
            this.c = true;
        }

        public boolean isDisposed() {
            return this.c;
        }

        /* Access modifiers changed, original: 0000 */
        public void f() {
            DisposableHelper.dispose(this.m);
        }

        public void run() {
            if (this.c) {
                this.o = true;
                f();
            }
            this.b.offer(n);
            if (c()) {
                g();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void g() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) this.b;
            r rVar = this.a;
            UnicastSubject unicastSubject = this.l;
            int i = 1;
            while (true) {
                boolean z = this.o;
                boolean z2 = this.d;
                Object poll = mpscLinkedQueue.poll();
                if (!(z2 && (poll == null || poll == n))) {
                    if (poll == null) {
                        i = a(-i);
                        if (i == 0) {
                            return;
                        }
                    } else if (poll == n) {
                        unicastSubject.onComplete();
                        if (z) {
                            this.k.dispose();
                        } else {
                            unicastSubject = UnicastSubject.a(this.j);
                            this.l = unicastSubject;
                            rVar.onNext(unicastSubject);
                        }
                    } else {
                        unicastSubject.onNext(NotificationLite.getValue(poll));
                    }
                }
            }
            this.l = null;
            mpscLinkedQueue.clear();
            f();
            Throwable th = this.e;
            if (th != null) {
                unicastSubject.onError(th);
            } else {
                unicastSubject.onComplete();
            }
        }
    }

    /* compiled from: ObservableWindowTimed */
    static final class c<T> extends j<T, Object, k<T>> implements io.reactivex.disposables.b, Runnable {
        final long g;
        final long h;
        final TimeUnit i;
        final io.reactivex.s.c j;
        final int k;
        final List<UnicastSubject<T>> l = new LinkedList();
        io.reactivex.disposables.b m;
        volatile boolean n;

        /* compiled from: ObservableWindowTimed */
        final class a implements Runnable {
            private final UnicastSubject<T> b;

            a(UnicastSubject<T> unicastSubject) {
                this.b = unicastSubject;
            }

            public void run() {
                c.this.a(this.b);
            }
        }

        /* compiled from: ObservableWindowTimed */
        static final class b<T> {
            final UnicastSubject<T> a;
            final boolean b;

            b(UnicastSubject<T> unicastSubject, boolean z) {
                this.a = unicastSubject;
                this.b = z;
            }
        }

        c(r<? super k<T>> rVar, long j, long j2, TimeUnit timeUnit, io.reactivex.s.c cVar, int i) {
            super(rVar, new MpscLinkedQueue());
            this.g = j;
            this.h = j2;
            this.i = timeUnit;
            this.j = cVar;
            this.k = i;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.m, bVar)) {
                this.m = bVar;
                this.a.onSubscribe(this);
                if (!this.c) {
                    UnicastSubject a = UnicastSubject.a(this.k);
                    this.l.add(a);
                    this.a.onNext(a);
                    this.j.a(new a(a), this.g, this.i);
                    this.j.a(this, this.h, this.h, this.i);
                }
            }
        }

        public void onNext(T t) {
            if (d()) {
                for (UnicastSubject onNext : this.l) {
                    onNext.onNext(t);
                }
                if (a(-1) == 0) {
                    return;
                }
            }
            this.b.offer(t);
            if (!c()) {
                return;
            }
            g();
        }

        public void onError(Throwable th) {
            this.e = th;
            this.d = true;
            if (c()) {
                g();
            }
            this.a.onError(th);
            f();
        }

        public void onComplete() {
            this.d = true;
            if (c()) {
                g();
            }
            this.a.onComplete();
            f();
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
        }

        /* Access modifiers changed, original: 0000 */
        public void a(UnicastSubject<T> unicastSubject) {
            this.b.offer(new b(unicastSubject, false));
            if (c()) {
                g();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void g() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) this.b;
            r rVar = this.a;
            List<UnicastSubject> list = this.l;
            int i = 1;
            while (!this.n) {
                boolean z = this.d;
                Object poll = mpscLinkedQueue.poll();
                Object obj = poll == null ? 1 : null;
                boolean z2 = poll instanceof b;
                if (z && (obj != null || z2)) {
                    mpscLinkedQueue.clear();
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
                    f();
                    list.clear();
                    return;
                } else if (obj != null) {
                    i = a(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (z2) {
                    b bVar = (b) poll;
                    if (!bVar.b) {
                        list.remove(bVar.a);
                        bVar.a.onComplete();
                        if (list.isEmpty() && this.c) {
                            this.n = true;
                        }
                    } else if (!this.c) {
                        UnicastSubject a = UnicastSubject.a(this.k);
                        list.add(a);
                        rVar.onNext(a);
                        this.j.a(new a(a), this.g, this.i);
                    }
                } else {
                    for (UnicastSubject onNext : list) {
                        onNext.onNext(poll);
                    }
                }
            }
            this.m.dispose();
            f();
            mpscLinkedQueue.clear();
            list.clear();
        }

        public void run() {
            b bVar = new b(UnicastSubject.a(this.k), true);
            if (!this.c) {
                this.b.offer(bVar);
            }
            if (c()) {
                g();
            }
        }
    }

    public bx(p<T> pVar, long j, long j2, TimeUnit timeUnit, s sVar, long j3, int i, boolean z) {
        super(pVar);
        this.b = j;
        this.c = j2;
        this.d = timeUnit;
        this.e = sVar;
        this.f = j3;
        this.g = i;
        this.h = z;
    }

    public void subscribeActual(r<? super k<T>> rVar) {
        e eVar = new e(rVar);
        if (this.b != this.c) {
            this.a.subscribe(new c(eVar, this.b, this.c, this.d, this.e.a(), this.g));
        } else if (this.f == Long.MAX_VALUE) {
            this.a.subscribe(new b(eVar, this.b, this.d, this.e, this.g));
        } else {
            this.a.subscribe(new a(eVar, this.b, this.d, this.e, this.g, this.f, this.h));
        }
    }
}

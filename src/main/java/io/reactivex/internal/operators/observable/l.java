package io.reactivex.internal.operators.observable;

import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.observers.j;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.k;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: ObservableBufferTimed */
public final class l<T, U extends Collection<? super T>> extends a<T, U> {
    final long b;
    final long c;
    final TimeUnit d;
    final s e;
    final Callable<U> f;
    final int g;
    final boolean h;

    /* compiled from: ObservableBufferTimed */
    static final class a<T, U extends Collection<? super T>> extends j<T, U, U> implements io.reactivex.disposables.b, Runnable {
        final Callable<U> g;
        final long h;
        final TimeUnit i;
        final int j;
        final boolean k;
        final io.reactivex.s.c l;
        U m;
        io.reactivex.disposables.b n;
        io.reactivex.disposables.b o;
        long p;
        long q;

        a(r<? super U> rVar, Callable<U> callable, long j, TimeUnit timeUnit, int i, boolean z, io.reactivex.s.c cVar) {
            super(rVar, new MpscLinkedQueue());
            this.g = callable;
            this.h = j;
            this.i = timeUnit;
            this.j = i;
            this.k = z;
            this.l = cVar;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.o, bVar)) {
                this.o = bVar;
                try {
                    this.m = (Collection) io.reactivex.internal.functions.a.a(this.g.call(), "The buffer supplied is null");
                    this.a.onSubscribe(this);
                    this.n = this.l.a(this, this.h, this.h, this.i);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    bVar.dispose();
                    EmptyDisposable.error(th, this.a);
                    this.l.dispose();
                }
            }
        }

        /* JADX WARNING: Missing block: B:13:0x0021, code skipped:
            if (r7.k == false) goto L_0x0028;
     */
        /* JADX WARNING: Missing block: B:14:0x0023, code skipped:
            r7.n.dispose();
     */
        /* JADX WARNING: Missing block: B:15:0x0028, code skipped:
            b(r0, false, r7);
     */
        /* JADX WARNING: Missing block: B:17:?, code skipped:
            r8 = (java.util.Collection) io.reactivex.internal.functions.a.a(r7.g.call(), "The buffer supplied is null");
     */
        /* JADX WARNING: Missing block: B:18:0x003a, code skipped:
            monitor-enter(r7);
     */
        /* JADX WARNING: Missing block: B:20:?, code skipped:
            r7.m = r8;
            r7.q++;
     */
        /* JADX WARNING: Missing block: B:21:0x0043, code skipped:
            monitor-exit(r7);
     */
        /* JADX WARNING: Missing block: B:23:0x0046, code skipped:
            if (r7.k == false) goto L_0x0057;
     */
        /* JADX WARNING: Missing block: B:24:0x0048, code skipped:
            r7.n = r7.l.a(r7, r7.h, r7.h, r7.i);
     */
        /* JADX WARNING: Missing block: B:25:0x0057, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:30:0x005b, code skipped:
            r8 = move-exception;
     */
        /* JADX WARNING: Missing block: B:31:0x005c, code skipped:
            io.reactivex.exceptions.a.b(r8);
            r7.a.onError(r8);
            dispose();
     */
        /* JADX WARNING: Missing block: B:32:0x0067, code skipped:
            return;
     */
        public void onNext(T r8) {
            /*
            r7 = this;
            monitor-enter(r7);
            r0 = r7.m;	 Catch:{ all -> 0x0068 }
            if (r0 != 0) goto L_0x0007;
        L_0x0005:
            monitor-exit(r7);	 Catch:{ all -> 0x0068 }
            return;
        L_0x0007:
            r0.add(r8);	 Catch:{ all -> 0x0068 }
            r8 = r0.size();	 Catch:{ all -> 0x0068 }
            r1 = r7.j;	 Catch:{ all -> 0x0068 }
            if (r8 >= r1) goto L_0x0014;
        L_0x0012:
            monitor-exit(r7);	 Catch:{ all -> 0x0068 }
            return;
        L_0x0014:
            r8 = 0;
            r7.m = r8;	 Catch:{ all -> 0x0068 }
            r1 = r7.p;	 Catch:{ all -> 0x0068 }
            r3 = 1;
            r1 = r1 + r3;
            r7.p = r1;	 Catch:{ all -> 0x0068 }
            monitor-exit(r7);	 Catch:{ all -> 0x0068 }
            r8 = r7.k;
            if (r8 == 0) goto L_0x0028;
        L_0x0023:
            r8 = r7.n;
            r8.dispose();
        L_0x0028:
            r8 = 0;
            r7.b(r0, r8, r7);
            r8 = r7.g;	 Catch:{ Throwable -> 0x005b }
            r8 = r8.call();	 Catch:{ Throwable -> 0x005b }
            r0 = "The buffer supplied is null";
            r8 = io.reactivex.internal.functions.a.a(r8, r0);	 Catch:{ Throwable -> 0x005b }
            r8 = (java.util.Collection) r8;	 Catch:{ Throwable -> 0x005b }
            monitor-enter(r7);
            r7.m = r8;	 Catch:{ all -> 0x0058 }
            r0 = r7.q;	 Catch:{ all -> 0x0058 }
            r8 = 0;
            r0 = r0 + r3;
            r7.q = r0;	 Catch:{ all -> 0x0058 }
            monitor-exit(r7);	 Catch:{ all -> 0x0058 }
            r8 = r7.k;
            if (r8 == 0) goto L_0x0057;
        L_0x0048:
            r0 = r7.l;
            r2 = r7.h;
            r4 = r7.h;
            r6 = r7.i;
            r1 = r7;
            r8 = r0.a(r1, r2, r4, r6);
            r7.n = r8;
        L_0x0057:
            return;
        L_0x0058:
            r8 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x0058 }
            throw r8;
        L_0x005b:
            r8 = move-exception;
            io.reactivex.exceptions.a.b(r8);
            r0 = r7.a;
            r0.onError(r8);
            r7.dispose();
            return;
        L_0x0068:
            r8 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x0068 }
            throw r8;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.l$a.onNext(java.lang.Object):void");
        }

        public void onError(Throwable th) {
            synchronized (this) {
                this.m = null;
            }
            this.a.onError(th);
            this.l.dispose();
        }

        public void onComplete() {
            Collection collection;
            this.l.dispose();
            synchronized (this) {
                collection = this.m;
                this.m = null;
            }
            this.b.offer(collection);
            this.d = true;
            if (c()) {
                k.a(this.b, this.a, false, this, this);
            }
        }

        public void a(r<? super U> rVar, U u) {
            rVar.onNext(u);
        }

        public void dispose() {
            if (!this.c) {
                this.c = true;
                this.o.dispose();
                this.l.dispose();
                synchronized (this) {
                    this.m = null;
                }
            }
        }

        public boolean isDisposed() {
            return this.c;
        }

        public void run() {
            try {
                Collection collection = (Collection) io.reactivex.internal.functions.a.a(this.g.call(), "The bufferSupplier returned a null buffer");
                synchronized (this) {
                    Collection collection2 = this.m;
                    if (collection2 != null) {
                        if (this.p == this.q) {
                            this.m = collection;
                            b(collection2, false, this);
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                dispose();
                this.a.onError(th);
            }
        }
    }

    /* compiled from: ObservableBufferTimed */
    static final class b<T, U extends Collection<? super T>> extends j<T, U, U> implements io.reactivex.disposables.b, Runnable {
        final Callable<U> g;
        final long h;
        final TimeUnit i;
        final s j;
        io.reactivex.disposables.b k;
        U l;
        final AtomicReference<io.reactivex.disposables.b> m = new AtomicReference();

        b(r<? super U> rVar, Callable<U> callable, long j, TimeUnit timeUnit, s sVar) {
            super(rVar, new MpscLinkedQueue());
            this.g = callable;
            this.h = j;
            this.i = timeUnit;
            this.j = sVar;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.k, bVar)) {
                this.k = bVar;
                try {
                    this.l = (Collection) io.reactivex.internal.functions.a.a(this.g.call(), "The buffer supplied is null");
                    this.a.onSubscribe(this);
                    if (!this.c) {
                        bVar = this.j.a(this, this.h, this.h, this.i);
                        if (!this.m.compareAndSet(null, bVar)) {
                            bVar.dispose();
                        }
                    }
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    dispose();
                    EmptyDisposable.error(th, this.a);
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                Collection collection = this.l;
                if (collection == null) {
                    return;
                }
                collection.add(t);
            }
        }

        public void onError(Throwable th) {
            synchronized (this) {
                this.l = null;
            }
            this.a.onError(th);
            DisposableHelper.dispose(this.m);
        }

        public void onComplete() {
            Collection collection;
            synchronized (this) {
                collection = this.l;
                this.l = null;
            }
            if (collection != null) {
                this.b.offer(collection);
                this.d = true;
                if (c()) {
                    k.a(this.b, this.a, false, null, this);
                }
            }
            DisposableHelper.dispose(this.m);
        }

        public void dispose() {
            DisposableHelper.dispose(this.m);
            this.k.dispose();
        }

        public boolean isDisposed() {
            return this.m.get() == DisposableHelper.DISPOSED;
        }

        public void run() {
            try {
                Collection collection;
                Collection collection2 = (Collection) io.reactivex.internal.functions.a.a(this.g.call(), "The bufferSupplier returned a null buffer");
                synchronized (this) {
                    collection = this.l;
                    if (collection != null) {
                        this.l = collection2;
                    }
                }
                if (collection == null) {
                    DisposableHelper.dispose(this.m);
                } else {
                    a(collection, false, this);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.a.onError(th);
                dispose();
            }
        }

        public void a(r<? super U> rVar, U u) {
            this.a.onNext(u);
        }
    }

    /* compiled from: ObservableBufferTimed */
    static final class c<T, U extends Collection<? super T>> extends j<T, U, U> implements io.reactivex.disposables.b, Runnable {
        final Callable<U> g;
        final long h;
        final long i;
        final TimeUnit j;
        final io.reactivex.s.c k;
        final List<U> l = new LinkedList();
        io.reactivex.disposables.b m;

        /* compiled from: ObservableBufferTimed */
        final class a implements Runnable {
            private final U b;

            a(U u) {
                this.b = u;
            }

            public void run() {
                synchronized (c.this) {
                    c.this.l.remove(this.b);
                }
                c.this.b(this.b, false, c.this.k);
            }
        }

        /* compiled from: ObservableBufferTimed */
        final class b implements Runnable {
            private final U b;

            b(U u) {
                this.b = u;
            }

            public void run() {
                synchronized (c.this) {
                    c.this.l.remove(this.b);
                }
                c.this.b(this.b, false, c.this.k);
            }
        }

        c(r<? super U> rVar, Callable<U> callable, long j, long j2, TimeUnit timeUnit, io.reactivex.s.c cVar) {
            super(rVar, new MpscLinkedQueue());
            this.g = callable;
            this.h = j;
            this.i = j2;
            this.j = timeUnit;
            this.k = cVar;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.m, bVar)) {
                this.m = bVar;
                try {
                    Collection collection = (Collection) io.reactivex.internal.functions.a.a(this.g.call(), "The buffer supplied is null");
                    this.l.add(collection);
                    this.a.onSubscribe(this);
                    this.k.a(this, this.i, this.i, this.j);
                    this.k.a(new b(collection), this.h, this.j);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    bVar.dispose();
                    EmptyDisposable.error(th, this.a);
                    this.k.dispose();
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                for (Collection add : this.l) {
                    add.add(t);
                }
            }
        }

        public void onError(Throwable th) {
            this.d = true;
            f();
            this.a.onError(th);
            this.k.dispose();
        }

        public void onComplete() {
            synchronized (this) {
                ArrayList<Collection> arrayList = new ArrayList(this.l);
                this.l.clear();
            }
            for (Collection offer : arrayList) {
                this.b.offer(offer);
            }
            this.d = true;
            if (c()) {
                k.a(this.b, this.a, false, this.k, this);
            }
        }

        public void dispose() {
            if (!this.c) {
                this.c = true;
                f();
                this.m.dispose();
                this.k.dispose();
            }
        }

        public boolean isDisposed() {
            return this.c;
        }

        /* Access modifiers changed, original: 0000 */
        public void f() {
            synchronized (this) {
                this.l.clear();
            }
        }

        public void run() {
            if (!this.c) {
                try {
                    Collection collection = (Collection) io.reactivex.internal.functions.a.a(this.g.call(), "The bufferSupplier returned a null buffer");
                    synchronized (this) {
                        if (this.c) {
                            return;
                        }
                        this.l.add(collection);
                        this.k.a(new a(collection), this.h, this.j);
                    }
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.a.onError(th);
                    dispose();
                }
            }
        }

        public void a(r<? super U> rVar, U u) {
            rVar.onNext(u);
        }
    }

    public l(p<T> pVar, long j, long j2, TimeUnit timeUnit, s sVar, Callable<U> callable, int i, boolean z) {
        super(pVar);
        this.b = j;
        this.c = j2;
        this.d = timeUnit;
        this.e = sVar;
        this.f = callable;
        this.g = i;
        this.h = z;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super U> rVar) {
        if (this.b == this.c && this.g == Filter.MAX) {
            this.a.subscribe(new b(new e(rVar), this.f, this.b, this.d, this.e));
            return;
        }
        io.reactivex.s.c a = this.e.a();
        if (this.b == this.c) {
            this.a.subscribe(new a(new e(rVar), this.f, this.b, this.d, this.g, this.h, a));
        } else {
            this.a.subscribe(new c(new e(rVar), this.f, this.b, this.c, this.d, a));
        }
    }
}

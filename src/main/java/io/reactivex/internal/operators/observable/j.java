package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.observers.c;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: ObservableBufferBoundarySupplier */
public final class j<T, U extends Collection<? super T>, B> extends a<T, U> {
    final Callable<? extends p<B>> b;
    final Callable<U> c;

    /* compiled from: ObservableBufferBoundarySupplier */
    static final class a<T, U extends Collection<? super T>, B> extends c<B> {
        final b<T, U, B> a;
        boolean b;

        a(b<T, U, B> bVar) {
            this.a = bVar;
        }

        public void onNext(B b) {
            if (!this.b) {
                this.b = true;
                dispose();
                this.a.g();
            }
        }

        public void onError(Throwable th) {
            if (this.b) {
                xk.a(th);
                return;
            }
            this.b = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.b) {
                this.b = true;
                this.a.g();
            }
        }
    }

    /* compiled from: ObservableBufferBoundarySupplier */
    static final class b<T, U extends Collection<? super T>, B> extends io.reactivex.internal.observers.j<T, U, U> implements io.reactivex.disposables.b, r<T> {
        final Callable<U> g;
        final Callable<? extends p<B>> h;
        io.reactivex.disposables.b i;
        final AtomicReference<io.reactivex.disposables.b> j = new AtomicReference();
        U k;

        b(r<? super U> rVar, Callable<U> callable, Callable<? extends p<B>> callable2) {
            super(rVar, new MpscLinkedQueue());
            this.g = callable;
            this.h = callable2;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.i, bVar)) {
                this.i = bVar;
                r rVar = this.a;
                try {
                    this.k = (Collection) io.reactivex.internal.functions.a.a(this.g.call(), "The buffer supplied is null");
                    try {
                        p pVar = (p) io.reactivex.internal.functions.a.a(this.h.call(), "The boundary ObservableSource supplied is null");
                        a aVar = new a(this);
                        this.j.set(aVar);
                        rVar.onSubscribe(this);
                        if (!this.c) {
                            pVar.subscribe(aVar);
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.c = true;
                        bVar.dispose();
                        EmptyDisposable.error(th, rVar);
                    }
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    this.c = true;
                    bVar.dispose();
                    EmptyDisposable.error(th2, rVar);
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                Collection collection = this.k;
                if (collection == null) {
                    return;
                }
                collection.add(t);
            }
        }

        public void onError(Throwable th) {
            dispose();
            this.a.onError(th);
        }

        /* JADX WARNING: Missing block: B:8:0x000b, code skipped:
            r3.b.offer(r0);
            r3.d = true;
     */
        /* JADX WARNING: Missing block: B:9:0x0017, code skipped:
            if (c() == false) goto L_0x0021;
     */
        /* JADX WARNING: Missing block: B:10:0x0019, code skipped:
            io.reactivex.internal.util.k.a(r3.b, r3.a, false, r3, r3);
     */
        /* JADX WARNING: Missing block: B:11:0x0021, code skipped:
            return;
     */
        public void onComplete() {
            /*
            r3 = this;
            monitor-enter(r3);
            r0 = r3.k;	 Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x0007;
        L_0x0005:
            monitor-exit(r3);	 Catch:{ all -> 0x0022 }
            return;
        L_0x0007:
            r1 = 0;
            r3.k = r1;	 Catch:{ all -> 0x0022 }
            monitor-exit(r3);	 Catch:{ all -> 0x0022 }
            r1 = r3.b;
            r1.offer(r0);
            r0 = 1;
            r3.d = r0;
            r0 = r3.c();
            if (r0 == 0) goto L_0x0021;
        L_0x0019:
            r0 = r3.b;
            r1 = r3.a;
            r2 = 0;
            io.reactivex.internal.util.k.a(r0, r1, r2, r3, r3);
        L_0x0021:
            return;
        L_0x0022:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0022 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.j$b.onComplete():void");
        }

        public void dispose() {
            if (!this.c) {
                this.c = true;
                this.i.dispose();
                f();
                if (c()) {
                    this.b.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.c;
        }

        /* Access modifiers changed, original: 0000 */
        public void f() {
            DisposableHelper.dispose(this.j);
        }

        /* Access modifiers changed, original: 0000 */
        /* JADX WARNING: Missing block: B:14:0x0033, code skipped:
            r1.subscribe(r2);
            a(r3, false, r4);
     */
        public void g() {
            /*
            r4 = this;
            r0 = r4.g;	 Catch:{ Throwable -> 0x0051 }
            r0 = r0.call();	 Catch:{ Throwable -> 0x0051 }
            r1 = "The buffer supplied is null";
            r0 = io.reactivex.internal.functions.a.a(r0, r1);	 Catch:{ Throwable -> 0x0051 }
            r0 = (java.util.Collection) r0;	 Catch:{ Throwable -> 0x0051 }
            r1 = r4.h;	 Catch:{ Throwable -> 0x003f }
            r1 = r1.call();	 Catch:{ Throwable -> 0x003f }
            r2 = "The boundary ObservableSource supplied is null";
            r1 = io.reactivex.internal.functions.a.a(r1, r2);	 Catch:{ Throwable -> 0x003f }
            r1 = (io.reactivex.p) r1;	 Catch:{ Throwable -> 0x003f }
            r2 = new io.reactivex.internal.operators.observable.j$a;
            r2.<init>(r4);
            r3 = r4.j;
            r3 = io.reactivex.internal.disposables.DisposableHelper.replace(r3, r2);
            if (r3 == 0) goto L_0x003e;
        L_0x0029:
            monitor-enter(r4);
            r3 = r4.k;	 Catch:{ all -> 0x003b }
            if (r3 != 0) goto L_0x0030;
        L_0x002e:
            monitor-exit(r4);	 Catch:{ all -> 0x003b }
            return;
        L_0x0030:
            r4.k = r0;	 Catch:{ all -> 0x003b }
            monitor-exit(r4);	 Catch:{ all -> 0x003b }
            r1.subscribe(r2);
            r0 = 0;
            r4.a(r3, r0, r4);
            goto L_0x003e;
        L_0x003b:
            r0 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x003b }
            throw r0;
        L_0x003e:
            return;
        L_0x003f:
            r0 = move-exception;
            io.reactivex.exceptions.a.b(r0);
            r1 = 1;
            r4.c = r1;
            r1 = r4.i;
            r1.dispose();
            r1 = r4.a;
            r1.onError(r0);
            return;
        L_0x0051:
            r0 = move-exception;
            io.reactivex.exceptions.a.b(r0);
            r4.dispose();
            r1 = r4.a;
            r1.onError(r0);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.j$b.g():void");
        }

        public void a(r<? super U> rVar, U u) {
            this.a.onNext(u);
        }
    }

    public j(p<T> pVar, Callable<? extends p<B>> callable, Callable<U> callable2) {
        super(pVar);
        this.b = callable;
        this.c = callable2;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super U> rVar) {
        this.a.subscribe(new b(new e(rVar), this.c, this.b));
    }
}

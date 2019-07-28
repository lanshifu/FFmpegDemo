package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.observers.j;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.observers.c;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import java.util.Collection;
import java.util.concurrent.Callable;

/* compiled from: ObservableBufferExactBoundary */
public final class k<T, U extends Collection<? super T>, B> extends a<T, U> {
    final p<B> b;
    final Callable<U> c;

    /* compiled from: ObservableBufferExactBoundary */
    static final class a<T, U extends Collection<? super T>, B> extends c<B> {
        final b<T, U, B> a;

        a(b<T, U, B> bVar) {
            this.a = bVar;
        }

        public void onNext(B b) {
            this.a.f();
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    /* compiled from: ObservableBufferExactBoundary */
    static final class b<T, U extends Collection<? super T>, B> extends j<T, U, U> implements io.reactivex.disposables.b, r<T> {
        final Callable<U> g;
        final p<B> h;
        io.reactivex.disposables.b i;
        io.reactivex.disposables.b j;
        U k;

        b(r<? super U> rVar, Callable<U> callable, p<B> pVar) {
            super(rVar, new MpscLinkedQueue());
            this.g = callable;
            this.h = pVar;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.i, bVar)) {
                this.i = bVar;
                try {
                    this.k = (Collection) io.reactivex.internal.functions.a.a(this.g.call(), "The buffer supplied is null");
                    a aVar = new a(this);
                    this.j = aVar;
                    this.a.onSubscribe(this);
                    if (!this.c) {
                        this.h.subscribe(aVar);
                    }
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.c = true;
                    bVar.dispose();
                    EmptyDisposable.error(th, this.a);
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
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.k$b.onComplete():void");
        }

        public void dispose() {
            if (!this.c) {
                this.c = true;
                this.j.dispose();
                this.i.dispose();
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
            try {
                Collection collection = (Collection) io.reactivex.internal.functions.a.a(this.g.call(), "The buffer supplied is null");
                synchronized (this) {
                    Collection collection2 = this.k;
                    if (collection2 == null) {
                        return;
                    }
                    this.k = collection;
                    a(collection2, false, this);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                dispose();
                this.a.onError(th);
            }
        }

        public void a(r<? super U> rVar, U u) {
            this.a.onNext(u);
        }
    }

    public k(p<T> pVar, p<B> pVar2, Callable<U> callable) {
        super(pVar);
        this.b = pVar2;
        this.c = callable;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super U> rVar) {
        this.a.subscribe(new b(new e(rVar), this.c, this.b));
    }
}

package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.c;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: ObservableDebounce */
public final class q<T, U> extends a<T, T> {
    final wm<? super T, ? extends p<U>> b;

    /* compiled from: ObservableDebounce */
    static final class a<T, U> implements b, r<T> {
        final r<? super T> a;
        final wm<? super T, ? extends p<U>> b;
        b c;
        final AtomicReference<b> d = new AtomicReference();
        volatile long e;
        boolean f;

        /* compiled from: ObservableDebounce */
        static final class a<T, U> extends c<U> {
            final a<T, U> a;
            final long b;
            final T c;
            boolean d;
            final AtomicBoolean e = new AtomicBoolean();

            a(a<T, U> aVar, long j, T t) {
                this.a = aVar;
                this.b = j;
                this.c = t;
            }

            public void onNext(U u) {
                if (!this.d) {
                    this.d = true;
                    dispose();
                    b();
                }
            }

            /* Access modifiers changed, original: 0000 */
            public void b() {
                if (this.e.compareAndSet(false, true)) {
                    this.a.a(this.b, this.c);
                }
            }

            public void onError(Throwable th) {
                if (this.d) {
                    xk.a(th);
                    return;
                }
                this.d = true;
                this.a.onError(th);
            }

            public void onComplete() {
                if (!this.d) {
                    this.d = true;
                    b();
                }
            }
        }

        a(r<? super T> rVar, wm<? super T, ? extends p<U>> wmVar) {
            this.a = rVar;
            this.b = wmVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.f) {
                long j = this.e + 1;
                this.e = j;
                b bVar = (b) this.d.get();
                if (bVar != null) {
                    bVar.dispose();
                }
                try {
                    p pVar = (p) io.reactivex.internal.functions.a.a(this.b.apply(t), "The ObservableSource supplied is null");
                    a aVar = new a(this, j, t);
                    if (this.d.compareAndSet(bVar, aVar)) {
                        pVar.subscribe(aVar);
                    }
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    dispose();
                    this.a.onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.d);
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.f) {
                this.f = true;
                b bVar = (b) this.d.get();
                if (bVar != DisposableHelper.DISPOSED) {
                    ((a) bVar).b();
                    DisposableHelper.dispose(this.d);
                    this.a.onComplete();
                }
            }
        }

        public void dispose() {
            this.c.dispose();
            DisposableHelper.dispose(this.d);
        }

        public boolean isDisposed() {
            return this.c.isDisposed();
        }

        /* Access modifiers changed, original: 0000 */
        public void a(long j, T t) {
            if (j == this.e) {
                this.a.onNext(t);
            }
        }
    }

    public q(p<T> pVar, wm<? super T, ? extends p<U>> wmVar) {
        super(pVar);
        this.b = wmVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(new e(rVar), this.b));
    }
}

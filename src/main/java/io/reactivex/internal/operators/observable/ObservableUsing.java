package io.reactivex.internal.operators.observable;

import defpackage.wl;
import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableUsing<T, D> extends k<T> {
    final Callable<? extends D> a;
    final wm<? super D, ? extends p<? extends T>> b;
    final wl<? super D> c;
    final boolean d;

    static final class UsingObserver<T, D> extends AtomicBoolean implements b, r<T> {
        private static final long serialVersionUID = 5904473792286235046L;
        final wl<? super D> disposer;
        final r<? super T> downstream;
        final boolean eager;
        final D resource;
        b upstream;

        UsingObserver(r<? super T> rVar, D d, wl<? super D> wlVar, boolean z) {
            this.downstream = rVar;
            this.resource = d;
            this.disposer = wlVar;
            this.eager = z;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th2) {
                        a.b(th2);
                        th = new CompositeException(th, th2);
                    }
                }
                this.upstream.dispose();
                this.downstream.onError(th);
                return;
            }
            this.downstream.onError(th);
            this.upstream.dispose();
            disposeAfter();
        }

        public void onComplete() {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th) {
                        a.b(th);
                        this.downstream.onError(th);
                        return;
                    }
                }
                this.upstream.dispose();
                this.downstream.onComplete();
            } else {
                this.downstream.onComplete();
                this.upstream.dispose();
                disposeAfter();
            }
        }

        public void dispose() {
            disposeAfter();
            this.upstream.dispose();
        }

        public boolean isDisposed() {
            return get();
        }

        /* Access modifiers changed, original: 0000 */
        public void disposeAfter() {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept(this.resource);
                } catch (Throwable th) {
                    a.b(th);
                    xk.a(th);
                }
            }
        }
    }

    public ObservableUsing(Callable<? extends D> callable, wm<? super D, ? extends p<? extends T>> wmVar, wl<? super D> wlVar, boolean z) {
        this.a = callable;
        this.b = wmVar;
        this.c = wlVar;
        this.d = z;
    }

    public void subscribeActual(r<? super T> rVar) {
        try {
            Object call = this.a.call();
            try {
                ((p) io.reactivex.internal.functions.a.a(this.b.apply(call), "The sourceSupplier returned a null ObservableSource")).subscribe(new UsingObserver(rVar, call, this.c, this.d));
            } catch (Throwable th) {
                a.b(th);
                EmptyDisposable.error(new CompositeException(th, th), (r) rVar);
            }
        } catch (Throwable th2) {
            a.b(th2);
            EmptyDisposable.error(th2, (r) rVar);
        }
    }
}

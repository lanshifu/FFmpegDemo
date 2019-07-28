package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableFlattenIterable */
public final class ah<T, R> extends a<T, R> {
    final wm<? super T, ? extends Iterable<? extends R>> b;

    /* compiled from: ObservableFlattenIterable */
    static final class a<T, R> implements b, r<T> {
        final r<? super R> a;
        final wm<? super T, ? extends Iterable<? extends R>> b;
        b c;

        a(r<? super R> rVar, wm<? super T, ? extends Iterable<? extends R>> wmVar) {
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
            if (this.c != DisposableHelper.DISPOSED) {
                try {
                    r rVar = this.a;
                    for (Object a : (Iterable) this.b.apply(t)) {
                        try {
                            try {
                                rVar.onNext(io.reactivex.internal.functions.a.a(a, "The iterator returned a null value"));
                            } catch (Throwable th) {
                                io.reactivex.exceptions.a.b(th);
                                this.c.dispose();
                                onError(th);
                                return;
                            }
                        } catch (Throwable th2) {
                            io.reactivex.exceptions.a.b(th2);
                            this.c.dispose();
                            onError(th2);
                            return;
                        }
                    }
                } catch (Throwable th22) {
                    io.reactivex.exceptions.a.b(th22);
                    this.c.dispose();
                    onError(th22);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.c == DisposableHelper.DISPOSED) {
                xk.a(th);
                return;
            }
            this.c = DisposableHelper.DISPOSED;
            this.a.onError(th);
        }

        public void onComplete() {
            if (this.c != DisposableHelper.DISPOSED) {
                this.c = DisposableHelper.DISPOSED;
                this.a.onComplete();
            }
        }

        public boolean isDisposed() {
            return this.c.isDisposed();
        }

        public void dispose() {
            this.c.dispose();
            this.c = DisposableHelper.DISPOSED;
        }
    }

    public ah(p<T> pVar, wm<? super T, ? extends Iterable<? extends R>> wmVar) {
        super(pVar);
        this.b = wmVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super R> rVar) {
        this.a.subscribe(new a(rVar, this.b));
    }
}

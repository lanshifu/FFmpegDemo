package io.reactivex.internal.operators.observable;

import defpackage.wv;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableTakeWhile */
public final class bs<T> extends a<T, T> {
    final wv<? super T> b;

    /* compiled from: ObservableTakeWhile */
    static final class a<T> implements b, r<T> {
        final r<? super T> a;
        final wv<? super T> b;
        b c;
        boolean d;

        a(r<? super T> rVar, wv<? super T> wvVar) {
            this.a = rVar;
            this.b = wvVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.c.dispose();
        }

        public boolean isDisposed() {
            return this.c.isDisposed();
        }

        public void onNext(T t) {
            if (!this.d) {
                try {
                    if (this.b.test(t)) {
                        this.a.onNext(t);
                        return;
                    }
                    this.d = true;
                    this.c.dispose();
                    this.a.onComplete();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.c.dispose();
                    onError(th);
                }
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
                this.a.onComplete();
            }
        }
    }

    public bs(p<T> pVar, wv<? super T> wvVar) {
        super(pVar);
        this.b = wvVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar, this.b));
    }
}

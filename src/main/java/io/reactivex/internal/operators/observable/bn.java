package io.reactivex.internal.operators.observable;

import defpackage.wv;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableSkipWhile */
public final class bn<T> extends a<T, T> {
    final wv<? super T> b;

    /* compiled from: ObservableSkipWhile */
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
            if (this.d) {
                this.a.onNext(t);
            } else {
                try {
                    if (!this.b.test(t)) {
                        this.d = true;
                        this.a.onNext(t);
                    }
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.c.dispose();
                    this.a.onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    public bn(p<T> pVar, wv<? super T> wvVar) {
        super(pVar);
        this.b = wvVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar, this.b));
    }
}

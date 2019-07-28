package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.g;
import io.reactivex.h;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableSingleMaybe */
public final class bj<T> extends g<T> {
    final p<T> a;

    /* compiled from: ObservableSingleMaybe */
    static final class a<T> implements b, r<T> {
        final h<? super T> a;
        b b;
        T c;
        boolean d;

        a(h<? super T> hVar) {
            this.a = hVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.b, bVar)) {
                this.b = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.b.dispose();
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }

        public void onNext(T t) {
            if (!this.d) {
                if (this.c != null) {
                    this.d = true;
                    this.b.dispose();
                    this.a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.c = t;
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
                Object obj = this.c;
                this.c = null;
                if (obj == null) {
                    this.a.onComplete();
                } else {
                    this.a.onSuccess(obj);
                }
            }
        }
    }

    public bj(p<T> pVar) {
        this.a = pVar;
    }

    public void b(h<? super T> hVar) {
        this.a.subscribe(new a(hVar));
    }
}

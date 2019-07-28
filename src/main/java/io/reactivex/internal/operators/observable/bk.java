package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.t;
import io.reactivex.u;
import java.util.NoSuchElementException;

/* compiled from: ObservableSingleSingle */
public final class bk<T> extends t<T> {
    final p<? extends T> a;
    final T b;

    /* compiled from: ObservableSingleSingle */
    static final class a<T> implements b, r<T> {
        final u<? super T> a;
        final T b;
        b c;
        T d;
        boolean e;

        a(u<? super T> uVar, T t) {
            this.a = uVar;
            this.b = t;
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
            if (!this.e) {
                if (this.d != null) {
                    this.e = true;
                    this.c.dispose();
                    this.a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.d = t;
            }
        }

        public void onError(Throwable th) {
            if (this.e) {
                xk.a(th);
                return;
            }
            this.e = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.e) {
                this.e = true;
                Object obj = this.d;
                this.d = null;
                if (obj == null) {
                    obj = this.b;
                }
                if (obj != null) {
                    this.a.onSuccess(obj);
                } else {
                    this.a.onError(new NoSuchElementException());
                }
            }
        }
    }

    public bk(p<? extends T> pVar, T t) {
        this.a = pVar;
        this.b = t;
    }

    public void b(u<? super T> uVar) {
        this.a.subscribe(new a(uVar, this.b));
    }
}

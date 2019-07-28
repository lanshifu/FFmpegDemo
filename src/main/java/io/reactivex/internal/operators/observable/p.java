package io.reactivex.internal.operators.observable;

import defpackage.wx;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.r;
import io.reactivex.t;
import io.reactivex.u;

/* compiled from: ObservableCountSingle */
public final class p<T> extends t<Long> implements wx<Long> {
    final io.reactivex.p<T> a;

    /* compiled from: ObservableCountSingle */
    static final class a implements b, r<Object> {
        final u<? super Long> a;
        b b;
        long c;

        a(u<? super Long> uVar) {
            this.a = uVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.b, bVar)) {
                this.b = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.b.dispose();
            this.b = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }

        public void onNext(Object obj) {
            this.c++;
        }

        public void onError(Throwable th) {
            this.b = DisposableHelper.DISPOSED;
            this.a.onError(th);
        }

        public void onComplete() {
            this.b = DisposableHelper.DISPOSED;
            this.a.onSuccess(Long.valueOf(this.c));
        }
    }

    public p(io.reactivex.p<T> pVar) {
        this.a = pVar;
    }

    public void b(u<? super Long> uVar) {
        this.a.subscribe(new a(uVar));
    }

    public k<Long> j_() {
        return xk.a(new o(this.a));
    }
}

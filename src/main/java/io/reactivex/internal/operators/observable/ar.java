package io.reactivex.internal.operators.observable;

import defpackage.wx;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableIgnoreElementsCompletable */
public final class ar<T> extends io.reactivex.a implements wx<T> {
    final p<T> a;

    /* compiled from: ObservableIgnoreElementsCompletable */
    static final class a<T> implements b, r<T> {
        final io.reactivex.b a;
        b b;

        public void onNext(T t) {
        }

        a(io.reactivex.b bVar) {
            this.a = bVar;
        }

        public void onSubscribe(b bVar) {
            this.b = bVar;
            this.a.onSubscribe(this);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }

        public void dispose() {
            this.b.dispose();
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }
    }

    public ar(p<T> pVar) {
        this.a = pVar;
    }

    public void b(io.reactivex.b bVar) {
        this.a.subscribe(new a(bVar));
    }

    public k<T> j_() {
        return xk.a(new aq(this.a));
    }
}

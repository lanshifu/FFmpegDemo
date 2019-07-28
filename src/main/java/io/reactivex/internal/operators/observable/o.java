package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableCount */
public final class o<T> extends a<T, Long> {

    /* compiled from: ObservableCount */
    static final class a implements b, r<Object> {
        final r<? super Long> a;
        b b;
        long c;

        a(r<? super Long> rVar) {
            this.a = rVar;
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

        public void onNext(Object obj) {
            this.c++;
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onNext(Long.valueOf(this.c));
            this.a.onComplete();
        }
    }

    public o(p<T> pVar) {
        super(pVar);
    }

    public void subscribeActual(r<? super Long> rVar) {
        this.a.subscribe(new a(rVar));
    }
}

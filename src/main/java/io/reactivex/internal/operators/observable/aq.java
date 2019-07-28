package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableIgnoreElements */
public final class aq<T> extends a<T, T> {

    /* compiled from: ObservableIgnoreElements */
    static final class a<T> implements b, r<T> {
        final r<? super T> a;
        b b;

        public void onNext(T t) {
        }

        a(r<? super T> rVar) {
            this.a = rVar;
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

    public aq(p<T> pVar) {
        super(pVar);
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar));
    }
}

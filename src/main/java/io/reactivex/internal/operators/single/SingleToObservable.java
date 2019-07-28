package io.reactivex.internal.operators.single;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import io.reactivex.k;
import io.reactivex.r;
import io.reactivex.u;
import io.reactivex.v;

public final class SingleToObservable<T> extends k<T> {
    final v<? extends T> a;

    static final class SingleToObservableObserver<T> extends DeferredScalarDisposable<T> implements u<T> {
        private static final long serialVersionUID = 3786543492451018833L;
        b upstream;

        SingleToObservableObserver(r<? super T> rVar) {
            super(rVar);
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            complete(t);
        }

        public void onError(Throwable th) {
            error(th);
        }

        public void dispose() {
            super.dispose();
            this.upstream.dispose();
        }
    }

    public SingleToObservable(v<? extends T> vVar) {
        this.a = vVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.a(a(rVar));
    }

    public static <T> u<T> a(r<? super T> rVar) {
        return new SingleToObservableObserver(rVar);
    }
}

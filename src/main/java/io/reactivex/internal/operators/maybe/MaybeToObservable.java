package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.b;
import io.reactivex.h;
import io.reactivex.i;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import io.reactivex.k;
import io.reactivex.r;

public final class MaybeToObservable<T> extends k<T> {
    final i<T> a;

    static final class MaybeToObservableObserver<T> extends DeferredScalarDisposable<T> implements h<T> {
        private static final long serialVersionUID = 7603343402964826922L;
        b upstream;

        MaybeToObservableObserver(r<? super T> rVar) {
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

        public void onComplete() {
            complete();
        }

        public void dispose() {
            super.dispose();
            this.upstream.dispose();
        }
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.a(a(rVar));
    }

    public static <T> h<T> a(r<? super T> rVar) {
        return new MaybeToObservableObserver(rVar);
    }
}

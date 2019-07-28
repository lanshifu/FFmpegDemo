package retrofit2.adapter.rxjava2;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.k;
import io.reactivex.r;
import retrofit2.Response;

final class ResultObservable<T> extends k<Result<T>> {
    private final k<Response<T>> upstream;

    private static class ResultObserver<R> implements r<Response<R>> {
        private final r<? super Result<R>> observer;

        ResultObserver(r<? super Result<R>> rVar) {
            this.observer = rVar;
        }

        public void onSubscribe(b bVar) {
            this.observer.onSubscribe(bVar);
        }

        public void onNext(Response<R> response) {
            this.observer.onNext(Result.response(response));
        }

        public void onError(Throwable th) {
            try {
                this.observer.onNext(Result.error(th));
                this.observer.onComplete();
            } catch (Throwable th2) {
                a.b(th2);
                xk.a(new CompositeException(th, th2));
            }
        }

        public void onComplete() {
            this.observer.onComplete();
        }
    }

    ResultObservable(k<Response<T>> kVar) {
        this.upstream = kVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super Result<T>> rVar) {
        this.upstream.subscribe(new ResultObserver(rVar));
    }
}

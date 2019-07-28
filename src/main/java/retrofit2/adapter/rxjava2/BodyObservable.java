package retrofit2.adapter.rxjava2;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.k;
import io.reactivex.r;
import retrofit2.Response;

final class BodyObservable<T> extends k<T> {
    private final k<Response<T>> upstream;

    private static class BodyObserver<R> implements r<Response<R>> {
        private final r<? super R> observer;
        private boolean terminated;

        BodyObserver(r<? super R> rVar) {
            this.observer = rVar;
        }

        public void onSubscribe(b bVar) {
            this.observer.onSubscribe(bVar);
        }

        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                this.observer.onNext(response.body());
                return;
            }
            this.terminated = true;
            try {
                this.observer.onError(new HttpException(response));
            } catch (Throwable th) {
                a.b(th);
                xk.a(new CompositeException(r1, th));
            }
        }

        public void onComplete() {
            if (!this.terminated) {
                this.observer.onComplete();
            }
        }

        public void onError(Throwable th) {
            if (this.terminated) {
                Throwable assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
                assertionError.initCause(th);
                xk.a(assertionError);
                return;
            }
            this.observer.onError(th);
        }
    }

    BodyObservable(k<Response<T>> kVar) {
        this.upstream = kVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.upstream.subscribe(new BodyObserver(rVar));
    }
}

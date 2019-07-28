package retrofit2.adapter.rxjava2;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.k;
import io.reactivex.r;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

final class CallEnqueueObservable<T> extends k<Response<T>> {
    private final Call<T> originalCall;

    private static final class CallCallback<T> implements b, Callback<T> {
        private final Call<?> call;
        private volatile boolean disposed;
        private final r<? super Response<T>> observer;
        boolean terminated = false;

        CallCallback(Call<?> call, r<? super Response<T>> rVar) {
            this.call = call;
            this.observer = rVar;
        }

        public void onResponse(Call<T> call, Response<T> response) {
            if (!this.disposed) {
                try {
                    this.observer.onNext(response);
                    if (!this.disposed) {
                        this.terminated = true;
                        this.observer.onComplete();
                    }
                } catch (Throwable th) {
                    a.b(th);
                    xk.a(new CompositeException(th, th));
                }
            }
        }

        public void onFailure(Call<T> call, Throwable th) {
            if (!call.isCanceled()) {
                try {
                    this.observer.onError(th);
                } catch (Throwable th2) {
                    a.b(th2);
                    xk.a(new CompositeException(th, th2));
                }
            }
        }

        public void dispose() {
            this.disposed = true;
            this.call.cancel();
        }

        public boolean isDisposed() {
            return this.disposed;
        }
    }

    CallEnqueueObservable(Call<T> call) {
        this.originalCall = call;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super Response<T>> rVar) {
        Call clone = this.originalCall.clone();
        CallCallback callCallback = new CallCallback(clone, rVar);
        rVar.onSubscribe(callCallback);
        clone.enqueue(callCallback);
    }
}

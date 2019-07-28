package retrofit2.adapter.rxjava2;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.k;
import io.reactivex.r;
import retrofit2.Call;
import retrofit2.Response;

final class CallExecuteObservable<T> extends k<Response<T>> {
    private final Call<T> originalCall;

    private static final class CallDisposable implements b {
        private final Call<?> call;
        private volatile boolean disposed;

        CallDisposable(Call<?> call) {
            this.call = call;
        }

        public void dispose() {
            this.disposed = true;
            this.call.cancel();
        }

        public boolean isDisposed() {
            return this.disposed;
        }
    }

    CallExecuteObservable(Call<T> call) {
        this.originalCall = call;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super Response<T>> rVar) {
        Throwable th;
        Object obj;
        Call clone = this.originalCall.clone();
        CallDisposable callDisposable = new CallDisposable(clone);
        rVar.onSubscribe(callDisposable);
        try {
            Response execute = clone.execute();
            if (!callDisposable.isDisposed()) {
                rVar.onNext(execute);
            }
            if (!callDisposable.isDisposed()) {
                try {
                    rVar.onComplete();
                } catch (Throwable th2) {
                    th = th2;
                    obj = 1;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            obj = null;
            a.b(th);
            if (obj != null) {
                xk.a(th);
            } else if (!callDisposable.isDisposed()) {
                try {
                    rVar.onError(th);
                } catch (Throwable th4) {
                    a.b(th4);
                    xk.a(new CompositeException(th, th4));
                }
            }
        }
    }
}

package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.Callable;

/* compiled from: ObservableFromCallable */
public final class aj<T> extends k<T> implements Callable<T> {
    final Callable<? extends T> a;

    public aj(Callable<? extends T> callable) {
        this.a = callable;
    }

    public void subscribeActual(r<? super T> rVar) {
        DeferredScalarDisposable deferredScalarDisposable = new DeferredScalarDisposable(rVar);
        rVar.onSubscribe(deferredScalarDisposable);
        if (!deferredScalarDisposable.isDisposed()) {
            try {
                deferredScalarDisposable.complete(a.a(this.a.call(), "Callable returned null"));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                if (deferredScalarDisposable.isDisposed()) {
                    xk.a(th);
                } else {
                    rVar.onError(th);
                }
            }
        }
    }

    public T call() throws Exception {
        return a.a(this.a.call(), "The callable returned a null value");
    }
}

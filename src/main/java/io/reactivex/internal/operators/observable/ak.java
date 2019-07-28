package io.reactivex.internal.operators.observable;

import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* compiled from: ObservableFromFuture */
public final class ak<T> extends k<T> {
    final Future<? extends T> a;
    final long b;
    final TimeUnit c;

    public ak(Future<? extends T> future, long j, TimeUnit timeUnit) {
        this.a = future;
        this.b = j;
        this.c = timeUnit;
    }

    public void subscribeActual(r<? super T> rVar) {
        DeferredScalarDisposable deferredScalarDisposable = new DeferredScalarDisposable(rVar);
        rVar.onSubscribe(deferredScalarDisposable);
        if (!deferredScalarDisposable.isDisposed()) {
            try {
                deferredScalarDisposable.complete(a.a(this.c != null ? this.a.get(this.b, this.c) : this.a.get(), "Future returned null"));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                if (!deferredScalarDisposable.isDisposed()) {
                    rVar.onError(th);
                }
            }
        }
    }
}

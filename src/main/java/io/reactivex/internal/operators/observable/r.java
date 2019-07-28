package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.k;
import io.reactivex.p;
import java.util.concurrent.Callable;

/* compiled from: ObservableDefer */
public final class r<T> extends k<T> {
    final Callable<? extends p<? extends T>> a;

    public r(Callable<? extends p<? extends T>> callable) {
        this.a = callable;
    }

    public void subscribeActual(io.reactivex.r<? super T> rVar) {
        try {
            ((p) a.a(this.a.call(), "null ObservableSource supplied")).subscribe(rVar);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, (io.reactivex.r) rVar);
        }
    }
}

package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.Callable;

/* compiled from: ObservableError */
public final class af<T> extends k<T> {
    final Callable<? extends Throwable> a;

    public af(Callable<? extends Throwable> callable) {
        this.a = callable;
    }

    public void subscribeActual(r<? super T> rVar) {
        Throwable th;
        try {
            th = (Throwable) a.a(this.a.call(), "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
        } catch (Throwable th2) {
            th = th2;
            io.reactivex.exceptions.a.b(th);
        }
        EmptyDisposable.error(th, (r) rVar);
    }
}

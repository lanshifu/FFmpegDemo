package io.reactivex.internal.operators.observable;

import defpackage.xb;
import io.reactivex.internal.operators.observable.ObservableScalarXMap.ScalarDisposable;
import io.reactivex.k;
import io.reactivex.r;

/* compiled from: ObservableJust */
public final class at<T> extends k<T> implements xb<T> {
    private final T a;

    public at(T t) {
        this.a = t;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        ScalarDisposable scalarDisposable = new ScalarDisposable(rVar, this.a);
        rVar.onSubscribe(scalarDisposable);
        scalarDisposable.run();
    }

    public T call() {
        return this.a;
    }
}

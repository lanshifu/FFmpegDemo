package io.reactivex.internal.operators.observable;

import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableFromUnsafeSource */
public final class an<T> extends k<T> {
    final p<T> a;

    public an(p<T> pVar) {
        this.a = pVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(rVar);
    }
}

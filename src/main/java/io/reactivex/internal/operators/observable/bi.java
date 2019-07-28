package io.reactivex.internal.operators.observable;

import io.reactivex.k;
import io.reactivex.observers.e;
import io.reactivex.r;

/* compiled from: ObservableSerialized */
public final class bi<T> extends a<T, T> {
    public bi(k<T> kVar) {
        super(kVar);
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new e(rVar));
    }
}

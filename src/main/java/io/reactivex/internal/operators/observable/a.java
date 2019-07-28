package io.reactivex.internal.operators.observable;

import io.reactivex.k;
import io.reactivex.p;

/* compiled from: AbstractObservableWithUpstream */
abstract class a<T, U> extends k<U> {
    protected final p<T> a;

    a(p<T> pVar) {
        this.a = pVar;
    }
}

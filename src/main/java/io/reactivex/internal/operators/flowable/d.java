package io.reactivex.internal.operators.flowable;

import defpackage.xb;
import io.reactivex.e;
import io.reactivex.internal.subscriptions.ScalarSubscription;

/* compiled from: FlowableJust */
public final class d<T> extends e<T> implements xb<T> {
    private final T b;

    public d(T t) {
        this.b = t;
    }

    /* Access modifiers changed, original: protected */
    public void b(aar<? super T> aar) {
        aar.onSubscribe(new ScalarSubscription(aar, this.b));
    }

    public T call() {
        return this.b;
    }
}

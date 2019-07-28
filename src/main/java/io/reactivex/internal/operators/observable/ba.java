package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.r;

/* compiled from: ObservableNever */
public final class ba extends k<Object> {
    public static final k<Object> a = new ba();

    private ba() {
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super Object> rVar) {
        rVar.onSubscribe(EmptyDisposable.NEVER);
    }
}

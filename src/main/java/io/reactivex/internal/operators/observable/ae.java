package io.reactivex.internal.operators.observable;

import defpackage.xb;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.r;

/* compiled from: ObservableEmpty */
public final class ae extends k<Object> implements xb<Object> {
    public static final k<Object> a = new ae();

    public Object call() {
        return null;
    }

    private ae() {
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super Object> rVar) {
        EmptyDisposable.complete((r) rVar);
    }
}

package io.reactivex.internal.operators.observable;

import defpackage.wf;
import defpackage.wl;
import io.reactivex.disposables.b;
import io.reactivex.internal.observers.g;
import io.reactivex.k;
import io.reactivex.r;

/* compiled from: ObservableDoOnLifecycle */
public final class aa<T> extends a<T, T> {
    private final wl<? super b> b;
    private final wf c;

    public aa(k<T> kVar, wl<? super b> wlVar, wf wfVar) {
        super(kVar);
        this.b = wlVar;
        this.c = wfVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new g(rVar, this.b, this.c));
    }
}

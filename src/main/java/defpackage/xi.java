package defpackage;

import io.reactivex.disposables.b;
import io.reactivex.internal.operators.observable.ObservableRefCount;
import io.reactivex.k;

/* compiled from: ConnectableObservable */
/* renamed from: xi */
public abstract class xi<T> extends k<T> {
    public abstract void a(wl<? super b> wlVar);

    public k<T> a() {
        return xk.a(new ObservableRefCount(this));
    }
}

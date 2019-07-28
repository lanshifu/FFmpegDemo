package defpackage;

import io.reactivex.k;
import io.reactivex.r;

/* compiled from: InitialValueObservable */
/* renamed from: mg */
public abstract class mg<T> extends k<T> {
    public abstract T a();

    public abstract void a(r<? super T> rVar);

    /* Access modifiers changed, original: protected|final */
    public final void subscribeActual(r<? super T> rVar) {
        a(rVar);
        rVar.onNext(a());
    }
}

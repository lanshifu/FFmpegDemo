package io.reactivex;

import defpackage.xk;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.f;
import io.reactivex.internal.operators.maybe.MaybeTakeUntilMaybe;

/* compiled from: Maybe */
public abstract class g<T> implements i<T> {
    public abstract void b(h<? super T> hVar);

    public final T a() {
        h fVar = new f();
        a(fVar);
        return fVar.b();
    }

    public final void a(h<? super T> hVar) {
        a.a((Object) hVar, "observer is null");
        Object a = xk.a(this, (h) hVar);
        a.a(a, "The RxJavaPlugins.onSubscribe hook returned a null MaybeObserver. Please check the handler provided to RxJavaPlugins.setOnMaybeSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            b(a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            new NullPointerException("subscribeActual failed").initCause(th);
        }
    }

    public final <U> g<T> a(i<U> iVar) {
        a.a((Object) iVar, "other is null");
        return xk.a(new MaybeTakeUntilMaybe(this, iVar));
    }
}

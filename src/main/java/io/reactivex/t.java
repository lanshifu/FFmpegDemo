package io.reactivex;

import defpackage.wm;
import defpackage.wx;
import defpackage.xk;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.f;
import io.reactivex.internal.operators.single.SingleTakeUntil;
import io.reactivex.internal.operators.single.SingleToFlowable;
import io.reactivex.internal.operators.single.SingleToObservable;

/* compiled from: Single */
public abstract class t<T> implements v<T> {
    public abstract void b(u<? super T> uVar);

    public final T a() {
        u fVar = new f();
        a(fVar);
        return fVar.b();
    }

    public final <R> t<R> a(wm<? super T, ? extends R> wmVar) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new io.reactivex.internal.operators.single.a(this, wmVar));
    }

    public final void a(u<? super T> uVar) {
        a.a((Object) uVar, "subscriber is null");
        Object a = xk.a(this, (u) uVar);
        a.a(a, "The RxJavaPlugins.onSubscribe hook returned a null SingleObserver. Please check the handler provided to RxJavaPlugins.setOnSingleSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            b(a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            new NullPointerException("subscribeActual failed").initCause(th);
        }
    }

    public final <E> t<T> a(aaq<E> aaq) {
        a.a((Object) aaq, "other is null");
        return xk.a(new SingleTakeUntil(this, aaq));
    }

    public final <E> t<T> a(v<? extends E> vVar) {
        a.a((Object) vVar, "other is null");
        return a(new SingleToFlowable(vVar));
    }

    public final k<T> b() {
        if (this instanceof wx) {
            return ((wx) this).j_();
        }
        return xk.a(new SingleToObservable(this));
    }
}

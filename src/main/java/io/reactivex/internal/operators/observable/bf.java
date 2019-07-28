package io.reactivex.internal.operators.observable;

import defpackage.wh;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.p;
import io.reactivex.t;
import io.reactivex.u;
import java.util.concurrent.Callable;

/* compiled from: ObservableReduceWithSingle */
public final class bf<T, R> extends t<R> {
    final p<T> a;
    final Callable<R> b;
    final wh<R, ? super T, R> c;

    public bf(p<T> pVar, Callable<R> callable, wh<R, ? super T, R> whVar) {
        this.a = pVar;
        this.b = callable;
        this.c = whVar;
    }

    /* Access modifiers changed, original: protected */
    public void b(u<? super R> uVar) {
        try {
            this.a.subscribe(new a(uVar, this.c, a.a(this.b.call(), "The seedSupplier returned a null value")));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, (u) uVar);
        }
    }
}

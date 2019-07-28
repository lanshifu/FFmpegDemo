package io.reactivex.internal.operators.observable;

import defpackage.wx;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.t;
import io.reactivex.u;
import java.util.Collection;
import java.util.concurrent.Callable;

/* compiled from: ObservableToListSingle */
public final class bv<T, U extends Collection<? super T>> extends t<U> implements wx<U> {
    final p<T> a;
    final Callable<U> b;

    /* compiled from: ObservableToListSingle */
    static final class a<T, U extends Collection<? super T>> implements b, r<T> {
        final u<? super U> a;
        U b;
        b c;

        a(u<? super U> uVar, U u) {
            this.a = uVar;
            this.b = u;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.c.dispose();
        }

        public boolean isDisposed() {
            return this.c.isDisposed();
        }

        public void onNext(T t) {
            this.b.add(t);
        }

        public void onError(Throwable th) {
            this.b = null;
            this.a.onError(th);
        }

        public void onComplete() {
            Collection collection = this.b;
            this.b = null;
            this.a.onSuccess(collection);
        }
    }

    public bv(p<T> pVar, int i) {
        this.a = pVar;
        this.b = Functions.a(i);
    }

    public bv(p<T> pVar, Callable<U> callable) {
        this.a = pVar;
        this.b = callable;
    }

    public void b(u<? super U> uVar) {
        try {
            this.a.subscribe(new a(uVar, (Collection) io.reactivex.internal.functions.a.a(this.b.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, (u) uVar);
        }
    }

    public k<U> j_() {
        return xk.a(new bu(this.a, this.b));
    }
}

package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.p;
import io.reactivex.r;
import java.util.Collection;
import java.util.concurrent.Callable;

/* compiled from: ObservableToList */
public final class bu<T, U extends Collection<? super T>> extends a<T, U> {
    final Callable<U> b;

    /* compiled from: ObservableToList */
    static final class a<T, U extends Collection<? super T>> implements b, r<T> {
        final r<? super U> a;
        b b;
        U c;

        a(r<? super U> rVar, U u) {
            this.a = rVar;
            this.c = u;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.b, bVar)) {
                this.b = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.b.dispose();
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }

        public void onNext(T t) {
            this.c.add(t);
        }

        public void onError(Throwable th) {
            this.c = null;
            this.a.onError(th);
        }

        public void onComplete() {
            Collection collection = this.c;
            this.c = null;
            this.a.onNext(collection);
            this.a.onComplete();
        }
    }

    public bu(p<T> pVar, int i) {
        super(pVar);
        this.b = Functions.a(i);
    }

    public bu(p<T> pVar, Callable<U> callable) {
        super(pVar);
        this.b = callable;
    }

    public void subscribeActual(r<? super U> rVar) {
        try {
            this.a.subscribe(new a(rVar, (Collection) io.reactivex.internal.functions.a.a(this.b.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, (r) rVar);
        }
    }
}

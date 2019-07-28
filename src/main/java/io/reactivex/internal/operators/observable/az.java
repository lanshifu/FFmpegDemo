package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.j;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableMaterialize */
public final class az<T> extends a<T, j<T>> {

    /* compiled from: ObservableMaterialize */
    static final class a<T> implements b, r<T> {
        final r<? super j<T>> a;
        b b;

        a(r<? super j<T>> rVar) {
            this.a = rVar;
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
            this.a.onNext(j.a((Object) t));
        }

        public void onError(Throwable th) {
            this.a.onNext(j.a(th));
            this.a.onComplete();
        }

        public void onComplete() {
            this.a.onNext(j.f());
            this.a.onComplete();
        }
    }

    public az(p<T> pVar) {
        super(pVar);
    }

    public void subscribeActual(r<? super j<T>> rVar) {
        this.a.subscribe(new a(rVar));
    }
}

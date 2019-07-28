package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableHide */
public final class ap<T> extends a<T, T> {

    /* compiled from: ObservableHide */
    static final class a<T> implements b, r<T> {
        final r<? super T> a;
        b b;

        a(r<? super T> rVar) {
            this.a = rVar;
        }

        public void dispose() {
            this.b.dispose();
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.b, bVar)) {
                this.b = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.a.onNext(t);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    public ap(p<T> pVar) {
        super(pVar);
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar));
    }
}

package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EmptyComponent;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableDetach */
public final class v<T> extends a<T, T> {

    /* compiled from: ObservableDetach */
    static final class a<T> implements b, r<T> {
        r<? super T> a;
        b b;

        a(r<? super T> rVar) {
            this.a = rVar;
        }

        public void dispose() {
            b bVar = this.b;
            this.b = EmptyComponent.INSTANCE;
            this.a = EmptyComponent.asObserver();
            bVar.dispose();
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
            r rVar = this.a;
            this.b = EmptyComponent.INSTANCE;
            this.a = EmptyComponent.asObserver();
            rVar.onError(th);
        }

        public void onComplete() {
            r rVar = this.a;
            this.b = EmptyComponent.INSTANCE;
            this.a = EmptyComponent.asObserver();
            rVar.onComplete();
        }
    }

    public v(p<T> pVar) {
        super(pVar);
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar));
    }
}

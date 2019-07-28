package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.r;
import io.reactivex.u;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatWithSingle<T> extends a<T, T> {
    final v<? extends T> b;

    static final class ConcatWithObserver<T> extends AtomicReference<b> implements b, r<T>, u<T> {
        private static final long serialVersionUID = -1953724749712440952L;
        final r<? super T> downstream;
        boolean inSingle;
        v<? extends T> other;

        ConcatWithObserver(r<? super T> rVar, v<? extends T> vVar) {
            this.downstream = rVar;
            this.other = vVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar) && !this.inSingle) {
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onSuccess(T t) {
            this.downstream.onNext(t);
            this.downstream.onComplete();
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.inSingle = true;
            DisposableHelper.replace(this, null);
            v vVar = this.other;
            this.other = null;
            vVar.a(this);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }
    }

    public ObservableConcatWithSingle(k<T> kVar, v<? extends T> vVar) {
        super(kVar);
        this.b = vVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new ConcatWithObserver(rVar, this.b));
    }
}

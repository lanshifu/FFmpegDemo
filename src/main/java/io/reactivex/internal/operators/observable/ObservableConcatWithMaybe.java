package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.h;
import io.reactivex.i;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatWithMaybe<T> extends a<T, T> {
    final i<? extends T> b;

    static final class ConcatWithObserver<T> extends AtomicReference<b> implements b, h<T>, r<T> {
        private static final long serialVersionUID = -1953724749712440952L;
        final r<? super T> downstream;
        boolean inMaybe;
        i<? extends T> other;

        ConcatWithObserver(r<? super T> rVar, i<? extends T> iVar) {
            this.downstream = rVar;
            this.other = iVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar) && !this.inMaybe) {
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
            if (this.inMaybe) {
                this.downstream.onComplete();
                return;
            }
            this.inMaybe = true;
            DisposableHelper.replace(this, null);
            i iVar = this.other;
            this.other = null;
            iVar.a(this);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }
    }

    public ObservableConcatWithMaybe(k<T> kVar, i<? extends T> iVar) {
        super(kVar);
        this.b = iVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new ConcatWithObserver(rVar, this.b));
    }
}

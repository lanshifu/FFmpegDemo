package io.reactivex.internal.operators.observable;

import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatWithCompletable<T> extends a<T, T> {
    final c b;

    static final class ConcatWithObserver<T> extends AtomicReference<b> implements io.reactivex.b, b, r<T> {
        private static final long serialVersionUID = -1953724749712440952L;
        final r<? super T> downstream;
        boolean inCompletable;
        c other;

        ConcatWithObserver(r<? super T> rVar, c cVar) {
            this.downstream = rVar;
            this.other = cVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar) && !this.inCompletable) {
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (this.inCompletable) {
                this.downstream.onComplete();
                return;
            }
            this.inCompletable = true;
            DisposableHelper.replace(this, null);
            c cVar = this.other;
            this.other = null;
            cVar.a(this);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }
    }

    public ObservableConcatWithCompletable(k<T> kVar, c cVar) {
        super(kVar);
        this.b = cVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new ConcatWithObserver(rVar, this.b));
    }
}

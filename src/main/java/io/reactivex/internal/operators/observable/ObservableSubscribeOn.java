package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSubscribeOn<T> extends a<T, T> {
    final s b;

    final class a implements Runnable {
        private final SubscribeOnObserver<T> b;

        a(SubscribeOnObserver<T> subscribeOnObserver) {
            this.b = subscribeOnObserver;
        }

        public void run() {
            ObservableSubscribeOn.this.a.subscribe(this.b);
        }
    }

    static final class SubscribeOnObserver<T> extends AtomicReference<b> implements b, r<T> {
        private static final long serialVersionUID = 8094547886072529208L;
        final r<? super T> downstream;
        final AtomicReference<b> upstream = new AtomicReference();

        SubscribeOnObserver(r<? super T> rVar) {
            this.downstream = rVar;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.downstream.onComplete();
        }

        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }

        /* Access modifiers changed, original: 0000 */
        public void setDisposable(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }
    }

    public ObservableSubscribeOn(p<T> pVar, s sVar) {
        super(pVar);
        this.b = sVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        SubscribeOnObserver subscribeOnObserver = new SubscribeOnObserver(rVar);
        rVar.onSubscribe(subscribeOnObserver);
        subscribeOnObserver.setDisposable(this.b.a(new a(subscribeOnObserver)));
    }
}

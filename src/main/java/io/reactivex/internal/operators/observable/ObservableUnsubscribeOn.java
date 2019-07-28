package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableUnsubscribeOn<T> extends a<T, T> {
    final s b;

    static final class UnsubscribeObserver<T> extends AtomicBoolean implements b, r<T> {
        private static final long serialVersionUID = 1015244841293359600L;
        final r<? super T> downstream;
        final s scheduler;
        b upstream;

        final class a implements Runnable {
            a() {
            }

            public void run() {
                UnsubscribeObserver.this.upstream.dispose();
            }
        }

        UnsubscribeObserver(r<? super T> rVar, s sVar) {
            this.downstream = rVar;
            this.scheduler = sVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!get()) {
                this.downstream.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (get()) {
                xk.a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        public void onComplete() {
            if (!get()) {
                this.downstream.onComplete();
            }
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                this.scheduler.a(new a());
            }
        }

        public boolean isDisposed() {
            return get();
        }
    }

    public ObservableUnsubscribeOn(p<T> pVar, s sVar) {
        super(pVar);
        this.b = sVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new UnsubscribeObserver(rVar, this.b));
    }
}

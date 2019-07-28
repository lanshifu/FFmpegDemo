package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.r;
import io.reactivex.s;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimer extends k<Long> {
    final s a;
    final long b;
    final TimeUnit c;

    static final class TimerObserver extends AtomicReference<b> implements b, Runnable {
        private static final long serialVersionUID = -2809475196591179431L;
        final r<? super Long> downstream;

        TimerObserver(r<? super Long> rVar) {
            this.downstream = rVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void run() {
            if (!isDisposed()) {
                this.downstream.onNext(Long.valueOf(0));
                lazySet(EmptyDisposable.INSTANCE);
                this.downstream.onComplete();
            }
        }

        public void setResource(b bVar) {
            DisposableHelper.trySet(this, bVar);
        }
    }

    public ObservableTimer(long j, TimeUnit timeUnit, s sVar) {
        this.b = j;
        this.c = timeUnit;
        this.a = sVar;
    }

    public void subscribeActual(r<? super Long> rVar) {
        TimerObserver timerObserver = new TimerObserver(rVar);
        rVar.onSubscribe(timerObserver);
        timerObserver.setResource(this.a.a(timerObserver, this.b, this.c));
    }
}

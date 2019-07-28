package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.schedulers.i;
import io.reactivex.k;
import io.reactivex.r;
import io.reactivex.s;
import io.reactivex.s.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableInterval extends k<Long> {
    final s a;
    final long b;
    final long c;
    final TimeUnit d;

    static final class IntervalObserver extends AtomicReference<b> implements b, Runnable {
        private static final long serialVersionUID = 346773832286157679L;
        long count;
        final r<? super Long> downstream;

        IntervalObserver(r<? super Long> rVar) {
            this.downstream = rVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void run() {
            if (get() != DisposableHelper.DISPOSED) {
                r rVar = this.downstream;
                long j = this.count;
                this.count = 1 + j;
                rVar.onNext(Long.valueOf(j));
            }
        }

        public void setResource(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }
    }

    public ObservableInterval(long j, long j2, TimeUnit timeUnit, s sVar) {
        this.b = j;
        this.c = j2;
        this.d = timeUnit;
        this.a = sVar;
    }

    public void subscribeActual(r<? super Long> rVar) {
        IntervalObserver intervalObserver = new IntervalObserver(rVar);
        rVar.onSubscribe(intervalObserver);
        s sVar = this.a;
        if (sVar instanceof i) {
            c a = sVar.a();
            intervalObserver.setResource(a);
            a.a(intervalObserver, this.b, this.c, this.d);
            return;
        }
        intervalObserver.setResource(sVar.a(intervalObserver, this.b, this.c, this.d));
    }
}

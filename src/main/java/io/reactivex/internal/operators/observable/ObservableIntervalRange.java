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

public final class ObservableIntervalRange extends k<Long> {
    final s a;
    final long b;
    final long c;
    final long d;
    final long e;
    final TimeUnit f;

    static final class IntervalRangeObserver extends AtomicReference<b> implements b, Runnable {
        private static final long serialVersionUID = 1891866368734007884L;
        long count;
        final r<? super Long> downstream;
        final long end;

        IntervalRangeObserver(r<? super Long> rVar, long j, long j2) {
            this.downstream = rVar;
            this.count = j;
            this.end = j2;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void run() {
            if (!isDisposed()) {
                long j = this.count;
                this.downstream.onNext(Long.valueOf(j));
                if (j == this.end) {
                    DisposableHelper.dispose(this);
                    this.downstream.onComplete();
                    return;
                }
                this.count = j + 1;
            }
        }

        public void setResource(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }
    }

    public ObservableIntervalRange(long j, long j2, long j3, long j4, TimeUnit timeUnit, s sVar) {
        this.d = j3;
        this.e = j4;
        this.f = timeUnit;
        this.a = sVar;
        this.b = j;
        this.c = j2;
    }

    public void subscribeActual(r<? super Long> rVar) {
        IntervalRangeObserver intervalRangeObserver = new IntervalRangeObserver(rVar, this.b, this.c);
        rVar.onSubscribe(intervalRangeObserver);
        s sVar = this.a;
        if (sVar instanceof i) {
            c a = sVar.a();
            intervalRangeObserver.setResource(a);
            a.a(intervalRangeObserver, this.d, this.e, this.f);
            return;
        }
        intervalRangeObserver.setResource(sVar.a(intervalRangeObserver, this.d, this.e, this.f));
    }
}

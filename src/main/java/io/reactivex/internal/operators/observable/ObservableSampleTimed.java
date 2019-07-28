package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSampleTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final s d;
    final boolean e;

    static abstract class SampleTimedObserver<T> extends AtomicReference<T> implements b, r<T>, Runnable {
        private static final long serialVersionUID = -3517602651313910099L;
        final r<? super T> downstream;
        final long period;
        final s scheduler;
        final AtomicReference<b> timer = new AtomicReference();
        final TimeUnit unit;
        b upstream;

        public abstract void complete();

        SampleTimedObserver(r<? super T> rVar, long j, TimeUnit timeUnit, s sVar) {
            this.downstream = rVar;
            this.period = j;
            this.unit = timeUnit;
            this.scheduler = sVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
                DisposableHelper.replace(this.timer, this.scheduler.a(this, this.period, this.period, this.unit));
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable th) {
            cancelTimer();
            this.downstream.onError(th);
        }

        public void onComplete() {
            cancelTimer();
            complete();
        }

        /* Access modifiers changed, original: 0000 */
        public void cancelTimer() {
            DisposableHelper.dispose(this.timer);
        }

        public void dispose() {
            cancelTimer();
            this.upstream.dispose();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        /* Access modifiers changed, original: 0000 */
        public void emit() {
            Object andSet = getAndSet(null);
            if (andSet != null) {
                this.downstream.onNext(andSet);
            }
        }
    }

    static final class SampleTimedEmitLast<T> extends SampleTimedObserver<T> {
        private static final long serialVersionUID = -7139995637533111443L;
        final AtomicInteger wip = new AtomicInteger(1);

        SampleTimedEmitLast(r<? super T> rVar, long j, TimeUnit timeUnit, s sVar) {
            super(rVar, j, timeUnit, sVar);
        }

        /* Access modifiers changed, original: 0000 */
        public void complete() {
            emit();
            if (this.wip.decrementAndGet() == 0) {
                this.downstream.onComplete();
            }
        }

        public void run() {
            if (this.wip.incrementAndGet() == 2) {
                emit();
                if (this.wip.decrementAndGet() == 0) {
                    this.downstream.onComplete();
                }
            }
        }
    }

    static final class SampleTimedNoLast<T> extends SampleTimedObserver<T> {
        private static final long serialVersionUID = -7139995637533111443L;

        SampleTimedNoLast(r<? super T> rVar, long j, TimeUnit timeUnit, s sVar) {
            super(rVar, j, timeUnit, sVar);
        }

        /* Access modifiers changed, original: 0000 */
        public void complete() {
            this.downstream.onComplete();
        }

        public void run() {
            emit();
        }
    }

    public ObservableSampleTimed(p<T> pVar, long j, TimeUnit timeUnit, s sVar, boolean z) {
        super(pVar);
        this.b = j;
        this.c = timeUnit;
        this.d = sVar;
        this.e = z;
    }

    public void subscribeActual(r<? super T> rVar) {
        e eVar = new e(rVar);
        if (this.e) {
            this.a.subscribe(new SampleTimedEmitLast(eVar, this.b, this.c, this.d));
        } else {
            this.a.subscribe(new SampleTimedNoLast(eVar, this.b, this.c, this.d));
        }
    }
}

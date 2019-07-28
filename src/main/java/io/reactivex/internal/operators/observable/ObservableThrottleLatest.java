package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.r;
import io.reactivex.s;
import io.reactivex.s.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableThrottleLatest<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final s d;
    final boolean e;

    static final class ThrottleLatestObserver<T> extends AtomicInteger implements b, r<T>, Runnable {
        private static final long serialVersionUID = -8296689127439125014L;
        volatile boolean cancelled;
        volatile boolean done;
        final r<? super T> downstream;
        final boolean emitLast;
        Throwable error;
        final AtomicReference<T> latest = new AtomicReference();
        final long timeout;
        volatile boolean timerFired;
        boolean timerRunning;
        final TimeUnit unit;
        b upstream;
        final c worker;

        ThrottleLatestObserver(r<? super T> rVar, long j, TimeUnit timeUnit, c cVar, boolean z) {
            this.downstream = rVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
            this.emitLast = z;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.latest.set(t);
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.worker.dispose();
            if (getAndIncrement() == 0) {
                this.latest.lazySet(null);
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void run() {
            this.timerFired = true;
            drain();
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                AtomicReference atomicReference = this.latest;
                r rVar = this.downstream;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = this.done;
                    if (!z || this.error == null) {
                        Object obj = atomicReference.get() == null ? 1 : null;
                        if (z) {
                            Object andSet = atomicReference.getAndSet(null);
                            if (obj == null && this.emitLast) {
                                rVar.onNext(andSet);
                            }
                            rVar.onComplete();
                            this.worker.dispose();
                            return;
                        }
                        if (obj != null) {
                            if (this.timerFired) {
                                this.timerRunning = false;
                                this.timerFired = false;
                            }
                        } else if (!this.timerRunning || this.timerFired) {
                            rVar.onNext(atomicReference.getAndSet(null));
                            this.timerFired = false;
                            this.timerRunning = true;
                            this.worker.a(this, this.timeout, this.unit);
                        }
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        atomicReference.lazySet(null);
                        rVar.onError(this.error);
                        this.worker.dispose();
                        return;
                    }
                }
                atomicReference.lazySet(null);
            }
        }
    }

    public ObservableThrottleLatest(k<T> kVar, long j, TimeUnit timeUnit, s sVar, boolean z) {
        super(kVar);
        this.b = j;
        this.c = timeUnit;
        this.d = sVar;
        this.e = z;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new ThrottleLatestObserver(rVar, this.b, this.c, this.d.a(), this.e));
    }
}

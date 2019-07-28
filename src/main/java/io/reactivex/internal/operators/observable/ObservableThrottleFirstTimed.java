package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import io.reactivex.s.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableThrottleFirstTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final s d;

    static final class DebounceTimedObserver<T> extends AtomicReference<b> implements b, r<T>, Runnable {
        private static final long serialVersionUID = 786994795061867455L;
        boolean done;
        final r<? super T> downstream;
        volatile boolean gate;
        final long timeout;
        final TimeUnit unit;
        b upstream;
        final c worker;

        DebounceTimedObserver(r<? super T> rVar, long j, TimeUnit timeUnit, c cVar) {
            this.downstream = rVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.gate && !this.done) {
                this.gate = true;
                this.downstream.onNext(t);
                b bVar = (b) get();
                if (bVar != null) {
                    bVar.dispose();
                }
                DisposableHelper.replace(this, this.worker.a(this, this.timeout, this.unit));
            }
        }

        public void run() {
            this.gate = false;
        }

        public void onError(Throwable th) {
            if (this.done) {
                xk.a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        public void dispose() {
            this.upstream.dispose();
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return this.worker.isDisposed();
        }
    }

    public ObservableThrottleFirstTimed(p<T> pVar, long j, TimeUnit timeUnit, s sVar) {
        super(pVar);
        this.b = j;
        this.c = timeUnit;
        this.d = sVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new DebounceTimedObserver(new e(rVar), this.b, this.c, this.d.a()));
    }
}

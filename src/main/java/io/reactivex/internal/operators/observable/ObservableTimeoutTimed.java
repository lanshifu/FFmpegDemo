package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimeoutTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final s d;
    final p<? extends T> e;

    interface b {
        void onTimeout(long j);
    }

    static final class c implements Runnable {
        final b a;
        final long b;

        c(long j, b bVar) {
            this.b = j;
            this.a = bVar;
        }

        public void run() {
            this.a.onTimeout(this.b);
        }
    }

    static final class TimeoutFallbackObserver<T> extends AtomicReference<io.reactivex.disposables.b> implements io.reactivex.disposables.b, b, r<T> {
        private static final long serialVersionUID = 3764492702657003550L;
        final r<? super T> downstream;
        p<? extends T> fallback;
        final AtomicLong index = new AtomicLong();
        final SequentialDisposable task = new SequentialDisposable();
        final long timeout;
        final TimeUnit unit;
        final AtomicReference<io.reactivex.disposables.b> upstream = new AtomicReference();
        final io.reactivex.s.c worker;

        TimeoutFallbackObserver(r<? super T> rVar, long j, TimeUnit timeUnit, io.reactivex.s.c cVar, p<? extends T> pVar) {
            this.downstream = rVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
            this.fallback = pVar;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        public void onNext(T t) {
            long j = this.index.get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (this.index.compareAndSet(j, j2)) {
                    ((io.reactivex.disposables.b) this.task.get()).dispose();
                    this.downstream.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void startTimeout(long j) {
            this.task.replace(this.worker.a(new c(j, this), this.timeout, this.unit));
        }

        public void onError(Throwable th) {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                this.worker.dispose();
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this.upstream);
                p pVar = this.fallback;
                this.fallback = null;
                pVar.subscribe(new a(this.downstream, this));
                this.worker.dispose();
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this);
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((io.reactivex.disposables.b) get());
        }
    }

    static final class TimeoutObserver<T> extends AtomicLong implements io.reactivex.disposables.b, b, r<T> {
        private static final long serialVersionUID = 3764492702657003550L;
        final r<? super T> downstream;
        final SequentialDisposable task = new SequentialDisposable();
        final long timeout;
        final TimeUnit unit;
        final AtomicReference<io.reactivex.disposables.b> upstream = new AtomicReference();
        final io.reactivex.s.c worker;

        TimeoutObserver(r<? super T> rVar, long j, TimeUnit timeUnit, io.reactivex.s.c cVar) {
            this.downstream = rVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        public void onNext(T t) {
            long j = get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    ((io.reactivex.disposables.b) this.task.get()).dispose();
                    this.downstream.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void startTimeout(long j) {
            this.task.replace(this.worker.a(new c(j, this), this.timeout, this.unit));
        }

        public void onError(Throwable th) {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                this.worker.dispose();
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        public void onTimeout(long j) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this.upstream);
                this.downstream.onError(new TimeoutException(ExceptionHelper.a(this.timeout, this.unit)));
                this.worker.dispose();
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((io.reactivex.disposables.b) this.upstream.get());
        }
    }

    static final class a<T> implements r<T> {
        final r<? super T> a;
        final AtomicReference<io.reactivex.disposables.b> b;

        a(r<? super T> rVar, AtomicReference<io.reactivex.disposables.b> atomicReference) {
            this.a = rVar;
            this.b = atomicReference;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.replace(this.b, bVar);
        }

        public void onNext(T t) {
            this.a.onNext(t);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    public ObservableTimeoutTimed(k<T> kVar, long j, TimeUnit timeUnit, s sVar, p<? extends T> pVar) {
        super(kVar);
        this.b = j;
        this.c = timeUnit;
        this.d = sVar;
        this.e = pVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        if (this.e == null) {
            TimeoutObserver timeoutObserver = new TimeoutObserver(rVar, this.b, this.c, this.d.a());
            rVar.onSubscribe(timeoutObserver);
            timeoutObserver.startTimeout(0);
            this.a.subscribe(timeoutObserver);
            return;
        }
        TimeoutFallbackObserver timeoutFallbackObserver = new TimeoutFallbackObserver(rVar, this.b, this.c, this.d.a(), this.e);
        rVar.onSubscribe(timeoutFallbackObserver);
        timeoutFallbackObserver.startTimeout(0);
        this.a.subscribe(timeoutFallbackObserver);
    }
}

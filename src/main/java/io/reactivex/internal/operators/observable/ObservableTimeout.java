package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimeout<T, U, V> extends a<T, T> {
    final p<U> b;
    final wm<? super T, ? extends p<V>> c;
    final p<? extends T> d;

    static final class TimeoutConsumer extends AtomicReference<b> implements b, r<Object> {
        private static final long serialVersionUID = 8708641127342403073L;
        final long idx;
        final a parent;

        TimeoutConsumer(long j, a aVar) {
            this.idx = j;
            this.parent = aVar;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        public void onNext(Object obj) {
            b bVar = (b) get();
            if (bVar != DisposableHelper.DISPOSED) {
                bVar.dispose();
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeout(this.idx);
            }
        }

        public void onError(Throwable th) {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeoutError(this.idx, th);
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeout(this.idx);
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }
    }

    interface a extends b {
        void onTimeoutError(long j, Throwable th);
    }

    static final class TimeoutFallbackObserver<T> extends AtomicReference<b> implements b, a, r<T> {
        private static final long serialVersionUID = -7508389464265974549L;
        final r<? super T> downstream;
        p<? extends T> fallback;
        final AtomicLong index;
        final wm<? super T, ? extends p<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<b> upstream;

        TimeoutFallbackObserver(r<? super T> rVar, wm<? super T, ? extends p<?>> wmVar, p<? extends T> pVar) {
            this.downstream = rVar;
            this.itemTimeoutIndicator = wmVar;
            this.fallback = pVar;
            this.index = new AtomicLong();
            this.upstream = new AtomicReference();
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        public void onNext(T t) {
            long j = this.index.get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (this.index.compareAndSet(j, j2)) {
                    b bVar = (b) this.task.get();
                    if (bVar != null) {
                        bVar.dispose();
                    }
                    this.downstream.onNext(t);
                    try {
                        p pVar = (p) io.reactivex.internal.functions.a.a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null ObservableSource.");
                        TimeoutConsumer timeoutConsumer = new TimeoutConsumer(j2, this);
                        if (this.task.replace(timeoutConsumer)) {
                            pVar.subscribe(timeoutConsumer);
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        ((b) this.upstream.get()).dispose();
                        this.index.getAndSet(Long.MAX_VALUE);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void startFirstTimeout(p<?> pVar) {
            if (pVar != null) {
                TimeoutConsumer timeoutConsumer = new TimeoutConsumer(0, this);
                if (this.task.replace(timeoutConsumer)) {
                    pVar.subscribe(timeoutConsumer);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                this.task.dispose();
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
                this.task.dispose();
            }
        }

        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this.upstream);
                p pVar = this.fallback;
                this.fallback = null;
                pVar.subscribe(new a(this.downstream, this));
            }
        }

        public void onTimeoutError(long j, Throwable th) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this);
                this.downstream.onError(th);
                return;
            }
            xk.a(th);
        }

        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this);
            this.task.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }
    }

    static final class TimeoutObserver<T> extends AtomicLong implements b, a, r<T> {
        private static final long serialVersionUID = 3764492702657003550L;
        final r<? super T> downstream;
        final wm<? super T, ? extends p<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<b> upstream = new AtomicReference();

        TimeoutObserver(r<? super T> rVar, wm<? super T, ? extends p<?>> wmVar) {
            this.downstream = rVar;
            this.itemTimeoutIndicator = wmVar;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        public void onNext(T t) {
            long j = get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    b bVar = (b) this.task.get();
                    if (bVar != null) {
                        bVar.dispose();
                    }
                    this.downstream.onNext(t);
                    try {
                        p pVar = (p) io.reactivex.internal.functions.a.a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null ObservableSource.");
                        TimeoutConsumer timeoutConsumer = new TimeoutConsumer(j2, this);
                        if (this.task.replace(timeoutConsumer)) {
                            pVar.subscribe(timeoutConsumer);
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        ((b) this.upstream.get()).dispose();
                        getAndSet(Long.MAX_VALUE);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void startFirstTimeout(p<?> pVar) {
            if (pVar != null) {
                TimeoutConsumer timeoutConsumer = new TimeoutConsumer(0, this);
                if (this.task.replace(timeoutConsumer)) {
                    pVar.subscribe(timeoutConsumer);
                }
            }
        }

        public void onError(Throwable th) {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
            }
        }

        public void onTimeout(long j) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this.upstream);
                this.downstream.onError(new TimeoutException());
            }
        }

        public void onTimeoutError(long j, Throwable th) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this.upstream);
                this.downstream.onError(th);
                return;
            }
            xk.a(th);
        }

        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            this.task.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) this.upstream.get());
        }
    }

    public ObservableTimeout(k<T> kVar, p<U> pVar, wm<? super T, ? extends p<V>> wmVar, p<? extends T> pVar2) {
        super(kVar);
        this.b = pVar;
        this.c = wmVar;
        this.d = pVar2;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        if (this.d == null) {
            TimeoutObserver timeoutObserver = new TimeoutObserver(rVar, this.c);
            rVar.onSubscribe(timeoutObserver);
            timeoutObserver.startFirstTimeout(this.b);
            this.a.subscribe(timeoutObserver);
            return;
        }
        TimeoutFallbackObserver timeoutFallbackObserver = new TimeoutFallbackObserver(rVar, this.c, this.d);
        rVar.onSubscribe(timeoutFallbackObserver);
        timeoutFallbackObserver.startFirstTimeout(this.b);
        this.a.subscribe(timeoutFallbackObserver);
    }
}

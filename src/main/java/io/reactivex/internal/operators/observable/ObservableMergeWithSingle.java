package io.reactivex.internal.operators.observable;

import defpackage.xc;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.k;
import io.reactivex.r;
import io.reactivex.u;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableMergeWithSingle<T> extends a<T, T> {
    final v<? extends T> b;

    static final class MergeWithObserver<T> extends AtomicInteger implements b, r<T> {
        static final int OTHER_STATE_CONSUMED_OR_EMPTY = 2;
        static final int OTHER_STATE_HAS_VALUE = 1;
        private static final long serialVersionUID = -4592979584110982903L;
        volatile boolean disposed;
        final r<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final AtomicReference<b> mainDisposable = new AtomicReference();
        volatile boolean mainDone;
        final OtherObserver<T> otherObserver = new OtherObserver(this);
        volatile int otherState;
        volatile xc<T> queue;
        T singleItem;

        static final class OtherObserver<T> extends AtomicReference<b> implements u<T> {
            private static final long serialVersionUID = -2935427570954647017L;
            final MergeWithObserver<T> parent;

            OtherObserver(MergeWithObserver<T> mergeWithObserver) {
                this.parent = mergeWithObserver;
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            public void onSuccess(T t) {
                this.parent.otherSuccess(t);
            }

            public void onError(Throwable th) {
                this.parent.otherError(th);
            }
        }

        MergeWithObserver(r<? super T> rVar) {
            this.downstream = rVar;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.mainDisposable, bVar);
        }

        public void onNext(T t) {
            if (compareAndSet(0, 1)) {
                this.downstream.onNext(t);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            getOrCreateQueue().offer(t);
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                DisposableHelper.dispose(this.mainDisposable);
                drain();
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            this.mainDone = true;
            drain();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) this.mainDisposable.get());
        }

        public void dispose() {
            this.disposed = true;
            DisposableHelper.dispose(this.mainDisposable);
            DisposableHelper.dispose(this.otherObserver);
            if (getAndIncrement() == 0) {
                this.queue = null;
                this.singleItem = null;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void otherSuccess(T t) {
            if (compareAndSet(0, 1)) {
                this.downstream.onNext(t);
                this.otherState = 2;
            } else {
                this.singleItem = t;
                this.otherState = 1;
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        /* Access modifiers changed, original: 0000 */
        public void otherError(Throwable th) {
            if (this.error.addThrowable(th)) {
                DisposableHelper.dispose(this.mainDisposable);
                drain();
                return;
            }
            xk.a(th);
        }

        /* Access modifiers changed, original: 0000 */
        public xc<T> getOrCreateQueue() {
            xc<T> xcVar = this.queue;
            if (xcVar != null) {
                return xcVar;
            }
            a aVar = new a(k.bufferSize());
            this.queue = aVar;
            return aVar;
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void drainLoop() {
            r rVar = this.downstream;
            int i = 1;
            while (!this.disposed) {
                if (this.error.get() != null) {
                    this.singleItem = null;
                    this.queue = null;
                    rVar.onError(this.error.terminate());
                    return;
                }
                int i2 = this.otherState;
                if (i2 == 1) {
                    Object obj = this.singleItem;
                    this.singleItem = null;
                    this.otherState = 2;
                    rVar.onNext(obj);
                    i2 = 2;
                }
                boolean z = this.mainDone;
                xc xcVar = this.queue;
                Object poll = xcVar != null ? xcVar.poll() : null;
                Object obj2 = poll == null ? 1 : null;
                if (z && obj2 != null && i2 == 2) {
                    this.queue = null;
                    rVar.onComplete();
                    return;
                } else if (obj2 != null) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    rVar.onNext(poll);
                }
            }
            this.singleItem = null;
            this.queue = null;
        }
    }

    public ObservableMergeWithSingle(k<T> kVar, v<? extends T> vVar) {
        super(kVar);
        this.b = vVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        MergeWithObserver mergeWithObserver = new MergeWithObserver(rVar);
        rVar.onSubscribe(mergeWithObserver);
        this.a.subscribe(mergeWithObserver);
        this.b.a(mergeWithObserver.otherObserver);
    }
}

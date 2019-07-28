package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.h;
import io.reactivex.i;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableFlatMapMaybe<T, R> extends a<T, R> {
    final wm<? super T, ? extends i<? extends R>> b;
    final boolean c;

    static final class FlatMapMaybeObserver<T, R> extends AtomicInteger implements b, r<T> {
        private static final long serialVersionUID = 8600231336733376951L;
        final AtomicInteger active = new AtomicInteger(1);
        volatile boolean cancelled;
        final boolean delayErrors;
        final r<? super R> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final wm<? super T, ? extends i<? extends R>> mapper;
        final AtomicReference<a<R>> queue = new AtomicReference();
        final io.reactivex.disposables.a set = new io.reactivex.disposables.a();
        b upstream;

        final class InnerObserver extends AtomicReference<b> implements b, h<R> {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            public void onSuccess(R r) {
                FlatMapMaybeObserver.this.innerSuccess(this, r);
            }

            public void onError(Throwable th) {
                FlatMapMaybeObserver.this.innerError(this, th);
            }

            public void onComplete() {
                FlatMapMaybeObserver.this.innerComplete(this);
            }

            public boolean isDisposed() {
                return DisposableHelper.isDisposed((b) get());
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        FlatMapMaybeObserver(r<? super R> rVar, wm<? super T, ? extends i<? extends R>> wmVar, boolean z) {
            this.downstream = rVar;
            this.mapper = wmVar;
            this.delayErrors = z;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            try {
                i iVar = (i) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                this.active.getAndIncrement();
                b innerObserver = new InnerObserver();
                if (!this.cancelled && this.set.a(innerObserver)) {
                    iVar.a(innerObserver);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            this.active.decrementAndGet();
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.set.dispose();
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void innerSuccess(InnerObserver innerObserver, R r) {
            this.set.c(innerObserver);
            if (get() == 0) {
                int i = 1;
                if (compareAndSet(0, 1)) {
                    this.downstream.onNext(r);
                    if (this.active.decrementAndGet() != 0) {
                        i = 0;
                    }
                    a aVar = (a) this.queue.get();
                    if (i == 0 || !(aVar == null || aVar.isEmpty())) {
                        if (decrementAndGet() == 0) {
                            return;
                        }
                        drainLoop();
                    }
                    Throwable terminate = this.errors.terminate();
                    if (terminate != null) {
                        this.downstream.onError(terminate);
                    } else {
                        this.downstream.onComplete();
                    }
                    return;
                }
            }
            a orCreateQueue = getOrCreateQueue();
            synchronized (orCreateQueue) {
                orCreateQueue.offer(r);
            }
            this.active.decrementAndGet();
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        /* Access modifiers changed, original: 0000 */
        public a<R> getOrCreateQueue() {
            a aVar;
            do {
                aVar = (a) this.queue.get();
                if (aVar != null) {
                    return aVar;
                }
                aVar = new a(k.bufferSize());
            } while (!this.queue.compareAndSet(null, aVar));
            return aVar;
        }

        /* Access modifiers changed, original: 0000 */
        public void innerError(InnerObserver innerObserver, Throwable th) {
            this.set.c(innerObserver);
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.upstream.dispose();
                    this.set.dispose();
                }
                this.active.decrementAndGet();
                drain();
                return;
            }
            xk.a(th);
        }

        /* Access modifiers changed, original: 0000 */
        public void innerComplete(InnerObserver innerObserver) {
            this.set.c(innerObserver);
            if (get() == 0) {
                int i = 1;
                if (compareAndSet(0, 1)) {
                    if (this.active.decrementAndGet() != 0) {
                        i = 0;
                    }
                    a aVar = (a) this.queue.get();
                    if (i != 0 && (aVar == null || aVar.isEmpty())) {
                        Throwable terminate = this.errors.terminate();
                        if (terminate != null) {
                            this.downstream.onError(terminate);
                        } else {
                            this.downstream.onComplete();
                        }
                        return;
                    } else if (decrementAndGet() != 0) {
                        drainLoop();
                    } else {
                        return;
                    }
                }
            }
            this.active.decrementAndGet();
            drain();
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void clear() {
            a aVar = (a) this.queue.get();
            if (aVar != null) {
                aVar.clear();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void drainLoop() {
            r rVar = this.downstream;
            AtomicInteger atomicInteger = this.active;
            AtomicReference atomicReference = this.queue;
            int i = 1;
            while (!this.cancelled) {
                Throwable terminate;
                if (this.delayErrors || ((Throwable) this.errors.get()) == null) {
                    Object obj = null;
                    Object obj2 = atomicInteger.get() == 0 ? 1 : null;
                    a aVar = (a) atomicReference.get();
                    Object poll = aVar != null ? aVar.poll() : null;
                    if (poll == null) {
                        obj = 1;
                    }
                    if (obj2 != null && obj != null) {
                        terminate = this.errors.terminate();
                        if (terminate != null) {
                            rVar.onError(terminate);
                        } else {
                            rVar.onComplete();
                        }
                        return;
                    } else if (obj != null) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        rVar.onNext(poll);
                    }
                } else {
                    terminate = this.errors.terminate();
                    clear();
                    rVar.onError(terminate);
                    return;
                }
            }
            clear();
        }
    }

    public ObservableFlatMapMaybe(p<T> pVar, wm<? super T, ? extends i<? extends R>> wmVar, boolean z) {
        super(pVar);
        this.b = wmVar;
        this.c = z;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super R> rVar) {
        this.a.subscribe(new FlatMapMaybeObserver(rVar, this.b, this.c));
    }
}

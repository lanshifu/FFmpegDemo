package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.wy;
import defpackage.xd;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatMap<T, U> extends a<T, U> {
    final wm<? super T, ? extends p<? extends U>> b;
    final int c;
    final ErrorMode d;

    static final class ConcatMapDelayErrorObserver<T, R> extends AtomicInteger implements b, r<T> {
        private static final long serialVersionUID = -6951100001833242599L;
        volatile boolean active;
        final int bufferSize;
        volatile boolean cancelled;
        volatile boolean done;
        final r<? super R> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final wm<? super T, ? extends p<? extends R>> mapper;
        final DelayErrorInnerObserver<R> observer;
        xd<T> queue;
        int sourceMode;
        final boolean tillTheEnd;
        b upstream;

        static final class DelayErrorInnerObserver<R> extends AtomicReference<b> implements r<R> {
            private static final long serialVersionUID = 2620149119579502636L;
            final r<? super R> downstream;
            final ConcatMapDelayErrorObserver<?, R> parent;

            DelayErrorInnerObserver(r<? super R> rVar, ConcatMapDelayErrorObserver<?, R> concatMapDelayErrorObserver) {
                this.downstream = rVar;
                this.parent = concatMapDelayErrorObserver;
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.replace(this, bVar);
            }

            public void onNext(R r) {
                this.downstream.onNext(r);
            }

            public void onError(Throwable th) {
                ConcatMapDelayErrorObserver concatMapDelayErrorObserver = this.parent;
                if (concatMapDelayErrorObserver.error.addThrowable(th)) {
                    if (!concatMapDelayErrorObserver.tillTheEnd) {
                        concatMapDelayErrorObserver.upstream.dispose();
                    }
                    concatMapDelayErrorObserver.active = false;
                    concatMapDelayErrorObserver.drain();
                    return;
                }
                xk.a(th);
            }

            public void onComplete() {
                ConcatMapDelayErrorObserver concatMapDelayErrorObserver = this.parent;
                concatMapDelayErrorObserver.active = false;
                concatMapDelayErrorObserver.drain();
            }

            /* Access modifiers changed, original: 0000 */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        ConcatMapDelayErrorObserver(r<? super R> rVar, wm<? super T, ? extends p<? extends R>> wmVar, int i, boolean z) {
            this.downstream = rVar;
            this.mapper = wmVar;
            this.bufferSize = i;
            this.tillTheEnd = z;
            this.observer = new DelayErrorInnerObserver(rVar, this);
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                if (bVar instanceof wy) {
                    wy wyVar = (wy) bVar;
                    int requestFusion = wyVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = wyVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = wyVar;
                        this.downstream.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new a(this.bufferSize);
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.sourceMode == 0) {
                this.queue.offer(t);
            }
            drain();
        }

        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.observer.dispose();
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                r rVar = this.downstream;
                xd xdVar = this.queue;
                AtomicThrowable atomicThrowable = this.error;
                while (true) {
                    if (!this.active) {
                        if (this.cancelled) {
                            xdVar.clear();
                            return;
                        } else if (this.tillTheEnd || ((Throwable) atomicThrowable.get()) == null) {
                            boolean z = this.done;
                            Throwable terminate;
                            try {
                                Object poll = xdVar.poll();
                                Object obj = poll == null ? 1 : null;
                                if (z && obj != null) {
                                    this.cancelled = true;
                                    terminate = atomicThrowable.terminate();
                                    if (terminate != null) {
                                        rVar.onError(terminate);
                                    } else {
                                        rVar.onComplete();
                                    }
                                    return;
                                } else if (obj == null) {
                                    try {
                                        p pVar = (p) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null ObservableSource");
                                        if (pVar instanceof Callable) {
                                            try {
                                                Object call = ((Callable) pVar).call();
                                                if (!(call == null || this.cancelled)) {
                                                    rVar.onNext(call);
                                                }
                                            } catch (Throwable th) {
                                                io.reactivex.exceptions.a.b(th);
                                                atomicThrowable.addThrowable(th);
                                            }
                                        } else {
                                            this.active = true;
                                            pVar.subscribe(this.observer);
                                        }
                                    } catch (Throwable th2) {
                                        io.reactivex.exceptions.a.b(th2);
                                        this.cancelled = true;
                                        this.upstream.dispose();
                                        xdVar.clear();
                                        atomicThrowable.addThrowable(th2);
                                        rVar.onError(atomicThrowable.terminate());
                                        return;
                                    }
                                }
                            } catch (Throwable terminate2) {
                                io.reactivex.exceptions.a.b(terminate2);
                                this.cancelled = true;
                                this.upstream.dispose();
                                atomicThrowable.addThrowable(terminate2);
                                rVar.onError(atomicThrowable.terminate());
                                return;
                            }
                        } else {
                            xdVar.clear();
                            this.cancelled = true;
                            rVar.onError(atomicThrowable.terminate());
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class SourceObserver<T, U> extends AtomicInteger implements b, r<T> {
        private static final long serialVersionUID = 8828587559905699186L;
        volatile boolean active;
        final int bufferSize;
        volatile boolean disposed;
        volatile boolean done;
        final r<? super U> downstream;
        int fusionMode;
        final InnerObserver<U> inner;
        final wm<? super T, ? extends p<? extends U>> mapper;
        xd<T> queue;
        b upstream;

        static final class InnerObserver<U> extends AtomicReference<b> implements r<U> {
            private static final long serialVersionUID = -7449079488798789337L;
            final r<? super U> downstream;
            final SourceObserver<?, ?> parent;

            InnerObserver(r<? super U> rVar, SourceObserver<?, ?> sourceObserver) {
                this.downstream = rVar;
                this.parent = sourceObserver;
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.set(this, bVar);
            }

            public void onNext(U u) {
                this.downstream.onNext(u);
            }

            public void onError(Throwable th) {
                this.parent.dispose();
                this.downstream.onError(th);
            }

            public void onComplete() {
                this.parent.innerComplete();
            }

            /* Access modifiers changed, original: 0000 */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        SourceObserver(r<? super U> rVar, wm<? super T, ? extends p<? extends U>> wmVar, int i) {
            this.downstream = rVar;
            this.mapper = wmVar;
            this.bufferSize = i;
            this.inner = new InnerObserver(rVar, this);
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                if (bVar instanceof wy) {
                    wy wyVar = (wy) bVar;
                    int requestFusion = wyVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = wyVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = wyVar;
                        this.downstream.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new a(this.bufferSize);
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.fusionMode == 0) {
                    this.queue.offer(t);
                }
                drain();
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                xk.a(th);
                return;
            }
            this.done = true;
            dispose();
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void innerComplete() {
            this.active = false;
            drain();
        }

        public boolean isDisposed() {
            return this.disposed;
        }

        public void dispose() {
            this.disposed = true;
            this.inner.dispose();
            this.upstream.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                while (!this.disposed) {
                    if (!this.active) {
                        boolean z = this.done;
                        try {
                            Object poll = this.queue.poll();
                            Object obj = poll == null ? 1 : null;
                            if (z && obj != null) {
                                this.disposed = true;
                                this.downstream.onComplete();
                                return;
                            } else if (obj == null) {
                                try {
                                    p pVar = (p) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null ObservableSource");
                                    this.active = true;
                                    pVar.subscribe(this.inner);
                                } catch (Throwable th) {
                                    io.reactivex.exceptions.a.b(th);
                                    dispose();
                                    this.queue.clear();
                                    this.downstream.onError(th);
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            io.reactivex.exceptions.a.b(th2);
                            dispose();
                            this.queue.clear();
                            this.downstream.onError(th2);
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                this.queue.clear();
            }
        }
    }

    public ObservableConcatMap(p<T> pVar, wm<? super T, ? extends p<? extends U>> wmVar, int i, ErrorMode errorMode) {
        super(pVar);
        this.b = wmVar;
        this.d = errorMode;
        this.c = Math.max(8, i);
    }

    public void subscribeActual(r<? super U> rVar) {
        if (!ObservableScalarXMap.a(this.a, rVar, this.b)) {
            if (this.d == ErrorMode.IMMEDIATE) {
                this.a.subscribe(new SourceObserver(new e(rVar), this.b, this.c));
            } else {
                this.a.subscribe(new ConcatMapDelayErrorObserver(rVar, this.b, this.c, this.d == ErrorMode.END));
            }
        }
    }
}

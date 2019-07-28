package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.wy;
import defpackage.xd;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.InnerQueuedObserver;
import io.reactivex.internal.observers.i;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.p;
import io.reactivex.r;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableConcatMapEager<T, R> extends a<T, R> {
    final wm<? super T, ? extends p<? extends R>> b;
    final ErrorMode c;
    final int d;
    final int e;

    static final class ConcatMapEagerMainObserver<T, R> extends AtomicInteger implements b, i<R>, r<T> {
        private static final long serialVersionUID = 8080567949447303262L;
        int activeCount;
        volatile boolean cancelled;
        InnerQueuedObserver<R> current;
        volatile boolean done;
        final r<? super R> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final ErrorMode errorMode;
        final wm<? super T, ? extends p<? extends R>> mapper;
        final int maxConcurrency;
        final ArrayDeque<InnerQueuedObserver<R>> observers = new ArrayDeque();
        final int prefetch;
        xd<T> queue;
        int sourceMode;
        b upstream;

        ConcatMapEagerMainObserver(r<? super R> rVar, wm<? super T, ? extends p<? extends R>> wmVar, int i, int i2, ErrorMode errorMode) {
            this.downstream = rVar;
            this.mapper = wmVar;
            this.maxConcurrency = i;
            this.prefetch = i2;
            this.errorMode = errorMode;
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
                this.queue = new a(this.prefetch);
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

        public void dispose() {
            this.cancelled = true;
            if (getAndIncrement() == 0) {
                this.queue.clear();
                disposeAll();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void disposeAll() {
            InnerQueuedObserver innerQueuedObserver = this.current;
            if (innerQueuedObserver != null) {
                innerQueuedObserver.dispose();
            }
            while (true) {
                innerQueuedObserver = (InnerQueuedObserver) this.observers.poll();
                if (innerQueuedObserver != null) {
                    innerQueuedObserver.dispose();
                } else {
                    return;
                }
            }
        }

        public void innerNext(InnerQueuedObserver<R> innerQueuedObserver, R r) {
            innerQueuedObserver.queue().offer(r);
            drain();
        }

        public void innerError(InnerQueuedObserver<R> innerQueuedObserver, Throwable th) {
            if (this.error.addThrowable(th)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.upstream.dispose();
                }
                innerQueuedObserver.setDone();
                drain();
                return;
            }
            xk.a(th);
        }

        public void innerComplete(InnerQueuedObserver<R> innerQueuedObserver) {
            innerQueuedObserver.setDone();
            drain();
        }

        public void drain() {
            if (getAndIncrement() == 0) {
                xd xdVar = this.queue;
                ArrayDeque arrayDeque = this.observers;
                r rVar = this.downstream;
                ErrorMode errorMode = this.errorMode;
                int i = 1;
                while (true) {
                    InnerQueuedObserver innerQueuedObserver;
                    int i2 = this.activeCount;
                    while (i2 != this.maxConcurrency) {
                        if (this.cancelled) {
                            xdVar.clear();
                            disposeAll();
                            return;
                        } else if (errorMode != ErrorMode.IMMEDIATE || ((Throwable) this.error.get()) == null) {
                            try {
                                Object poll = xdVar.poll();
                                if (poll == null) {
                                    break;
                                }
                                p pVar = (p) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null ObservableSource");
                                innerQueuedObserver = new InnerQueuedObserver(this, this.prefetch);
                                arrayDeque.offer(innerQueuedObserver);
                                pVar.subscribe(innerQueuedObserver);
                                i2++;
                            } catch (Throwable th) {
                                io.reactivex.exceptions.a.b(th);
                                this.upstream.dispose();
                                xdVar.clear();
                                disposeAll();
                                this.error.addThrowable(th);
                                rVar.onError(this.error.terminate());
                                return;
                            }
                        } else {
                            xdVar.clear();
                            disposeAll();
                            rVar.onError(this.error.terminate());
                            return;
                        }
                    }
                    this.activeCount = i2;
                    if (this.cancelled) {
                        xdVar.clear();
                        disposeAll();
                        return;
                    } else if (errorMode != ErrorMode.IMMEDIATE || ((Throwable) this.error.get()) == null) {
                        InnerQueuedObserver innerQueuedObserver2 = this.current;
                        if (innerQueuedObserver2 == null) {
                            if (errorMode != ErrorMode.BOUNDARY || ((Throwable) this.error.get()) == null) {
                                boolean z = this.done;
                                innerQueuedObserver = (InnerQueuedObserver) arrayDeque.poll();
                                Object obj = innerQueuedObserver == null ? 1 : null;
                                if (!z || obj == null) {
                                    if (obj == null) {
                                        this.current = innerQueuedObserver;
                                    }
                                    innerQueuedObserver2 = innerQueuedObserver;
                                } else {
                                    if (((Throwable) this.error.get()) != null) {
                                        xdVar.clear();
                                        disposeAll();
                                        rVar.onError(this.error.terminate());
                                    } else {
                                        rVar.onComplete();
                                    }
                                    return;
                                }
                            }
                            xdVar.clear();
                            disposeAll();
                            rVar.onError(this.error.terminate());
                            return;
                        }
                        if (innerQueuedObserver2 != null) {
                            xd queue = innerQueuedObserver2.queue();
                            while (!this.cancelled) {
                                boolean isDone = innerQueuedObserver2.isDone();
                                if (errorMode != ErrorMode.IMMEDIATE || ((Throwable) this.error.get()) == null) {
                                    try {
                                        Object poll2 = queue.poll();
                                        Object obj2 = poll2 == null ? 1 : null;
                                        if (isDone && obj2 != null) {
                                            this.current = null;
                                            this.activeCount--;
                                        } else if (obj2 == null) {
                                            rVar.onNext(poll2);
                                        }
                                    } catch (Throwable th2) {
                                        io.reactivex.exceptions.a.b(th2);
                                        this.error.addThrowable(th2);
                                        this.current = null;
                                        this.activeCount--;
                                    }
                                } else {
                                    xdVar.clear();
                                    disposeAll();
                                    rVar.onError(this.error.terminate());
                                    return;
                                }
                            }
                            xdVar.clear();
                            disposeAll();
                            return;
                        }
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        xdVar.clear();
                        disposeAll();
                        rVar.onError(this.error.terminate());
                        return;
                    }
                }
            }
        }
    }

    public ObservableConcatMapEager(p<T> pVar, wm<? super T, ? extends p<? extends R>> wmVar, ErrorMode errorMode, int i, int i2) {
        super(pVar);
        this.b = wmVar;
        this.c = errorMode;
        this.d = i;
        this.e = i2;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super R> rVar) {
        this.a.subscribe(new ConcatMapEagerMainObserver(rVar, this.b, this.d, this.e, this.c));
    }
}

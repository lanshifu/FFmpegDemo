package io.reactivex.internal.operators.mixed;

import defpackage.wm;
import defpackage.wy;
import defpackage.xd;
import defpackage.xk;
import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatMapCompletable<T> extends a {
    final k<T> a;
    final wm<? super T, ? extends c> b;
    final ErrorMode c;
    final int d;

    static final class ConcatMapCompletableObserver<T> extends AtomicInteger implements b, r<T> {
        private static final long serialVersionUID = 3610901111000061034L;
        volatile boolean active;
        volatile boolean disposed;
        volatile boolean done;
        final io.reactivex.b downstream;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final ConcatMapInnerObserver inner = new ConcatMapInnerObserver(this);
        final wm<? super T, ? extends c> mapper;
        final int prefetch;
        xd<T> queue;
        b upstream;

        static final class ConcatMapInnerObserver extends AtomicReference<b> implements io.reactivex.b {
            private static final long serialVersionUID = 5638352172918776687L;
            final ConcatMapCompletableObserver<?> parent;

            ConcatMapInnerObserver(ConcatMapCompletableObserver<?> concatMapCompletableObserver) {
                this.parent = concatMapCompletableObserver;
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.replace(this, bVar);
            }

            public void onError(Throwable th) {
                this.parent.innerError(th);
            }

            public void onComplete() {
                this.parent.innerComplete();
            }

            /* Access modifiers changed, original: 0000 */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        ConcatMapCompletableObserver(io.reactivex.b bVar, wm<? super T, ? extends c> wmVar, ErrorMode errorMode, int i) {
            this.downstream = bVar;
            this.mapper = wmVar;
            this.errorMode = errorMode;
            this.prefetch = i;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                if (bVar instanceof wy) {
                    wy wyVar = (wy) bVar;
                    int requestFusion = wyVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.queue = wyVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.queue = wyVar;
                        this.downstream.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new io.reactivex.internal.queue.a(this.prefetch);
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (t != null) {
                this.queue.offer(t);
            }
            drain();
        }

        public void onError(Throwable th) {
            if (!this.errors.addThrowable(th)) {
                xk.a(th);
            } else if (this.errorMode == ErrorMode.IMMEDIATE) {
                this.disposed = true;
                this.inner.dispose();
                th = this.errors.terminate();
                if (th != ExceptionHelper.a) {
                    this.downstream.onError(th);
                }
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            } else {
                this.done = true;
                drain();
            }
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void dispose() {
            this.disposed = true;
            this.upstream.dispose();
            this.inner.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        public boolean isDisposed() {
            return this.disposed;
        }

        /* Access modifiers changed, original: 0000 */
        public void innerError(Throwable th) {
            if (!this.errors.addThrowable(th)) {
                xk.a(th);
            } else if (this.errorMode == ErrorMode.IMMEDIATE) {
                this.disposed = true;
                this.upstream.dispose();
                th = this.errors.terminate();
                if (th != ExceptionHelper.a) {
                    this.downstream.onError(th);
                }
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            } else {
                this.active = false;
                drain();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void innerComplete() {
            this.active = false;
            drain();
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                AtomicThrowable atomicThrowable = this.errors;
                ErrorMode errorMode = this.errorMode;
                while (!this.disposed) {
                    if (!this.active) {
                        if (errorMode != ErrorMode.BOUNDARY || atomicThrowable.get() == null) {
                            boolean z = this.done;
                            c cVar = null;
                            try {
                                Object poll = this.queue.poll();
                                if (poll != null) {
                                    cVar = (c) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null CompletableSource");
                                    poll = null;
                                } else {
                                    poll = 1;
                                }
                                if (z && poll != null) {
                                    this.disposed = true;
                                    Throwable terminate = atomicThrowable.terminate();
                                    if (terminate != null) {
                                        this.downstream.onError(terminate);
                                    } else {
                                        this.downstream.onComplete();
                                    }
                                    return;
                                } else if (poll == null) {
                                    this.active = true;
                                    cVar.a(this.inner);
                                }
                            } catch (Throwable th) {
                                io.reactivex.exceptions.a.b(th);
                                this.disposed = true;
                                this.queue.clear();
                                this.upstream.dispose();
                                atomicThrowable.addThrowable(th);
                                this.downstream.onError(atomicThrowable.terminate());
                                return;
                            }
                        }
                        this.disposed = true;
                        this.queue.clear();
                        this.downstream.onError(atomicThrowable.terminate());
                        return;
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                this.queue.clear();
            }
        }
    }

    public ObservableConcatMapCompletable(k<T> kVar, wm<? super T, ? extends c> wmVar, ErrorMode errorMode, int i) {
        this.a = kVar;
        this.b = wmVar;
        this.c = errorMode;
        this.d = i;
    }

    /* Access modifiers changed, original: protected */
    public void b(io.reactivex.b bVar) {
        if (!a.a(this.a, this.b, bVar)) {
            this.a.subscribe(new ConcatMapCompletableObserver(bVar, this.b, this.c, this.d));
        }
    }
}

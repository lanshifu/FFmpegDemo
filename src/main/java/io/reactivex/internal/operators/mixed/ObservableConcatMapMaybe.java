package io.reactivex.internal.operators.mixed;

import defpackage.wm;
import defpackage.xc;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.h;
import io.reactivex.i;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatMapMaybe<T, R> extends k<R> {
    final k<T> a;
    final wm<? super T, ? extends i<? extends R>> b;
    final ErrorMode c;
    final int d;

    static final class ConcatMapMaybeMainObserver<T, R> extends AtomicInteger implements b, r<T> {
        static final int STATE_ACTIVE = 1;
        static final int STATE_INACTIVE = 0;
        static final int STATE_RESULT_VALUE = 2;
        private static final long serialVersionUID = -9140123220065488293L;
        volatile boolean cancelled;
        volatile boolean done;
        final r<? super R> downstream;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final ConcatMapMaybeObserver<R> inner = new ConcatMapMaybeObserver(this);
        R item;
        final wm<? super T, ? extends i<? extends R>> mapper;
        final xc<T> queue;
        volatile int state;
        b upstream;

        static final class ConcatMapMaybeObserver<R> extends AtomicReference<b> implements h<R> {
            private static final long serialVersionUID = -3051469169682093892L;
            final ConcatMapMaybeMainObserver<?, R> parent;

            ConcatMapMaybeObserver(ConcatMapMaybeMainObserver<?, R> concatMapMaybeMainObserver) {
                this.parent = concatMapMaybeMainObserver;
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.replace(this, bVar);
            }

            public void onSuccess(R r) {
                this.parent.innerSuccess(r);
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

        ConcatMapMaybeMainObserver(r<? super R> rVar, wm<? super T, ? extends i<? extends R>> wmVar, int i, ErrorMode errorMode) {
            this.downstream = rVar;
            this.mapper = wmVar;
            this.errorMode = errorMode;
            this.queue = new a(i);
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.inner.dispose();
                }
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
            this.upstream.dispose();
            this.inner.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
                this.item = null;
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void innerSuccess(R r) {
            this.item = r;
            this.state = 2;
            drain();
        }

        /* Access modifiers changed, original: 0000 */
        public void innerComplete() {
            this.state = 0;
            drain();
        }

        /* Access modifiers changed, original: 0000 */
        public void innerError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.dispose();
                }
                this.state = 0;
                drain();
                return;
            }
            xk.a(th);
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                r rVar = this.downstream;
                ErrorMode errorMode = this.errorMode;
                xc xcVar = this.queue;
                AtomicThrowable atomicThrowable = this.errors;
                int i = 1;
                while (true) {
                    if (this.cancelled) {
                        xcVar.clear();
                        this.item = null;
                    } else {
                        int i2 = this.state;
                        if (atomicThrowable.get() == null || !(errorMode == ErrorMode.IMMEDIATE || (errorMode == ErrorMode.BOUNDARY && i2 == 0))) {
                            int i3 = 0;
                            if (i2 == 0) {
                                boolean z = this.done;
                                Object poll = xcVar.poll();
                                if (poll == null) {
                                    i3 = 1;
                                }
                                Throwable terminate;
                                if (z && i3 != 0) {
                                    terminate = atomicThrowable.terminate();
                                    if (terminate == null) {
                                        rVar.onComplete();
                                    } else {
                                        rVar.onError(terminate);
                                    }
                                    return;
                                } else if (i3 == 0) {
                                    try {
                                        i iVar = (i) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null MaybeSource");
                                        this.state = 1;
                                        iVar.a(this.inner);
                                    } catch (Throwable terminate2) {
                                        io.reactivex.exceptions.a.b(terminate2);
                                        this.upstream.dispose();
                                        xcVar.clear();
                                        atomicThrowable.addThrowable(terminate2);
                                        rVar.onError(atomicThrowable.terminate());
                                        return;
                                    }
                                }
                            } else if (i2 == 2) {
                                Object obj = this.item;
                                this.item = null;
                                rVar.onNext(obj);
                                this.state = 0;
                            }
                        }
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
                xcVar.clear();
                this.item = null;
                rVar.onError(atomicThrowable.terminate());
            }
        }
    }

    public ObservableConcatMapMaybe(k<T> kVar, wm<? super T, ? extends i<? extends R>> wmVar, ErrorMode errorMode, int i) {
        this.a = kVar;
        this.b = wmVar;
        this.c = errorMode;
        this.d = i;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super R> rVar) {
        if (!a.a(this.a, this.b, (r) rVar)) {
            this.a.subscribe(new ConcatMapMaybeMainObserver(rVar, this.b, this.d, this.c));
        }
    }
}

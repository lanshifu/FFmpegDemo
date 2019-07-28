package io.reactivex.internal.operators.mixed;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.k;
import io.reactivex.r;
import io.reactivex.u;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSwitchMapSingle<T, R> extends k<R> {
    final k<T> a;
    final wm<? super T, ? extends v<? extends R>> b;
    final boolean c;

    static final class SwitchMapSingleMainObserver<T, R> extends AtomicInteger implements b, r<T> {
        static final SwitchMapSingleObserver<Object> INNER_DISPOSED = new SwitchMapSingleObserver(null);
        private static final long serialVersionUID = -5402190102429853762L;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final r<? super R> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicReference<SwitchMapSingleObserver<R>> inner = new AtomicReference();
        final wm<? super T, ? extends v<? extends R>> mapper;
        b upstream;

        static final class SwitchMapSingleObserver<R> extends AtomicReference<b> implements u<R> {
            private static final long serialVersionUID = 8042919737683345351L;
            volatile R item;
            final SwitchMapSingleMainObserver<?, R> parent;

            SwitchMapSingleObserver(SwitchMapSingleMainObserver<?, R> switchMapSingleMainObserver) {
                this.parent = switchMapSingleMainObserver;
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            public void onSuccess(R r) {
                this.item = r;
                this.parent.drain();
            }

            public void onError(Throwable th) {
                this.parent.innerError(this, th);
            }

            /* Access modifiers changed, original: 0000 */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        SwitchMapSingleMainObserver(r<? super R> rVar, wm<? super T, ? extends v<? extends R>> wmVar, boolean z) {
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
            SwitchMapSingleObserver switchMapSingleObserver = (SwitchMapSingleObserver) this.inner.get();
            if (switchMapSingleObserver != null) {
                switchMapSingleObserver.dispose();
            }
            try {
                v vVar = (v) a.a(this.mapper.apply(t), "The mapper returned a null SingleSource");
                switchMapSingleObserver = new SwitchMapSingleObserver(this);
                SwitchMapSingleObserver switchMapSingleObserver2;
                do {
                    switchMapSingleObserver2 = (SwitchMapSingleObserver) this.inner.get();
                    if (switchMapSingleObserver2 == INNER_DISPOSED) {
                        break;
                    }
                } while (!this.inner.compareAndSet(switchMapSingleObserver2, switchMapSingleObserver));
                vVar.a(switchMapSingleObserver);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.upstream.dispose();
                this.inner.getAndSet(INNER_DISPOSED);
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    disposeInner();
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

        /* Access modifiers changed, original: 0000 */
        public void disposeInner() {
            SwitchMapSingleObserver switchMapSingleObserver = (SwitchMapSingleObserver) this.inner.getAndSet(INNER_DISPOSED);
            if (switchMapSingleObserver != null && switchMapSingleObserver != INNER_DISPOSED) {
                switchMapSingleObserver.dispose();
            }
        }

        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            disposeInner();
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void innerError(SwitchMapSingleObserver<R> switchMapSingleObserver, Throwable th) {
            if (this.inner.compareAndSet(switchMapSingleObserver, null) && this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.upstream.dispose();
                    disposeInner();
                }
                drain();
                return;
            }
            xk.a(th);
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                r rVar = this.downstream;
                AtomicThrowable atomicThrowable = this.errors;
                AtomicReference atomicReference = this.inner;
                int i = 1;
                while (!this.cancelled) {
                    if (atomicThrowable.get() == null || this.delayErrors) {
                        boolean z = this.done;
                        SwitchMapSingleObserver switchMapSingleObserver = (SwitchMapSingleObserver) atomicReference.get();
                        Object obj = switchMapSingleObserver == null ? 1 : null;
                        if (z && obj != null) {
                            Throwable terminate = atomicThrowable.terminate();
                            if (terminate != null) {
                                rVar.onError(terminate);
                            } else {
                                rVar.onComplete();
                            }
                            return;
                        } else if (obj != null || switchMapSingleObserver.item == null) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else {
                            atomicReference.compareAndSet(switchMapSingleObserver, null);
                            rVar.onNext(switchMapSingleObserver.item);
                        }
                    } else {
                        rVar.onError(atomicThrowable.terminate());
                        return;
                    }
                }
            }
        }
    }

    public ObservableSwitchMapSingle(k<T> kVar, wm<? super T, ? extends v<? extends R>> wmVar, boolean z) {
        this.a = kVar;
        this.b = wmVar;
        this.c = z;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super R> rVar) {
        if (!a.b(this.a, this.b, rVar)) {
            this.a.subscribe(new SwitchMapSingleMainObserver(rVar, this.b, this.c));
        }
    }
}

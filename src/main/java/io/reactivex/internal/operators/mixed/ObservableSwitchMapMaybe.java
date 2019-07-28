package io.reactivex.internal.operators.mixed;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.h;
import io.reactivex.i;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSwitchMapMaybe<T, R> extends k<R> {
    final k<T> a;
    final wm<? super T, ? extends i<? extends R>> b;
    final boolean c;

    static final class SwitchMapMaybeMainObserver<T, R> extends AtomicInteger implements b, r<T> {
        static final SwitchMapMaybeObserver<Object> INNER_DISPOSED = new SwitchMapMaybeObserver(null);
        private static final long serialVersionUID = -5402190102429853762L;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final r<? super R> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicReference<SwitchMapMaybeObserver<R>> inner = new AtomicReference();
        final wm<? super T, ? extends i<? extends R>> mapper;
        b upstream;

        static final class SwitchMapMaybeObserver<R> extends AtomicReference<b> implements h<R> {
            private static final long serialVersionUID = 8042919737683345351L;
            volatile R item;
            final SwitchMapMaybeMainObserver<?, R> parent;

            SwitchMapMaybeObserver(SwitchMapMaybeMainObserver<?, R> switchMapMaybeMainObserver) {
                this.parent = switchMapMaybeMainObserver;
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

            public void onComplete() {
                this.parent.innerComplete(this);
            }

            /* Access modifiers changed, original: 0000 */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        SwitchMapMaybeMainObserver(r<? super R> rVar, wm<? super T, ? extends i<? extends R>> wmVar, boolean z) {
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
            SwitchMapMaybeObserver switchMapMaybeObserver = (SwitchMapMaybeObserver) this.inner.get();
            if (switchMapMaybeObserver != null) {
                switchMapMaybeObserver.dispose();
            }
            try {
                i iVar = (i) a.a(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                switchMapMaybeObserver = new SwitchMapMaybeObserver(this);
                SwitchMapMaybeObserver switchMapMaybeObserver2;
                do {
                    switchMapMaybeObserver2 = (SwitchMapMaybeObserver) this.inner.get();
                    if (switchMapMaybeObserver2 == INNER_DISPOSED) {
                        break;
                    }
                } while (!this.inner.compareAndSet(switchMapMaybeObserver2, switchMapMaybeObserver));
                iVar.a(switchMapMaybeObserver);
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
            SwitchMapMaybeObserver switchMapMaybeObserver = (SwitchMapMaybeObserver) this.inner.getAndSet(INNER_DISPOSED);
            if (switchMapMaybeObserver != null && switchMapMaybeObserver != INNER_DISPOSED) {
                switchMapMaybeObserver.dispose();
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
        public void innerError(SwitchMapMaybeObserver<R> switchMapMaybeObserver, Throwable th) {
            if (this.inner.compareAndSet(switchMapMaybeObserver, null) && this.errors.addThrowable(th)) {
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
        public void innerComplete(SwitchMapMaybeObserver<R> switchMapMaybeObserver) {
            if (this.inner.compareAndSet(switchMapMaybeObserver, null)) {
                drain();
            }
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
                        SwitchMapMaybeObserver switchMapMaybeObserver = (SwitchMapMaybeObserver) atomicReference.get();
                        Object obj = switchMapMaybeObserver == null ? 1 : null;
                        if (z && obj != null) {
                            Throwable terminate = atomicThrowable.terminate();
                            if (terminate != null) {
                                rVar.onError(terminate);
                            } else {
                                rVar.onComplete();
                            }
                            return;
                        } else if (obj != null || switchMapMaybeObserver.item == null) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else {
                            atomicReference.compareAndSet(switchMapMaybeObserver, null);
                            rVar.onNext(switchMapMaybeObserver.item);
                        }
                    } else {
                        rVar.onError(atomicThrowable.terminate());
                        return;
                    }
                }
            }
        }
    }

    public ObservableSwitchMapMaybe(k<T> kVar, wm<? super T, ? extends i<? extends R>> wmVar, boolean z) {
        this.a = kVar;
        this.b = wmVar;
        this.c = z;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super R> rVar) {
        if (!a.a(this.a, this.b, (r) rVar)) {
            this.a.subscribe(new SwitchMapMaybeMainObserver(rVar, this.b, this.c));
        }
    }
}

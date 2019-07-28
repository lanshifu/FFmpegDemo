package io.reactivex.internal.operators.mixed;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSwitchMapCompletable<T> extends a {
    final k<T> a;
    final wm<? super T, ? extends c> b;
    final boolean c;

    static final class SwitchMapCompletableObserver<T> implements b, r<T> {
        static final SwitchMapInnerObserver f = new SwitchMapInnerObserver(null);
        final io.reactivex.b a;
        final wm<? super T, ? extends c> b;
        final boolean c;
        final AtomicThrowable d = new AtomicThrowable();
        final AtomicReference<SwitchMapInnerObserver> e = new AtomicReference();
        volatile boolean g;
        b h;

        static final class SwitchMapInnerObserver extends AtomicReference<b> implements io.reactivex.b {
            private static final long serialVersionUID = -8003404460084760287L;
            final SwitchMapCompletableObserver<?> parent;

            SwitchMapInnerObserver(SwitchMapCompletableObserver<?> switchMapCompletableObserver) {
                this.parent = switchMapCompletableObserver;
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            public void onError(Throwable th) {
                this.parent.a(this, th);
            }

            public void onComplete() {
                this.parent.a(this);
            }

            /* Access modifiers changed, original: 0000 */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        SwitchMapCompletableObserver(io.reactivex.b bVar, wm<? super T, ? extends c> wmVar, boolean z) {
            this.a = bVar;
            this.b = wmVar;
            this.c = z;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.h, bVar)) {
                this.h = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            try {
                SwitchMapInnerObserver switchMapInnerObserver;
                c cVar = (c) io.reactivex.internal.functions.a.a(this.b.apply(t), "The mapper returned a null CompletableSource");
                SwitchMapInnerObserver switchMapInnerObserver2 = new SwitchMapInnerObserver(this);
                do {
                    switchMapInnerObserver = (SwitchMapInnerObserver) this.e.get();
                    if (switchMapInnerObserver == f) {
                        break;
                    }
                } while (!this.e.compareAndSet(switchMapInnerObserver, switchMapInnerObserver2));
                if (switchMapInnerObserver != null) {
                    switchMapInnerObserver.dispose();
                }
                cVar.a(switchMapInnerObserver2);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.h.dispose();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (!this.d.addThrowable(th)) {
                xk.a(th);
            } else if (this.c) {
                onComplete();
            } else {
                a();
                th = this.d.terminate();
                if (th != ExceptionHelper.a) {
                    this.a.onError(th);
                }
            }
        }

        public void onComplete() {
            this.g = true;
            if (this.e.get() == null) {
                Throwable terminate = this.d.terminate();
                if (terminate == null) {
                    this.a.onComplete();
                } else {
                    this.a.onError(terminate);
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            SwitchMapInnerObserver switchMapInnerObserver = (SwitchMapInnerObserver) this.e.getAndSet(f);
            if (switchMapInnerObserver != null && switchMapInnerObserver != f) {
                switchMapInnerObserver.dispose();
            }
        }

        public void dispose() {
            this.h.dispose();
            a();
        }

        public boolean isDisposed() {
            return this.e.get() == f;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(SwitchMapInnerObserver switchMapInnerObserver, Throwable th) {
            if (this.e.compareAndSet(switchMapInnerObserver, null) && this.d.addThrowable(th)) {
                if (!this.c) {
                    dispose();
                    Throwable terminate = this.d.terminate();
                    if (terminate != ExceptionHelper.a) {
                        this.a.onError(terminate);
                    }
                } else if (this.g) {
                    this.a.onError(this.d.terminate());
                }
                return;
            }
            xk.a(th);
        }

        /* Access modifiers changed, original: 0000 */
        public void a(SwitchMapInnerObserver switchMapInnerObserver) {
            if (this.e.compareAndSet(switchMapInnerObserver, null) && this.g) {
                Throwable terminate = this.d.terminate();
                if (terminate == null) {
                    this.a.onComplete();
                } else {
                    this.a.onError(terminate);
                }
            }
        }
    }

    public ObservableSwitchMapCompletable(k<T> kVar, wm<? super T, ? extends c> wmVar, boolean z) {
        this.a = kVar;
        this.b = wmVar;
        this.c = z;
    }

    /* Access modifiers changed, original: protected */
    public void b(io.reactivex.b bVar) {
        if (!a.a(this.a, this.b, bVar)) {
            this.a.subscribe(new SwitchMapCompletableObserver(bVar, this.b, this.c));
        }
    }
}

package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.c;
import io.reactivex.disposables.a;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableFlatMapCompletable<T> extends a<T, T> {
    final wm<? super T, ? extends c> b;
    final boolean c;

    static final class FlatMapCompletableMainObserver<T> extends BasicIntQueueDisposable<T> implements r<T> {
        private static final long serialVersionUID = 8443155186132538303L;
        final boolean delayErrors;
        volatile boolean disposed;
        final r<? super T> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final wm<? super T, ? extends c> mapper;
        final a set = new a();
        b upstream;

        final class InnerObserver extends AtomicReference<b> implements io.reactivex.b, b {
            private static final long serialVersionUID = 8606673141535671828L;

            InnerObserver() {
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            public void onComplete() {
                FlatMapCompletableMainObserver.this.innerComplete(this);
            }

            public void onError(Throwable th) {
                FlatMapCompletableMainObserver.this.innerError(this, th);
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            public boolean isDisposed() {
                return DisposableHelper.isDisposed((b) get());
            }
        }

        public void clear() {
        }

        public boolean isEmpty() {
            return true;
        }

        public T poll() throws Exception {
            return null;
        }

        public int requestFusion(int i) {
            return i & 2;
        }

        FlatMapCompletableMainObserver(r<? super T> rVar, wm<? super T, ? extends c> wmVar, boolean z) {
            this.downstream = rVar;
            this.mapper = wmVar;
            this.delayErrors = z;
            lazySet(1);
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            try {
                c cVar = (c) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null CompletableSource");
                getAndIncrement();
                b innerObserver = new InnerObserver();
                if (!this.disposed && this.set.a(innerObserver)) {
                    cVar.a(innerObserver);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (!this.errors.addThrowable(th)) {
                xk.a(th);
            } else if (!this.delayErrors) {
                dispose();
                if (getAndSet(0) > 0) {
                    this.downstream.onError(this.errors.terminate());
                }
            } else if (decrementAndGet() == 0) {
                this.downstream.onError(this.errors.terminate());
            }
        }

        public void onComplete() {
            if (decrementAndGet() == 0) {
                Throwable terminate = this.errors.terminate();
                if (terminate != null) {
                    this.downstream.onError(terminate);
                } else {
                    this.downstream.onComplete();
                }
            }
        }

        public void dispose() {
            this.disposed = true;
            this.upstream.dispose();
            this.set.dispose();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        /* Access modifiers changed, original: 0000 */
        public void innerComplete(InnerObserver innerObserver) {
            this.set.c(innerObserver);
            onComplete();
        }

        /* Access modifiers changed, original: 0000 */
        public void innerError(InnerObserver innerObserver, Throwable th) {
            this.set.c(innerObserver);
            onError(th);
        }
    }

    public ObservableFlatMapCompletable(p<T> pVar, wm<? super T, ? extends c> wmVar, boolean z) {
        super(pVar);
        this.b = wmVar;
        this.c = z;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new FlatMapCompletableMainObserver(rVar, this.b, this.c));
    }
}

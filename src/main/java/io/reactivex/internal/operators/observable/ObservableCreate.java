package io.reactivex.internal.operators.observable;

import defpackage.wk;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.n;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCreate<T> extends k<T> {
    final n<T> a;

    static final class CreateEmitter<T> extends AtomicReference<b> implements b, m<T> {
        private static final long serialVersionUID = -3434801548987643227L;
        final r<? super T> observer;

        CreateEmitter(r<? super T> rVar) {
            this.observer = rVar;
        }

        public void onNext(T t) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            if (!isDisposed()) {
                this.observer.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                xk.a(th);
            }
        }

        public boolean tryOnError(Throwable th) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (isDisposed()) {
                return false;
            }
            try {
                this.observer.onError(th);
                return true;
            } finally {
                dispose();
            }
        }

        public void onComplete() {
            if (!isDisposed()) {
                try {
                    this.observer.onComplete();
                } finally {
                    dispose();
                }
            }
        }

        public void setDisposable(b bVar) {
            DisposableHelper.set(this, bVar);
        }

        public void setCancellable(wk wkVar) {
            setDisposable(new CancellableDisposable(wkVar));
        }

        public m<T> serialize() {
            return new SerializedEmitter(this);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }

        public String toString() {
            return String.format("%s{%s}", new Object[]{getClass().getSimpleName(), super.toString()});
        }
    }

    static final class SerializedEmitter<T> extends AtomicInteger implements m<T> {
        private static final long serialVersionUID = 4883307006032401862L;
        volatile boolean done;
        final m<T> emitter;
        final AtomicThrowable error = new AtomicThrowable();
        final a<T> queue = new a(16);

        public m<T> serialize() {
            return this;
        }

        SerializedEmitter(m<T> mVar) {
            this.emitter = mVar;
        }

        public void onNext(T t) {
            if (!this.emitter.isDisposed() && !this.done) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                if (get() == 0 && compareAndSet(0, 1)) {
                    this.emitter.onNext(t);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                a aVar = this.queue;
                synchronized (aVar) {
                    aVar.offer(t);
                }
                if (getAndIncrement() != 0) {
                    return;
                }
                drainLoop();
            }
        }

        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                xk.a(th);
            }
        }

        public boolean tryOnError(Throwable th) {
            if (this.emitter.isDisposed() || this.done) {
                return false;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (!this.error.addThrowable(th)) {
                return false;
            }
            this.done = true;
            drain();
            return true;
        }

        public void onComplete() {
            if (!this.emitter.isDisposed() && !this.done) {
                this.done = true;
                drain();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void drainLoop() {
            m mVar = this.emitter;
            a aVar = this.queue;
            AtomicThrowable atomicThrowable = this.error;
            int i = 1;
            while (!mVar.isDisposed()) {
                if (atomicThrowable.get() != null) {
                    aVar.clear();
                    mVar.onError(atomicThrowable.terminate());
                    return;
                }
                boolean z = this.done;
                Object poll = aVar.poll();
                Object obj = poll == null ? 1 : null;
                if (z && obj != null) {
                    mVar.onComplete();
                    return;
                } else if (obj != null) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    mVar.onNext(poll);
                }
            }
            aVar.clear();
        }

        public void setDisposable(b bVar) {
            this.emitter.setDisposable(bVar);
        }

        public void setCancellable(wk wkVar) {
            this.emitter.setCancellable(wkVar);
        }

        public boolean isDisposed() {
            return this.emitter.isDisposed();
        }

        public String toString() {
            return this.emitter.toString();
        }
    }

    public ObservableCreate(n<T> nVar) {
        this.a = nVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        CreateEmitter createEmitter = new CreateEmitter(rVar);
        rVar.onSubscribe(createEmitter);
        try {
            this.a.subscribe(createEmitter);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            createEmitter.onError(th);
        }
    }
}

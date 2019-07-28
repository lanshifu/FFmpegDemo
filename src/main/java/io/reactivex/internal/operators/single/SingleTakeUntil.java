package io.reactivex.internal.operators.single;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.f;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.t;
import io.reactivex.u;
import io.reactivex.v;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleTakeUntil<T, U> extends t<T> {
    final v<T> a;
    final aaq<U> b;

    static final class TakeUntilMainObserver<T> extends AtomicReference<b> implements b, u<T> {
        private static final long serialVersionUID = -622603812305745221L;
        final u<? super T> downstream;
        final TakeUntilOtherSubscriber other = new TakeUntilOtherSubscriber(this);

        TakeUntilMainObserver(u<? super T> uVar) {
            this.downstream = uVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            this.other.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        public void onSuccess(T t) {
            this.other.dispose();
            if (((b) getAndSet(DisposableHelper.DISPOSED)) != DisposableHelper.DISPOSED) {
                this.downstream.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            this.other.dispose();
            if (((b) get()) == DisposableHelper.DISPOSED || ((b) getAndSet(DisposableHelper.DISPOSED)) == DisposableHelper.DISPOSED) {
                xk.a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void otherError(Throwable th) {
            if (((b) get()) != DisposableHelper.DISPOSED) {
                b bVar = (b) getAndSet(DisposableHelper.DISPOSED);
                if (bVar != DisposableHelper.DISPOSED) {
                    if (bVar != null) {
                        bVar.dispose();
                    }
                    this.downstream.onError(th);
                    return;
                }
            }
            xk.a(th);
        }
    }

    static final class TakeUntilOtherSubscriber extends AtomicReference<aas> implements f<Object> {
        private static final long serialVersionUID = 5170026210238877381L;
        final TakeUntilMainObserver<?> parent;

        TakeUntilOtherSubscriber(TakeUntilMainObserver<?> takeUntilMainObserver) {
            this.parent = takeUntilMainObserver;
        }

        public void onSubscribe(aas aas) {
            SubscriptionHelper.setOnce(this, aas, Long.MAX_VALUE);
        }

        public void onNext(Object obj) {
            if (SubscriptionHelper.cancel(this)) {
                this.parent.otherError(new CancellationException());
            }
        }

        public void onError(Throwable th) {
            this.parent.otherError(th);
        }

        public void onComplete() {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.otherError(new CancellationException());
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }
    }

    public SingleTakeUntil(v<T> vVar, aaq<U> aaq) {
        this.a = vVar;
        this.b = aaq;
    }

    /* Access modifiers changed, original: protected */
    public void b(u<? super T> uVar) {
        TakeUntilMainObserver takeUntilMainObserver = new TakeUntilMainObserver(uVar);
        uVar.onSubscribe(takeUntilMainObserver);
        this.b.a(takeUntilMainObserver.other);
        this.a.a(takeUntilMainObserver);
    }
}

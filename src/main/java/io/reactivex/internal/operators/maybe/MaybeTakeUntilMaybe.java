package io.reactivex.internal.operators.maybe;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.h;
import io.reactivex.i;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeTakeUntilMaybe<T, U> extends a<T, T> {
    final i<U> b;

    static final class TakeUntilMainMaybeObserver<T, U> extends AtomicReference<b> implements b, h<T> {
        private static final long serialVersionUID = -2187421758664251153L;
        final h<? super T> downstream;
        final TakeUntilOtherMaybeObserver<U> other = new TakeUntilOtherMaybeObserver(this);

        static final class TakeUntilOtherMaybeObserver<U> extends AtomicReference<b> implements h<U> {
            private static final long serialVersionUID = -1266041316834525931L;
            final TakeUntilMainMaybeObserver<?, U> parent;

            TakeUntilOtherMaybeObserver(TakeUntilMainMaybeObserver<?, U> takeUntilMainMaybeObserver) {
                this.parent = takeUntilMainMaybeObserver;
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            public void onSuccess(Object obj) {
                this.parent.otherComplete();
            }

            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            public void onComplete() {
                this.parent.otherComplete();
            }
        }

        TakeUntilMainMaybeObserver(h<? super T> hVar) {
            this.downstream = hVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            DisposableHelper.dispose(this.other);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        public void onSuccess(T t) {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onError(th);
            } else {
                xk.a(th);
            }
        }

        public void onComplete() {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onComplete();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void otherError(Throwable th) {
            if (DisposableHelper.dispose(this)) {
                this.downstream.onError(th);
            } else {
                xk.a(th);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void otherComplete() {
            if (DisposableHelper.dispose(this)) {
                this.downstream.onComplete();
            }
        }
    }

    public MaybeTakeUntilMaybe(i<T> iVar, i<U> iVar2) {
        super(iVar);
        this.b = iVar2;
    }

    /* Access modifiers changed, original: protected */
    public void b(h<? super T> hVar) {
        TakeUntilMainMaybeObserver takeUntilMainMaybeObserver = new TakeUntilMainMaybeObserver(hVar);
        hVar.onSubscribe(takeUntilMainMaybeObserver);
        this.b.a(takeUntilMainMaybeObserver.other);
        this.a.a(takeUntilMainMaybeObserver);
    }
}

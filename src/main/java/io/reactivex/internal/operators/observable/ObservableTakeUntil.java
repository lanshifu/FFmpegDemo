package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.f;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTakeUntil<T, U> extends a<T, T> {
    final p<? extends U> b;

    static final class TakeUntilMainObserver<T, U> extends AtomicInteger implements b, r<T> {
        private static final long serialVersionUID = 1418547743690811973L;
        final r<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final OtherObserver otherObserver = new OtherObserver();
        final AtomicReference<b> upstream = new AtomicReference();

        final class OtherObserver extends AtomicReference<b> implements r<U> {
            private static final long serialVersionUID = -8693423678067375039L;

            OtherObserver() {
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            public void onNext(U u) {
                DisposableHelper.dispose(this);
                TakeUntilMainObserver.this.otherComplete();
            }

            public void onError(Throwable th) {
                TakeUntilMainObserver.this.otherError(th);
            }

            public void onComplete() {
                TakeUntilMainObserver.this.otherComplete();
            }
        }

        TakeUntilMainObserver(r<? super T> rVar) {
            this.downstream = rVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this.otherObserver);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) this.upstream.get());
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        public void onNext(T t) {
            f.a(this.downstream, (Object) t, (AtomicInteger) this, this.error);
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.otherObserver);
            f.a(this.downstream, th, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            DisposableHelper.dispose(this.otherObserver);
            f.a(this.downstream, (AtomicInteger) this, this.error);
        }

        /* Access modifiers changed, original: 0000 */
        public void otherError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            f.a(this.downstream, th, (AtomicInteger) this, this.error);
        }

        /* Access modifiers changed, original: 0000 */
        public void otherComplete() {
            DisposableHelper.dispose(this.upstream);
            f.a(this.downstream, (AtomicInteger) this, this.error);
        }
    }

    public ObservableTakeUntil(p<T> pVar, p<? extends U> pVar2) {
        super(pVar);
        this.b = pVar2;
    }

    public void subscribeActual(r<? super T> rVar) {
        TakeUntilMainObserver takeUntilMainObserver = new TakeUntilMainObserver(rVar);
        rVar.onSubscribe(takeUntilMainObserver);
        this.b.subscribe(takeUntilMainObserver.otherObserver);
        this.a.subscribe(takeUntilMainObserver);
    }
}

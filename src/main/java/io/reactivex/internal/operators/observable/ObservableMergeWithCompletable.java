package io.reactivex.internal.operators.observable;

import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.f;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableMergeWithCompletable<T> extends a<T, T> {
    final c b;

    static final class MergeWithObserver<T> extends AtomicInteger implements b, r<T> {
        private static final long serialVersionUID = -4592979584110982903L;
        final r<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final AtomicReference<b> mainDisposable = new AtomicReference();
        volatile boolean mainDone;
        volatile boolean otherDone;
        final OtherObserver otherObserver = new OtherObserver(this);

        static final class OtherObserver extends AtomicReference<b> implements io.reactivex.b {
            private static final long serialVersionUID = -2935427570954647017L;
            final MergeWithObserver<?> parent;

            OtherObserver(MergeWithObserver<?> mergeWithObserver) {
                this.parent = mergeWithObserver;
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            public void onComplete() {
                this.parent.otherComplete();
            }
        }

        MergeWithObserver(r<? super T> rVar) {
            this.downstream = rVar;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.mainDisposable, bVar);
        }

        public void onNext(T t) {
            f.a(this.downstream, (Object) t, (AtomicInteger) this, this.error);
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.mainDisposable);
            f.a(this.downstream, th, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            this.mainDone = true;
            if (this.otherDone) {
                f.a(this.downstream, (AtomicInteger) this, this.error);
            }
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) this.mainDisposable.get());
        }

        public void dispose() {
            DisposableHelper.dispose(this.mainDisposable);
            DisposableHelper.dispose(this.otherObserver);
        }

        /* Access modifiers changed, original: 0000 */
        public void otherError(Throwable th) {
            DisposableHelper.dispose(this.mainDisposable);
            f.a(this.downstream, th, (AtomicInteger) this, this.error);
        }

        /* Access modifiers changed, original: 0000 */
        public void otherComplete() {
            this.otherDone = true;
            if (this.mainDone) {
                f.a(this.downstream, (AtomicInteger) this, this.error);
            }
        }
    }

    public ObservableMergeWithCompletable(k<T> kVar, c cVar) {
        super(kVar);
        this.b = cVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        MergeWithObserver mergeWithObserver = new MergeWithObserver(rVar);
        rVar.onSubscribe(mergeWithObserver);
        this.a.subscribe(mergeWithObserver);
        this.b.a(mergeWithObserver.otherObserver);
    }
}

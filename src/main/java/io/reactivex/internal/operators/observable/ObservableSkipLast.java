package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;
import java.util.ArrayDeque;

public final class ObservableSkipLast<T> extends a<T, T> {
    final int b;

    static final class SkipLastObserver<T> extends ArrayDeque<T> implements b, r<T> {
        private static final long serialVersionUID = -3807491841935125653L;
        final r<? super T> downstream;
        final int skip;
        b upstream;

        SkipLastObserver(r<? super T> rVar, int i) {
            super(i);
            this.downstream = rVar;
            this.skip = i;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void dispose() {
            this.upstream.dispose();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        public void onNext(T t) {
            if (this.skip == size()) {
                this.downstream.onNext(poll());
            }
            offer(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.downstream.onComplete();
        }
    }

    public ObservableSkipLast(p<T> pVar, int i) {
        super(pVar);
        this.b = i;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new SkipLastObserver(rVar, this.b));
    }
}

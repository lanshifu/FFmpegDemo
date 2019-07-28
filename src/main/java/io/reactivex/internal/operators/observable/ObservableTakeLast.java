package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;
import java.util.ArrayDeque;

public final class ObservableTakeLast<T> extends a<T, T> {
    final int b;

    static final class TakeLastObserver<T> extends ArrayDeque<T> implements b, r<T> {
        private static final long serialVersionUID = 7240042530241604978L;
        volatile boolean cancelled;
        final int count;
        final r<? super T> downstream;
        b upstream;

        TakeLastObserver(r<? super T> rVar, int i) {
            this.downstream = rVar;
            this.count = i;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.count == size()) {
                poll();
            }
            offer(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            r rVar = this.downstream;
            while (!this.cancelled) {
                Object poll = poll();
                if (poll == null) {
                    if (!this.cancelled) {
                        rVar.onComplete();
                    }
                    return;
                }
                rVar.onNext(poll);
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }
    }

    public ObservableTakeLast(p<T> pVar, int i) {
        super(pVar);
        this.b = i;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new TakeLastObserver(rVar, this.b));
    }
}

package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRepeat<T> extends a<T, T> {
    final long b;

    static final class RepeatObserver<T> extends AtomicInteger implements r<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final r<? super T> downstream;
        long remaining;
        final SequentialDisposable sd;
        final p<? extends T> source;

        RepeatObserver(r<? super T> rVar, long j, SequentialDisposable sequentialDisposable, p<? extends T> pVar) {
            this.downstream = rVar;
            this.sd = sequentialDisposable;
            this.source = pVar;
            this.remaining = j;
        }

        public void onSubscribe(b bVar) {
            this.sd.replace(bVar);
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            long j = this.remaining;
            if (j != Long.MAX_VALUE) {
                this.remaining = j - 1;
            }
            if (j != 0) {
                subscribeNext();
            } else {
                this.downstream.onComplete();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.sd.isDisposed()) {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                    if (i == 0) {
                    }
                }
            }
        }
    }

    public ObservableRepeat(k<T> kVar, long j) {
        super(kVar);
        this.b = j;
    }

    public void subscribeActual(r<? super T> rVar) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        rVar.onSubscribe(sequentialDisposable);
        long j = Long.MAX_VALUE;
        if (this.b != Long.MAX_VALUE) {
            j = this.b - 1;
        }
        new RepeatObserver(rVar, j, sequentialDisposable, this.a).subscribeNext();
    }
}

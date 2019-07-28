package io.reactivex.internal.operators.observable;

import defpackage.wi;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRetryBiPredicate<T> extends a<T, T> {
    final wi<? super Integer, ? super Throwable> b;

    static final class RetryBiObserver<T> extends AtomicInteger implements r<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final r<? super T> downstream;
        final wi<? super Integer, ? super Throwable> predicate;
        int retries;
        final p<? extends T> source;
        final SequentialDisposable upstream;

        RetryBiObserver(r<? super T> rVar, wi<? super Integer, ? super Throwable> wiVar, SequentialDisposable sequentialDisposable, p<? extends T> pVar) {
            this.downstream = rVar;
            this.upstream = sequentialDisposable;
            this.source = pVar;
            this.predicate = wiVar;
        }

        public void onSubscribe(b bVar) {
            this.upstream.update(bVar);
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            try {
                wi wiVar = this.predicate;
                int i = this.retries + 1;
                this.retries = i;
                if (wiVar.a(Integer.valueOf(i), th)) {
                    subscribeNext();
                } else {
                    this.downstream.onError(th);
                }
            } catch (Throwable th2) {
                a.b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        public void onComplete() {
            this.downstream.onComplete();
        }

        /* Access modifiers changed, original: 0000 */
        public void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.upstream.isDisposed()) {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                    if (i == 0) {
                    }
                }
            }
        }
    }

    public ObservableRetryBiPredicate(k<T> kVar, wi<? super Integer, ? super Throwable> wiVar) {
        super(kVar);
        this.b = wiVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        rVar.onSubscribe(sequentialDisposable);
        new RetryBiObserver(rVar, this.b, sequentialDisposable, this.a).subscribeNext();
    }
}

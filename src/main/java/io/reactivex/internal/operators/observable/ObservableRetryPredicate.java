package io.reactivex.internal.operators.observable;

import defpackage.wv;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRetryPredicate<T> extends a<T, T> {
    final wv<? super Throwable> b;
    final long c;

    static final class RepeatObserver<T> extends AtomicInteger implements r<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final r<? super T> downstream;
        final wv<? super Throwable> predicate;
        long remaining;
        final p<? extends T> source;
        final SequentialDisposable upstream;

        RepeatObserver(r<? super T> rVar, long j, wv<? super Throwable> wvVar, SequentialDisposable sequentialDisposable, p<? extends T> pVar) {
            this.downstream = rVar;
            this.upstream = sequentialDisposable;
            this.source = pVar;
            this.predicate = wvVar;
            this.remaining = j;
        }

        public void onSubscribe(b bVar) {
            this.upstream.update(bVar);
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            long j = this.remaining;
            if (j != Long.MAX_VALUE) {
                this.remaining = j - 1;
            }
            if (j == 0) {
                this.downstream.onError(th);
            } else {
                try {
                    if (this.predicate.test(th)) {
                        subscribeNext();
                    } else {
                        this.downstream.onError(th);
                    }
                } catch (Throwable th2) {
                    a.b(th2);
                    this.downstream.onError(new CompositeException(th, th2));
                }
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

    public ObservableRetryPredicate(k<T> kVar, long j, wv<? super Throwable> wvVar) {
        super(kVar);
        this.b = wvVar;
        this.c = j;
    }

    public void subscribeActual(r<? super T> rVar) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        rVar.onSubscribe(sequentialDisposable);
        new RepeatObserver(rVar, this.c, this.b, sequentialDisposable, this.a).subscribeNext();
    }
}

package io.reactivex.internal.operators.observable;

import defpackage.wj;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRepeatUntil<T> extends a<T, T> {
    final wj b;

    static final class RepeatUntilObserver<T> extends AtomicInteger implements r<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final r<? super T> downstream;
        final p<? extends T> source;
        final wj stop;
        final SequentialDisposable upstream;

        RepeatUntilObserver(r<? super T> rVar, wj wjVar, SequentialDisposable sequentialDisposable, p<? extends T> pVar) {
            this.downstream = rVar;
            this.upstream = sequentialDisposable;
            this.source = pVar;
            this.stop = wjVar;
        }

        public void onSubscribe(b bVar) {
            this.upstream.replace(bVar);
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            try {
                if (this.stop.a()) {
                    this.downstream.onComplete();
                } else {
                    subscribeNext();
                }
            } catch (Throwable th) {
                a.b(th);
                this.downstream.onError(th);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                do {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }
    }

    public ObservableRepeatUntil(k<T> kVar, wj wjVar) {
        super(kVar);
        this.b = wjVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        rVar.onSubscribe(sequentialDisposable);
        new RepeatUntilObserver(rVar, this.b, sequentialDisposable, this.a).subscribeNext();
    }
}

package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSampleWithObservable<T> extends a<T, T> {
    final p<?> b;
    final boolean c;

    static abstract class SampleMainObserver<T> extends AtomicReference<T> implements b, r<T> {
        private static final long serialVersionUID = -3517602651313910099L;
        final r<? super T> downstream;
        final AtomicReference<b> other = new AtomicReference();
        final p<?> sampler;
        b upstream;

        public abstract void completeMain();

        public abstract void completeOther();

        public abstract void run();

        SampleMainObserver(r<? super T> rVar, p<?> pVar) {
            this.downstream = rVar;
            this.sampler = pVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
                if (this.other.get() == null) {
                    this.sampler.subscribe(new a(this));
                }
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            this.downstream.onError(th);
        }

        public void onComplete() {
            DisposableHelper.dispose(this.other);
            completeMain();
        }

        /* Access modifiers changed, original: 0000 */
        public boolean setOther(b bVar) {
            return DisposableHelper.setOnce(this.other, bVar);
        }

        public void dispose() {
            DisposableHelper.dispose(this.other);
            this.upstream.dispose();
        }

        public boolean isDisposed() {
            return this.other.get() == DisposableHelper.DISPOSED;
        }

        public void error(Throwable th) {
            this.upstream.dispose();
            this.downstream.onError(th);
        }

        public void complete() {
            this.upstream.dispose();
            completeOther();
        }

        /* Access modifiers changed, original: 0000 */
        public void emit() {
            Object andSet = getAndSet(null);
            if (andSet != null) {
                this.downstream.onNext(andSet);
            }
        }
    }

    static final class a<T> implements r<Object> {
        final SampleMainObserver<T> a;

        a(SampleMainObserver<T> sampleMainObserver) {
            this.a = sampleMainObserver;
        }

        public void onSubscribe(b bVar) {
            this.a.setOther(bVar);
        }

        public void onNext(Object obj) {
            this.a.run();
        }

        public void onError(Throwable th) {
            this.a.error(th);
        }

        public void onComplete() {
            this.a.complete();
        }
    }

    static final class SampleMainEmitLast<T> extends SampleMainObserver<T> {
        private static final long serialVersionUID = -3029755663834015785L;
        volatile boolean done;
        final AtomicInteger wip = new AtomicInteger();

        SampleMainEmitLast(r<? super T> rVar, p<?> pVar) {
            super(rVar, pVar);
        }

        /* Access modifiers changed, original: 0000 */
        public void completeMain() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.downstream.onComplete();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void completeOther() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.downstream.onComplete();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void run() {
            if (this.wip.getAndIncrement() == 0) {
                do {
                    boolean z = this.done;
                    emit();
                    if (z) {
                        this.downstream.onComplete();
                        return;
                    }
                } while (this.wip.decrementAndGet() != 0);
            }
        }
    }

    static final class SampleMainNoLast<T> extends SampleMainObserver<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        SampleMainNoLast(r<? super T> rVar, p<?> pVar) {
            super(rVar, pVar);
        }

        /* Access modifiers changed, original: 0000 */
        public void completeMain() {
            this.downstream.onComplete();
        }

        /* Access modifiers changed, original: 0000 */
        public void completeOther() {
            this.downstream.onComplete();
        }

        /* Access modifiers changed, original: 0000 */
        public void run() {
            emit();
        }
    }

    public ObservableSampleWithObservable(p<T> pVar, p<?> pVar2, boolean z) {
        super(pVar);
        this.b = pVar2;
        this.c = z;
    }

    public void subscribeActual(r<? super T> rVar) {
        e eVar = new e(rVar);
        if (this.c) {
            this.a.subscribe(new SampleMainEmitLast(eVar, this.b));
        } else {
            this.a.subscribe(new SampleMainNoLast(eVar, this.b));
        }
    }
}

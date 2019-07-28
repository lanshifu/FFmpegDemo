package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.subjects.UnicastSubject;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableWindow<T> extends a<T, k<T>> {
    final long b;
    final long c;
    final int d;

    static final class WindowExactObserver<T> extends AtomicInteger implements b, r<T>, Runnable {
        private static final long serialVersionUID = -7481782523886138128L;
        volatile boolean cancelled;
        final int capacityHint;
        final long count;
        final r<? super k<T>> downstream;
        long size;
        b upstream;
        UnicastSubject<T> window;

        WindowExactObserver(r<? super k<T>> rVar, long j, int i) {
            this.downstream = rVar;
            this.count = j;
            this.capacityHint = i;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            UnicastSubject unicastSubject = this.window;
            if (unicastSubject == null && !this.cancelled) {
                unicastSubject = UnicastSubject.a(this.capacityHint, (Runnable) this);
                this.window = unicastSubject;
                this.downstream.onNext(unicastSubject);
            }
            if (unicastSubject != null) {
                unicastSubject.onNext(t);
                long j = this.size + 1;
                this.size = j;
                if (j >= this.count) {
                    this.size = 0;
                    this.window = null;
                    unicastSubject.onComplete();
                    if (this.cancelled) {
                        this.upstream.dispose();
                    }
                }
            }
        }

        public void onError(Throwable th) {
            UnicastSubject unicastSubject = this.window;
            if (unicastSubject != null) {
                this.window = null;
                unicastSubject.onError(th);
            }
            this.downstream.onError(th);
        }

        public void onComplete() {
            UnicastSubject unicastSubject = this.window;
            if (unicastSubject != null) {
                this.window = null;
                unicastSubject.onComplete();
            }
            this.downstream.onComplete();
        }

        public void dispose() {
            this.cancelled = true;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void run() {
            if (this.cancelled) {
                this.upstream.dispose();
            }
        }
    }

    static final class WindowSkipObserver<T> extends AtomicBoolean implements b, r<T>, Runnable {
        private static final long serialVersionUID = 3366976432059579510L;
        volatile boolean cancelled;
        final int capacityHint;
        final long count;
        final r<? super k<T>> downstream;
        long firstEmission;
        long index;
        final long skip;
        b upstream;
        final ArrayDeque<UnicastSubject<T>> windows;
        final AtomicInteger wip = new AtomicInteger();

        WindowSkipObserver(r<? super k<T>> rVar, long j, long j2, int i) {
            this.downstream = rVar;
            this.count = j;
            this.skip = j2;
            this.capacityHint = i;
            this.windows = new ArrayDeque();
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            ArrayDeque arrayDeque = this.windows;
            long j = this.index;
            long j2 = this.skip;
            if (j % j2 == 0 && !this.cancelled) {
                this.wip.getAndIncrement();
                UnicastSubject a = UnicastSubject.a(this.capacityHint, (Runnable) this);
                arrayDeque.offer(a);
                this.downstream.onNext(a);
            }
            long j3 = this.firstEmission + 1;
            Iterator it = arrayDeque.iterator();
            while (it.hasNext()) {
                ((UnicastSubject) it.next()).onNext(t);
            }
            if (j3 >= this.count) {
                ((UnicastSubject) arrayDeque.poll()).onComplete();
                if (arrayDeque.isEmpty() && this.cancelled) {
                    this.upstream.dispose();
                    return;
                }
                this.firstEmission = j3 - j2;
            } else {
                this.firstEmission = j3;
            }
            this.index = j + 1;
        }

        public void onError(Throwable th) {
            ArrayDeque arrayDeque = this.windows;
            while (!arrayDeque.isEmpty()) {
                ((UnicastSubject) arrayDeque.poll()).onError(th);
            }
            this.downstream.onError(th);
        }

        public void onComplete() {
            ArrayDeque arrayDeque = this.windows;
            while (!arrayDeque.isEmpty()) {
                ((UnicastSubject) arrayDeque.poll()).onComplete();
            }
            this.downstream.onComplete();
        }

        public void dispose() {
            this.cancelled = true;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void run() {
            if (this.wip.decrementAndGet() == 0 && this.cancelled) {
                this.upstream.dispose();
            }
        }
    }

    public ObservableWindow(p<T> pVar, long j, long j2, int i) {
        super(pVar);
        this.b = j;
        this.c = j2;
        this.d = i;
    }

    public void subscribeActual(r<? super k<T>> rVar) {
        if (this.b == this.c) {
            this.a.subscribe(new WindowExactObserver(rVar, this.b, this.d));
            return;
        }
        this.a.subscribe(new WindowSkipObserver(rVar, this.b, this.c, this.d));
    }
}

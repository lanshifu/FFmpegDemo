package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableSkipLastTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final s d;
    final int e;
    final boolean f;

    static final class SkipLastTimedObserver<T> extends AtomicInteger implements b, r<T> {
        private static final long serialVersionUID = -5677354903406201275L;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        final r<? super T> downstream;
        Throwable error;
        final a<Object> queue;
        final s scheduler;
        final long time;
        final TimeUnit unit;
        b upstream;

        SkipLastTimedObserver(r<? super T> rVar, long j, TimeUnit timeUnit, s sVar, int i, boolean z) {
            this.downstream = rVar;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = sVar;
            this.queue = new a(i);
            this.delayError = z;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.queue.a(Long.valueOf(this.scheduler.a(this.unit)), (Object) t);
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                r rVar = this.downstream;
                a aVar = this.queue;
                boolean z = this.delayError;
                TimeUnit timeUnit = this.unit;
                s sVar = this.scheduler;
                long j = this.time;
                int i = 1;
                while (!this.cancelled) {
                    boolean z2 = this.done;
                    Long l = (Long) aVar.a();
                    Object obj = l == null ? 1 : null;
                    long a = sVar.a(timeUnit);
                    if (obj == null && l.longValue() > a - j) {
                        obj = 1;
                    }
                    if (z2) {
                        if (!z) {
                            Throwable th = this.error;
                            if (th != null) {
                                this.queue.clear();
                                rVar.onError(th);
                                return;
                            } else if (obj != null) {
                                rVar.onComplete();
                                return;
                            }
                        } else if (obj != null) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                rVar.onError(th2);
                            } else {
                                rVar.onComplete();
                            }
                            return;
                        }
                    }
                    if (obj != null) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        aVar.poll();
                        rVar.onNext(aVar.poll());
                    }
                }
                this.queue.clear();
            }
        }
    }

    public ObservableSkipLastTimed(p<T> pVar, long j, TimeUnit timeUnit, s sVar, int i, boolean z) {
        super(pVar);
        this.b = j;
        this.c = timeUnit;
        this.d = sVar;
        this.e = i;
        this.f = z;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new SkipLastTimedObserver(rVar, this.b, this.c, this.d, this.e, this.f));
    }
}

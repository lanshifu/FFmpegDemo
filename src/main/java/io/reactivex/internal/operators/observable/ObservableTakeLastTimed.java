package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableTakeLastTimed<T> extends a<T, T> {
    final long b;
    final long c;
    final TimeUnit d;
    final s e;
    final int f;
    final boolean g;

    static final class TakeLastTimedObserver<T> extends AtomicBoolean implements b, r<T> {
        private static final long serialVersionUID = -5677354903406201275L;
        volatile boolean cancelled;
        final long count;
        final boolean delayError;
        final r<? super T> downstream;
        Throwable error;
        final a<Object> queue;
        final s scheduler;
        final long time;
        final TimeUnit unit;
        b upstream;

        TakeLastTimedObserver(r<? super T> rVar, long j, long j2, TimeUnit timeUnit, s sVar, int i, boolean z) {
            this.downstream = rVar;
            this.count = j;
            this.time = j2;
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
            a aVar = this.queue;
            long a = this.scheduler.a(this.unit);
            long j = this.time;
            long j2 = this.count;
            Object obj = j2 == Long.MAX_VALUE ? 1 : null;
            aVar.a(Long.valueOf(a), (Object) t);
            while (!aVar.isEmpty()) {
                if (((Long) aVar.a()).longValue() <= a - j || (obj == null && ((long) (aVar.b() >> 1)) > j2)) {
                    aVar.poll();
                    aVar.poll();
                } else {
                    return;
                }
            }
        }

        public void onError(Throwable th) {
            this.error = th;
            drain();
        }

        public void onComplete() {
            drain();
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                if (compareAndSet(false, true)) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (compareAndSet(false, true)) {
                r rVar = this.downstream;
                a aVar = this.queue;
                boolean z = this.delayError;
                while (!this.cancelled) {
                    if (!z) {
                        Throwable th = this.error;
                        if (th != null) {
                            aVar.clear();
                            rVar.onError(th);
                            return;
                        }
                    }
                    Object poll = aVar.poll();
                    if ((poll == null ? 1 : null) != null) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            rVar.onError(th2);
                        } else {
                            rVar.onComplete();
                        }
                        return;
                    }
                    Object poll2 = aVar.poll();
                    if (((Long) poll).longValue() >= this.scheduler.a(this.unit) - this.time) {
                        rVar.onNext(poll2);
                    }
                }
                aVar.clear();
            }
        }
    }

    public ObservableTakeLastTimed(p<T> pVar, long j, long j2, TimeUnit timeUnit, s sVar, int i, boolean z) {
        super(pVar);
        this.b = j;
        this.c = j2;
        this.d = timeUnit;
        this.e = sVar;
        this.f = i;
        this.g = z;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new TakeLastTimedObserver(rVar, this.b, this.c, this.d, this.e, this.f, this.g));
    }
}

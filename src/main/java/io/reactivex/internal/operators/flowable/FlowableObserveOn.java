package io.reactivex.internal.operators.flowable;

import defpackage.ww;
import defpackage.xa;
import defpackage.xd;
import defpackage.xk;
import io.reactivex.e;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.exceptions.a;
import io.reactivex.f;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.s;
import io.reactivex.s.c;
import java.util.concurrent.atomic.AtomicLong;

public final class FlowableObserveOn<T> extends a<T, T> {
    final s c;
    final boolean d;
    final int e;

    static abstract class BaseObserveOnSubscriber<T> extends BasicIntQueueSubscription<T> implements f<T>, Runnable {
        private static final long serialVersionUID = -8241002408341274697L;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final int limit;
        boolean outputFused;
        final int prefetch;
        long produced;
        xd<T> queue;
        final AtomicLong requested = new AtomicLong();
        int sourceMode;
        aas upstream;
        final c worker;

        public abstract void runAsync();

        public abstract void runBackfused();

        public abstract void runSync();

        BaseObserveOnSubscriber(c cVar, boolean z, int i) {
            this.worker = cVar;
            this.delayError = z;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        public final void onNext(T t) {
            if (!this.done) {
                if (this.sourceMode == 2) {
                    trySchedule();
                    return;
                }
                if (!this.queue.offer(t)) {
                    this.upstream.cancel();
                    this.error = new MissingBackpressureException("Queue is full?!");
                    this.done = true;
                }
                trySchedule();
            }
        }

        public final void onError(Throwable th) {
            if (this.done) {
                xk.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            trySchedule();
        }

        public final void onComplete() {
            if (!this.done) {
                this.done = true;
                trySchedule();
            }
        }

        public final void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                trySchedule();
            }
        }

        public final void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                this.worker.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* Access modifiers changed, original: final */
        public final void trySchedule() {
            if (getAndIncrement() == 0) {
                this.worker.a((Runnable) this);
            }
        }

        public final void run() {
            if (this.outputFused) {
                runBackfused();
            } else if (this.sourceMode == 1) {
                runSync();
            } else {
                runAsync();
            }
        }

        /* Access modifiers changed, original: final */
        public final boolean checkTerminated(boolean z, boolean z2, aar<?> aar) {
            if (this.cancelled) {
                clear();
                return true;
            }
            if (z) {
                Throwable th;
                if (!this.delayError) {
                    th = this.error;
                    if (th != null) {
                        this.cancelled = true;
                        clear();
                        aar.onError(th);
                        this.worker.dispose();
                        return true;
                    } else if (z2) {
                        this.cancelled = true;
                        aar.onComplete();
                        this.worker.dispose();
                        return true;
                    }
                } else if (z2) {
                    this.cancelled = true;
                    th = this.error;
                    if (th != null) {
                        aar.onError(th);
                    } else {
                        aar.onComplete();
                    }
                    this.worker.dispose();
                    return true;
                }
            }
            return false;
        }

        public final int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public final void clear() {
            this.queue.clear();
        }

        public final boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    static final class ObserveOnConditionalSubscriber<T> extends BaseObserveOnSubscriber<T> {
        private static final long serialVersionUID = 644624475404284533L;
        long consumed;
        final ww<? super T> downstream;

        ObserveOnConditionalSubscriber(ww<? super T> wwVar, c cVar, boolean z, int i) {
            super(cVar, z, i);
            this.downstream = wwVar;
        }

        public void onSubscribe(aas aas) {
            if (SubscriptionHelper.validate(this.upstream, aas)) {
                this.upstream = aas;
                if (aas instanceof xa) {
                    xa xaVar = (xa) aas;
                    int requestFusion = xaVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = 1;
                        this.queue = xaVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = 2;
                        this.queue = xaVar;
                        this.downstream.onSubscribe(this);
                        aas.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.downstream.onSubscribe(this);
                aas.request((long) this.prefetch);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void runSync() {
            ww wwVar = this.downstream;
            xd xdVar = this.queue;
            long j = this.produced;
            int i = 1;
            while (true) {
                long j2 = this.requested.get();
                while (j != j2) {
                    try {
                        Object poll = xdVar.poll();
                        if (!this.cancelled) {
                            if (poll == null) {
                                this.cancelled = true;
                                wwVar.onComplete();
                                this.worker.dispose();
                                return;
                            } else if (wwVar.a(poll)) {
                                j++;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        a.b(th);
                        this.cancelled = true;
                        this.upstream.cancel();
                        wwVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (!this.cancelled) {
                    if (xdVar.isEmpty()) {
                        this.cancelled = true;
                        wwVar.onComplete();
                        this.worker.dispose();
                        return;
                    }
                    int i2 = get();
                    if (i == i2) {
                        this.produced = j;
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        i = i2;
                    }
                } else {
                    return;
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void runAsync() {
            ww wwVar = this.downstream;
            xd xdVar = this.queue;
            long j = this.produced;
            long j2 = this.consumed;
            int i = 1;
            while (true) {
                long j3 = this.requested.get();
                while (j != j3) {
                    boolean z = this.done;
                    try {
                        Object poll = xdVar.poll();
                        boolean z2 = poll == null;
                        if (!checkTerminated(z, z2, wwVar)) {
                            if (z2) {
                                break;
                            }
                            if (wwVar.a(poll)) {
                                j++;
                            }
                            j2++;
                            if (j2 == ((long) this.limit)) {
                                this.upstream.request(j2);
                                j2 = 0;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        a.b(th);
                        this.cancelled = true;
                        this.upstream.cancel();
                        xdVar.clear();
                        wwVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (j != j3 || !checkTerminated(this.done, xdVar.isEmpty(), wwVar)) {
                    int i2 = get();
                    if (i == i2) {
                        this.produced = j;
                        this.consumed = j2;
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        i = i2;
                    }
                } else {
                    return;
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void runBackfused() {
            int i = 1;
            while (!this.cancelled) {
                boolean z = this.done;
                this.downstream.onNext(null);
                if (z) {
                    this.cancelled = true;
                    Throwable th = this.error;
                    if (th != null) {
                        this.downstream.onError(th);
                    } else {
                        this.downstream.onComplete();
                    }
                    this.worker.dispose();
                    return;
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        public T poll() throws Exception {
            Object poll = this.queue.poll();
            if (!(poll == null || this.sourceMode == 1)) {
                long j = this.consumed + 1;
                if (j == ((long) this.limit)) {
                    this.consumed = 0;
                    this.upstream.request(j);
                } else {
                    this.consumed = j;
                }
            }
            return poll;
        }
    }

    static final class ObserveOnSubscriber<T> extends BaseObserveOnSubscriber<T> implements f<T> {
        private static final long serialVersionUID = -4547113800637756442L;
        final aar<? super T> downstream;

        ObserveOnSubscriber(aar<? super T> aar, c cVar, boolean z, int i) {
            super(cVar, z, i);
            this.downstream = aar;
        }

        public void onSubscribe(aas aas) {
            if (SubscriptionHelper.validate(this.upstream, aas)) {
                this.upstream = aas;
                if (aas instanceof xa) {
                    xa xaVar = (xa) aas;
                    int requestFusion = xaVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = 1;
                        this.queue = xaVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = 2;
                        this.queue = xaVar;
                        this.downstream.onSubscribe(this);
                        aas.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.downstream.onSubscribe(this);
                aas.request((long) this.prefetch);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void runSync() {
            aar aar = this.downstream;
            xd xdVar = this.queue;
            long j = this.produced;
            int i = 1;
            while (true) {
                long j2 = this.requested.get();
                while (j != j2) {
                    try {
                        Object poll = xdVar.poll();
                        if (!this.cancelled) {
                            if (poll == null) {
                                this.cancelled = true;
                                aar.onComplete();
                                this.worker.dispose();
                                return;
                            }
                            aar.onNext(poll);
                            j++;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        a.b(th);
                        this.cancelled = true;
                        this.upstream.cancel();
                        aar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (!this.cancelled) {
                    if (xdVar.isEmpty()) {
                        this.cancelled = true;
                        aar.onComplete();
                        this.worker.dispose();
                        return;
                    }
                    int i2 = get();
                    if (i == i2) {
                        this.produced = j;
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        i = i2;
                    }
                } else {
                    return;
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void runAsync() {
            aar aar = this.downstream;
            xd xdVar = this.queue;
            long j = this.produced;
            int i = 1;
            while (true) {
                long j2 = this.requested.get();
                while (j != j2) {
                    boolean z = this.done;
                    try {
                        Object poll = xdVar.poll();
                        boolean z2 = poll == null;
                        if (!checkTerminated(z, z2, aar)) {
                            if (z2) {
                                break;
                            }
                            aar.onNext(poll);
                            j++;
                            if (j == ((long) this.limit)) {
                                if (j2 != Long.MAX_VALUE) {
                                    j2 = this.requested.addAndGet(-j);
                                }
                                this.upstream.request(j);
                                j = 0;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        a.b(th);
                        this.cancelled = true;
                        this.upstream.cancel();
                        xdVar.clear();
                        aar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (j != j2 || !checkTerminated(this.done, xdVar.isEmpty(), aar)) {
                    int i2 = get();
                    if (i == i2) {
                        this.produced = j;
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        i = i2;
                    }
                } else {
                    return;
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void runBackfused() {
            int i = 1;
            while (!this.cancelled) {
                boolean z = this.done;
                this.downstream.onNext(null);
                if (z) {
                    this.cancelled = true;
                    Throwable th = this.error;
                    if (th != null) {
                        this.downstream.onError(th);
                    } else {
                        this.downstream.onComplete();
                    }
                    this.worker.dispose();
                    return;
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        public T poll() throws Exception {
            Object poll = this.queue.poll();
            if (!(poll == null || this.sourceMode == 1)) {
                long j = this.produced + 1;
                if (j == ((long) this.limit)) {
                    this.produced = 0;
                    this.upstream.request(j);
                } else {
                    this.produced = j;
                }
            }
            return poll;
        }
    }

    public FlowableObserveOn(e<T> eVar, s sVar, boolean z, int i) {
        super(eVar);
        this.c = sVar;
        this.d = z;
        this.e = i;
    }

    public void b(aar<? super T> aar) {
        c a = this.c.a();
        if (aar instanceof ww) {
            this.b.a(new ObserveOnConditionalSubscriber((ww) aar, a, this.d, this.e));
        } else {
            this.b.a(new ObserveOnSubscriber(aar, a, this.d, this.e));
        }
    }
}

package io.reactivex.internal.operators.flowable;

import defpackage.wf;
import defpackage.xc;
import io.reactivex.e;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.f;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;

public final class FlowableOnBackpressureBuffer<T> extends a<T, T> {
    final int c;
    final boolean d;
    final boolean e;
    final wf f;

    static final class BackpressureBufferSubscriber<T> extends BasicIntQueueSubscription<T> implements f<T> {
        private static final long serialVersionUID = -2514538129242366402L;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        final aar<? super T> downstream;
        Throwable error;
        final wf onOverflow;
        boolean outputFused;
        final xc<T> queue;
        final AtomicLong requested = new AtomicLong();
        aas upstream;

        BackpressureBufferSubscriber(aar<? super T> aar, int i, boolean z, boolean z2, wf wfVar) {
            xc aVar;
            this.downstream = aar;
            this.onOverflow = wfVar;
            this.delayError = z2;
            if (z) {
                aVar = new a(i);
            } else {
                aVar = new SpscArrayQueue(i);
            }
            this.queue = aVar;
        }

        public void onSubscribe(aas aas) {
            if (SubscriptionHelper.validate(this.upstream, aas)) {
                this.upstream = aas;
                this.downstream.onSubscribe(this);
                aas.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (this.queue.offer(t)) {
                if (this.outputFused) {
                    this.downstream.onNext(null);
                } else {
                    drain();
                }
                return;
            }
            this.upstream.cancel();
            MissingBackpressureException missingBackpressureException = new MissingBackpressureException("Buffer is full");
            try {
                this.onOverflow.a();
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                missingBackpressureException.initCause(th);
            }
            onError(missingBackpressureException);
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            if (this.outputFused) {
                this.downstream.onError(th);
            } else {
                drain();
            }
        }

        public void onComplete() {
            this.done = true;
            if (this.outputFused) {
                this.downstream.onComplete();
            } else {
                drain();
            }
        }

        public void request(long j) {
            if (!this.outputFused && SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                xc xcVar = this.queue;
                aar aar = this.downstream;
                int i = 1;
                while (!checkTerminated(this.done, xcVar.isEmpty(), aar)) {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        boolean z = this.done;
                        Object poll = xcVar.poll();
                        boolean z2 = poll == null;
                        if (!checkTerminated(z, z2, aar)) {
                            if (z2) {
                                break;
                            }
                            aar.onNext(poll);
                            j2++;
                        } else {
                            return;
                        }
                    }
                    if (j2 != j || !checkTerminated(this.done, xcVar.isEmpty(), aar)) {
                        if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                            this.requested.addAndGet(-j2);
                        }
                        i = addAndGet(-i);
                        if (i == 0) {
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, aar<? super T> aar) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            }
            if (z) {
                Throwable th;
                if (!this.delayError) {
                    th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        aar.onError(th);
                        return true;
                    } else if (z2) {
                        aar.onComplete();
                        return true;
                    }
                } else if (z2) {
                    th = this.error;
                    if (th != null) {
                        aar.onError(th);
                    } else {
                        aar.onComplete();
                    }
                    return true;
                }
            }
            return false;
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public T poll() throws Exception {
            return this.queue.poll();
        }

        public void clear() {
            this.queue.clear();
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    public FlowableOnBackpressureBuffer(e<T> eVar, int i, boolean z, boolean z2, wf wfVar) {
        super(eVar);
        this.c = i;
        this.d = z;
        this.e = z2;
        this.f = wfVar;
    }

    /* Access modifiers changed, original: protected */
    public void b(aar<? super T> aar) {
        this.b.a(new BackpressureBufferSubscriber(aar, this.c, this.d, this.e, this.f));
    }
}

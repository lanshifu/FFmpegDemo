package io.reactivex.internal.operators.flowable;

import io.reactivex.e;
import io.reactivex.f;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class FlowableOnBackpressureLatest<T> extends a<T, T> {

    static final class BackpressureLatestSubscriber<T> extends AtomicInteger implements aas, f<T> {
        private static final long serialVersionUID = 163080509307634843L;
        volatile boolean cancelled;
        final AtomicReference<T> current = new AtomicReference();
        volatile boolean done;
        final aar<? super T> downstream;
        Throwable error;
        final AtomicLong requested = new AtomicLong();
        aas upstream;

        BackpressureLatestSubscriber(aar<? super T> aar) {
            this.downstream = aar;
        }

        public void onSubscribe(aas aas) {
            if (SubscriptionHelper.validate(this.upstream, aas)) {
                this.upstream = aas;
                this.downstream.onSubscribe(this);
                aas.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.current.lazySet(t);
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

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.current.lazySet(null);
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                aar aar = this.downstream;
                AtomicLong atomicLong = this.requested;
                AtomicReference atomicReference = this.current;
                int i = 1;
                do {
                    boolean z;
                    boolean z2;
                    long j = 0;
                    while (true) {
                        z = false;
                        if (j == atomicLong.get()) {
                            break;
                        }
                        z2 = this.done;
                        Object andSet = atomicReference.getAndSet(null);
                        boolean z3 = andSet == null;
                        if (!checkTerminated(z2, z3, aar, atomicReference)) {
                            if (z3) {
                                break;
                            }
                            aar.onNext(andSet);
                            j++;
                        } else {
                            return;
                        }
                    }
                    if (j == atomicLong.get()) {
                        z2 = this.done;
                        if (atomicReference.get() == null) {
                            z = true;
                        }
                        if (checkTerminated(z2, z, aar, atomicReference)) {
                            return;
                        }
                    }
                    if (j != 0) {
                        b.b(atomicLong, j);
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, aar<?> aar, AtomicReference<T> atomicReference) {
            if (this.cancelled) {
                atomicReference.lazySet(null);
                return true;
            }
            if (z) {
                Throwable th = this.error;
                if (th != null) {
                    atomicReference.lazySet(null);
                    aar.onError(th);
                    return true;
                } else if (z2) {
                    aar.onComplete();
                    return true;
                }
            }
            return false;
        }
    }

    public FlowableOnBackpressureLatest(e<T> eVar) {
        super(eVar);
    }

    /* Access modifiers changed, original: protected */
    public void b(aar<? super T> aar) {
        this.b.a(new BackpressureLatestSubscriber(aar));
    }
}

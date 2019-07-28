package io.reactivex.internal.operators.flowable;

import defpackage.xk;
import io.reactivex.e;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.f;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;

public final class FlowableOnBackpressureError<T> extends a<T, T> {

    static final class BackpressureErrorSubscriber<T> extends AtomicLong implements aas, f<T> {
        private static final long serialVersionUID = -3176480756392482682L;
        boolean done;
        final aar<? super T> downstream;
        aas upstream;

        BackpressureErrorSubscriber(aar<? super T> aar) {
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
            if (!this.done) {
                if (get() != 0) {
                    this.downstream.onNext(t);
                    b.b(this, 1);
                } else {
                    onError(new MissingBackpressureException("could not emit value due to lack of requests"));
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                xk.a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a((AtomicLong) this, j);
            }
        }

        public void cancel() {
            this.upstream.cancel();
        }
    }

    public FlowableOnBackpressureError(e<T> eVar) {
        super(eVar);
    }

    /* Access modifiers changed, original: protected */
    public void b(aar<? super T> aar) {
        this.b.a(new BackpressureErrorSubscriber(aar));
    }
}

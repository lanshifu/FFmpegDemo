package io.reactivex.internal.operators.flowable;

import defpackage.wl;
import defpackage.xk;
import io.reactivex.e;
import io.reactivex.exceptions.a;
import io.reactivex.f;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;

public final class FlowableOnBackpressureDrop<T> extends a<T, T> implements wl<T> {
    final wl<? super T> c = this;

    static final class BackpressureDropSubscriber<T> extends AtomicLong implements aas, f<T> {
        private static final long serialVersionUID = -6246093802440953054L;
        boolean done;
        final aar<? super T> downstream;
        final wl<? super T> onDrop;
        aas upstream;

        BackpressureDropSubscriber(aar<? super T> aar, wl<? super T> wlVar) {
            this.downstream = aar;
            this.onDrop = wlVar;
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
                    try {
                        this.onDrop.accept(t);
                    } catch (Throwable th) {
                        a.b(th);
                        cancel();
                        onError(th);
                    }
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

    public void accept(T t) {
    }

    public FlowableOnBackpressureDrop(e<T> eVar) {
        super(eVar);
    }

    /* Access modifiers changed, original: protected */
    public void b(aar<? super T> aar) {
        this.b.a(new BackpressureDropSubscriber(aar, this.c));
    }
}

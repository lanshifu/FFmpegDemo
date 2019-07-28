package io.reactivex.internal.operators.flowable;

import io.reactivex.e;
import io.reactivex.f;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class FlowableTakeUntil<T, U> extends a<T, T> {
    final aaq<? extends U> c;

    static final class TakeUntilMainSubscriber<T> extends AtomicInteger implements aas, f<T> {
        private static final long serialVersionUID = -4945480365982832967L;
        final aar<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final OtherSubscriber other = new OtherSubscriber();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<aas> upstream = new AtomicReference();

        final class OtherSubscriber extends AtomicReference<aas> implements f<Object> {
            private static final long serialVersionUID = -3592821756711087922L;

            OtherSubscriber() {
            }

            public void onSubscribe(aas aas) {
                SubscriptionHelper.setOnce(this, aas, Long.MAX_VALUE);
            }

            public void onNext(Object obj) {
                SubscriptionHelper.cancel(this);
                onComplete();
            }

            public void onError(Throwable th) {
                SubscriptionHelper.cancel(TakeUntilMainSubscriber.this.upstream);
                io.reactivex.internal.util.f.a(TakeUntilMainSubscriber.this.downstream, th, TakeUntilMainSubscriber.this, TakeUntilMainSubscriber.this.error);
            }

            public void onComplete() {
                SubscriptionHelper.cancel(TakeUntilMainSubscriber.this.upstream);
                io.reactivex.internal.util.f.a(TakeUntilMainSubscriber.this.downstream, TakeUntilMainSubscriber.this, TakeUntilMainSubscriber.this.error);
            }
        }

        TakeUntilMainSubscriber(aar<? super T> aar) {
            this.downstream = aar;
        }

        public void onSubscribe(aas aas) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, aas);
        }

        public void onNext(T t) {
            io.reactivex.internal.util.f.a(this.downstream, (Object) t, (AtomicInteger) this, this.error);
        }

        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            io.reactivex.internal.util.f.a(this.downstream, th, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            io.reactivex.internal.util.f.a(this.downstream, (AtomicInteger) this, this.error);
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            SubscriptionHelper.cancel(this.other);
        }
    }

    public FlowableTakeUntil(e<T> eVar, aaq<? extends U> aaq) {
        super(eVar);
        this.c = aaq;
    }

    /* Access modifiers changed, original: protected */
    public void b(aar<? super T> aar) {
        f takeUntilMainSubscriber = new TakeUntilMainSubscriber(aar);
        aar.onSubscribe(takeUntilMainSubscriber);
        this.c.a(takeUntilMainSubscriber.other);
        this.b.a(takeUntilMainSubscriber);
    }
}

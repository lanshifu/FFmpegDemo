package io.reactivex.internal.subscriptions;

import defpackage.xa;
import java.util.concurrent.atomic.AtomicInteger;

public final class ScalarSubscription<T> extends AtomicInteger implements xa<T> {
    static final int CANCELLED = 2;
    static final int NO_REQUEST = 0;
    static final int REQUESTED = 1;
    private static final long serialVersionUID = -3830916580126663321L;
    final aar<? super T> subscriber;
    final T value;

    public int requestFusion(int i) {
        return i & 1;
    }

    public ScalarSubscription(aar<? super T> aar, T t) {
        this.subscriber = aar;
        this.value = t;
    }

    public void request(long j) {
        if (SubscriptionHelper.validate(j) && compareAndSet(0, 1)) {
            aar aar = this.subscriber;
            aar.onNext(this.value);
            if (get() != 2) {
                aar.onComplete();
            }
        }
    }

    public void cancel() {
        lazySet(2);
    }

    public boolean isCancelled() {
        return get() == 2;
    }

    public boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public T poll() {
        if (get() != 0) {
            return null;
        }
        lazySet(1);
        return this.value;
    }

    public boolean isEmpty() {
        return get() != 0;
    }

    public void clear() {
        lazySet(1);
    }
}

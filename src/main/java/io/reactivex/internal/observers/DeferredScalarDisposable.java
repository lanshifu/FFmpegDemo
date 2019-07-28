package io.reactivex.internal.observers;

import defpackage.xk;
import io.reactivex.r;

public class DeferredScalarDisposable<T> extends BasicIntQueueDisposable<T> {
    static final int DISPOSED = 4;
    static final int FUSED_CONSUMED = 32;
    static final int FUSED_EMPTY = 8;
    static final int FUSED_READY = 16;
    static final int TERMINATED = 2;
    private static final long serialVersionUID = -5502432239815349361L;
    protected final r<? super T> downstream;
    protected T value;

    public DeferredScalarDisposable(r<? super T> rVar) {
        this.downstream = rVar;
    }

    public final int requestFusion(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    public final void complete(T t) {
        int i = get();
        if ((i & 54) == 0) {
            r rVar = this.downstream;
            if (i == 8) {
                this.value = t;
                lazySet(16);
                rVar.onNext(null);
            } else {
                lazySet(2);
                rVar.onNext(t);
            }
            if (get() != 4) {
                rVar.onComplete();
            }
        }
    }

    public final void error(Throwable th) {
        if ((get() & 54) != 0) {
            xk.a(th);
            return;
        }
        lazySet(2);
        this.downstream.onError(th);
    }

    public final void complete() {
        if ((get() & 54) == 0) {
            lazySet(2);
            this.downstream.onComplete();
        }
    }

    public final T poll() throws Exception {
        if (get() != 16) {
            return null;
        }
        Object obj = this.value;
        this.value = null;
        lazySet(32);
        return obj;
    }

    public final boolean isEmpty() {
        return get() != 16;
    }

    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    public void dispose() {
        set(4);
        this.value = null;
    }

    public final boolean tryDispose() {
        return getAndSet(4) != 4;
    }

    public final boolean isDisposed() {
        return get() == 4;
    }
}

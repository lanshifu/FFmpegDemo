package io.reactivex.internal.schedulers;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.a;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class ScheduledRunnable extends AtomicReferenceArray<Object> implements b, Runnable, Callable<Object> {
    static final Object ASYNC_DISPOSED = new Object();
    static final Object DONE = new Object();
    static final int FUTURE_INDEX = 1;
    static final Object PARENT_DISPOSED = new Object();
    static final int PARENT_INDEX = 0;
    static final Object SYNC_DISPOSED = new Object();
    static final int THREAD_INDEX = 2;
    private static final long serialVersionUID = -6120223772001106981L;
    final Runnable actual;

    public ScheduledRunnable(Runnable runnable, a aVar) {
        super(3);
        this.actual = runnable;
        lazySet(0, aVar);
    }

    public Object call() {
        run();
        return null;
    }

    public void run() {
        Object obj;
        lazySet(2, Thread.currentThread());
        try {
            this.actual.run();
        } catch (Throwable th) {
            lazySet(2, null);
            obj = get(0);
            if (!(obj == PARENT_DISPOSED || !compareAndSet(0, obj, DONE) || obj == null)) {
                ((a) obj).c(this);
            }
            while (true) {
                obj = get(1);
                if (obj == SYNC_DISPOSED || obj == ASYNC_DISPOSED || compareAndSet(1, obj, DONE)) {
                }
            }
        }
        lazySet(2, null);
        obj = get(0);
        if (!(obj == PARENT_DISPOSED || !compareAndSet(0, obj, DONE) || obj == null)) {
            ((a) obj).c(this);
        }
        do {
            obj = get(1);
            if (obj == SYNC_DISPOSED || obj == ASYNC_DISPOSED) {
                return;
            }
        } while (!compareAndSet(1, obj, DONE));
    }

    public void setFuture(Future<?> future) {
        Object obj;
        do {
            obj = get(1);
            if (obj != DONE) {
                if (obj == SYNC_DISPOSED) {
                    future.cancel(false);
                    return;
                } else if (obj == ASYNC_DISPOSED) {
                    future.cancel(true);
                    return;
                }
            } else {
                return;
            }
        } while (!compareAndSet(1, obj, future));
    }

    public void dispose() {
        Object obj;
        boolean z;
        Object obj2;
        do {
            obj = get(1);
            if (obj == DONE || obj == SYNC_DISPOSED || obj == ASYNC_DISPOSED) {
                break;
            }
            z = get(2) != Thread.currentThread();
        } while (!compareAndSet(1, obj, z ? ASYNC_DISPOSED : SYNC_DISPOSED));
        if (obj != null) {
            ((Future) obj).cancel(z);
        }
        do {
            obj2 = get(0);
            if (obj2 == DONE || obj2 == PARENT_DISPOSED || obj2 == null) {
                return;
            }
        } while (!compareAndSet(0, obj2, PARENT_DISPOSED));
        ((a) obj2).c(this);
    }

    public boolean isDisposed() {
        Object obj = get(0);
        if (obj == PARENT_DISPOSED || obj == DONE) {
            return true;
        }
        return false;
    }
}

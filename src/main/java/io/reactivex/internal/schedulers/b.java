package io.reactivex.internal.schedulers;

import defpackage.xk;
import io.reactivex.internal.functions.Functions;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: InstantPeriodicTask */
final class b implements io.reactivex.disposables.b, Callable<Void> {
    static final FutureTask<Void> f = new FutureTask(Functions.b, null);
    final Runnable a;
    final AtomicReference<Future<?>> b = new AtomicReference();
    final AtomicReference<Future<?>> c = new AtomicReference();
    final ExecutorService d;
    Thread e;

    b(Runnable runnable, ExecutorService executorService) {
        this.a = runnable;
        this.d = executorService;
    }

    /* renamed from: a */
    public Void call() throws Exception {
        this.e = Thread.currentThread();
        try {
            this.a.run();
            b(this.d.submit(this));
            this.e = null;
        } catch (Throwable th) {
            this.e = null;
            xk.a(th);
        }
        return null;
    }

    public void dispose() {
        Future future = (Future) this.c.getAndSet(f);
        boolean z = false;
        if (!(future == null || future == f)) {
            future.cancel(this.e != Thread.currentThread());
        }
        future = (Future) this.b.getAndSet(f);
        if (future != null && future != f) {
            if (this.e != Thread.currentThread()) {
                z = true;
            }
            future.cancel(z);
        }
    }

    public boolean isDisposed() {
        return this.c.get() == f;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Future<?> future) {
        Future future2;
        do {
            future2 = (Future) this.c.get();
            if (future2 == f) {
                future.cancel(this.e != Thread.currentThread());
                return;
            }
        } while (!this.c.compareAndSet(future2, future));
    }

    /* Access modifiers changed, original: 0000 */
    public void b(Future<?> future) {
        Future future2;
        do {
            future2 = (Future) this.b.get();
            if (future2 == f) {
                future.cancel(this.e != Thread.currentThread());
                return;
            }
        } while (!this.b.compareAndSet(future2, future));
    }
}

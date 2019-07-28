package io.reactivex.internal.schedulers;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.s;
import io.reactivex.s.c;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: SingleScheduler */
public final class h extends s {
    static final RxThreadFactory d = new RxThreadFactory("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    static final ScheduledExecutorService e = Executors.newScheduledThreadPool(0);
    final ThreadFactory b;
    final AtomicReference<ScheduledExecutorService> c;

    /* compiled from: SingleScheduler */
    static final class a extends c {
        final ScheduledExecutorService a;
        final io.reactivex.disposables.a b = new io.reactivex.disposables.a();
        volatile boolean c;

        a(ScheduledExecutorService scheduledExecutorService) {
            this.a = scheduledExecutorService;
        }

        public b a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.c) {
                return EmptyDisposable.INSTANCE;
            }
            Future submit;
            b scheduledRunnable = new ScheduledRunnable(xk.a(runnable), this.b);
            this.b.a(scheduledRunnable);
            if (j <= 0) {
                try {
                    submit = this.a.submit(scheduledRunnable);
                } catch (RejectedExecutionException e) {
                    dispose();
                    xk.a(e);
                    return EmptyDisposable.INSTANCE;
                }
            }
            submit = this.a.schedule(scheduledRunnable, j, timeUnit);
            scheduledRunnable.setFuture(submit);
            return scheduledRunnable;
        }

        public void dispose() {
            if (!this.c) {
                this.c = true;
                this.b.dispose();
            }
        }

        public boolean isDisposed() {
            return this.c;
        }
    }

    static {
        e.shutdown();
    }

    public h() {
        this(d);
    }

    public h(ThreadFactory threadFactory) {
        this.c = new AtomicReference();
        this.b = threadFactory;
        this.c.lazySet(a(threadFactory));
    }

    static ScheduledExecutorService a(ThreadFactory threadFactory) {
        return g.a(threadFactory);
    }

    public void b() {
        ScheduledExecutorService scheduledExecutorService = null;
        ScheduledExecutorService scheduledExecutorService2;
        do {
            scheduledExecutorService2 = (ScheduledExecutorService) this.c.get();
            if (scheduledExecutorService2 != e) {
                if (scheduledExecutorService != null) {
                    scheduledExecutorService.shutdown();
                }
                return;
            } else if (scheduledExecutorService == null) {
                scheduledExecutorService = a(this.b);
            }
        } while (!this.c.compareAndSet(scheduledExecutorService2, scheduledExecutorService));
    }

    public c a() {
        return new a((ScheduledExecutorService) this.c.get());
    }

    public b a(Runnable runnable, long j, TimeUnit timeUnit) {
        Future submit;
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(xk.a(runnable));
        if (j <= 0) {
            try {
                submit = ((ScheduledExecutorService) this.c.get()).submit(scheduledDirectTask);
            } catch (RejectedExecutionException e) {
                xk.a(e);
                return EmptyDisposable.INSTANCE;
            }
        }
        submit = ((ScheduledExecutorService) this.c.get()).schedule(scheduledDirectTask, j, timeUnit);
        scheduledDirectTask.setFuture(submit);
        return scheduledDirectTask;
    }

    public b a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        runnable = xk.a(runnable);
        if (j2 <= 0) {
            Future submit;
            ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) this.c.get();
            b bVar = new b(runnable, scheduledExecutorService);
            if (j <= 0) {
                try {
                    submit = scheduledExecutorService.submit(bVar);
                } catch (RejectedExecutionException e) {
                    xk.a(e);
                    return EmptyDisposable.INSTANCE;
                }
            }
            submit = scheduledExecutorService.schedule(bVar, j, timeUnit);
            bVar.a(submit);
            return bVar;
        }
        ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(runnable);
        try {
            scheduledDirectPeriodicTask.setFuture(((ScheduledExecutorService) this.c.get()).scheduleAtFixedRate(scheduledDirectPeriodicTask, j, j2, timeUnit));
            return scheduledDirectPeriodicTask;
        } catch (RejectedExecutionException e2) {
            xk.a(e2);
            return EmptyDisposable.INSTANCE;
        }
    }
}

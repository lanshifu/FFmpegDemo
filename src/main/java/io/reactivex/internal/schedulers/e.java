package io.reactivex.internal.schedulers;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.a;
import io.reactivex.s.c;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* compiled from: NewThreadWorker */
public class e extends c implements b {
    volatile boolean a;
    private final ScheduledExecutorService b;

    public e(ThreadFactory threadFactory) {
        this.b = g.a(threadFactory);
    }

    public b a(Runnable runnable) {
        return a(runnable, 0, null);
    }

    public b a(Runnable runnable, long j, TimeUnit timeUnit) {
        if (this.a) {
            return EmptyDisposable.INSTANCE;
        }
        return a(runnable, j, timeUnit, null);
    }

    public b b(Runnable runnable, long j, TimeUnit timeUnit) {
        Future submit;
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(xk.a(runnable));
        if (j <= 0) {
            try {
                submit = this.b.submit(scheduledDirectTask);
            } catch (RejectedExecutionException e) {
                xk.a(e);
                return EmptyDisposable.INSTANCE;
            }
        }
        submit = this.b.schedule(scheduledDirectTask, j, timeUnit);
        scheduledDirectTask.setFuture(submit);
        return scheduledDirectTask;
    }

    public b b(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        runnable = xk.a(runnable);
        if (j2 <= 0) {
            Future submit;
            b bVar = new b(runnable, this.b);
            if (j <= 0) {
                try {
                    submit = this.b.submit(bVar);
                } catch (RejectedExecutionException e) {
                    xk.a(e);
                    return EmptyDisposable.INSTANCE;
                }
            }
            submit = this.b.schedule(bVar, j, timeUnit);
            bVar.a(submit);
            return bVar;
        }
        ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(runnable);
        try {
            scheduledDirectPeriodicTask.setFuture(this.b.scheduleAtFixedRate(scheduledDirectPeriodicTask, j, j2, timeUnit));
            return scheduledDirectPeriodicTask;
        } catch (RejectedExecutionException e2) {
            xk.a(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    public ScheduledRunnable a(Runnable runnable, long j, TimeUnit timeUnit, a aVar) {
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(xk.a(runnable), aVar);
        if (aVar != null && !aVar.a(scheduledRunnable)) {
            return scheduledRunnable;
        }
        Future submit;
        if (j <= 0) {
            try {
                submit = this.b.submit(scheduledRunnable);
            } catch (RejectedExecutionException e) {
                if (aVar != null) {
                    aVar.b(scheduledRunnable);
                }
                xk.a(e);
            }
        } else {
            submit = this.b.schedule(scheduledRunnable, j, timeUnit);
        }
        scheduledRunnable.setFuture(submit);
        return scheduledRunnable;
    }

    public void dispose() {
        if (!this.a) {
            this.a = true;
            this.b.shutdownNow();
        }
    }

    public void b() {
        if (!this.a) {
            this.a = true;
            this.b.shutdown();
        }
    }

    public boolean isDisposed() {
        return this.a;
    }
}

package io.fabric.sdk.android.services.concurrency;

import android.annotation.TargetApi;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: PriorityThreadPoolExecutor */
public class h extends ThreadPoolExecutor {
    private static final int a = Runtime.getRuntime().availableProcessors();
    private static final int b = (a + 1);
    private static final int c = ((a * 2) + 1);

    /* compiled from: PriorityThreadPoolExecutor */
    protected static final class a implements ThreadFactory {
        private final int a;

        public a(int i) {
            this.a = i;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(this.a);
            thread.setName("Queue");
            return thread;
        }
    }

    <T extends Runnable & a & i & f> h(int i, int i2, long j, TimeUnit timeUnit, DependencyPriorityBlockingQueue<T> dependencyPriorityBlockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, dependencyPriorityBlockingQueue, threadFactory);
        prestartAllCoreThreads();
    }

    public static <T extends Runnable & a & i & f> h a(int i, int i2) {
        return new h(i, i2, 1, TimeUnit.SECONDS, new DependencyPriorityBlockingQueue(), new a(10));
    }

    public static h a() {
        return a(b, c);
    }

    /* Access modifiers changed, original: protected */
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new e(runnable, t);
    }

    /* Access modifiers changed, original: protected */
    public <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new e(callable);
    }

    @TargetApi(9)
    public void execute(Runnable runnable) {
        if (g.a((Object) runnable)) {
            super.execute(runnable);
        } else {
            super.execute(newTaskFor(runnable, null));
        }
    }

    /* Access modifiers changed, original: protected */
    public void afterExecute(Runnable runnable, Throwable th) {
        i iVar = (i) runnable;
        iVar.b(true);
        iVar.a(th);
        getQueue().recycleBlockedQueue();
        super.afterExecute(runnable, th);
    }

    /* renamed from: b */
    public DependencyPriorityBlockingQueue getQueue() {
        return (DependencyPriorityBlockingQueue) super.getQueue();
    }
}

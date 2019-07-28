package io.fabric.sdk.android.services.concurrency;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AsyncTask<Params, Progress, Result> {
    private static final int a = Runtime.getRuntime().availableProcessors();
    public static final Executor b = new ThreadPoolExecutor(d, e, 1, TimeUnit.SECONDS, g, f);
    public static final Executor c = new c();
    private static final int d = (a + 1);
    private static final int e = ((a * 2) + 1);
    private static final ThreadFactory f = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("AsyncTask #");
            stringBuilder.append(this.a.getAndIncrement());
            return new Thread(runnable, stringBuilder.toString());
        }
    };
    private static final BlockingQueue<Runnable> g = new LinkedBlockingQueue(128);
    private static final b h = new b();
    private static volatile Executor i = c;
    private final d<Params, Result> j = new d<Params, Result>() {
        public Result call() throws Exception {
            AsyncTask.this.n.set(true);
            Process.setThreadPriority(10);
            return AsyncTask.this.e(AsyncTask.this.a(this.b));
        }
    };
    private final FutureTask<Result> k = new FutureTask<Result>(this.j) {
        /* Access modifiers changed, original: protected */
        public void done() {
            try {
                AsyncTask.this.d(get());
            } catch (InterruptedException e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException unused) {
                AsyncTask.this.d(null);
            }
        }
    };
    private volatile Status l = Status.PENDING;
    private final AtomicBoolean m = new AtomicBoolean();
    private final AtomicBoolean n = new AtomicBoolean();

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    private static class a<Data> {
        final AsyncTask a;
        final Data[] b;

        a(AsyncTask asyncTask, Data... dataArr) {
            this.a = asyncTask;
            this.b = dataArr;
        }
    }

    private static class b extends Handler {
        public b() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            a aVar = (a) message.obj;
            switch (message.what) {
                case 1:
                    aVar.a.f(aVar.b[0]);
                    return;
                case 2:
                    aVar.a.b(aVar.b);
                    return;
                default:
                    return;
            }
        }
    }

    private static class c implements Executor {
        final LinkedList<Runnable> a;
        Runnable b;

        private c() {
            this.a = new LinkedList();
        }

        /* synthetic */ c(AnonymousClass1 anonymousClass1) {
            this();
        }

        public synchronized void execute(final Runnable runnable) {
            this.a.offer(new Runnable() {
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        c.this.a();
                    }
                }
            });
            if (this.b == null) {
                a();
            }
        }

        /* Access modifiers changed, original: protected|declared_synchronized */
        public synchronized void a() {
            Runnable runnable = (Runnable) this.a.poll();
            this.b = runnable;
            if (runnable != null) {
                AsyncTask.b.execute(this.b);
            }
        }
    }

    private static abstract class d<Params, Result> implements Callable<Result> {
        Params[] b;

        private d() {
        }

        /* synthetic */ d(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public abstract Result a(Params... paramsArr);

    /* Access modifiers changed, original: protected */
    public void a() {
    }

    /* Access modifiers changed, original: protected */
    public void a(Result result) {
    }

    /* Access modifiers changed, original: protected|varargs */
    public void b(Progress... progressArr) {
    }

    /* Access modifiers changed, original: protected */
    public void i_() {
    }

    private void d(Result result) {
        if (!this.n.get()) {
            e(result);
        }
    }

    private Result e(Result result) {
        h.obtainMessage(1, new a(this, result)).sendToTarget();
        return result;
    }

    public final Status h_() {
        return this.l;
    }

    /* Access modifiers changed, original: protected */
    public void b(Result result) {
        i_();
    }

    public final boolean e() {
        return this.m.get();
    }

    public final boolean a(boolean z) {
        this.m.set(true);
        return this.k.cancel(z);
    }

    public final AsyncTask<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.l != Status.PENDING) {
            switch (this.l) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.l = Status.RUNNING;
        a();
        this.j.b = paramsArr;
        executor.execute(this.k);
        return this;
    }

    private void f(Result result) {
        if (e()) {
            b((Object) result);
        } else {
            a((Object) result);
        }
        this.l = Status.FINISHED;
    }
}

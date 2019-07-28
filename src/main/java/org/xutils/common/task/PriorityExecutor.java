package org.xutils.common.task;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PriorityExecutor implements Executor {
    private static final AtomicLong a = new AtomicLong(0);
    private static final ThreadFactory b = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("xTID#");
            stringBuilder.append(this.a.getAndIncrement());
            return new Thread(runnable, stringBuilder.toString());
        }
    };
    private static final Comparator<Runnable> c = new Comparator<Runnable>() {
        /* renamed from: a */
        public int compare(Runnable runnable, Runnable runnable2) {
            if (!(runnable instanceof a) || !(runnable2 instanceof a)) {
                return 0;
            }
            a aVar = (a) runnable;
            a aVar2 = (a) runnable2;
            int ordinal = aVar.b.ordinal() - aVar2.b.ordinal();
            if (ordinal == 0) {
                ordinal = (int) (aVar.a - aVar2.a);
            }
            return ordinal;
        }
    };
    private static final Comparator<Runnable> d = new Comparator<Runnable>() {
        /* renamed from: a */
        public int compare(Runnable runnable, Runnable runnable2) {
            if (!(runnable instanceof a) || !(runnable2 instanceof a)) {
                return 0;
            }
            a aVar = (a) runnable;
            a aVar2 = (a) runnable2;
            int ordinal = aVar.b.ordinal() - aVar2.b.ordinal();
            if (ordinal == 0) {
                ordinal = (int) (aVar2.a - aVar.a);
            }
            return ordinal;
        }
    };
    private final ThreadPoolExecutor e;

    public PriorityExecutor(boolean z) {
        this(5, z);
    }

    public PriorityExecutor(int i, boolean z) {
        this.e = new ThreadPoolExecutor(i, 256, 1, TimeUnit.SECONDS, new PriorityBlockingQueue(256, z ? c : d), b);
    }

    public int getPoolSize() {
        return this.e.getCorePoolSize();
    }

    public void setPoolSize(int i) {
        if (i > 0) {
            this.e.setCorePoolSize(i);
        }
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return this.e;
    }

    public boolean isBusy() {
        return this.e.getActiveCount() >= this.e.getCorePoolSize();
    }

    public void execute(Runnable runnable) {
        if (runnable instanceof a) {
            ((a) runnable).a = a.getAndIncrement();
        }
        this.e.execute(runnable);
    }
}

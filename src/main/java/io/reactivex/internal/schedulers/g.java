package io.reactivex.internal.schedulers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: SchedulerPoolFactory */
public final class g {
    public static final boolean a;
    public static final int b;
    static final AtomicReference<ScheduledExecutorService> c = new AtomicReference();
    static final Map<ScheduledThreadPoolExecutor, Object> d = new ConcurrentHashMap();

    /* compiled from: SchedulerPoolFactory */
    static final class a {
        boolean a;
        int b;

        a() {
        }

        /* Access modifiers changed, original: 0000 */
        public void a(Properties properties) {
            if (properties.containsKey("rx2.purge-enabled")) {
                this.a = Boolean.parseBoolean(properties.getProperty("rx2.purge-enabled"));
            } else {
                this.a = true;
            }
            if (this.a && properties.containsKey("rx2.purge-period-seconds")) {
                try {
                    this.b = Integer.parseInt(properties.getProperty("rx2.purge-period-seconds"));
                    return;
                } catch (NumberFormatException unused) {
                    this.b = 1;
                    return;
                }
            }
            this.b = 1;
        }
    }

    /* compiled from: SchedulerPoolFactory */
    static final class b implements Runnable {
        b() {
        }

        public void run() {
            Iterator it = new ArrayList(g.d.keySet()).iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it.next();
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    g.d.remove(scheduledThreadPoolExecutor);
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        }
    }

    static {
        Properties properties = System.getProperties();
        a aVar = new a();
        aVar.a(properties);
        a = aVar.a;
        b = aVar.b;
        a();
    }

    public static void a() {
        a(a);
    }

    static void a(boolean z) {
        if (z) {
            while (true) {
                ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) c.get();
                if (scheduledExecutorService == null) {
                    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge"));
                    if (c.compareAndSet(scheduledExecutorService, newScheduledThreadPool)) {
                        newScheduledThreadPool.scheduleAtFixedRate(new b(), (long) b, (long) b, TimeUnit.SECONDS);
                        return;
                    }
                    newScheduledThreadPool.shutdownNow();
                } else {
                    return;
                }
            }
        }
    }

    public static ScheduledExecutorService a(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        a(a, newScheduledThreadPool);
        return newScheduledThreadPool;
    }

    static void a(boolean z, ScheduledExecutorService scheduledExecutorService) {
        if (z && (scheduledExecutorService instanceof ScheduledThreadPoolExecutor)) {
            d.put((ScheduledThreadPoolExecutor) scheduledExecutorService, scheduledExecutorService);
        }
    }
}

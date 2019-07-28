package io.reactivex.internal.schedulers;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.s;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: IoScheduler */
public final class c extends s {
    static final RxThreadFactory b;
    static final RxThreadFactory c;
    static final c d = new c(new RxThreadFactory("RxCachedThreadSchedulerShutdown"));
    static final a g = new a(0, null, b);
    private static final TimeUnit h = TimeUnit.SECONDS;
    final ThreadFactory e;
    final AtomicReference<a> f;

    /* compiled from: IoScheduler */
    static final class a implements Runnable {
        final io.reactivex.disposables.a a;
        private final long b;
        private final ConcurrentLinkedQueue<c> c;
        private final ScheduledExecutorService d;
        private final Future<?> e;
        private final ThreadFactory f;

        a(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
            Future scheduleWithFixedDelay;
            this.b = timeUnit != null ? timeUnit.toNanos(j) : 0;
            this.c = new ConcurrentLinkedQueue();
            this.a = new io.reactivex.disposables.a();
            this.f = threadFactory;
            ScheduledExecutorService scheduledExecutorService = null;
            if (timeUnit != null) {
                scheduledExecutorService = Executors.newScheduledThreadPool(1, c.c);
                scheduleWithFixedDelay = scheduledExecutorService.scheduleWithFixedDelay(this, this.b, this.b, TimeUnit.NANOSECONDS);
            } else {
                scheduleWithFixedDelay = null;
            }
            this.d = scheduledExecutorService;
            this.e = scheduleWithFixedDelay;
        }

        public void run() {
            b();
        }

        /* Access modifiers changed, original: 0000 */
        public c a() {
            if (this.a.isDisposed()) {
                return c.d;
            }
            while (!this.c.isEmpty()) {
                c cVar = (c) this.c.poll();
                if (cVar != null) {
                    return cVar;
                }
            }
            io.reactivex.disposables.b cVar2 = new c(this.f);
            this.a.a(cVar2);
            return cVar2;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(c cVar) {
            cVar.a(c() + this.b);
            this.c.offer(cVar);
        }

        /* Access modifiers changed, original: 0000 */
        public void b() {
            if (!this.c.isEmpty()) {
                long c = c();
                Iterator it = this.c.iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    if (cVar.a() > c) {
                        return;
                    }
                    if (this.c.remove(cVar)) {
                        this.a.b(cVar);
                    }
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public long c() {
            return System.nanoTime();
        }

        /* Access modifiers changed, original: 0000 */
        public void d() {
            this.a.dispose();
            if (this.e != null) {
                this.e.cancel(true);
            }
            if (this.d != null) {
                this.d.shutdownNow();
            }
        }
    }

    /* compiled from: IoScheduler */
    static final class b extends io.reactivex.s.c {
        final AtomicBoolean a = new AtomicBoolean();
        private final io.reactivex.disposables.a b;
        private final a c;
        private final c d;

        b(a aVar) {
            this.c = aVar;
            this.b = new io.reactivex.disposables.a();
            this.d = aVar.a();
        }

        public void dispose() {
            if (this.a.compareAndSet(false, true)) {
                this.b.dispose();
                this.c.a(this.d);
            }
        }

        public boolean isDisposed() {
            return this.a.get();
        }

        public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.b.isDisposed()) {
                return EmptyDisposable.INSTANCE;
            }
            return this.d.a(runnable, j, timeUnit, this.b);
        }
    }

    /* compiled from: IoScheduler */
    static final class c extends e {
        private long b = 0;

        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }

        public long a() {
            return this.b;
        }

        public void a(long j) {
            this.b = j;
        }
    }

    static {
        d.dispose();
        int max = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        b = new RxThreadFactory("RxCachedThreadScheduler", max);
        c = new RxThreadFactory("RxCachedWorkerPoolEvictor", max);
        g.d();
    }

    public c() {
        this(b);
    }

    public c(ThreadFactory threadFactory) {
        this.e = threadFactory;
        this.f = new AtomicReference(g);
        b();
    }

    public void b() {
        a aVar = new a(60, h, this.e);
        if (!this.f.compareAndSet(g, aVar)) {
            aVar.d();
        }
    }

    public io.reactivex.s.c a() {
        return new b((a) this.f.get());
    }
}

package io.reactivex.internal.schedulers;

import defpackage.xk;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.s;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: TrampolineScheduler */
public final class i extends s {
    private static final i b = new i();

    /* compiled from: TrampolineScheduler */
    static final class a implements Runnable {
        private final Runnable a;
        private final c b;
        private final long c;

        a(Runnable runnable, c cVar, long j) {
            this.a = runnable;
            this.b = cVar;
            this.c = j;
        }

        public void run() {
            if (!this.b.c) {
                long a = this.b.a(TimeUnit.MILLISECONDS);
                if (this.c > a) {
                    try {
                        Thread.sleep(this.c - a);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        xk.a(e);
                        return;
                    }
                }
                if (!this.b.c) {
                    this.a.run();
                }
            }
        }
    }

    /* compiled from: TrampolineScheduler */
    static final class b implements Comparable<b> {
        final Runnable a;
        final long b;
        final int c;
        volatile boolean d;

        b(Runnable runnable, Long l, int i) {
            this.a = runnable;
            this.b = l.longValue();
            this.c = i;
        }

        /* renamed from: a */
        public int compareTo(b bVar) {
            int a = io.reactivex.internal.functions.a.a(this.b, bVar.b);
            return a == 0 ? io.reactivex.internal.functions.a.a(this.c, bVar.c) : a;
        }
    }

    /* compiled from: TrampolineScheduler */
    static final class c extends io.reactivex.s.c implements io.reactivex.disposables.b {
        final PriorityBlockingQueue<b> a = new PriorityBlockingQueue();
        final AtomicInteger b = new AtomicInteger();
        volatile boolean c;
        private final AtomicInteger d = new AtomicInteger();

        /* compiled from: TrampolineScheduler */
        final class a implements Runnable {
            final b a;

            a(b bVar) {
                this.a = bVar;
            }

            public void run() {
                this.a.d = true;
                c.this.a.remove(this.a);
            }
        }

        c() {
        }

        public io.reactivex.disposables.b a(Runnable runnable) {
            return a(runnable, a(TimeUnit.MILLISECONDS));
        }

        public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            long a = a(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return a(new a(runnable, this, a), a);
        }

        /* Access modifiers changed, original: 0000 */
        public io.reactivex.disposables.b a(Runnable runnable, long j) {
            if (this.c) {
                return EmptyDisposable.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j), this.b.incrementAndGet());
            this.a.add(bVar);
            if (this.d.getAndIncrement() != 0) {
                return io.reactivex.disposables.c.a(new a(bVar));
            }
            int i = 1;
            while (!this.c) {
                b bVar2 = (b) this.a.poll();
                if (bVar2 == null) {
                    i = this.d.addAndGet(-i);
                    if (i == 0) {
                        return EmptyDisposable.INSTANCE;
                    }
                } else if (!bVar2.d) {
                    bVar2.a.run();
                }
            }
            this.a.clear();
            return EmptyDisposable.INSTANCE;
        }

        public void dispose() {
            this.c = true;
        }

        public boolean isDisposed() {
            return this.c;
        }
    }

    public static i c() {
        return b;
    }

    public io.reactivex.s.c a() {
        return new c();
    }

    i() {
    }

    public io.reactivex.disposables.b a(Runnable runnable) {
        xk.a(runnable).run();
        return EmptyDisposable.INSTANCE;
    }

    public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            xk.a(runnable).run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            xk.a(e);
        }
        return EmptyDisposable.INSTANCE;
    }
}

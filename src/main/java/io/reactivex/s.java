package io.reactivex;

import defpackage.xk;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.schedulers.e;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.TimeUnit;

/* compiled from: Scheduler */
public abstract class s {
    static final long a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    /* compiled from: Scheduler */
    static final class a implements io.reactivex.disposables.b, Runnable {
        final Runnable a;
        final c b;
        Thread c;

        a(Runnable runnable, c cVar) {
            this.a = runnable;
            this.b = cVar;
        }

        public void run() {
            this.c = Thread.currentThread();
            try {
                this.a.run();
            } finally {
                dispose();
                this.c = null;
            }
        }

        public void dispose() {
            if (this.c == Thread.currentThread() && (this.b instanceof e)) {
                ((e) this.b).b();
            } else {
                this.b.dispose();
            }
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }
    }

    /* compiled from: Scheduler */
    static final class b implements io.reactivex.disposables.b, Runnable {
        final Runnable a;
        final c b;
        volatile boolean c;

        b(Runnable runnable, c cVar) {
            this.a = runnable;
            this.b = cVar;
        }

        public void run() {
            if (!this.c) {
                try {
                    this.a.run();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.b.dispose();
                    RuntimeException a = ExceptionHelper.a(th);
                }
            }
        }

        public void dispose() {
            this.c = true;
            this.b.dispose();
        }

        public boolean isDisposed() {
            return this.c;
        }
    }

    /* compiled from: Scheduler */
    public static abstract class c implements io.reactivex.disposables.b {

        /* compiled from: Scheduler */
        final class a implements Runnable {
            final Runnable a;
            final SequentialDisposable b;
            final long c;
            long d;
            long e;
            long f;

            a(long j, Runnable runnable, long j2, SequentialDisposable sequentialDisposable, long j3) {
                this.a = runnable;
                this.b = sequentialDisposable;
                this.c = j3;
                this.e = j2;
                this.f = j;
            }

            public void run() {
                this.a.run();
                if (!this.b.isDisposed()) {
                    long j;
                    long a = c.this.a(TimeUnit.NANOSECONDS);
                    long j2;
                    if (s.a + a < this.e || a >= (this.e + this.c) + s.a) {
                        j = this.c + a;
                        j2 = this.c;
                        long j3 = this.d + 1;
                        this.d = j3;
                        this.f = j - (j2 * j3);
                    } else {
                        j = this.f;
                        j2 = this.d + 1;
                        this.d = j2;
                        j += j2 * this.c;
                    }
                    this.e = a;
                    this.b.replace(c.this.a(this, j - a, TimeUnit.NANOSECONDS));
                }
            }
        }

        public abstract io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit);

        public io.reactivex.disposables.b a(Runnable runnable) {
            return a(runnable, 0, TimeUnit.NANOSECONDS);
        }

        public io.reactivex.disposables.b a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            long j3 = j;
            TimeUnit timeUnit2 = timeUnit;
            SequentialDisposable sequentialDisposable = new SequentialDisposable();
            SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
            Runnable a = xk.a(runnable);
            long toNanos = timeUnit2.toNanos(j2);
            long a2 = a(TimeUnit.NANOSECONDS);
            SequentialDisposable sequentialDisposable3 = sequentialDisposable;
            a aVar = r0;
            a aVar2 = new a(a2 + timeUnit2.toNanos(j3), a, a2, sequentialDisposable2, toNanos);
            io.reactivex.disposables.b a3 = a(aVar, j3, timeUnit2);
            if (a3 == EmptyDisposable.INSTANCE) {
                return a3;
            }
            sequentialDisposable3.replace(a3);
            return sequentialDisposable2;
        }

        public long a(TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public abstract c a();

    public void b() {
    }

    public long a(TimeUnit timeUnit) {
        return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public io.reactivex.disposables.b a(Runnable runnable) {
        return a(runnable, 0, TimeUnit.NANOSECONDS);
    }

    public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        c a = a();
        a aVar = new a(xk.a(runnable), a);
        a.a(aVar, j, timeUnit);
        return aVar;
    }

    public io.reactivex.disposables.b a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        c a = a();
        b bVar = new b(xk.a(runnable), a);
        io.reactivex.disposables.b a2 = a.a(bVar, j, j2, timeUnit);
        return a2 == EmptyDisposable.INSTANCE ? a2 : bVar;
    }
}

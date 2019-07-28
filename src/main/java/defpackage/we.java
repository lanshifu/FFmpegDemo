package defpackage;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import io.reactivex.disposables.b;
import io.reactivex.s;
import io.reactivex.s.c;
import java.util.concurrent.TimeUnit;

/* compiled from: HandlerScheduler */
/* renamed from: we */
final class we extends s {
    private final Handler b;
    private final boolean c;

    /* compiled from: HandlerScheduler */
    /* renamed from: we$b */
    private static final class b implements io.reactivex.disposables.b, Runnable {
        private final Handler a;
        private final Runnable b;
        private volatile boolean c;

        b(Handler handler, Runnable runnable) {
            this.a = handler;
            this.b = runnable;
        }

        public void run() {
            try {
                this.b.run();
            } catch (Throwable th) {
                xk.a(th);
            }
        }

        public void dispose() {
            this.a.removeCallbacks(this);
            this.c = true;
        }

        public boolean isDisposed() {
            return this.c;
        }
    }

    /* compiled from: HandlerScheduler */
    /* renamed from: we$a */
    private static final class a extends c {
        private final Handler a;
        private final boolean b;
        private volatile boolean c;

        a(Handler handler, boolean z) {
            this.a = handler;
            this.b = z;
        }

        @SuppressLint({"NewApi"})
        public b a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (runnable == null) {
                throw new NullPointerException("run == null");
            } else if (timeUnit == null) {
                throw new NullPointerException("unit == null");
            } else if (this.c) {
                return io.reactivex.disposables.c.b();
            } else {
                b bVar = new b(this.a, xk.a(runnable));
                Message obtain = Message.obtain(this.a, bVar);
                obtain.obj = this;
                if (this.b) {
                    obtain.setAsynchronous(true);
                }
                this.a.sendMessageDelayed(obtain, timeUnit.toMillis(j));
                if (!this.c) {
                    return bVar;
                }
                this.a.removeCallbacks(bVar);
                return io.reactivex.disposables.c.b();
            }
        }

        public void dispose() {
            this.c = true;
            this.a.removeCallbacksAndMessages(this);
        }

        public boolean isDisposed() {
            return this.c;
        }
    }

    we(Handler handler, boolean z) {
        this.b = handler;
        this.c = z;
    }

    public b a(Runnable runnable, long j, TimeUnit timeUnit) {
        if (runnable == null) {
            throw new NullPointerException("run == null");
        } else if (timeUnit != null) {
            b bVar = new b(this.b, xk.a(runnable));
            this.b.postDelayed(bVar, timeUnit.toMillis(j));
            return bVar;
        } else {
            throw new NullPointerException("unit == null");
        }
    }

    public c a() {
        return new a(this.b, this.c);
    }
}

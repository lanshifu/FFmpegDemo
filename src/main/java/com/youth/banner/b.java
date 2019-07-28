package com.youth.banner;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: WeakHandler */
public class b {
    @VisibleForTesting
    final a a = new a(this.d, null);
    private final Callback b = null;
    private final b c = new b();
    private Lock d = new ReentrantLock();

    /* compiled from: WeakHandler */
    static class a {
        @Nullable
        a a;
        @Nullable
        a b;
        @NonNull
        final Runnable c;
        @NonNull
        final c d;
        @NonNull
        Lock e;

        public a(@NonNull Lock lock, @NonNull Runnable runnable) {
            this.c = runnable;
            this.e = lock;
            this.d = new c(new WeakReference(runnable), new WeakReference(this));
        }

        public c a() {
            this.e.lock();
            try {
                if (this.b != null) {
                    this.b.a = this.a;
                }
                if (this.a != null) {
                    this.a.b = this.b;
                }
                this.b = null;
                this.a = null;
                return this.d;
            } finally {
                this.e.unlock();
            }
        }

        public void a(@NonNull a aVar) {
            this.e.lock();
            try {
                if (this.a != null) {
                    this.a.b = aVar;
                }
                aVar.a = this.a;
                this.a = aVar;
                aVar.b = this;
            } finally {
                this.e.unlock();
            }
        }

        @Nullable
        public c a(Runnable runnable) {
            this.e.lock();
            try {
                for (a aVar = this.a; aVar != null; aVar = aVar.a) {
                    if (aVar.c == runnable) {
                        c a = aVar.a();
                        return a;
                    }
                }
                this.e.unlock();
                return null;
            } finally {
                this.e.unlock();
            }
        }
    }

    /* compiled from: WeakHandler */
    private static class b extends Handler {
        private final WeakReference<Callback> a = null;

        b() {
        }

        public void handleMessage(@NonNull Message message) {
            if (this.a != null) {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.handleMessage(message);
                }
            }
        }
    }

    /* compiled from: WeakHandler */
    static class c implements Runnable {
        private final WeakReference<Runnable> a;
        private final WeakReference<a> b;

        c(WeakReference<Runnable> weakReference, WeakReference<a> weakReference2) {
            this.a = weakReference;
            this.b = weakReference2;
        }

        public void run() {
            Runnable runnable = (Runnable) this.a.get();
            a aVar = (a) this.b.get();
            if (aVar != null) {
                aVar.a();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public final boolean a(@NonNull Runnable runnable) {
        return this.c.post(c(runnable));
    }

    public final boolean a(Runnable runnable, long j) {
        return this.c.postDelayed(c(runnable), j);
    }

    public final void b(Runnable runnable) {
        c a = this.a.a(runnable);
        if (a != null) {
            this.c.removeCallbacks(a);
        }
    }

    private c c(@NonNull Runnable runnable) {
        if (runnable != null) {
            a aVar = new a(this.d, runnable);
            this.a.a(aVar);
            return aVar.d;
        }
        throw new NullPointerException("Runnable can't be null");
    }
}

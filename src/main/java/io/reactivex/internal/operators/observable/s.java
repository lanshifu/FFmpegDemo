package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.TimeUnit;

/* compiled from: ObservableDelay */
public final class s<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final io.reactivex.s d;
    final boolean e;

    /* compiled from: ObservableDelay */
    static final class a<T> implements io.reactivex.disposables.b, r<T> {
        final r<? super T> a;
        final long b;
        final TimeUnit c;
        final io.reactivex.s.c d;
        final boolean e;
        io.reactivex.disposables.b f;

        /* compiled from: ObservableDelay */
        final class a implements Runnable {
            a() {
            }

            public void run() {
                try {
                    a.this.a.onComplete();
                } finally {
                    a.this.d.dispose();
                }
            }
        }

        /* compiled from: ObservableDelay */
        final class b implements Runnable {
            private final Throwable b;

            b(Throwable th) {
                this.b = th;
            }

            public void run() {
                try {
                    a.this.a.onError(this.b);
                } finally {
                    a.this.d.dispose();
                }
            }
        }

        /* compiled from: ObservableDelay */
        final class c implements Runnable {
            private final T b;

            c(T t) {
                this.b = t;
            }

            public void run() {
                a.this.a.onNext(this.b);
            }
        }

        a(r<? super T> rVar, long j, TimeUnit timeUnit, io.reactivex.s.c cVar, boolean z) {
            this.a = rVar;
            this.b = j;
            this.c = timeUnit;
            this.d = cVar;
            this.e = z;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.f, bVar)) {
                this.f = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.d.a(new c(t), this.b, this.c);
        }

        public void onError(Throwable th) {
            this.d.a(new b(th), this.e ? this.b : 0, this.c);
        }

        public void onComplete() {
            this.d.a(new a(), this.b, this.c);
        }

        public void dispose() {
            this.f.dispose();
            this.d.dispose();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }
    }

    public s(p<T> pVar, long j, TimeUnit timeUnit, io.reactivex.s sVar, boolean z) {
        super(pVar);
        this.b = j;
        this.c = timeUnit;
        this.d = sVar;
        this.e = z;
    }

    public void subscribeActual(r<? super T> rVar) {
        r rVar2;
        if (this.e) {
            rVar2 = rVar;
        } else {
            rVar2 = new e(rVar);
        }
        this.a.subscribe(new a(rVar2, this.b, this.c, this.d.a(), this.e));
    }
}

package io.reactivex.internal.operators.observable;

import defpackage.xm;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import java.util.concurrent.TimeUnit;

/* compiled from: ObservableTimeInterval */
public final class bt<T> extends a<T, xm<T>> {
    final s b;
    final TimeUnit c;

    /* compiled from: ObservableTimeInterval */
    static final class a<T> implements b, r<T> {
        final r<? super xm<T>> a;
        final TimeUnit b;
        final s c;
        long d;
        b e;

        a(r<? super xm<T>> rVar, TimeUnit timeUnit, s sVar) {
            this.a = rVar;
            this.c = sVar;
            this.b = timeUnit;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.e, bVar)) {
                this.e = bVar;
                this.d = this.c.a(this.b);
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.e.dispose();
        }

        public boolean isDisposed() {
            return this.e.isDisposed();
        }

        public void onNext(T t) {
            long a = this.c.a(this.b);
            long j = this.d;
            this.d = a;
            this.a.onNext(new xm(t, a - j, this.b));
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    public bt(p<T> pVar, TimeUnit timeUnit, s sVar) {
        super(pVar);
        this.b = sVar;
        this.c = timeUnit;
    }

    public void subscribeActual(r<? super xm<T>> rVar) {
        this.a.subscribe(new a(rVar, this.c, this.b));
    }
}

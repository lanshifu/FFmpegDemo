package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableSkip */
public final class bl<T> extends a<T, T> {
    final long b;

    /* compiled from: ObservableSkip */
    static final class a<T> implements b, r<T> {
        final r<? super T> a;
        long b;
        b c;

        a(r<? super T> rVar, long j) {
            this.a = rVar;
            this.b = j;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.b != 0) {
                this.b--;
            } else {
                this.a.onNext(t);
            }
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }

        public void dispose() {
            this.c.dispose();
        }

        public boolean isDisposed() {
            return this.c.isDisposed();
        }
    }

    public bl(p<T> pVar, long j) {
        super(pVar);
        this.b = j;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar, this.b));
    }
}

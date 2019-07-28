package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableTakeLastOne */
public final class bq<T> extends a<T, T> {

    /* compiled from: ObservableTakeLastOne */
    static final class a<T> implements b, r<T> {
        final r<? super T> a;
        b b;
        T c;

        a(r<? super T> rVar) {
            this.a = rVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.b, bVar)) {
                this.b = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.c = t;
        }

        public void onError(Throwable th) {
            this.c = null;
            this.a.onError(th);
        }

        public void onComplete() {
            a();
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            Object obj = this.c;
            if (obj != null) {
                this.c = null;
                this.a.onNext(obj);
            }
            this.a.onComplete();
        }

        public void dispose() {
            this.c = null;
            this.b.dispose();
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }
    }

    public bq(p<T> pVar) {
        super(pVar);
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar));
    }
}

package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.k;
import io.reactivex.r;

/* compiled from: FlowableFromObservable */
public final class c<T> extends e<T> {
    private final k<T> b;

    /* compiled from: FlowableFromObservable */
    static final class a<T> implements aas, r<T> {
        final aar<? super T> a;
        b b;

        public void request(long j) {
        }

        a(aar<? super T> aar) {
            this.a = aar;
        }

        public void onComplete() {
            this.a.onComplete();
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onNext(T t) {
            this.a.onNext(t);
        }

        public void onSubscribe(b bVar) {
            this.b = bVar;
            this.a.onSubscribe(this);
        }

        public void cancel() {
            this.b.dispose();
        }
    }

    public c(k<T> kVar) {
        this.b = kVar;
    }

    /* Access modifiers changed, original: protected */
    public void b(aar<? super T> aar) {
        this.b.subscribe(new a(aar));
    }
}

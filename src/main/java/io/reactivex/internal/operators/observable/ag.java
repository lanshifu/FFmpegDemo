package io.reactivex.internal.operators.observable;

import defpackage.wv;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableFilter */
public final class ag<T> extends a<T, T> {
    final wv<? super T> b;

    /* compiled from: ObservableFilter */
    static final class a<T> extends io.reactivex.internal.observers.a<T, T> {
        final wv<? super T> f;

        a(r<? super T> rVar, wv<? super T> wvVar) {
            super(rVar);
            this.f = wvVar;
        }

        public void onNext(T t) {
            if (this.e == 0) {
                try {
                    if (this.f.test(t)) {
                        this.a.onNext(t);
                    }
                } catch (Throwable th) {
                    a(th);
                    return;
                }
            }
            this.a.onNext(null);
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public T poll() throws Exception {
            Object poll;
            do {
                poll = this.c.poll();
                if (poll == null) {
                    break;
                }
            } while (!this.f.test(poll));
            return poll;
        }
    }

    public ag(p<T> pVar, wv<? super T> wvVar) {
        super(pVar);
        this.b = wvVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar, this.b));
    }
}

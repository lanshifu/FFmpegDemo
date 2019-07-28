package io.reactivex.internal.operators.observable;

import defpackage.wl;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableDoAfterNext */
public final class y<T> extends a<T, T> {
    final wl<? super T> b;

    /* compiled from: ObservableDoAfterNext */
    static final class a<T> extends io.reactivex.internal.observers.a<T, T> {
        final wl<? super T> f;

        a(r<? super T> rVar, wl<? super T> wlVar) {
            super(rVar);
            this.f = wlVar;
        }

        public void onNext(T t) {
            this.a.onNext(t);
            if (this.e == 0) {
                try {
                    this.f.accept(t);
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public T poll() throws Exception {
            Object poll = this.c.poll();
            if (poll != null) {
                this.f.accept(poll);
            }
            return poll;
        }
    }

    public y(p<T> pVar, wl<? super T> wlVar) {
        super(pVar);
        this.b = wlVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar, this.b));
    }
}

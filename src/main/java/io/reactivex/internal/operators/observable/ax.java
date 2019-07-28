package io.reactivex.internal.operators.observable;

import defpackage.wm;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableMap */
public final class ax<T, U> extends a<T, U> {
    final wm<? super T, ? extends U> b;

    /* compiled from: ObservableMap */
    static final class a<T, U> extends io.reactivex.internal.observers.a<T, U> {
        final wm<? super T, ? extends U> f;

        a(r<? super U> rVar, wm<? super T, ? extends U> wmVar) {
            super(rVar);
            this.f = wmVar;
        }

        public void onNext(T t) {
            if (!this.d) {
                if (this.e != 0) {
                    this.a.onNext(null);
                    return;
                }
                try {
                    this.a.onNext(io.reactivex.internal.functions.a.a(this.f.apply(t), "The mapper function returned a null value."));
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public U poll() throws Exception {
            Object poll = this.c.poll();
            return poll != null ? io.reactivex.internal.functions.a.a(this.f.apply(poll), "The mapper function returned a null value.") : null;
        }
    }

    public ax(p<T> pVar, wm<? super T, ? extends U> wmVar) {
        super(pVar);
        this.b = wmVar;
    }

    public void subscribeActual(r<? super U> rVar) {
        this.a.subscribe(new a(rVar, this.b));
    }
}

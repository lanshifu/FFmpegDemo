package io.reactivex.internal.operators.observable;

import defpackage.wi;
import defpackage.wm;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableDistinctUntilChanged */
public final class x<T, K> extends a<T, T> {
    final wm<? super T, K> b;
    final wi<? super K, ? super K> c;

    /* compiled from: ObservableDistinctUntilChanged */
    static final class a<T, K> extends io.reactivex.internal.observers.a<T, T> {
        final wm<? super T, K> f;
        final wi<? super K, ? super K> g;
        K h;
        boolean i;

        a(r<? super T> rVar, wm<? super T, K> wmVar, wi<? super K, ? super K> wiVar) {
            super(rVar);
            this.f = wmVar;
            this.g = wiVar;
        }

        public void onNext(T t) {
            if (!this.d) {
                if (this.e != 0) {
                    this.a.onNext(t);
                    return;
                }
                try {
                    Object apply = this.f.apply(t);
                    if (this.i) {
                        boolean a = this.g.a(this.h, apply);
                        this.h = apply;
                        if (a) {
                            return;
                        }
                    }
                    this.i = true;
                    this.h = apply;
                    this.a.onNext(t);
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public T poll() throws Exception {
            while (true) {
                Object poll = this.c.poll();
                if (poll == null) {
                    return null;
                }
                Object apply = this.f.apply(poll);
                if (!this.i) {
                    this.i = true;
                    this.h = apply;
                    return poll;
                } else if (this.g.a(this.h, apply)) {
                    this.h = apply;
                } else {
                    this.h = apply;
                    return poll;
                }
            }
        }
    }

    public x(p<T> pVar, wm<? super T, K> wmVar, wi<? super K, ? super K> wiVar) {
        super(pVar);
        this.b = wmVar;
        this.c = wiVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar, this.b, this.c));
    }
}

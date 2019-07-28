package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.p;
import io.reactivex.r;
import java.util.Collection;
import java.util.concurrent.Callable;

/* compiled from: ObservableDistinct */
public final class w<T, K> extends a<T, T> {
    final wm<? super T, K> b;
    final Callable<? extends Collection<? super K>> c;

    /* compiled from: ObservableDistinct */
    static final class a<T, K> extends io.reactivex.internal.observers.a<T, T> {
        final Collection<? super K> f;
        final wm<? super T, K> g;

        a(r<? super T> rVar, wm<? super T, K> wmVar, Collection<? super K> collection) {
            super(rVar);
            this.g = wmVar;
            this.f = collection;
        }

        public void onNext(T t) {
            if (!this.d) {
                if (this.e == 0) {
                    try {
                        if (this.f.add(io.reactivex.internal.functions.a.a(this.g.apply(t), "The keySelector returned a null key"))) {
                            this.a.onNext(t);
                        }
                    } catch (Throwable th) {
                        a(th);
                        return;
                    }
                }
                this.a.onNext(null);
            }
        }

        public void onError(Throwable th) {
            if (this.d) {
                xk.a(th);
                return;
            }
            this.d = true;
            this.f.clear();
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.d) {
                this.d = true;
                this.f.clear();
                this.a.onComplete();
            }
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
            } while (!this.f.add(io.reactivex.internal.functions.a.a(this.g.apply(poll), "The keySelector returned a null key")));
            return poll;
        }

        public void clear() {
            this.f.clear();
            super.clear();
        }
    }

    public w(p<T> pVar, wm<? super T, K> wmVar, Callable<? extends Collection<? super K>> callable) {
        super(pVar);
        this.b = wmVar;
        this.c = callable;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        try {
            this.a.subscribe(new a(rVar, this.b, (Collection) io.reactivex.internal.functions.a.a(this.c.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, (r) rVar);
        }
    }
}

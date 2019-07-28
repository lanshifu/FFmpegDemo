package io.reactivex.internal.operators.observable;

import defpackage.wh;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableScan */
public final class bg<T> extends a<T, T> {
    final wh<T, T, T> b;

    /* compiled from: ObservableScan */
    static final class a<T> implements b, r<T> {
        final r<? super T> a;
        final wh<T, T, T> b;
        b c;
        T d;
        boolean e;

        a(r<? super T> rVar, wh<T, T, T> whVar) {
            this.a = rVar;
            this.b = whVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.c.dispose();
        }

        public boolean isDisposed() {
            return this.c.isDisposed();
        }

        public void onNext(T t) {
            if (!this.e) {
                r rVar = this.a;
                Object obj = this.d;
                if (obj == null) {
                    this.d = t;
                    rVar.onNext(t);
                } else {
                    try {
                        Object a = io.reactivex.internal.functions.a.a(this.b.apply(obj, t), "The value returned by the accumulator is null");
                        this.d = a;
                        rVar.onNext(a);
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.c.dispose();
                        onError(th);
                    }
                }
            }
        }

        public void onError(Throwable th) {
            if (this.e) {
                xk.a(th);
                return;
            }
            this.e = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.e) {
                this.e = true;
                this.a.onComplete();
            }
        }
    }

    public bg(p<T> pVar, wh<T, T, T> whVar) {
        super(pVar);
        this.b = whVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar, this.b));
    }
}

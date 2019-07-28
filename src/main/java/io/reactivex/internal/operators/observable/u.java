package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.j;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableDematerialize */
public final class u<T> extends a<j<T>, T> {

    /* compiled from: ObservableDematerialize */
    static final class a<T> implements b, r<j<T>> {
        final r<? super T> a;
        boolean b;
        b c;

        a(r<? super T> rVar) {
            this.a = rVar;
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

        /* renamed from: a */
        public void onNext(j<T> jVar) {
            if (this.b) {
                if (jVar.b()) {
                    xk.a(jVar.e());
                }
                return;
            }
            if (jVar.b()) {
                this.c.dispose();
                onError(jVar.e());
            } else if (jVar.a()) {
                this.c.dispose();
                onComplete();
            } else {
                this.a.onNext(jVar.d());
            }
        }

        public void onError(Throwable th) {
            if (this.b) {
                xk.a(th);
                return;
            }
            this.b = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.b) {
                this.b = true;
                this.a.onComplete();
            }
        }
    }

    public u(p<j<T>> pVar) {
        super(pVar);
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar));
    }
}

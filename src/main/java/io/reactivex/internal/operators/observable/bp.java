package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableTake */
public final class bp<T> extends a<T, T> {
    final long b;

    /* compiled from: ObservableTake */
    static final class a<T> implements b, r<T> {
        final r<? super T> a;
        boolean b;
        b c;
        long d;

        a(r<? super T> rVar, long j) {
            this.a = rVar;
            this.d = j;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                if (this.d == 0) {
                    this.b = true;
                    bVar.dispose();
                    EmptyDisposable.complete(this.a);
                    return;
                }
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.b) {
                long j = this.d;
                this.d = j - 1;
                if (j > 0) {
                    Object obj = this.d == 0 ? 1 : null;
                    this.a.onNext(t);
                    if (obj != null) {
                        onComplete();
                    }
                }
            }
        }

        public void onError(Throwable th) {
            if (this.b) {
                xk.a(th);
                return;
            }
            this.b = true;
            this.c.dispose();
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.b) {
                this.b = true;
                this.c.dispose();
                this.a.onComplete();
            }
        }

        public void dispose() {
            this.c.dispose();
        }

        public boolean isDisposed() {
            return this.c.isDisposed();
        }
    }

    public bp(p<T> pVar, long j) {
        super(pVar);
        this.b = j;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar, this.b));
    }
}

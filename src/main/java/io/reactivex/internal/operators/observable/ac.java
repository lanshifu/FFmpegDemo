package io.reactivex.internal.operators.observable;

import defpackage.wx;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.g;
import io.reactivex.h;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableElementAtMaybe */
public final class ac<T> extends g<T> implements wx<T> {
    final p<T> a;
    final long b;

    /* compiled from: ObservableElementAtMaybe */
    static final class a<T> implements b, r<T> {
        final h<? super T> a;
        final long b;
        b c;
        long d;
        boolean e;

        a(h<? super T> hVar, long j) {
            this.a = hVar;
            this.b = j;
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
                long j = this.d;
                if (j == this.b) {
                    this.e = true;
                    this.c.dispose();
                    this.a.onSuccess(t);
                    return;
                }
                this.d = j + 1;
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

    public ac(p<T> pVar, long j) {
        this.a = pVar;
        this.b = j;
    }

    public void b(h<? super T> hVar) {
        this.a.subscribe(new a(hVar, this.b));
    }

    public k<T> j_() {
        return xk.a(new ab(this.a, this.b, null, false));
    }
}

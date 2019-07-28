package io.reactivex.internal.operators.observable;

import defpackage.wx;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.t;
import io.reactivex.u;
import java.util.NoSuchElementException;

/* compiled from: ObservableElementAtSingle */
public final class ad<T> extends t<T> implements wx<T> {
    final p<T> a;
    final long b;
    final T c;

    /* compiled from: ObservableElementAtSingle */
    static final class a<T> implements b, r<T> {
        final u<? super T> a;
        final long b;
        final T c;
        b d;
        long e;
        boolean f;

        a(u<? super T> uVar, long j, T t) {
            this.a = uVar;
            this.b = j;
            this.c = t;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.d, bVar)) {
                this.d = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.d.dispose();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        public void onNext(T t) {
            if (!this.f) {
                long j = this.e;
                if (j == this.b) {
                    this.f = true;
                    this.d.dispose();
                    this.a.onSuccess(t);
                    return;
                }
                this.e = j + 1;
            }
        }

        public void onError(Throwable th) {
            if (this.f) {
                xk.a(th);
                return;
            }
            this.f = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.f) {
                this.f = true;
                Object obj = this.c;
                if (obj != null) {
                    this.a.onSuccess(obj);
                } else {
                    this.a.onError(new NoSuchElementException());
                }
            }
        }
    }

    public ad(p<T> pVar, long j, T t) {
        this.a = pVar;
        this.b = j;
        this.c = t;
    }

    public void b(u<? super T> uVar) {
        this.a.subscribe(new a(uVar, this.b, this.c));
    }

    public k<T> j_() {
        return xk.a(new ab(this.a, this.b, this.c, true));
    }
}

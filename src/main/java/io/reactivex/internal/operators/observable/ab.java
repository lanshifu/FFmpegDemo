package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;
import java.util.NoSuchElementException;

/* compiled from: ObservableElementAt */
public final class ab<T> extends a<T, T> {
    final long b;
    final T c;
    final boolean d;

    /* compiled from: ObservableElementAt */
    static final class a<T> implements b, r<T> {
        final r<? super T> a;
        final long b;
        final T c;
        final boolean d;
        b e;
        long f;
        boolean g;

        a(r<? super T> rVar, long j, T t, boolean z) {
            this.a = rVar;
            this.b = j;
            this.c = t;
            this.d = z;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.e, bVar)) {
                this.e = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.e.dispose();
        }

        public boolean isDisposed() {
            return this.e.isDisposed();
        }

        public void onNext(T t) {
            if (!this.g) {
                long j = this.f;
                if (j == this.b) {
                    this.g = true;
                    this.e.dispose();
                    this.a.onNext(t);
                    this.a.onComplete();
                    return;
                }
                this.f = j + 1;
            }
        }

        public void onError(Throwable th) {
            if (this.g) {
                xk.a(th);
                return;
            }
            this.g = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.g) {
                this.g = true;
                Object obj = this.c;
                if (obj == null && this.d) {
                    this.a.onError(new NoSuchElementException());
                    return;
                }
                if (obj != null) {
                    this.a.onNext(obj);
                }
                this.a.onComplete();
            }
        }
    }

    public ab(p<T> pVar, long j, T t, boolean z) {
        super(pVar);
        this.b = j;
        this.c = t;
        this.d = z;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar, this.b, this.c, this.d));
    }
}

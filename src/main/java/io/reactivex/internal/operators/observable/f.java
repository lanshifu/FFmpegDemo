package io.reactivex.internal.operators.observable;

import defpackage.wv;
import defpackage.wx;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.t;
import io.reactivex.u;

/* compiled from: ObservableAllSingle */
public final class f<T> extends t<Boolean> implements wx<Boolean> {
    final p<T> a;
    final wv<? super T> b;

    /* compiled from: ObservableAllSingle */
    static final class a<T> implements b, r<T> {
        final u<? super Boolean> a;
        final wv<? super T> b;
        b c;
        boolean d;

        a(u<? super Boolean> uVar, wv<? super T> wvVar) {
            this.a = uVar;
            this.b = wvVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.d) {
                try {
                    if (!this.b.test(t)) {
                        this.d = true;
                        this.c.dispose();
                        this.a.onSuccess(Boolean.valueOf(false));
                    }
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.c.dispose();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.d) {
                xk.a(th);
                return;
            }
            this.d = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.d) {
                this.d = true;
                this.a.onSuccess(Boolean.valueOf(true));
            }
        }

        public void dispose() {
            this.c.dispose();
        }

        public boolean isDisposed() {
            return this.c.isDisposed();
        }
    }

    public f(p<T> pVar, wv<? super T> wvVar) {
        this.a = pVar;
        this.b = wvVar;
    }

    /* Access modifiers changed, original: protected */
    public void b(u<? super Boolean> uVar) {
        this.a.subscribe(new a(uVar, this.b));
    }

    public k<Boolean> j_() {
        return xk.a(new e(this.a, this.b));
    }
}

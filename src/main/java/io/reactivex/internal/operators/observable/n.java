package io.reactivex.internal.operators.observable;

import defpackage.wg;
import defpackage.wx;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.t;
import io.reactivex.u;
import java.util.concurrent.Callable;

/* compiled from: ObservableCollectSingle */
public final class n<T, U> extends t<U> implements wx<U> {
    final p<T> a;
    final Callable<? extends U> b;
    final wg<? super U, ? super T> c;

    /* compiled from: ObservableCollectSingle */
    static final class a<T, U> implements b, r<T> {
        final u<? super U> a;
        final wg<? super U, ? super T> b;
        final U c;
        b d;
        boolean e;

        a(u<? super U> uVar, U u, wg<? super U, ? super T> wgVar) {
            this.a = uVar;
            this.b = wgVar;
            this.c = u;
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
            if (!this.e) {
                try {
                    this.b.accept(this.c, t);
                } catch (Throwable th) {
                    this.d.dispose();
                    onError(th);
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
                this.a.onSuccess(this.c);
            }
        }
    }

    public n(p<T> pVar, Callable<? extends U> callable, wg<? super U, ? super T> wgVar) {
        this.a = pVar;
        this.b = callable;
        this.c = wgVar;
    }

    /* Access modifiers changed, original: protected */
    public void b(u<? super U> uVar) {
        try {
            this.a.subscribe(new a(uVar, io.reactivex.internal.functions.a.a(this.b.call(), "The initialSupplier returned a null value"), this.c));
        } catch (Throwable th) {
            EmptyDisposable.error(th, (u) uVar);
        }
    }

    public k<U> j_() {
        return xk.a(new m(this.a, this.b, this.c));
    }
}

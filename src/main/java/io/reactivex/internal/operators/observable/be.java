package io.reactivex.internal.operators.observable;

import defpackage.wh;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.t;
import io.reactivex.u;

/* compiled from: ObservableReduceSeedSingle */
public final class be<T, R> extends t<R> {
    final p<T> a;
    final R b;
    final wh<R, ? super T, R> c;

    /* compiled from: ObservableReduceSeedSingle */
    static final class a<T, R> implements b, r<T> {
        final u<? super R> a;
        final wh<R, ? super T, R> b;
        R c;
        b d;

        a(u<? super R> uVar, wh<R, ? super T, R> whVar, R r) {
            this.a = uVar;
            this.c = r;
            this.b = whVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.d, bVar)) {
                this.d = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            Object obj = this.c;
            if (obj != null) {
                try {
                    this.c = io.reactivex.internal.functions.a.a(this.b.apply(obj, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.d.dispose();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.c != null) {
                this.c = null;
                this.a.onError(th);
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            Object obj = this.c;
            if (obj != null) {
                this.c = null;
                this.a.onSuccess(obj);
            }
        }

        public void dispose() {
            this.d.dispose();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }
    }

    public be(p<T> pVar, R r, wh<R, ? super T, R> whVar) {
        this.a = pVar;
        this.b = r;
        this.c = whVar;
    }

    /* Access modifiers changed, original: protected */
    public void b(u<? super R> uVar) {
        this.a.subscribe(new a(uVar, this.c, this.b));
    }
}

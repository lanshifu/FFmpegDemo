package io.reactivex.internal.operators.single;

import defpackage.wm;
import io.reactivex.disposables.b;
import io.reactivex.t;
import io.reactivex.u;
import io.reactivex.v;

/* compiled from: SingleMap */
public final class a<T, R> extends t<R> {
    final v<? extends T> a;
    final wm<? super T, ? extends R> b;

    /* compiled from: SingleMap */
    static final class a<T, R> implements u<T> {
        final u<? super R> a;
        final wm<? super T, ? extends R> b;

        a(u<? super R> uVar, wm<? super T, ? extends R> wmVar) {
            this.a = uVar;
            this.b = wmVar;
        }

        public void onSubscribe(b bVar) {
            this.a.onSubscribe(bVar);
        }

        public void onSuccess(T t) {
            try {
                this.a.onSuccess(io.reactivex.internal.functions.a.a(this.b.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                onError(th);
            }
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }
    }

    public a(v<? extends T> vVar, wm<? super T, ? extends R> wmVar) {
        this.a = vVar;
        this.b = wmVar;
    }

    /* Access modifiers changed, original: protected */
    public void b(u<? super R> uVar) {
        this.a.a(new a(uVar, this.b));
    }
}

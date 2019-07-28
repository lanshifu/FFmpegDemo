package io.reactivex.internal.operators.observable;

import defpackage.wh;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.g;
import io.reactivex.h;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableReduceMaybe */
public final class bd<T> extends g<T> {
    final p<T> a;
    final wh<T, T, T> b;

    /* compiled from: ObservableReduceMaybe */
    static final class a<T> implements b, r<T> {
        final h<? super T> a;
        final wh<T, T, T> b;
        boolean c;
        T d;
        b e;

        a(h<? super T> hVar, wh<T, T, T> whVar) {
            this.a = hVar;
            this.b = whVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.e, bVar)) {
                this.e = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.c) {
                Object obj = this.d;
                if (obj == null) {
                    this.d = t;
                    return;
                }
                try {
                    this.d = io.reactivex.internal.functions.a.a(this.b.apply(obj, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.e.dispose();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.c) {
                xk.a(th);
                return;
            }
            this.c = true;
            this.d = null;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.c) {
                this.c = true;
                Object obj = this.d;
                this.d = null;
                if (obj != null) {
                    this.a.onSuccess(obj);
                } else {
                    this.a.onComplete();
                }
            }
        }

        public void dispose() {
            this.e.dispose();
        }

        public boolean isDisposed() {
            return this.e.isDisposed();
        }
    }

    public bd(p<T> pVar, wh<T, T, T> whVar) {
        this.a = pVar;
        this.b = whVar;
    }

    /* Access modifiers changed, original: protected */
    public void b(h<? super T> hVar) {
        this.a.subscribe(new a(hVar, this.b));
    }
}

package io.reactivex.internal.operators.observable;

import defpackage.wm;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableOnErrorReturn */
public final class bc<T> extends a<T, T> {
    final wm<? super Throwable, ? extends T> b;

    /* compiled from: ObservableOnErrorReturn */
    static final class a<T> implements b, r<T> {
        final r<? super T> a;
        final wm<? super Throwable, ? extends T> b;
        b c;

        a(r<? super T> rVar, wm<? super Throwable, ? extends T> wmVar) {
            this.a = rVar;
            this.b = wmVar;
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
            this.a.onNext(t);
        }

        public void onError(Throwable th) {
            try {
                Object apply = this.b.apply(th);
                if (apply == null) {
                    NullPointerException nullPointerException = new NullPointerException("The supplied value is null");
                    nullPointerException.initCause(th);
                    this.a.onError(nullPointerException);
                    return;
                }
                this.a.onNext(apply);
                this.a.onComplete();
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.a.onError(new CompositeException(th, th2));
            }
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    public bc(p<T> pVar, wm<? super Throwable, ? extends T> wmVar) {
        super(pVar);
        this.b = wmVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar, this.b));
    }
}

package io.reactivex.internal.operators.observable;

import defpackage.wh;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.Callable;

/* compiled from: ObservableScanSeed */
public final class bh<T, R> extends a<T, R> {
    final wh<R, ? super T, R> b;
    final Callable<R> c;

    /* compiled from: ObservableScanSeed */
    static final class a<T, R> implements b, r<T> {
        final r<? super R> a;
        final wh<R, ? super T, R> b;
        R c;
        b d;
        boolean e;

        a(r<? super R> rVar, wh<R, ? super T, R> whVar, R r) {
            this.a = rVar;
            this.b = whVar;
            this.c = r;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.d, bVar)) {
                this.d = bVar;
                this.a.onSubscribe(this);
                this.a.onNext(this.c);
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
                    Object a = io.reactivex.internal.functions.a.a(this.b.apply(this.c, t), "The accumulator returned a null value");
                    this.c = a;
                    this.a.onNext(a);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
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
                this.a.onComplete();
            }
        }
    }

    public bh(p<T> pVar, Callable<R> callable, wh<R, ? super T, R> whVar) {
        super(pVar);
        this.b = whVar;
        this.c = callable;
    }

    public void subscribeActual(r<? super R> rVar) {
        try {
            this.a.subscribe(new a(rVar, this.b, io.reactivex.internal.functions.a.a(this.c.call(), "The seed supplied is null")));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, (r) rVar);
        }
    }
}

package io.reactivex.internal.operators.observable;

import defpackage.wm;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.Callable;

/* compiled from: ObservableMapNotification */
public final class ay<T, R> extends a<T, p<? extends R>> {
    final wm<? super T, ? extends p<? extends R>> b;
    final wm<? super Throwable, ? extends p<? extends R>> c;
    final Callable<? extends p<? extends R>> d;

    /* compiled from: ObservableMapNotification */
    static final class a<T, R> implements b, r<T> {
        final r<? super p<? extends R>> a;
        final wm<? super T, ? extends p<? extends R>> b;
        final wm<? super Throwable, ? extends p<? extends R>> c;
        final Callable<? extends p<? extends R>> d;
        b e;

        a(r<? super p<? extends R>> rVar, wm<? super T, ? extends p<? extends R>> wmVar, wm<? super Throwable, ? extends p<? extends R>> wmVar2, Callable<? extends p<? extends R>> callable) {
            this.a = rVar;
            this.b = wmVar;
            this.c = wmVar2;
            this.d = callable;
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
            try {
                this.a.onNext((p) io.reactivex.internal.functions.a.a(this.b.apply(t), "The onNext ObservableSource returned is null"));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.a.onError(th);
            }
        }

        public void onError(Throwable th) {
            try {
                this.a.onNext((p) io.reactivex.internal.functions.a.a(this.c.apply(th), "The onError ObservableSource returned is null"));
                this.a.onComplete();
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.a.onError(new CompositeException(th, th2));
            }
        }

        public void onComplete() {
            try {
                this.a.onNext((p) io.reactivex.internal.functions.a.a(this.d.call(), "The onComplete ObservableSource returned is null"));
                this.a.onComplete();
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.a.onError(th);
            }
        }
    }

    public ay(p<T> pVar, wm<? super T, ? extends p<? extends R>> wmVar, wm<? super Throwable, ? extends p<? extends R>> wmVar2, Callable<? extends p<? extends R>> callable) {
        super(pVar);
        this.b = wmVar;
        this.c = wmVar2;
        this.d = callable;
    }

    public void subscribeActual(r<? super p<? extends R>> rVar) {
        this.a.subscribe(new a(rVar, this.b, this.c, this.d));
    }
}

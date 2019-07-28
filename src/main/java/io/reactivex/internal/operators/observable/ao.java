package io.reactivex.internal.operators.observable;

import defpackage.wh;
import defpackage.wl;
import defpackage.xk;
import io.reactivex.d;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.Callable;

/* compiled from: ObservableGenerate */
public final class ao<T, S> extends k<T> {
    final Callable<S> a;
    final wh<S, d<T>, S> b;
    final wl<? super S> c;

    /* compiled from: ObservableGenerate */
    static final class a<T, S> implements d<T>, b {
        final r<? super T> a;
        final wh<S, ? super d<T>, S> b;
        final wl<? super S> c;
        S d;
        volatile boolean e;
        boolean f;
        boolean g;

        a(r<? super T> rVar, wh<S, ? super d<T>, S> whVar, wl<? super S> wlVar, S s) {
            this.a = rVar;
            this.b = whVar;
            this.c = wlVar;
            this.d = s;
        }

        public void a() {
            Object obj = this.d;
            if (this.e) {
                this.d = null;
                a(obj);
                return;
            }
            wh whVar = this.b;
            while (!this.e) {
                this.g = false;
                try {
                    Object apply = whVar.apply(obj, this);
                    if (this.f) {
                        this.e = true;
                        this.d = null;
                        a(apply);
                        return;
                    }
                    obj = apply;
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.d = null;
                    this.e = true;
                    onError(th);
                    a(obj);
                    return;
                }
            }
            this.d = null;
            a(obj);
        }

        private void a(S s) {
            try {
                this.c.accept(s);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                xk.a(th);
            }
        }

        public void dispose() {
            this.e = true;
        }

        public boolean isDisposed() {
            return this.e;
        }

        public void onNext(T t) {
            if (!this.f) {
                if (this.g) {
                    onError(new IllegalStateException("onNext already called in this generate turn"));
                } else if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                } else {
                    this.g = true;
                    this.a.onNext(t);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.f) {
                xk.a(th);
                return;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.f = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.f) {
                this.f = true;
                this.a.onComplete();
            }
        }
    }

    public ao(Callable<S> callable, wh<S, d<T>, S> whVar, wl<? super S> wlVar) {
        this.a = callable;
        this.b = whVar;
        this.c = wlVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        try {
            a aVar = new a(rVar, this.b, this.c, this.a.call());
            rVar.onSubscribe(aVar);
            aVar.a();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, (r) rVar);
        }
    }
}

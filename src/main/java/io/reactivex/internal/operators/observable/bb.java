package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableOnErrorNext */
public final class bb<T> extends a<T, T> {
    final wm<? super Throwable, ? extends p<? extends T>> b;
    final boolean c;

    /* compiled from: ObservableOnErrorNext */
    static final class a<T> implements r<T> {
        final r<? super T> a;
        final wm<? super Throwable, ? extends p<? extends T>> b;
        final boolean c;
        final SequentialDisposable d = new SequentialDisposable();
        boolean e;
        boolean f;

        a(r<? super T> rVar, wm<? super Throwable, ? extends p<? extends T>> wmVar, boolean z) {
            this.a = rVar;
            this.b = wmVar;
            this.c = z;
        }

        public void onSubscribe(b bVar) {
            this.d.replace(bVar);
        }

        public void onNext(T t) {
            if (!this.f) {
                this.a.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (!this.e) {
                this.e = true;
                if (!this.c || (th instanceof Exception)) {
                    try {
                        p pVar = (p) this.b.apply(th);
                        if (pVar == null) {
                            NullPointerException nullPointerException = new NullPointerException("Observable is null");
                            nullPointerException.initCause(th);
                            this.a.onError(nullPointerException);
                            return;
                        }
                        pVar.subscribe(this);
                        return;
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
                        this.a.onError(new CompositeException(th, th2));
                        return;
                    }
                }
                this.a.onError(th);
            } else if (this.f) {
                xk.a(th);
            } else {
                this.a.onError(th);
            }
        }

        public void onComplete() {
            if (!this.f) {
                this.f = true;
                this.e = true;
                this.a.onComplete();
            }
        }
    }

    public bb(p<T> pVar, wm<? super Throwable, ? extends p<? extends T>> wmVar, boolean z) {
        super(pVar);
        this.b = wmVar;
        this.c = z;
    }

    public void subscribeActual(r<? super T> rVar) {
        a aVar = new a(rVar, this.b, this.c);
        rVar.onSubscribe(aVar.d);
        this.a.subscribe(aVar);
    }
}

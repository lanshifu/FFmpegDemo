package io.reactivex.internal.operators.observable;

import defpackage.wf;
import defpackage.wl;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableDoOnEach */
public final class z<T> extends a<T, T> {
    final wl<? super T> b;
    final wl<? super Throwable> c;
    final wf d;
    final wf e;

    /* compiled from: ObservableDoOnEach */
    static final class a<T> implements b, r<T> {
        final r<? super T> a;
        final wl<? super T> b;
        final wl<? super Throwable> c;
        final wf d;
        final wf e;
        b f;
        boolean g;

        a(r<? super T> rVar, wl<? super T> wlVar, wl<? super Throwable> wlVar2, wf wfVar, wf wfVar2) {
            this.a = rVar;
            this.b = wlVar;
            this.c = wlVar2;
            this.d = wfVar;
            this.e = wfVar2;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.f, bVar)) {
                this.f = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f.dispose();
        }

        public boolean isDisposed() {
            return this.f.isDisposed();
        }

        public void onNext(T t) {
            if (!this.g) {
                try {
                    this.b.accept(t);
                    this.a.onNext(t);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.f.dispose();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.g) {
                xk.a(th);
                return;
            }
            this.g = true;
            try {
                this.c.accept(th);
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                th = new CompositeException(th, th2);
            }
            this.a.onError(th);
            try {
                this.e.a();
            } catch (Throwable th3) {
                io.reactivex.exceptions.a.b(th3);
                xk.a(th3);
            }
        }

        public void onComplete() {
            if (!this.g) {
                try {
                    this.d.a();
                    this.g = true;
                    this.a.onComplete();
                    try {
                        this.e.a();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        xk.a(th);
                    }
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    onError(th2);
                }
            }
        }
    }

    public z(p<T> pVar, wl<? super T> wlVar, wl<? super Throwable> wlVar2, wf wfVar, wf wfVar2) {
        super(pVar);
        this.b = wlVar;
        this.c = wlVar2;
        this.d = wfVar;
        this.e = wfVar2;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(rVar, this.b, this.c, this.d, this.e));
    }
}

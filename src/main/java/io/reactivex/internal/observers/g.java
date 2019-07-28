package io.reactivex.internal.observers;

import defpackage.wf;
import defpackage.wl;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.r;

/* compiled from: DisposableLambdaObserver */
public final class g<T> implements b, r<T> {
    final r<? super T> a;
    final wl<? super b> b;
    final wf c;
    b d;

    public g(r<? super T> rVar, wl<? super b> wlVar, wf wfVar) {
        this.a = rVar;
        this.b = wlVar;
        this.c = wfVar;
    }

    public void onSubscribe(b bVar) {
        try {
            this.b.accept(bVar);
            if (DisposableHelper.validate(this.d, bVar)) {
                this.d = bVar;
                this.a.onSubscribe(this);
            }
        } catch (Throwable th) {
            a.b(th);
            bVar.dispose();
            this.d = DisposableHelper.DISPOSED;
            EmptyDisposable.error(th, this.a);
        }
    }

    public void onNext(T t) {
        this.a.onNext(t);
    }

    public void onError(Throwable th) {
        if (this.d != DisposableHelper.DISPOSED) {
            this.a.onError(th);
        } else {
            xk.a(th);
        }
    }

    public void onComplete() {
        if (this.d != DisposableHelper.DISPOSED) {
            this.a.onComplete();
        }
    }

    public void dispose() {
        try {
            this.c.a();
        } catch (Throwable th) {
            a.b(th);
            xk.a(th);
        }
        this.d.dispose();
    }

    public boolean isDisposed() {
        return this.d.isDisposed();
    }
}

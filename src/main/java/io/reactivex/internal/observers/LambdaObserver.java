package io.reactivex.internal.observers;

import defpackage.wf;
import defpackage.wl;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.Functions;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicReference;

public final class LambdaObserver<T> extends AtomicReference<b> implements b, r<T> {
    private static final long serialVersionUID = -7251123623727029452L;
    final wf onComplete;
    final wl<? super Throwable> onError;
    final wl<? super T> onNext;
    final wl<? super b> onSubscribe;

    public LambdaObserver(wl<? super T> wlVar, wl<? super Throwable> wlVar2, wf wfVar, wl<? super b> wlVar3) {
        this.onNext = wlVar;
        this.onError = wlVar2;
        this.onComplete = wfVar;
        this.onSubscribe = wlVar3;
    }

    public void onSubscribe(b bVar) {
        if (DisposableHelper.setOnce(this, bVar)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable th) {
                a.b(th);
                bVar.dispose();
                onError(th);
            }
        }
    }

    public void onNext(T t) {
        if (!isDisposed()) {
            try {
                this.onNext.accept(t);
            } catch (Throwable th) {
                a.b(th);
                ((b) get()).dispose();
                onError(th);
            }
        }
    }

    public void onError(Throwable th) {
        if (isDisposed()) {
            xk.a(th);
            return;
        }
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            a.b(th2);
            xk.a(new CompositeException(th, th2));
        }
    }

    public void onComplete() {
        if (!isDisposed()) {
            lazySet(DisposableHelper.DISPOSED);
            try {
                this.onComplete.a();
            } catch (Throwable th) {
                a.b(th);
                xk.a(th);
            }
        }
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    public boolean hasCustomOnError() {
        return this.onError != Functions.f;
    }
}

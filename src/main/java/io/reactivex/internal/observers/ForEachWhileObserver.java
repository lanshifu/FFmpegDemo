package io.reactivex.internal.observers;

import defpackage.wf;
import defpackage.wl;
import defpackage.wv;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicReference;

public final class ForEachWhileObserver<T> extends AtomicReference<b> implements b, r<T> {
    private static final long serialVersionUID = -4403180040475402120L;
    boolean done;
    final wf onComplete;
    final wl<? super Throwable> onError;
    final wv<? super T> onNext;

    public ForEachWhileObserver(wv<? super T> wvVar, wl<? super Throwable> wlVar, wf wfVar) {
        this.onNext = wvVar;
        this.onError = wlVar;
        this.onComplete = wfVar;
    }

    public void onSubscribe(b bVar) {
        DisposableHelper.setOnce(this, bVar);
    }

    public void onNext(T t) {
        if (!this.done) {
            try {
                if (!this.onNext.test(t)) {
                    dispose();
                    onComplete();
                }
            } catch (Throwable th) {
                a.b(th);
                dispose();
                onError(th);
            }
        }
    }

    public void onError(Throwable th) {
        if (this.done) {
            xk.a(th);
            return;
        }
        this.done = true;
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            a.b(th2);
            xk.a(new CompositeException(th, th2));
        }
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
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
        return DisposableHelper.isDisposed((b) get());
    }
}

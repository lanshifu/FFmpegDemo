package io.reactivex.internal.subscribers;

import defpackage.wf;
import defpackage.wl;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.f;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class LambdaSubscriber<T> extends AtomicReference<aas> implements aas, b, f<T> {
    private static final long serialVersionUID = -7251123623727029452L;
    final wf onComplete;
    final wl<? super Throwable> onError;
    final wl<? super T> onNext;
    final wl<? super aas> onSubscribe;

    public LambdaSubscriber(wl<? super T> wlVar, wl<? super Throwable> wlVar2, wf wfVar, wl<? super aas> wlVar3) {
        this.onNext = wlVar;
        this.onError = wlVar2;
        this.onComplete = wfVar;
        this.onSubscribe = wlVar3;
    }

    public void onSubscribe(aas aas) {
        if (SubscriptionHelper.setOnce(this, aas)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable th) {
                a.b(th);
                aas.cancel();
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
                ((aas) get()).cancel();
                onError(th);
            }
        }
    }

    public void onError(Throwable th) {
        if (get() != SubscriptionHelper.CANCELLED) {
            lazySet(SubscriptionHelper.CANCELLED);
            try {
                this.onError.accept(th);
                return;
            } catch (Throwable th2) {
                a.b(th2);
                xk.a(new CompositeException(th, th2));
                return;
            }
        }
        xk.a(th);
    }

    public void onComplete() {
        if (get() != SubscriptionHelper.CANCELLED) {
            lazySet(SubscriptionHelper.CANCELLED);
            try {
                this.onComplete.a();
            } catch (Throwable th) {
                a.b(th);
                xk.a(th);
            }
        }
    }

    public void dispose() {
        cancel();
    }

    public boolean isDisposed() {
        return get() == SubscriptionHelper.CANCELLED;
    }

    public void request(long j) {
        ((aas) get()).request(j);
    }

    public void cancel() {
        SubscriptionHelper.cancel(this);
    }

    public boolean hasCustomOnError() {
        return this.onError != Functions.f;
    }
}

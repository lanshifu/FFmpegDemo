package io.reactivex.internal.util;

import defpackage.xk;
import io.reactivex.b;
import io.reactivex.f;
import io.reactivex.h;
import io.reactivex.r;
import io.reactivex.u;

public enum EmptyComponent implements aas, b, io.reactivex.disposables.b, f<Object>, h<Object>, r<Object>, u<Object> {
    INSTANCE;

    public void cancel() {
    }

    public void dispose() {
    }

    public boolean isDisposed() {
        return true;
    }

    public void onComplete() {
    }

    public void onNext(Object obj) {
    }

    public void onSuccess(Object obj) {
    }

    public void request(long j) {
    }

    public static <T> aar<T> asSubscriber() {
        return INSTANCE;
    }

    public static <T> r<T> asObserver() {
        return INSTANCE;
    }

    public void onSubscribe(io.reactivex.disposables.b bVar) {
        bVar.dispose();
    }

    public void onSubscribe(aas aas) {
        aas.cancel();
    }

    public void onError(Throwable th) {
        xk.a(th);
    }
}

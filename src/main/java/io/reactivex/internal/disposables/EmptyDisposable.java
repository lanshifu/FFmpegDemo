package io.reactivex.internal.disposables;

import defpackage.wy;
import io.reactivex.b;
import io.reactivex.h;
import io.reactivex.r;
import io.reactivex.u;

public enum EmptyDisposable implements wy<Object> {
    INSTANCE,
    NEVER;

    public void clear() {
    }

    public void dispose() {
    }

    public boolean isEmpty() {
        return true;
    }

    public Object poll() throws Exception {
        return null;
    }

    public int requestFusion(int i) {
        return i & 2;
    }

    public boolean isDisposed() {
        return this == INSTANCE;
    }

    public static void complete(r<?> rVar) {
        rVar.onSubscribe(INSTANCE);
        rVar.onComplete();
    }

    public static void complete(h<?> hVar) {
        hVar.onSubscribe(INSTANCE);
        hVar.onComplete();
    }

    public static void error(Throwable th, r<?> rVar) {
        rVar.onSubscribe(INSTANCE);
        rVar.onError(th);
    }

    public static void complete(b bVar) {
        bVar.onSubscribe(INSTANCE);
        bVar.onComplete();
    }

    public static void error(Throwable th, b bVar) {
        bVar.onSubscribe(INSTANCE);
        bVar.onError(th);
    }

    public static void error(Throwable th, u<?> uVar) {
        uVar.onSubscribe(INSTANCE);
        uVar.onError(th);
    }

    public static void error(Throwable th, h<?> hVar) {
        hVar.onSubscribe(INSTANCE);
        hVar.onError(th);
    }

    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}

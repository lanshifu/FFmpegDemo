package io.reactivex;

import io.reactivex.disposables.b;

/* compiled from: MaybeObserver */
public interface h<T> {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(b bVar);

    void onSuccess(T t);
}

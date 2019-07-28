package io.reactivex;

import io.reactivex.disposables.b;

/* compiled from: SingleObserver */
public interface u<T> {
    void onError(Throwable th);

    void onSubscribe(b bVar);

    void onSuccess(T t);
}

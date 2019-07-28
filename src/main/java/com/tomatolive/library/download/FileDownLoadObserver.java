package com.tomatolive.library.download;

import io.reactivex.observers.b;

public abstract class FileDownLoadObserver<T> extends b<T> {
    public void onComplete() {
    }

    public abstract void onDownLoadFail(Throwable th);

    public abstract void onDownLoadSuccess(T t);

    public abstract void onProgress(int i, long j);

    public void onNext(T t) {
        onDownLoadSuccess(t);
    }

    public void onError(Throwable th) {
        onDownLoadFail(th);
    }
}

package com.tomatolive.library.http;

import android.content.Context;
import com.tomatolive.library.http.exception.ApiException;
import io.reactivex.disposables.b;
import io.reactivex.r;

public class StatisticsHttpRxObserver<T> implements r<T> {
    private ResultCallBack callBack;

    public void onComplete() {
    }

    public void onSubscribe(b bVar) {
    }

    public StatisticsHttpRxObserver(Context context, ResultCallBack<T> resultCallBack) {
        this.callBack = resultCallBack;
    }

    public void onError(Throwable th) {
        th.printStackTrace();
        try {
            ApiException apiException = (ApiException) th;
            if (this.callBack != null) {
                this.callBack.onError(apiException.getCode(), apiException.getMsg());
            }
        } catch (Exception unused) {
        }
    }

    public void onNext(T t) {
        if (this.callBack != null) {
            this.callBack.onSuccess(t);
        }
    }
}

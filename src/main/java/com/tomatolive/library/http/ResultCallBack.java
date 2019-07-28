package com.tomatolive.library.http;

public interface ResultCallBack<T> {
    void onError(int i, String str);

    void onSuccess(T t);
}

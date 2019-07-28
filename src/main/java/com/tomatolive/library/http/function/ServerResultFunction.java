package com.tomatolive.library.http.function;

import com.tomatolive.library.http.HttpResultModel;
import com.tomatolive.library.http.exception.ServerException;
import defpackage.wm;

public class ServerResultFunction<T> implements wm<HttpResultModel<T>, T> {
    public T apply(HttpResultModel<T> httpResultModel) {
        if (httpResultModel.isSuccess()) {
            return httpResultModel.getData();
        }
        throw new ServerException(httpResultModel.getCode(), httpResultModel.getMessage());
    }
}

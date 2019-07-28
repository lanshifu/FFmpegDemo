package com.tomatolive.library.http.function;

import com.tomatolive.library.http.exception.ExceptionEngine;
import defpackage.wm;
import io.reactivex.k;

public class HttpResultFunction<T> implements wm<Throwable, k<T>> {
    public k<T> apply(Throwable th) {
        return k.error(ExceptionEngine.handleException(th));
    }
}

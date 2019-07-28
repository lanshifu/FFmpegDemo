package io.reactivex.internal.util;

import defpackage.wm;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public enum ArrayListSupplier implements Callable<List<Object>>, wm<Object, List<Object>> {
    INSTANCE;

    public static <T> Callable<List<T>> asCallable() {
        return INSTANCE;
    }

    public static <T, O> wm<O, List<T>> asFunction() {
        return INSTANCE;
    }

    public List<Object> call() throws Exception {
        return new ArrayList();
    }

    public List<Object> apply(Object obj) throws Exception {
        return new ArrayList();
    }
}

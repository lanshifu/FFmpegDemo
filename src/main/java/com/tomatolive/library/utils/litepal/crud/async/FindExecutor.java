package com.tomatolive.library.utils.litepal.crud.async;

import com.tomatolive.library.utils.litepal.crud.callback.FindCallback;

public class FindExecutor<T> extends AsyncExecutor {
    private FindCallback<T> cb;

    public void listen(FindCallback<T> findCallback) {
        this.cb = findCallback;
        execute();
    }

    public FindCallback<T> getListener() {
        return this.cb;
    }
}

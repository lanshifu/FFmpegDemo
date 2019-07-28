package com.tomatolive.library.utils.litepal.crud.async;

import com.tomatolive.library.utils.litepal.crud.callback.FindMultiCallback;

public class FindMultiExecutor<T> extends AsyncExecutor {
    private FindMultiCallback<T> cb;

    public void listen(FindMultiCallback<T> findMultiCallback) {
        this.cb = findMultiCallback;
        execute();
    }

    public FindMultiCallback<T> getListener() {
        return this.cb;
    }
}

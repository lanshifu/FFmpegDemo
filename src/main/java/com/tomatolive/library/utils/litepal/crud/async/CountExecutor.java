package com.tomatolive.library.utils.litepal.crud.async;

import com.tomatolive.library.utils.litepal.crud.callback.CountCallback;

public class CountExecutor extends AsyncExecutor {
    private CountCallback cb;

    public void listen(CountCallback countCallback) {
        this.cb = countCallback;
        execute();
    }

    public CountCallback getListener() {
        return this.cb;
    }
}

package com.tomatolive.library.utils.litepal.crud.async;

import com.tomatolive.library.utils.litepal.crud.callback.UpdateOrDeleteCallback;

public class UpdateOrDeleteExecutor extends AsyncExecutor {
    private UpdateOrDeleteCallback cb;

    public void listen(UpdateOrDeleteCallback updateOrDeleteCallback) {
        this.cb = updateOrDeleteCallback;
        execute();
    }

    public UpdateOrDeleteCallback getListener() {
        return this.cb;
    }
}

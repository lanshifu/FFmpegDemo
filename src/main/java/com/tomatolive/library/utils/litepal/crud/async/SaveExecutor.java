package com.tomatolive.library.utils.litepal.crud.async;

import com.tomatolive.library.utils.litepal.crud.callback.SaveCallback;

public class SaveExecutor extends AsyncExecutor {
    private SaveCallback cb;

    public void listen(SaveCallback saveCallback) {
        this.cb = saveCallback;
        execute();
    }

    public SaveCallback getListener() {
        return this.cb;
    }
}

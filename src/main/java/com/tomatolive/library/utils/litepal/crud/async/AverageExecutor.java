package com.tomatolive.library.utils.litepal.crud.async;

import com.tomatolive.library.utils.litepal.crud.callback.AverageCallback;

public class AverageExecutor extends AsyncExecutor {
    private AverageCallback cb;

    public void listen(AverageCallback averageCallback) {
        this.cb = averageCallback;
        execute();
    }

    public AverageCallback getListener() {
        return this.cb;
    }
}

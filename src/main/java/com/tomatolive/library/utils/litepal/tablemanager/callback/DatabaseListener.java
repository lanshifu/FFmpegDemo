package com.tomatolive.library.utils.litepal.tablemanager.callback;

public interface DatabaseListener {
    void onCreate();

    void onUpgrade(int i, int i2);
}

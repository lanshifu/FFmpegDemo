package com.tomatolive.library.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.tomatolive.library.utils.o;

public class NetworkChangeReceiver extends BroadcastReceiver {
    private a a = null;
    private int b = 100;

    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE")) {
            int c = o.c();
            if (this.b != c) {
                this.b = c;
                if (this.a != null) {
                    this.a.onNetChangeListener(c);
                }
            }
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.a = aVar;
        }
    }
}

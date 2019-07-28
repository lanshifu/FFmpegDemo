package com.tomatolive.library.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

class BaseActivity$b extends BroadcastReceiver {
    final /* synthetic */ BaseActivity a;

    private BaseActivity$b(BaseActivity baseActivity) {
        this.a = baseActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && TextUtils.equals(intent.getAction(), "LIVE_TOKEN_INVALID_ACTION")) {
            BaseActivity.access$100(this.a);
        }
    }
}

package com.tomatolive.library.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

class BaseActivity$a extends BroadcastReceiver {
    final /* synthetic */ BaseActivity a;

    private BaseActivity$a(BaseActivity baseActivity) {
        this.a = baseActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && TextUtils.equals(intent.getAction(), "LIVE_KICK_OUT_ACTION")) {
            BaseActivity.access$000(this.a);
        }
    }
}

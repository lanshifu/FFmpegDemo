package com.tomatolive.library.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

public class TokenDialogService extends IntentService {
    public TokenDialogService() {
        super("TokenDialogService");
    }

    /* Access modifiers changed, original: protected */
    public void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(500);
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("LIVE_TOKEN_INVALID_ACTION"));
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}

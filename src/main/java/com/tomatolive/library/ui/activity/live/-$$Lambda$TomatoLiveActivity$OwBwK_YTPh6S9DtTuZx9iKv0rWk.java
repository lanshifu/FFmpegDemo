package com.tomatolive.library.ui.activity.live;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$OwBwK_YTPh6S9DtTuZx9iKv0rWk implements OnDismissListener {
    private final /* synthetic */ TomatoLiveActivity f$0;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$OwBwK_YTPh6S9DtTuZx9iKv0rWk(TomatoLiveActivity tomatoLiveActivity) {
        this.f$0 = tomatoLiveActivity;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.f$0.inputChangeStatus(0, 0);
    }
}

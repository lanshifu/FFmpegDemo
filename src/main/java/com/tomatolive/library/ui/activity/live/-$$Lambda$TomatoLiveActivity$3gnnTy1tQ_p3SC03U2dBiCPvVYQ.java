package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.ui.view.dialog.LiveActionBottomDialog.OnLiveActionListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$3gnnTy1tQ_p3SC03U2dBiCPvVYQ implements OnLiveActionListener {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ String f$1;
    private final /* synthetic */ String f$2;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$3gnnTy1tQ_p3SC03U2dBiCPvVYQ(TomatoLiveActivity tomatoLiveActivity, String str, String str2) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = str;
        this.f$2 = str2;
    }

    public final void onLiveAction(int i, boolean z) {
        TomatoLiveActivity.lambda$showControlPermissionDialog$39(this.f$0, this.f$1, this.f$2, i, z);
    }
}

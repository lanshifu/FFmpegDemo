package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.ui.view.dialog.LiveActionBottomDialog.OnLiveActionListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$jSfoaK8olAXqL6Ict-vSMRZa-iw implements OnLiveActionListener {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ String f$1;
    private final /* synthetic */ String f$2;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$jSfoaK8olAXqL6Ict-vSMRZa-iw(TomatoLiveActivity tomatoLiveActivity, String str, String str2) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = str;
        this.f$2 = str2;
    }

    public final void onLiveAction(int i, boolean z) {
        TomatoLiveActivity.lambda$showSuperControlPermissionDialog$40(this.f$0, this.f$1, this.f$2, i, z);
    }
}

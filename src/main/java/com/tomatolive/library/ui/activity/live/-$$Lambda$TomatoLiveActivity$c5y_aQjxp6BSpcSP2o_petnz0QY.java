package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.ui.view.dialog.BottomDialogUtils.LiveBottomBannedMenuListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$c5y_aQjxp6BSpcSP2o_petnz0QY implements LiveBottomBannedMenuListener {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ String f$1;
    private final /* synthetic */ String f$2;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$c5y_aQjxp6BSpcSP2o_petnz0QY(TomatoLiveActivity tomatoLiveActivity, String str, String str2) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = str;
        this.f$2 = str2;
    }

    public final void onLiveBottomBannedMenuListener(long j) {
        TomatoLiveActivity.lambda$clickBanned$41(this.f$0, this.f$1, this.f$2, j);
    }
}

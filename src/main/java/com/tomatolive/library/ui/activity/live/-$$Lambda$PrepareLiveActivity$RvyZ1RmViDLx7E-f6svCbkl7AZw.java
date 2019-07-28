package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$RvyZ1RmViDLx7E-f6svCbkl7AZw implements Runnable {
    private final /* synthetic */ PrepareLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$RvyZ1RmViDLx7E-f6svCbkl7AZw(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        this.f$0 = prepareLiveActivity;
        this.f$1 = resultData;
    }

    public final void run() {
        this.f$0.mLivePusherInfoView.setGiftNoticeAnim(this.f$1.userName, this.f$1.anchorName, this.f$1.giftNum, this.f$1.giftName, this.f$1.giftId, this.f$0.giftTrumpetPlayPeriod);
    }
}

package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$40mumaNP8OCCSfwcyHHdWz_MchY implements Runnable {
    private final /* synthetic */ PrepareLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$40mumaNP8OCCSfwcyHHdWz_MchY(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        this.f$0 = prepareLiveActivity;
        this.f$1 = resultData;
    }

    public final void run() {
        this.f$0.mLivePusherInfoView.setSysNoticeAnim(this.f$1.content, this.f$0.giftTrumpetPlayPeriod);
    }
}

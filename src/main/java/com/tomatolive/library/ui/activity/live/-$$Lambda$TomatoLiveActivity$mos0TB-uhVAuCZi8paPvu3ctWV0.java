package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$mos0TB-uhVAuCZi8paPvu3ctWV0 implements Runnable {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$mos0TB-uhVAuCZi8paPvu3ctWV0(TomatoLiveActivity tomatoLiveActivity, ResultData resultData) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = resultData;
    }

    public final void run() {
        this.f$0.mLivePusherInfoView.setSysNoticeAnim(this.f$1.content, this.f$0.giftTrumpetPlayPeriod);
    }
}

package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$5zmTbVNebgVz9iOgBTrV7_nnExY implements Runnable {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$5zmTbVNebgVz9iOgBTrV7_nnExY(TomatoLiveActivity tomatoLiveActivity, ResultData resultData) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = resultData;
    }

    public final void run() {
        this.f$0.mLivePusherInfoView.setGiftNoticeAnim(this.f$1.userName, this.f$1.anchorName, this.f$1.giftNum, this.f$1.giftName, this.f$1.giftId, this.f$0.giftTrumpetPlayPeriod);
    }
}

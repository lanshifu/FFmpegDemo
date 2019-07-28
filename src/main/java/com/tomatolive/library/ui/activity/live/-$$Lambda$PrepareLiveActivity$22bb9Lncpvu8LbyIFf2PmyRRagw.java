package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.GiftItemEntity;
import com.tomatolive.library.ui.view.gift.GiftAnimModel;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$22bb9Lncpvu8LbyIFf2PmyRRagw implements Runnable {
    private final /* synthetic */ PrepareLiveActivity f$0;
    private final /* synthetic */ GiftAnimModel f$1;
    private final /* synthetic */ GiftItemEntity f$2;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$22bb9Lncpvu8LbyIFf2PmyRRagw(PrepareLiveActivity prepareLiveActivity, GiftAnimModel giftAnimModel, GiftItemEntity giftItemEntity) {
        this.f$0 = prepareLiveActivity;
        this.f$1 = giftAnimModel;
        this.f$2 = giftItemEntity;
    }

    public final void run() {
        this.f$0.liveAnimationView.loadReceiveGift(this.f$1, this.f$2);
    }
}

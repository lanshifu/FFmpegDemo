package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.GiftItemEntity;
import com.tomatolive.library.ui.view.gift.GiftAnimModel;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$FZyRjw_CJ-zAH6k8sw2uS_Pwlwo implements Runnable {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ GiftAnimModel f$1;
    private final /* synthetic */ GiftItemEntity f$2;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$FZyRjw_CJ-zAH6k8sw2uS_Pwlwo(TomatoLiveActivity tomatoLiveActivity, GiftAnimModel giftAnimModel, GiftItemEntity giftItemEntity) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = giftAnimModel;
        this.f$2 = giftItemEntity;
    }

    public final void run() {
        this.f$0.liveAnimationView.loadGift(this.f$1, this.f$2);
    }
}

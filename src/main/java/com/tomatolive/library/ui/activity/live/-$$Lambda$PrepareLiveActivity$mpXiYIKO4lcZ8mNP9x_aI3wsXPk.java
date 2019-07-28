package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.ChatEntity;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$mpXiYIKO4lcZ8mNP9x_aI3wsXPk implements Runnable {
    private final /* synthetic */ PrepareLiveActivity f$0;
    private final /* synthetic */ ChatEntity f$1;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$mpXiYIKO4lcZ8mNP9x_aI3wsXPk(PrepareLiveActivity prepareLiveActivity, ChatEntity chatEntity) {
        this.f$0 = prepareLiveActivity;
        this.f$1 = chatEntity;
    }

    public final void run() {
        this.f$0.mLivePusherInfoView.addDanmuMsg(this.f$1);
    }
}

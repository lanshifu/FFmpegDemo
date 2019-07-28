package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.ChatEntity;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$7xE-4V0AXCLMcscutEEIbYSuyfY implements Runnable {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ ChatEntity f$1;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$7xE-4V0AXCLMcscutEEIbYSuyfY(TomatoLiveActivity tomatoLiveActivity, ChatEntity chatEntity) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = chatEntity;
    }

    public final void run() {
        this.f$0.mLivePusherInfoView.addDanmuMsg(this.f$1);
    }
}

package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.ChatEntity;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$kzeWgzX7dijuL5UyfR4YuXLCN8g implements Runnable {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ ChatEntity f$1;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$kzeWgzX7dijuL5UyfR4YuXLCN8g(TomatoLiveActivity tomatoLiveActivity, ChatEntity chatEntity) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = chatEntity;
    }

    public final void run() {
        this.f$0.mLiveChatMsgView.addChatMsg(this.f$1);
    }
}

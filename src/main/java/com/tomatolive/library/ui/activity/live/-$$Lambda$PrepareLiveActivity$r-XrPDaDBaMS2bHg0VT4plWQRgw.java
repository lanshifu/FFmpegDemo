package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.ChatEntity;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$r-XrPDaDBaMS2bHg0VT4plWQRgw implements Runnable {
    private final /* synthetic */ PrepareLiveActivity f$0;
    private final /* synthetic */ ChatEntity f$1;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$r-XrPDaDBaMS2bHg0VT4plWQRgw(PrepareLiveActivity prepareLiveActivity, ChatEntity chatEntity) {
        this.f$0 = prepareLiveActivity;
        this.f$1 = chatEntity;
    }

    public final void run() {
        this.f$0.liveChatMsgView.addChatMsg(this.f$1);
    }
}

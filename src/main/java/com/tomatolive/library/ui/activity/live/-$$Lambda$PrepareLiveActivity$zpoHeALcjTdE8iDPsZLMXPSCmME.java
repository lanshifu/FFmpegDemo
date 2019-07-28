package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$zpoHeALcjTdE8iDPsZLMXPSCmME implements Runnable {
    private final /* synthetic */ PrepareLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$zpoHeALcjTdE8iDPsZLMXPSCmME(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        this.f$0 = prepareLiveActivity;
        this.f$1 = resultData;
    }

    public final void run() {
        PrepareLiveActivity.lambda$dealGiftBoxPutMsgFromSocket$30(this.f$0, this.f$1);
    }
}

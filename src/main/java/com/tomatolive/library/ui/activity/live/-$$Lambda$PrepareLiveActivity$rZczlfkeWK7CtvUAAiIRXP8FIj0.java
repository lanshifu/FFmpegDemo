package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$rZczlfkeWK7CtvUAAiIRXP8FIj0 implements Runnable {
    private final /* synthetic */ PrepareLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$rZczlfkeWK7CtvUAAiIRXP8FIj0(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        this.f$0 = prepareLiveActivity;
        this.f$1 = resultData;
    }

    public final void run() {
        PrepareLiveActivity.lambda$onBackThreadReceiveMessage$29(this.f$0, this.f$1);
    }
}

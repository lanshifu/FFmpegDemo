package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$92mX0y8KRmATCtZFq_4g1UJ3v38 implements Runnable {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$92mX0y8KRmATCtZFq_4g1UJ3v38(TomatoLiveActivity tomatoLiveActivity, ResultData resultData) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = resultData;
    }

    public final void run() {
        TomatoLiveActivity.lambda$dealUserBalanceMsgFromSocket$30(this.f$0, this.f$1);
    }
}

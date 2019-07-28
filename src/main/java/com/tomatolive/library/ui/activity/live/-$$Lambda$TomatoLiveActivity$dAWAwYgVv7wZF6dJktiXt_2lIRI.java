package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$dAWAwYgVv7wZF6dJktiXt_2lIRI implements Runnable {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$dAWAwYgVv7wZF6dJktiXt_2lIRI(TomatoLiveActivity tomatoLiveActivity, ResultData resultData) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = resultData;
    }

    public final void run() {
        TomatoLiveActivity.lambda$onBackThreadReceiveMessage$25(this.f$0, this.f$1);
    }
}

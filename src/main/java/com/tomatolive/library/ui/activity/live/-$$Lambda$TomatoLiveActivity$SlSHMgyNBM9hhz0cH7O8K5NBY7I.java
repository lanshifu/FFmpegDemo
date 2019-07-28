package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$SlSHMgyNBM9hhz0cH7O8K5NBY7I implements Runnable {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ boolean f$1;
    private final /* synthetic */ ResultData f$2;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$SlSHMgyNBM9hhz0cH7O8K5NBY7I(TomatoLiveActivity tomatoLiveActivity, boolean z, ResultData resultData) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = z;
        this.f$2 = resultData;
    }

    public final void run() {
        TomatoLiveActivity.lambda$dealBanPostMsgFromSocket$33(this.f$0, this.f$1, this.f$2);
    }
}

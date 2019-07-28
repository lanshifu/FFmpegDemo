package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$nvA4R4qkKA7hZcLuLBFCnp12Fsg implements Runnable {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$nvA4R4qkKA7hZcLuLBFCnp12Fsg(TomatoLiveActivity tomatoLiveActivity, ResultData resultData) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = resultData;
    }

    public final void run() {
        this.f$0.dealLeaveMsgFromSocket(this.f$1);
    }
}

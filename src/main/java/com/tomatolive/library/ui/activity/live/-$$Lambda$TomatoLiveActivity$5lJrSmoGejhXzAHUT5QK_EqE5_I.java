package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$5lJrSmoGejhXzAHUT5QK_EqE5_I implements Runnable {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;
    private final /* synthetic */ String f$2;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$5lJrSmoGejhXzAHUT5QK_EqE5_I(TomatoLiveActivity tomatoLiveActivity, ResultData resultData, String str) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = resultData;
        this.f$2 = str;
    }

    public final void run() {
        TomatoLiveActivity.lambda$dealEnterMsg$54(this.f$0, this.f$1, this.f$2);
    }
}

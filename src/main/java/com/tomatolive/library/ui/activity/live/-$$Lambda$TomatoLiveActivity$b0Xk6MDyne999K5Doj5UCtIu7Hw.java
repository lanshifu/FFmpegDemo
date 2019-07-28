package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;
import com.tomatolive.library.utils.y.a;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$b0Xk6MDyne999K5Doj5UCtIu7Hw implements a {
    private final /* synthetic */ TomatoLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$b0Xk6MDyne999K5Doj5UCtIu7Hw(TomatoLiveActivity tomatoLiveActivity, ResultData resultData) {
        this.f$0 = tomatoLiveActivity;
        this.f$1 = resultData;
    }

    public final void onSuc(String str) {
        this.f$0.showReceiveMsgOnChatList(this.f$1, str, 3);
    }
}

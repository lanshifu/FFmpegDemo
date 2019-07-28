package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.SocketMessageEvent.ResultData;
import com.tomatolive.library.utils.y.a;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$zNtbE37tag_Mg_2HgRKMSt6gCFk implements a {
    private final /* synthetic */ PrepareLiveActivity f$0;
    private final /* synthetic */ ResultData f$1;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$zNtbE37tag_Mg_2HgRKMSt6gCFk(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        this.f$0 = prepareLiveActivity;
        this.f$1 = resultData;
    }

    public final void onSuc(String str) {
        this.f$0.showReceiveMsgOnChatList(this.f$1, str, 3);
    }
}

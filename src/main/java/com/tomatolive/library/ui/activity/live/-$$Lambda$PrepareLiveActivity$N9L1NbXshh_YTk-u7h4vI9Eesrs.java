package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.ChatEntity;
import com.tomatolive.library.ui.adapter.ChatMsgListAdapter.OnItemClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$N9L1NbXshh_YTk-u7h4vI9Eesrs implements OnItemClickListener {
    private final /* synthetic */ PrepareLiveActivity f$0;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$N9L1NbXshh_YTk-u7h4vI9Eesrs(PrepareLiveActivity prepareLiveActivity) {
        this.f$0 = prepareLiveActivity;
    }

    public final void onItemClick(ChatEntity chatEntity) {
        PrepareLiveActivity.lambda$initChatList$18(this.f$0, chatEntity);
    }
}

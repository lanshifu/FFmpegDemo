package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.ChatEntity;
import com.tomatolive.library.ui.adapter.ChatMsgListAdapter.OnItemClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$QbZkUQ20HFouQXNA0_qSEA2LVNQ implements OnItemClickListener {
    private final /* synthetic */ TomatoLiveActivity f$0;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$QbZkUQ20HFouQXNA0_qSEA2LVNQ(TomatoLiveActivity tomatoLiveActivity) {
        this.f$0 = tomatoLiveActivity;
    }

    public final void onItemClick(ChatEntity chatEntity) {
        this.f$0.showUserCard(chatEntity);
    }
}

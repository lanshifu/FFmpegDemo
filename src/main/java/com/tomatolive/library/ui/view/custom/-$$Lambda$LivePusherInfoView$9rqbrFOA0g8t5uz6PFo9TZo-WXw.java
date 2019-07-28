package com.tomatolive.library.ui.view.custom;

import com.tomatolive.library.model.UserEntity;
import com.tomatolive.library.ui.adapter.UserAvatarListAdapter.UserListClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$LivePusherInfoView$9rqbrFOA0g8t5uz6PFo9TZo-WXw implements UserListClickListener {
    private final /* synthetic */ LivePusherInfoView f$0;

    public /* synthetic */ -$$Lambda$LivePusherInfoView$9rqbrFOA0g8t5uz6PFo9TZo-WXw(LivePusherInfoView livePusherInfoView) {
        this.f$0 = livePusherInfoView;
    }

    public final void onUserClick(UserEntity userEntity) {
        LivePusherInfoView.lambda$initUserAvatarListAdapter$6(this.f$0, userEntity);
    }
}

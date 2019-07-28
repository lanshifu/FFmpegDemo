package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.ReceiveGiftRecordEntity;
import com.tomatolive.library.ui.adapter.GiftRecordAdapter.OnGiftRecordListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$chfL_J4KZiZzC6Sfrp3pTYo0AN4 implements OnGiftRecordListener {
    private final /* synthetic */ PrepareLiveActivity f$0;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$chfL_J4KZiZzC6Sfrp3pTYo0AN4(PrepareLiveActivity prepareLiveActivity) {
        this.f$0 = prepareLiveActivity;
    }

    public final void onItemClick(ReceiveGiftRecordEntity receiveGiftRecordEntity) {
        this.f$0.showUserAvatarDialog(receiveGiftRecordEntity.avatar, receiveGiftRecordEntity.userName, receiveGiftRecordEntity.userId, receiveGiftRecordEntity.sex, "", receiveGiftRecordEntity.expGrade, receiveGiftRecordEntity.getRole(), receiveGiftRecordEntity.guardType);
    }
}

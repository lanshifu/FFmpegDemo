package com.tomatolive.library.ui.activity.mylive;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tomatolive.library.model.BannedEntity;
import com.tomatolive.library.ui.view.dialog.BottomDialogUtils.LiveBottomBannedMenuListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$BannedSettingActivity$a0QHmU4A-pLJQUV2TE0z2yna_bo implements LiveBottomBannedMenuListener {
    private final /* synthetic */ BannedSettingActivity f$0;
    private final /* synthetic */ BannedEntity f$1;
    private final /* synthetic */ BaseQuickAdapter f$2;
    private final /* synthetic */ int f$3;

    public /* synthetic */ -$$Lambda$BannedSettingActivity$a0QHmU4A-pLJQUV2TE0z2yna_bo(BannedSettingActivity bannedSettingActivity, BannedEntity bannedEntity, BaseQuickAdapter baseQuickAdapter, int i) {
        this.f$0 = bannedSettingActivity;
        this.f$1 = bannedEntity;
        this.f$2 = baseQuickAdapter;
        this.f$3 = i;
    }

    public final void onLiveBottomBannedMenuListener(long j) {
        BannedSettingActivity.lambda$null$5(this.f$0, this.f$1, this.f$2, this.f$3, j);
    }
}

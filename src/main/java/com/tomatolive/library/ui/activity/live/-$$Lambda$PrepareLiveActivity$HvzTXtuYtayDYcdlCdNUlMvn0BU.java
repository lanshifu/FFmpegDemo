package com.tomatolive.library.ui.activity.live;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tomatolive.library.model.MenuEntity;
import com.tomatolive.library.ui.view.dialog.BottomDialogUtils.LiveBottomMoreMenuListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$HvzTXtuYtayDYcdlCdNUlMvn0BU implements LiveBottomMoreMenuListener {
    private final /* synthetic */ PrepareLiveActivity f$0;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$HvzTXtuYtayDYcdlCdNUlMvn0BU(PrepareLiveActivity prepareLiveActivity) {
        this.f$0 = prepareLiveActivity;
    }

    public final void onLiveBottomMoreMenuListener(BaseQuickAdapter baseQuickAdapter, MenuEntity menuEntity, int i) {
        PrepareLiveActivity.lambda$initBottomMoreDialog$22(this.f$0, baseQuickAdapter, menuEntity, i);
    }
}

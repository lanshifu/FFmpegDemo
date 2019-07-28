package com.tomatolive.library.ui.view.gift.giftpanel;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$GiftPanelControl$yVR1s76AcIar06ldwmyIClydvok implements OnItemClickListener {
    private final /* synthetic */ GiftPanelControl f$0;
    private final /* synthetic */ GiftAdapter f$1;

    public /* synthetic */ -$$Lambda$GiftPanelControl$yVR1s76AcIar06ldwmyIClydvok(GiftPanelControl giftPanelControl, GiftAdapter giftAdapter) {
        this.f$0 = giftPanelControl;
        this.f$1 = giftAdapter;
    }

    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        GiftPanelControl.lambda$viewPagerItem$0(this.f$0, this.f$1, baseQuickAdapter, view, i);
    }
}

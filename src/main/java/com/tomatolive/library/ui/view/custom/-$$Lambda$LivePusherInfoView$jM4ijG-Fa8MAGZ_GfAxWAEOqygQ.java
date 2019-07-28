package com.tomatolive.library.ui.view.custom;

import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.ui.view.dialog.GuardOpenContentDialog;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$LivePusherInfoView$jM4ijG-Fa8MAGZ_GfAxWAEOqygQ implements OnClickListener {
    private final /* synthetic */ LivePusherInfoView f$0;

    public /* synthetic */ -$$Lambda$LivePusherInfoView$jM4ijG-Fa8MAGZ_GfAxWAEOqygQ(LivePusherInfoView livePusherInfoView) {
        this.f$0 = livePusherInfoView;
    }

    public final void onClick(View view) {
        GuardOpenContentDialog.newInstance(this.f$0.guardInfoItem, this.f$0.openGuardCallbackListener).show(this.f$0.fragmentManager);
    }
}

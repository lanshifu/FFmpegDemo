package com.tomatolive.library.ui.view.dialog;

import android.app.Dialog;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.tomatolive.library.ui.view.dialog.BottomDialogUtils.LiveBottomMoreMenuListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$BottomDialogUtils$B1HMJ1OAjcHNqsZXOPvRXcCiD58 implements OnItemClickListener {
    private final /* synthetic */ Dialog f$0;
    private final /* synthetic */ LiveBottomMoreMenuListener f$1;

    public /* synthetic */ -$$Lambda$BottomDialogUtils$B1HMJ1OAjcHNqsZXOPvRXcCiD58(Dialog dialog, LiveBottomMoreMenuListener liveBottomMoreMenuListener) {
        this.f$0 = dialog;
        this.f$1 = liveBottomMoreMenuListener;
    }

    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        BottomDialogUtils.lambda$getLiveBottomDialog$0(this.f$0, this.f$1, baseQuickAdapter, view, i);
    }
}

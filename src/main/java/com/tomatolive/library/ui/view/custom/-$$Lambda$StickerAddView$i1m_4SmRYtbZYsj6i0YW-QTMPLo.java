package com.tomatolive.library.ui.view.custom;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$StickerAddView$i1m_4SmRYtbZYsj6i0YW-QTMPLo implements OnDismissListener {
    private final /* synthetic */ StickerAddView f$0;

    public /* synthetic */ -$$Lambda$StickerAddView$i1m_4SmRYtbZYsj6i0YW-QTMPLo(StickerAddView stickerAddView) {
        this.f$0 = stickerAddView;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.f$0.setVisibility(0);
    }
}

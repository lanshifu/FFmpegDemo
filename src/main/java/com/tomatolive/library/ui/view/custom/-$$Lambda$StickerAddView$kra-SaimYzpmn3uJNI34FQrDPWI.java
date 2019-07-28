package com.tomatolive.library.ui.view.custom;

import com.tomatolive.library.ui.view.dialog.StickerHelpTipsDialog;
import com.tomatolive.library.utils.r.a;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$StickerAddView$kra-SaimYzpmn3uJNI34FQrDPWI implements a {
    private final /* synthetic */ StickerAddView f$0;

    public /* synthetic */ -$$Lambda$StickerAddView$kra-SaimYzpmn3uJNI34FQrDPWI(StickerAddView stickerAddView) {
        this.f$0 = stickerAddView;
    }

    public final void action(Object obj) {
        StickerHelpTipsDialog.newInstance().show(this.f$0.mFragmentManager);
    }
}

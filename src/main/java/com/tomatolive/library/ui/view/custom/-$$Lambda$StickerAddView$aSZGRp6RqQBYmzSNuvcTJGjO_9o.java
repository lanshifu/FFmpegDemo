package com.tomatolive.library.ui.view.custom;

import android.view.View;
import com.tomatolive.library.ui.view.dialog.StickerEditTipsDialog;
import com.tomatolive.library.utils.c;
import com.tomatolive.library.utils.r.a;
import defpackage.sh;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.k;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$StickerAddView$aSZGRp6RqQBYmzSNuvcTJGjO_9o implements a {
    private final /* synthetic */ StickerAddView f$0;

    public /* synthetic */ -$$Lambda$StickerAddView$aSZGRp6RqQBYmzSNuvcTJGjO_9o(StickerAddView stickerAddView) {
        this.f$0 = stickerAddView;
    }

    public final void action(Object obj) {
        k.just(Boolean.valueOf(this.f$0.isStickerEdit())).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new sh<Boolean>() {
            public void accept(Boolean bool) {
                if (bool.booleanValue()) {
                    StickerEditTipsDialog.newInstance(new -$$Lambda$StickerAddView$1$M1eif0PI49esbFMsioHPfmrMqYg(this)).show(StickerAddView.this.mFragmentManager);
                    return;
                }
                StickerAddView.this.setVisibility(4);
                StickerAddView.this.onAddStickerCallback.onSaveStickerClick();
            }

            public static /* synthetic */ void lambda$accept$0(AnonymousClass1 anonymousClass1, View view) {
                StickerAddView.this.removeViewAt(0);
                StickerAddView.this.mStickerShowView = null;
                StickerAddView.this.setVisibility(4);
                StickerAddView.this.addStickerView();
                StickerAddView.this.addLastTextList(c.b());
                StickerAddView.this.onAddStickerCallback.onSaveStickerClick();
            }
        });
    }
}

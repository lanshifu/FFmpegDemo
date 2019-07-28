package com.tomatolive.library.ui.view.custom;

import com.tomatolive.library.ui.view.widget.progress.AnimDownloadProgressButton.LoadingEndListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$GiftBoxView$xovRGYXDzcFJVJhUgp54pk-67i8 implements LoadingEndListener {
    private final /* synthetic */ GiftBoxView f$0;

    public /* synthetic */ -$$Lambda$GiftBoxView$xovRGYXDzcFJVJhUgp54pk-67i8(GiftBoxView giftBoxView) {
        this.f$0 = giftBoxView;
    }

    public final void onLoadingEnd() {
        GiftBoxView.lambda$showLoading$1(this.f$0);
    }
}

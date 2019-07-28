package com.tomatolive.library.ui.activity.home;

import android.webkit.DownloadListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$WebViewActivity$TAqA-QuW7_geIjwNIcENzKkoVxk implements DownloadListener {
    private final /* synthetic */ WebViewActivity f$0;

    public /* synthetic */ -$$Lambda$WebViewActivity$TAqA-QuW7_geIjwNIcENzKkoVxk(WebViewActivity webViewActivity) {
        this.f$0 = webViewActivity;
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        WebViewActivity.lambda$initWebView$2(this.f$0, str, str2, str3, str4, j);
    }
}

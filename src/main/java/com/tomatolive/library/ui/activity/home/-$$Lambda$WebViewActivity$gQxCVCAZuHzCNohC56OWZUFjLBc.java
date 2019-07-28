package com.tomatolive.library.ui.activity.home;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$WebViewActivity$gQxCVCAZuHzCNohC56OWZUFjLBc implements OnClickListener {
    private final /* synthetic */ WebViewActivity f$0;
    private final /* synthetic */ String f$1;

    public /* synthetic */ -$$Lambda$WebViewActivity$gQxCVCAZuHzCNohC56OWZUFjLBc(WebViewActivity webViewActivity, String str) {
        this.f$0 = webViewActivity;
        this.f$1 = str;
    }

    public final void onClick(View view) {
        this.f$0.wvContent.loadUrl(this.f$1);
    }
}

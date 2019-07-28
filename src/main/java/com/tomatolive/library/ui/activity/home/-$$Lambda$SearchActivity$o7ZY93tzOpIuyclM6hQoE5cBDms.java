package com.tomatolive.library.ui.activity.home;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$SearchActivity$o7ZY93tzOpIuyclM6hQoE5cBDms implements OnEditorActionListener {
    private final /* synthetic */ SearchActivity f$0;

    public /* synthetic */ -$$Lambda$SearchActivity$o7ZY93tzOpIuyclM6hQoE5cBDms(SearchActivity searchActivity) {
        this.f$0 = searchActivity;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return SearchActivity.lambda$initListener$3(this.f$0, textView, i, keyEvent);
    }
}

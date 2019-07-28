package com.tomatolive.library.ui.activity.mylive;

import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.ui.presenter.LivePreNoticePresenter;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$LivePreNoticeActivity$PCWu8l1nG4gGI1Kg0_cmqe8XLbA implements OnClickListener {
    private final /* synthetic */ LivePreNoticeActivity f$0;
    private final /* synthetic */ String f$1;

    public /* synthetic */ -$$Lambda$LivePreNoticeActivity$PCWu8l1nG4gGI1Kg0_cmqe8XLbA(LivePreNoticeActivity livePreNoticeActivity, String str) {
        this.f$0 = livePreNoticeActivity;
        this.f$1 = str;
    }

    public final void onClick(View view) {
        ((LivePreNoticePresenter) this.f$0.mPresenter).onSaveContent(this.f$1);
    }
}

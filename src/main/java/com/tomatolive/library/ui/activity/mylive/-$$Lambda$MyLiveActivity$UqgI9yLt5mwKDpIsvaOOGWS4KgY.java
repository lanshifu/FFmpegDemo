package com.tomatolive.library.ui.activity.mylive;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$MyLiveActivity$UqgI9yLt5mwKDpIsvaOOGWS4KgY implements OnClickListener {
    private final /* synthetic */ MyLiveActivity f$0;

    public /* synthetic */ -$$Lambda$MyLiveActivity$UqgI9yLt5mwKDpIsvaOOGWS4KgY(MyLiveActivity myLiveActivity) {
        this.f$0 = myLiveActivity;
    }

    public final void onClick(View view) {
        this.f$0.startActivityByLogin(LivePreNoticeActivity.class);
    }
}

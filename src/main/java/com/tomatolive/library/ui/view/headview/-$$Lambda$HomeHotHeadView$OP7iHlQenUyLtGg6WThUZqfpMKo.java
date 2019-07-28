package com.tomatolive.library.ui.view.headview;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.ui.activity.home.RankingActivity;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$HomeHotHeadView$OP7iHlQenUyLtGg6WThUZqfpMKo implements OnClickListener {
    private final /* synthetic */ HomeHotHeadView f$0;

    public /* synthetic */ -$$Lambda$HomeHotHeadView$OP7iHlQenUyLtGg6WThUZqfpMKo(HomeHotHeadView homeHotHeadView) {
        this.f$0 = homeHotHeadView;
    }

    public final void onClick(View view) {
        this.f$0.mContext.startActivity(new Intent(this.f$0.mContext, RankingActivity.class));
    }
}

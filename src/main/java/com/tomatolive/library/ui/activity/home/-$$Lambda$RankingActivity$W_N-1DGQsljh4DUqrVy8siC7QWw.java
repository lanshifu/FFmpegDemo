package com.tomatolive.library.ui.activity.home;

import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.ui.view.dialog.RankingAllDialog;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$RankingActivity$W_N-1DGQsljh4DUqrVy8siC7QWw implements OnClickListener {
    private final /* synthetic */ RankingActivity f$0;

    public /* synthetic */ -$$Lambda$RankingActivity$W_N-1DGQsljh4DUqrVy8siC7QWw(RankingActivity rankingActivity) {
        this.f$0 = rankingActivity;
    }

    public final void onClick(View view) {
        RankingAllDialog.newInstance(this.f$0.topTagValue, new -$$Lambda$RankingActivity$V6EomXaXGRcsU_Eofvsc0v5BdKA(this.f$0)).show(this.f$0.getSupportFragmentManager());
    }
}

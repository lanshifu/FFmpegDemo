package com.tomatolive.library.ui.activity.mylive;

import com.tomatolive.library.ui.presenter.AnchorGradePresenter;
import com.tomatolive.library.ui.view.widget.StateView.OnRetryClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$AnchorGradeActivity$FptS93L2JXZwuHDBhWY5Vgv_wJs implements OnRetryClickListener {
    private final /* synthetic */ AnchorGradeActivity f$0;

    public /* synthetic */ -$$Lambda$AnchorGradeActivity$FptS93L2JXZwuHDBhWY5Vgv_wJs(AnchorGradeActivity anchorGradeActivity) {
        this.f$0 = anchorGradeActivity;
    }

    public final void onRetryClick() {
        ((AnchorGradePresenter) this.f$0.mPresenter).getData(this.f$0.mStateView, true);
    }
}

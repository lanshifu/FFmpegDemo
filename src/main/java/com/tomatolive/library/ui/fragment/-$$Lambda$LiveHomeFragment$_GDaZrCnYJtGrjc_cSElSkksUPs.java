package com.tomatolive.library.ui.fragment;

import com.tomatolive.library.ui.presenter.HomePresenter;
import com.tomatolive.library.ui.view.widget.StateView.OnRetryClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$LiveHomeFragment$_GDaZrCnYJtGrjc_cSElSkksUPs implements OnRetryClickListener {
    private final /* synthetic */ LiveHomeFragment f$0;

    public /* synthetic */ -$$Lambda$LiveHomeFragment$_GDaZrCnYJtGrjc_cSElSkksUPs(LiveHomeFragment liveHomeFragment) {
        this.f$0 = liveHomeFragment;
    }

    public final void onRetryClick() {
        ((HomePresenter) this.f$0.mPresenter).getTagList(this.f$0.mStateView, true);
    }
}

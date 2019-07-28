package com.tomatolive.library.ui.fragment;

import com.tomatolive.library.ui.presenter.ConsumePresenter;
import com.tomatolive.library.ui.view.widget.StateView.OnRetryClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$ConsumeFragment$hGAmTjfI7H42CJtjMCFaA2WhPRk implements OnRetryClickListener {
    private final /* synthetic */ ConsumeFragment f$0;

    public /* synthetic */ -$$Lambda$ConsumeFragment$hGAmTjfI7H42CJtjMCFaA2WhPRk(ConsumeFragment consumeFragment) {
        this.f$0 = consumeFragment;
    }

    public final void onRetryClick() {
        ((ConsumePresenter) this.f$0.mPresenter).getDataList(this.f$0.mStateView, true, this.f$0.mChoosedDate);
    }
}

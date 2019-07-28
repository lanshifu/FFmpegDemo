package com.tomatolive.library.ui.fragment;

import com.tomatolive.library.ui.presenter.IncomePresenter;
import com.tomatolive.library.ui.view.widget.StateView.OnRetryClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$IncomeFragment$_aOJLhUr3QtL_6bYMJcvq7qv-Uc implements OnRetryClickListener {
    private final /* synthetic */ IncomeFragment f$0;

    public /* synthetic */ -$$Lambda$IncomeFragment$_aOJLhUr3QtL_6bYMJcvq7qv-Uc(IncomeFragment incomeFragment) {
        this.f$0 = incomeFragment;
    }

    public final void onRetryClick() {
        ((IncomePresenter) this.f$0.mPresenter).getDataList(this.f$0.mStateView, true, this.f$0.mChoosedDate);
    }
}

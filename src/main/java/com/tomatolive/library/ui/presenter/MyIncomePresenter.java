package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.iview.IMyIncomeView;

public class MyIncomePresenter extends a<IMyIncomeView> {
    public MyIncomePresenter(Context context, IMyIncomeView iMyIncomeView) {
        super(context, iMyIncomeView);
    }

    public void initData() {
        addMapSubscription(this.mApiService.getUserBalanceService(new RequestParams().getUserIdParams()), new HttpRxObserver(getContext(), new ResultCallBack<AnchorEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(AnchorEntity anchorEntity) {
                ((IMyIncomeView) MyIncomePresenter.this.getView()).onDataSuccess(anchorEntity);
            }
        }));
    }
}

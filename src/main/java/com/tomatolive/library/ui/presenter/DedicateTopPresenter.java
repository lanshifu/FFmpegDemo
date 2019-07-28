package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.iview.IDedicateTopView;
import com.tomatolive.library.ui.view.widget.StateView;
import java.util.List;

public class DedicateTopPresenter extends a<IDedicateTopView> {
    public DedicateTopPresenter(Context context, IDedicateTopView iDedicateTopView) {
        super(context, iDedicateTopView);
    }

    public void getDataList(StateView stateView, String str, int i, boolean z, final boolean z2) {
        addMapSubscription(this.mApiService.getDedicateTopListService(new RequestParams().getContributionListParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<List<AnchorEntity>>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(List<AnchorEntity> list) {
                if (list != null) {
                    ((IDedicateTopView) DedicateTopPresenter.this.getView()).onDataListSuccess(list, false, z2);
                }
            }
        }, stateView, z));
    }
}

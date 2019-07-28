package com.tomatolive.library.ui.presenter;

import android.content.Context;
import android.text.TextUtils;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.view.iview.IHomeSortView;
import com.tomatolive.library.ui.view.widget.StateView;

public class HomeSortPresenter extends a<IHomeSortView> {
    public HomeSortPresenter(Context context, IHomeSortView iHomeSortView) {
        super(context, iHomeSortView);
    }

    public void getLiveList(StateView stateView, String str, int i, boolean z, final boolean z2) {
        if (TextUtils.equals(str, "allTagId")) {
            addMapSubscription(this.mApiService.getAllListService(new RequestParams().getTagPageListParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() {
                public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                    if (httpResultPageModel != null) {
                        ((IHomeSortView) HomeSortPresenter.this.getView()).onLiveListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                    }
                }

                public void onError(int i, String str) {
                    ((IHomeSortView) HomeSortPresenter.this.getView()).onLiveListFail();
                }
            }, stateView, z));
        } else {
            addMapSubscription(this.mApiService.getSortListService(new RequestParams().getTagPageListParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() {
                public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                    if (httpResultPageModel != null) {
                        ((IHomeSortView) HomeSortPresenter.this.getView()).onLiveListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                    }
                }

                public void onError(int i, String str) {
                    ((IHomeSortView) HomeSortPresenter.this.getView()).onLiveListFail();
                }
            }, stateView, z));
        }
    }
}

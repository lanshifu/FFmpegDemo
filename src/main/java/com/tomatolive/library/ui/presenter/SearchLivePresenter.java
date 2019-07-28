package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.view.iview.ISearchLiveView;
import com.tomatolive.library.ui.view.widget.StateView;

public class SearchLivePresenter extends a<ISearchLiveView> {
    public SearchLivePresenter(Context context, ISearchLiveView iSearchLiveView) {
        super(context, iSearchLiveView);
    }

    public void getLiveList(StateView stateView, String str, int i, boolean z, final boolean z2) {
        addMapSubscription(this.mApiService.getSearchLiveListService(new RequestParams().getPageListByKeyParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() {
            public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                if (httpResultPageModel != null) {
                    ((ISearchLiveView) SearchLivePresenter.this.getView()).onDataListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                }
            }

            public void onError(int i, String str) {
                ((ISearchLiveView) SearchLivePresenter.this.getView()).onResultError(i);
            }
        }, stateView, z));
    }
}

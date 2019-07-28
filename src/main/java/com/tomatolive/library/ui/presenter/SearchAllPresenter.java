package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.view.iview.ISearchAllView;
import com.tomatolive.library.ui.view.widget.StateView;

public class SearchAllPresenter extends a<ISearchAllView> {
    public SearchAllPresenter(Context context, ISearchAllView iSearchAllView) {
        super(context, iSearchAllView);
    }

    public void getAnchorList(String str) {
        addMapSubscription(this.mApiService.getSearchAnchorListService(new RequestParams().getSearchAnchorListParams(str, 1)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<AnchorEntity>>() {
            public void onSuccess(HttpResultPageModel<AnchorEntity> httpResultPageModel) {
                if (httpResultPageModel != null) {
                    ((ISearchAllView) SearchAllPresenter.this.getView()).onAnchorListSuccess(httpResultPageModel.dataList);
                }
            }

            public void onError(int i, String str) {
                ((ISearchAllView) SearchAllPresenter.this.getView()).onAnchorListFail();
            }
        }));
    }

    public void getLiveList(StateView stateView, String str, int i, boolean z, final boolean z2) {
        addMapSubscription(this.mApiService.getSearchLiveListService(new RequestParams().getPageListByKeyParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() {
            public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                if (httpResultPageModel != null) {
                    ((ISearchAllView) SearchAllPresenter.this.getView()).onLiveListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                }
            }

            public void onError(int i, String str) {
                ((ISearchAllView) SearchAllPresenter.this.getView()).onResultError(i);
            }
        }, stateView, z));
    }
}

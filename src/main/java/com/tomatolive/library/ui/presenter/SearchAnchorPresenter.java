package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.iview.ISearchAnchorView;
import com.tomatolive.library.ui.view.widget.StateView;

public class SearchAnchorPresenter extends a<ISearchAnchorView> {
    public SearchAnchorPresenter(Context context, ISearchAnchorView iSearchAnchorView) {
        super(context, iSearchAnchorView);
    }

    public void getAnchorList(StateView stateView, String str, int i, boolean z, final boolean z2) {
        addMapSubscription(this.mApiService.getSearchAnchorListService(new RequestParams().getSearchAnchorListParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<AnchorEntity>>() {
            public void onSuccess(HttpResultPageModel<AnchorEntity> httpResultPageModel) {
                if (httpResultPageModel != null) {
                    ((ISearchAnchorView) SearchAnchorPresenter.this.getView()).onDataListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                }
            }

            public void onError(int i, String str) {
                ((ISearchAnchorView) SearchAnchorPresenter.this.getView()).onResultError(i);
            }
        }, stateView, z));
    }

    public void attentionAnchor(String str, int i) {
        addMapSubscription(this.mApiService.getAttentionAnchorService(new RequestParams().getAttentionAnchorParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                ((ISearchAnchorView) SearchAnchorPresenter.this.getView()).onAttentionSuccess();
            }
        }));
    }
}

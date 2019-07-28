package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.view.iview.IHomeAttentionView;
import com.tomatolive.library.ui.view.widget.StateView;
import java.util.List;

public class HomeAttentionPresenter extends a<IHomeAttentionView> {
    public HomeAttentionPresenter(Context context, IHomeAttentionView iHomeAttentionView) {
        super(context, iHomeAttentionView);
    }

    public void getAttentionAnchorListList(StateView stateView, int i, boolean z, final boolean z2) {
        addMapSubscription(this.mApiService.getAttentionAnchorListService(new RequestParams().getPageListByIdParams(i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() {
            public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                if (httpResultPageModel != null) {
                    ((IHomeAttentionView) HomeAttentionPresenter.this.getView()).onAttentionListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                }
            }

            public void onError(int i, String str) {
                ((IHomeAttentionView) HomeAttentionPresenter.this.getView()).onAttentionListFail();
            }
        }, stateView, z));
    }

    public void getRecommendAnchorList(StateView stateView, boolean z) {
        addMapSubscription(this.mApiService.getRecommendAnchorService(new RequestParams().getPageListParams(1, 3)), new HttpRxObserver(getContext(), new ResultCallBack<List<AnchorEntity>>() {
            public void onSuccess(List<AnchorEntity> list) {
                if (list != null) {
                    ((IHomeAttentionView) HomeAttentionPresenter.this.getView()).onRecommendListSuccess(list);
                }
            }

            public void onError(int i, String str) {
                ((IHomeAttentionView) HomeAttentionPresenter.this.getView()).onRecommendListFail();
            }
        }, stateView, z));
    }

    public void attentionAnchor(String str, int i) {
        addMapSubscription(this.mApiService.getAttentionAnchorService(new RequestParams().getAttentionAnchorParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                ((IHomeAttentionView) HomeAttentionPresenter.this.getView()).onAttentionSuccess();
            }
        }));
    }
}

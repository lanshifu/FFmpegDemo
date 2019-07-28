package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.view.iview.IHomeHotView;
import com.tomatolive.library.ui.view.widget.StateView;
import java.util.List;

public class HomeHotPresenter extends a<IHomeHotView> {
    public HomeHotPresenter(Context context, IHomeHotView iHomeHotView) {
        super(context, iHomeHotView);
    }

    public void getLiveList(StateView stateView, int i, boolean z, final boolean z2) {
        addMapSubscription(this.mApiService.getRecommendListService(new RequestParams().getPageListParams(i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() {
            public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                if (httpResultPageModel != null) {
                    ((IHomeHotView) HomeHotPresenter.this.getView()).onLiveListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                }
            }

            public void onError(int i, String str) {
                ((IHomeHotView) HomeHotPresenter.this.getView()).onLiveListFail();
            }
        }, stateView, z));
    }

    public void onAnchorAuth() {
        addMapSubscription(this.mApiService.getAnchorAuthService(new RequestParams().getUserIdParams()), new HttpRxObserver(getContext(), new ResultCallBack<AnchorEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(AnchorEntity anchorEntity) {
                if (anchorEntity != null) {
                    ((IHomeHotView) HomeHotPresenter.this.getView()).onAnchorAuthSuccess(anchorEntity);
                }
            }
        }, true));
    }

    public void getBannerList(String str) {
        addMapSubscription(this.mApiService.getBannerListService(new RequestParams().getBannerListParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<List<BannerEntity>>() {
            public void onSuccess(List<BannerEntity> list) {
                ((IHomeHotView) HomeHotPresenter.this.getView()).onBannerListSuccess(list);
            }

            public void onError(int i, String str) {
                ((IHomeHotView) HomeHotPresenter.this.getView()).onBannerListFail();
            }
        }));
    }

    public void getTopList() {
        addMapSubscription(this.mApiService.getHomeCharmTopListService(new RequestParams().getHomeStrengthTopParams("day")), new HttpRxObserver(getContext(), new ResultCallBack<List<AnchorEntity>>() {
            public void onSuccess(List<AnchorEntity> list) {
                if (list != null) {
                    ((IHomeHotView) HomeHotPresenter.this.getView()).onTopListSuccess(list);
                }
            }

            public void onError(int i, String str) {
                ((IHomeHotView) HomeHotPresenter.this.getView()).onTopListFail();
            }
        }));
    }
}

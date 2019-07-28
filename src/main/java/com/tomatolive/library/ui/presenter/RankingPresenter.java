package com.tomatolive.library.ui.presenter;

import android.content.Context;
import android.widget.LinearLayout;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.iview.IRankingView;
import com.tomatolive.library.ui.view.widget.LoadingView;
import com.tomatolive.library.ui.view.widget.StateView;
import java.util.List;

public class RankingPresenter extends a<IRankingView> {
    public RankingPresenter(Context context, IRankingView iRankingView) {
        super(context, iRankingView);
    }

    public void getCharmTopList(StateView stateView, String str, final int i, boolean z, final boolean z2) {
        addMapSubscription(this.mApiService.getCharmTopListService(new RequestParams().getHomeTopParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<List<AnchorEntity>>() {
            public void onSuccess(List<AnchorEntity> list) {
                if (list != null) {
                    ((IRankingView) RankingPresenter.this.getView()).onCharmTopListSuccess(list, i, z2);
                }
            }

            public void onError(int i, String str) {
                ((IRankingView) RankingPresenter.this.getView()).onResultError(i);
            }
        }, stateView, z));
    }

    public void getStrengthTopList(StateView stateView, String str, final int i, boolean z, final boolean z2) {
        addMapSubscription(this.mApiService.getStrengthTopListService(new RequestParams().getHomeStrengthTopParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<List<AnchorEntity>>() {
            public void onSuccess(List<AnchorEntity> list) {
                if (list != null) {
                    ((IRankingView) RankingPresenter.this.getView()).onStrengthTopListSuccess(list, i, z2);
                }
            }

            public void onError(int i, String str) {
                ((IRankingView) RankingPresenter.this.getView()).onResultError(i);
            }
        }, stateView, z));
    }

    public void attentionAnchor(String str, int i) {
        addMapSubscription(this.mApiService.getAttentionAnchorService(new RequestParams().getAttentionAnchorParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                ((IRankingView) RankingPresenter.this.getView()).onAttentionSuccess();
            }
        }));
    }

    public void getRankConfig(LoadingView loadingView, LinearLayout linearLayout) {
        if (loadingView != null) {
            loadingView.setVisibility(0);
            loadingView.showLoading();
        }
        addMapSubscription(this.mApiService.getIndexRankConfigService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<List<String>>() {
            public void onSuccess(List<String> list) {
                ((IRankingView) RankingPresenter.this.getView()).onRankConfigSuccess(list);
            }

            public void onError(int i, String str) {
                ((IRankingView) RankingPresenter.this.getView()).onRankConfigFail();
            }
        }));
    }
}

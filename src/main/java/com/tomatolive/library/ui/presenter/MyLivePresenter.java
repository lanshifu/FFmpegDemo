package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.MyLiveEntity;
import com.tomatolive.library.ui.view.iview.IMyLiveView;
import com.tomatolive.library.ui.view.widget.StateView;

public class MyLivePresenter extends a<IMyLiveView> {
    public MyLivePresenter(Context context, IMyLiveView iMyLiveView) {
        super(context, iMyLiveView);
    }

    public void initData(StateView stateView, boolean z) {
        addMapSubscription(this.mApiService.getMyLiveInitDataService(new RequestParams().getUserIdParams()), new HttpRxObserver(getContext(), new ResultCallBack<MyLiveEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(MyLiveEntity myLiveEntity) {
                ((IMyLiveView) MyLivePresenter.this.getView()).onDataSuccess(myLiveEntity);
            }
        }, stateView, z));
    }

    public void onAnchorAuth() {
        addMapSubscription(this.mApiService.getAnchorAuthService(new RequestParams().getUserIdParams()), new HttpRxObserver(getContext(), new ResultCallBack<AnchorEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(AnchorEntity anchorEntity) {
                if (anchorEntity != null) {
                    ((IMyLiveView) MyLivePresenter.this.getView()).onAnchorAuthSuccess(anchorEntity);
                }
            }
        }, true));
    }

    public void getUserGradeData() {
        addMapSubscription(this.mApiService.getUserInfoService(new RequestParams().getUserIdByIdParams()), new HttpRxObserver(getContext(), new ResultCallBack<AnchorEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(AnchorEntity anchorEntity) {
                if (anchorEntity != null) {
                    ((IMyLiveView) MyLivePresenter.this.getView()).onUserGradeSuccess(anchorEntity);
                }
            }
        }));
    }
}

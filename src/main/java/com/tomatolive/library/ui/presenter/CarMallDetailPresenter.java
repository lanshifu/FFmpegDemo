package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.UserEntity;
import com.tomatolive.library.ui.view.iview.ICarMallDetailView;

public class CarMallDetailPresenter extends a<ICarMallDetailView> {
    public CarMallDetailPresenter(Context context, ICarMallDetailView iCarMallDetailView) {
        super(context, iCarMallDetailView);
    }

    public void getUserOver() {
        addMapSubscription(this.mApiService.getUserOverService(new RequestParams().getUserOverParams()), new HttpRxObserver(getContext(), new ResultCallBack<UserEntity>() {
            public void onSuccess(UserEntity userEntity) {
                if (userEntity == null) {
                    ((ICarMallDetailView) CarMallDetailPresenter.this.getView()).onUserOverFail();
                } else {
                    ((ICarMallDetailView) CarMallDetailPresenter.this.getView()).onUserOverSuccess(userEntity.getTotResult());
                }
            }

            public void onError(int i, String str) {
                ((ICarMallDetailView) CarMallDetailPresenter.this.getView()).onUserOverFail();
            }
        }));
    }

    public void buyCar(String str, String str2, String str3) {
        addMapSubscription(this.mApiService.getBuyCarService(new RequestParams().getBuyCarParams(str, str2, str3)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                if (obj != null) {
                    ((ICarMallDetailView) CarMallDetailPresenter.this.getView()).onBuyCarSuccess();
                }
            }
        }, true));
    }
}

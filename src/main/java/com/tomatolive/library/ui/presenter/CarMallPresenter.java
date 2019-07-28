package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.CarEntity;
import com.tomatolive.library.model.CarHistoryRecordEntity;
import com.tomatolive.library.ui.view.iview.ICarMallView;
import com.tomatolive.library.ui.view.widget.StateView;
import java.util.List;

public class CarMallPresenter extends a<ICarMallView> {
    public CarMallPresenter(Context context, ICarMallView iCarMallView) {
        super(context, iCarMallView);
    }

    public void getCarList(StateView stateView, int i, boolean z, final boolean z2) {
        addMapSubscription(this.mApiService.getCarListService(new RequestParams().getScopeParams()), new HttpRxObserver(getContext(), new ResultCallBack<List<CarEntity>>() {
            public void onSuccess(List<CarEntity> list) {
                if (list != null) {
                    ((ICarMallView) CarMallPresenter.this.getView()).onDataListSuccess(list, true, z2);
                }
            }

            public void onError(int i, String str) {
                ((ICarMallView) CarMallPresenter.this.getView()).onDataListFail();
            }
        }, stateView, z));
    }

    public void buyCar(CarEntity carEntity, String str, String str2) {
        addMapSubscription(this.mApiService.getBuyCarService(new RequestParams().getBuyCarParams(carEntity.id, str, str2)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                if (obj != null) {
                    ((ICarMallView) CarMallPresenter.this.getView()).onBuyCarSuccess();
                }
            }
        }, true));
    }

    public void getCarPurchaseCarouselRecord() {
        addMapSubscription(this.mApiService.getCarHistoryRecordListService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<List<CarHistoryRecordEntity>>() {
            public void onSuccess(List<CarHistoryRecordEntity> list) {
                if (list != null) {
                    ((ICarMallView) CarMallPresenter.this.getView()).onGetCarPurchaseCarouselRecordSuccess(list);
                }
            }

            public void onError(int i, String str) {
                ((ICarMallView) CarMallPresenter.this.getView()).onGetCarPurchaseCarouselRecordFail();
            }
        }));
    }
}

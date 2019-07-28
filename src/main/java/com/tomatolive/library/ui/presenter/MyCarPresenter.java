package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.MyCarEntity;
import com.tomatolive.library.ui.view.iview.IMyCarView;
import com.tomatolive.library.ui.view.widget.StateView;
import java.util.List;

public class MyCarPresenter extends a<IMyCarView> {
    public MyCarPresenter(Context context, IMyCarView iMyCarView) {
        super(context, iMyCarView);
    }

    public void useCar(final MyCarEntity myCarEntity) {
        addMapSubscription(this.mApiService.getUseCarService(new RequestParams().getUseCarParams(myCarEntity.uniqueId, myCarEntity.isUsed)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onSuccess(Object obj) {
                if (obj != null) {
                    ((IMyCarView) MyCarPresenter.this.getView()).onUseCarSuccess(myCarEntity);
                }
            }

            public void onError(int i, String str) {
                ((IMyCarView) MyCarPresenter.this.getView()).onUseCarFail();
            }
        }));
    }

    public void getMyCar(StateView stateView, int i, boolean z, final boolean z2) {
        addMapSubscription(this.mApiService.getMyCarListService(new RequestParams().getUserIdParams()), new HttpRxObserver(getContext(), new ResultCallBack<List<MyCarEntity>>() {
            public void onSuccess(List<MyCarEntity> list) {
                if (list != null) {
                    ((IMyCarView) MyCarPresenter.this.getView()).onDataListSuccess(list, true, z2);
                }
            }

            public void onError(int i, String str) {
                ((IMyCarView) MyCarPresenter.this.getView()).onDataListFail();
            }
        }, stateView, z));
    }
}

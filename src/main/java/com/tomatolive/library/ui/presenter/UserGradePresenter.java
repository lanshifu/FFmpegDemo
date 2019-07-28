package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.iview.IUserGradeView;
import com.tomatolive.library.ui.view.widget.StateView;

public class UserGradePresenter extends a<IUserGradeView> {
    public UserGradePresenter(Context context, IUserGradeView iUserGradeView) {
        super(context, iUserGradeView);
    }

    public void getData(StateView stateView, boolean z) {
        addMapSubscription(this.mApiService.getUserInfoService(new RequestParams().getUserIdByIdParams()), new HttpRxObserver(getContext(), new ResultCallBack<AnchorEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(AnchorEntity anchorEntity) {
                if (anchorEntity != null) {
                    ((IUserGradeView) UserGradePresenter.this.getView()).onDataSuccess(anchorEntity);
                }
            }
        }, stateView, z));
    }
}

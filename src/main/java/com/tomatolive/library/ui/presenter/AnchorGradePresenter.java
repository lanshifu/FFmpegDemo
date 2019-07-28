package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.iview.IAnchorGradeView;
import com.tomatolive.library.ui.view.widget.StateView;

public class AnchorGradePresenter extends a<IAnchorGradeView> {
    public AnchorGradePresenter(Context context, IAnchorGradeView iAnchorGradeView) {
        super(context, iAnchorGradeView);
    }

    public void getData(StateView stateView, boolean z) {
        addMapSubscription(this.mApiService.getAnchorInfoService(new RequestParams().getPreStartLiveInfoParams()), new HttpRxObserver(getContext(), new ResultCallBack<AnchorEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(AnchorEntity anchorEntity) {
                if (anchorEntity != null) {
                    ((IAnchorGradeView) AnchorGradePresenter.this.getView()).onDataSuccess(anchorEntity);
                }
            }
        }, stateView, z));
    }
}

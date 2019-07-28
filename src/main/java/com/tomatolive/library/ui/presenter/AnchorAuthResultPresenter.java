package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.iview.IAnchorAuthResultView;

public class AnchorAuthResultPresenter extends a<IAnchorAuthResultView> {
    public AnchorAuthResultPresenter(Context context, IAnchorAuthResultView iAnchorAuthResultView) {
        super(context, iAnchorAuthResultView);
    }

    public void onAnchorAuth() {
        addMapSubscription(this.mApiService.getAnchorAuthService(new RequestParams().getUserIdParams()), new HttpRxObserver(getContext(), new ResultCallBack<AnchorEntity>() {
            public void onSuccess(AnchorEntity anchorEntity) {
                if (anchorEntity != null) {
                    ((IAnchorAuthResultView) AnchorAuthResultPresenter.this.getView()).onAnchorAuthSuccess(anchorEntity);
                }
            }

            public void onError(int i, String str) {
                ((IAnchorAuthResultView) AnchorAuthResultPresenter.this.getView()).onLiveListFail();
            }
        }));
    }
}

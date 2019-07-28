package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.LivePreNoticeEntity;
import com.tomatolive.library.ui.view.iview.ILivePreNoticeView;

public class LivePreNoticePresenter extends a<ILivePreNoticeView> {
    public LivePreNoticePresenter(Context context, ILivePreNoticeView iLivePreNoticeView) {
        super(context, iLivePreNoticeView);
    }

    public void getDataList() {
        addMapSubscription(this.mApiService.getLivePreNoticeService(new RequestParams().getUserIdParams()), new HttpRxObserver(getContext(), new ResultCallBack<LivePreNoticeEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(LivePreNoticeEntity livePreNoticeEntity) {
                if (livePreNoticeEntity != null) {
                    ((ILivePreNoticeView) LivePreNoticePresenter.this.getView()).onDataSuccess(livePreNoticeEntity);
                }
            }
        }));
    }

    public void onSaveContent(String str) {
        addMapSubscription(this.mApiService.getAddLivePreNoticeService(new RequestParams().getLivePreNoticeParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                ((ILivePreNoticeView) LivePreNoticePresenter.this.getView()).onSaveSuccess();
            }
        }, true));
    }
}

package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.view.iview.IWatchRecordView;
import com.tomatolive.library.ui.view.widget.StateView;

public class WatchRecordPresenter extends a<IWatchRecordView> {
    public WatchRecordPresenter(Context context, IWatchRecordView iWatchRecordView) {
        super(context, iWatchRecordView);
    }

    public void getDataList(StateView stateView, int i, boolean z, final boolean z2) {
        addMapSubscription(this.mApiService.getWatchHistoryListService(new RequestParams().getUserIdParams(i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<LiveEntity>>() {
            public void onSuccess(HttpResultPageModel<LiveEntity> httpResultPageModel) {
                if (httpResultPageModel != null) {
                    ((IWatchRecordView) WatchRecordPresenter.this.getView()).onDataListSuccess(httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z2);
                }
            }

            public void onError(int i, String str) {
                ((IWatchRecordView) WatchRecordPresenter.this.getView()).onDataListFail();
            }
        }, stateView, z));
    }

    public void onDelete(String str, final int i) {
        addMapSubscription(this.mApiService.getDeleteWatchHistoryService(new RequestParams().getDelWatchHistoryParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                ((IWatchRecordView) WatchRecordPresenter.this.getView()).onDeleteSuccess(i);
            }
        }, true));
    }

    public void onDeleteAll() {
        addMapSubscription(this.mApiService.getDeleteWatchHistoryAllService(new RequestParams().getUserIdParams()), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                ((IWatchRecordView) WatchRecordPresenter.this.getView()).onDeleteAllSuccess();
            }
        }, true));
    }
}

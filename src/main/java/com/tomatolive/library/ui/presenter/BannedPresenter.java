package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.BannedEntity;
import com.tomatolive.library.ui.view.iview.IBannedView;
import com.tomatolive.library.ui.view.widget.StateView;
import java.util.List;

public class BannedPresenter extends a<IBannedView> {
    public BannedPresenter(Context context, IBannedView iBannedView) {
        super(context, iBannedView);
    }

    public void getDataList(StateView stateView, boolean z, String str, int i, boolean z2, final boolean z3) {
        if (z) {
            getSearchUsersList(stateView, str, z2);
        } else {
            addMapSubscription(this.mApiService.getBannedListService(new RequestParams().getAnchorIdParams(i)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<BannedEntity>>() {
                public void onError(int i, String str) {
                }

                public void onSuccess(HttpResultPageModel<BannedEntity> httpResultPageModel) {
                    if (httpResultPageModel != null) {
                        ((IBannedView) BannedPresenter.this.getView()).onDataListSuccess(httpResultPageModel.totalRowsCount, httpResultPageModel.dataList, httpResultPageModel.isMorePage(), z3);
                    }
                }
            }, stateView, z2));
        }
    }

    public void getSearchUsersList(StateView stateView, String str, boolean z) {
        addMapSubscription(this.mApiService.getSearchUserListService(new RequestParams().getSearchUsersParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<List<BannedEntity>>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(List<BannedEntity> list) {
                ((IBannedView) BannedPresenter.this.getView()).onSearchDataListSuccess(list);
            }
        }, stateView, z));
    }

    public void bannedSetting(String str, int i, String str2, final int i2, final BannedEntity bannedEntity) {
        addMapSubscription(this.mApiService.getBannedSetService(new RequestParams().getBannedSettingParams(str, str2, i)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                ((IBannedView) BannedPresenter.this.getView()).onBannedSettingSuccess(i2, bannedEntity);
            }
        }, true));
    }
}

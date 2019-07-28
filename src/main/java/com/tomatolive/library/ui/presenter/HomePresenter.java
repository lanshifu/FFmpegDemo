package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.model.SysParamsInfoEntity;
import com.tomatolive.library.ui.view.iview.IHomeView;
import com.tomatolive.library.ui.view.widget.StateView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.z;
import java.util.List;

public class HomePresenter extends a<IHomeView> {
    public HomePresenter(Context context, IHomeView iHomeView) {
        super(context, iHomeView);
    }

    public void getTagList(StateView stateView, boolean z) {
        if (isApiService()) {
            addMapSubscription(this.mApiService.getLabelListService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<List<LabelEntity>>() {
                public void onSuccess(List<LabelEntity> list) {
                    ((IHomeView) HomePresenter.this.getView()).onTagListSuccess(list);
                }

                public void onError(int i, String str) {
                    ((IHomeView) HomePresenter.this.getView()).onTagListFail();
                }
            }, stateView, z));
            return;
        }
        if (stateView != null) {
            stateView.showRetry();
        }
    }

    public void getSysParamsInfo() {
        if (isApiService() && !b.k()) {
            addMapSubscription(this.mApiService.getSysParamsInfoService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<SysParamsInfoEntity>() {
                public void onSuccess(SysParamsInfoEntity sysParamsInfoEntity) {
                    int i = 60;
                    if (sysParamsInfoEntity == null) {
                        z.a().a(60);
                        return;
                    }
                    z a = z.a();
                    if (sysParamsInfoEntity.isEnableGrade120()) {
                        i = 120;
                    }
                    a.a(i);
                }

                public void onError(int i, String str) {
                    z.a().a(60);
                }
            }));
        }
    }
}

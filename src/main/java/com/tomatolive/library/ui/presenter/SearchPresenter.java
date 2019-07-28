package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.view.iview.ISearchView;
import com.tomatolive.library.ui.view.widget.StateView;
import java.util.List;

public class SearchPresenter extends a<ISearchView> {
    public SearchPresenter(Context context, ISearchView iSearchView) {
        super(context, iSearchView);
    }

    public void getLiveEnjoyList(StateView stateView) {
        addMapSubscription(this.mApiService.getEnjoyListService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<List<LiveEntity>>() {
            public void onSuccess(List<LiveEntity> list) {
                if (list != null) {
                    ((ISearchView) SearchPresenter.this.getView()).onLiveListSuccess(list);
                }
            }

            public void onError(int i, String str) {
                ((ISearchView) SearchPresenter.this.getView()).onResultError(i);
            }
        }, stateView, true));
    }

    public void getHotKeyList() {
        addMapSubscription(this.mApiService.getHotKeyListService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<List<LabelEntity>>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(List<LabelEntity> list) {
                if (list != null) {
                    ((ISearchView) SearchPresenter.this.getView()).onHotKeyListSuccess(list);
                }
            }
        }));
    }

    public void getAutoKeyList(final String str) {
        addMapSubscription(this.mApiService.getHotKeyListService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<List<LabelEntity>>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(List<LabelEntity> list) {
                if (list != null) {
                    ((ISearchView) SearchPresenter.this.getView()).onAutoKeyListSuccess(str, list);
                }
            }
        }));
    }
}

package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.CountryCodeEntity;
import com.tomatolive.library.model.UploadFileEntity;
import com.tomatolive.library.ui.view.iview.IAnchorAuthView;
import com.tomatolive.library.utils.b;
import java.io.File;
import java.util.List;

public class AnchorAuthPresenter extends a<IAnchorAuthView> {
    public AnchorAuthPresenter(Context context, IAnchorAuthView iAnchorAuthView) {
        super(context, iAnchorAuthView);
    }

    public void onAnchorAuth(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        addMapSubscription(this.mApiService.getSubmitAnchorAuthService(new RequestParams().getAnchorAuthParams(str, str2, str3, str4, str5, str6, str7)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onSuccess(Object obj) {
                ((IAnchorAuthView) AnchorAuthPresenter.this.getView()).onAuthSuccess();
            }

            public void onError(int i, String str) {
                ((IAnchorAuthView) AnchorAuthPresenter.this.getView()).onResultError(i);
            }
        }, true));
    }

    public void onSendPhoneCode(String str, String str2) {
        addMapSubscription(this.mApiService.getPhoneCodeService(new RequestParams().getSendPhoneCodeParams(str, str2)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                ((IAnchorAuthView) AnchorAuthPresenter.this.getView()).onSendPhoneCodeSuccess();
            }
        }, true));
    }

    public void onCountryCode() {
        addMapSubscription(this.mApiService.getCountryCodeService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<List<CountryCodeEntity>>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(List<CountryCodeEntity> list) {
                ((IAnchorAuthView) AnchorAuthPresenter.this.getView()).onCountryPhoneCodeSuccess(list);
            }
        }, true));
    }

    public void onUpload(String str, final boolean z) {
        addMapSubscription(this.mApiService.uploadFile(b.c(), b.a(new File(str))), new HttpRxObserver(getContext(), new ResultCallBack<UploadFileEntity>() {
            public void onSuccess(UploadFileEntity uploadFileEntity) {
                ((IAnchorAuthView) AnchorAuthPresenter.this.getView()).onUploadSuc(uploadFileEntity, z);
            }

            public void onError(int i, String str) {
                ((IAnchorAuthView) AnchorAuthPresenter.this.getView()).onUploadFail(z);
            }
        }));
    }
}

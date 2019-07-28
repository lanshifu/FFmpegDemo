package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.UploadFileEntity;
import com.tomatolive.library.ui.view.iview.ICoverUploadView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.z;
import java.io.File;

public class CoverUploadPresenter extends a<ICoverUploadView> {
    public CoverUploadPresenter(Context context, ICoverUploadView iCoverUploadView) {
        super(context, iCoverUploadView);
    }

    public void uploadCover(String str, String str2) {
        addMapSubscription(this.mApiService.getUploadLiveCoverService(new RequestParams().getUploadLiveCoverParams(z.a().c(), str, str2)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onSuccess(Object obj) {
                ((ICoverUploadView) CoverUploadPresenter.this.getView()).onUploadCoverSuccess();
            }

            public void onError(int i, String str) {
                ((ICoverUploadView) CoverUploadPresenter.this.getView()).onUploadCoverFail();
            }
        }));
    }

    public void onUpload(String str, final String str2) {
        addMapSubscription(this.mApiService.uploadFile(b.c(), b.a(new File(str))), new HttpRxObserver(getContext(), new ResultCallBack<UploadFileEntity>() {
            public void onSuccess(UploadFileEntity uploadFileEntity) {
                ((ICoverUploadView) CoverUploadPresenter.this.getView()).onUploadSuc(uploadFileEntity, str2);
            }

            public void onError(int i, String str) {
                ((ICoverUploadView) CoverUploadPresenter.this.getView()).onUploadFail();
            }
        }));
    }
}

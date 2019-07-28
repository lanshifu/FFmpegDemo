package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.model.UploadFileEntity;

public interface ICoverUploadView {
    void onUploadCoverFail();

    void onUploadCoverSuccess();

    void onUploadFail();

    void onUploadSuc(UploadFileEntity uploadFileEntity, String str);
}

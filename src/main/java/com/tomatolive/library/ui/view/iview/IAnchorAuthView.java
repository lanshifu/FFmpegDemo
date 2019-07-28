package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.CountryCodeEntity;
import com.tomatolive.library.model.UploadFileEntity;
import java.util.List;

public interface IAnchorAuthView extends b {
    void onAuthSuccess();

    void onCountryPhoneCodeSuccess(List<CountryCodeEntity> list);

    void onSendPhoneCodeSuccess();

    void onUploadFail(boolean z);

    void onUploadSuc(UploadFileEntity uploadFileEntity, boolean z);
}

package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;

public interface ICarMallDetailView extends b {
    void onBuyCarSuccess();

    void onUserOverFail();

    void onUserOverSuccess(String str);
}

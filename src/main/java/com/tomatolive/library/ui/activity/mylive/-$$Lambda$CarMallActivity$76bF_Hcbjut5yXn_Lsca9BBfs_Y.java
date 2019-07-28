package com.tomatolive.library.ui.activity.mylive;

import com.tomatolive.library.model.CarEntity;
import com.tomatolive.library.ui.presenter.CarMallPresenter;
import com.tomatolive.library.ui.view.dialog.CarBuyDialog.OnBuyListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$CarMallActivity$76bF_Hcbjut5yXn_Lsca9BBfs_Y implements OnBuyListener {
    private final /* synthetic */ CarMallActivity f$0;
    private final /* synthetic */ CarEntity f$1;

    public /* synthetic */ -$$Lambda$CarMallActivity$76bF_Hcbjut5yXn_Lsca9BBfs_Y(CarMallActivity carMallActivity, CarEntity carEntity) {
        this.f$0 = carMallActivity;
        this.f$1 = carEntity;
    }

    public final void onBuy(String str, String str2) {
        ((CarMallPresenter) this.f$0.mPresenter).buyCar(this.f$1, str, str2);
    }
}

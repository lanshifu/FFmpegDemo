package com.tomatolive.library.ui.activity.home;

import com.tomatolive.library.model.CountryCodeEntity;
import com.tomatolive.library.ui.view.dialog.BottomDialogUtils.CountryCodeListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$AnchorAuthActivity$AJlUFELmCTMs5MFSV0onCZ_qTEE implements CountryCodeListener {
    private final /* synthetic */ AnchorAuthActivity f$0;

    public /* synthetic */ -$$Lambda$AnchorAuthActivity$AJlUFELmCTMs5MFSV0onCZ_qTEE(AnchorAuthActivity anchorAuthActivity) {
        this.f$0 = anchorAuthActivity;
    }

    public final void onCountryCodeListener(CountryCodeEntity countryCodeEntity, int i) {
        AnchorAuthActivity.lambda$showPhoneCountryCodeDialog$5(this.f$0, countryCodeEntity, i);
    }
}

package com.tomatolive.library.ui.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$LiveHomeFragment$2$qMSExcE1EyVkfT01qNR8cCpRhB8 implements OnClickListener {
    private final /* synthetic */ ViewPager f$0;
    private final /* synthetic */ int f$1;

    public /* synthetic */ -$$Lambda$LiveHomeFragment$2$qMSExcE1EyVkfT01qNR8cCpRhB8(ViewPager viewPager, int i) {
        this.f$0 = viewPager;
        this.f$1 = i;
    }

    public final void onClick(View view) {
        this.f$0.setCurrentItem(this.f$1, false);
    }
}

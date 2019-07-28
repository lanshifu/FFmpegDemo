package com.tomatolive.library.ui.activity.home;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$SearchActivity$3$U31T4EzJCsGbOtD8kQg9H26dfKI implements OnClickListener {
    private final /* synthetic */ ViewPager f$0;
    private final /* synthetic */ int f$1;

    public /* synthetic */ -$$Lambda$SearchActivity$3$U31T4EzJCsGbOtD8kQg9H26dfKI(ViewPager viewPager, int i) {
        this.f$0 = viewPager;
        this.f$1 = i;
    }

    public final void onClick(View view) {
        this.f$0.setCurrentItem(this.f$1);
    }
}

package com.tomatolive.library.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.ui.activity.home.SearchActivity;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$LiveHomeFragment$jPSy5Hgh6AnOsq7nmPOCtiTvOlA implements OnClickListener {
    private final /* synthetic */ LiveHomeFragment f$0;

    public /* synthetic */ -$$Lambda$LiveHomeFragment$jPSy5Hgh6AnOsq7nmPOCtiTvOlA(LiveHomeFragment liveHomeFragment) {
        this.f$0 = liveHomeFragment;
    }

    public final void onClick(View view) {
        this.f$0.startActivity(SearchActivity.class);
    }
}

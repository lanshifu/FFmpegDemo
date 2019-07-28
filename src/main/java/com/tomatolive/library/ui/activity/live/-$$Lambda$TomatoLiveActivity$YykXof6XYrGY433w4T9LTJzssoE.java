package com.tomatolive.library.ui.activity.live;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$YykXof6XYrGY433w4T9LTJzssoE implements OnTouchListener {
    private final /* synthetic */ TomatoLiveActivity f$0;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$YykXof6XYrGY433w4T9LTJzssoE(TomatoLiveActivity tomatoLiveActivity) {
        this.f$0 = tomatoLiveActivity;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f$0.swipeAnimationController.a(motionEvent);
    }
}

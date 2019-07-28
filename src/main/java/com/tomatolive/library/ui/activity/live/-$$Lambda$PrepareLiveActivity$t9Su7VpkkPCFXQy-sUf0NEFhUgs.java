package com.tomatolive.library.ui.activity.live;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$t9Su7VpkkPCFXQy-sUf0NEFhUgs implements OnTouchListener {
    private final /* synthetic */ PrepareLiveActivity f$0;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$t9Su7VpkkPCFXQy-sUf0NEFhUgs(PrepareLiveActivity prepareLiveActivity) {
        this.f$0 = prepareLiveActivity;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f$0.swipeAnimationController.a(motionEvent);
    }
}

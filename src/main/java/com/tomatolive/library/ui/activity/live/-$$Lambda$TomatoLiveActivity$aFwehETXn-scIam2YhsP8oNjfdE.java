package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.model.TaskBoxEntity;
import com.tomatolive.library.ui.view.dialog.TaskBottomDialog.TaskClickListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$TomatoLiveActivity$aFwehETXn-scIam2YhsP8oNjfdE implements TaskClickListener {
    private final /* synthetic */ TomatoLiveActivity f$0;

    public /* synthetic */ -$$Lambda$TomatoLiveActivity$aFwehETXn-scIam2YhsP8oNjfdE(TomatoLiveActivity tomatoLiveActivity) {
        this.f$0 = tomatoLiveActivity;
    }

    public final void onTaskCallback(TaskBoxEntity taskBoxEntity) {
        TomatoLiveActivity.lambda$initTaskDialog$13(this.f$0, taskBoxEntity);
    }
}

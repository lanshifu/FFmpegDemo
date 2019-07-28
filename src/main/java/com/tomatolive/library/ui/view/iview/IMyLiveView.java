package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.MyLiveEntity;

public interface IMyLiveView extends b {
    void onAnchorAuthSuccess(AnchorEntity anchorEntity);

    void onDataSuccess(MyLiveEntity myLiveEntity);

    void onUserGradeSuccess(AnchorEntity anchorEntity);
}

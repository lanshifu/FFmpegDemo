package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.AnchorEntity;

public interface IAnchorAuthResultView extends b {
    void onAnchorAuthSuccess(AnchorEntity anchorEntity);

    void onLiveListFail();
}

package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.LiveEntity;
import java.util.List;

public interface ISearchAllView extends b {
    void onAnchorListFail();

    void onAnchorListSuccess(List<AnchorEntity> list);

    void onLiveListSuccess(List<LiveEntity> list, boolean z, boolean z2);
}

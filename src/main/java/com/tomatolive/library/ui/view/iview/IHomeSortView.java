package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.LiveEntity;
import java.util.List;

public interface IHomeSortView extends b {
    void onLiveListFail();

    void onLiveListSuccess(List<LiveEntity> list, boolean z, boolean z2);
}

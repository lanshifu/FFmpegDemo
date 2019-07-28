package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.LiveEntity;
import java.util.List;

public interface ISearchLiveView extends b {
    void onDataListSuccess(List<LiveEntity> list, boolean z, boolean z2);
}

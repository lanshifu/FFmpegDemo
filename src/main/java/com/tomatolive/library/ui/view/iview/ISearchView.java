package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.model.LiveEntity;
import java.util.List;

public interface ISearchView extends b {
    void onAutoKeyListSuccess(String str, List<LabelEntity> list);

    void onHotKeyListSuccess(List<LabelEntity> list);

    void onLiveListSuccess(List<LiveEntity> list);
}

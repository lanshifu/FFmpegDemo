package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.LabelEntity;
import java.util.List;

public interface IHomeView extends b {
    void onTagListFail();

    void onTagListSuccess(List<LabelEntity> list);
}

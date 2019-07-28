package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.AnchorEntity;
import java.util.List;

public interface IDedicateTopView extends b {
    void onDataListSuccess(List<AnchorEntity> list, boolean z, boolean z2);
}

package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.MyCarEntity;
import java.util.List;

public interface IMyCarView extends b {
    void onDataListFail();

    void onDataListSuccess(List<MyCarEntity> list, boolean z, boolean z2);

    void onUseCarFail();

    void onUseCarSuccess(MyCarEntity myCarEntity);
}

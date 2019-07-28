package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.LiveEntity;
import java.util.List;

public interface IWatchRecordView extends b {
    void onDataListFail();

    void onDataListSuccess(List<LiveEntity> list, boolean z, boolean z2);

    void onDeleteAllSuccess();

    void onDeleteSuccess(int i);
}

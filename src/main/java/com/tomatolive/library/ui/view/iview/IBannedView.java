package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.BannedEntity;
import java.util.List;

public interface IBannedView extends b {
    void onBannedSettingSuccess(int i, BannedEntity bannedEntity);

    void onDataListSuccess(int i, List<BannedEntity> list, boolean z, boolean z2);

    void onSearchDataListSuccess(List<BannedEntity> list);
}

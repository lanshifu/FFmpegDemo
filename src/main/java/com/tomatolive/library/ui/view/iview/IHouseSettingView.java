package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.BannedEntity;
import java.util.List;

public interface IHouseSettingView extends b {
    void onDataListSuccess(int i, List<BannedEntity> list, boolean z, boolean z2);

    void onHouseSettingSuccess(int i, BannedEntity bannedEntity);

    void onSearchDataListSuccess(List<BannedEntity> list);
}

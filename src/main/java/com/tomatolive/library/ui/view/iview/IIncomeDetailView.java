package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.IncomeEntity;
import java.util.List;

public interface IIncomeDetailView extends b {
    void onDataListSuccess(List<IncomeEntity> list, boolean z, boolean z2, String str);
}

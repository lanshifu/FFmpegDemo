package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.IncomeEntity;
import com.tomatolive.library.model.IncomeMenuEntity;
import java.util.List;

public interface IIncomeView extends b {
    void onDataListSuccess(List<IncomeEntity> list, IncomeMenuEntity incomeMenuEntity);
}

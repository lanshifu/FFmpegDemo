package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.ExpenseMenuEntity;
import com.tomatolive.library.model.IncomeEntity;
import java.util.List;

public interface IConsumeView extends b {
    void onDataListSuccess(List<IncomeEntity> list, ExpenseMenuEntity expenseMenuEntity);
}

package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.ExpenseMenuEntity;
import com.tomatolive.library.model.IncomeEntity;
import com.tomatolive.library.ui.view.iview.IConsumeView;
import com.tomatolive.library.ui.view.widget.StateView;
import java.util.ArrayList;
import java.util.List;

public class ConsumePresenter extends a<IConsumeView> {
    public ConsumePresenter(Context context, IConsumeView iConsumeView) {
        super(context, iConsumeView);
    }

    public void getDataList(StateView stateView, boolean z, String str) {
        addMapSubscription(this.mApiService.getExpenseStatisticsService(new RequestParams().getIncomeConsumeParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<ExpenseMenuEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(ExpenseMenuEntity expenseMenuEntity) {
                if (expenseMenuEntity != null) {
                    ((IConsumeView) ConsumePresenter.this.getView()).onDataListSuccess(ConsumePresenter.this.formatList(expenseMenuEntity), expenseMenuEntity);
                }
            }
        }, stateView, z));
    }

    private List<IncomeEntity> formatList(ExpenseMenuEntity expenseMenuEntity) {
        ArrayList arrayList = new ArrayList();
        IncomeEntity incomeEntity = new IncomeEntity();
        incomeEntity.incomeType = String.valueOf(1);
        incomeEntity.incomeCount = expenseMenuEntity.getGiftExpense();
        arrayList.add(incomeEntity);
        incomeEntity = new IncomeEntity();
        incomeEntity.incomeType = String.valueOf(2);
        incomeEntity.incomeCount = expenseMenuEntity.getGuardExpense();
        arrayList.add(incomeEntity);
        incomeEntity = new IncomeEntity();
        incomeEntity.incomeType = String.valueOf(3);
        incomeEntity.incomeCount = expenseMenuEntity.getCarExpense();
        arrayList.add(incomeEntity);
        incomeEntity = new IncomeEntity();
        incomeEntity.incomeType = String.valueOf(4);
        incomeEntity.incomeCount = expenseMenuEntity.getPropsExpense();
        arrayList.add(incomeEntity);
        return arrayList;
    }
}

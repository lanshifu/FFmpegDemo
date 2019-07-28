package com.tomatolive.library.ui.presenter;

import android.content.Context;
import android.util.Log;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.IncomeEntity;
import com.tomatolive.library.model.IncomeMenuEntity;
import com.tomatolive.library.ui.view.iview.IIncomeView;
import com.tomatolive.library.ui.view.widget.StateView;
import java.util.ArrayList;
import java.util.List;

public class IncomePresenter extends a<IIncomeView> {
    public IncomePresenter(Context context, IIncomeView iIncomeView) {
        super(context, iIncomeView);
    }

    public void getDataList(StateView stateView, boolean z, String str) {
        addMapSubscription(this.mApiService.getIncomeStatisticsService(new RequestParams().getIncomeConsumeParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<IncomeMenuEntity>() {
            public void onSuccess(IncomeMenuEntity incomeMenuEntity) {
                if (incomeMenuEntity != null) {
                    ((IIncomeView) IncomePresenter.this.getView()).onDataListSuccess(IncomePresenter.this.formatList(incomeMenuEntity), incomeMenuEntity);
                }
            }

            public void onError(int i, String str) {
                Log.i("", "");
            }
        }, stateView, z));
    }

    private List<IncomeEntity> formatList(IncomeMenuEntity incomeMenuEntity) {
        ArrayList arrayList = new ArrayList();
        IncomeEntity incomeEntity = new IncomeEntity();
        incomeEntity.incomeType = String.valueOf(1);
        incomeEntity.incomeCount = incomeMenuEntity.getGiftIncome();
        arrayList.add(incomeEntity);
        incomeEntity = new IncomeEntity();
        incomeEntity.incomeType = String.valueOf(2);
        incomeEntity.incomeCount = incomeMenuEntity.getGuardIncome();
        arrayList.add(incomeEntity);
        incomeEntity = new IncomeEntity();
        incomeEntity.incomeType = String.valueOf(4);
        incomeEntity.incomeCount = incomeMenuEntity.getPropsIncome();
        arrayList.add(incomeEntity);
        return arrayList;
    }
}

package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.CarEntity;
import com.tomatolive.library.model.CarHistoryRecordEntity;
import java.util.List;

public interface ICarMallView extends b {
    void onBuyCarSuccess();

    void onDataListFail();

    void onDataListSuccess(List<CarEntity> list, boolean z, boolean z2);

    void onGetCarPurchaseCarouselRecordFail();

    void onGetCarPurchaseCarouselRecordSuccess(List<CarHistoryRecordEntity> list);
}

package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.download.CarDownLoadManager;
import com.tomatolive.library.download.GiftDownLoadManager;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.CarDownloadEntity;
import com.tomatolive.library.model.ExpenseCarDetailEntity;
import com.tomatolive.library.model.GiftIncomeExpenseDetail;
import com.tomatolive.library.model.GuardIncomeExpenseDetail;
import com.tomatolive.library.model.IncomeEntity;
import com.tomatolive.library.model.PropsIncomeExpenseDetail;
import com.tomatolive.library.ui.view.iview.IIncomeDetailView;
import com.tomatolive.library.ui.view.widget.StateView;
import java.util.ArrayList;
import java.util.List;

public class IncomeDetailPresenter extends a<IIncomeDetailView> {
    public IncomeDetailPresenter(Context context, IIncomeDetailView iIncomeDetailView) {
        super(context, iIncomeDetailView);
    }

    public void getPropsIncomeDataList(StateView stateView, int i, boolean z, final boolean z2, String str, boolean z3) {
        addMapSubscription(this.mApiService.getIncomePropsListService(new RequestParams().getIncomeConsumeDetailParams(i, str, z3)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<PropsIncomeExpenseDetail>>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(HttpResultPageModel<PropsIncomeExpenseDetail> httpResultPageModel) {
                if (httpResultPageModel != null) {
                    ((IIncomeDetailView) IncomeDetailPresenter.this.getView()).onDataListSuccess(IncomeDetailPresenter.this.formatPropsDetailList(httpResultPageModel.dataList, false), httpResultPageModel.isMorePage(), z2, httpResultPageModel.totalGold);
                }
            }
        }, stateView, z));
    }

    public void getPropsExpenseDataList(StateView stateView, int i, boolean z, final boolean z2, String str, boolean z3) {
        addMapSubscription(this.mApiService.getExpensePropsListService(new RequestParams().getIncomeConsumeDetailParams(i, str, z3)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<PropsIncomeExpenseDetail>>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(HttpResultPageModel<PropsIncomeExpenseDetail> httpResultPageModel) {
                if (httpResultPageModel != null) {
                    ((IIncomeDetailView) IncomeDetailPresenter.this.getView()).onDataListSuccess(IncomeDetailPresenter.this.formatPropsDetailList(httpResultPageModel.dataList, true), httpResultPageModel.isMorePage(), z2, httpResultPageModel.totalGold);
                }
            }
        }, stateView, z));
    }

    public void getIncomeDataList(StateView stateView, int i, boolean z, final boolean z2, int i2, String str) {
        switch (i2) {
            case 1:
                addMapSubscription(this.mApiService.getIncomeGiftListService(new RequestParams().getIncomeConsumeDetailParams(i, str)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<GiftIncomeExpenseDetail>>() {
                    public void onError(int i, String str) {
                    }

                    public void onSuccess(HttpResultPageModel<GiftIncomeExpenseDetail> httpResultPageModel) {
                        if (httpResultPageModel != null) {
                            ((IIncomeDetailView) IncomeDetailPresenter.this.getView()).onDataListSuccess(IncomeDetailPresenter.this.formatGiftDetailList(httpResultPageModel.dataList, 1, false), httpResultPageModel.isMorePage(), z2, httpResultPageModel.totalGold);
                        }
                    }
                }, stateView, z));
                return;
            case 2:
                addMapSubscription(this.mApiService.getIncomeGuardListService(new RequestParams().getIncomeConsumeDetailParams(i, str)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<GuardIncomeExpenseDetail>>() {
                    public void onError(int i, String str) {
                    }

                    public void onSuccess(HttpResultPageModel<GuardIncomeExpenseDetail> httpResultPageModel) {
                        if (httpResultPageModel != null) {
                            ((IIncomeDetailView) IncomeDetailPresenter.this.getView()).onDataListSuccess(IncomeDetailPresenter.this.formateGuardDetailList(httpResultPageModel.dataList, false), httpResultPageModel.isMorePage(), z2, httpResultPageModel.totalGold);
                        }
                    }
                }, stateView, z));
                return;
            default:
                return;
        }
    }

    public void getExpenseDataList(StateView stateView, int i, boolean z, final boolean z2, int i2, String str) {
        switch (i2) {
            case 1:
                addMapSubscription(this.mApiService.getExpenseGiftListService(new RequestParams().getIncomeConsumeDetailParams(i, str)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<GiftIncomeExpenseDetail>>() {
                    public void onError(int i, String str) {
                    }

                    public void onSuccess(HttpResultPageModel<GiftIncomeExpenseDetail> httpResultPageModel) {
                        if (httpResultPageModel != null) {
                            ((IIncomeDetailView) IncomeDetailPresenter.this.getView()).onDataListSuccess(IncomeDetailPresenter.this.formatGiftDetailList(httpResultPageModel.dataList, 1, true), httpResultPageModel.isMorePage(), z2, httpResultPageModel.totalGold);
                        }
                    }
                }, stateView, z));
                return;
            case 2:
                addMapSubscription(this.mApiService.getExpenseGuardListService(new RequestParams().getIncomeConsumeDetailParams(i, str)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<GuardIncomeExpenseDetail>>() {
                    public void onError(int i, String str) {
                    }

                    public void onSuccess(HttpResultPageModel<GuardIncomeExpenseDetail> httpResultPageModel) {
                        if (httpResultPageModel != null) {
                            ((IIncomeDetailView) IncomeDetailPresenter.this.getView()).onDataListSuccess(IncomeDetailPresenter.this.formateGuardDetailList(httpResultPageModel.dataList, true), httpResultPageModel.isMorePage(), z2, httpResultPageModel.totalGold);
                        }
                    }
                }, stateView, z));
                return;
            case 3:
                addMapSubscription(this.mApiService.getExpenseCarListService(new RequestParams().getIncomeConsumeDetailParams(i, str)), new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<ExpenseCarDetailEntity>>() {
                    public void onError(int i, String str) {
                    }

                    public void onSuccess(HttpResultPageModel<ExpenseCarDetailEntity> httpResultPageModel) {
                        if (httpResultPageModel != null) {
                            ((IIncomeDetailView) IncomeDetailPresenter.this.getView()).onDataListSuccess(IncomeDetailPresenter.this.getExpenseCarDetailList(httpResultPageModel.dataList, 3), httpResultPageModel.isMorePage(), z2, httpResultPageModel.totalGold);
                        }
                    }
                }, stateView, z));
                return;
            default:
                return;
        }
    }

    private List<IncomeEntity> formateGuardDetailList(List<GuardIncomeExpenseDetail> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (GuardIncomeExpenseDetail guardIncomeExpenseDetail : list) {
            IncomeEntity incomeEntity = new IncomeEntity();
            if (z) {
                incomeEntity.nickName = guardIncomeExpenseDetail.getAnchorName();
                incomeEntity.incomeCount = guardIncomeExpenseDetail.getGold();
            } else {
                incomeEntity.nickName = guardIncomeExpenseDetail.getUserName();
                incomeEntity.incomeCount = guardIncomeExpenseDetail.getAnchorIncomeGold();
            }
            incomeEntity.guardType = guardIncomeExpenseDetail.getGuardType();
            incomeEntity.giftName = guardIncomeExpenseDetail.getGuardName();
            incomeEntity.incomeTime = guardIncomeExpenseDetail.getCreateTime();
            arrayList.add(incomeEntity);
        }
        return arrayList;
    }

    private List<IncomeEntity> formatGiftDetailList(List<GiftIncomeExpenseDetail> list, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (GiftIncomeExpenseDetail giftIncomeExpenseDetail : list) {
            IncomeEntity incomeEntity = new IncomeEntity();
            incomeEntity.nickName = giftIncomeExpenseDetail.getAnchorName();
            incomeEntity.giftImg = getLocaltIcon(giftIncomeExpenseDetail.getGiftId(), i);
            incomeEntity.giftName = giftIncomeExpenseDetail.getGiftName();
            incomeEntity.incomeTime = giftIncomeExpenseDetail.getCreateTime();
            if (z) {
                incomeEntity.nickName = giftIncomeExpenseDetail.getAnchorName();
                incomeEntity.incomeCount = giftIncomeExpenseDetail.getGold();
            } else {
                incomeEntity.nickName = giftIncomeExpenseDetail.getUserName();
                incomeEntity.incomeCount = giftIncomeExpenseDetail.getAnchorIncomeGold();
            }
            arrayList.add(incomeEntity);
        }
        return arrayList;
    }

    private List<IncomeEntity> formatPropsDetailList(List<PropsIncomeExpenseDetail> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (PropsIncomeExpenseDetail propsIncomeExpenseDetail : list) {
            IncomeEntity incomeEntity = new IncomeEntity();
            incomeEntity.propName = propsIncomeExpenseDetail.propName;
            incomeEntity.nickName = propsIncomeExpenseDetail.userName;
            incomeEntity.incomeTime = propsIncomeExpenseDetail.createTime;
            incomeEntity.incomeCount = propsIncomeExpenseDetail.count;
            arrayList.add(incomeEntity);
        }
        return arrayList;
    }

    private List<IncomeEntity> getExpenseCarDetailList(List<ExpenseCarDetailEntity> list, int i) {
        ArrayList arrayList = new ArrayList();
        for (ExpenseCarDetailEntity expenseCarDetailEntity : list) {
            IncomeEntity incomeEntity = new IncomeEntity();
            incomeEntity.carName = expenseCarDetailEntity.getCarName();
            incomeEntity.carImg = getLocaltIcon(expenseCarDetailEntity.getCarId(), i);
            incomeEntity.carTimes = expenseCarDetailEntity.getDuringDate();
            incomeEntity.incomeTime = expenseCarDetailEntity.getCreateTime();
            incomeEntity.incomeCount = expenseCarDetailEntity.getGold();
            arrayList.add(incomeEntity);
        }
        return arrayList;
    }

    private String getLocaltIcon(String str, int i) {
        String str2 = "";
        if (i == 1) {
            return GiftDownLoadManager.getInstance().getGiftItemImgUrl(str);
        }
        if (i != 3) {
            return str2;
        }
        CarDownloadEntity carItemEntity = CarDownLoadManager.getInstance().getCarItemEntity(str);
        return carItemEntity != null ? carItemEntity.imgUrl : str2;
    }
}

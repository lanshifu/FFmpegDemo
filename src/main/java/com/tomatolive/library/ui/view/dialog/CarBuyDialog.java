package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.d;
import com.opensource.svgaplayer.f;
import com.tomatolive.library.R;
import com.tomatolive.library.a;
import com.tomatolive.library.download.CarDownLoadManager;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.http.function.HttpResultFunction;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.model.CarDownloadEntity;
import com.tomatolive.library.model.CarEntity;
import com.tomatolive.library.model.UserEntity;
import com.tomatolive.library.utils.p;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.disposables.b;
import io.reactivex.k;
import io.reactivex.r;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CarBuyDialog extends BaseDialogFragment {
    private static final String ITEM = "ITEM";
    private CarEntity carItemEntity;
    private SVGAImageView ivAnim;
    private LinearLayout llPrice30Bg;
    private LinearLayout llPrice7Bg;
    private OnBuyListener onBuyListener;
    private d svgaParser;
    private TextView tvCurrentGold;
    private TextView tvDay30;
    private TextView tvDay7;
    private TextView tvPrice30;
    private TextView tvPrice7;
    private String userOver = "0";

    public interface OnBuyListener {
        void onBuy(String str, String str2);
    }

    @SuppressLint({"ValidFragment"})
    private CarBuyDialog() {
    }

    public static CarBuyDialog newInstance(CarEntity carEntity, OnBuyListener onBuyListener) {
        Bundle bundle = new Bundle();
        CarBuyDialog carBuyDialog = new CarBuyDialog();
        carBuyDialog.setArguments(bundle);
        bundle.putSerializable(ITEM, carEntity);
        carBuyDialog.setBuyListener(onBuyListener);
        return carBuyDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_car_buy;
    }

    public void initView(View view) {
        if (getArguments() != null) {
            this.carItemEntity = (CarEntity) getArguments().getSerializable(ITEM);
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_name);
        this.tvPrice7 = (TextView) view.findViewById(R.id.tv_price_7);
        this.tvDay7 = (TextView) view.findViewById(R.id.tv_time_7);
        this.tvPrice30 = (TextView) view.findViewById(R.id.tv_price_30);
        this.tvDay30 = (TextView) view.findViewById(R.id.tv_time_30);
        this.tvCurrentGold = (TextView) view.findViewById(R.id.tv_buy_gold);
        this.llPrice7Bg = (LinearLayout) view.findViewById(R.id.ll_price_7_bg);
        this.llPrice30Bg = (LinearLayout) view.findViewById(R.id.ll_price_30_bg);
        this.ivAnim = (SVGAImageView) view.findViewById(R.id.iv_anim);
        getUserOver();
        if (this.carItemEntity != null) {
            textView.setText(this.carItemEntity.name);
            this.tvPrice7.setText(Html.fromHtml(this.mContext.getString(R.string.fq_car_buy_gold, new Object[]{formatGold(this.carItemEntity.weekGold)})));
            this.tvPrice30.setText(Html.fromHtml(this.mContext.getString(R.string.fq_car_buy_gold, new Object[]{formatGold(this.carItemEntity.monthGold)})));
            this.tvDay7.setText(this.mContext.getString(R.string.fq_count_day, new Object[]{"7"}));
            this.tvDay30.setText(this.mContext.getString(R.string.fq_count_day, new Object[]{"30"}));
            initPriceSelectedView(true);
            startAnim();
        }
    }

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
        super.initListener(view);
        this.llPrice7Bg.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CarBuyDialog.this.initPriceSelectedView(true);
            }
        });
        this.llPrice30Bg.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CarBuyDialog.this.initPriceSelectedView(false);
            }
        });
        this.tvCurrentGold.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CarBuyDialog.this.getUserOver();
            }
        });
        view.findViewById(R.id.tv_now_buy).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CarBuyDialog.this.onBuyListener != null && CarBuyDialog.this.carItemEntity != null && CarBuyDialog.this.carItemEntity.isPublicCar()) {
                    if (p.b(CarBuyDialog.this.userOver) == 0) {
                        CarBuyDialog.this.goToRecharge();
                        return;
                    }
                    CarBuyDialog.this.dismiss();
                    String str = "7";
                    String str2 = "0";
                    if (CarBuyDialog.this.llPrice7Bg.isSelected() && !CarBuyDialog.this.llPrice30Bg.isSelected()) {
                        str2 = CarBuyDialog.this.carItemEntity.weekGold;
                    }
                    if (!CarBuyDialog.this.llPrice7Bg.isSelected() && CarBuyDialog.this.llPrice30Bg.isSelected()) {
                        str = "30";
                        str2 = CarBuyDialog.this.carItemEntity.monthGold;
                    }
                    CarBuyDialog.this.onBuyListener.onBuy(str, str2);
                }
            }
        });
    }

    private void initPriceSelectedView(boolean z) {
        this.llPrice7Bg.setSelected(z);
        this.tvPrice7.setSelected(z);
        this.tvDay7.setSelected(z);
        this.llPrice30Bg.setSelected(z ^ 1);
        this.tvPrice30.setSelected(z ^ 1);
        this.tvDay30.setSelected(z ^ 1);
    }

    private void startAnim() {
        try {
            k.just(Boolean.valueOf(true)).map(new -$$Lambda$CarBuyDialog$Afz9Dmihit7sRea7lb8_LmJ-pis(this)).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<InputStream>() {
                public void onComplete() {
                }

                public void onSubscribe(b bVar) {
                }

                public void onNext(InputStream inputStream) {
                    if (inputStream == null) {
                        CarBuyDialog.this.loadNetAnimation();
                        return;
                    }
                    if (CarBuyDialog.this.svgaParser == null) {
                        CarBuyDialog.this.svgaParser = new d(CarBuyDialog.this.mContext);
                    }
                    CarBuyDialog.this.svgaParser.a(inputStream, CarBuyDialog.this.carItemEntity.id, new d.b() {
                        public void onError() {
                        }

                        public void onComplete(f fVar) {
                            CarBuyDialog.this.ivAnim.setVisibility(0);
                            CarBuyDialog.this.ivAnim.setVideoItem(fVar);
                            CarBuyDialog.this.ivAnim.b();
                        }
                    }, true);
                }

                public void onError(Throwable th) {
                    CarBuyDialog.this.loadNetAnimation();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ InputStream lambda$startAnim$0(CarBuyDialog carBuyDialog, Boolean bool) throws Exception {
        return carBuyDialog.carItemEntity == null ? null : com.tomatolive.library.utils.b.r(carBuyDialog.carItemEntity.id);
    }

    private void loadNetAnimation() {
        CarDownloadEntity formatCarDownloadEntity = formatCarDownloadEntity();
        if (formatCarDownloadEntity != null) {
            CarDownLoadManager.getInstance().updateAnimOnlineSingleRes(formatCarDownloadEntity);
            if (this.svgaParser == null) {
                this.svgaParser = new d(this.mContext);
            }
            try {
                this.svgaParser.a(new URL(formatCarDownloadEntity.getOnlineUrl()), new d.b() {
                    public void onError() {
                    }

                    public void onComplete(f fVar) {
                        CarBuyDialog.this.ivAnim.setVideoItem(fVar);
                        CarBuyDialog.this.ivAnim.b();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public OnBuyListener getOnBuyListener() {
        return this.onBuyListener;
    }

    public void setBuyListener(OnBuyListener onBuyListener) {
        this.onBuyListener = onBuyListener;
    }

    private String formatGold(String str) {
        return p.d(str);
    }

    private void getUserOver() {
        this.tvCurrentGold.setText(getString(R.string.fq_userover_loading));
        ApiRetrofit.getInstance().getApiService().getUserOverService(new RequestParams().getUserOverParams()).map(new ServerResultFunction<UserEntity>() {
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new HttpRxObserver(this.mContext, new ResultCallBack<UserEntity>() {
            public void onSuccess(UserEntity userEntity) {
                CarBuyDialog.this.userOver = userEntity == null ? "0" : userEntity.getTotResult();
                CarBuyDialog.this.tvCurrentGold.setText(CarBuyDialog.this.mContext.getString(R.string.fq_current_buy_gold, new Object[]{CarBuyDialog.this.formatGold(CarBuyDialog.this.userOver)}));
            }

            public void onError(int i, String str) {
                CarBuyDialog.this.tvCurrentGold.setText(R.string.fq_userover_loading_fail);
            }
        }));
    }

    private void goToRecharge() {
        if (a.a().a != null) {
            SureCancelDialog.newInstance(getString(R.string.fq_over_insufficient), new -$$Lambda$CarBuyDialog$r3Mw8yhkeMcJ32L_3F147bC6aZk(this)).show(getFragmentManager());
        }
    }

    public static /* synthetic */ void lambda$goToRecharge$1(CarBuyDialog carBuyDialog, View view) {
        if (a.a().a != null) {
            carBuyDialog.dismiss();
            a.a().a.a(carBuyDialog.mContext);
        }
    }

    private CarDownloadEntity formatCarDownloadEntity() {
        if (this.carItemEntity == null) {
            return null;
        }
        CarDownloadEntity carDownloadEntity = new CarDownloadEntity();
        carDownloadEntity.id = this.carItemEntity.id;
        carDownloadEntity.imgUrl = this.carItemEntity.imgUrl;
        carDownloadEntity.name = this.carItemEntity.name;
        carDownloadEntity.zipUrl = this.carItemEntity.zipUrl;
        carDownloadEntity.onlineUrl = this.carItemEntity.onlineUrl;
        return carDownloadEntity;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.ivAnim != null) {
            this.ivAnim.a(true);
        }
        if (this.svgaParser != null) {
            this.svgaParser = null;
        }
    }
}

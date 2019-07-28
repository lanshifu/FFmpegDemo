package com.tomatolive.library.ui.activity.mylive;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.d;
import com.opensource.svgaplayer.f;
import com.tomatolive.library.R;
import com.tomatolive.library.a;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.download.CarDownLoadManager;
import com.tomatolive.library.model.CarDownloadEntity;
import com.tomatolive.library.model.CarEntity;
import com.tomatolive.library.ui.presenter.CarMallDetailPresenter;
import com.tomatolive.library.ui.view.dialog.SureCancelDialog;
import com.tomatolive.library.ui.view.iview.ICarMallDetailView;
import com.tomatolive.library.utils.p;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.disposables.b;
import io.reactivex.k;
import io.reactivex.r;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CarMallDetailActivity extends BaseActivity<CarMallDetailPresenter> implements ICarMallDetailView {
    private CarEntity carItemEntity;
    private String currentCarGold = "0";
    private SVGAImageView ivAnim;
    private d svgaParser;
    private TextView tvCurrentGold;
    private TextView tvDay30;
    private TextView tvDay7;
    private TextView tvNameGold;
    private TextView tvNowBuy;
    private String userOver = "0";

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public CarMallDetailPresenter createPresenter() {
        return new CarMallDetailPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_car_mall_detail;
    }

    public void initView(Bundle bundle) {
        setActivityTitle(R.string.fq_my_live_car_mall_detail);
        this.carItemEntity = (CarEntity) getIntent().getSerializableExtra("resultItem");
        this.ivAnim = (SVGAImageView) findViewById(R.id.iv_anim);
        this.tvNameGold = (TextView) findViewById(R.id.tv_name_gold);
        this.tvDay7 = (TextView) findViewById(R.id.tv_day_7);
        this.tvDay30 = (TextView) findViewById(R.id.tv_day_30);
        TextView textView = (TextView) findViewById(R.id.tv_desc);
        this.tvCurrentGold = (TextView) findViewById(R.id.tv_buy_gold);
        this.tvNowBuy = (TextView) findViewById(R.id.tv_now_buy);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        sendUserOverRequest();
        if (this.carItemEntity != null) {
            initPriceSelectedView(true);
            this.tvDay7.setText(getString(R.string.fq_count_day, new Object[]{"7"}));
            this.tvDay30.setText(getString(R.string.fq_count_day, new Object[]{"30"}));
            textView.setText(this.carItemEntity.description);
            startAnim();
        }
    }

    public void initListener() {
        super.initListener();
        this.tvDay7.setOnClickListener(new -$$Lambda$CarMallDetailActivity$gle1QekB-UbRe4ak7iSHaqTALJc(this));
        this.tvDay30.setOnClickListener(new -$$Lambda$CarMallDetailActivity$P_lzAJHJW7XBXYH460nkDoIe9ds(this));
        this.tvCurrentGold.setOnClickListener(new -$$Lambda$CarMallDetailActivity$xgPGLINP6Y9LDlz2QNbflxe0z7U(this));
        this.tvNowBuy.setOnClickListener(new -$$Lambda$CarMallDetailActivity$iI3r_wCNAOM0z2fZhpB0P3es7YU(this));
    }

    public static /* synthetic */ void lambda$initListener$3(CarMallDetailActivity carMallDetailActivity, View view) {
        if (carMallDetailActivity.carItemEntity != null) {
            if (!carMallDetailActivity.carItemEntity.isPublicCar()) {
                carMallDetailActivity.showToast(R.string.fq_private_car_buy_tips);
            } else if (p.b(carMallDetailActivity.userOver) == 0 || p.b(carMallDetailActivity.userOver) < p.b(carMallDetailActivity.currentCarGold)) {
                carMallDetailActivity.goToRecharge();
            } else {
                ((CarMallDetailPresenter) carMallDetailActivity.mPresenter).buyCar(carMallDetailActivity.carItemEntity.id, carMallDetailActivity.getCarType(), carMallDetailActivity.currentCarGold);
            }
        }
    }

    private void sendUserOverRequest() {
        this.tvCurrentGold.setText(getString(R.string.fq_userover_loading));
        ((CarMallDetailPresenter) this.mPresenter).getUserOver();
    }

    private void initPriceSelectedView(boolean z) {
        if (this.carItemEntity != null) {
            this.tvDay7.setSelected(z);
            this.tvDay30.setSelected(z ^ 1);
            initNameGoldStr(z ? this.carItemEntity.weekGold : this.carItemEntity.monthGold);
            this.currentCarGold = z ? this.carItemEntity.weekGold : this.carItemEntity.monthGold;
        }
    }

    private void initNameGoldStr(String str) {
        this.tvNameGold.setText(Html.fromHtml(getString(R.string.fq_car_buy_gold_tips, new Object[]{this.carItemEntity.name, formatGold(str)})));
    }

    private String formatGold(String str) {
        return p.d(str);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.ivAnim != null) {
            this.ivAnim.a(true);
        }
    }

    public void onUserOverSuccess(String str) {
        this.userOver = str;
        this.tvCurrentGold.setText(this.mContext.getString(R.string.fq_current_buy_gold, new Object[]{formatGold(str)}));
    }

    public void onUserOverFail() {
        this.tvCurrentGold.setText(R.string.fq_userover_loading_fail);
    }

    public void onBuyCarSuccess() {
        showToast(R.string.fq_purchase_succeeded);
        finish();
    }

    private String getCarType() {
        return this.tvDay7.isSelected() ? "7" : "30";
    }

    private void goToRecharge() {
        if (a.a().a != null) {
            SureCancelDialog.newInstance(getString(R.string.fq_over_insufficient), new -$$Lambda$CarMallDetailActivity$VPs8Z3HO9vxQ6C49_JAlPSm7kqg(this)).show(getSupportFragmentManager());
        }
    }

    public static /* synthetic */ void lambda$goToRecharge$4(CarMallDetailActivity carMallDetailActivity, View view) {
        if (a.a().a != null) {
            a.a().a.a(carMallDetailActivity.mContext);
        }
    }

    private void startAnim() {
        try {
            k.just(Boolean.valueOf(true)).map(new -$$Lambda$CarMallDetailActivity$CitOrLzP0pxx9XwmzlGZ_zBfeT0(this)).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<InputStream>() {
                public void onComplete() {
                }

                public void onSubscribe(b bVar) {
                }

                public void onNext(InputStream inputStream) {
                    if (CarMallDetailActivity.this.svgaParser == null) {
                        CarMallDetailActivity.this.svgaParser = new d(CarMallDetailActivity.this.mContext);
                    }
                    if (inputStream == null) {
                        CarMallDetailActivity.this.loadNetAnimation();
                    } else {
                        CarMallDetailActivity.this.svgaParser.a(inputStream, CarMallDetailActivity.this.carItemEntity.id, new d.b() {
                            public void onError() {
                            }

                            public void onComplete(f fVar) {
                                CarMallDetailActivity.this.ivAnim.setVisibility(0);
                                CarMallDetailActivity.this.ivAnim.setVideoItem(fVar);
                                CarMallDetailActivity.this.ivAnim.b();
                            }
                        }, true);
                    }
                }

                public void onError(Throwable th) {
                    CarMallDetailActivity.this.loadNetAnimation();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ InputStream lambda$startAnim$5(CarMallDetailActivity carMallDetailActivity, Boolean bool) throws Exception {
        return carMallDetailActivity.carItemEntity == null ? null : com.tomatolive.library.utils.b.r(carMallDetailActivity.carItemEntity.id);
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
                        CarMallDetailActivity.this.ivAnim.setVideoItem(fVar);
                        CarMallDetailActivity.this.ivAnim.b();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}

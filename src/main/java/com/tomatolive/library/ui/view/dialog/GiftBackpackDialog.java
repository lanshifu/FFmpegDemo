package com.tomatolive.library.ui.view.dialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.function.HttpResultFunction;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.model.BackpackItemEntity;
import com.tomatolive.library.model.BaseGiftBackpackEntity;
import com.tomatolive.library.model.GiftDownloadItemEntity;
import com.tomatolive.library.ui.view.gift.giftpanel.BackpackPanelControl;
import com.tomatolive.library.ui.view.gift.giftpanel.GiftPanelControl;
import com.tomatolive.library.utils.a;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.disposables.b;
import io.reactivex.r;
import java.text.NumberFormat;
import java.util.List;

public class GiftBackpackDialog extends BaseBottomDialogFragment {
    private static final String KEY_LAYOUT_RES = "bottom_layout_res";
    private BackpackPanelControl backpackPanelControl;
    private TextView btnSend;
    private LinearLayout dotsBackpack;
    private LinearLayout dotsGift;
    private int giftNum = 1;
    private GiftPanelControl giftPanelControl;
    private SendClickListener giftSendClickListener;
    private boolean isSelectGift = true;
    private LinearLayout llBackpackView;
    private LinearLayout llGiftView;
    private List<GiftDownloadItemEntity> mData;
    private FragmentManager mFragmentManager;
    @LayoutRes
    private int mLayoutRes;
    private BackpackItemEntity selectBackpackEntity;
    private GiftDownloadItemEntity selectGiftEntity;
    private TextView tvBackpackTitle;
    private TextView tvEmptyView;
    private TextView tvGiftTitle;
    private TextView tvLoadingView;
    private TextView tvPrice;
    private double userBalance = 0.0d;
    private ViewPager viewPagerBackpack;
    private ViewPager viewPagerGift;

    public interface SendClickListener {
        void onRechargeCallback(View view);

        void onSendCallback(boolean z, BaseGiftBackpackEntity baseGiftBackpackEntity);
    }

    public float getDimAmount() {
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public static GiftBackpackDialog create(FragmentManager fragmentManager, List<GiftDownloadItemEntity> list, double d, SendClickListener sendClickListener) {
        GiftBackpackDialog giftBackpackDialog = new GiftBackpackDialog();
        giftBackpackDialog.setFragmentManager(fragmentManager);
        giftBackpackDialog.setGiftList(list);
        giftBackpackDialog.setOnSendClickListener(sendClickListener);
        return giftBackpackDialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mLayoutRes = bundle.getInt(KEY_LAYOUT_RES);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(KEY_LAYOUT_RES, this.mLayoutRes);
        super.onSaveInstanceState(bundle);
    }

    public int getLayoutRes() {
        this.mLayoutRes = R.layout.fq_dialog_gift_backpack;
        return this.mLayoutRes;
    }

    public void initView(View view) {
        this.llGiftView = (LinearLayout) view.findViewById(R.id.ll_gift_view);
        this.llBackpackView = (LinearLayout) view.findViewById(R.id.ll_backpack_view);
        this.tvGiftTitle = (TextView) view.findViewById(R.id.tv_gift);
        this.tvBackpackTitle = (TextView) view.findViewById(R.id.tv_backpack);
        this.tvPrice = (TextView) view.findViewById(R.id.tv_price);
        this.tvEmptyView = (TextView) view.findViewById(R.id.tv_empty_view);
        this.tvLoadingView = (TextView) view.findViewById(R.id.tv_loading_view);
        this.btnSend = (TextView) view.findViewById(R.id.tv_gift_send);
        this.viewPagerGift = (ViewPager) view.findViewById(R.id.view_pager_gift);
        this.viewPagerBackpack = (ViewPager) view.findViewById(R.id.view_pager_backpack);
        this.dotsGift = (LinearLayout) view.findViewById(R.id.dots_gift);
        this.dotsBackpack = (LinearLayout) view.findViewById(R.id.dots_backpack);
        this.giftPanelControl = new GiftPanelControl(this.mContext, this.viewPagerGift, this.dotsGift, getGiftList());
        this.backpackPanelControl = new BackpackPanelControl(this.mContext, this.viewPagerBackpack, this.dotsBackpack);
        setUserBalanceTip(R.string.fq_userover_loading);
        initTagSelector(true);
        this.btnSend.setEnabled(false);
    }

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
        super.initListener(view);
        this.giftPanelControl.setGiftClickListener(new -$$Lambda$GiftBackpackDialog$fPdndAaalNi-hBja5bff5whgjxU(this));
        this.backpackPanelControl.setGiftClickListener(new -$$Lambda$GiftBackpackDialog$mGFRq85pWjDlNGigTPcDhVax0AM(this));
        this.btnSend.setOnClickListener(new -$$Lambda$GiftBackpackDialog$tkMQpcaW6RLz-R5dOmgKFxY8P74(this));
        this.tvPrice.setOnClickListener(new -$$Lambda$GiftBackpackDialog$8zm9lxRO7TqegciaMJR-wuoIr94(this));
        this.llGiftView.setOnClickListener(new -$$Lambda$GiftBackpackDialog$kpwfdg_ak8HfXQOZpOOdEtWnSZQ(this));
        this.llBackpackView.setOnClickListener(new -$$Lambda$GiftBackpackDialog$-g8QJ0wZZRAeZv4Y3p35I_-6KiY(this));
    }

    public static /* synthetic */ void lambda$initListener$0(GiftBackpackDialog giftBackpackDialog, GiftDownloadItemEntity giftDownloadItemEntity) {
        giftBackpackDialog.selectGiftEntity = giftDownloadItemEntity;
        TextView textView = giftBackpackDialog.btnSend;
        boolean z = giftBackpackDialog.isSelectGift && giftBackpackDialog.selectGiftEntity != null;
        textView.setEnabled(z);
    }

    public static /* synthetic */ void lambda$initListener$1(GiftBackpackDialog giftBackpackDialog, BackpackItemEntity backpackItemEntity) {
        giftBackpackDialog.selectBackpackEntity = backpackItemEntity;
        TextView textView = giftBackpackDialog.btnSend;
        boolean z = (giftBackpackDialog.isSelectGift || giftBackpackDialog.selectBackpackEntity == null) ? false : true;
        textView.setEnabled(z);
    }

    public static /* synthetic */ void lambda$initListener$2(GiftBackpackDialog giftBackpackDialog, View view) {
        if (giftBackpackDialog.giftSendClickListener != null) {
            giftBackpackDialog.giftSendClickListener.onSendCallback(giftBackpackDialog.isSelectGift, giftBackpackDialog.isSelectGift ? giftBackpackDialog.selectGiftEntity : giftBackpackDialog.selectBackpackEntity);
            if (!giftBackpackDialog.isSelectGift) {
                giftBackpackDialog.backpackPanelControl.updateSelectedItemCount();
                if (giftBackpackDialog.backpackPanelControl.isEmpty()) {
                    giftBackpackDialog.tvEmptyView.setVisibility(0);
                    giftBackpackDialog.viewPagerBackpack.setVisibility(8);
                }
            }
            a.a(giftBackpackDialog.btnSend);
        }
    }

    public static /* synthetic */ void lambda$initListener$3(GiftBackpackDialog giftBackpackDialog, View view) {
        giftBackpackDialog.dismiss();
        if (giftBackpackDialog.giftSendClickListener != null) {
            giftBackpackDialog.giftSendClickListener.onRechargeCallback(view);
        }
    }

    private void setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public void show() {
        show(this.mFragmentManager);
    }

    public void setUserBalanceTip(@StringRes int i) {
        if (this.tvPrice != null) {
            this.tvPrice.setText(i);
        }
    }

    private void setOnSendClickListener(SendClickListener sendClickListener) {
        this.giftSendClickListener = sendClickListener;
    }

    private double getUserBalance() {
        return this.userBalance;
    }

    public void setUserBalance(double d) {
        this.userBalance = d;
        if (this.tvPrice != null) {
            this.tvPrice.setText(formatPrice(getUserBalance()));
        }
    }

    public int getGiftNum() {
        return this.giftNum;
    }

    public void setGiftNum(int i) {
        this.giftNum = i;
    }

    private String formatPrice(double d) {
        NumberFormat instance = NumberFormat.getInstance();
        instance.setGroupingUsed(false);
        return instance.format(d);
    }

    private void initTagSelector(boolean z) {
        this.isSelectGift = z;
        this.llGiftView.setSelected(z);
        this.tvGiftTitle.setSelected(z);
        this.llBackpackView.setSelected(z ^ 1);
        this.tvBackpackTitle.setSelected(z ^ 1);
        int i = 4;
        int i2 = 0;
        this.viewPagerGift.setVisibility(z ? 0 : 4);
        LinearLayout linearLayout = this.dotsGift;
        if (z) {
            i = 0;
        }
        linearLayout.setVisibility(i);
        ViewPager viewPager = this.viewPagerBackpack;
        int i3 = (z || !this.backpackPanelControl.isCountEnabled()) ? 8 : 0;
        viewPager.setVisibility(i3);
        linearLayout = this.dotsBackpack;
        if (z) {
            i2 = 8;
        }
        linearLayout.setVisibility(i2);
        this.btnSend.setEnabled(getSendEnabled());
        this.tvEmptyView.setVisibility(8);
        sendBackpackRequest(z);
    }

    private void sendBackpackRequest(boolean z) {
        if (!z) {
            this.btnSend.setEnabled(false);
            ApiRetrofit.getInstance().getApiService().getUserPropsListService(new RequestParams().getUserIdParams()).map(new ServerResultFunction<HttpResultPageModel<BackpackItemEntity>>() {
            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new r<HttpResultPageModel<BackpackItemEntity>>() {
                public void onComplete() {
                }

                public void onSubscribe(b bVar) {
                    GiftBackpackDialog.this.tvLoadingView.setVisibility(0);
                    GiftBackpackDialog.this.tvEmptyView.setVisibility(8);
                    GiftBackpackDialog.this.viewPagerBackpack.setVisibility(8);
                }

                public void onNext(HttpResultPageModel<BackpackItemEntity> httpResultPageModel) {
                    if (httpResultPageModel != null) {
                        GiftBackpackDialog.this.backpackPanelControl.updateBackpackList(httpResultPageModel.dataList);
                        int i = 8;
                        GiftBackpackDialog.this.tvLoadingView.setVisibility(8);
                        GiftBackpackDialog.this.tvEmptyView.setVisibility(!GiftBackpackDialog.this.backpackPanelControl.isCountEnabled() ? 0 : 8);
                        ViewPager access$200 = GiftBackpackDialog.this.viewPagerBackpack;
                        if (GiftBackpackDialog.this.backpackPanelControl.isCountEnabled()) {
                            i = 0;
                        }
                        access$200.setVisibility(i);
                    }
                }

                public void onError(Throwable th) {
                    GiftBackpackDialog.this.tvLoadingView.setVisibility(8);
                    GiftBackpackDialog.this.tvEmptyView.setVisibility(0);
                    GiftBackpackDialog.this.viewPagerBackpack.setVisibility(8);
                }
            });
        }
    }

    private boolean getSendEnabled() {
        if (this.isSelectGift) {
            if (this.selectGiftEntity == null) {
                return false;
            }
        } else if (this.selectBackpackEntity == null) {
            return false;
        }
        return true;
    }

    private List<GiftDownloadItemEntity> getGiftList() {
        return this.mData;
    }

    private void setGiftList(List<GiftDownloadItemEntity> list) {
        this.mData = list;
    }

    public void updateGiftList(List<GiftDownloadItemEntity> list) {
        setGiftList(list);
        if (this.giftPanelControl != null) {
            this.giftPanelControl.updateGiftList(getGiftList());
        }
    }

    public void release() {
        if (this.giftPanelControl != null) {
            this.giftPanelControl.onDestroy();
        }
        if (this.backpackPanelControl != null) {
            this.backpackPanelControl.onDestroy();
        }
        setOnSendClickListener(null);
    }
}

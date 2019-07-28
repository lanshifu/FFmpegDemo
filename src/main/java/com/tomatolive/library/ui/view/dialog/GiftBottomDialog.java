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
import com.tomatolive.library.model.GiftDownloadItemEntity;
import com.tomatolive.library.ui.view.gift.giftpanel.GiftPanelControl;
import com.tomatolive.library.ui.view.gift.giftpanel.GiftPanelControl.GiftClickListener;
import com.tomatolive.library.utils.a;
import com.yalantis.ucrop.view.CropImageView;
import java.text.NumberFormat;
import java.util.List;

public class GiftBottomDialog extends BaseBottomDialogFragment {
    private static final String KEY_LAYOUT_RES = "bottom_layout_res";
    private TextView btnSend;
    private int giftNum = 1;
    private GiftPanelControl giftPanelControl;
    private GiftSendClickListener giftSendClickListener;
    private List<GiftDownloadItemEntity> mData;
    private FragmentManager mFragmentManager;
    @LayoutRes
    private int mLayoutRes;
    private GiftDownloadItemEntity selectGift;
    private TextView tvPrice;
    private double userBalance = 0.0d;

    public interface GiftSendClickListener {
        void onGiftRechargeCallback(View view);

        void onGiftSendCallback(View view, GiftDownloadItemEntity giftDownloadItemEntity);
    }

    public float getDimAmount() {
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public static GiftBottomDialog create(FragmentManager fragmentManager, List<GiftDownloadItemEntity> list, double d, GiftSendClickListener giftSendClickListener) {
        GiftBottomDialog giftBottomDialog = new GiftBottomDialog();
        giftBottomDialog.setFragmentManager(fragmentManager);
        giftBottomDialog.setmData(list);
        giftBottomDialog.setOnSendClickListener(giftSendClickListener);
        return giftBottomDialog;
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
        this.mLayoutRes = R.layout.fq_layout_gift_view;
        return this.mLayoutRes;
    }

    public void initView(View view) {
        this.btnSend = (TextView) view.findViewById(R.id.tv_gift_send);
        TextView textView = (TextView) view.findViewById(R.id.tv_gift_num_select);
        this.tvPrice = (TextView) view.findViewById(R.id.tv_price);
        this.giftPanelControl = new GiftPanelControl(this.mContext, (ViewPager) view.findViewById(R.id.toolbox_pagers_face), (LinearLayout) view.findViewById(R.id.face_dots_container), getData());
        this.giftPanelControl.setGiftClickListener(new GiftClickListener() {
            public void onClick(GiftDownloadItemEntity giftDownloadItemEntity) {
                GiftBottomDialog.this.selectGift = giftDownloadItemEntity;
                GiftBottomDialog.this.btnSend.setEnabled(GiftBottomDialog.this.selectGift != null);
            }
        });
        setUserBalanceTip(R.string.fq_userover_loading);
        textView.setText(String.valueOf(this.giftNum));
        this.btnSend.setEnabled(false);
        this.btnSend.setOnClickListener(new -$$Lambda$GiftBottomDialog$8pW42Qbs396YKSVo2hZCHQOaVFw(this));
        this.tvPrice.setOnClickListener(new -$$Lambda$GiftBottomDialog$WEqQgXsXKiC_ETBISRe-sb_vvHc(this));
    }

    public static /* synthetic */ void lambda$initView$0(GiftBottomDialog giftBottomDialog, View view) {
        if (giftBottomDialog.giftSendClickListener != null) {
            giftBottomDialog.giftSendClickListener.onGiftSendCallback(view, giftBottomDialog.selectGift);
            a.a(giftBottomDialog.btnSend);
        }
    }

    public static /* synthetic */ void lambda$initView$1(GiftBottomDialog giftBottomDialog, View view) {
        giftBottomDialog.dismiss();
        if (giftBottomDialog.giftSendClickListener != null) {
            giftBottomDialog.giftSendClickListener.onGiftRechargeCallback(view);
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

    private void setOnSendClickListener(GiftSendClickListener giftSendClickListener) {
        this.giftSendClickListener = giftSendClickListener;
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

    private List<GiftDownloadItemEntity> getData() {
        return this.mData;
    }

    private void setmData(List<GiftDownloadItemEntity> list) {
        this.mData = list;
    }

    public void updateDatas(List<GiftDownloadItemEntity> list) {
        setmData(list);
        if (this.giftPanelControl != null) {
            this.giftPanelControl.updateGiftList(getData());
        }
    }
}

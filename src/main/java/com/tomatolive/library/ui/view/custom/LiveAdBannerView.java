package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.a;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.adapter.LiveBannerImageLoaderAdapter;
import com.tomatolive.library.ui.view.dialog.WebViewDialog;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.r;
import com.tomatolive.library.websocket.nvwebsocket.ConnectSocketParams;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.Banner;
import java.util.List;

public class LiveAdBannerView extends RelativeLayout {
    private Banner bannerView;
    private ImageView ivAdClose;
    private ImageView ivAdImg;
    private ImageView ivBannerClose;
    private Context mContext;
    private RelativeLayout rlAdBg;
    private int tempPos = -1;

    public LiveAdBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView();
    }

    private void initView() {
        inflate(this.mContext, R.layout.fq_layout_live_banner_view, this);
        this.bannerView = (Banner) findViewById(R.id.banner_top);
        this.ivAdImg = (ImageView) findViewById(R.id.iv_ad);
        this.ivBannerClose = (ImageView) findViewById(R.id.iv_banner_close);
        this.ivAdClose = (ImageView) findViewById(R.id.iv_ad_close);
        this.rlAdBg = (RelativeLayout) findViewById(R.id.rl_ad_bg);
        this.ivAdClose.setOnClickListener(new -$$Lambda$LiveAdBannerView$v0Yw9GbAi-EbJSQHKUycVIwE7rk(this));
        this.ivBannerClose.setOnClickListener(new -$$Lambda$LiveAdBannerView$AAN0piByH9FLjon7wHWm-pZWXaw(this));
    }

    public static /* synthetic */ void lambda$initView$1(LiveAdBannerView liveAdBannerView, View view) {
        liveAdBannerView.bannerView.setVisibility(4);
        liveAdBannerView.ivBannerClose.setVisibility(4);
    }

    public void initAdBannerImages(List<BannerEntity> list) {
        if (this.bannerView != null) {
            int i = 4;
            if (list == null || list.isEmpty()) {
                this.bannerView.setVisibility(4);
                this.ivBannerClose.setVisibility(4);
            } else if (b.b((List) list) != null && b.b((List) list).size() != 0) {
                this.bannerView.setVisibility(0);
                ImageView imageView = this.ivBannerClose;
                if (isAllowClose(list)) {
                    i = 0;
                }
                imageView.setVisibility(i);
                this.bannerView.a(new -$$Lambda$LiveAdBannerView$xtJfjC418fKANwWishzrMVychAs(this, list));
                this.bannerView.a(b.b((List) list)).a(new LiveBannerImageLoaderAdapter()).a();
            }
        }
    }

    public static /* synthetic */ void lambda$initAdBannerImages$2(LiveAdBannerView liveAdBannerView, List list, int i) {
        BannerEntity bannerEntity = (BannerEntity) b.a(list).get(i);
        if (bannerEntity != null) {
            a.a().a(liveAdBannerView.mContext, new RequestParams().getBannerStatisticsParams(bannerEntity.id, bannerEntity.name));
            if (TextUtils.equals("1", bannerEntity.method)) {
                LiveEntity a = b.a(bannerEntity);
                if (b.a(liveAdBannerView.mContext)) {
                    b.a(liveAdBannerView.mContext, 1, a);
                }
            } else if (liveAdBannerView.mContext instanceof FragmentActivity) {
                WebViewDialog.newInstance(bannerEntity.name, bannerEntity.url).show(((FragmentActivity) liveAdBannerView.mContext).getSupportFragmentManager());
            }
        }
    }

    public void initVerticalAdImage(List<BannerEntity> list) {
        int i = 4;
        if (list == null || list.isEmpty()) {
            this.rlAdBg.setVisibility(4);
            return;
        }
        List a = b.a((List) list);
        if (a.size() > 0) {
            BannerEntity bannerEntity = (BannerEntity) a.get(0);
            this.rlAdBg.setVisibility(0);
            i.c(this.mContext, this.ivAdImg, bannerEntity.img);
            ImageView imageView = this.ivAdClose;
            if (!TextUtils.equals(ConnectSocketParams.EFFECT_TYPE_BIG, bannerEntity.allow_close)) {
                i = 0;
            }
            imageView.setVisibility(i);
            r.a().a(this.ivAdImg, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$LiveAdBannerView$3LBsla2cOGOFQfKisOpZo7BHRhg(this, bannerEntity));
        }
    }

    public static /* synthetic */ void lambda$initVerticalAdImage$3(LiveAdBannerView liveAdBannerView, BannerEntity bannerEntity, Object obj) {
        a.a().a(liveAdBannerView.mContext, new RequestParams().getBannerStatisticsParams(bannerEntity.id, bannerEntity.name));
        if (liveAdBannerView.mContext instanceof FragmentActivity) {
            WebViewDialog.newInstance(bannerEntity.name, bannerEntity.url).show(((FragmentActivity) liveAdBannerView.mContext).getSupportFragmentManager());
        }
    }

    private boolean isAllowClose(List<BannerEntity> list) {
        for (BannerEntity bannerEntity : list) {
            if (TextUtils.equals(ConnectSocketParams.EFFECT_TYPE_BIG, bannerEntity.allow_close)) {
                return false;
            }
        }
        return true;
    }
}

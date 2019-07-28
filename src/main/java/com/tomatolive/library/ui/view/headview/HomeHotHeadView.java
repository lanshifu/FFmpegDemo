package com.tomatolive.library.ui.view.headview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.utils.i;
import com.youth.banner.Banner;
import java.util.List;

public class HomeHotHeadView extends LinearLayout {
    private List<BannerEntity> bannerList;
    private Banner bannerView;
    private ImageView ivAvatarCopper;
    private ImageView ivAvatarGold;
    private ImageView ivAvatarSilver;
    private ImageView ivDefaultCover;
    private Context mContext;
    private OnBannerClickListener onBannerClickListener;

    public interface OnBannerClickListener {
        void onBannerClick(BannerEntity bannerEntity);
    }

    public HomeHotHeadView(Context context) {
        this(context, null);
    }

    public HomeHotHeadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HomeHotHeadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        inflate(getContext(), R.layout.fq_layout_head_view_home_hot, this);
        this.mContext = context;
        this.bannerView = (Banner) findViewById(R.id.banner);
        this.ivAvatarGold = (ImageView) findViewById(R.id.iv_avatar_gold);
        this.ivAvatarSilver = (ImageView) findViewById(R.id.iv_avatar_silver);
        this.ivAvatarCopper = (ImageView) findViewById(R.id.iv_avatar_copper);
        this.ivDefaultCover = (ImageView) findViewById(R.id.iv_default_cover);
        findViewById(R.id.rl_top_bg).setOnClickListener(new -$$Lambda$HomeHotHeadView$OP7iHlQenUyLtGg6WThUZqfpMKo(this));
    }

    public void initBannerClickListener(OnBannerClickListener onBannerClickListener) {
        this.onBannerClickListener = onBannerClickListener;
    }

    /* JADX WARNING: Missing block: B:17:0x0058, code skipped:
            return;
     */
    public void initBannerImages(java.util.List<com.tomatolive.library.model.BannerEntity> r5) {
        /*
        r4 = this;
        r0 = 0;
        r1 = 4;
        if (r5 == 0) goto L_0x0059;
    L_0x0004:
        r2 = r5.isEmpty();
        if (r2 == 0) goto L_0x000b;
    L_0x000a:
        goto L_0x0059;
    L_0x000b:
        r2 = r4.bannerView;
        r2.setVisibility(r0);
        r0 = r4.ivDefaultCover;
        r0.setVisibility(r1);
        r0 = com.tomatolive.library.utils.b.a(r5);
        r4.bannerList = r0;
        r5 = com.tomatolive.library.utils.b.b(r5);
        r0 = r4.bannerList;
        if (r0 == 0) goto L_0x0058;
    L_0x0023:
        r0 = r4.bannerList;
        r0 = r0.size();
        if (r0 != 0) goto L_0x002c;
    L_0x002b:
        goto L_0x0058;
    L_0x002c:
        if (r5 == 0) goto L_0x0057;
    L_0x002e:
        r0 = r5.size();
        if (r0 != 0) goto L_0x0035;
    L_0x0034:
        goto L_0x0057;
    L_0x0035:
        r0 = r4.bannerView;	 Catch:{ Exception -> 0x004c }
        r5 = r0.a(r5);	 Catch:{ Exception -> 0x004c }
        r0 = 7;
        r5 = r5.a(r0);	 Catch:{ Exception -> 0x004c }
        r0 = new com.tomatolive.library.ui.adapter.BannerImageLoaderAdapter;	 Catch:{ Exception -> 0x004c }
        r0.<init>();	 Catch:{ Exception -> 0x004c }
        r5 = r5.a(r0);	 Catch:{ Exception -> 0x004c }
        r5.a();	 Catch:{ Exception -> 0x004c }
    L_0x004c:
        r5 = r4.bannerView;
        r0 = new com.tomatolive.library.ui.view.headview.-$$Lambda$HomeHotHeadView$ErvpSVwS7XkdN0AQKQPYG9frGXA;
        r0.<init>(r4);
        r5.a(r0);
        return;
    L_0x0057:
        return;
    L_0x0058:
        return;
    L_0x0059:
        r5 = r4.bannerView;
        r5.setVisibility(r1);
        r5 = r4.ivDefaultCover;
        r5.setVisibility(r0);
        r5 = r4.mContext;
        r0 = r4.ivDefaultCover;
        r1 = com.tomatolive.library.R.drawable.fq_shape_default_banner_cover_bg;
        r2 = 6;
        r3 = jp.wasabeef.glide.transformations.RoundedCornersTransformation.CornerType.ALL;
        com.tomatolive.library.utils.i.a(r5, r0, r1, r2, r3);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.headview.HomeHotHeadView.initBannerImages(java.util.List):void");
    }

    public static /* synthetic */ void lambda$initBannerImages$1(HomeHotHeadView homeHotHeadView, int i) {
        if (homeHotHeadView.onBannerClickListener != null && homeHotHeadView.bannerList != null && homeHotHeadView.bannerList.size() > 0) {
            homeHotHeadView.onBannerClickListener.onBannerClick((BannerEntity) homeHotHeadView.bannerList.get(i));
        }
    }

    public void initTopList(List<AnchorEntity> list) {
        if (list != null && !list.isEmpty()) {
            i.a(getContext(), this.ivAvatarGold, ((AnchorEntity) list.get(0)).avatar, R.drawable.fq_ic_placeholder_avatar);
            if (list.size() >= 2) {
                i.a(getContext(), this.ivAvatarSilver, ((AnchorEntity) list.get(1)).avatar, R.drawable.fq_ic_placeholder_avatar);
            }
            if (list.size() >= 3) {
                i.a(getContext(), this.ivAvatarCopper, ((AnchorEntity) list.get(2)).avatar, R.drawable.fq_ic_placeholder_avatar);
            }
        }
    }
}

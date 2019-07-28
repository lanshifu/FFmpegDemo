package com.tomatolive.library.ui.adapter;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;
import com.tomatolive.library.utils.u;

public class HomeLiveAdapter extends BaseQuickAdapter<LiveEntity, BaseViewHolder> {
    private Fragment fragment = null;

    public HomeLiveAdapter(int i) {
        super(i);
    }

    public HomeLiveAdapter(Fragment fragment, int i) {
        super(i);
        this.fragment = fragment;
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, LiveEntity liveEntity) {
        baseViewHolder.setText(R.id.tv_live_title, u.a(liveEntity.topic, 15)).setText(R.id.tv_personal, b.d(liveEntity.popularity)).setText(R.id.tv_nick_name, liveEntity.nickname).setText(R.id.tv_pre_notice, getPreNoticeStr(liveEntity)).setVisible(R.id.rl_live_status, b.a(liveEntity) ^ 1).setVisible(R.id.iv_pay, b.a(liveEntity.free)).setVisible(R.id.rl_pre_notice_bg, isPreNotice(liveEntity));
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_label);
        imageView.setVisibility(TextUtils.isEmpty(liveEntity.markerUrl) ? 4 : 0);
        if (!TextUtils.isEmpty(liveEntity.markerUrl)) {
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ScaleType.FIT_START);
            i.b(this.mContext, imageView, liveEntity.markerUrl);
        }
        ImageView imageView2 = (ImageView) baseViewHolder.getView(R.id.iv_cover);
        String str = TextUtils.isEmpty(liveEntity.liveCoverUrl) ? liveEntity.avatar : liveEntity.liveCoverUrl;
        if (this.fragment != null) {
            i.a(this.fragment, imageView2, str, 6, R.drawable.fq_ic_placeholder_corners);
        } else {
            i.c(this.mContext, imageView2, str, 6, R.drawable.fq_ic_placeholder_corners);
        }
    }

    private boolean isPreNotice(LiveEntity liveEntity) {
        return (TextUtils.isEmpty(liveEntity.herald) || liveEntity.isOnLiving()) ? false : true;
    }

    private String getPreNoticeStr(LiveEntity liveEntity) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(liveEntity.herald);
        if (!TextUtils.isEmpty(liveEntity.publishTime)) {
            stringBuilder.append("\n");
            stringBuilder.append("(");
            stringBuilder.append(d.c(p.b(liveEntity.publishTime) * 1000));
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}

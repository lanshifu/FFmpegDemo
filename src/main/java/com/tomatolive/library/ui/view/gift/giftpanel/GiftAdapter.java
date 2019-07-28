package com.tomatolive.library.ui.view.gift.giftpanel;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.GiftDownloadItemEntity;
import com.tomatolive.library.utils.i;
import java.util.List;

public class GiftAdapter extends BaseQuickAdapter<GiftDownloadItemEntity, BaseViewHolder> {
    private int selectedPosition = -1;

    public GiftAdapter(@Nullable List<GiftDownloadItemEntity> list) {
        super(R.layout.fq_item_grid_gift, list);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, GiftDownloadItemEntity giftDownloadItemEntity) {
        boolean z = false;
        boolean z2 = this.selectedPosition == baseViewHolder.getAdapterPosition();
        BaseViewHolder visible = baseViewHolder.setText(R.id.tv_item_title, giftDownloadItemEntity.name).setTextColor(R.id.tv_item_title, ContextCompat.getColor(this.mContext, giftDownloadItemEntity.isStayTuned ? R.color.fq_colorGray66 : R.color.fq_colorWhite)).setText(R.id.tv_item_desc, giftDownloadItemEntity.tomatoPrice).setVisible(R.id.tv_item_desc, giftDownloadItemEntity.isStayTuned ^ 1);
        int i = R.id.iv_label;
        if (!giftDownloadItemEntity.isStayTuned && giftDownloadItemEntity.isBroadcastFlag()) {
            z = true;
        }
        visible.setVisible(i, z).getView(R.id.ll_item_layout).setSelected(z2);
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_item_logo);
        if (TextUtils.isEmpty(giftDownloadItemEntity.imgurl)) {
            i.a(this.mContext, imageView, R.drawable.fq_live_giftlist_comingsoon);
        } else {
            i.b(this.mContext, imageView, giftDownloadItemEntity.imgurl, R.drawable.fq_ic_gift_default);
        }
    }

    public void setSelectedPosition(int i) {
        this.selectedPosition = i;
        notifyDataSetChanged();
    }

    public void clearSelectedPosition() {
        if (isPosition(this.selectedPosition)) {
            View viewByPosition = getViewByPosition(this.selectedPosition, R.id.ll_item_layout);
            if (viewByPosition != null) {
                viewByPosition.setSelected(false);
            }
        }
    }

    public int getSelectedPosition() {
        return this.selectedPosition;
    }

    private boolean isPosition(int i) {
        return i >= 0 && i < getData().size();
    }
}

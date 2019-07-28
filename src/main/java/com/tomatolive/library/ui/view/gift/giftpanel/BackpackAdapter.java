package com.tomatolive.library.ui.view.gift.giftpanel;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.gongwen.marqueen.b;
import com.tomatolive.library.R;
import com.tomatolive.library.model.BackpackItemEntity;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;
import java.util.ArrayList;
import java.util.List;

public class BackpackAdapter extends BaseQuickAdapter<BackpackItemEntity, BaseViewHolder> {
    private b<String> marqueeFactory;
    private int selectedPosition = -1;

    public BackpackAdapter(@Nullable List<BackpackItemEntity> list) {
        super(R.layout.fq_item_grid_bag, list);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, BackpackItemEntity backpackItemEntity) {
        int i = 0;
        boolean z = this.selectedPosition == baseViewHolder.getAdapterPosition();
        baseViewHolder.setText(R.id.tv_item_title, backpackItemEntity.name).setText(R.id.tv_item_desc, getRemainTime(backpackItemEntity)).setText(R.id.tv_count, formatCount(backpackItemEntity.count)).setVisible(R.id.tv_item_title, backpackItemEntity.isStayTuned ^ 1).setVisible(R.id.tv_count, backpackItemEntity.isStayTuned ^ 1).setVisible(R.id.iv_item_logo, 1 ^ backpackItemEntity.isStayTuned).getView(R.id.ll_item_layout).setSelected(z);
        if (!backpackItemEntity.isStayTuned) {
            i.b(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_item_logo), backpackItemEntity.coverUrl, R.drawable.fq_ic_gift_default);
        }
        TextView textView = (TextView) baseViewHolder.getView(R.id.tv_item_desc);
        SimpleMarqueeView simpleMarqueeView = (SimpleMarqueeView) baseViewHolder.getView(R.id.marqueeView);
        if (!z) {
            simpleMarqueeView.setVisibility(4);
            if (simpleMarqueeView.isFlipping()) {
                simpleMarqueeView.stopFlipping();
            }
            if (backpackItemEntity.isStayTuned) {
                i = 4;
            }
            textView.setVisibility(i);
        } else if (TextUtils.equals(backpackItemEntity.remainTime, "-1")) {
            textView.setVisibility(0);
            simpleMarqueeView.setVisibility(4);
            if (simpleMarqueeView.isFlipping()) {
                simpleMarqueeView.stopFlipping();
            }
        } else {
            textView.setVisibility(4);
            if (backpackItemEntity.isStayTuned) {
                i = 4;
            }
            simpleMarqueeView.setVisibility(i);
            this.marqueeFactory = new b(this.mContext);
            this.marqueeFactory.a(getMarqueeData(backpackItemEntity));
            simpleMarqueeView.setMarqueeFactory(this.marqueeFactory);
            simpleMarqueeView.startFlipping();
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

    public BackpackItemEntity getSelectedItem() {
        if (this.selectedPosition <= 0) {
            return null;
        }
        return (BackpackItemEntity) getItem(this.selectedPosition);
    }

    private String getRemainTime(BackpackItemEntity backpackItemEntity) {
        if (!TextUtils.equals(backpackItemEntity.remainTime, "-1")) {
            return d.d(p.b(backpackItemEntity.remainTime));
        }
        return this.mContext.getString(R.string.fq_props_add_exp, new Object[]{backpackItemEntity.exp});
    }

    private List<String> getMarqueeData(BackpackItemEntity backpackItemEntity) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getRemainTime(backpackItemEntity));
        arrayList.add(this.mContext.getString(R.string.fq_props_add_exp, new Object[]{backpackItemEntity.exp}));
        return arrayList;
    }

    private String formatCount(String str) {
        return p.a(str) > 99 ? "99+" : str;
    }

    private boolean isPosition(int i) {
        return i >= 0 && i < getData().size();
    }
}

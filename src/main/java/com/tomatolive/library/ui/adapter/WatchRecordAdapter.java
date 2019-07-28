package com.tomatolive.library.ui.adapter;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.db.WatchRecordEntity;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.u;

public class WatchRecordAdapter extends BaseQuickAdapter<WatchRecordEntity, BaseViewHolder> {
    public WatchRecordAdapter(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, WatchRecordEntity watchRecordEntity) {
        baseViewHolder.setText(R.id.tv_live_title, u.a(watchRecordEntity.title, 15)).setText(R.id.tv_anchor_nickname, watchRecordEntity.anchorNickname).setText(R.id.tv_live_label, watchRecordEntity.label).setText(R.id.tv_live_time, d.c(watchRecordEntity.liveTime * 1000)).setVisible(R.id.iv_living_label, false).addOnClickListener(R.id.rl_content).addOnClickListener(R.id.tv_delete);
        i.c(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_cover), watchRecordEntity.coverUrl, 6, R.drawable.fq_ic_placeholder_corners);
    }

    public void clearAll() {
        getData().clear();
        notifyDataSetChanged();
    }
}

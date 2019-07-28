package com.tomatolive.library.ui.adapter;

import android.text.TextUtils;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.u;

public class SearchLiveAdapter extends BaseQuickAdapter<LiveEntity, BaseViewHolder> {
    public SearchLiveAdapter(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, LiveEntity liveEntity) {
        baseViewHolder.setText(R.id.tv_live_title, u.a(liveEntity.topic, 15)).setText(R.id.tv_address, liveEntity.gpsAddress).setText(R.id.tv_personal, liveEntity.watchCount).setText(R.id.tv_nick_name, liveEntity.nickname).setVisible(R.id.rl_live_status, b.b(liveEntity.liveStatus) ^ 1).setVisible(R.id.tv_pay, b.a(liveEntity.free));
        i.b(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_cover), TextUtils.isEmpty(liveEntity.liveCoverUrl) ? liveEntity.avatar : liveEntity.liveCoverUrl, R.drawable.fq_ic_placeholder);
    }
}

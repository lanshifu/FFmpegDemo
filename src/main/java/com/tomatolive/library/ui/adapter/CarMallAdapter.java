package com.tomatolive.library.ui.adapter;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.CarEntity;
import com.tomatolive.library.utils.i;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation.CornerType;

public class CarMallAdapter extends BaseQuickAdapter<CarEntity, BaseViewHolder> {
    public CarMallAdapter(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, CarEntity carEntity) {
        baseViewHolder.setText(R.id.tv_name, carEntity.name).setText(R.id.tv_tips, carEntity.brief).setText(R.id.tv_times, this.mContext.getString(R.string.fq_count_day, new Object[]{"7"})).setText(R.id.tv_money, this.mContext.getString(R.string.fq_current_buy_gold, new Object[]{carEntity.weekGold})).setVisible(R.id.iv_label, carEntity.isPublicPermission() ^ 1).addOnClickListener(R.id.tv_money);
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_img), carEntity.imgUrl, 6, CornerType.TOP, R.drawable.fq_ic_placeholder_banner);
    }
}

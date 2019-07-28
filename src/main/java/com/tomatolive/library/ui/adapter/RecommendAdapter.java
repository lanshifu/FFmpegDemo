package com.tomatolive.library.ui.adapter;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.u;

public class RecommendAdapter extends BaseQuickAdapter<AnchorEntity, BaseViewHolder> {
    public RecommendAdapter(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, AnchorEntity anchorEntity) {
        baseViewHolder.setText(R.id.tv_nick_name, u.a(anchorEntity.nickname, 6)).setText(R.id.tv_attention, anchorEntity.isAttention() ? R.string.fq_home_btn_attention_yes : R.string.fq_home_btn_attention).setVisible(R.id.iv_live, b.b(anchorEntity.isLiving)).addOnClickListener(R.id.tv_attention).getView(R.id.tv_attention).setSelected(anchorEntity.isAttention());
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_avatar), anchorEntity.avatar);
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_live));
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_gender_sex);
        if (b.e(anchorEntity.sex) != -1) {
            imageView.setImageResource(b.e(anchorEntity.sex));
        }
    }
}

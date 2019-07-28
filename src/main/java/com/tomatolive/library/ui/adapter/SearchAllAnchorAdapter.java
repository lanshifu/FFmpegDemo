package com.tomatolive.library.ui.adapter;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.custom.AnchorGradeView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.u;

public class SearchAllAnchorAdapter extends BaseQuickAdapter<AnchorEntity, BaseViewHolder> {
    private String keyword = "";

    public SearchAllAnchorAdapter(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, AnchorEntity anchorEntity) {
        baseViewHolder.setText(R.id.tv_nick_name, u.a(this.mContext, u.a(anchorEntity.nickname, 6), this.keyword, R.color.fq_colorRed)).setText(R.id.tv_attention, this.mContext.getString(R.string.fq_text_attention_num, new Object[]{b.c(anchorEntity.followerCount)})).setVisible(R.id.iv_live, b.b(anchorEntity.isLiving));
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_live));
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_avatar), anchorEntity.avatar);
        ((AnchorGradeView) baseViewHolder.getView(R.id.anchor_grade_view)).initUserGrade(anchorEntity.expGrade);
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_gender_sex);
        if (b.e(anchorEntity.sex) != -1) {
            imageView.setImageResource(b.e(anchorEntity.sex));
        }
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }
}

package com.tomatolive.library.ui.adapter;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.custom.UserNickNameGradeView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.u;

public class SearchAnchorAdapter extends BaseQuickAdapter<AnchorEntity, BaseViewHolder> {
    private Fragment fragment;
    private String keyWord = "";

    public SearchAnchorAdapter(Fragment fragment, int i) {
        super(i);
        this.fragment = fragment;
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, AnchorEntity anchorEntity) {
        baseViewHolder.setText(R.id.tv_attention_num, this.mContext.getString(R.string.fq_text_attention_num, new Object[]{b.c(anchorEntity.followerCount)})).setVisible(R.id.iv_live, b.b(anchorEntity.isLiving)).addOnClickListener(R.id.tv_attention).getView(R.id.tv_attention).setSelected(anchorEntity.isAttention());
        ((UserNickNameGradeView) baseViewHolder.getView(R.id.user_name_grade_view)).initAnchorData(u.a(this.mContext, anchorEntity.nickname, this.keyWord, R.color.fq_colorRed), anchorEntity.sex, anchorEntity.expGrade);
        i.a(this.fragment, (ImageView) baseViewHolder.getView(R.id.iv_avatar), anchorEntity.avatar);
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_live));
    }

    public String getKeyWord() {
        return this.keyWord;
    }

    public void setKeyWord(String str) {
        this.keyWord = str;
    }
}

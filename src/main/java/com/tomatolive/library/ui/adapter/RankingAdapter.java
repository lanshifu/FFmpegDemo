package com.tomatolive.library.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.custom.UserNickNameGradeView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;

public class RankingAdapter extends BaseQuickAdapter<AnchorEntity, BaseViewHolder> {
    private boolean isDialog = false;
    private int type;

    private boolean isShowBrand(int i) {
        return i == 0 || i == 1 || i == 2;
    }

    public RankingAdapter(int i, int i2) {
        super(i);
        this.type = i2;
    }

    public RankingAdapter(int i, int i2, boolean z) {
        super(i);
        this.type = i2;
        this.isDialog = z;
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, AnchorEntity anchorEntity) {
        int adapterPosition = baseViewHolder.getAdapterPosition();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(adapterPosition + 1);
        String stringBuilder2 = stringBuilder.toString();
        baseViewHolder.setText(R.id.tv_diamond, getDiamondStr(anchorEntity)).setVisible(R.id.tv_attention, this.type == 4).setVisible(R.id.iv_live, b.b(anchorEntity.liveStatus)).setBackgroundRes(R.id.fl_avatar_bg, getAvatarBgResId(adapterPosition)).setImageResource(R.id.iv_brand, getBrandResId(adapterPosition)).setVisible(R.id.iv_brand, isShowBrand(adapterPosition)).setVisible(R.id.tv_num, isShowBrand(adapterPosition) ^ 1).setVisible(R.id.iv_live, b.b(anchorEntity.liveStatus)).addOnClickListener(R.id.tv_attention).getView(R.id.tv_attention).setSelected(anchorEntity.isAttention());
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_avatar), anchorEntity.avatar);
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_live));
        b.a(this.mContext, (TextView) baseViewHolder.getView(R.id.tv_num), stringBuilder2);
        UserNickNameGradeView userNickNameGradeView = (UserNickNameGradeView) baseViewHolder.getView(R.id.user_nickname);
        if (this.type == 4) {
            userNickNameGradeView.initAnchorData(anchorEntity.nickname, this.isDialog ? R.color.fq_colorWhite : R.color.fq_colorBlack, anchorEntity.sex, anchorEntity.expGrade);
        } else {
            userNickNameGradeView.initData(anchorEntity.nickname, this.isDialog ? R.color.fq_colorWhite : R.color.fq_colorBlack, anchorEntity.sex, anchorEntity.expGrade);
        }
    }

    public void setType(int i) {
        this.type = i;
        notifyDataSetChanged();
    }

    private String getDiamondStr(AnchorEntity anchorEntity) {
        if (anchorEntity == null) {
            return "";
        }
        Context context;
        int i;
        String str = this.type == 4 ? anchorEntity.income : anchorEntity.expend;
        if (this.type == 4) {
            context = this.mContext;
            i = R.string.fq_tomato_money_gain;
        } else {
            context = this.mContext;
            i = R.string.fq_tomato_money_reward;
        }
        String string = context.getString(i);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.append(str);
        stringBuilder.append(b.b(this.mContext));
        return stringBuilder.toString();
    }

    private int getAvatarBgResId(int i) {
        switch (i) {
            case 0:
                return R.drawable.fq_shape_top_tag_gold_circle;
            case 1:
                return R.drawable.fq_shape_top_tag_silver_circle;
            case 2:
                return R.drawable.fq_shape_top_tag_copper_circle;
            default:
                return R.drawable.fq_shape_top_tag_gray_circle;
        }
    }

    private int getBrandResId(int i) {
        switch (i) {
            case 0:
                return R.drawable.fq_ic_top_brand_no_1;
            case 1:
                return R.drawable.fq_ic_top_brand_no_2;
            case 2:
                return R.drawable.fq_ic_top_brand_no_3;
            default:
                return R.drawable.fq_ic_top_brand_no_3;
        }
    }
}

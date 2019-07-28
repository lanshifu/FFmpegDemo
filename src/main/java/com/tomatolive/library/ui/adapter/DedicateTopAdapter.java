package com.tomatolive.library.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.custom.UserNickNameGradeView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;

public class DedicateTopAdapter extends BaseQuickAdapter<AnchorEntity, BaseViewHolder> {
    private boolean isDialog = false;

    private boolean isShowBrand(int i) {
        return i == 0 || i == 1 || i == 2;
    }

    public DedicateTopAdapter(int i) {
        super(i);
    }

    public DedicateTopAdapter(int i, boolean z) {
        super(i);
        this.isDialog = z;
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, AnchorEntity anchorEntity) {
        int adapterPosition = baseViewHolder.getAdapterPosition();
        baseViewHolder.setText(R.id.tv_income, this.mContext.getString(R.string.fq_my_live_income_num, new Object[]{anchorEntity.expend, b.b(this.mContext)})).setBackgroundRes(R.id.fl_avatar_bg, getAvatarBgResId(adapterPosition)).setImageResource(R.id.iv_brand, getBrandResId(adapterPosition)).setVisible(R.id.iv_brand, isShowBrand(adapterPosition)).setVisible(R.id.tv_num, isShowBrand(adapterPosition) ^ 1);
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_avatar), anchorEntity.avatar);
        ((UserNickNameGradeView) baseViewHolder.getView(R.id.user_nickname)).initData(anchorEntity.nickname, this.isDialog ? R.color.fq_colorWhite : R.color.fq_colorBlack, anchorEntity.sex, anchorEntity.expGrade);
        b.a(this.mContext, (TextView) baseViewHolder.getView(R.id.tv_num), getNumStr(adapterPosition));
    }

    private String getNumStr(int i) {
        StringBuilder stringBuilder;
        if (i >= 3) {
            i++;
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(i);
            return stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(i);
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

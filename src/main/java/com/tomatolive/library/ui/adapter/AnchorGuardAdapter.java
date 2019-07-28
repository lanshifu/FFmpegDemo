package com.tomatolive.library.ui.adapter;

import android.text.TextUtils;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.custom.UserGradeView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;

public class AnchorGuardAdapter extends BaseQuickAdapter<AnchorEntity, BaseViewHolder> {
    public AnchorGuardAdapter(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, AnchorEntity anchorEntity) {
        baseViewHolder.setText(R.id.tv_name, anchorEntity.nickname).setText(R.id.tv_money, formatMoney(anchorEntity.contribution)).setVisible(R.id.iv_guard_type, TextUtils.equals(anchorEntity.guardType, "0") ^ 1).setImageResource(R.id.iv_guard_type, TextUtils.equals(anchorEntity.guardType, "3") ? R.drawable.fq_ic_live_msg_year_guard : R.drawable.fq_ic_live_msg_mouth_guard).setImageResource(R.id.iv_gender, b.e(anchorEntity.sex));
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_avatar), anchorEntity.avatar);
        ((UserGradeView) baseViewHolder.getView(R.id.user_grade)).initUserGrade(anchorEntity.expGrade);
    }

    private String formatMoney(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(" ");
        stringBuffer.append(b.b(this.mContext));
        return stringBuffer.toString();
    }
}

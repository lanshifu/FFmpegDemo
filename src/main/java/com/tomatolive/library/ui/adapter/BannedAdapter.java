package com.tomatolive.library.ui.adapter;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.BannedEntity;
import com.tomatolive.library.ui.view.custom.UserGradeView;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.i;

public class BannedAdapter extends BaseQuickAdapter<BannedEntity, BaseViewHolder> {
    public BannedAdapter(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, BannedEntity bannedEntity) {
        baseViewHolder.setText(R.id.tv_nick_name, bannedEntity.name).setText(R.id.tv_time, this.mContext.getString(R.string.fq_banned_time_to, new Object[]{d.c(bannedEntity.duration)})).setText(R.id.tv_banned, this.mContext.getString(bannedEntity.isBanned() ? R.string.btn_cancel_banned : R.string.btn_banned)).setVisible(R.id.tv_time, bannedEntity.isBanned()).addOnClickListener(R.id.tv_banned).getView(R.id.tv_banned).setSelected(bannedEntity.isBanned());
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_avatar), bannedEntity.avatar);
        ((UserGradeView) baseViewHolder.getView(R.id.user_grade)).initUserGrade(bannedEntity.expGrade);
    }
}

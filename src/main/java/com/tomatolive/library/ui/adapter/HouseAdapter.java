package com.tomatolive.library.ui.adapter;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.BannedEntity;
import com.tomatolive.library.ui.view.custom.UserGradeView;
import com.tomatolive.library.utils.i;

public class HouseAdapter extends BaseQuickAdapter<BannedEntity, BaseViewHolder> {
    private boolean isSearch;

    public HouseAdapter(int i, boolean z) {
        super(i);
        this.isSearch = z;
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, BannedEntity bannedEntity) {
        baseViewHolder.setText(R.id.tv_nick_name, bannedEntity.name).setText(R.id.tv_banned, this.mContext.getString(bannedEntity.isHouseManager() ? R.string.fq_my_live_house_manager_cancel : R.string.fq_my_live_house_manager)).setText(R.id.tv_time, this.mContext.getString(R.string.fq_appointment_time, new Object[]{bannedEntity.createTime})).setText(R.id.tv_last_time, getLastTime(bannedEntity)).setVisible(R.id.tv_time, this.isSearch ^ 1).setVisible(R.id.tv_last_time, this.isSearch ^ 1).addOnClickListener(R.id.tv_banned).getView(R.id.tv_banned).setSelected(bannedEntity.isHouseManager());
        i.a(this.mContext, (ImageView) baseViewHolder.getView(R.id.iv_avatar), bannedEntity.avatar);
        ((UserGradeView) baseViewHolder.getView(R.id.user_grade)).initUserGrade(bannedEntity.expGrade);
    }

    private Spanned getLastTime(BannedEntity bannedEntity) {
        if (TextUtils.equals(bannedEntity.lastEnterTime, "不活跃")) {
            return Html.fromHtml(this.mContext.getString(R.string.fq_last_join_live_time_2, new Object[]{bannedEntity.lastEnterTime}));
        }
        return Html.fromHtml(this.mContext.getString(R.string.fq_last_join_live_time, new Object[]{bannedEntity.lastEnterTime}));
    }
}

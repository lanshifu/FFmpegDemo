package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.custom.UserNickNameGradeView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;

public class AnchorAvatarDialog extends BaseDialogFragment {
    private AnchorEntity anchorEntity;
    private int liveType = 1;
    private OnClickListener onBtnClickListener;
    private TextView tvFansNum;
    private TextView tvManage;

    @SuppressLint({"ValidFragment"})
    private AnchorAvatarDialog() {
    }

    public static AnchorAvatarDialog newInstance(AnchorEntity anchorEntity, int i, OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        AnchorAvatarDialog anchorAvatarDialog = new AnchorAvatarDialog();
        anchorAvatarDialog.setArguments(bundle);
        anchorAvatarDialog.setAnchorEntity(anchorEntity);
        anchorAvatarDialog.setLiveType(i);
        anchorAvatarDialog.setOnBtnClickListener(onClickListener);
        return anchorAvatarDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_anchor_avatar;
    }

    public void initView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_avatar);
        UserNickNameGradeView userNickNameGradeView = (UserNickNameGradeView) view.findViewById(R.id.user_nickname);
        TextView textView = (TextView) view.findViewById(R.id.tv_id);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_tips);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_attention_num);
        this.tvFansNum = (TextView) view.findViewById(R.id.tv_fans_num);
        this.tvManage = (TextView) view.findViewById(R.id.tv_manage);
        if (this.anchorEntity != null) {
            i.a(getActivity(), imageView, this.anchorEntity.avatar, 6, ContextCompat.getColor(this.mContext, R.color.fq_colorWhite));
            textView.setText(getString(R.string.fq_room_id, new Object[]{this.anchorEntity.liveId}));
            textView2.setText(R.string.fq_avatar_dialog_sign_tips);
            textView3.setText(TextUtils.isEmpty(this.anchorEntity.followAnchorCount) ? "0" : this.anchorEntity.followAnchorCount);
            this.tvFansNum.setText(TextUtils.isEmpty(this.anchorEntity.followerCount) ? "0" : this.anchorEntity.followerCount);
            userNickNameGradeView.initAnchorData(this.anchorEntity.nickname, this.anchorEntity.sex, this.anchorEntity.expGrade);
            if (this.liveType == 1) {
                this.tvManage.setVisibility(8);
                this.tvManage.setText(R.string.fq_mangers_manger);
                return;
            }
            this.tvManage.setVisibility(0);
            this.tvManage.setText(this.anchorEntity.isAttention() ? R.string.fq_home_btn_attention_yes : R.string.fq_home_btn_attention);
            this.tvManage.setSelected(this.anchorEntity.isAttention());
        }
    }

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
        super.initListener(view);
        view.findViewById(R.id.iv_close).setOnClickListener(new -$$Lambda$AnchorAvatarDialog$TDFCqnKTMrfYimshh_pA8A1V57Y(this));
        this.tvManage.setOnClickListener(new -$$Lambda$AnchorAvatarDialog$S5XyFG31s67S4hsU_jT02JsWaOE(this));
    }

    public static /* synthetic */ void lambda$initListener$1(AnchorAvatarDialog anchorAvatarDialog, View view) {
        if (anchorAvatarDialog.onBtnClickListener != null && anchorAvatarDialog.anchorEntity != null && b.a(anchorAvatarDialog.getContext(), anchorAvatarDialog.anchorEntity.userId)) {
            int a;
            if (anchorAvatarDialog.anchorEntity.isAttention()) {
                a = p.a(anchorAvatarDialog.anchorEntity.followerCount) - 1;
            } else {
                a = p.a(anchorAvatarDialog.anchorEntity.followerCount) + 1;
            }
            if (a < 0) {
                a = 0;
            }
            anchorAvatarDialog.anchorEntity.followerCount = String.valueOf(a);
            anchorAvatarDialog.tvManage.setText(anchorAvatarDialog.anchorEntity.isAttention() ? R.string.fq_home_btn_attention : R.string.fq_home_btn_attention_yes);
            anchorAvatarDialog.tvFansNum.setText(anchorAvatarDialog.anchorEntity.followerCount);
            anchorAvatarDialog.onBtnClickListener.onClick(view);
        }
    }

    public AnchorEntity getAnchorEntity() {
        return this.anchorEntity;
    }

    private void setAnchorEntity(AnchorEntity anchorEntity) {
        this.anchorEntity = anchorEntity;
    }

    public int getLiveType() {
        return this.liveType;
    }

    private void setLiveType(int i) {
        this.liveType = i;
    }

    public OnClickListener getOnBtnClickListener() {
        return this.onBtnClickListener;
    }

    private void setOnBtnClickListener(OnClickListener onClickListener) {
        this.onBtnClickListener = onClickListener;
    }
}

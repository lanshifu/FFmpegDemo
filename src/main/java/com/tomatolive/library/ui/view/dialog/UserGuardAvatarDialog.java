package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.custom.UserNickNameGradeView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;

public class UserGuardAvatarDialog extends BaseDialogFragment implements OnClickListener {
    private static final String AUDIENCEEXPGRADE = "audienceExpGrade";
    private static final String AVATAR = "avatar";
    private static final String GUARD_TYPE = "targetGuardType";
    private static final String MANAGER = "manager";
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String SEX = "sex";
    private static final String TIPS = "tips";
    private static final String USER_ID = "id";
    private OnClickListener attentionListener;
    private OnClickListener reportListener;

    @SuppressLint({"ValidFragment"})
    private UserGuardAvatarDialog() {
    }

    public static UserGuardAvatarDialog newInstance(String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i, OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        bundle.putString(USER_ID, str);
        bundle.putString(AVATAR, str2);
        bundle.putString("name", str3);
        bundle.putString(SEX, str4);
        bundle.putString(TIPS, str5);
        bundle.putBoolean(MANAGER, z);
        bundle.putString(ROLE, str6);
        bundle.putString(AUDIENCEEXPGRADE, str7);
        bundle.putInt(GUARD_TYPE, i);
        UserGuardAvatarDialog userGuardAvatarDialog = new UserGuardAvatarDialog();
        userGuardAvatarDialog.setArguments(bundle);
        userGuardAvatarDialog.setReportListener(onClickListener);
        return userGuardAvatarDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_live_user_guard_avatar;
    }

    public void initView(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_dialog_bg);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_avatar);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.iv_avatar_bg);
        UserNickNameGradeView userNickNameGradeView = (UserNickNameGradeView) view.findViewById(R.id.user_nickname);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.iv_idCard);
        TextView textView = (TextView) view.findViewById(R.id.tv_id);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_tips);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_ta);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.iv_manage);
        ImageView imageView5 = (ImageView) view.findViewById(R.id.iv_guard_type);
        textView3.setText(" ");
        if (b.j(getArgumentsString(ROLE))) {
            imageView3.setVisibility(0);
            imageView3.setImageResource(R.drawable.fq_ic_live_msg_manager_big_2);
        }
        if (getArguments().getBoolean(MANAGER, false)) {
            imageView4.setVisibility(0);
            imageView4.setOnClickListener(new -$$Lambda$UserGuardAvatarDialog$ZUfoII7VmmIbpvASsGukZ8RrUKY(this));
        } else {
            imageView4.setOnClickListener(null);
            imageView4.setVisibility(4);
        }
        relativeLayout.setBackgroundResource(b.d(getArgumentsInt(GUARD_TYPE)) ? R.drawable.fq_ic_dialog_bg_guard_year : R.drawable.fq_ic_dialog_bg_guard_month);
        imageView2.setImageResource(b.d(getArgumentsInt(GUARD_TYPE)) ? R.drawable.fq_ic_guard_year_avatar_bg_big : R.drawable.fq_ic_guard_month_avatar_bg_big);
        imageView5.setImageResource(b.d(getArgumentsInt(GUARD_TYPE)) ? R.drawable.fq_ic_live_msg_year_guard : R.drawable.fq_ic_live_msg_mouth_guard);
        i.a(getActivity(), imageView, getArgumentsString(AVATAR), R.drawable.fq_ic_placeholder_avatar);
        textView.setText(getString(R.string.fq_live_room_id, new Object[]{getArgumentsString(USER_ID)}));
        textView2.setText(R.string.fq_avatar_dialog_sign_tips);
        userNickNameGradeView.initData(getArgumentsString("name"), getArgumentsString(SEX), getArgumentsString(AUDIENCEEXPGRADE));
    }

    public static /* synthetic */ void lambda$initView$0(UserGuardAvatarDialog userGuardAvatarDialog, View view) {
        if (userGuardAvatarDialog.reportListener != null) {
            userGuardAvatarDialog.reportListener.onClick(view);
        }
    }

    public void onClick(View view) {
        dismiss();
    }

    public OnClickListener getReportListener() {
        return this.reportListener;
    }

    private void setReportListener(OnClickListener onClickListener) {
        this.reportListener = onClickListener;
    }

    public OnClickListener getAttentionListener() {
        return this.attentionListener;
    }

    private void setAttentionListener(OnClickListener onClickListener) {
        this.attentionListener = onClickListener;
    }
}

package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.custom.UserNickNameGradeView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;

public class UserSuperAvatarDialog extends BaseDialogFragment implements OnClickListener {
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
    private UserSuperAvatarDialog() {
    }

    public static UserSuperAvatarDialog newInstance(String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i, OnClickListener onClickListener, OnClickListener onClickListener2) {
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
        UserSuperAvatarDialog userSuperAvatarDialog = new UserSuperAvatarDialog();
        userSuperAvatarDialog.setArguments(bundle);
        userSuperAvatarDialog.setReportListener(onClickListener);
        userSuperAvatarDialog.setAttentionListener(onClickListener2);
        return userSuperAvatarDialog;
    }

    public static UserSuperAvatarDialog newInstance(String str, String str2, String str3, String str4, String str5, String str6, int i) {
        Bundle bundle = new Bundle();
        bundle.putString(USER_ID, str);
        bundle.putString(AVATAR, str2);
        bundle.putString("name", str3);
        bundle.putString(SEX, str4);
        bundle.putString(TIPS, str5);
        bundle.putString(AUDIENCEEXPGRADE, str6);
        bundle.putInt(GUARD_TYPE, i);
        UserSuperAvatarDialog userSuperAvatarDialog = new UserSuperAvatarDialog();
        userSuperAvatarDialog.setArguments(bundle);
        return userSuperAvatarDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_live_super_avatar;
    }

    public void initView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_avatar);
        UserNickNameGradeView userNickNameGradeView = (UserNickNameGradeView) view.findViewById(R.id.user_nickname);
        TextView textView = (TextView) view.findViewById(R.id.tv_id);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_tips);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.iv_guard_type);
        view.findViewById(R.id.tv_attention).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (UserSuperAvatarDialog.this.attentionListener != null) {
                    UserSuperAvatarDialog.this.attentionListener.onClick(view);
                }
            }
        });
        i.a(getActivity(), imageView, getArgumentsString(AVATAR), 6, ContextCompat.getColor(this.mContext, R.color.fq_colorWhite));
        textView.setText(getString(R.string.fq_live_room_id, new Object[]{getArgumentsString(USER_ID)}));
        textView2.setText(R.string.fq_avatar_dialog_sign_tips);
        userNickNameGradeView.initData(getArgumentsString("name"), R.color.fq_text_white_color, getArgumentsString(SEX), getArgumentsString(AUDIENCEEXPGRADE));
        if (b.c(getArgumentsInt(GUARD_TYPE))) {
            imageView2.setImageResource(b.d(getArgumentsInt(GUARD_TYPE)) ? R.drawable.fq_ic_live_msg_year_guard : R.drawable.fq_ic_live_msg_mouth_guard);
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

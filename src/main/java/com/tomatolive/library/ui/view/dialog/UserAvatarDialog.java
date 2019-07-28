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

public class UserAvatarDialog extends BaseDialogFragment implements OnClickListener {
    private static final String AUDIENCEEXPGRADE = "audienceExpGrade";
    private static final String AVATAR = "avatar";
    private static final String MANAGER = "manager";
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String SEX = "sex";
    private static final String TIPS = "tips";
    private static final String USER_ID = "id";
    private OnClickListener attentionListener;
    private OnClickListener reportListener;

    @SuppressLint({"ValidFragment"})
    private UserAvatarDialog() {
    }

    public static UserAvatarDialog newInstance(String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Bundle bundle = new Bundle();
        bundle.putString(USER_ID, str);
        bundle.putString(AVATAR, str2);
        bundle.putString("name", str3);
        bundle.putString(SEX, str4);
        bundle.putString(TIPS, str5);
        bundle.putBoolean(MANAGER, z);
        bundle.putString(ROLE, str6);
        bundle.putString(AUDIENCEEXPGRADE, str7);
        UserAvatarDialog userAvatarDialog = new UserAvatarDialog();
        userAvatarDialog.setArguments(bundle);
        userAvatarDialog.setReportListener(onClickListener);
        userAvatarDialog.setAttentionListener(onClickListener2);
        return userAvatarDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_live_user_avatar;
    }

    public void initView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_avatar);
        UserNickNameGradeView userNickNameGradeView = (UserNickNameGradeView) view.findViewById(R.id.user_nickname);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.iv_idCard);
        if (b.j(getArgumentsString(ROLE))) {
            imageView2.setVisibility(0);
            imageView2.setImageResource(R.drawable.fq_ic_live_msg_manager_big_2);
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_id);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_tips);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_manage);
        if (getArguments().getBoolean(MANAGER, false)) {
            textView3.setVisibility(0);
            textView3.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (UserAvatarDialog.this.reportListener != null) {
                        UserAvatarDialog.this.reportListener.onClick(view);
                    }
                }
            });
        } else {
            textView3.setOnClickListener(null);
            textView3.setVisibility(4);
        }
        view.findViewById(R.id.iv_close).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UserAvatarDialog.this.dismiss();
            }
        });
        view.findViewById(R.id.tv_attention).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (UserAvatarDialog.this.attentionListener != null) {
                    UserAvatarDialog.this.attentionListener.onClick(view);
                }
            }
        });
        i.a(getActivity(), imageView, getArgumentsString(AVATAR), 6, ContextCompat.getColor(this.mContext, R.color.fq_colorWhite));
        textView.setText(getString(R.string.fq_live_room_id, new Object[]{getArgumentsString(USER_ID)}));
        textView2.setText(R.string.fq_avatar_dialog_sign_tips);
        userNickNameGradeView.initData(getArgumentsString("name"), getArgumentsString(SEX), getArgumentsString(AUDIENCEEXPGRADE));
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

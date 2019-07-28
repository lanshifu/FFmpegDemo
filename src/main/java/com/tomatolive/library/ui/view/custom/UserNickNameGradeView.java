package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.utils.b;

public class UserNickNameGradeView extends RelativeLayout {
    private AnchorGradeView anchorGradeView;
    private ImageView ivGender;
    private Context mContext;
    private TextView tvNickName;
    private UserGradeView userGradeView;

    public UserNickNameGradeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView();
    }

    private void initView() {
        inflate(this.mContext, R.layout.fq_layout_user_nickname_grade, this);
        this.tvNickName = (TextView) findViewById(R.id.tv_nick_name);
        this.userGradeView = (UserGradeView) findViewById(R.id.grade_view);
        this.anchorGradeView = (AnchorGradeView) findViewById(R.id.anchor_grade_view);
        this.ivGender = (ImageView) findViewById(R.id.iv_gender);
    }

    public void initData(String str, String str2, String str3) {
        this.userGradeView.setVisibility(0);
        this.anchorGradeView.setVisibility(8);
        this.tvNickName.setText(str);
        if (b.e(str2) != -1) {
            this.ivGender.setImageResource(b.e(str2));
        }
        this.userGradeView.initUserGrade(str3);
    }

    public void initData(String str, @ColorRes int i, String str2, String str3) {
        this.tvNickName.setTextColor(ContextCompat.getColor(this.mContext, i));
        initData(str, str2, str3);
    }

    public void initAnchorData(String str, String str2, String str3) {
        this.userGradeView.setVisibility(8);
        this.anchorGradeView.setVisibility(0);
        this.tvNickName.setText(str);
        if (b.e(str2) != -1) {
            this.ivGender.setImageResource(b.e(str2));
        }
        this.anchorGradeView.initUserGrade(str3);
    }

    public void initAnchorData(SpannableString spannableString, String str, String str2) {
        this.userGradeView.setVisibility(8);
        this.anchorGradeView.setVisibility(0);
        this.tvNickName.setText(spannableString);
        if (b.e(str) != -1) {
            this.ivGender.setImageResource(b.e(str));
        }
        this.anchorGradeView.initUserGrade(str2);
    }

    public void initAnchorData(String str, @ColorRes int i, String str2, String str3) {
        this.tvNickName.setTextColor(ContextCompat.getColor(this.mContext, i));
        initAnchorData(str, str2, str3);
    }
}

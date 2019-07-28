package com.tomatolive.library.ui.view.headview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.view.custom.UserGradeView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;
import java.util.List;

public class GuardListHeadView extends LinearLayout {
    private ImageView ivAvatar;
    private ImageView ivGender;
    private Context mContext;
    private TextView tvMoney;
    private TextView tvName;
    private UserGradeView userGrade;

    public GuardListHeadView(Context context) {
        super(context);
        initView(context);
    }

    public GuardListHeadView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        inflate(context, R.layout.fq_layout_head_view_guard_list, this);
        this.ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        this.tvName = (TextView) findViewById(R.id.tv_name);
        this.tvMoney = (TextView) findViewById(R.id.tv_money);
        this.ivGender = (ImageView) findViewById(R.id.iv_gender);
        this.userGrade = (UserGradeView) findViewById(R.id.user_grade);
    }

    public void initData(List<AnchorEntity> list) {
        if (list != null && list.size() > 0) {
            AnchorEntity anchorEntity = (AnchorEntity) list.get(0);
            if (p.a(anchorEntity.contribution, 0) == 0) {
                this.tvName.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_text_gray));
                this.tvName.setText(R.string.fq_text_list_empty_waiting);
                this.ivAvatar.setImageResource(R.drawable.fq_ic_guard_sofa);
                this.tvMoney.setText("");
                this.ivGender.setVisibility(4);
                this.userGrade.setVisibility(4);
                return;
            }
            this.ivGender.setVisibility(0);
            this.userGrade.setVisibility(0);
            this.tvName.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_text_black));
            i.a(this.mContext, this.ivAvatar, anchorEntity.avatar, R.drawable.fq_ic_placeholder_avatar);
            this.tvName.setText(anchorEntity.nickname);
            this.ivGender.setImageResource(b.e(anchorEntity.sex));
            this.tvMoney.setText(formatMoney(anchorEntity.contribution));
            this.userGrade.initUserGrade(anchorEntity.expGrade);
        }
    }

    private String formatMoney(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(" ");
        stringBuffer.append(b.b(this.mContext));
        return stringBuffer.toString();
    }
}

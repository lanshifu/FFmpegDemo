package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.p;

public class UserGradeView extends RelativeLayout {
    private boolean isSmallIcon;
    private boolean isWhiteStroke;
    private Context mContext;
    private TextView tvGrade;

    public UserGradeView(Context context) {
        this(context, null);
    }

    public UserGradeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isWhiteStroke = false;
        this.isSmallIcon = false;
        this.mContext = context;
        initView();
    }

    private void initView() {
        inflate(this.mContext, R.layout.fq_layout_user_grade, this);
        this.tvGrade = (TextView) findViewById(R.id.tv_grade);
        b.a(this.mContext, this.tvGrade, "");
    }

    public void initUserGrade(String str) {
        initUserGrade(p.a(b.h(str), 1));
    }

    public void initUserGradeMsg(String str, boolean z) {
        this.isWhiteStroke = z;
        initUserGrade(p.a(b.h(str), 1));
    }

    public void initUserGrade(int i) {
        this.tvGrade.setBackgroundResource(b.a(this.isWhiteStroke, i));
        setTextViewDrawable(b.b(this.isSmallIcon, i));
        this.tvGrade.setText(String.valueOf(i));
    }

    private void setTextViewDrawable(@DrawableRes int i) {
        this.tvGrade.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.mContext, i), null, null, null);
    }
}

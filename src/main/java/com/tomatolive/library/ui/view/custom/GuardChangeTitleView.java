package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.model.GuardItemEntity;
import com.tomatolive.library.utils.b;

public class GuardChangeTitleView extends RelativeLayout {
    private GuardItemEntity guardItemEntity;
    private ImageView ivArrow;
    private ImageView ivLabel;
    private Context mContext;
    private TextView tvMoney;
    private TextView tvTitle;

    public GuardChangeTitleView(Context context) {
        super(context);
        initView(context);
    }

    public GuardChangeTitleView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.fq_layout_guard_change_title_view, this);
        this.mContext = context;
        this.ivLabel = (ImageView) findViewById(R.id.iv_label);
        this.ivArrow = (ImageView) findViewById(R.id.iv_arrow);
        this.tvTitle = (TextView) findViewById(R.id.tv_guard_title);
        this.tvMoney = (TextView) findViewById(R.id.tv_guard_money);
    }

    public void initData(GuardItemEntity guardItemEntity, String str) {
        this.guardItemEntity = guardItemEntity;
        if (guardItemEntity != null) {
            this.guardItemEntity.anchorId = str;
            this.ivLabel.setVisibility(TextUtils.equals(guardItemEntity.type, "1") ? 0 : 4);
            this.tvTitle.setText(guardItemEntity.name);
            b.a(this.mContext, this.tvMoney, guardItemEntity.tomatoPrice);
            Drawable drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_guard_year_label);
            TextView textView = this.tvTitle;
            if (!TextUtils.equals(guardItemEntity.type, "3")) {
                drawable = null;
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
    }

    public void showArrow(boolean z) {
        this.ivArrow.setVisibility(z ? 0 : 4);
    }

    private java.lang.String getTitleStr(java.lang.String r2) {
        /*
        r1 = this;
        r0 = r2.hashCode();
        switch(r0) {
            case 49: goto L_0x001c;
            case 50: goto L_0x0012;
            case 51: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0026;
    L_0x0008:
        r0 = "3";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0026;
    L_0x0010:
        r2 = 2;
        goto L_0x0027;
    L_0x0012:
        r0 = "2";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0026;
    L_0x001a:
        r2 = 1;
        goto L_0x0027;
    L_0x001c:
        r0 = "1";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0026;
    L_0x0024:
        r2 = 0;
        goto L_0x0027;
    L_0x0026:
        r2 = -1;
    L_0x0027:
        switch(r2) {
            case 0: goto L_0x0045;
            case 1: goto L_0x003c;
            case 2: goto L_0x0033;
            default: goto L_0x002a;
        };
    L_0x002a:
        r2 = r1.mContext;
        r0 = com.tomatolive.library.R.string.fq_guard_week;
        r2 = r2.getString(r0);
        return r2;
    L_0x0033:
        r2 = r1.mContext;
        r0 = com.tomatolive.library.R.string.fq_guard_year;
        r2 = r2.getString(r0);
        return r2;
    L_0x003c:
        r2 = r1.mContext;
        r0 = com.tomatolive.library.R.string.fq_guard_month;
        r2 = r2.getString(r0);
        return r2;
    L_0x0045:
        r2 = r1.mContext;
        r0 = com.tomatolive.library.R.string.fq_guard_week;
        r2 = r2.getString(r0);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.custom.GuardChangeTitleView.getTitleStr(java.lang.String):java.lang.String");
    }

    public GuardItemEntity getGuardItemEntity() {
        return this.guardItemEntity;
    }

    public boolean isSelected() {
        return this.ivArrow.getVisibility() == 0;
    }
}

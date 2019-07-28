package com.tomatolive.library.ui.view.emptyview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tomatolive.library.R;

public class RecyclerIncomeEmptyView extends LinearLayout {
    private int emptyType;
    private Context mContext;
    private TextView tvText;

    public RecyclerIncomeEmptyView(Context context) {
        this(context, null, 0);
    }

    public RecyclerIncomeEmptyView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public RecyclerIncomeEmptyView(Context context, int i) {
        this(context, null, i);
    }

    public RecyclerIncomeEmptyView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, 0, i);
    }

    public RecyclerIncomeEmptyView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.emptyType = i2;
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.fq_layout_empty_view_income, this);
        this.tvText = (TextView) findViewById(R.id.tv_empty_text);
        this.tvText.setText(getEmptyText());
        this.tvText.setTextColor(ContextCompat.getColor(this.mContext, getTextColor()));
        setDrawableTop();
    }

    private String getEmptyText() {
        switch (this.emptyType) {
            case 32:
                return this.mContext.getString(R.string.fq_text_list_empty_income);
            case 33:
                return this.mContext.getString(R.string.fq_text_list_empty_top);
            case 35:
                return this.mContext.getString(R.string.fq_text_list_empty_house_manager);
            case 36:
                return this.mContext.getString(R.string.fq_text_list_empty_banned_setting);
            case 37:
                return this.mContext.getString(R.string.fq_text_list_empty_consume);
            case 38:
                return this.mContext.getString(R.string.fq_text_list_empty_top);
            case 39:
                return this.mContext.getString(R.string.fq_text_list_empty_watch_record);
            case 40:
                return this.mContext.getString(R.string.fq_gift_empty_prompt);
            case 41:
                return this.mContext.getString(R.string.fq_car_list_empty_tips);
            case 42:
                return this.mContext.getString(R.string.fq_text_list_empty_waiting);
            default:
                return this.mContext.getString(R.string.fq_text_list_empty);
        }
    }

    private void setDrawableTop() {
        Drawable drawable;
        switch (this.emptyType) {
            case 32:
            case 37:
                drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_empty_income);
                break;
            case 33:
                drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_empty_top);
                break;
            case 34:
                drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_empty_head);
                break;
            case 35:
            case 36:
                drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_empty_head);
                break;
            case 38:
                drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_empty_top_gray_2);
                break;
            case 39:
                drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_empty_watch_record);
                break;
            case 40:
                drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_empty_gift_record);
                break;
            case 41:
                drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_empty_car_record);
                break;
            case 42:
                drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_empty_top);
                break;
            default:
                drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_ic_empty_income);
                break;
        }
        this.tvText.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
    }

    @ColorRes
    private int getTextColor() {
        return (this.emptyType == 40 || this.emptyType == 38) ? R.color.fq_gray66 : R.color.fq_text_gray;
    }
}

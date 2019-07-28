package com.tomatolive.library.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.n;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.IncomeEntity;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.u;
import org.slf4j.Marker;

public class IncomeDetailAdapter extends BaseQuickAdapter<IncomeEntity, BaseViewHolder> {
    private boolean isExpend = false;
    private int type;

    public IncomeDetailAdapter(int i, int i2) {
        super(i);
        this.type = i2;
    }

    public IncomeDetailAdapter(int i, int i2, boolean z) {
        super(i);
        this.type = i2;
        this.isExpend = z;
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, IncomeEntity incomeEntity) {
        Context context;
        int i;
        Context context2;
        Object[] objArr;
        BaseViewHolder text = baseViewHolder.setText(R.id.tv_reward_tips, formatTips(incomeEntity)).setText(R.id.tv_reward_time, n.a(Long.parseLong(incomeEntity.incomeTime) * 1000)).setText(R.id.tv_count, formatCount(incomeEntity.incomeCount));
        int i2 = R.id.tv_reward_tips;
        if (this.isExpend || this.type == 4) {
            context = this.mContext;
            i = R.color.fq_text_black;
        } else {
            context = this.mContext;
            i = R.color.fq_colorRed;
        }
        text.setTextColor(i2, ContextCompat.getColor(context, i));
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_logo);
        int i3 = 8;
        if (this.type == 1) {
            i.b(this.mContext, imageView, incomeEntity.giftImg, R.drawable.fq_ic_gift_default);
        } else if (this.type == 3) {
            i.b(this.mContext, imageView, incomeEntity.carImg, R.drawable.fq_ic_placeholder_avatar);
        } else if (this.type == 2) {
            imageView.setImageResource(TextUtils.equals(incomeEntity.guardType, "3") ? R.drawable.fq_ic_guard_year_icon : R.drawable.fq_ic_guard_month_icon);
        } else {
            imageView.setVisibility(8);
        }
        ((TextView) baseViewHolder.getView(R.id.tv_count)).setVisibility(this.type == 4 ? 8 : 0);
        TextView textView = (TextView) baseViewHolder.getView(R.id.tv_reward_send);
        if (this.type == 4) {
            i3 = 0;
        }
        textView.setVisibility(i3);
        if (this.isExpend) {
            context2 = this.mContext;
            i2 = R.string.fq_props_expend_tips;
            objArr = new Object[]{incomeEntity.nickName};
        } else {
            context2 = this.mContext;
            i2 = R.string.fq_props_income_tips;
            objArr = new Object[]{incomeEntity.nickName};
        }
        textView.setText(context2.getString(i2, objArr));
    }

    private Spanned formatTips(IncomeEntity incomeEntity) {
        Spanned fromHtml;
        switch (this.type) {
            case 2:
                if (this.isExpend) {
                    fromHtml = Html.fromHtml(this.mContext.getString(R.string.fq_expend_open, new Object[]{formatNickName(incomeEntity.nickName), b.b(this.mContext, incomeEntity.guardType)}));
                } else {
                    fromHtml = Html.fromHtml(this.mContext.getString(R.string.fq_income_open, new Object[]{formatNickName(incomeEntity.nickName), b.b(this.mContext, incomeEntity.guardType)}));
                }
                return fromHtml;
            case 3:
                return Html.fromHtml(this.mContext.getString(R.string.fq_expend_pay, new Object[]{incomeEntity.carName, incomeEntity.carTimes}));
            case 4:
                return Html.fromHtml(this.mContext.getString(R.string.fq_props_name_tips, new Object[]{incomeEntity.propName, incomeEntity.incomeCount}));
            default:
                if (this.isExpend) {
                    fromHtml = Html.fromHtml(this.mContext.getString(R.string.fq_expend_send, new Object[]{formatNickName(incomeEntity.nickName), "1", incomeEntity.giftName}));
                } else {
                    fromHtml = Html.fromHtml(this.mContext.getString(R.string.fq_income_reward, new Object[]{formatNickName(incomeEntity.nickName), incomeEntity.giftName, "1"}));
                }
                return fromHtml;
        }
    }

    private String formatCount(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.isExpend ? "-" : Marker.ANY_NON_NULL_MARKER);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    private String formatNickName(String str) {
        return u.a(str, 5);
    }
}

package com.tomatolive.library.ui.adapter;

import android.text.Html;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.IncomeEntity;
import com.tomatolive.library.utils.p;

public class IncomeMenuAdapter extends BaseQuickAdapter<IncomeEntity, BaseViewHolder> {
    private boolean isExpend = false;

    public IncomeMenuAdapter(int i, boolean z) {
        super(i);
        this.isExpend = z;
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, IncomeEntity incomeEntity) {
        baseViewHolder.setText(R.id.tv_type, formatTitleStr(p.a(incomeEntity.incomeType))).setText(R.id.tv_total, Html.fromHtml(this.mContext.getString(R.string.fq_income_total, new Object[]{incomeEntity.incomeCount}))).setImageResource(R.id.iv_logo, formatImageResource(p.a(incomeEntity.incomeType)));
    }

    private int formatTitleStr(int i) {
        switch (i) {
            case 1:
                return this.isExpend ? R.string.fq_gift_expend : R.string.fq_gift_income;
            case 2:
                return this.isExpend ? R.string.fq_guard_expend : R.string.fq_guard_income;
            case 3:
                return this.isExpend ? R.string.fq_car_expend : R.string.fq_my_live_consume_detail;
            case 4:
                boolean z = this.isExpend;
                return R.string.fq_props;
            default:
                return this.isExpend ? R.string.fq_my_live_consume_detail : R.string.fq_my_live_income_detail;
        }
    }

    private int formatImageResource(int i) {
        switch (i) {
            case 1:
                return R.drawable.fq_ic_gift;
            case 2:
                return R.drawable.fq_ic_my_live_guard;
            case 3:
                return R.drawable.fq_ic_my_live_car;
            case 4:
                return R.drawable.fq_ic_props;
            default:
                return R.drawable.fq_ic_gift;
        }
    }
}

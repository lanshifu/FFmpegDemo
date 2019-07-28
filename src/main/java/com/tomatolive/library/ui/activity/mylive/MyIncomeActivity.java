package com.tomatolive.library.ui.activity.mylive;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.presenter.MyIncomePresenter;
import com.tomatolive.library.ui.view.iview.IMyIncomeView;

public class MyIncomeActivity extends BaseActivity<MyIncomePresenter> implements IMyIncomeView {
    private TextView tvNumber;

    static /* synthetic */ void lambda$initListener$0(View view) {
    }

    static /* synthetic */ void lambda$initListener$1(View view) {
    }

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public MyIncomePresenter createPresenter() {
        return new MyIncomePresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_my_income;
    }

    public void initView(Bundle bundle) {
        setActivityTitle(R.string.fq_my_live_my_income);
        this.tvNumber = (TextView) findViewById(R.id.tv_number);
        ((MyIncomePresenter) this.mPresenter).initData();
    }

    public void initListener() {
        super.initListener();
        findViewById(R.id.tv_convert).setOnClickListener(-$$Lambda$MyIncomeActivity$kwSyMkfnpgQEKkNOgv0uQ8GztPU.INSTANCE);
        findViewById(R.id.tv_withdraw).setOnClickListener(-$$Lambda$MyIncomeActivity$Z2z7EyihUoMnWmOKOT5Qy8Y2TsI.INSTANCE);
    }

    public void onDataSuccess(AnchorEntity anchorEntity) {
        if (anchorEntity != null) {
            this.tvNumber.setText(anchorEntity.balance);
        }
    }
}

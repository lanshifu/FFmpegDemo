package com.tomatolive.library.ui.activity.mylive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.a;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.MyLiveEntity;
import com.tomatolive.library.ui.activity.home.AnchorAuthActivity;
import com.tomatolive.library.ui.activity.home.AnchorAuthResultActivity;
import com.tomatolive.library.ui.presenter.MyLivePresenter;
import com.tomatolive.library.ui.view.iview.IMyLiveView;
import com.tomatolive.library.utils.b;

public class MyLiveActivity extends BaseActivity<MyLivePresenter> implements IMyLiveView {
    private boolean isAuth = false;
    private LinearLayout llAnchorBg;
    private LinearLayout llAnchorGradeBg;
    private LinearLayout llMyCarBg;
    private TextView tvAuth;
    private TextView tvGrade;
    private TextView tvMyIncome;

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public MyLivePresenter createPresenter() {
        return new MyLivePresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_my_live;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView() {
        return findViewById(R.id.fl_content_view);
    }

    public void initView(Bundle bundle) {
        setActivityTitle(R.string.fq_my_live);
        this.tvAuth = (TextView) findViewById(R.id.tv_auth);
        this.tvGrade = (TextView) findViewById(R.id.tv_live_grade);
        this.tvMyIncome = (TextView) findViewById(R.id.tv_my_income);
        this.llAnchorBg = (LinearLayout) findViewById(R.id.ll_anchor_bg);
        this.llAnchorGradeBg = (LinearLayout) findViewById(R.id.ll_anchor_grade_bg);
        this.llMyCarBg = (LinearLayout) findViewById(R.id.ll_my_car_bg);
        ((MyLivePresenter) this.mPresenter).initData(this.mStateView, true);
        ((MyLivePresenter) this.mPresenter).getUserGradeData();
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$MyLiveActivity$dvBANTqcHviqCRngEVveTHs4wqw(this));
        findViewById(R.id.rl_live_grade).setOnClickListener(new -$$Lambda$MyLiveActivity$KD6w_VBgEBHF7RKdljrBGezKRpQ(this));
        findViewById(R.id.rl_auth).setOnClickListener(new -$$Lambda$MyLiveActivity$1CpH1vCSVdSHBJ4Z9bzGJRBrmC0(this));
        findViewById(R.id.tv_income_record).setOnClickListener(new -$$Lambda$MyLiveActivity$0UWsjA2hZXMPl74vZt_UGrRjp5I(this));
        findViewById(R.id.tv_watch_record).setOnClickListener(new -$$Lambda$MyLiveActivity$O_C3uPKWrrE58k3TQtFP37gYu3Y(this));
        findViewById(R.id.tv_my_income).setOnClickListener(new -$$Lambda$MyLiveActivity$Tpe0Sv9MFq6KlefWfUUUiA_BnNg(this));
        findViewById(R.id.tv_dedicate).setOnClickListener(new -$$Lambda$MyLiveActivity$Jiu7O6AmF7-alalHi6wwqhYiB6Y(this));
        findViewById(R.id.tv_house_setting).setOnClickListener(new -$$Lambda$MyLiveActivity$_ods-C135x2rKgPy8kM32w7KFjg(this));
        findViewById(R.id.tv_banned_setting).setOnClickListener(new -$$Lambda$MyLiveActivity$PT1Nhj2rqO_kU2RjlLc8hKWZ2fg(this));
        findViewById(R.id.tv_live_pre_notice).setOnClickListener(new -$$Lambda$MyLiveActivity$UqgI9yLt5mwKDpIsvaOOGWS4KgY(this));
        findViewById(R.id.rl_live_anchor_grade).setOnClickListener(new -$$Lambda$MyLiveActivity$eJ4lIsFHn3BCay8harVcIe-5eZM(this));
        findViewById(R.id.tv_my_car).setOnClickListener(new -$$Lambda$MyLiveActivity$vi33eetKQexLGhV8bjC8NxbpjR8(this));
    }

    public static /* synthetic */ void lambda$initListener$0(MyLiveActivity myLiveActivity) {
        ((MyLivePresenter) myLiveActivity.mPresenter).initData(myLiveActivity.mStateView, true);
        ((MyLivePresenter) myLiveActivity.mPresenter).getUserGradeData();
    }

    public static /* synthetic */ void lambda$initListener$2(MyLiveActivity myLiveActivity, View view) {
        if (!myLiveActivity.isAuth) {
            ((MyLivePresenter) myLiveActivity.mPresenter).onAnchorAuth();
        }
    }

    public static /* synthetic */ void lambda$initListener$3(MyLiveActivity myLiveActivity, View view) {
        if (b.a(myLiveActivity.mContext)) {
            Intent intent = new Intent(myLiveActivity.mContext, IncomeRecordActivity.class);
            intent.putExtra("isAuth", myLiveActivity.isAuth);
            myLiveActivity.startActivity(intent);
        }
    }

    public static /* synthetic */ void lambda$initListener$5(MyLiveActivity myLiveActivity, View view) {
        if (b.a(myLiveActivity.mContext) && a.a().a != null) {
            a.a().a.c(myLiveActivity.mContext);
        }
    }

    public void onDataSuccess(MyLiveEntity myLiveEntity) {
        if (myLiveEntity != null) {
            this.isAuth = TextUtils.equals("1", myLiveEntity.role);
            this.tvAuth.setText(TextUtils.equals("1", myLiveEntity.role) ? R.string.fq_already_identy : R.string.fq_no_identy);
            this.tvAuth.setTextColor(ContextCompat.getColor(this.mContext, TextUtils.equals("1", myLiveEntity.role) ? R.color.fq_text_gray : R.color.fq_colorRed));
            this.llAnchorBg.setVisibility(this.isAuth ? 0 : 4);
            this.llAnchorGradeBg.setVisibility(this.isAuth ? 0 : 4);
            this.tvMyIncome.setVisibility(this.isAuth ? 0 : 4);
            this.llMyCarBg.setVisibility(myLiveEntity.openCar ? 0 : 8);
            if (TextUtils.equals(myLiveEntity.role, "4")) {
                this.llAnchorBg.setVisibility(4);
                this.llAnchorGradeBg.setVisibility(8);
                this.tvMyIncome.setVisibility(0);
            }
        }
    }

    public void onAnchorAuthSuccess(AnchorEntity anchorEntity) {
        if (anchorEntity != null) {
            Intent intent;
            switch (anchorEntity.isChecked) {
                case -2:
                    intent = new Intent(this.mContext, AnchorAuthActivity.class);
                    intent.putExtra("authType", anchorEntity.isChecked);
                    startActivity(intent);
                    break;
                case -1:
                case 0:
                    intent = new Intent(this.mContext, AnchorAuthResultActivity.class);
                    intent.putExtra("authType", anchorEntity.isChecked);
                    startActivity(intent);
                    break;
            }
        }
    }

    public void onUserGradeSuccess(AnchorEntity anchorEntity) {
        this.tvGrade.setText(String.format(getString(R.string.fq_lv), new Object[]{b.h(anchorEntity.expGrade)}));
    }
}

package com.tomatolive.library.ui.activity.mylive;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.presenter.UserGradePresenter;
import com.tomatolive.library.ui.view.iview.IUserGradeView;
import com.tomatolive.library.ui.view.widget.StateView.OnRetryClickListener;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;
import java.text.NumberFormat;

public class UserGradeActivity extends BaseActivity<UserGradePresenter> implements IUserGradeView {
    private ImageView ivAvatar;
    private ImageView ivGrade;
    private ProgressBar progressBar;
    private LinearLayout rlGradeBg;
    private TextView tvExperience;
    private TextView tvGrade;
    private TextView tvLv;
    private TextView tvNickName;
    private TextView tvPercentage;

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public UserGradePresenter createPresenter() {
        return new UserGradePresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_user_grade;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView() {
        return findViewById(R.id.fl_content_view);
    }

    public void initView(Bundle bundle) {
        setActivityTitle(R.string.fq_my_live_user_grade);
        this.ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        this.ivGrade = (ImageView) findViewById(R.id.iv_grade);
        this.tvNickName = (TextView) findViewById(R.id.tv_nick_name);
        this.tvGrade = (TextView) findViewById(R.id.tv_grade);
        this.tvExperience = (TextView) findViewById(R.id.tv_experience);
        this.tvLv = (TextView) findViewById(R.id.tv_lv);
        this.tvPercentage = (TextView) findViewById(R.id.tv_percentage);
        TextView textView = (TextView) findViewById(R.id.tv_send_gift_tips);
        this.rlGradeBg = (LinearLayout) findViewById(R.id.rl_grade_bg);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView.setText(getString(R.string.fq_my_live_send_gift_tips, new Object[]{b.b(this.mContext)}));
        ((UserGradePresenter) this.mPresenter).getData(this.mStateView, true);
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new OnRetryClickListener() {
            public void onRetryClick() {
                ((UserGradePresenter) UserGradeActivity.this.mPresenter).getData(UserGradeActivity.this.mStateView, true);
            }
        });
    }

    public void onDataSuccess(AnchorEntity anchorEntity) {
        i.a(this.mContext, this.ivAvatar, anchorEntity.avatar, R.drawable.fq_ic_placeholder_avatar_white);
        this.tvNickName.setText(anchorEntity.name);
        this.tvExperience.setText(Html.fromHtml(getExperienceTips(anchorEntity)));
        this.tvLv.setText(String.format(getString(R.string.fq_lv), new Object[]{b.h(anchorEntity.expGrade)}));
        this.progressBar.setMax(p.a(anchorEntity.nextGradeExp, 100));
        this.progressBar.setProgress(p.a(anchorEntity.exp, 1));
        initUserGrade(p.a(b.h(anchorEntity.expGrade)));
        if (isMaxGrade(anchorEntity)) {
            this.tvPercentage.setText("∞");
            return;
        }
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(1);
        double c = p.c(anchorEntity.exp) / p.c(anchorEntity.nextGradeExp);
        if (Double.isNaN(c) || Double.isInfinite(c)) {
            this.tvPercentage.setText("0%");
        } else if (this.progressBar.getProgress() >= this.progressBar.getMax()) {
            this.tvPercentage.setText("100%");
        } else {
            this.tvPercentage.setText(percentInstance.format(c));
        }
    }

    private void initUserGrade(int i) {
        b.a(this.mContext, this.tvGrade, "");
        this.tvGrade.setText(String.valueOf(i));
        this.rlGradeBg.setBackgroundResource(b.a(false, i));
        this.ivGrade.setImageResource(b.b(false, i));
    }

    private String getExperienceTips(AnchorEntity anchorEntity) {
        int a = p.a(anchorEntity.nextGradeExp, 0) - p.a(anchorEntity.exp, 0);
        if (a < 0) {
            a = Math.abs(a);
        }
        if (isMaxGrade(anchorEntity)) {
            return getString(R.string.fq_my_live_grade_current_experience_tips, new Object[]{String.valueOf(anchorEntity.exp)});
        }
        return getString(R.string.fq_my_live_grade_experience_tips, new Object[]{String.valueOf(a), String.valueOf(getNextExpGrade(anchorEntity.expGrade))});
    }

    private int getExpGrade(String str) {
        return p.a(b.h(str), 1);
    }

    private int getNextExpGrade(String str) {
        int expGrade = getExpGrade(str) + 1;
        return expGrade > b.a ? b.a : expGrade;
    }

    private boolean isMaxGrade(AnchorEntity anchorEntity) {
        return (b.j() && getExpGrade(anchorEntity.expGrade) >= 60) || (b.k() && getExpGrade(anchorEntity.expGrade) >= 120);
    }
}

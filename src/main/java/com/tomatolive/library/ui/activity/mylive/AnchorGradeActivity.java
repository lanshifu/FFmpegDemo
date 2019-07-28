package com.tomatolive.library.ui.activity.mylive;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.presenter.AnchorGradePresenter;
import com.tomatolive.library.ui.view.custom.AnchorGradeView;
import com.tomatolive.library.ui.view.iview.IAnchorGradeView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;

public class AnchorGradeActivity extends BaseActivity<AnchorGradePresenter> implements IAnchorGradeView {
    private AnchorGradeView currentGradeView;
    private ImageView ivAvatar;
    private AnchorGradeView nextGradeView;
    private TextView tvCurrentLv;
    private TextView tvExperience;
    private TextView tvNextLv;
    private TextView tvNickName;

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public AnchorGradePresenter createPresenter() {
        return new AnchorGradePresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_anchor_grade;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView() {
        return findViewById(R.id.fl_content_view);
    }

    public void initView(Bundle bundle) {
        setActivityTitle(R.string.fq_my_live_anchor_grade);
        this.tvNickName = (TextView) findViewById(R.id.tv_nick_name);
        this.tvExperience = (TextView) findViewById(R.id.tv_experience);
        this.tvCurrentLv = (TextView) findViewById(R.id.tv_current_lv);
        this.tvNextLv = (TextView) findViewById(R.id.tv_next_lv);
        this.ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        this.currentGradeView = (AnchorGradeView) findViewById(R.id.current_grade_view);
        this.nextGradeView = (AnchorGradeView) findViewById(R.id.next_grade_view);
        ((AnchorGradePresenter) this.mPresenter).getData(this.mStateView, true);
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$AnchorGradeActivity$FptS93L2JXZwuHDBhWY5Vgv_wJs(this));
    }

    public void onDataSuccess(AnchorEntity anchorEntity) {
        if (anchorEntity != null) {
            i.a(this.mContext, this.ivAvatar, anchorEntity.avatar, 6, ContextCompat.getColor(this.mContext, R.color.fq_colorWhite));
            this.tvNickName.setText(anchorEntity.nickname);
            this.tvExperience.setText(getExperienceTips(anchorEntity));
            this.tvCurrentLv.setText(getCurrentLv(anchorEntity));
            this.tvNextLv.setText(getNextLv(anchorEntity));
            if (b.g(anchorEntity.expGrade)) {
                this.nextGradeView.setVisibility(4);
                this.currentGradeView.initUserGrade(getExpGrade(String.valueOf(30)));
            } else {
                this.nextGradeView.setVisibility(0);
                this.currentGradeView.initUserGrade(getExpGrade(anchorEntity.expGrade));
            }
            this.nextGradeView.initUserGrade(getNextExpGrade(anchorEntity.expGrade));
        }
    }

    private int getExpGrade(String str) {
        return p.a(b.h(str), 1);
    }

    private int getNextExpGrade(String str) {
        int expGrade = getExpGrade(str) + 1;
        return expGrade > 30 ? 30 : expGrade;
    }

    private String getCurrentLv(AnchorEntity anchorEntity) {
        if (b.g(anchorEntity.expGrade)) {
            return getString(R.string.fq_my_live_grade_current_exp, new Object[]{String.valueOf(30), b.i(anchorEntity.exp)});
        }
        return getString(R.string.fq_my_live_grade_current_exp, new Object[]{b.h(anchorEntity.expGrade), b.i(anchorEntity.exp)});
    }

    private String getNextLv(AnchorEntity anchorEntity) {
        if (b.g(anchorEntity.expGrade)) {
            return "";
        }
        String valueOf = String.valueOf(getNextExpGrade(anchorEntity.expGrade));
        return getString(R.string.fq_my_live_grade_current_exp, new Object[]{valueOf, b.i(anchorEntity.nextGradeExp)});
    }

    private String getExperienceTips(AnchorEntity anchorEntity) {
        if (b.g(anchorEntity.expGrade)) {
            return getString(R.string.fq_my_live_grade_current_next_exp_tips, new Object[]{String.valueOf(anchorEntity.exp)});
        }
        int a = p.a(anchorEntity.nextGradeExp, 0) - p.a(anchorEntity.exp, 0);
        if (a < 0) {
            a = Math.abs(a);
        }
        return getString(R.string.fq_my_live_grade_next_exp_tips, new Object[]{String.valueOf(a), String.valueOf(getNextExpGrade(anchorEntity.expGrade))});
    }
}

package com.tomatolive.library.ui.activity.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.activity.live.PrepareLiveActivity;
import com.tomatolive.library.ui.presenter.AnchorAuthResultPresenter;
import com.tomatolive.library.ui.view.iview.IAnchorAuthResultView;
import com.tomatolive.library.utils.z;
import defpackage.wd;
import io.reactivex.disposables.b;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.TimeUnit;

public class AnchorAuthResultActivity extends BaseActivity<AnchorAuthResultPresenter> implements IAnchorAuthResultView {
    private ImageView ivAuthLogo;
    private String name;
    private String phone;
    private RelativeLayout rlAnchorInfo;
    private TextView tvAnchorName;
    private TextView tvAnchorPhone;
    private TextView tvReAuth;
    private TextView tvResultInfo;
    private TextView tvResultTips;
    private TextView tvStartLive;
    private int type;

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public AnchorAuthResultPresenter createPresenter() {
        return new AnchorAuthResultPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_identy_result;
    }

    public void initView(Bundle bundle) {
        setActivityTitle(R.string.fq_anchor_identy);
        this.ivAuthLogo = (ImageView) findViewById(R.id.iv_auth_logo);
        this.tvResultInfo = (TextView) findViewById(R.id.tv_result_info);
        this.tvResultTips = (TextView) findViewById(R.id.tv_fail_info);
        this.tvAnchorName = (TextView) findViewById(R.id.tv_anchor_name);
        this.tvAnchorPhone = (TextView) findViewById(R.id.tv_anchor_phone);
        this.tvStartLive = (TextView) findViewById(R.id.tv_start_live);
        this.tvReAuth = (TextView) findViewById(R.id.tv_re_auth);
        this.rlAnchorInfo = (RelativeLayout) findViewById(R.id.rl_anchor_info);
        this.type = getIntent().getIntExtra("authType", 0);
        this.name = z.a().f();
        this.phone = "";
        showCheckResult();
        if (this.type == 0) {
            ((AnchorAuthResultPresenter) this.mPresenter).onAnchorAuth();
        }
        this.tvStartLive.setOnClickListener(new -$$Lambda$AnchorAuthResultActivity$BShZj6EyLFF2xQqbJBjwuPRlFSs(this));
        this.tvReAuth.setOnClickListener(new -$$Lambda$AnchorAuthResultActivity$zBsl4qf2PFZCT_wm0_eVTdA_BPM(this));
    }

    public static /* synthetic */ void lambda$initView$0(AnchorAuthResultActivity anchorAuthResultActivity, View view) {
        anchorAuthResultActivity.startActivity(PrepareLiveActivity.class);
        anchorAuthResultActivity.finish();
    }

    public static /* synthetic */ void lambda$initView$1(AnchorAuthResultActivity anchorAuthResultActivity, View view) {
        anchorAuthResultActivity.startActivity(AnchorAuthActivity.class);
        anchorAuthResultActivity.finish();
    }

    private void showCheckResult() {
        switch (this.type) {
            case -1:
                this.ivAuthLogo.setImageResource(R.drawable.fq_identy_failure);
                this.tvResultTips.setVisibility(0);
                this.tvReAuth.setVisibility(0);
                this.rlAnchorInfo.setVisibility(4);
                this.tvStartLive.setVisibility(4);
                this.tvResultTips.setText(R.string.fq_identity_information_does_not_match);
                return;
            case 0:
                this.ivAuthLogo.setImageResource(R.drawable.fq_anchor_identy);
                this.tvResultTips.setVisibility(0);
                this.tvResultTips.setText(R.string.fq_wait_identy);
                this.rlAnchorInfo.setVisibility(4);
                this.tvStartLive.setVisibility(4);
                this.tvReAuth.setVisibility(4);
                return;
            case 1:
                this.ivAuthLogo.setImageResource(R.drawable.fq_identy_succ);
                this.tvResultInfo.setText(R.string.fq_already_identy);
                this.tvAnchorName.setText(this.name);
                this.tvAnchorPhone.setText(this.phone);
                this.tvResultTips.setVisibility(4);
                this.tvReAuth.setVisibility(4);
                this.rlAnchorInfo.setVisibility(0);
                this.tvStartLive.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void onAnchorAuthSuccess(AnchorEntity anchorEntity) {
        this.type = anchorEntity.isChecked;
        this.name = TextUtils.isEmpty(anchorEntity.realName) ? z.a().f() : anchorEntity.realName;
        this.phone = TextUtils.isEmpty(anchorEntity.phone) ? "" : anchorEntity.phone;
        showCheckResult();
        if (this.type != 1 && this.type != -1) {
            starTimer();
        }
    }

    private void starTimer() {
        k.timer(5, TimeUnit.SECONDS).compose(bindToLifecycle()).observeOn(wd.a()).subscribe(new r<Long>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onSubscribe(b bVar) {
            }

            public void onNext(Long l) {
                ((AnchorAuthResultPresenter) AnchorAuthResultActivity.this.mPresenter).onAnchorAuth();
            }
        });
    }

    public void onLiveListFail() {
        starTimer();
    }
}

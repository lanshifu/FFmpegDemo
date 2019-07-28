package com.tomatolive.library.ui.activity.mylive;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.LivePreNoticeEntity;
import com.tomatolive.library.ui.presenter.LivePreNoticePresenter;
import com.tomatolive.library.ui.view.dialog.SureCancelDialog;
import com.tomatolive.library.ui.view.iview.ILivePreNoticeView;
import com.tomatolive.library.utils.e;
import defpackage.mj;
import defpackage.sh;

public class LivePreNoticeActivity extends BaseActivity<LivePreNoticePresenter> implements ILivePreNoticeView {
    private EditText etContent;
    private TextView tvCurrentPre;
    private TextView tvNewPre;
    private TextView tvSave;

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public LivePreNoticePresenter createPresenter() {
        return new LivePreNoticePresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_live_pre_notice;
    }

    public void initView(Bundle bundle) {
        setActivityTitle(R.string.fq_my_live_pre_notice);
        this.etContent = (EditText) findViewById(R.id.et_content);
        this.tvCurrentPre = (TextView) findViewById(R.id.tv_current_pre);
        this.tvNewPre = (TextView) findViewById(R.id.tv_new_pre);
        this.tvSave = (TextView) findViewById(R.id.tv_save);
        ((LivePreNoticePresenter) this.mPresenter).getDataList();
    }

    public void initListener() {
        super.initListener();
        mj.a(this.etContent).map(-$$Lambda$LivePreNoticeActivity$S0tQxr1IYs1Mz-P78dtLYlErgmM.INSTANCE).compose(bindToLifecycle()).subscribe(new sh<Boolean>() {
            public void accept(Boolean bool) {
                LivePreNoticeActivity.this.tvSave.setEnabled(bool.booleanValue());
            }
        });
        this.tvSave.setOnClickListener(new -$$Lambda$LivePreNoticeActivity$tWTks0CUWoSRHd6bMdjfOn68dGU(this));
    }

    public static /* synthetic */ void lambda$initListener$2(LivePreNoticeActivity livePreNoticeActivity, View view) {
        String trim = livePreNoticeActivity.etContent.getText().toString().trim();
        if (e.a(trim)) {
            livePreNoticeActivity.showToast(R.string.fq_no_emoji_search);
        } else if (trim.length() < 5) {
            livePreNoticeActivity.showToast(R.string.fq_my_live_pre_notice_chat_short_tips);
        } else if (trim.length() > 25) {
            livePreNoticeActivity.showToast(R.string.fq_my_live_pre_notice_chat_long_tips);
        } else {
            SureCancelDialog.newInstance(livePreNoticeActivity.getString(R.string.fq_my_live_sure_submit_pre_notice), new -$$Lambda$LivePreNoticeActivity$PCWu8l1nG4gGI1Kg0_cmqe8XLbA(livePreNoticeActivity, trim)).show(livePreNoticeActivity.getSupportFragmentManager());
        }
    }

    public void onDataSuccess(LivePreNoticeEntity livePreNoticeEntity) {
        if (livePreNoticeEntity != null) {
            if (TextUtils.isEmpty(livePreNoticeEntity.useLiveHerald)) {
                this.tvCurrentPre.setText(R.string.fq_my_live_no_current_pre_notice_tips);
            } else {
                this.tvCurrentPre.setText(getString(R.string.fq_my_live_current_pre_notice_tips, new Object[]{livePreNoticeEntity.useLiveHerald}));
            }
            if (!TextUtils.isEmpty(livePreNoticeEntity.auditLiveHerald)) {
                this.tvNewPre.setText(getString(R.string.fq_my_live_new_pre_notice_tips, new Object[]{livePreNoticeEntity.auditLiveHerald}));
            }
        }
    }

    public void onSaveSuccess() {
        showToast(R.string.fq_submit_suc);
        this.etContent.setText("");
        ((LivePreNoticePresenter) this.mPresenter).getDataList();
    }
}

package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.tomatolive.library.R;

public class CoverSettingDialog extends BaseDialogFragment {
    private static final String ISLOCATION = "islocation";
    private static final String TIPS = "tips";
    private static final String TITLE = "title";
    private OnClickListener cancelListener;
    private OnClickListener sureListener;

    public boolean getCancelOutside() {
        return false;
    }

    @SuppressLint({"ValidFragment"})
    private CoverSettingDialog() {
    }

    public static CoverSettingDialog newInstance(String str, String str2, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Bundle bundle = new Bundle();
        CoverSettingDialog coverSettingDialog = new CoverSettingDialog();
        coverSettingDialog.setArguments(bundle);
        bundle.putString(TITLE, str);
        bundle.putString(TIPS, str2);
        coverSettingDialog.setSureListener(onClickListener);
        coverSettingDialog.setCancelListener(onClickListener2);
        return coverSettingDialog;
    }

    public static CoverSettingDialog newInstance(boolean z, String str, String str2, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Bundle bundle = new Bundle();
        CoverSettingDialog coverSettingDialog = new CoverSettingDialog();
        coverSettingDialog.setArguments(bundle);
        bundle.putString(TITLE, str);
        bundle.putString(TIPS, str2);
        bundle.putBoolean(ISLOCATION, z);
        coverSettingDialog.setSureListener(onClickListener);
        coverSettingDialog.setCancelListener(onClickListener2);
        return coverSettingDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_setting_cover_tips;
    }

    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_tips);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_start_live);
        TextView textView4 = (TextView) view.findViewById(R.id.tv_setting);
        if (getArgumentsBoolean(ISLOCATION)) {
            textView3.setText(R.string.fq_btn_sure);
            textView4.setText(R.string.fq_btn_cancel);
        }
        textView.setText(getArgumentsString(TITLE));
        textView2.setText(getArgumentsString(TIPS));
        textView3.setOnClickListener(new -$$Lambda$CoverSettingDialog$kflHoQSSSwwaMJLKk85vyj8OJdM(this));
        textView4.setOnClickListener(new -$$Lambda$CoverSettingDialog$P2oaq4SeClwvUSr8ThqtOvGmfhk(this));
    }

    public static /* synthetic */ void lambda$initView$0(CoverSettingDialog coverSettingDialog, View view) {
        if (coverSettingDialog.cancelListener != null) {
            coverSettingDialog.dismiss();
            coverSettingDialog.cancelListener.onClick(view);
        }
    }

    public static /* synthetic */ void lambda$initView$1(CoverSettingDialog coverSettingDialog, View view) {
        if (coverSettingDialog.sureListener != null) {
            coverSettingDialog.dismiss();
            coverSettingDialog.sureListener.onClick(view);
        }
    }

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
        super.initListener(view);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.sureListener != null) {
            this.sureListener = null;
        }
        if (this.cancelListener != null) {
            this.cancelListener = null;
        }
    }

    public OnClickListener getAuthListener() {
        return this.sureListener;
    }

    public void setSureListener(OnClickListener onClickListener) {
        this.sureListener = onClickListener;
    }

    public OnClickListener getCancelListener() {
        return this.cancelListener;
    }

    public void setCancelListener(OnClickListener onClickListener) {
        this.cancelListener = onClickListener;
    }
}

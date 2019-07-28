package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.tomatolive.library.R;

public class SureCancelDialog extends BaseDialogFragment implements OnClickListener {
    private static final String TIPS = "tips";
    private OnClickListener authListener;

    @SuppressLint({"ValidFragment"})
    private SureCancelDialog() {
    }

    public static SureCancelDialog newInstance(String str, OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        SureCancelDialog sureCancelDialog = new SureCancelDialog();
        sureCancelDialog.setArguments(bundle);
        bundle.putString(TIPS, str);
        sureCancelDialog.setAuthListener(onClickListener);
        return sureCancelDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_auth_tips;
    }

    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_auth);
        ((TextView) view.findViewById(R.id.tv_auth_tips)).setText(getArgumentsString(TIPS));
        textView.setText(getString(R.string.fq_btn_sure));
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        view.findViewById(R.id.tv_auth).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SureCancelDialog.this.authListener != null) {
                    SureCancelDialog.this.dismiss();
                    SureCancelDialog.this.authListener.onClick(view);
                }
            }
        });
    }

    public void onClick(View view) {
        dismiss();
    }

    public OnClickListener getAuthListener() {
        return this.authListener;
    }

    private void setAuthListener(OnClickListener onClickListener) {
        this.authListener = onClickListener;
    }
}

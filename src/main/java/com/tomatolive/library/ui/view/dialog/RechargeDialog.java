package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.tomatolive.library.R;

public class RechargeDialog extends BaseDialogFragment implements OnClickListener {
    private static final String TIPS = "tips";
    private OnClickListener rechargeListener;

    @SuppressLint({"ValidFragment"})
    private RechargeDialog() {
    }

    public static RechargeDialog newInstance(OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        bundle.putString(TIPS, "");
        RechargeDialog rechargeDialog = new RechargeDialog();
        rechargeDialog.setRechargeListener(onClickListener);
        rechargeDialog.setArguments(bundle);
        return rechargeDialog;
    }

    public static RechargeDialog newInstance(String str, OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        bundle.putString(TIPS, str);
        RechargeDialog rechargeDialog = new RechargeDialog();
        rechargeDialog.setRechargeListener(onClickListener);
        rechargeDialog.setArguments(bundle);
        return rechargeDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_recharge_tips;
    }

    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_auth_tips);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        String argumentsString = getArgumentsString(TIPS);
        if (!TextUtils.isEmpty(argumentsString)) {
            textView.setText(argumentsString);
        }
        view.findViewById(R.id.tv_start).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RechargeDialog.this.dismiss();
                if (RechargeDialog.this.rechargeListener != null) {
                    RechargeDialog.this.rechargeListener.onClick(view);
                }
            }
        });
    }

    public void onClick(View view) {
        dismiss();
    }

    public OnClickListener getAuthListener() {
        return this.rechargeListener;
    }

    private void setRechargeListener(OnClickListener onClickListener) {
        this.rechargeListener = onClickListener;
    }
}

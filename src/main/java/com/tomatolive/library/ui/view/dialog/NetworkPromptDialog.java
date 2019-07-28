package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.tomatolive.library.R;

public class NetworkPromptDialog extends BaseDialogFragment {
    private static final String CANCEL_STR = "cancelStr";
    private static final String SURE_STR = "sureStr";
    private static final String TIPS = "tips";
    private OnClickListener cancelListener;
    private OnClickListener sureListener;

    @SuppressLint({"ValidFragment"})
    private NetworkPromptDialog() {
    }

    public static NetworkPromptDialog newInstance(String str, String str2, String str3, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Bundle bundle = new Bundle();
        NetworkPromptDialog networkPromptDialog = new NetworkPromptDialog();
        networkPromptDialog.setArguments(bundle);
        bundle.putString(TIPS, str);
        bundle.putString(SURE_STR, str2);
        bundle.putString(CANCEL_STR, str3);
        networkPromptDialog.setCancelListener(onClickListener2);
        networkPromptDialog.setSureListener(onClickListener);
        return networkPromptDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_auth_tips;
    }

    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_cancel);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_auth);
        ((TextView) view.findViewById(R.id.tv_auth_tips)).setText(getArgumentsString(TIPS));
        textView.setText(getArgumentsString(CANCEL_STR));
        textView2.setText(getArgumentsString(SURE_STR));
        view.findViewById(R.id.tv_cancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (NetworkPromptDialog.this.cancelListener != null) {
                    NetworkPromptDialog.this.dismiss();
                    NetworkPromptDialog.this.cancelListener.onClick(view);
                }
            }
        });
        view.findViewById(R.id.tv_auth).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (NetworkPromptDialog.this.sureListener != null) {
                    NetworkPromptDialog.this.dismiss();
                    NetworkPromptDialog.this.sureListener.onClick(view);
                }
            }
        });
    }

    public OnClickListener getSureListener() {
        return this.sureListener;
    }

    private void setSureListener(OnClickListener onClickListener) {
        this.sureListener = onClickListener;
    }

    public OnClickListener getCancelListener() {
        return this.cancelListener;
    }

    private void setCancelListener(OnClickListener onClickListener) {
        this.cancelListener = onClickListener;
    }
}

package com.tomatolive.library.ui.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.tomatolive.library.R;

public class LoadingDialog extends Dialog {
    private Context mContext;
    private String tips;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.DialogStyle);
        this.mContext = context;
    }

    public LoadingDialog(@NonNull Context context, String str) {
        super(context, R.style.DialogStyle);
        this.tips = str;
        this.mContext = context;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = View.inflate(this.mContext, R.layout.fq_dialog_loading, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_tips);
        if (!TextUtils.isEmpty(this.tips)) {
            textView.setText(this.tips);
        }
        Window window = getWindow();
        if (window != null) {
            Activity ownerActivity = getOwnerActivity();
            if (ownerActivity != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ownerActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                double d = (double) displayMetrics.widthPixels;
                Double.isNaN(d);
                window.setLayout((int) (d * 0.7d), -2);
                window.setGravity(17);
                window.setDimAmount(0.7f);
            }
        }
        setContentView(inflate);
    }

    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

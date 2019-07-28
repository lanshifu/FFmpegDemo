package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.a;
import com.tomatolive.library.utils.z;

public class TokenInvalidDialog extends BaseDialogFragment {
    public boolean getCancelOutside() {
        return false;
    }

    @SuppressLint({"ValidFragment"})
    private TokenInvalidDialog() {
    }

    public static TokenInvalidDialog newInstance() {
        Bundle bundle = new Bundle();
        TokenInvalidDialog tokenInvalidDialog = new TokenInvalidDialog();
        z.a().k();
        tokenInvalidDialog.setArguments(bundle);
        return tokenInvalidDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_warn_tips;
    }

    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_tips);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_auth_tips);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_sure);
        textView.setText(R.string.fq_tip);
        textView2.setText(R.string.fq_token_invalid_tip);
        textView3.setText(R.string.fq_btn_sure);
        textView2.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_colorRed));
        textView3.setOnClickListener(new -$$Lambda$TokenInvalidDialog$uiIMJnbDozAMYoULdWQKmhHKx20(this));
    }

    public static /* synthetic */ void lambda$initView$0(TokenInvalidDialog tokenInvalidDialog, View view) {
        if (a.a().a != null) {
            a.a().a.b(view.getContext());
        }
        tokenInvalidDialog.dismiss();
    }
}

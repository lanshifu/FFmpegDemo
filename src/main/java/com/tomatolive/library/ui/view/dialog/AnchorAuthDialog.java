package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.activity.home.AnchorAuthActivity;

public class AnchorAuthDialog extends BaseDialogFragment {
    private OnClickListener authListener;

    public void initView(View view) {
    }

    @SuppressLint({"ValidFragment"})
    private AnchorAuthDialog() {
    }

    public static AnchorAuthDialog newInstance() {
        Bundle bundle = new Bundle();
        AnchorAuthDialog anchorAuthDialog = new AnchorAuthDialog();
        anchorAuthDialog.setArguments(bundle);
        return anchorAuthDialog;
    }

    public static AnchorAuthDialog newInstance(OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        AnchorAuthDialog anchorAuthDialog = new AnchorAuthDialog();
        anchorAuthDialog.setArguments(bundle);
        anchorAuthDialog.setAuthListener(onClickListener);
        return anchorAuthDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_auth_tips;
    }

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
        super.initListener(view);
        view.findViewById(R.id.tv_cancel).setOnClickListener(new -$$Lambda$AnchorAuthDialog$voIaA11zzsKj5jBJR5AWhp6lOF4(this));
        view.findViewById(R.id.tv_auth).setOnClickListener(new -$$Lambda$AnchorAuthDialog$YCYB5nQcEnxcFolErQotBc6O1yw(this));
    }

    public static /* synthetic */ void lambda$initListener$1(AnchorAuthDialog anchorAuthDialog, View view) {
        anchorAuthDialog.dismiss();
        anchorAuthDialog.mContext.startActivity(new Intent(anchorAuthDialog.mContext, AnchorAuthActivity.class));
    }

    public OnClickListener getAuthListener() {
        return this.authListener;
    }

    private void setAuthListener(OnClickListener onClickListener) {
        this.authListener = onClickListener;
    }
}

package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.R;

public class TransDialog extends BaseDialogFragment implements OnClickListener {
    private OnClickListener transListener;

    @SuppressLint({"ValidFragment"})
    private TransDialog() {
    }

    public static TransDialog newInstance(OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        TransDialog transDialog = new TransDialog();
        transDialog.setArguments(bundle);
        transDialog.setTransListener(onClickListener);
        return transDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_tans_tips;
    }

    public void initView(View view) {
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        view.findViewById(R.id.tv_start).setOnClickListener(new -$$Lambda$TransDialog$thLXn70vHsVZkPyoTwa5AAEv73I(this));
    }

    public static /* synthetic */ void lambda$initView$0(TransDialog transDialog, View view) {
        if (transDialog.transListener != null) {
            transDialog.dismiss();
            transDialog.transListener.onClick(view);
        }
    }

    public void onClick(View view) {
        dismiss();
    }

    public OnClickListener getAuthListener() {
        return this.transListener;
    }

    private void setTransListener(OnClickListener onClickListener) {
        this.transListener = onClickListener;
    }
}

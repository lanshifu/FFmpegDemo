package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.blankj.utilcode.util.o;
import com.tomatolive.library.R;

public class UpdateTitleDialog extends BaseBottomDialogFragment {
    private OnUpdateLiveTitleListener liveTitleListener;
    private EditText mEditText;

    public interface OnUpdateLiveTitleListener {
        void onUpdateLiveTitle(String str);
    }

    @SuppressLint({"ValidFragment"})
    private UpdateTitleDialog() {
    }

    public static UpdateTitleDialog newInstance(OnUpdateLiveTitleListener onUpdateLiveTitleListener) {
        Bundle bundle = new Bundle();
        UpdateTitleDialog updateTitleDialog = new UpdateTitleDialog();
        updateTitleDialog.setArguments(bundle);
        updateTitleDialog.setLiveTitleListener(onUpdateLiveTitleListener);
        return updateTitleDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_update_input_title;
    }

    public void initView(View view) {
        this.mEditText = (EditText) view.findViewById(R.id.et_input_message);
        this.mEditText.setFocusable(true);
        this.mEditText.setFocusableInTouchMode(true);
        this.mEditText.requestFocus();
        this.mEditText.post(new -$$Lambda$UpdateTitleDialog$PPUypYXSG8Gw4FfrTxM8p3y0zbA(this));
        this.mEditText.setOnEditorActionListener(new -$$Lambda$UpdateTitleDialog$pCMOUGGgPrEHgfrN1XMTS3rt7JY(this));
        view.findViewById(R.id.btn_send).setOnClickListener(new -$$Lambda$UpdateTitleDialog$Sikqi9TWWMhKPY1HKeKYj3CcMWo(this));
    }

    public static /* synthetic */ boolean lambda$initView$1(UpdateTitleDialog updateTitleDialog, TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        String trim = updateTitleDialog.mEditText.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            o.a(updateTitleDialog.getString(R.string.fq_live_input_title));
            return true;
        }
        updateTitleDialog.dismiss();
        if (updateTitleDialog.liveTitleListener != null) {
            updateTitleDialog.liveTitleListener.onUpdateLiveTitle(trim);
        }
        return true;
    }

    public static /* synthetic */ void lambda$initView$2(UpdateTitleDialog updateTitleDialog, View view) {
        String trim = updateTitleDialog.mEditText.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            o.a(updateTitleDialog.getString(R.string.fq_live_input_title));
            return;
        }
        updateTitleDialog.dismiss();
        if (updateTitleDialog.liveTitleListener != null) {
            updateTitleDialog.liveTitleListener.onUpdateLiveTitle(trim);
        }
    }

    private void setLiveTitleListener(OnUpdateLiveTitleListener onUpdateLiveTitleListener) {
        this.liveTitleListener = onUpdateLiveTitleListener;
    }
}

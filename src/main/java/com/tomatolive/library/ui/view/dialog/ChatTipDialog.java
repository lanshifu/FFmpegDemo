package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.tomatolive.library.R;

public class ChatTipDialog extends BaseDialogFragment {
    private static final String CONTENT = "content";

    public boolean getCancelOutside() {
        return false;
    }

    @SuppressLint({"ValidFragment"})
    private ChatTipDialog() {
    }

    public static ChatTipDialog newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(CONTENT, str);
        ChatTipDialog chatTipDialog = new ChatTipDialog();
        chatTipDialog.setArguments(bundle);
        return chatTipDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_chat_tip_dialog;
    }

    public void initView(View view) {
        ((TextView) view.findViewById(R.id.tv_sure)).setText(R.string.fq_kown);
        ((TextView) view.findViewById(R.id.tv_tips)).setText(getArgumentsString(CONTENT));
    }

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
        super.initListener(view);
        view.findViewById(R.id.ll_button).setOnClickListener(new -$$Lambda$ChatTipDialog$IH8va6rTQnjP2kOmRWI5unzdV14(this));
    }
}

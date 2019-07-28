package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.tomatolive.library.R;

public class TextCancelTipsDialog extends BaseDialogFragment implements OnClickListener {
    private static final String TIPS = "tips";
    private static final String TITLE = "title";

    public boolean getCancelOutside() {
        return false;
    }

    @SuppressLint({"ValidFragment"})
    private TextCancelTipsDialog() {
    }

    public static TextCancelTipsDialog newInstance(String str) {
        Bundle bundle = new Bundle();
        TextCancelTipsDialog textCancelTipsDialog = new TextCancelTipsDialog();
        textCancelTipsDialog.setArguments(bundle);
        bundle.putString(TIPS, str);
        return textCancelTipsDialog;
    }

    public static TextCancelTipsDialog newInstance(String str, String str2) {
        Bundle bundle = new Bundle();
        TextCancelTipsDialog textCancelTipsDialog = new TextCancelTipsDialog();
        textCancelTipsDialog.setArguments(bundle);
        bundle.putString(TITLE, str);
        bundle.putString(TIPS, str2);
        return textCancelTipsDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_live_kick_out;
    }

    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_tips);
        view.findViewById(R.id.ll_button).setOnClickListener(this);
        CharSequence argumentsString = getArgumentsString(TITLE);
        if (TextUtils.isEmpty(argumentsString)) {
            argumentsString = getString(R.string.fq_tip);
        }
        textView.setText(argumentsString);
        textView2.setText(getArgumentsString(TIPS));
    }

    public void onClick(View view) {
        dismiss();
    }
}

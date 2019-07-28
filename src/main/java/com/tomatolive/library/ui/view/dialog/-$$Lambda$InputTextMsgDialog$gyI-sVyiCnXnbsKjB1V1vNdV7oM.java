package com.tomatolive.library.ui.view.dialog;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$InputTextMsgDialog$gyI-sVyiCnXnbsKjB1V1vNdV7oM implements OnEditorActionListener {
    private final /* synthetic */ InputTextMsgDialog f$0;

    public /* synthetic */ -$$Lambda$InputTextMsgDialog$gyI-sVyiCnXnbsKjB1V1vNdV7oM(InputTextMsgDialog inputTextMsgDialog) {
        this.f$0 = inputTextMsgDialog;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return InputTextMsgDialog.lambda$initListener$2(this.f$0, textView, i, keyEvent);
    }
}

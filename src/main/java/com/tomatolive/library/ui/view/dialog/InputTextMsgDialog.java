package com.tomatolive.library.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.blankj.utilcode.util.o;
import com.tomatolive.library.R;
import com.tomatolive.library.a;

public class InputTextMsgDialog extends Dialog {
    private TextView btnSend;
    private EditText etMsg;
    private OnTextSendListener mOnTextSendListener;
    private TextView mTvDanmu;

    public interface OnTextSendListener {
        void onClickDM(boolean z);

        void onTextSend(String str);
    }

    public InputTextMsgDialog(Context context, OnTextSendListener onTextSendListener) {
        super(context, R.style.fq_InputDialog);
        this.mOnTextSendListener = onTextSendListener;
        setContentView(R.layout.fq_dialog_input_send_msg);
        initView();
        initListener();
        Window window = getWindow();
        window.setSoftInputMode(4);
        LayoutParams attributes = window.getAttributes();
        attributes.width = context.getResources().getDisplayMetrics().widthPixels;
        attributes.x = 0;
        attributes.gravity = 80;
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    private void initView() {
        this.etMsg = (EditText) findViewById(R.id.et_input_message);
        this.btnSend = (TextView) findViewById(R.id.btn_send);
        this.mTvDanmu = (TextView) findViewById(R.id.fq_cb_danmu);
        this.etMsg.setFocusable(true);
        this.etMsg.setFocusableInTouchMode(true);
        this.etMsg.requestFocus();
    }

    public void initDanmuOpen(boolean z) {
        if (this.mTvDanmu != null) {
            this.mTvDanmu.setSelected(z);
            initEdMsgView();
        }
    }

    public void hideDanmu() {
        if (this.mTvDanmu != null) {
            this.mTvDanmu.setVisibility(8);
        }
    }

    public void toggleDanmu() {
        if (this.mTvDanmu != null) {
            this.mTvDanmu.setSelected(this.mTvDanmu.isSelected() ^ 1);
            initEdMsgView();
        }
    }

    private void initListener() {
        this.mTvDanmu.setOnClickListener(new -$$Lambda$InputTextMsgDialog$39653sum4iJK0qJd34QPpnYmDa0(this));
        this.btnSend.setOnClickListener(new -$$Lambda$InputTextMsgDialog$8UtE8ffEzcmJjihNvqqwUlqNFqg(this));
        this.etMsg.setOnEditorActionListener(new -$$Lambda$InputTextMsgDialog$gyI-sVyiCnXnbsKjB1V1vNdV7oM(this));
    }

    public static /* synthetic */ void lambda$initListener$0(InputTextMsgDialog inputTextMsgDialog, View view) {
        if (inputTextMsgDialog.mOnTextSendListener != null) {
            inputTextMsgDialog.mOnTextSendListener.onClickDM(inputTextMsgDialog.mTvDanmu.isSelected());
        }
    }

    public static /* synthetic */ void lambda$initListener$1(InputTextMsgDialog inputTextMsgDialog, View view) {
        String trim = inputTextMsgDialog.etMsg.getText().toString().trim();
        if (!(TextUtils.isEmpty(trim) || inputTextMsgDialog.mOnTextSendListener == null)) {
            inputTextMsgDialog.mOnTextSendListener.onTextSend(trim);
            inputTextMsgDialog.etMsg.setText("");
            inputTextMsgDialog.dismiss();
        }
    }

    public static /* synthetic */ boolean lambda$initListener$2(InputTextMsgDialog inputTextMsgDialog, TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        String trim = inputTextMsgDialog.etMsg.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            o.a(a.a().c().getString(R.string.fq_live_input_title));
            return true;
        }
        if (inputTextMsgDialog.mOnTextSendListener != null) {
            inputTextMsgDialog.mOnTextSendListener.onTextSend(trim);
            inputTextMsgDialog.etMsg.setText("");
            inputTextMsgDialog.dismiss();
        }
        return true;
    }

    private void initEdMsgView() {
        if (this.etMsg != null) {
            this.etMsg.setHint(this.mTvDanmu.isSelected() ? R.string.fq_send_danmu_tips : R.string.fq_text_input_hint);
            EditText editText = this.etMsg;
            InputFilter[] inputFilterArr = new InputFilter[1];
            inputFilterArr[0] = new LengthFilter(this.mTvDanmu.isSelected() ? 15 : 53);
            editText.setFilters(inputFilterArr);
        }
    }

    public void dismiss() {
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.etMsg.getWindowToken(), 2);
        super.dismiss();
    }

    public void onDestroy() {
        if (this.mOnTextSendListener != null) {
            this.mOnTextSendListener = null;
        }
        if (this.mTvDanmu != null) {
            this.mTvDanmu.setOnClickListener(null);
        }
        if (this.btnSend != null) {
            this.btnSend.setOnClickListener(null);
        }
        if (this.etMsg != null) {
            this.etMsg.clearFocus();
            this.etMsg.setOnEditorActionListener(null);
        }
    }

    public void cancelBandPost() {
        if (this.etMsg != null) {
            EditText editText = this.etMsg;
            InputFilter[] inputFilterArr = new InputFilter[1];
            inputFilterArr[0] = new LengthFilter(this.mTvDanmu.isSelected() ? 15 : 53);
            editText.setFilters(inputFilterArr);
            this.etMsg.setEnabled(true);
            this.etMsg.setText("");
            this.etMsg.setTextColor(ContextCompat.getColor(a.a().c(), R.color.fq_colorBlack));
        }
        if (this.btnSend != null) {
            this.btnSend.setEnabled(true);
        }
    }

    public void setBandPost(String str) {
        setBanedAllPost(String.format(a.a().c().getResources().getString(R.string.fq_banned_time_to), new Object[]{str}));
    }

    public void setBandPostBySuperManager() {
        setBanedAllPost(String.format(a.a().c().getResources().getString(R.string.fq_banned_forever), new Object[]{a.a().c().getString(R.string.fq_super_manager)}));
    }

    public void setBanedAllPost(String str) {
        if (this.etMsg != null) {
            this.etMsg.setFilters(new InputFilter[]{new LengthFilter(53)});
            this.etMsg.setText(str);
            this.etMsg.setTextColor(ContextCompat.getColor(a.a().c(), R.color.fq_colorRed));
            this.etMsg.setEnabled(false);
        }
        if (this.btnSend != null) {
            this.btnSend.setEnabled(false);
        }
    }
}

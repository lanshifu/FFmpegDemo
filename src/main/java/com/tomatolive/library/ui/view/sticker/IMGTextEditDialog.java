package com.tomatolive.library.ui.view.sticker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.sticker.core.IMGText;
import com.tomatolive.library.ui.view.sticker.view.IMGColorGroup;
import com.tomatolive.library.utils.t;

public class IMGTextEditDialog extends Dialog implements OnClickListener, OnCheckedChangeListener {
    private static final String TAG = "IMGTextEditDialog";
    private Callback mCallback;
    private IMGColorGroup mColorGroup;
    private IMGText mDefaultText;
    private EditText mEditText;

    public interface Callback {
        void onText(IMGText iMGText);
    }

    public IMGTextEditDialog(Context context, Callback callback) {
        super(context, R.style.ImageTextDialog);
        setContentView(R.layout.fq_dialog_sticker_text_color);
        this.mCallback = callback;
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mColorGroup = (IMGColorGroup) findViewById(R.id.cg_colors);
        this.mColorGroup.setOnCheckedChangeListener(this);
        this.mEditText = (EditText) findViewById(R.id.et_text);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_done).setOnClickListener(this);
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        if (this.mDefaultText != null) {
            this.mEditText.setText(this.mDefaultText.getText());
            this.mEditText.setTextColor(this.mDefaultText.getColor());
            if (!this.mDefaultText.isEmpty()) {
                this.mEditText.setSelection(this.mEditText.length());
            }
            this.mDefaultText = null;
        } else {
            this.mEditText.setText("");
        }
        this.mColorGroup.setCheckColor(this.mEditText.getCurrentTextColor());
    }

    public void setText(IMGText iMGText) {
        this.mDefaultText = iMGText;
    }

    public void reset() {
        setText(new IMGText(null, -1));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_done) {
            onDone();
        } else if (id == R.id.tv_cancel) {
            dismiss();
        }
    }

    private void onDone() {
        String obj = this.mEditText.getText().toString();
        if (!(TextUtils.isEmpty(obj) || this.mCallback == null)) {
            this.mCallback.onText(new IMGText(obj, this.mEditText.getCurrentTextColor()));
        }
        dismiss();
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.mEditText.setTextColor(this.mColorGroup.getCheckColor());
    }

    public void dismiss() {
        t.a((Dialog) this);
        super.dismiss();
    }
}

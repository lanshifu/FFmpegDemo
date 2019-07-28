package com.tomatolive.library.ui.view.sticker.view;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import com.tomatolive.library.ui.view.sticker.IMGTextEditDialog;
import com.tomatolive.library.ui.view.sticker.IMGTextEditDialog.Callback;
import com.tomatolive.library.ui.view.sticker.core.IMGText;
import com.yalantis.ucrop.view.CropImageView;

public class IMGStickerTextView extends IMGStickerView implements Callback {
    private static final int PADDING = 26;
    private static final String TAG = "IMGStickerTextView";
    private static final float TEXT_SIZE_SP = 9.0f;
    private static float mBaseTextSize = -1.0f;
    private IMGTextEditDialog mDialog;
    private IMGText mText;
    private TextView mTextView;

    public IMGStickerTextView(Context context) {
        this(context, null, 0);
    }

    public IMGStickerTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IMGStickerTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onInitialize(Context context) {
        if (mBaseTextSize <= CropImageView.DEFAULT_ASPECT_RATIO) {
            mBaseTextSize = TypedValue.applyDimension(2, TEXT_SIZE_SP, context.getResources().getDisplayMetrics());
        }
        super.onInitialize(context);
    }

    public View onCreateContentView(Context context) {
        this.mTextView = new TextView(context);
        this.mTextView.setTextSize(mBaseTextSize);
        this.mTextView.setPadding(26, 26, 26, 26);
        this.mTextView.setTextColor(-1);
        this.mTextView.setSingleLine();
        this.mTextView.setEllipsize(TruncateAt.END);
        return this.mTextView;
    }

    public void setText(IMGText iMGText) {
        this.mText = iMGText;
        if (this.mText != null && this.mTextView != null) {
            this.mTextView.setText(this.mText.getText());
            this.mTextView.setTextColor(this.mText.getColor());
        }
    }

    public IMGText getText() {
        return this.mText;
    }

    public void onContentTap() {
        IMGTextEditDialog dialog = getDialog();
        dialog.setText(this.mText);
        dialog.show();
    }

    private IMGTextEditDialog getDialog() {
        if (this.mDialog == null) {
            this.mDialog = new IMGTextEditDialog(getContext(), this);
        }
        return this.mDialog;
    }

    public void onText(IMGText iMGText) {
        this.mText = iMGText;
        if (this.mText != null && this.mTextView != null) {
            this.mTextView.setText(this.mText.getText());
            this.mTextView.setTextColor(this.mText.getColor());
        }
    }
}

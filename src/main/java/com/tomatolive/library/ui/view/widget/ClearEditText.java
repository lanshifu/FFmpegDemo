package com.tomatolive.library.ui.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import com.tomatolive.library.R;

public class ClearEditText extends AppCompatEditText implements TextWatcher, OnFocusChangeListener, OnTouchListener {
    private Drawable mClearTextIcon;
    private final Context mContext;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTouchListener mOnTouchListener;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public ClearEditText(Context context) {
        super(context);
        this.mContext = context;
        init(context);
    }

    public ClearEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init(context);
    }

    public ClearEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        init(context);
    }

    private void init(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.fq_ic_et_close);
        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, getCurrentHintTextColor());
            this.mClearTextIcon = drawable;
            this.mClearTextIcon.setBounds(0, 0, this.mClearTextIcon.getIntrinsicHeight(), this.mClearTextIcon.getIntrinsicHeight());
        }
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.mOnFocusChangeListener = onFocusChangeListener;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
    }

    public void onFocusChange(View view, boolean z) {
        boolean z2 = false;
        if (z) {
            if (getText().length() > 0) {
                z2 = true;
            }
            setClearIconVisible(z2);
        } else {
            hideSoftInput(this.mContext, view);
            setClearIconVisible(false);
        }
        if (this.mOnFocusChangeListener != null) {
            this.mOnFocusChangeListener.onFocusChange(view, z);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        boolean z = true;
        if (!this.mClearTextIcon.isVisible() || x <= (getWidth() - getPaddingRight()) - this.mClearTextIcon.getIntrinsicWidth()) {
            if (this.mOnTouchListener == null || !this.mOnTouchListener.onTouch(view, motionEvent)) {
                z = false;
            }
            return z;
        }
        if (motionEvent.getAction() == 1) {
            setError(null);
            setText("");
        }
        return true;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (isFocused()) {
            setClearIconVisible(charSequence.length() > 0);
        }
    }

    private void setClearIconVisible(boolean z) {
        this.mClearTextIcon.setVisible(z, false);
        Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], z ? this.mClearTextIcon : null, compoundDrawables[3]);
    }

    private void hideSoftInput(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

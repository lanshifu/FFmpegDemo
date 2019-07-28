package com.tomatolive.library.ui.view.widget.easypop;

import android.content.Context;
import android.view.View;

public class EasyPopup extends BasePopup<EasyPopup> {
    private OnViewListener mOnViewListener;

    public interface OnViewListener {
        void initViews(View view, EasyPopup easyPopup);
    }

    /* Access modifiers changed, original: protected */
    public void initAttributes() {
    }

    public static EasyPopup create() {
        return new EasyPopup();
    }

    public static EasyPopup create(Context context) {
        return new EasyPopup(context);
    }

    public EasyPopup(Context context) {
        setContext(context);
    }

    /* Access modifiers changed, original: protected */
    public void initViews(View view, EasyPopup easyPopup) {
        if (this.mOnViewListener != null) {
            this.mOnViewListener.initViews(view, easyPopup);
        }
    }

    public EasyPopup setOnViewListener(OnViewListener onViewListener) {
        this.mOnViewListener = onViewListener;
        return this;
    }
}

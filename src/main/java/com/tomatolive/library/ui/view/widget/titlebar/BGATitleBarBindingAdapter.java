package com.tomatolive.library.ui.view.widget.titlebar;

import android.graphics.drawable.Drawable;
import com.tomatolive.library.ui.view.widget.titlebar.BGATitleBar.Delegate;

public class BGATitleBarBindingAdapter {
    public static void setDelegate(BGATitleBar bGATitleBar, Delegate delegate) {
        bGATitleBar.setDelegate(delegate);
    }

    public static void setLeftText(BGATitleBar bGATitleBar, String str) {
        bGATitleBar.setLeftText((CharSequence) str);
    }

    public static void setTitleText(BGATitleBar bGATitleBar, String str) {
        bGATitleBar.setTitleText((CharSequence) str);
    }

    public static void setRightText(BGATitleBar bGATitleBar, String str) {
        bGATitleBar.setRightText((CharSequence) str);
    }

    public static void setRightSecondaryText(BGATitleBar bGATitleBar, String str) {
        bGATitleBar.setRightSecondaryText((CharSequence) str);
    }

    public static void setLeftDrawable(BGATitleBar bGATitleBar, Drawable drawable) {
        bGATitleBar.setLeftDrawable(drawable);
    }

    public static void setTitleDrawable(BGATitleBar bGATitleBar, Drawable drawable) {
        bGATitleBar.setTitleDrawable(drawable);
    }

    public static void setRightDrawable(BGATitleBar bGATitleBar, Drawable drawable) {
        bGATitleBar.setRightDrawable(drawable);
    }

    public static void setRightSecondaryDrawable(BGATitleBar bGATitleBar, Drawable drawable) {
        bGATitleBar.setRightSecondaryDrawable(drawable);
    }
}

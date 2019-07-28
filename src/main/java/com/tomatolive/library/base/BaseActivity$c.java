package com.tomatolive.library.base;

import android.view.View.OnClickListener;
import com.tomatolive.library.ui.view.widget.titlebar.BGATitleBar;
import com.tomatolive.library.ui.view.widget.titlebar.BGATitleBar.Delegate;

class BaseActivity$c implements Delegate {
    final /* synthetic */ BaseActivity a;
    private BGATitleBar b;
    private OnClickListener c;
    private OnClickListener d;

    public void onClickTitleCtv() {
    }

    public BaseActivity$c(BaseActivity baseActivity) {
        this.a = baseActivity;
    }

    public BaseActivity$c(BaseActivity baseActivity, BGATitleBar bGATitleBar, OnClickListener onClickListener) {
        this.a = baseActivity;
        this.b = bGATitleBar;
        this.c = onClickListener;
    }

    public void onClickLeftCtv() {
        this.a.onBackPressed();
    }

    public void onClickRightCtv() {
        if (this.c != null && this.b != null) {
            this.c.onClick(this.b.getRightCtv());
        }
    }

    public void onClickRightSecondaryCtv() {
        if (this.d != null && this.b != null) {
            this.d.onClick(this.b.getRightSecondaryCtv());
        }
    }
}

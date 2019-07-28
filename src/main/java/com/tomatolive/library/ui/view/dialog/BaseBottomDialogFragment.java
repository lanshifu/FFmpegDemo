package com.tomatolive.library.ui.view.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.tomatolive.library.R;

public abstract class BaseBottomDialogFragment extends BaseRxDialogFragment {
    private static final float DEFAULT_DIM = 0.7f;
    protected Dialog mDialog;
    protected Window mWindow;
    protected double maxHeightScale = 0.85d;
    public View rootView;

    /* Access modifiers changed, original: protected */
    public boolean getCancelOutside() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public float getDimAmount() {
        return DEFAULT_DIM;
    }

    /* Access modifiers changed, original: protected */
    public double getHeightScale() {
        return 0.0d;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
    }

    public abstract void initView(View view);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.fq_BottomDialog);
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mDialog = getDialog();
        this.mWindow = this.mDialog.getWindow();
        if (this.mWindow != null) {
            this.mWindow.requestFeature(1);
            this.mWindow.setBackgroundDrawable(new ColorDrawable(0));
        }
        if (this.rootView != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.rootView.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.rootView);
            }
        } else {
            this.rootView = layoutInflater.inflate(getLayoutRes(), viewGroup, false);
            initView(this.rootView);
            initListener(this.rootView);
        }
        return this.rootView;
    }

    public void onStart() {
        super.onStart();
        if (this.mDialog != null) {
            int i = -2;
            if (getHeightScale() > 0.0d && this.mHeightPixels > 0) {
                double d = (double) this.mHeightPixels;
                double heightScale = getHeightScale();
                Double.isNaN(d);
                i = (int) (d * heightScale);
            }
            if (this.mWindow != null) {
                this.mWindow.setLayout(-1, i);
                this.mWindow.setDimAmount(getDimAmount());
                this.mWindow.setGravity(80);
            }
            this.mDialog.setCancelable(getCancelOutside());
            this.mDialog.setCanceledOnTouchOutside(getCancelOutside());
        }
    }

    /* Access modifiers changed, original: protected */
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, getFragmentTag());
    }

    public void show(FragmentManager fragmentManager, String str) {
        try {
            fragmentManager.beginTransaction().remove(this).commit();
            super.show(fragmentManager, str);
        } catch (IllegalStateException unused) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

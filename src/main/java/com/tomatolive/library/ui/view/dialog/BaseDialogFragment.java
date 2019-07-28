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

public abstract class BaseDialogFragment extends BaseRxDialogFragment {
    private static final float DEFAULT_DIM = 0.7f;
    protected Dialog mDialog;
    protected Window mWindow;
    public View rootView;

    public boolean getCancelOutside() {
        return true;
    }

    public float getDimAmount() {
        return DEFAULT_DIM;
    }

    public double getHeightScale() {
        return 0.0d;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public double getWidthScale() {
        return 0.75d;
    }

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
    }

    public abstract void initView(View view);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.fq_CenterDialog);
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
        if (this.mDialog != null) {
            if (this.mWindow != null) {
                int i;
                int i2 = -2;
                if (this.mWidthPixels > 0) {
                    double d = (double) this.mWidthPixels;
                    double widthScale = getWidthScale();
                    Double.isNaN(d);
                    i = (int) (d * widthScale);
                } else {
                    i = -2;
                }
                if (getHeightScale() > 0.0d) {
                    if (this.mHeightPixels > 0) {
                        double d2 = (double) this.mHeightPixels;
                        double heightScale = getHeightScale();
                        Double.isNaN(d2);
                        i2 = (int) (d2 * heightScale);
                    }
                    this.mWindow.setLayout(i, i2);
                } else {
                    this.mWindow.setLayout(i, -2);
                }
                this.mWindow.setDimAmount(getDimAmount());
            }
            this.mDialog.setCancelable(getCancelOutside());
            this.mDialog.setCanceledOnTouchOutside(getCancelOutside());
        }
        super.onStart();
    }

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

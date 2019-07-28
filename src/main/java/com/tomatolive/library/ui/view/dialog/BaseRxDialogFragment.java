package com.tomatolive.library.ui.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import com.blankj.utilcode.util.l;
import com.tomatolive.library.utils.t;
import com.trello.rxlifecycle2.components.support.RxDialogFragment;
import com.yalantis.ucrop.view.CropImageView;

public abstract class BaseRxDialogFragment extends RxDialogFragment {
    protected Context mContext;
    protected Activity mDialogActivity;
    private DialogDismissListener mDialogDismissListener;
    protected int mHeightPixels;
    protected int mWidthPixels;

    public interface DialogDismissListener {
        void onDialogDismiss(BaseRxDialogFragment baseRxDialogFragment);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (VERSION.SDK_INT < 23) {
            onAttachToContext(activity);
        }
    }

    public void onAttachToContext(Context context) {
        this.mContext = context;
        this.mDialogActivity = getActivity() == null ? (Activity) context : getActivity();
        this.mWidthPixels = l.a();
        this.mHeightPixels = l.b();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        initSoftInputListener();
    }

    public void dismiss() {
        t.a(getDialog());
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.mDialogDismissListener != null) {
            this.mDialogDismissListener.onDialogDismiss(this);
        }
    }

    public void onDestroyView() {
        if (getDialog() != null) {
            getDialog().setOnShowListener(null);
            getDialog().setOnCancelListener(null);
            getDialog().setOnDismissListener(null);
        }
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        setOnDismissListener(null);
    }

    public void setOnDismissListener(DialogDismissListener dialogDismissListener) {
        this.mDialogDismissListener = dialogDismissListener;
    }

    public String getArgumentsString(String str) {
        return getArgumentsString(str, "");
    }

    public String getArgumentsString(String str, String str2) {
        if (getArguments() == null) {
            return str2;
        }
        return getArguments().getString(str, str2);
    }

    public int getArgumentsInt(String str) {
        return getArgumentsInt(str, 0);
    }

    public int getArgumentsInt(String str, int i) {
        if (getArguments() == null) {
            return i;
        }
        return getArguments().getInt(str, i);
    }

    public double getArgumentsDouble(String str) {
        return getArgumentsDouble(str, 0.0d);
    }

    public double getArgumentsDouble(String str, double d) {
        if (getArguments() == null) {
            return d;
        }
        return getArguments().getDouble(str, d);
    }

    public float getArgumentsFloat(String str) {
        return getArgumentsFloat(str, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public float getArgumentsFloat(String str, float f) {
        if (getArguments() == null) {
            return f;
        }
        return getArguments().getFloat(str, f);
    }

    public boolean getArgumentsBoolean(String str) {
        return getArgumentsBoolean(str, false);
    }

    public boolean getArgumentsBoolean(String str, boolean z) {
        if (getArguments() == null) {
            return z;
        }
        return getArguments().getBoolean(str, z);
    }

    private void initSoftInputListener() {
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            if (window != null) {
                window.getDecorView().setOnTouchListener(new -$$Lambda$BaseRxDialogFragment$qB5JbaHOtSeAtKS7YyvsGSdtFTg(this));
            }
        }
    }

    public static /* synthetic */ boolean lambda$initSoftInputListener$0(BaseRxDialogFragment baseRxDialogFragment, View view, MotionEvent motionEvent) {
        if (!(motionEvent.getAction() != 0 || baseRxDialogFragment.getDialog().getCurrentFocus() == null || baseRxDialogFragment.getDialog().getCurrentFocus().getWindowToken() == null)) {
            t.a(baseRxDialogFragment.getDialog());
        }
        return false;
    }
}

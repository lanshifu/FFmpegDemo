package com.tomatolive.library.ui.view.widget.matisse.internal.entity;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.IncapableDialog;

public class IncapableCause {
    public static final int DIALOG = 1;
    public static final int NONE = 2;
    public static final int TOAST = 0;
    private int mForm = 0;
    private String mMessage;
    private String mTitle;

    public IncapableCause(String str) {
        this.mMessage = str;
    }

    public IncapableCause(String str, String str2) {
        this.mTitle = str;
        this.mMessage = str2;
    }

    public IncapableCause(int i, String str) {
        this.mForm = i;
        this.mMessage = str;
    }

    public IncapableCause(int i, String str, String str2) {
        this.mForm = i;
        this.mTitle = str;
        this.mMessage = str2;
    }

    public static void handleCause(Context context, IncapableCause incapableCause) {
        if (incapableCause != null) {
            switch (incapableCause.mForm) {
                case 1:
                    IncapableDialog.newInstance(incapableCause.mTitle, incapableCause.mMessage).show(((FragmentActivity) context).getSupportFragmentManager(), IncapableDialog.class.getName());
                    break;
                case 2:
                    break;
                default:
                    Toast.makeText(context, incapableCause.mMessage, 0).show();
                    break;
            }
        }
    }
}

package com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import com.tomatolive.library.R;

public class IncapableDialog extends DialogFragment {
    public static final String EXTRA_MESSAGE = "extra_message";
    public static final String EXTRA_TITLE = "extra_title";

    public static IncapableDialog newInstance(String str, String str2) {
        IncapableDialog incapableDialog = new IncapableDialog();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, str);
        bundle.putString(EXTRA_MESSAGE, str2);
        incapableDialog.setArguments(bundle);
        return incapableDialog;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        String string = getArguments().getString(EXTRA_TITLE);
        String string2 = getArguments().getString(EXTRA_MESSAGE);
        Builder builder = new Builder(getActivity());
        if (!TextUtils.isEmpty(string)) {
            builder.setTitle(string);
        }
        if (!TextUtils.isEmpty(string2)) {
            builder.setMessage(string2);
        }
        builder.setPositiveButton(R.string.fq_matisse_button_ok, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }
}

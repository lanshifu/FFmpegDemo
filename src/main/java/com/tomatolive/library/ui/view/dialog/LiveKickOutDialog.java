package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.R;

public class LiveKickOutDialog extends BaseDialogFragment {
    public boolean getCancelOutside() {
        return false;
    }

    @SuppressLint({"ValidFragment"})
    private LiveKickOutDialog() {
    }

    public static LiveKickOutDialog newInstance() {
        Bundle bundle = new Bundle();
        LiveKickOutDialog liveKickOutDialog = new LiveKickOutDialog();
        liveKickOutDialog.setArguments(bundle);
        return liveKickOutDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_live_kick_out;
    }

    public void initView(View view) {
        view.findViewById(R.id.ll_button).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LiveKickOutDialog.this.dismiss();
            }
        });
    }
}

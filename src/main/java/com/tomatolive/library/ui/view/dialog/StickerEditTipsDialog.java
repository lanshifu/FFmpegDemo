package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.R;

public class StickerEditTipsDialog extends BaseDialogFragment {
    private static final String TIPS = "tips";
    private OnClickListener authListener;

    @SuppressLint({"ValidFragment"})
    private StickerEditTipsDialog() {
    }

    public static StickerEditTipsDialog newInstance(OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        StickerEditTipsDialog stickerEditTipsDialog = new StickerEditTipsDialog();
        stickerEditTipsDialog.setArguments(bundle);
        stickerEditTipsDialog.setSureListener(onClickListener);
        return stickerEditTipsDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_sticker_edit_tips;
    }

    public void initView(View view) {
        view.findViewById(R.id.tv_think).setOnClickListener(new -$$Lambda$StickerEditTipsDialog$358onK5YdGpyHCt6E4Nee6ln708(this));
        view.findViewById(R.id.tv_cancel).setOnClickListener(new -$$Lambda$StickerEditTipsDialog$Q8w0Lu_tnOh6XJEedKx5zLblXtA(this));
    }

    public static /* synthetic */ void lambda$initView$1(StickerEditTipsDialog stickerEditTipsDialog, View view) {
        if (stickerEditTipsDialog.authListener != null) {
            stickerEditTipsDialog.dismiss();
            stickerEditTipsDialog.authListener.onClick(view);
        }
    }

    private void setSureListener(OnClickListener onClickListener) {
        this.authListener = onClickListener;
    }
}

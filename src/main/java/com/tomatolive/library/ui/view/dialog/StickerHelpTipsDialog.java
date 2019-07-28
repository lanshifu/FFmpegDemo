package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.tomatolive.library.R;
import com.tomatolive.library.utils.i;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation.CornerType;

public class StickerHelpTipsDialog extends BaseDialogFragment {
    public double getHeightScale() {
        return 0.8d;
    }

    public double getWidthScale() {
        return 0.8d;
    }

    @SuppressLint({"ValidFragment"})
    private StickerHelpTipsDialog() {
    }

    public static StickerHelpTipsDialog newInstance() {
        Bundle bundle = new Bundle();
        StickerHelpTipsDialog stickerHelpTipsDialog = new StickerHelpTipsDialog();
        stickerHelpTipsDialog.setArguments(bundle);
        return stickerHelpTipsDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_sticker_help_tips;
    }

    public void initView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_see_img);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.iv_add_img);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.iv_move_img);
        i.a(this.mContext, (ImageView) view.findViewById(R.id.iv_save_img), R.drawable.fq_ic_sticker_help_1, 6, CornerType.ALL);
        i.a(this.mContext, imageView, R.drawable.fq_ic_sticker_help_2, 6, CornerType.ALL);
        i.a(this.mContext, imageView2, R.drawable.fq_ic_sticker_help_3, 6, CornerType.ALL);
        i.a(this.mContext, imageView3, R.drawable.fq_ic_sticker_help_4, 6, CornerType.ALL);
        view.setOnClickListener(new -$$Lambda$StickerHelpTipsDialog$yMQe5_cAzgQJKhrvTk2RFbobSl0(this));
    }
}

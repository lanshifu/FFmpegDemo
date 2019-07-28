package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.utils.i;

public class GiftBoxPresenterDialog extends BaseDialogFragment {
    private static final String AVATAR = "avatar";
    private static final String NAME = "name";

    @SuppressLint({"ValidFragment"})
    private GiftBoxPresenterDialog() {
    }

    public static GiftBoxPresenterDialog newInstance(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(AVATAR, str);
        bundle.putString("name", str2);
        GiftBoxPresenterDialog giftBoxPresenterDialog = new GiftBoxPresenterDialog();
        giftBoxPresenterDialog.setArguments(bundle);
        return giftBoxPresenterDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_giftbox_avatar;
    }

    public void initView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_avatar);
        ((TextView) view.findViewById(R.id.tv_name)).setText(getArgumentsString("name"));
        view.findViewById(R.id.iv_finish).setOnClickListener(new -$$Lambda$GiftBoxPresenterDialog$6VzyEdjAqVyrj79wNBCxLzEe1dY(this));
        i.a(this.mContext, imageView, getArgumentsString(AVATAR), 6, ContextCompat.getColor(this.mContext, R.color.fq_colorWhite));
    }
}

package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.tomatolive.library.R;

public class MyLiveBannedDialog extends BaseDialogFragment implements OnClickListener {
    private static final String TIPS = "tips";
    private OnClickListener authListener;

    @SuppressLint({"ValidFragment"})
    private MyLiveBannedDialog() {
    }

    public static MyLiveBannedDialog newInstance(String str, OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        MyLiveBannedDialog myLiveBannedDialog = new MyLiveBannedDialog();
        myLiveBannedDialog.setArguments(bundle);
        bundle.putString(TIPS, str);
        myLiveBannedDialog.setAuthListener(onClickListener);
        return myLiveBannedDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_auth_tips;
    }

    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_auth_tips);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_auth);
        textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.fq_text_black));
        textView.setText(Html.fromHtml(getString(R.string.fq_my_live_banned_dialog_tips, new Object[]{getArgumentsString(TIPS)})));
        textView2.setText(getString(R.string.fq_btn_sure));
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        view.findViewById(R.id.tv_auth).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (MyLiveBannedDialog.this.authListener != null) {
                    MyLiveBannedDialog.this.dismiss();
                    MyLiveBannedDialog.this.authListener.onClick(view);
                }
            }
        });
    }

    public void onClick(View view) {
        dismiss();
    }

    public OnClickListener getAuthListener() {
        return this.authListener;
    }

    private void setAuthListener(OnClickListener onClickListener) {
        this.authListener = onClickListener;
    }
}

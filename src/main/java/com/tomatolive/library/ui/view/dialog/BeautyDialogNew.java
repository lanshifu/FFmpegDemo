package com.tomatolive.library.ui.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.faceunity.beautycontrolview.a;
import com.tomatolive.library.R;
import com.tomatolive.library.faceunity.BeautyControlView;
import com.yalantis.ucrop.view.CropImageView;

public class BeautyDialogNew extends DialogFragment {
    private Dialog dialog;
    private a mFURenderer;

    public static BeautyDialogNew newInstance(a aVar, int i, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("faceProgress", i);
        bundle.putInt("bigEyeProgress", i2);
        bundle.putInt("whiteProgress", i3);
        BeautyDialogNew beautyDialogNew = new BeautyDialogNew();
        beautyDialogNew.setmFURenderer(aVar);
        beautyDialogNew.setArguments(bundle);
        return beautyDialogNew;
    }

    private void setmFURenderer(a aVar) {
        this.mFURenderer = aVar;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        this.dialog = getDialog();
        if (this.dialog.getWindow() != null) {
            this.dialog.getWindow().requestFeature(1);
            this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        View inflate = layoutInflater.inflate(R.layout.fq_layout_beauty_dialog_new, viewGroup, false);
        inflate.findViewById(R.id.root).setOnClickListener(new -$$Lambda$BeautyDialogNew$kmPx9Qb0LP8asppNKBBz2ofb0RY(this));
        ((BeautyControlView) inflate.findViewById(R.id.faceunity_control)).setOnFaceUnityControlListener(this.mFURenderer);
        return inflate;
    }

    public void onStart() {
        super.onStart();
        if (this.dialog != null && this.dialog.getWindow() != null) {
            this.dialog.getWindow().setLayout(-1, -1);
            this.dialog.getWindow().setGravity(80);
            this.dialog.getWindow().setDimAmount(CropImageView.DEFAULT_ASPECT_RATIO);
            this.dialog.setCancelable(true);
            this.dialog.setCanceledOnTouchOutside(true);
        }
    }
}

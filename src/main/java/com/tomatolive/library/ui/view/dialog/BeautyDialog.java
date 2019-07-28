package com.tomatolive.library.ui.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.tomatolive.library.R;
import com.yalantis.ucrop.view.CropImageView;

public class BeautyDialog extends DialogFragment implements OnSeekBarChangeListener {
    public static final String BEAUTY_KEY = "beauty_key";
    public static final int BEAUTY_PARAM = 3;
    public static final int RUBBY_PARAM = 2;
    public static final String RUDDY_KEY = "ruddy_key";
    public static final int WHITEN_PARAM = 1;
    public static final String WHITE_KEY = "white_key";
    private SeekBar beautySeekBar;
    private Dialog dialog;
    private BeautyParams mBeautyParams;
    private OnBeautyParamsChangeListener mBeautyParamsChangeListener;
    private SeekBar ruddySeekBar;
    private SeekBar whiteBar;

    public class BeautyParams {
        public int beauty = 3;
        public int ruddy = 5;
        public int whiten = 7;
    }

    public interface OnBeautyParamsChangeListener {
        void onBeautyParamsChange(BeautyParams beautyParams, int i);

        void onDismiss();
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public static BeautyDialog newInstance(int i, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt(RUDDY_KEY, i);
        bundle.putInt(BEAUTY_KEY, i2);
        bundle.putInt(WHITE_KEY, i3);
        BeautyDialog beautyDialog = new BeautyDialog();
        beautyDialog.setArguments(bundle);
        return beautyDialog;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        int id = seekBar.getId();
        if (id == R.id.ruddy_seekbar) {
            if (this.mBeautyParamsChangeListener != null) {
                this.mBeautyParams.ruddy = i;
                this.mBeautyParamsChangeListener.onBeautyParamsChange(this.mBeautyParams, 2);
            }
        } else if (id == R.id.beauty_seekbar) {
            if (this.mBeautyParamsChangeListener != null) {
                this.mBeautyParams.beauty = i;
                this.mBeautyParamsChangeListener.onBeautyParamsChange(this.mBeautyParams, 3);
            }
        } else if (id == R.id.whiten_seekbar && this.mBeautyParamsChangeListener != null) {
            this.mBeautyParams.whiten = i;
            this.mBeautyParamsChangeListener.onBeautyParamsChange(this.mBeautyParams, 1);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        int i;
        this.dialog = getDialog();
        if (this.dialog.getWindow() != null) {
            this.dialog.getWindow().requestFeature(1);
            this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        View inflate = layoutInflater.inflate(R.layout.fq_layout_beauty_dialog, viewGroup, false);
        this.ruddySeekBar = (SeekBar) inflate.findViewById(R.id.ruddy_seekbar);
        this.beautySeekBar = (SeekBar) inflate.findViewById(R.id.beauty_seekbar);
        this.whiteBar = (SeekBar) inflate.findViewById(R.id.whiten_seekbar);
        this.ruddySeekBar.setOnSeekBarChangeListener(this);
        this.beautySeekBar.setOnSeekBarChangeListener(this);
        this.whiteBar.setOnSeekBarChangeListener(this);
        this.mBeautyParams = new BeautyParams();
        SeekBar seekBar = this.ruddySeekBar;
        if (getArguments().getInt(RUDDY_KEY) == -1) {
            i = this.mBeautyParams.ruddy;
        } else {
            i = getArguments().getInt(RUDDY_KEY);
        }
        seekBar.setProgress(i);
        seekBar = this.beautySeekBar;
        if (getArguments().getInt(BEAUTY_KEY) == -1) {
            i = this.mBeautyParams.beauty;
        } else {
            i = getArguments().getInt(BEAUTY_KEY);
        }
        seekBar.setProgress(i);
        seekBar = this.whiteBar;
        if (getArguments().getInt(WHITE_KEY) == -1) {
            i = this.mBeautyParams.whiten;
        } else {
            i = getArguments().getInt(WHITE_KEY);
        }
        seekBar.setProgress(i);
        return inflate;
    }

    public void onStart() {
        super.onStart();
        if (this.dialog != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            if (this.dialog.getWindow() != null) {
                this.dialog.getWindow().setLayout(-1, -2);
                this.dialog.getWindow().setGravity(80);
                this.dialog.getWindow().setDimAmount(CropImageView.DEFAULT_ASPECT_RATIO);
            }
            this.dialog.setCancelable(true);
            this.dialog.setCanceledOnTouchOutside(true);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.mBeautyParamsChangeListener != null) {
            this.mBeautyParamsChangeListener.onDismiss();
        }
    }

    public void setBeautyParamsListener(OnBeautyParamsChangeListener onBeautyParamsChangeListener) {
        this.mBeautyParamsChangeListener = onBeautyParamsChangeListener;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mBeautyParamsChangeListener != null) {
            this.mBeautyParamsChangeListener = null;
        }
        this.ruddySeekBar.setOnSeekBarChangeListener(null);
        this.beautySeekBar.setOnSeekBarChangeListener(null);
        this.whiteBar.setOnSeekBarChangeListener(null);
    }
}

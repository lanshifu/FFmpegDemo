package com.tomatolive.library.ui.view.sticker.core.sticker;

import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;

public class IMGStickerMoveHelper {
    private static final Matrix M = new Matrix();
    private static final String TAG = "IMGStickerMoveHelper";
    private View mView;
    private float mX;
    private float mY;

    public IMGStickerMoveHelper(View view) {
        this.mView = view;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mX = motionEvent.getX();
            this.mY = motionEvent.getY();
            M.reset();
            M.setRotate(view.getRotation());
            return true;
        } else if (actionMasked != 2) {
            return false;
        } else {
            float[] fArr = new float[]{motionEvent.getX() - this.mX, motionEvent.getY() - this.mY};
            M.mapPoints(fArr);
            view.setTranslationX(this.mView.getTranslationX() + fArr[0]);
            view.setTranslationY(this.mView.getTranslationY() + fArr[1]);
            view.getTranslationX();
            return true;
        }
    }
}

package com.tomatolive.library.ui.view.sticker.core.sticker;

import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.tomatolive.library.ui.view.sticker.view.IMGStickerView;
import com.yalantis.ucrop.view.CropImageView;

public class IMGStickerAdjustHelper implements OnTouchListener {
    private static final String TAG = "IMGStickerAdjustHelper";
    private Matrix M = new Matrix();
    private float mCenterX;
    private float mCenterY;
    private IMGStickerView mContainer;
    private double mDegrees;
    private double mRadius;
    private View mView;

    public IMGStickerAdjustHelper(IMGStickerView iMGStickerView, View view) {
        this.mView = view;
        this.mContainer = iMGStickerView;
        this.mView.setOnTouchListener(this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.mCenterY = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mCenterX = CropImageView.DEFAULT_ASPECT_RATIO;
            float x2 = (this.mView.getX() + x) - this.mContainer.getPivotX();
            float y2 = (this.mView.getY() + y) - this.mContainer.getPivotY();
            this.mRadius = toLength(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, x2, y2);
            this.mDegrees = toDegrees(y2, x2);
            this.M.setTranslate(x2 - x, y2 - y);
            return true;
        } else if (action != 2) {
            return false;
        } else {
            float[] fArr = new float[]{motionEvent.getX(), motionEvent.getY()};
            double toLength = toLength(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (this.mView.getX() + fArr[0]) - this.mContainer.getPivotX(), (this.mView.getY() + fArr[1]) - this.mContainer.getPivotY());
            this.mContainer.addScale((float) (toLength / this.mRadius));
            this.mRadius = toLength;
            return true;
        }
    }

    private static double toDegrees(float f, float f2) {
        return Math.toDegrees(Math.atan2((double) f, (double) f2));
    }

    private static double toLength(float f, float f2, float f3, float f4) {
        f -= f3;
        f2 -= f4;
        return Math.sqrt((double) ((f * f) + (f2 * f2)));
    }
}

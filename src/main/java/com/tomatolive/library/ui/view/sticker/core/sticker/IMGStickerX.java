package com.tomatolive.library.ui.view.sticker.core.sticker;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.MotionEvent;
import com.yalantis.ucrop.view.CropImageView;

public class IMGStickerX {
    private static final float SIZE_ANCHOR = 60.0f;
    private static final float STROKE_FRAME = 6.0f;
    private boolean isActivated = true;
    private RectF mAdjustFrame = new RectF();
    private float mBaseRotate = CropImageView.DEFAULT_ASPECT_RATIO;
    private float mBaseScale = 1.0f;
    protected RectF mFrame = new RectF();
    private Paint mPaint = new Paint(1);
    protected float[] mPivotXY = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO};
    private RectF mRemoveFrame = new RectF();
    private float mRotate = CropImageView.DEFAULT_ASPECT_RATIO;
    private float mScale = 1.0f;
    private StickerEvent mTouchEvent;
    private float mX = CropImageView.DEFAULT_ASPECT_RATIO;
    private float mY = CropImageView.DEFAULT_ASPECT_RATIO;

    public enum StickerEvent {
        REMOVE,
        BODY,
        ADJUST
    }

    public IMGStickerX() {
        this.mPaint.setColor(-65536);
        this.mPaint.setStrokeWidth(STROKE_FRAME);
        this.mPaint.setStyle(Style.STROKE);
        this.mFrame.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 120.0f, 120.0f);
        this.mRemoveFrame.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 60.0f, 60.0f);
        this.mAdjustFrame.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 60.0f, 60.0f);
    }

    public boolean isActivated() {
        return this.isActivated;
    }

    public void setActivated(boolean z) {
        this.isActivated = z;
    }

    public void onMeasure(float f, float f2) {
        this.mFrame.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f, f2);
        this.mFrame.offset(this.mPivotXY[0] - this.mFrame.centerX(), this.mPivotXY[1] - this.mFrame.centerY());
    }

    public void onDraw(Canvas canvas) {
        if (this.isActivated) {
            canvas.save();
            canvas.rotate(this.mRotate, this.mPivotXY[0], this.mPivotXY[1]);
            canvas.drawRect(this.mFrame, this.mPaint);
            canvas.translate(this.mFrame.left, this.mFrame.top);
            canvas.drawRect(this.mRemoveFrame, this.mPaint);
            canvas.translate(this.mFrame.width() - this.mAdjustFrame.width(), this.mFrame.height() - this.mAdjustFrame.height());
            canvas.drawRect(this.mAdjustFrame, this.mPaint);
            canvas.restore();
        }
        canvas.rotate(this.mRotate, this.mPivotXY[0], this.mPivotXY[1]);
    }

    public void setScale(float f) {
        this.mScale = f;
    }

    public void setRotate(float f) {
        this.mRotate = f;
    }

    public void setBaseScale(float f) {
        this.mBaseScale = f;
    }

    public void setBaseRotate(float f) {
        this.mBaseRotate = f;
    }

    public void offset(float f, float f2) {
        float[] fArr = this.mPivotXY;
        fArr[0] = fArr[0] + f;
        float[] fArr2 = this.mPivotXY;
        fArr2[1] = fArr2[1] + f2;
        this.mFrame.offset(this.mPivotXY[0] - this.mFrame.centerX(), this.mPivotXY[1] - this.mFrame.centerY());
    }

    public StickerEvent onTouch(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (this.mTouchEvent == null && actionMasked != 0) {
            return null;
        }
        if (actionMasked == 0) {
            this.mX = motionEvent.getX();
            this.mY = motionEvent.getY();
            this.mTouchEvent = getTouchEvent(this.mX, this.mY);
            return this.mTouchEvent;
        } else if (actionMasked != 2) {
            return this.mTouchEvent;
        } else {
            if (this.mTouchEvent == StickerEvent.BODY) {
                offset(motionEvent.getX() - this.mX, motionEvent.getY() - this.mY);
                this.mX = motionEvent.getX();
                this.mY = motionEvent.getY();
            }
            return this.mTouchEvent;
        }
    }

    private StickerEvent getTouchEvent(float f, float f2) {
        float[] fArr = new float[]{f, f2};
        Matrix matrix = new Matrix();
        matrix.setRotate(this.mRotate, this.mFrame.centerX(), this.mFrame.centerY());
        matrix.mapPoints(fArr);
        if (!this.mFrame.contains(fArr[0], fArr[1])) {
            return null;
        }
        StickerEvent stickerEvent;
        if (isInsideRemove(fArr[0], fArr[1])) {
            stickerEvent = StickerEvent.REMOVE;
            this.mTouchEvent = stickerEvent;
            return stickerEvent;
        } else if (!isInsideAdjust(fArr[0], fArr[1])) {
            return StickerEvent.BODY;
        } else {
            stickerEvent = StickerEvent.ADJUST;
            this.mTouchEvent = stickerEvent;
            return stickerEvent;
        }
    }

    public void setTouchEvent(StickerEvent stickerEvent) {
        this.mTouchEvent = stickerEvent;
    }

    public boolean isInsideRemove(float f, float f2) {
        return this.mRemoveFrame.contains(f - this.mFrame.left, f2 - this.mFrame.top);
    }

    public boolean isInsideAdjust(float f, float f2) {
        return this.mAdjustFrame.contains((f - this.mFrame.right) + this.mAdjustFrame.width(), (f2 - this.mFrame.bottom) + this.mAdjustFrame.height());
    }

    public boolean isInside(float f, float f2) {
        float[] fArr = new float[]{f, f2};
        Matrix matrix = new Matrix();
        matrix.setRotate(this.mRotate, this.mFrame.centerX(), this.mFrame.centerY());
        matrix.mapPoints(fArr);
        return this.mFrame.contains(fArr[0], fArr[1]);
    }

    public void transform(Matrix matrix) {
        matrix.mapPoints(this.mPivotXY);
        this.mFrame.offset(this.mPivotXY[0] - this.mFrame.centerX(), this.mPivotXY[1] - this.mFrame.centerY());
    }
}

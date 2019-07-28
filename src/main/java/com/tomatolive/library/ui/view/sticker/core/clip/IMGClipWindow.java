package com.tomatolive.library.ui.view.sticker.core.clip;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import com.tomatolive.library.ui.view.sticker.core.clip.IMGClip.Anchor;
import com.tomatolive.library.ui.view.sticker.core.util.IMGUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.Array;

public class IMGClipWindow implements IMGClip {
    private static final int COLOR_CELL = -2130706433;
    private static final int COLOR_CORNER = -1;
    private static final int COLOR_FRAME = -1;
    private static final int COLOR_SHADE = -872415232;
    private static final float VERTICAL_RATIO = 0.8f;
    private Matrix M = new Matrix();
    private boolean isClipping = false;
    private boolean isHoming = false;
    private boolean isResetting = true;
    private boolean isShowShade = false;
    private RectF mBaseFrame = new RectF();
    private float[][] mBaseSizes = ((float[][]) Array.newInstance(float.class, new int[]{2, 4}));
    private float[] mCells = new float[16];
    private float[] mCorners = new float[32];
    private RectF mFrame = new RectF();
    private Paint mPaint = new Paint(1);
    private Path mShadePath = new Path();
    private RectF mTargetFrame = new RectF();
    private RectF mWin = new RectF();
    private RectF mWinFrame = new RectF();

    public IMGClipWindow() {
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeCap(Cap.SQUARE);
    }

    public void setClipWinSize(float f, float f2) {
        this.mWin.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f, f2);
        this.mWinFrame.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f, f2 * VERTICAL_RATIO);
        if (!this.mFrame.isEmpty()) {
            IMGUtils.center(this.mWinFrame, this.mFrame);
            this.mTargetFrame.set(this.mFrame);
        }
    }

    public void reset(RectF rectF, float f) {
        RectF rectF2 = new RectF();
        this.M.setRotate(f, rectF.centerX(), rectF.centerY());
        this.M.mapRect(rectF2, rectF);
        reset(rectF2.width(), rectF2.height());
    }

    private void reset(float f, float f2) {
        setResetting(true);
        this.mFrame.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f, f2);
        IMGUtils.fitCenter(this.mWinFrame, this.mFrame, 60.0f);
        this.mTargetFrame.set(this.mFrame);
    }

    public boolean homing() {
        this.mBaseFrame.set(this.mFrame);
        this.mTargetFrame.set(this.mFrame);
        IMGUtils.fitCenter(this.mWinFrame, this.mTargetFrame, 60.0f);
        int equals = this.mTargetFrame.equals(this.mBaseFrame) ^ 1;
        this.isHoming = equals;
        return equals;
    }

    public void homing(float f) {
        if (this.isHoming) {
            this.mFrame.set(this.mBaseFrame.left + ((this.mTargetFrame.left - this.mBaseFrame.left) * f), this.mBaseFrame.top + ((this.mTargetFrame.top - this.mBaseFrame.top) * f), this.mBaseFrame.right + ((this.mTargetFrame.right - this.mBaseFrame.right) * f), this.mBaseFrame.bottom + ((this.mTargetFrame.bottom - this.mBaseFrame.bottom) * f));
        }
    }

    public boolean isHoming() {
        return this.isHoming;
    }

    public void setHoming(boolean z) {
        this.isHoming = z;
    }

    public boolean isClipping() {
        return this.isClipping;
    }

    public void setClipping(boolean z) {
        this.isClipping = z;
    }

    public boolean isResetting() {
        return this.isResetting;
    }

    public void setResetting(boolean z) {
        this.isResetting = z;
    }

    public RectF getFrame() {
        return this.mFrame;
    }

    public RectF getWinFrame() {
        return this.mWinFrame;
    }

    public RectF getOffsetFrame(float f, float f2) {
        RectF rectF = new RectF(this.mFrame);
        rectF.offset(f, f2);
        return rectF;
    }

    public RectF getTargetFrame() {
        return this.mTargetFrame;
    }

    public RectF getOffsetTargetFrame(float f, float f2) {
        RectF rectF = new RectF(this.mFrame);
        rectF.offset(f, f2);
        return rectF;
    }

    public boolean isShowShade() {
        return this.isShowShade;
    }

    public void setShowShade(boolean z) {
        this.isShowShade = z;
    }

    public void onDraw(Canvas canvas) {
        if (!this.isResetting) {
            r1 = new float[2];
            int i = 0;
            r1[0] = this.mFrame.width();
            r1[1] = this.mFrame.height();
            for (int i2 = 0; i2 < this.mBaseSizes.length; i2++) {
                for (int i3 = 0; i3 < this.mBaseSizes[i2].length; i3++) {
                    this.mBaseSizes[i2][i3] = r1[i2] * CLIP_SIZE_RATIO[i3];
                }
            }
            for (int i4 = 0; i4 < this.mCells.length; i4++) {
                this.mCells[i4] = this.mBaseSizes[i4 & 1][(IMGClip.CLIP_CELL_STRIDES >>> (i4 << 1)) & 3];
            }
            while (i < this.mCorners.length) {
                this.mCorners[i] = (this.mBaseSizes[i & 1][(IMGClip.CLIP_CORNER_STRIDES >>> i) & 1] + CLIP_CORNER_SIZES[CLIP_CORNERS[i] & 3]) + CLIP_CORNER_STEPS[CLIP_CORNERS[i] >> 2];
                i++;
            }
            canvas.translate(this.mFrame.left, this.mFrame.top);
            this.mPaint.setStyle(Style.STROKE);
            this.mPaint.setColor(COLOR_CELL);
            this.mPaint.setStrokeWidth(3.0f);
            canvas.drawLines(this.mCells, this.mPaint);
            canvas.translate(-this.mFrame.left, -this.mFrame.top);
            this.mPaint.setColor(-1);
            this.mPaint.setStrokeWidth(8.0f);
            canvas.drawRect(this.mFrame, this.mPaint);
            canvas.translate(this.mFrame.left, this.mFrame.top);
            this.mPaint.setColor(-1);
            this.mPaint.setStrokeWidth(14.0f);
            canvas.drawLines(this.mCorners, this.mPaint);
        }
    }

    public void onDrawShade(Canvas canvas) {
        if (this.isShowShade) {
            this.mShadePath.reset();
            this.mShadePath.setFillType(FillType.WINDING);
            this.mShadePath.addRect(this.mFrame.left + 100.0f, this.mFrame.top + 100.0f, this.mFrame.right - 100.0f, this.mFrame.bottom - 100.0f, Direction.CW);
            this.mPaint.setColor(COLOR_SHADE);
            this.mPaint.setStyle(Style.FILL);
            canvas.drawPath(this.mShadePath, this.mPaint);
        }
    }

    public Anchor getAnchor(float f, float f2) {
        if (!Anchor.isCohesionContains(this.mFrame, -48.0f, f, f2) || Anchor.isCohesionContains(this.mFrame, 48.0f, f, f2)) {
            return null;
        }
        float[] cohesion = Anchor.cohesion(this.mFrame, CropImageView.DEFAULT_ASPECT_RATIO);
        float[] fArr = new float[]{f, f2};
        int i = 0;
        for (int i2 = 0; i2 < cohesion.length; i2++) {
            if (Math.abs(cohesion[i2] - fArr[i2 >> 1]) < 48.0f) {
                i |= 1 << i2;
            }
        }
        Anchor valueOf = Anchor.valueOf(i);
        if (valueOf != null) {
            this.isHoming = false;
        }
        return valueOf;
    }

    public void onScroll(Anchor anchor, float f, float f2) {
        anchor.move(this.mWinFrame, this.mFrame, f, f2);
    }
}

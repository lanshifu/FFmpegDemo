package com.tomatolive.library.ui.view.sticker.core;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.util.Log;
import com.tomatolive.library.ui.view.sticker.core.clip.IMGClip.Anchor;
import com.tomatolive.library.ui.view.sticker.core.clip.IMGClipWindow;
import com.tomatolive.library.ui.view.sticker.core.homing.IMGHoming;
import com.tomatolive.library.ui.view.sticker.core.sticker.IMGSticker;
import com.tomatolive.library.ui.view.sticker.core.util.IMGUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class IMGImage {
    private static final int COLOR_SHADE = -872415232;
    private static final boolean DEBUG = false;
    private static final Bitmap DEFAULT_IMAGE = Bitmap.createBitmap(100, 100, Config.ARGB_8888);
    private static final int MAX_SIZE = 10000;
    private static final int MIN_SIZE = 500;
    private static final String TAG = "IMGImage";
    private Matrix M;
    private boolean isAnimCanceled = false;
    private boolean isDrawClip = false;
    private boolean isFreezing;
    private boolean isInitialHoming;
    private boolean isRequestToBaseFitting = false;
    private boolean isSteady = true;
    private Anchor mAnchor;
    private List<IMGSticker> mBackStickers;
    private RectF mBackupClipFrame = new RectF();
    private float mBackupClipRotate = CropImageView.DEFAULT_ASPECT_RATIO;
    private RectF mClipFrame = new RectF();
    private IMGClipWindow mClipWin = new IMGClipWindow();
    private List<IMGPath> mDoodles;
    private IMGSticker mForeSticker;
    private RectF mFrame = new RectF();
    private Bitmap mImage;
    private IMGMode mMode = IMGMode.NONE;
    private Bitmap mMosaicImage;
    private Paint mMosaicPaint;
    private List<IMGPath> mMosaics;
    private Paint mPaint;
    private float mRotate = CropImageView.DEFAULT_ASPECT_RATIO;
    private Path mShade = new Path();
    private Paint mShadePaint;
    private float mTargetRotate = CropImageView.DEFAULT_ASPECT_RATIO;
    private RectF mTempClipFrame = new RectF();
    private RectF mWindow;

    public void onScaleBegin() {
    }

    public void onScaleEnd() {
    }

    public IMGImage() {
        this.isFreezing = this.mMode == IMGMode.CLIP;
        this.mWindow = new RectF();
        this.isInitialHoming = false;
        this.mBackStickers = new ArrayList();
        this.mDoodles = new ArrayList();
        this.mMosaics = new ArrayList();
        this.M = new Matrix();
        this.mShade.setFillType(FillType.WINDING);
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeWidth(20.0f);
        this.mPaint.setColor(-65536);
        this.mPaint.setPathEffect(new CornerPathEffect(20.0f));
        this.mPaint.setStrokeCap(Cap.ROUND);
        this.mPaint.setStrokeJoin(Join.ROUND);
        this.mImage = DEFAULT_IMAGE;
        if (this.mMode == IMGMode.CLIP) {
            initShadePaint();
        }
    }

    public void setBitmap(Bitmap bitmap) {
        Log.i("meme", "setBitmap...");
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mImage = bitmap;
            if (this.mMosaicImage != null) {
                this.mMosaicImage.recycle();
            }
            this.mMosaicImage = null;
            makeMosaicBitmap();
            onImageChanged();
        }
    }

    public IMGMode getMode() {
        return this.mMode;
    }

    public void setMode(IMGMode iMGMode) {
        if (this.mMode != iMGMode) {
            moveToBackground(this.mForeSticker);
            if (iMGMode == IMGMode.CLIP) {
                setFreezing(true);
            }
            this.mMode = iMGMode;
            if (this.mMode == IMGMode.CLIP) {
                initShadePaint();
                this.mBackupClipRotate = getRotate();
                this.mBackupClipFrame.set(this.mClipFrame);
                float scale = 1.0f / getScale();
                this.M.setTranslate(-this.mFrame.left, -this.mFrame.top);
                this.M.postScale(scale, scale);
                this.M.mapRect(this.mBackupClipFrame);
                this.mClipWin.reset(this.mClipFrame, getTargetRotate());
            } else {
                if (this.mMode == IMGMode.MOSAIC) {
                    makeMosaicBitmap();
                }
                this.mClipWin.setClipping(false);
            }
        }
    }

    private void rotateStickers(float f) {
        this.M.setRotate(f, this.mClipFrame.centerX(), this.mClipFrame.centerY());
        for (IMGSticker iMGSticker : this.mBackStickers) {
            this.M.mapRect(iMGSticker.getFrame());
            iMGSticker.setRotation(iMGSticker.getRotation() + f);
            iMGSticker.setX(iMGSticker.getFrame().centerX() - iMGSticker.getPivotX());
            iMGSticker.setY(iMGSticker.getFrame().centerY() - iMGSticker.getPivotY());
        }
    }

    private void initShadePaint() {
        if (this.mShadePaint == null) {
            this.mShadePaint = new Paint(1);
            this.mShadePaint.setColor(COLOR_SHADE);
            this.mShadePaint.setStyle(Style.FILL);
        }
    }

    public boolean isMosaicEmpty() {
        return this.mMosaics.isEmpty();
    }

    public boolean isDoodleEmpty() {
        return this.mDoodles.isEmpty();
    }

    public void undoDoodle() {
        if (!this.mDoodles.isEmpty()) {
            this.mDoodles.remove(this.mDoodles.size() - 1);
        }
    }

    public void undoMosaic() {
        if (!this.mMosaics.isEmpty()) {
            this.mMosaics.remove(this.mMosaics.size() - 1);
        }
    }

    public RectF getClipFrame() {
        return this.mClipFrame;
    }

    public IMGHoming clip(float f, float f2) {
        RectF offsetFrame = this.mClipWin.getOffsetFrame(f, f2);
        this.M.setRotate(-getRotate(), this.mClipFrame.centerX(), this.mClipFrame.centerY());
        this.M.mapRect(this.mClipFrame, offsetFrame);
        return new IMGHoming(f + (this.mClipFrame.centerX() - offsetFrame.centerX()), f2 + (this.mClipFrame.centerY() - offsetFrame.centerY()), getScale(), getRotate());
    }

    public void toBackupClip() {
        this.M.setScale(getScale(), getScale());
        this.M.postTranslate(this.mFrame.left, this.mFrame.top);
        this.M.mapRect(this.mClipFrame, this.mBackupClipFrame);
        setTargetRotate(this.mBackupClipRotate);
        this.isRequestToBaseFitting = true;
    }

    public void resetClip() {
        setTargetRotate(getRotate() - (getRotate() % 360.0f));
        this.mClipFrame.set(this.mFrame);
        this.mClipWin.reset(this.mClipFrame, getTargetRotate());
    }

    /* JADX WARNING: Missing block: B:11:0x005a, code skipped:
            return;
     */
    private void makeMosaicBitmap() {
        /*
        r6 = this;
        r0 = r6.mMosaicImage;
        if (r0 != 0) goto L_0x005a;
    L_0x0004:
        r0 = r6.mImage;
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        goto L_0x005a;
    L_0x0009:
        r0 = r6.mMode;
        r1 = com.tomatolive.library.ui.view.sticker.core.IMGMode.MOSAIC;
        if (r0 != r1) goto L_0x0059;
    L_0x000f:
        r0 = r6.mImage;
        r0 = r0.getWidth();
        r0 = (float) r0;
        r1 = 1115684864; // 0x42800000 float:64.0 double:5.51221563E-315;
        r0 = r0 / r1;
        r0 = java.lang.Math.round(r0);
        r2 = r6.mImage;
        r2 = r2.getHeight();
        r2 = (float) r2;
        r2 = r2 / r1;
        r1 = java.lang.Math.round(r2);
        r2 = 8;
        r0 = java.lang.Math.max(r0, r2);
        r1 = java.lang.Math.max(r1, r2);
        r2 = r6.mMosaicPaint;
        r3 = 0;
        if (r2 != 0) goto L_0x0051;
    L_0x0038:
        r2 = new android.graphics.Paint;
        r4 = 1;
        r2.<init>(r4);
        r6.mMosaicPaint = r2;
        r2 = r6.mMosaicPaint;
        r2.setFilterBitmap(r3);
        r2 = r6.mMosaicPaint;
        r4 = new android.graphics.PorterDuffXfermode;
        r5 = android.graphics.PorterDuff.Mode.SRC_IN;
        r4.<init>(r5);
        r2.setXfermode(r4);
    L_0x0051:
        r2 = r6.mImage;
        r0 = android.graphics.Bitmap.createScaledBitmap(r2, r0, r1, r3);
        r6.mMosaicImage = r0;
    L_0x0059:
        return;
    L_0x005a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.sticker.core.IMGImage.makeMosaicBitmap():void");
    }

    private void onImageChanged() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onImageChanged...mWindow.width=");
        stringBuilder.append(this.mWindow.width());
        stringBuilder.append(" mWindow.height");
        stringBuilder.append(this.mWindow.height());
        Log.i("meme", stringBuilder.toString());
        this.isInitialHoming = false;
        onWindowChanged(this.mWindow.width(), this.mWindow.height());
        if (this.mMode == IMGMode.CLIP) {
            this.mClipWin.reset(this.mClipFrame, getTargetRotate());
        }
    }

    public RectF getFrame() {
        return this.mFrame;
    }

    public boolean onClipHoming() {
        return this.mClipWin.homing();
    }

    public IMGHoming getStartHoming(float f, float f2) {
        return new IMGHoming(f, f2, getScale(), getRotate());
    }

    public IMGHoming getEndHoming(float f, float f2) {
        IMGHoming iMGHoming = new IMGHoming(f, f2, getScale(), getTargetRotate());
        RectF rectF;
        RectF rectF2;
        if (this.mMode == IMGMode.CLIP) {
            rectF = new RectF(this.mClipWin.getTargetFrame());
            rectF.offset(f, f2);
            if (this.mClipWin.isResetting()) {
                RectF rectF3 = new RectF();
                this.M.setRotate(getTargetRotate(), this.mClipFrame.centerX(), this.mClipFrame.centerY());
                this.M.mapRect(rectF3, this.mClipFrame);
                iMGHoming.rConcat(IMGUtils.fill(rectF, rectF3));
            } else {
                rectF2 = new RectF();
                if (this.mClipWin.isHoming()) {
                    this.M.setRotate(getTargetRotate() - getRotate(), this.mClipFrame.centerX(), this.mClipFrame.centerY());
                    this.M.mapRect(rectF2, this.mClipWin.getOffsetFrame(f, f2));
                    iMGHoming.rConcat(IMGUtils.fitHoming(rectF, rectF2, this.mClipFrame.centerX(), this.mClipFrame.centerY()));
                } else {
                    this.M.setRotate(getTargetRotate(), this.mClipFrame.centerX(), this.mClipFrame.centerY());
                    this.M.mapRect(rectF2, this.mFrame);
                    iMGHoming.rConcat(IMGUtils.fillHoming(rectF, rectF2, this.mClipFrame.centerX(), this.mClipFrame.centerY()));
                }
            }
        } else {
            rectF = new RectF();
            this.M.setRotate(getTargetRotate(), this.mClipFrame.centerX(), this.mClipFrame.centerY());
            this.M.mapRect(rectF, this.mClipFrame);
            rectF2 = new RectF(this.mWindow);
            rectF2.offset(f, f2);
            iMGHoming.rConcat(IMGUtils.fitHoming(rectF2, rectF, this.isRequestToBaseFitting));
            this.isRequestToBaseFitting = false;
        }
        return iMGHoming;
    }

    public <S extends IMGSticker> void addSticker(S s) {
        if (s != null) {
            moveToForeground(s);
        }
    }

    public void addPath(IMGPath iMGPath, float f, float f2) {
        if (iMGPath != null) {
            float scale = 1.0f / getScale();
            this.M.setTranslate(f, f2);
            this.M.postRotate(-getRotate(), this.mClipFrame.centerX(), this.mClipFrame.centerY());
            this.M.postTranslate(-this.mFrame.left, -this.mFrame.top);
            this.M.postScale(scale, scale);
            iMGPath.transform(this.M);
            switch (iMGPath.getMode()) {
                case DOODLE:
                    this.mDoodles.add(iMGPath);
                    break;
                case MOSAIC:
                    iMGPath.setWidth(iMGPath.getWidth() * scale);
                    this.mMosaics.add(iMGPath);
                    break;
            }
        }
    }

    private void moveToForeground(IMGSticker iMGSticker) {
        if (iMGSticker != null) {
            moveToBackground(this.mForeSticker);
            if (iMGSticker.isShowing()) {
                this.mForeSticker = iMGSticker;
                this.mBackStickers.remove(iMGSticker);
            } else {
                iMGSticker.show();
            }
        }
    }

    private void moveToBackground(IMGSticker iMGSticker) {
        if (iMGSticker != null) {
            if (iMGSticker.isShowing()) {
                iMGSticker.dismiss();
            } else {
                if (!this.mBackStickers.contains(iMGSticker)) {
                    this.mBackStickers.add(iMGSticker);
                }
                if (this.mForeSticker == iMGSticker) {
                    this.mForeSticker = null;
                }
            }
        }
    }

    public void stickAll() {
        moveToBackground(this.mForeSticker);
    }

    public void onDismiss(IMGSticker iMGSticker) {
        moveToBackground(iMGSticker);
    }

    public void onShowing(IMGSticker iMGSticker) {
        if (this.mForeSticker != iMGSticker) {
            moveToForeground(iMGSticker);
        }
    }

    public void onRemoveSticker(IMGSticker iMGSticker) {
        if (this.mForeSticker == iMGSticker) {
            this.mForeSticker = null;
        } else {
            this.mBackStickers.remove(iMGSticker);
        }
    }

    public void onWindowChanged(float f, float f2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onWindowChanged: width=");
        stringBuilder.append(f);
        stringBuilder.append(" height=");
        stringBuilder.append(f2);
        Log.i("meme", stringBuilder.toString());
        if (f != CropImageView.DEFAULT_ASPECT_RATIO && f2 != CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mWindow.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f, f2);
            if (this.isInitialHoming) {
                this.M.setTranslate(this.mWindow.centerX() - this.mClipFrame.centerX(), this.mWindow.centerY() - this.mClipFrame.centerY());
                this.M.mapRect(this.mFrame);
                this.M.mapRect(this.mClipFrame);
            } else {
                onInitialHoming(f, f2);
            }
            this.mClipWin.setClipWinSize(f, f2);
        }
    }

    private void onInitialHoming(float f, float f2) {
        this.mFrame.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f, f2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onInitialHoming: mFrame ==> width=");
        stringBuilder.append(f);
        stringBuilder.append(" height=");
        stringBuilder.append(f2);
        Log.i("meme", stringBuilder.toString());
        this.mClipFrame.set(this.mFrame);
        this.mClipWin.setClipWinSize(f, f2);
        if (!this.mClipFrame.isEmpty()) {
            toBaseHoming();
            this.isInitialHoming = true;
            onInitialHomingDone();
        }
    }

    private void toBaseHoming() {
        if (!this.mClipFrame.isEmpty()) {
            float min = Math.min(this.mWindow.width() / this.mClipFrame.width(), this.mWindow.height() / this.mClipFrame.height());
            this.M.setScale(min, min, this.mClipFrame.centerX(), this.mClipFrame.centerY());
            this.M.postTranslate(this.mWindow.centerX() - this.mClipFrame.centerX(), this.mWindow.centerY() - this.mClipFrame.centerY());
            this.M.mapRect(this.mFrame);
            this.M.mapRect(this.mClipFrame);
        }
    }

    private void onInitialHomingDone() {
        if (this.mMode == IMGMode.CLIP) {
            this.mClipWin.reset(this.mClipFrame, getTargetRotate());
        }
    }

    public void onDrawImage(Canvas canvas) {
        canvas.clipRect(this.mClipWin.isClipping() ? this.mFrame : this.mClipFrame);
        canvas.drawBitmap(this.mImage, null, this.mFrame, null);
    }

    public int onDrawMosaicsPath(Canvas canvas) {
        int saveLayer = canvas.saveLayer(this.mFrame, null, 31);
        if (!isMosaicEmpty()) {
            canvas.save();
            float scale = getScale();
            canvas.translate(this.mFrame.left, this.mFrame.top);
            canvas.scale(scale, scale);
            for (IMGPath onDrawMosaic : this.mMosaics) {
                onDrawMosaic.onDrawMosaic(canvas, this.mPaint);
            }
            canvas.restore();
        }
        return saveLayer;
    }

    public void onDrawMosaic(Canvas canvas, int i) {
        canvas.drawBitmap(this.mMosaicImage, null, this.mFrame, this.mMosaicPaint);
        canvas.restoreToCount(i);
    }

    public void onDrawDoodles(Canvas canvas) {
        if (!isDoodleEmpty()) {
            canvas.save();
            float scale = getScale();
            canvas.translate(this.mFrame.left, this.mFrame.top);
            canvas.scale(scale, scale);
            for (IMGPath onDrawDoodle : this.mDoodles) {
                onDrawDoodle.onDrawDoodle(canvas, this.mPaint);
            }
            canvas.restore();
        }
    }

    public void onDrawStickerClip(Canvas canvas) {
        this.M.setRotate(getRotate(), this.mClipFrame.centerX(), this.mClipFrame.centerY());
        this.M.mapRect(this.mTempClipFrame, this.mClipWin.isClipping() ? this.mFrame : this.mClipFrame);
        canvas.clipRect(this.mTempClipFrame);
    }

    public void onDrawStickers(Canvas canvas) {
        if (!this.mBackStickers.isEmpty()) {
            canvas.save();
            for (IMGSticker iMGSticker : this.mBackStickers) {
                if (!iMGSticker.isShowing()) {
                    float x = iMGSticker.getX() + iMGSticker.getPivotX();
                    float y = iMGSticker.getY() + iMGSticker.getPivotY();
                    canvas.save();
                    this.M.setTranslate(iMGSticker.getX(), iMGSticker.getY());
                    this.M.postScale(iMGSticker.getScale(), iMGSticker.getScale(), x, y);
                    this.M.postRotate(iMGSticker.getRotation(), x, y);
                    canvas.concat(this.M);
                    iMGSticker.onSticker(canvas);
                    canvas.restore();
                }
            }
            canvas.restore();
        }
    }

    public void onDrawShade(Canvas canvas) {
        if (this.mMode == IMGMode.CLIP && this.isSteady) {
            this.mShade.reset();
            this.mShade.addRect(this.mFrame.left - 2.0f, this.mFrame.top - 2.0f, this.mFrame.right + 2.0f, this.mFrame.bottom + 2.0f, Direction.CW);
            this.mShade.addRect(this.mClipFrame, Direction.CCW);
            canvas.drawPath(this.mShade, this.mShadePaint);
        }
    }

    public void onDrawClip(Canvas canvas, float f, float f2) {
        if (this.mMode == IMGMode.CLIP) {
            this.mClipWin.onDraw(canvas);
        }
    }

    public void onTouchDown(float f, float f2) {
        this.isSteady = false;
        moveToBackground(this.mForeSticker);
        if (this.mMode == IMGMode.CLIP) {
            this.mAnchor = this.mClipWin.getAnchor(f, f2);
        }
    }

    public void onTouchUp(float f, float f2) {
        if (this.mAnchor != null) {
            this.mAnchor = null;
        }
    }

    public void onSteady(float f, float f2) {
        this.isSteady = true;
        onClipHoming();
        this.mClipWin.setShowShade(true);
    }

    public IMGHoming onScroll(float f, float f2, float f3, float f4) {
        if (this.mMode == IMGMode.CLIP) {
            this.mClipWin.setShowShade(false);
            if (this.mAnchor != null) {
                this.mClipWin.onScroll(this.mAnchor, f3, f4);
                RectF rectF = new RectF();
                this.M.setRotate(getRotate(), this.mClipFrame.centerX(), this.mClipFrame.centerY());
                this.M.mapRect(rectF, this.mFrame);
                RectF offsetFrame = this.mClipWin.getOffsetFrame(f, f2);
                IMGHoming iMGHoming = new IMGHoming(f, f2, getScale(), getTargetRotate());
                iMGHoming.rConcat(IMGUtils.fillHoming(offsetFrame, rectF, this.mClipFrame.centerX(), this.mClipFrame.centerY()));
                return iMGHoming;
            }
        }
        return null;
    }

    public float getTargetRotate() {
        return this.mTargetRotate;
    }

    public void setTargetRotate(float f) {
        this.mTargetRotate = f;
    }

    public void rotate(int i) {
        this.mTargetRotate = (float) (Math.round((this.mRotate + ((float) i)) / 90.0f) * 90);
        this.mClipWin.reset(this.mClipFrame, getTargetRotate());
    }

    public float getRotate() {
        return this.mRotate;
    }

    public void setRotate(float f) {
        this.mRotate = f;
    }

    public float getScale() {
        return (this.mFrame.width() * 1.0f) / ((float) this.mImage.getWidth());
    }

    public void setScale(float f) {
        setScale(f, this.mClipFrame.centerX(), this.mClipFrame.centerY());
    }

    public void setScale(float f, float f2, float f3) {
        onScale(f / getScale(), f2, f3);
    }

    public void onScale(float f, float f2, float f3) {
        if (f != 1.0f) {
            if (Math.max(this.mClipFrame.width(), this.mClipFrame.height()) >= 10000.0f || Math.min(this.mClipFrame.width(), this.mClipFrame.height()) <= 500.0f) {
                f += (1.0f - f) / 2.0f;
            }
            this.M.setScale(f, f, f2, f3);
            this.M.mapRect(this.mFrame);
            this.M.mapRect(this.mClipFrame);
            this.mFrame.contains(this.mClipFrame);
            for (IMGSticker iMGSticker : this.mBackStickers) {
                this.M.mapRect(iMGSticker.getFrame());
                float x = iMGSticker.getX() + iMGSticker.getPivotX();
                float y = iMGSticker.getY() + iMGSticker.getPivotY();
                iMGSticker.addScale(f);
                iMGSticker.setX((iMGSticker.getX() + iMGSticker.getFrame().centerX()) - x);
                iMGSticker.setY((iMGSticker.getY() + iMGSticker.getFrame().centerY()) - y);
            }
        }
    }

    public void onHomingStart(boolean z) {
        this.isAnimCanceled = false;
        this.isDrawClip = true;
    }

    public void onHoming(float f) {
        this.mClipWin.homing(f);
    }

    public boolean onHomingEnd(float f, float f2, boolean z) {
        this.isDrawClip = true;
        if (this.mMode == IMGMode.CLIP) {
            int i = this.isAnimCanceled ^ 1;
            this.mClipWin.setHoming(false);
            this.mClipWin.setClipping(true);
            this.mClipWin.setResetting(false);
            return i;
        }
        if (this.isFreezing && !this.isAnimCanceled) {
            setFreezing(false);
        }
        return false;
    }

    public boolean isFreezing() {
        return this.isFreezing;
    }

    private void setFreezing(boolean z) {
        if (z != this.isFreezing) {
            rotateStickers(z ? -getRotate() : getTargetRotate());
            this.isFreezing = z;
        }
    }

    public void onHomingCancel(boolean z) {
        this.isAnimCanceled = true;
        Log.d(TAG, "Homing cancel");
    }

    public void release() {
        if (this.mImage != null && !this.mImage.isRecycled()) {
            this.mImage.recycle();
        }
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        super.finalize();
        if (DEFAULT_IMAGE != null) {
            DEFAULT_IMAGE.recycle();
        }
    }
}

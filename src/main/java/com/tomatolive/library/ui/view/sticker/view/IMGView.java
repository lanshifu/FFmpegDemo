package com.tomatolive.library.ui.view.sticker.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
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
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.tomatolive.library.model.db.StickerEntity;
import com.tomatolive.library.ui.view.sticker.core.IMGImage;
import com.tomatolive.library.ui.view.sticker.core.IMGMode;
import com.tomatolive.library.ui.view.sticker.core.IMGPath;
import com.tomatolive.library.ui.view.sticker.core.IMGText;
import com.tomatolive.library.ui.view.sticker.core.anim.IMGHomingAnimator;
import com.tomatolive.library.ui.view.sticker.core.homing.IMGHoming;
import com.tomatolive.library.ui.view.sticker.core.sticker.IMGSticker;
import com.tomatolive.library.ui.view.sticker.core.sticker.IMGStickerPortrait;
import com.tomatolive.library.ui.view.sticker.core.sticker.IMGStickerPortrait.Callback;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;

public class IMGView extends FrameLayout implements AnimatorListener, AnimatorUpdateListener, OnScaleGestureListener, Callback, Runnable {
    private static final boolean DEBUG = true;
    private static final String TAG = "IMGView";
    private Paint mDoodlePaint;
    private IMGHomingAnimator mHomingAnimator;
    private IMGImage mImage;
    private Paint mMosaicPaint;
    private Pen mPen;
    private int mPointerCount;
    private IMGMode mPreMode;

    private class MoveAdapter extends SimpleOnGestureListener {
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        private MoveAdapter() {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return IMGView.this.onScroll(f, f2);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }
    }

    private static class Pen extends IMGPath {
        private int identity;

        private Pen() {
            this.identity = CheckView.UNCHECKED;
        }

        /* synthetic */ Pen(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* Access modifiers changed, original: 0000 */
        public void reset() {
            this.path.reset();
            this.identity = CheckView.UNCHECKED;
        }

        /* Access modifiers changed, original: 0000 */
        public void reset(float f, float f2) {
            this.path.reset();
            this.path.moveTo(f, f2);
            this.identity = CheckView.UNCHECKED;
        }

        /* Access modifiers changed, original: 0000 */
        public void setIdentity(int i) {
            this.identity = i;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isIdentity(int i) {
            return this.identity == i;
        }

        /* Access modifiers changed, original: 0000 */
        public void lineTo(float f, float f2) {
            this.path.lineTo(f, f2);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isEmpty() {
            return this.path.isEmpty();
        }

        /* Access modifiers changed, original: 0000 */
        public IMGPath toPath() {
            return new IMGPath(new Path(this.path), getMode(), getColor(), getWidth());
        }
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public IMGView(Context context) {
        this(context, null, 0);
    }

    public IMGView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IMGView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPreMode = IMGMode.NONE;
        this.mImage = new IMGImage();
        this.mPen = new Pen();
        this.mPointerCount = 0;
        this.mDoodlePaint = new Paint(1);
        this.mMosaicPaint = new Paint(1);
        this.mDoodlePaint.setStyle(Style.STROKE);
        this.mDoodlePaint.setStrokeWidth(20.0f);
        this.mDoodlePaint.setColor(-65536);
        this.mDoodlePaint.setPathEffect(new CornerPathEffect(20.0f));
        this.mDoodlePaint.setStrokeCap(Cap.ROUND);
        this.mDoodlePaint.setStrokeJoin(Join.ROUND);
        this.mMosaicPaint.setStyle(Style.STROKE);
        this.mMosaicPaint.setStrokeWidth(72.0f);
        this.mMosaicPaint.setColor(-16777216);
        this.mMosaicPaint.setPathEffect(new CornerPathEffect(72.0f));
        this.mMosaicPaint.setStrokeCap(Cap.ROUND);
        this.mMosaicPaint.setStrokeJoin(Join.ROUND);
        initialize(context);
    }

    private void initialize(Context context) {
        this.mPen.setMode(this.mImage.getMode());
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.mImage.setBitmap(bitmap);
        invalidate();
    }

    public void setMode(IMGMode iMGMode) {
        this.mPreMode = this.mImage.getMode();
        this.mImage.setMode(iMGMode);
        this.mPen.setMode(iMGMode);
        onHoming();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isHoming() {
        return this.mHomingAnimator != null && this.mHomingAnimator.isRunning();
    }

    private void onHoming() {
        invalidate();
        stopHoming();
        startHoming(this.mImage.getStartHoming((float) getScrollX(), (float) getScrollY()), this.mImage.getEndHoming((float) getScrollX(), (float) getScrollY()));
    }

    private void startHoming(IMGHoming iMGHoming, IMGHoming iMGHoming2) {
        if (this.mHomingAnimator == null) {
            this.mHomingAnimator = new IMGHomingAnimator();
            this.mHomingAnimator.addUpdateListener(this);
            this.mHomingAnimator.addListener(this);
        }
        this.mHomingAnimator.setHomingValues(iMGHoming, iMGHoming2);
        this.mHomingAnimator.start();
    }

    private void stopHoming() {
        if (this.mHomingAnimator != null) {
            this.mHomingAnimator.cancel();
        }
    }

    public void doRotate() {
        if (!isHoming()) {
            this.mImage.rotate(-90);
            onHoming();
        }
    }

    public void resetClip() {
        this.mImage.resetClip();
        onHoming();
    }

    public void doClip() {
        this.mImage.clip((float) getScrollX(), (float) getScrollY());
        setMode(this.mPreMode);
        onHoming();
    }

    public void cancelClip() {
        this.mImage.toBackupClip();
        setMode(this.mPreMode);
    }

    public void setPenColor(int i) {
        this.mPen.setColor(i);
    }

    public boolean isDoodleEmpty() {
        return this.mImage.isDoodleEmpty();
    }

    public void undoDoodle() {
        this.mImage.undoDoodle();
        invalidate();
    }

    public boolean isMosaicEmpty() {
        return this.mImage.isMosaicEmpty();
    }

    public void undoMosaic() {
        this.mImage.undoMosaic();
        invalidate();
    }

    public IMGMode getMode() {
        return this.mImage.getMode();
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        onDrawImages(canvas);
    }

    private void onDrawImages(Canvas canvas) {
        canvas.save();
        RectF clipFrame = this.mImage.getClipFrame();
        canvas.rotate(this.mImage.getRotate(), clipFrame.centerX(), clipFrame.centerY());
        if (this.mImage.isFreezing()) {
            this.mImage.onDrawStickers(canvas);
        }
        this.mImage.onDrawShade(canvas);
        canvas.restore();
        if (!this.mImage.isFreezing()) {
            this.mImage.onDrawStickerClip(canvas);
            this.mImage.onDrawStickers(canvas);
        }
        if (this.mImage.getMode() == IMGMode.CLIP) {
            canvas.save();
            canvas.translate((float) getScrollX(), (float) getScrollY());
            this.mImage.onDrawClip(canvas, (float) getScrollX(), (float) getScrollY());
            canvas.restore();
        }
    }

    public Bitmap saveBitmap() {
        this.mImage.stickAll();
        this.mImage.getScale();
        RectF rectF = new RectF(this.mImage.getClipFrame());
        Matrix matrix = new Matrix();
        matrix.setRotate(this.mImage.getRotate(), rectF.centerX(), rectF.centerY());
        matrix.mapRect(rectF);
        matrix.mapRect(rectF);
        Bitmap createBitmap = Bitmap.createBitmap(Math.round(rectF.width()), Math.round(rectF.height()), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate(-rectF.left, -rectF.top);
        onDrawImages(canvas);
        return createBitmap;
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.mImage.onWindowChanged((float) (i3 - i), (float) (i4 - i2));
        }
    }

    public <V extends View & IMGSticker> void addStickerView(V v, LayoutParams layoutParams) {
        if (v != null) {
            addView(v, layoutParams);
            ((IMGStickerPortrait) v).registerCallback(this);
            this.mImage.addSticker((IMGSticker) v);
        }
    }

    public void addStickerText(IMGText iMGText) {
        IMGStickerTextView iMGStickerTextView = new IMGStickerTextView(getContext());
        iMGStickerTextView.setText(iMGText);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        iMGStickerTextView.setX((float) getScrollX());
        iMGStickerTextView.setY((float) getScrollY());
        addStickerView(iMGStickerTextView, layoutParams);
    }

    public void addLastStickerText(IMGText iMGText, final StickerEntity stickerEntity) {
        final IMGStickerTextView iMGStickerTextView = new IMGStickerTextView(getContext());
        iMGStickerTextView.setText(iMGText);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        iMGStickerTextView.setX((float) getScrollX());
        iMGStickerTextView.setY((float) getScrollY());
        addStickerView(iMGStickerTextView, layoutParams);
        iMGStickerTextView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                iMGStickerTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                iMGStickerTextView.setTranslationX(stickerEntity.translationX);
                iMGStickerTextView.setTranslationY(stickerEntity.translationY);
                iMGStickerTextView.addScale(stickerEntity.scale);
            }
        });
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        boolean z = onInterceptTouch(motionEvent) || super.onInterceptTouchEvent(motionEvent);
        return z;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean onInterceptTouch(MotionEvent motionEvent) {
        if (isHoming()) {
            stopHoming();
            return true;
        } else if (this.mImage.getMode() == IMGMode.CLIP) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 3) {
            switch (actionMasked) {
                case 0:
                    removeCallbacks(this);
                    break;
                case 1:
                    break;
            }
        }
        postDelayed(this, 1200);
        return onTouch(motionEvent);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean onTouch(MotionEvent motionEvent) {
        if (isHoming()) {
            return false;
        }
        this.mPointerCount = motionEvent.getPointerCount();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 3) {
            switch (actionMasked) {
                case 0:
                    this.mImage.onTouchDown(motionEvent.getX(), motionEvent.getY());
                    break;
                case 1:
                    break;
            }
        }
        this.mImage.onTouchUp((float) getScrollX(), (float) getScrollY());
        onHoming();
        return true;
    }

    private boolean onTouchPath(MotionEvent motionEvent) {
        boolean z = false;
        switch (motionEvent.getActionMasked()) {
            case 0:
                return onPathBegin(motionEvent);
            case 1:
            case 3:
                if (this.mPen.isIdentity(motionEvent.getPointerId(0)) && onPathDone()) {
                    z = true;
                }
                return z;
            case 2:
                return onPathMove(motionEvent);
            default:
                return false;
        }
    }

    private boolean onPathBegin(MotionEvent motionEvent) {
        this.mPen.reset(motionEvent.getX(), motionEvent.getY());
        this.mPen.setIdentity(motionEvent.getPointerId(0));
        return true;
    }

    private boolean onPathMove(MotionEvent motionEvent) {
        if (!this.mPen.isIdentity(motionEvent.getPointerId(0))) {
            return false;
        }
        this.mPen.lineTo(motionEvent.getX(), motionEvent.getY());
        invalidate();
        return true;
    }

    private boolean onPathDone() {
        if (this.mPen.isEmpty()) {
            return false;
        }
        this.mImage.addPath(this.mPen.toPath(), (float) getScrollX(), (float) getScrollY());
        this.mPen.reset();
        invalidate();
        return true;
    }

    public void run() {
        if (!onSteady()) {
            postDelayed(this, 500);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean onSteady() {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onSteady: isHoming=");
        stringBuilder.append(isHoming());
        Log.d(str, stringBuilder.toString());
        if (isHoming()) {
            return false;
        }
        this.mImage.onSteady((float) getScrollX(), (float) getScrollY());
        onHoming();
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this);
        this.mImage.release();
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        if (this.mPointerCount <= 1) {
            return false;
        }
        this.mImage.onScale(scaleGestureDetector.getScaleFactor(), ((float) getScrollX()) + scaleGestureDetector.getFocusX(), ((float) getScrollY()) + scaleGestureDetector.getFocusY());
        invalidate();
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        if (this.mPointerCount <= 1) {
            return false;
        }
        this.mImage.onScaleBegin();
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        this.mImage.onScaleEnd();
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.mImage.onHoming(valueAnimator.getAnimatedFraction());
        toApplyHoming((IMGHoming) valueAnimator.getAnimatedValue());
    }

    private void toApplyHoming(IMGHoming iMGHoming) {
        this.mImage.setScale(iMGHoming.scale);
        this.mImage.setRotate(iMGHoming.rotate);
        if (!onScrollTo(Math.round(iMGHoming.x), Math.round(iMGHoming.y))) {
            invalidate();
        }
    }

    private boolean onScrollTo(int i, int i2) {
        if (getScrollX() == i && getScrollY() == i2) {
            return false;
        }
        scrollTo(i, i2);
        return true;
    }

    public <V extends View & IMGSticker> void onDismiss(V v) {
        this.mImage.onDismiss((IMGSticker) v);
        invalidate();
    }

    public <V extends View & IMGSticker> void onShowing(V v) {
        this.mImage.onShowing((IMGSticker) v);
        invalidate();
    }

    public <V extends View & IMGSticker> boolean onRemove(V v) {
        if (this.mImage != null) {
            this.mImage.onRemoveSticker((IMGSticker) v);
        }
        ((IMGStickerPortrait) v).unregisterCallback(this);
        ViewParent parent = v.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(v);
        }
        return true;
    }

    public void onAnimationStart(Animator animator) {
        Log.d(TAG, "onAnimationStart");
        this.mImage.onHomingStart(this.mHomingAnimator.isRotate());
    }

    public void onAnimationEnd(Animator animator) {
        Log.d(TAG, "onAnimationEnd");
        if (this.mImage.onHomingEnd((float) getScrollX(), (float) getScrollY(), this.mHomingAnimator.isRotate())) {
            toApplyHoming(this.mImage.clip((float) getScrollX(), (float) getScrollY()));
        }
    }

    public void onAnimationCancel(Animator animator) {
        Log.d(TAG, "onAnimationCancel");
        this.mImage.onHomingCancel(this.mHomingAnimator.isRotate());
    }

    private boolean onScroll(float f, float f2) {
        IMGHoming onScroll = this.mImage.onScroll((float) getScrollX(), (float) getScrollY(), -f, -f2);
        if (onScroll == null) {
            return onScrollTo(getScrollX() + Math.round(f), getScrollY() + Math.round(f2));
        }
        toApplyHoming(onScroll);
        return true;
    }
}

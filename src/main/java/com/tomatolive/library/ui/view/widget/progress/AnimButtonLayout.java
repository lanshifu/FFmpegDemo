package com.tomatolive.library.ui.view.widget.progress;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.tomatolive.library.R;
import com.yalantis.ucrop.view.CropImageView;

public class AnimButtonLayout extends LinearLayout {
    private final long ANIM_DOWN_DURATION = 128;
    private final long ANIM_UP_DURATION = 352;
    private final int DEFAULT_COLOR = -7829368;
    private final String PROPERTY_CANVAS_SCALE = "canvasScale";
    private float mCanvasScale = 1.0f;
    private float mCenterX;
    private float mCenterY;
    private float mDensity;
    private AnimDownloadProgressButton mDownloadProgressButton;
    private TimeInterpolator mInterpolator;
    private ValueAnimator mLayoutDownAnimator;
    private int mLayoutHeight;
    private ValueAnimator mLayoutUpAnimator;
    private int mLayoutWidth;
    private float mMinScale = 0.95f;
    private Drawable mShadowDrawable;
    private float mTargetScale = 1.0f;

    public AnimButtonLayout(Context context) {
        super(context);
        this.mDownloadProgressButton = new AnimDownloadProgressButton(context);
        this.mDownloadProgressButton.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.mDownloadProgressButton);
        init(context, null);
    }

    public AnimButtonLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
        this.mDownloadProgressButton = new AnimDownloadProgressButton(context, attributeSet);
        this.mDownloadProgressButton.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.mDownloadProgressButton);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (VERSION.SDK_INT >= 21) {
            this.mInterpolator = new PathInterpolator(0.33f, CropImageView.DEFAULT_ASPECT_RATIO, 0.33f, 1.0f);
        } else {
            this.mInterpolator = new AccelerateDecelerateInterpolator();
        }
        this.mShadowDrawable = getResources().getDrawable(R.drawable.gradient_layout_shadow);
        this.mDensity = getResources().getDisplayMetrics().density;
    }

    /* Access modifiers changed, original: protected */
    public void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.scale(this.mCanvasScale, this.mCanvasScale, this.mCenterX, this.mCenterY);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mCanvasScale);
        stringBuilder.append("");
        Log.w("tan", stringBuilder.toString());
        drawShadow(canvas);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    private void drawShadow(Canvas canvas) {
        if (this.mShadowDrawable != null) {
            canvas.save();
            float f = 1.0f - ((1.0f - this.mCanvasScale) * 6.0f);
            canvas.scale(f, f, this.mCenterX, this.mCenterY);
            canvas.translate(CropImageView.DEFAULT_ASPECT_RATIO, ((((this.mCanvasScale - 1.0f) * ((float) this.mLayoutHeight)) * 6.0f) + (((float) this.mLayoutHeight) * 0.4f)) + this.mDensity);
            this.mShadowDrawable.draw(canvas);
            canvas.restore();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (!isEnabled() || !isClickable()) {
            return false;
        }
        switch (action) {
            case 0:
                handleActionDown(motionEvent);
                Log.w("tan", "action down");
                break;
            case 1:
                handleActionUp(motionEvent);
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Log.w("tan", "onsize change");
        this.mLayoutWidth = i;
        this.mLayoutHeight = i2;
        this.mCenterX = (float) (this.mLayoutWidth / 2);
        this.mCenterY = (float) (this.mLayoutHeight / 2);
        if (this.mShadowDrawable != null) {
            this.mShadowDrawable.setColorFilter(-7829368, Mode.SRC_IN);
            this.mShadowDrawable.setBounds(0, 0, this.mLayoutWidth, this.mLayoutHeight);
            if (getParent() instanceof ViewGroup) {
                ((ViewGroup) getParent()).setClipChildren(false);
            }
        }
    }

    private void handleActionDown(MotionEvent motionEvent) {
        setupLayoutDownAnimator();
        this.mLayoutDownAnimator.start();
    }

    private void handleActionUp(MotionEvent motionEvent) {
        setupLayoutUpAnimator();
        this.mLayoutUpAnimator.start();
    }

    private void setupLayoutDownAnimator() {
        this.mLayoutDownAnimator = ValueAnimator.ofFloat(new float[]{1.0f, 0.95f});
        this.mLayoutDownAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimButtonLayout.this.mCanvasScale = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                AnimButtonLayout.this.invalidate();
            }
        });
        this.mLayoutDownAnimator.setInterpolator(this.mInterpolator);
        this.mLayoutDownAnimator.setDuration(128);
    }

    private void setupLayoutUpAnimator() {
        this.mLayoutUpAnimator = ValueAnimator.ofFloat(new float[]{0.95f, 1.0f});
        this.mLayoutUpAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimButtonLayout.this.mCanvasScale = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                AnimButtonLayout.this.invalidate();
            }
        });
        this.mLayoutUpAnimator.setInterpolator(this.mInterpolator);
        this.mLayoutUpAnimator.setDuration(352);
    }

    public void invalidate() {
        super.invalidate();
        this.mDownloadProgressButton.invalidate();
    }

    public int getState() {
        return this.mDownloadProgressButton.getState();
    }

    public void setState(int i) {
        this.mDownloadProgressButton.setState(i);
    }

    public void setCurrentText(CharSequence charSequence) {
        this.mDownloadProgressButton.setCurrentText(charSequence);
    }

    public void setProgressText(String str, float f) {
        this.mDownloadProgressButton.setProgressText(str, f);
    }

    public float getProgress() {
        return this.mDownloadProgressButton.getProgress();
    }

    public void setProgress(float f) {
        this.mDownloadProgressButton.setProgress(f);
    }

    public void removeAllAnim() {
        this.mDownloadProgressButton.removeAllAnim();
    }

    public float getButtonRadius() {
        return this.mDownloadProgressButton.getButtonRadius();
    }

    public void setButtonRadius(float f) {
        this.mDownloadProgressButton.setButtonRadius(f);
    }

    public int getTextColor() {
        return this.mDownloadProgressButton.getTextColor();
    }

    public void setTextColor(int i) {
        this.mDownloadProgressButton.setTextColor(i);
    }

    public int getTextCoverColor() {
        return this.mDownloadProgressButton.getTextCoverColor();
    }

    public void setTextCoverColor(int i) {
        this.mDownloadProgressButton.setTextCoverColor(i);
    }

    public int getMinProgress() {
        return this.mDownloadProgressButton.getMinProgress();
    }

    public void setMinProgress(int i) {
        this.mDownloadProgressButton.setMinProgress(i);
    }

    public int getMaxProgress() {
        return this.mDownloadProgressButton.getMaxProgress();
    }

    public void setMaxProgress(int i) {
        this.mDownloadProgressButton.setMaxProgress(i);
    }

    public void enabelDefaultPress(boolean z) {
        this.mDownloadProgressButton.enabelDefaultPress(z);
    }

    public void enabelDefaultGradient(boolean z) {
        this.mDownloadProgressButton.enabelDefaultGradient(z);
    }

    public void setTextSize(float f) {
        this.mDownloadProgressButton.setTextSize(f);
    }

    public float getTextSize() {
        return this.mDownloadProgressButton.getTextSize();
    }

    public AnimDownloadProgressButton setCustomerController(ButtonController buttonController) {
        return this.mDownloadProgressButton.setCustomerController(buttonController);
    }
}

package com.tencent.liteav.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout.LayoutParams;

public class TXCFocusIndicatorView extends View {
    private static final int FOCUS_AREA_STROKE = 1;
    private int mFocusAreaStroke = 2;
    private Runnable mHideRunnable = new Runnable() {
        public void run() {
            TXCFocusIndicatorView.this.setVisibility(8);
        }
    };
    private Paint mPaint;
    private ScaleAnimation mScaleAnimation;
    private int mSize = 0;

    public TXCFocusIndicatorView(Context context) {
        super(context);
        init(null, 0);
    }

    public TXCFocusIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public TXCFocusIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    private void init(AttributeSet attributeSet, int i) {
        this.mPaint = new Paint();
        this.mFocusAreaStroke = (int) ((getContext().getResources().getDisplayMetrics().density * 1.0f) + 0.5f);
        this.mScaleAnimation = new ScaleAnimation(1.3f, 1.0f, 1.3f, 1.0f, 1, 0.5f, 1, 0.5f);
        this.mScaleAnimation.setDuration(200);
    }

    public void show(int i, int i2, int i3) {
        removeCallbacks(this.mHideRunnable);
        this.mScaleAnimation.cancel();
        this.mSize = i3;
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.setMargins(i, i2, 0, 0);
        layoutParams.width = this.mSize;
        layoutParams.height = this.mSize;
        setVisibility(0);
        requestLayout();
        this.mScaleAnimation.reset();
        startAnimation(this.mScaleAnimation);
        postDelayed(this.mHideRunnable, 1000);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        canvas.save();
        Rect rect = new Rect(0, 0, this.mSize, this.mSize);
        this.mPaint.setColor(-1);
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeWidth((float) this.mFocusAreaStroke);
        canvas.drawRect(rect, this.mPaint);
        canvas.restore();
        super.onDraw(canvas);
    }
}

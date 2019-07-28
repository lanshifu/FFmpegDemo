package com.tomatolive.library.ui.view.sticker.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.sticker.core.sticker.IMGSticker;
import com.tomatolive.library.ui.view.sticker.core.sticker.IMGStickerAdjustHelper;
import com.tomatolive.library.ui.view.sticker.core.sticker.IMGStickerHelper;
import com.tomatolive.library.ui.view.sticker.core.sticker.IMGStickerMoveHelper;
import com.tomatolive.library.ui.view.sticker.core.sticker.IMGStickerPortrait.Callback;

public abstract class IMGStickerView extends ViewGroup implements OnClickListener, IMGSticker {
    private static final int ANCHOR_SIZE = 48;
    private static final int ANCHOR_SIZE_HALF = 24;
    private static final float MAX_SCALE_VALUE = 4.0f;
    private static final float STROKE_WIDTH = 2.0f;
    private static final String TAG = "IMGStickerView";
    private Paint PAINT;
    private ImageView mAdjustView;
    private View mContentView;
    private int mDownShowing;
    private RectF mFrame;
    private Matrix mMatrix;
    private float mMaxScaleValue;
    private IMGStickerMoveHelper mMoveHelper;
    private ImageView mRemoveView;
    private float mScale;
    private IMGStickerHelper<IMGStickerView> mStickerHelper;
    private Rect mTempFrame;

    public void onContentTap() {
    }

    public abstract View onCreateContentView(Context context);

    public IMGStickerView(Context context) {
        this(context, null, 0);
    }

    public IMGStickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IMGStickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mScale = 1.0f;
        this.mDownShowing = 0;
        this.mMaxScaleValue = MAX_SCALE_VALUE;
        this.mMatrix = new Matrix();
        this.mFrame = new RectF();
        this.mTempFrame = new Rect();
        this.PAINT = new Paint(1);
        this.PAINT.setColor(-1);
        this.PAINT.setStyle(Style.STROKE);
        this.PAINT.setStrokeWidth(STROKE_WIDTH);
        onInitialize(context);
    }

    public void onInitialize(Context context) {
        setBackgroundColor(0);
        this.mContentView = onCreateContentView(context);
        addView(this.mContentView, getContentLayoutParams());
        this.mRemoveView = new ImageView(context);
        this.mRemoveView.setScaleType(ScaleType.FIT_XY);
        this.mRemoveView.setImageResource(R.drawable.fq_icons_fork_4);
        addView(this.mRemoveView, getAnchorLayoutParams());
        this.mRemoveView.setOnClickListener(this);
        this.mAdjustView = new ImageView(context);
        this.mAdjustView.setScaleType(ScaleType.FIT_XY);
        this.mAdjustView.setImageResource(R.drawable.fq_icon_scaling);
        addView(this.mAdjustView, getAnchorLayoutParams());
        IMGStickerAdjustHelper iMGStickerAdjustHelper = new IMGStickerAdjustHelper(this, this.mAdjustView);
        this.mStickerHelper = new IMGStickerHelper(this);
        this.mMoveHelper = new IMGStickerMoveHelper(this);
    }

    public float getScale() {
        return this.mScale;
    }

    public void setScale(float f) {
        if (f <= 1.5f && f >= 0.8f) {
            this.mScale = f;
            this.mContentView.setScaleX(this.mScale);
            this.mContentView.setScaleY(this.mScale);
            f = (float) ((getLeft() + getRight()) >> 1);
            float top = (float) ((getTop() + getBottom()) >> 1);
            this.mFrame.set(f, top, f, top);
            this.mFrame.inset((float) (-(this.mContentView.getMeasuredWidth() >> 1)), (float) (-(this.mContentView.getMeasuredHeight() >> 1)));
            this.mMatrix.setScale(this.mScale, this.mScale, this.mFrame.centerX(), this.mFrame.centerY());
            this.mMatrix.mapRect(this.mFrame);
            this.mFrame.round(this.mTempFrame);
            layout(this.mTempFrame.left, this.mTempFrame.top, this.mTempFrame.right, this.mTempFrame.bottom);
        }
    }

    public void addScale(float f) {
        setScale(getScale() * f);
    }

    private LayoutParams getContentLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    private LayoutParams getAnchorLayoutParams() {
        return new LayoutParams(48, 48);
    }

    public void draw(Canvas canvas) {
        if (isShowing()) {
            canvas.drawRect(24.0f, 24.0f, (float) (getWidth() - 24), (float) (getHeight() - 24), this.PAINT);
        }
        super.draw(canvas);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                childAt.measure(i, i2);
                i4 = Math.round(Math.max((float) i4, ((float) childAt.getMeasuredWidth()) * childAt.getScaleX()));
                i3 = Math.round(Math.max((float) i3, ((float) childAt.getMeasuredHeight()) * childAt.getScaleY()));
                i5 = combineMeasuredStates(i5, childAt.getMeasuredState());
            }
        }
        setMeasuredDimension(resolveSizeAndState(Math.max(i4, getSuggestedMinimumWidth()), i, i5), resolveSizeAndState(Math.max(i3, getSuggestedMinimumHeight()), i2, i5 << 16));
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mFrame.set((float) i, (float) i2, (float) i3, (float) i4);
        if (getChildCount() != 0) {
            this.mRemoveView.layout(0, 0, this.mRemoveView.getMeasuredWidth(), this.mRemoveView.getMeasuredHeight());
            i3 -= i;
            i4 -= i2;
            this.mAdjustView.layout(i3 - this.mAdjustView.getMeasuredWidth(), i4 - this.mAdjustView.getMeasuredHeight(), i3, i4);
            int i5 = i3 >> 1;
            i = i4 >> 1;
            i2 = this.mContentView.getMeasuredWidth() >> 1;
            i3 = this.mContentView.getMeasuredHeight() >> 1;
            this.mContentView.layout(i5 - i2, i - i3, i5 + i2, i + i3);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        return isShowing() && super.drawChild(canvas, view, j);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        if (isShowing() || motionEvent.getAction() != 0) {
            if (!(isShowing() && super.onInterceptTouchEvent(motionEvent))) {
                z = false;
            }
            return z;
        }
        this.mDownShowing = 0;
        show();
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouch = this.mMoveHelper.onTouch(this, motionEvent);
        boolean z = true;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.mDownShowing++;
                break;
            case 1:
                if (this.mDownShowing > 1 && motionEvent.getEventTime() - motionEvent.getDownTime() < ((long) ViewConfiguration.getTapTimeout())) {
                    onContentTap();
                    return true;
                }
        }
        if (!(onTouch || super.onTouchEvent(motionEvent))) {
            z = false;
        }
        return z;
    }

    public void onClick(View view) {
        if (view == this.mRemoveView) {
            onRemove();
        }
    }

    public void onRemove() {
        this.mStickerHelper.remove();
    }

    public boolean show() {
        return this.mStickerHelper.show();
    }

    public boolean remove() {
        return this.mStickerHelper.remove();
    }

    public boolean dismiss() {
        return this.mStickerHelper.dismiss();
    }

    public boolean isShowing() {
        return this.mStickerHelper.isShowing();
    }

    public RectF getFrame() {
        return this.mStickerHelper.getFrame();
    }

    public void onSticker(Canvas canvas) {
        canvas.translate(this.mContentView.getX(), this.mContentView.getY());
        this.mContentView.draw(canvas);
    }

    public void registerCallback(Callback callback) {
        this.mStickerHelper.registerCallback(callback);
    }

    public void unregisterCallback(Callback callback) {
        this.mStickerHelper.unregisterCallback(callback);
    }
}

package com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.tomatolive.library.R;

public class CheckView extends View {
    private static final float BG_RADIUS = 11.0f;
    private static final int CONTENT_SIZE = 16;
    private static final float SHADOW_WIDTH = 6.0f;
    private static final int SIZE = 48;
    private static final float STROKE_RADIUS = 11.5f;
    private static final float STROKE_WIDTH = 3.0f;
    public static final int UNCHECKED = Integer.MIN_VALUE;
    private Paint mBackgroundPaint;
    private Drawable mCheckDrawable;
    private Rect mCheckRect;
    private boolean mChecked;
    private int mCheckedNum;
    private boolean mCountable;
    private float mDensity;
    private boolean mEnabled = true;
    private Paint mShadowPaint;
    private Paint mStrokePaint;
    private TextPaint mTextPaint;

    public CheckView(Context context) {
        super(context);
        init(context);
    }

    public CheckView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public CheckView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        i = MeasureSpec.makeMeasureSpec((int) (this.mDensity * 48.0f), 1073741824);
        super.onMeasure(i, i);
    }

    private void init(Context context) {
        this.mDensity = context.getResources().getDisplayMetrics().density;
        this.mStrokePaint = new Paint();
        this.mStrokePaint.setAntiAlias(true);
        this.mStrokePaint.setStyle(Style.STROKE);
        this.mStrokePaint.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
        this.mStrokePaint.setStrokeWidth(this.mDensity * 3.0f);
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.item_checkCircle_borderColor});
        int color = obtainStyledAttributes.getColor(0, ResourcesCompat.getColor(getResources(), R.color.zhihu_item_checkCircle_borderColor, getContext().getTheme()));
        obtainStyledAttributes.recycle();
        this.mStrokePaint.setColor(color);
        this.mCheckDrawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.fq_ic_matisse_check_white_18dp, context.getTheme());
    }

    public void setChecked(boolean z) {
        if (this.mCountable) {
            throw new IllegalStateException("CheckView is countable, call setCheckedNum() instead.");
        }
        this.mChecked = z;
        invalidate();
    }

    public void setCountable(boolean z) {
        this.mCountable = z;
    }

    public void setCheckedNum(int i) {
        if (!this.mCountable) {
            throw new IllegalStateException("CheckView is not countable, call setChecked() instead.");
        } else if (i == UNCHECKED || i > 0) {
            this.mCheckedNum = i;
            invalidate();
        } else {
            throw new IllegalArgumentException("checked num can't be negative.");
        }
    }

    public void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            this.mEnabled = z;
            invalidate();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initShadowPaint();
        canvas.drawCircle((this.mDensity * 48.0f) / 2.0f, (this.mDensity * 48.0f) / 2.0f, this.mDensity * 19.0f, this.mShadowPaint);
        canvas.drawCircle((this.mDensity * 48.0f) / 2.0f, (this.mDensity * 48.0f) / 2.0f, this.mDensity * STROKE_RADIUS, this.mStrokePaint);
        if (this.mCountable) {
            if (this.mCheckedNum != UNCHECKED) {
                initBackgroundPaint();
                canvas.drawCircle((this.mDensity * 48.0f) / 2.0f, (this.mDensity * 48.0f) / 2.0f, this.mDensity * BG_RADIUS, this.mBackgroundPaint);
                initTextPaint();
                String valueOf = String.valueOf(this.mCheckedNum);
                canvas.drawText(valueOf, (float) (((int) (((float) canvas.getWidth()) - this.mTextPaint.measureText(valueOf))) / 2), (float) (((int) ((((float) canvas.getHeight()) - this.mTextPaint.descent()) - this.mTextPaint.ascent())) / 2), this.mTextPaint);
            }
        } else if (this.mChecked) {
            initBackgroundPaint();
            canvas.drawCircle((this.mDensity * 48.0f) / 2.0f, (this.mDensity * 48.0f) / 2.0f, this.mDensity * BG_RADIUS, this.mBackgroundPaint);
            this.mCheckDrawable.setBounds(getCheckRect());
            this.mCheckDrawable.draw(canvas);
        }
        setAlpha(this.mEnabled ? 1.0f : 0.5f);
    }

    private void initShadowPaint() {
        if (this.mShadowPaint == null) {
            this.mShadowPaint = new Paint();
            this.mShadowPaint.setAntiAlias(true);
            float f = (this.mDensity * 48.0f) / 2.0f;
            float f2 = 19.0f * this.mDensity;
            this.mShadowPaint.setShader(new RadialGradient(f, (this.mDensity * 48.0f) / 2.0f, f2, new int[]{Color.parseColor("#00000000"), Color.parseColor("#0D000000"), Color.parseColor("#0D000000"), Color.parseColor("#00000000")}, new float[]{0.21052632f, 0.5263158f, 0.68421054f, 1.0f}, TileMode.CLAMP));
        }
    }

    private void initBackgroundPaint() {
        if (this.mBackgroundPaint == null) {
            this.mBackgroundPaint = new Paint();
            this.mBackgroundPaint.setAntiAlias(true);
            this.mBackgroundPaint.setStyle(Style.FILL);
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.item_checkCircle_backgroundColor});
            int color = obtainStyledAttributes.getColor(0, ResourcesCompat.getColor(getResources(), R.color.zhihu_item_checkCircle_backgroundColor, getContext().getTheme()));
            obtainStyledAttributes.recycle();
            this.mBackgroundPaint.setColor(color);
        }
    }

    private void initTextPaint() {
        if (this.mTextPaint == null) {
            this.mTextPaint = new TextPaint();
            this.mTextPaint.setAntiAlias(true);
            this.mTextPaint.setColor(-1);
            this.mTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
            this.mTextPaint.setTextSize(this.mDensity * 12.0f);
        }
    }

    private Rect getCheckRect() {
        if (this.mCheckRect == null) {
            int i = (int) (((this.mDensity * 48.0f) / 2.0f) - ((this.mDensity * 16.0f) / 2.0f));
            float f = (float) i;
            this.mCheckRect = new Rect(i, i, (int) ((this.mDensity * 48.0f) - f), (int) ((this.mDensity * 48.0f) - f));
        }
        return this.mCheckRect;
    }
}

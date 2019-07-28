package com.tomatolive.library.ui.view.gift;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.tomatolive.library.R;

public class StrokeTextView extends AppCompatTextView {
    private static final int DEFAULT_BORDER_COLOR = -1;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private int mBorderColor;
    private int mBorderWidth;
    private TextView outlineTextView;

    public StrokeTextView(Context context) {
        super(context);
        this.outlineTextView = null;
        this.mBorderColor = -1;
        this.mBorderWidth = 0;
        this.outlineTextView = new TextView(context);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StrokeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.outlineTextView = null;
        this.mBorderColor = -1;
        this.mBorderWidth = 0;
        this.outlineTextView = new TextView(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.StrokeTextView, i, 0);
        this.mBorderWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.StrokeTextView_strokeWidth, 0);
        this.mBorderColor = obtainStyledAttributes.getColor(R.styleable.StrokeTextView_strokeColor, -1);
        obtainStyledAttributes.recycle();
        init();
    }

    private void init() {
        TextPaint paint = this.outlineTextView.getPaint();
        paint.setStrokeWidth((float) this.mBorderWidth);
        paint.setStyle(Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        this.outlineTextView.setTextColor(this.mBorderColor);
        this.outlineTextView.setGravity(getGravity());
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        this.outlineTextView.setLayoutParams(layoutParams);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        CharSequence text = this.outlineTextView.getText();
        if (text == null || !text.equals(getText())) {
            this.outlineTextView.setText(getText());
            postInvalidate();
        }
        super.onMeasure(i, i2);
        this.outlineTextView.measure(i, i2);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.outlineTextView.layout(i, i2, i3, i4);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        this.outlineTextView.draw(canvas);
        super.onDraw(canvas);
    }
}

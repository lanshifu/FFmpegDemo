package com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.yalantis.ucrop.view.CropImageView;

public class RoundedRectangleImageView extends AppCompatImageView {
    private float mRadius;
    private RectF mRectF;
    private Path mRoundedRectPath;

    public RoundedRectangleImageView(Context context) {
        super(context);
        init(context);
    }

    public RoundedRectangleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public RoundedRectangleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.mRadius = context.getResources().getDisplayMetrics().density * 2.0f;
        this.mRoundedRectPath = new Path();
        this.mRectF = new RectF();
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mRectF.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) getMeasuredWidth(), (float) getMeasuredHeight());
        this.mRoundedRectPath.addRoundRect(this.mRectF, this.mRadius, this.mRadius, Direction.CW);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        canvas.clipPath(this.mRoundedRectPath);
        super.onDraw(canvas);
    }
}

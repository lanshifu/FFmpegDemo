package com.tomatolive.library.ui.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.yalantis.ucrop.view.CropImageView;

public class FadingRecyclerView extends RecyclerView {
    private static final String TAG = "FadingRecyclerView";
    private int height;
    private Paint paint;
    private int spanPixel = 50;
    private int width;

    public FadingRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public FadingRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public FadingRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
    }

    public void setSpanPixel(int i) {
        this.spanPixel = i;
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.height = i2;
        this.width = i;
        float f = ((float) this.spanPixel) / (((float) this.height) / 2.0f);
        this.paint.setShader(new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) this.height) / 2.0f, new int[]{0, -16777216, -16777216}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, f, 1.0f}, TileMode.MIRROR));
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        canvas2.saveLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.width, (float) this.height, null, 31);
        super.draw(canvas);
        canvas2.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.width, (float) this.height, this.paint);
        canvas.restore();
    }
}

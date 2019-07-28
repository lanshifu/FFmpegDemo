package com.tomatolive.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;
import jp.wasabeef.glide.transformations.BitmapTransformation;

/* compiled from: GlideCircleBorderTransform */
public class h extends BitmapTransformation {
    private final String a = getClass().getName();
    private Paint b;
    private float c;

    public h(float f, int i) {
        this.c = f;
        this.b = new Paint();
        this.b.setColor(i);
        this.b.setStyle(Style.STROKE);
        this.b.setAntiAlias(true);
        this.b.setStrokeWidth(f);
        this.b.setDither(true);
    }

    /* Access modifiers changed, original: protected */
    public Bitmap transform(Context context, BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return a(bitmapPool, bitmap);
    }

    private Bitmap a(BitmapPool bitmapPool, Bitmap bitmap) {
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        bitmap = Bitmap.createBitmap(bitmap, (bitmap.getWidth() - min) / 2, (bitmap.getHeight() - min) / 2, min, min);
        Bitmap bitmap2 = bitmapPool.get(min, min, Config.ARGB_8888);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(min, min, Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP));
        paint.setAntiAlias(true);
        float f = ((float) min) / 2.0f;
        canvas.drawCircle(f, f, f, paint);
        canvas.drawCircle(f, f, f - (this.c / 2.0f), this.b);
        return bitmap2;
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(this.a.getBytes(CHARSET));
    }

    public boolean equals(Object obj) {
        return obj instanceof h;
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}

package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.yalantis.ucrop.view.CropImageView;
import java.security.MessageDigest;
import jp.wasabeef.glide.transformations.internal.FastBlur;

public class BlurTransformation extends BitmapTransformation {
    private static int DEFAULT_DOWN_SAMPLING = 1;
    private static final String ID = "jp.wasabeef.glide.transformations.BlurTransformation.1";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private static int MAX_RADIUS = 25;
    private static final int VERSION = 1;
    private int radius;
    private int sampling;

    public BlurTransformation() {
        this(MAX_RADIUS, DEFAULT_DOWN_SAMPLING);
    }

    public BlurTransformation(int i) {
        this(i, DEFAULT_DOWN_SAMPLING);
    }

    public BlurTransformation(int i, int i2) {
        this.radius = i;
        this.sampling = i2;
    }

    /* Access modifiers changed, original: protected */
    public Bitmap transform(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        Bitmap bitmap2 = bitmapPool.get(bitmap.getWidth() / this.sampling, bitmap.getHeight() / this.sampling, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap2);
        canvas.scale(1.0f / ((float) this.sampling), 1.0f / ((float) this.sampling));
        Paint paint = new Paint();
        paint.setFlags(2);
        canvas.drawBitmap(bitmap, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, paint);
        return FastBlur.blur(bitmap2, this.radius, true);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BlurTransformation(radius=");
        stringBuilder.append(this.radius);
        stringBuilder.append(", sampling=");
        stringBuilder.append(this.sampling);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        return obj instanceof BlurTransformation;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}

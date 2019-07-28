package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.yalantis.ucrop.view.CropImageView;
import java.security.MessageDigest;

public class CropTransformation extends BitmapTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.CropTransformation.1";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private static final int VERSION = 1;
    private CropType cropType;
    private int height;
    private int width;

    public enum CropType {
        TOP,
        CENTER,
        BOTTOM
    }

    public CropTransformation(int i, int i2) {
        this(i, i2, CropType.CENTER);
    }

    public CropTransformation(int i, int i2, CropType cropType) {
        this.cropType = CropType.CENTER;
        this.width = i;
        this.height = i2;
        this.cropType = cropType;
    }

    /* Access modifiers changed, original: protected */
    public Bitmap transform(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        this.width = this.width == 0 ? bitmap.getWidth() : this.width;
        this.height = this.height == 0 ? bitmap.getHeight() : this.height;
        Bitmap bitmap2 = bitmapPool.get(this.width, this.height, bitmap.getConfig() != null ? bitmap.getConfig() : Config.ARGB_8888);
        bitmap2.setHasAlpha(true);
        float max = Math.max(((float) this.width) / ((float) bitmap.getWidth()), ((float) this.height) / ((float) bitmap.getHeight()));
        float width = ((float) bitmap.getWidth()) * max;
        max *= (float) bitmap.getHeight();
        float f = (((float) this.width) - width) / 2.0f;
        float top = getTop(max);
        new Canvas(bitmap2).drawBitmap(bitmap, null, new RectF(f, top, width + f, max + top), null);
        return bitmap2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CropTransformation(width=");
        stringBuilder.append(this.width);
        stringBuilder.append(", height=");
        stringBuilder.append(this.height);
        stringBuilder.append(", cropType=");
        stringBuilder.append(this.cropType);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        return obj instanceof CropTransformation;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }

    private float getTop(float f) {
        switch (this.cropType) {
            case TOP:
                return CropImageView.DEFAULT_ASPECT_RATIO;
            case CENTER:
                return (((float) this.height) - f) / 2.0f;
            case BOTTOM:
                return ((float) this.height) - f;
            default:
                return CropImageView.DEFAULT_ASPECT_RATIO;
        }
    }
}

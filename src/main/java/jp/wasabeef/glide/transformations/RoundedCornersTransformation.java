package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

public class RoundedCornersTransformation extends BitmapTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.RoundedCornersTransformation.1";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private static final int VERSION = 1;
    private CornerType cornerType;
    private int diameter;
    private int margin;
    private int radius;

    public enum CornerType {
        ALL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        OTHER_TOP_LEFT,
        OTHER_TOP_RIGHT,
        OTHER_BOTTOM_LEFT,
        OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT,
        DIAGONAL_FROM_TOP_RIGHT
    }

    public RoundedCornersTransformation(int i, int i2) {
        this(i, i2, CornerType.ALL);
    }

    public RoundedCornersTransformation(int i, int i2, CornerType cornerType) {
        this.radius = i;
        this.diameter = this.radius * 2;
        this.margin = i2;
        this.cornerType = cornerType;
    }

    /* Access modifiers changed, original: protected */
    public Bitmap transform(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        i = bitmap.getHeight();
        Bitmap bitmap2 = bitmapPool.get(width, i, Config.ARGB_8888);
        bitmap2.setHasAlpha(true);
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP));
        drawRoundRect(canvas, paint, (float) width, (float) i);
        return bitmap2;
    }

    private void drawRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        f -= (float) this.margin;
        f2 -= (float) this.margin;
        switch (this.cornerType) {
            case ALL:
                canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, f, f2), (float) this.radius, (float) this.radius, paint);
                return;
            case TOP_LEFT:
                drawTopLeftRoundRect(canvas, paint, f, f2);
                return;
            case TOP_RIGHT:
                drawTopRightRoundRect(canvas, paint, f, f2);
                return;
            case BOTTOM_LEFT:
                drawBottomLeftRoundRect(canvas, paint, f, f2);
                return;
            case BOTTOM_RIGHT:
                drawBottomRightRoundRect(canvas, paint, f, f2);
                return;
            case TOP:
                drawTopRoundRect(canvas, paint, f, f2);
                return;
            case BOTTOM:
                drawBottomRoundRect(canvas, paint, f, f2);
                return;
            case LEFT:
                drawLeftRoundRect(canvas, paint, f, f2);
                return;
            case RIGHT:
                drawRightRoundRect(canvas, paint, f, f2);
                return;
            case OTHER_TOP_LEFT:
                drawOtherTopLeftRoundRect(canvas, paint, f, f2);
                return;
            case OTHER_TOP_RIGHT:
                drawOtherTopRightRoundRect(canvas, paint, f, f2);
                return;
            case OTHER_BOTTOM_LEFT:
                drawOtherBottomLeftRoundRect(canvas, paint, f, f2);
                return;
            case OTHER_BOTTOM_RIGHT:
                drawOtherBottomRightRoundRect(canvas, paint, f, f2);
                return;
            case DIAGONAL_FROM_TOP_LEFT:
                drawDiagonalFromTopLeftRoundRect(canvas, paint, f, f2);
                return;
            case DIAGONAL_FROM_TOP_RIGHT:
                drawDiagonalFromTopRightRoundRect(canvas, paint, f, f2);
                return;
            default:
                canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, f, f2), (float) this.radius, (float) this.radius, paint);
                return;
        }
    }

    private void drawTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) (this.margin + this.radius), (float) (this.margin + this.radius), f2), paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) this.margin, f, f2), paint);
    }

    private void drawTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.diameter), (float) this.margin, f, (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, f - ((float) this.radius), f2), paint);
        canvas.drawRect(new RectF(f - ((float) this.radius), (float) (this.margin + this.radius), f, f2), paint);
    }

    private void drawBottomLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.margin, f2 - ((float) this.diameter), (float) (this.margin + this.diameter), f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), f2 - ((float) this.radius)), paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) this.margin, f, f2), paint);
    }

    private void drawBottomRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.diameter), f2 - ((float) this.diameter), f, f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, f - ((float) this.radius), f2), paint);
        canvas.drawRect(new RectF(f - ((float) this.radius), (float) this.margin, f, f2 - ((float) this.radius)), paint);
    }

    private void drawTopRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, f, (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) (this.margin + this.radius), f, f2), paint);
    }

    private void drawBottomRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.margin, f2 - ((float) this.diameter), f, f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, f, f2 - ((float) this.radius)), paint);
    }

    private void drawLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) this.margin, f, f2), paint);
    }

    private void drawRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.diameter), (float) this.margin, f, f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, f - ((float) this.radius), f2), paint);
    }

    private void drawOtherTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.margin, f2 - ((float) this.diameter), f, f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF(f - ((float) this.diameter), (float) this.margin, f, f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, f - ((float) this.radius), f2 - ((float) this.radius)), paint);
    }

    private void drawOtherTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF((float) this.margin, f2 - ((float) this.diameter), f, f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) this.margin, f, f2 - ((float) this.radius)), paint);
    }

    private void drawOtherBottomLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, f, (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF(f - ((float) this.diameter), (float) this.margin, f, f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) (this.margin + this.radius), f - ((float) this.radius), f2), paint);
    }

    private void drawOtherBottomRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, f, (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) (this.margin + this.radius), f, f2), paint);
    }

    private void drawDiagonalFromTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF(f - ((float) this.diameter), f2 - ((float) this.diameter), f, f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) (this.margin + this.radius), f - ((float) this.diameter), f2), paint);
        canvas.drawRect(new RectF((float) (this.margin + this.diameter), (float) this.margin, f, f2 - ((float) this.radius)), paint);
    }

    private void drawDiagonalFromTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.diameter), (float) this.margin, f, (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF((float) this.margin, f2 - ((float) this.diameter), (float) (this.margin + this.diameter), f2), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, f - ((float) this.radius), f2 - ((float) this.radius)), paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) (this.margin + this.radius), f, f2), paint);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RoundedTransformation(radius=");
        stringBuilder.append(this.radius);
        stringBuilder.append(", margin=");
        stringBuilder.append(this.margin);
        stringBuilder.append(", diameter=");
        stringBuilder.append(this.diameter);
        stringBuilder.append(", cornerType=");
        stringBuilder.append(this.cornerType.name());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        return obj instanceof RoundedCornersTransformation;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}

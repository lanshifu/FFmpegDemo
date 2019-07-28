package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.yalantis.ucrop.view.CropImageView;
import java.security.MessageDigest;
import jp.wasabeef.glide.transformations.internal.Utils;

public class MaskTransformation extends BitmapTransformation {
    private static final String ID = "jp.wasabeef.glide.transformations.MaskTransformation.1";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private static final int VERSION = 1;
    private static Paint paint = new Paint();
    private int maskId;

    static {
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
    }

    public MaskTransformation(int i) {
        this.maskId = i;
    }

    /* Access modifiers changed, original: protected */
    public Bitmap transform(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        i = bitmap.getWidth();
        i2 = bitmap.getHeight();
        Bitmap bitmap2 = bitmapPool.get(i, i2, Config.ARGB_8888);
        bitmap2.setHasAlpha(true);
        Drawable maskDrawable = Utils.getMaskDrawable(context.getApplicationContext(), this.maskId);
        Canvas canvas = new Canvas(bitmap2);
        maskDrawable.setBounds(0, 0, i, i2);
        maskDrawable.draw(canvas);
        canvas.drawBitmap(bitmap, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, paint);
        return bitmap2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MaskTransformation(maskId=");
        stringBuilder.append(this.maskId);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        return obj instanceof MaskTransformation;
    }

    public int hashCode() {
        return ID.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}

package jp.wasabeef.glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Util;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import java.security.MessageDigest;

public abstract class BitmapTransformation implements Transformation<Bitmap> {
    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract Bitmap transform(@NonNull Context context, @NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2);

    public abstract void updateDiskCacheKey(MessageDigest messageDigest);

    public final Resource<Bitmap> transform(Context context, Resource<Bitmap> resource, int i, int i2) {
        if (Util.isValidDimensions(i, i2)) {
            BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
            Bitmap bitmap = (Bitmap) resource.get();
            if (i == CheckView.UNCHECKED) {
                i = bitmap.getWidth();
            }
            int i3 = i;
            if (i2 == CheckView.UNCHECKED) {
                i2 = bitmap.getHeight();
            }
            Bitmap transform = transform(context.getApplicationContext(), bitmapPool, bitmap, i3, i2);
            if (bitmap.equals(transform)) {
                return resource;
            }
            return BitmapResource.obtain(transform, bitmapPool);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot apply transformation on width: ");
        stringBuilder.append(i);
        stringBuilder.append(" or height: ");
        stringBuilder.append(i2);
        stringBuilder.append(" less than or equal to zero and not Target.SIZE_ORIGINAL");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}

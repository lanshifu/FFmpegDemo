package org.xutils.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/* compiled from: ReusableBitmapDrawable */
final class c extends BitmapDrawable implements d {
    private b a;

    public c(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    public b getMemCacheKey() {
        return this.a;
    }

    public void setMemCacheKey(b bVar) {
        this.a = bVar;
    }
}

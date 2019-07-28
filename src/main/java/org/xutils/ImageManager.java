package org.xutils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.io.File;
import org.xutils.common.Callback.CacheCallback;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.image.ImageOptions;

public interface ImageManager {
    void bind(ImageView imageView, String str);

    void bind(ImageView imageView, String str, CommonCallback<Drawable> commonCallback);

    void bind(ImageView imageView, String str, ImageOptions imageOptions);

    void bind(ImageView imageView, String str, ImageOptions imageOptions, CommonCallback<Drawable> commonCallback);

    void clearCacheFiles();

    void clearMemCache();

    Cancelable loadDrawable(String str, ImageOptions imageOptions, CommonCallback<Drawable> commonCallback);

    Cancelable loadFile(String str, ImageOptions imageOptions, CacheCallback<File> cacheCallback);
}

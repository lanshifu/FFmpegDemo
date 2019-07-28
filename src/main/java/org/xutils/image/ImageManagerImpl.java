package org.xutils.image;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.io.File;
import org.xutils.ImageManager;
import org.xutils.common.Callback.CacheCallback;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.x;
import org.xutils.x.Ext;

public final class ImageManagerImpl implements ImageManager {
    private static final Object a = new Object();
    private static volatile ImageManagerImpl b;

    private ImageManagerImpl() {
    }

    public static void registerInstance() {
        if (b == null) {
            synchronized (a) {
                if (b == null) {
                    b = new ImageManagerImpl();
                }
            }
        }
        Ext.setImageManager(b);
    }

    public void bind(final ImageView imageView, final String str) {
        x.task().autoPost(new Runnable() {
            public void run() {
                a.a(imageView, str, null, null);
            }
        });
    }

    public void bind(final ImageView imageView, final String str, final ImageOptions imageOptions) {
        x.task().autoPost(new Runnable() {
            public void run() {
                a.a(imageView, str, imageOptions, null);
            }
        });
    }

    public void bind(final ImageView imageView, final String str, final CommonCallback<Drawable> commonCallback) {
        x.task().autoPost(new Runnable() {
            public void run() {
                a.a(imageView, str, null, commonCallback);
            }
        });
    }

    public void bind(ImageView imageView, String str, ImageOptions imageOptions, CommonCallback<Drawable> commonCallback) {
        final ImageView imageView2 = imageView;
        final String str2 = str;
        final ImageOptions imageOptions2 = imageOptions;
        final CommonCallback<Drawable> commonCallback2 = commonCallback;
        x.task().autoPost(new Runnable() {
            public void run() {
                a.a(imageView2, str2, imageOptions2, commonCallback2);
            }
        });
    }

    public Cancelable loadDrawable(String str, ImageOptions imageOptions, CommonCallback<Drawable> commonCallback) {
        return a.a(str, imageOptions, (CommonCallback) commonCallback);
    }

    public Cancelable loadFile(String str, ImageOptions imageOptions, CacheCallback<File> cacheCallback) {
        return a.a(str, imageOptions, (CacheCallback) cacheCallback);
    }

    public void clearMemCache() {
        a.a();
    }

    public void clearCacheFiles() {
        a.b();
        ImageDecoder.a();
    }
}

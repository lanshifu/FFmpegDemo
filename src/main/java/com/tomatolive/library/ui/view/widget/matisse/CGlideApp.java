package com.tomatolive.library.ui.view.widget.matisse;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy;
import com.bumptech.glide.module.AppGlideModule;

public class CGlideApp extends AppGlideModule {
    public boolean isManifestParsingEnabled() {
        return false;
    }

    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder glideBuilder) {
        AnonymousClass1 anonymousClass1 = new UncaughtThrowableStrategy() {
            public void handle(Throwable th) {
            }
        };
        long j = (long) 20971520;
        glideBuilder.setMemoryCache(new LruResourceCache(j)).setDiskCache(new InternalCacheDiskCacheFactory(context, (long) 104857600)).setBitmapPool(new LruBitmapPool(j)).setDiskCacheExecutor(GlideExecutor.newDiskCacheExecutor(anonymousClass1)).setSourceExecutor(GlideExecutor.newSourceExecutor(anonymousClass1));
    }
}

package org.xutils.image;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.tencent.ugc.TXRecordCommon;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import org.xutils.cache.LruCache;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.Callback.CacheCallback;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.common.Callback.PrepareCallback;
import org.xutils.common.Callback.ProgressCallback;
import org.xutils.common.Callback.TypedCallback;
import org.xutils.common.task.Priority;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.FileLockedException;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions.ParamsBuilder;
import org.xutils.x;

/* compiled from: ImageLoader */
final class a implements CacheCallback<Drawable>, Cancelable, PrepareCallback<File, Drawable>, ProgressCallback<Drawable>, TypedCallback<Drawable> {
    private static final AtomicLong d = new AtomicLong(0);
    private static final Executor m = new PriorityExecutor(10, false);
    private static final LruCache<b, Drawable> n = new LruCache<b, Drawable>(4194304) {
        private boolean a = false;

        /* Access modifiers changed, original: protected */
        public int sizeOf(b bVar, Drawable drawable) {
            if (drawable instanceof BitmapDrawable) {
                int i;
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (bitmap == null) {
                    i = 0;
                } else {
                    i = bitmap.getByteCount();
                }
                return i;
            } else if (drawable instanceof GifDrawable) {
                return ((GifDrawable) drawable).getByteCount();
            } else {
                return super.sizeOf(bVar, drawable);
            }
        }

        public void trimToSize(int i) {
            if (i < 0) {
                this.a = true;
            }
            super.trimToSize(i);
            this.a = false;
        }

        /* Access modifiers changed, original: protected */
        public void entryRemoved(boolean z, b bVar, Drawable drawable, Drawable drawable2) {
            super.entryRemoved(z, bVar, drawable, drawable2);
            if (z && this.a && (drawable instanceof d)) {
                ((d) drawable).setMemCacheKey(null);
            }
        }
    };
    private static final HashMap<String, a> o = new HashMap();
    private static final Type p = File.class;
    private b a;
    private ImageOptions b;
    private WeakReference<ImageView> c;
    private final long e = d.incrementAndGet();
    private volatile boolean f = false;
    private volatile boolean g = false;
    private Cancelable h;
    private CommonCallback<Drawable> i;
    private PrepareCallback<File, Drawable> j;
    private CacheCallback<Drawable> k;
    private ProgressCallback<Drawable> l;
    private boolean q = false;

    @SuppressLint({"ViewConstructor"})
    /* compiled from: ImageLoader */
    private static final class a extends ImageView {
        private Drawable a;

        public void setLayerType(int i, Paint paint) {
        }

        public void setScaleType(ScaleType scaleType) {
        }

        public void startAnimation(Animation animation) {
        }

        public a() {
            super(x.app());
        }

        public void setImageDrawable(Drawable drawable) {
            this.a = drawable;
        }

        public Drawable getDrawable() {
            return this.a;
        }
    }

    static {
        int memoryClass = (((ActivityManager) x.app().getSystemService("activity")).getMemoryClass() * 1048576) / 8;
        if (memoryClass < 4194304) {
            memoryClass = 4194304;
        }
        n.resize(memoryClass);
    }

    private a() {
    }

    static void a() {
        n.evictAll();
    }

    static void b() {
        LruDiskCache.getDiskCache("xUtils_img").clearCacheFiles();
    }

    static Cancelable a(String str, ImageOptions imageOptions, CommonCallback<Drawable> commonCallback) {
        if (TextUtils.isEmpty(str)) {
            a(null, imageOptions, "url is null", (CommonCallback) commonCallback);
            return null;
        }
        ImageView imageView;
        synchronized (o) {
            imageView = (a) o.get(str);
            if (imageView == null) {
                imageView = new a();
            }
        }
        return a(imageView, str, imageOptions, (CommonCallback) commonCallback);
    }

    static Cancelable a(String str, ImageOptions imageOptions, CacheCallback<File> cacheCallback) {
        if (TextUtils.isEmpty(str)) {
            a(null, imageOptions, "url is null", (CommonCallback) cacheCallback);
            return null;
        }
        return x.http().get(a(str, imageOptions), cacheCallback);
    }

    /* JADX WARNING: Removed duplicated region for block: B:93:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007e  */
    /* JADX WARNING: Missing block: B:34:0x0079, code skipped:
            if (r2.isRecycled() == false) goto L_0x007c;
     */
    static org.xutils.common.Callback.Cancelable a(android.widget.ImageView r5, java.lang.String r6, org.xutils.image.ImageOptions r7, org.xutils.common.Callback.CommonCallback<android.graphics.drawable.Drawable> r8) {
        /*
        r0 = 0;
        if (r5 != 0) goto L_0x0009;
    L_0x0003:
        r5 = "view is null";
        a(r0, r7, r5, r8);
        return r0;
    L_0x0009:
        r1 = android.text.TextUtils.isEmpty(r6);
        if (r1 == 0) goto L_0x0015;
    L_0x000f:
        r6 = "url is null";
        a(r5, r7, r6, r8);
        return r0;
    L_0x0015:
        if (r7 != 0) goto L_0x0019;
    L_0x0017:
        r7 = org.xutils.image.ImageOptions.DEFAULT;
    L_0x0019:
        r7.a(r5);
        r1 = new org.xutils.image.b;
        r1.<init>(r6, r7);
        r2 = r5.getDrawable();
        r3 = r2 instanceof org.xutils.image.AsyncDrawable;
        if (r3 == 0) goto L_0x0042;
    L_0x0029:
        r2 = (org.xutils.image.AsyncDrawable) r2;
        r2 = r2.getImageLoader();
        if (r2 == 0) goto L_0x005a;
    L_0x0031:
        r3 = r2.f;
        if (r3 != 0) goto L_0x005a;
    L_0x0035:
        r3 = r2.a;
        r3 = r1.equals(r3);
        if (r3 == 0) goto L_0x003e;
    L_0x003d:
        return r0;
    L_0x003e:
        r2.cancel();
        goto L_0x005a;
    L_0x0042:
        r3 = r2 instanceof org.xutils.image.d;
        if (r3 == 0) goto L_0x005a;
    L_0x0046:
        r3 = r2;
        r3 = (org.xutils.image.d) r3;
        r3 = r3.getMemCacheKey();
        if (r3 == 0) goto L_0x005a;
    L_0x004f:
        r3 = r3.equals(r1);
        if (r3 == 0) goto L_0x005a;
    L_0x0055:
        r3 = n;
        r3.put(r1, r2);
    L_0x005a:
        r2 = r7.isUseMemCache();
        if (r2 == 0) goto L_0x007b;
    L_0x0060:
        r2 = n;
        r1 = r2.get(r1);
        r1 = (android.graphics.drawable.Drawable) r1;
        r2 = r1 instanceof android.graphics.drawable.BitmapDrawable;
        if (r2 == 0) goto L_0x007c;
    L_0x006c:
        r2 = r1;
        r2 = (android.graphics.drawable.BitmapDrawable) r2;
        r2 = r2.getBitmap();
        if (r2 == 0) goto L_0x007b;
    L_0x0075:
        r2 = r2.isRecycled();
        if (r2 == 0) goto L_0x007c;
    L_0x007b:
        r1 = r0;
    L_0x007c:
        if (r1 == 0) goto L_0x0105;
    L_0x007e:
        r2 = 0;
        r3 = r8 instanceof org.xutils.common.Callback.ProgressCallback;	 Catch:{ Throwable -> 0x00df }
        if (r3 == 0) goto L_0x0089;
    L_0x0083:
        r3 = r8;
        r3 = (org.xutils.common.Callback.ProgressCallback) r3;	 Catch:{ Throwable -> 0x00df }
        r3.onWaiting();	 Catch:{ Throwable -> 0x00df }
    L_0x0089:
        r3 = r7.getImageScaleType();	 Catch:{ Throwable -> 0x00df }
        r5.setScaleType(r3);	 Catch:{ Throwable -> 0x00df }
        r5.setImageDrawable(r1);	 Catch:{ Throwable -> 0x00df }
        r3 = 1;
        r4 = r8 instanceof org.xutils.common.Callback.CacheCallback;	 Catch:{ Throwable -> 0x00db, all -> 0x00d8 }
        if (r4 == 0) goto L_0x00c1;
    L_0x0098:
        r4 = r8;
        r4 = (org.xutils.common.Callback.CacheCallback) r4;	 Catch:{ Throwable -> 0x00db, all -> 0x00d8 }
        r1 = r4.onCache(r1);	 Catch:{ Throwable -> 0x00db, all -> 0x00d8 }
        if (r1 != 0) goto L_0x00c7;
    L_0x00a1:
        r0 = new org.xutils.image.a;	 Catch:{ Throwable -> 0x00be, all -> 0x00bb }
        r0.<init>();	 Catch:{ Throwable -> 0x00be, all -> 0x00bb }
        r0 = r0.b(r5, r6, r7, r8);	 Catch:{ Throwable -> 0x00be, all -> 0x00bb }
        if (r1 == 0) goto L_0x00ba;
    L_0x00ac:
        if (r8 == 0) goto L_0x00ba;
    L_0x00ae:
        r8.onFinished();	 Catch:{ Throwable -> 0x00b2 }
        goto L_0x00ba;
    L_0x00b2:
        r5 = move-exception;
        r6 = r5.getMessage();
        org.xutils.common.util.LogUtil.e(r6, r5);
    L_0x00ba:
        return r0;
    L_0x00bb:
        r5 = move-exception;
        r2 = r1;
        goto L_0x00f4;
    L_0x00be:
        r0 = move-exception;
        r3 = r1;
        goto L_0x00e1;
    L_0x00c1:
        if (r8 == 0) goto L_0x00c6;
    L_0x00c3:
        r8.onSuccess(r1);	 Catch:{ Throwable -> 0x00db, all -> 0x00d8 }
    L_0x00c6:
        r1 = 1;
    L_0x00c7:
        if (r1 == 0) goto L_0x00d7;
    L_0x00c9:
        if (r8 == 0) goto L_0x00d7;
    L_0x00cb:
        r8.onFinished();	 Catch:{ Throwable -> 0x00cf }
        goto L_0x00d7;
    L_0x00cf:
        r5 = move-exception;
        r6 = r5.getMessage();
        org.xutils.common.util.LogUtil.e(r6, r5);
    L_0x00d7:
        return r0;
    L_0x00d8:
        r5 = move-exception;
        r2 = 1;
        goto L_0x00f4;
    L_0x00db:
        r0 = move-exception;
        goto L_0x00e1;
    L_0x00dd:
        r5 = move-exception;
        goto L_0x00f4;
    L_0x00df:
        r0 = move-exception;
        r3 = 0;
    L_0x00e1:
        r1 = r0.getMessage();	 Catch:{ all -> 0x00f2 }
        org.xutils.common.util.LogUtil.e(r1, r0);	 Catch:{ all -> 0x00f2 }
        r0 = new org.xutils.image.a;	 Catch:{ all -> 0x00dd }
        r0.<init>();	 Catch:{ all -> 0x00dd }
        r5 = r0.b(r5, r6, r7, r8);	 Catch:{ all -> 0x00dd }
        return r5;
    L_0x00f2:
        r5 = move-exception;
        r2 = r3;
    L_0x00f4:
        if (r2 == 0) goto L_0x0104;
    L_0x00f6:
        if (r8 == 0) goto L_0x0104;
    L_0x00f8:
        r8.onFinished();	 Catch:{ Throwable -> 0x00fc }
        goto L_0x0104;
    L_0x00fc:
        r6 = move-exception;
        r7 = r6.getMessage();
        org.xutils.common.util.LogUtil.e(r7, r6);
    L_0x0104:
        throw r5;
    L_0x0105:
        r0 = new org.xutils.image.a;
        r0.<init>();
        r5 = r0.b(r5, r6, r7, r8);
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.image.a.a(android.widget.ImageView, java.lang.String, org.xutils.image.ImageOptions, org.xutils.common.Callback$CommonCallback):org.xutils.common.Callback$Cancelable");
    }

    private Cancelable b(ImageView imageView, String str, ImageOptions imageOptions, CommonCallback<Drawable> commonCallback) {
        this.c = new WeakReference(imageView);
        this.b = imageOptions;
        this.a = new b(str, imageOptions);
        this.i = commonCallback;
        if (commonCallback instanceof ProgressCallback) {
            this.l = (ProgressCallback) commonCallback;
        }
        if (commonCallback instanceof PrepareCallback) {
            this.j = (PrepareCallback) commonCallback;
        }
        if (commonCallback instanceof CacheCallback) {
            this.k = (CacheCallback) commonCallback;
        }
        if (imageOptions.isForceLoadingDrawable()) {
            Drawable loadingDrawable = imageOptions.getLoadingDrawable(imageView);
            imageView.setScaleType(imageOptions.getPlaceholderScaleType());
            imageView.setImageDrawable(new AsyncDrawable(this, loadingDrawable));
        } else {
            imageView.setImageDrawable(new AsyncDrawable(this, imageView.getDrawable()));
        }
        RequestParams a = a(str, imageOptions);
        if (imageView instanceof a) {
            synchronized (o) {
                o.put(str, (a) imageView);
            }
        }
        Cancelable cancelable = x.http().get(a, this);
        this.h = cancelable;
        return cancelable;
    }

    public void cancel() {
        this.f = true;
        this.g = true;
        if (this.h != null) {
            this.h.cancel();
        }
    }

    public boolean isCancelled() {
        return this.g || !a(false);
    }

    public void onWaiting() {
        if (this.l != null) {
            this.l.onWaiting();
        }
    }

    public void onStarted() {
        if (a(true) && this.l != null) {
            this.l.onStarted();
        }
    }

    public void onLoading(long j, long j2, boolean z) {
        if (a(true) && this.l != null) {
            this.l.onLoading(j, j2, z);
        }
    }

    public Type getLoadType() {
        return p;
    }

    public Drawable prepare(File file) {
        if (!a(true)) {
            return null;
        }
        try {
            Object obj = this.j != null ? (Drawable) this.j.prepare(file) : null;
            if (obj == null) {
                obj = ImageDecoder.a(file, this.b, (Cancelable) this);
            }
            if (obj != null && (obj instanceof d)) {
                ((d) obj).setMemCacheKey(this.a);
                n.put(this.a, obj);
            }
            return obj;
        } catch (IOException e) {
            IOUtil.deleteFileOrDir(file);
            LogUtil.w(e.getMessage(), e);
            return null;
        }
    }

    public boolean onCache(Drawable drawable) {
        if (!a(true) || drawable == null) {
            return false;
        }
        this.q = true;
        a(drawable);
        if (this.k != null) {
            return this.k.onCache(drawable);
        }
        if (this.i == null) {
            return true;
        }
        this.i.onSuccess(drawable);
        return true;
    }

    public void onSuccess(Drawable drawable) {
        if (a(this.q ^ 1) && drawable != null) {
            a(drawable);
            if (this.i != null) {
                this.i.onSuccess(drawable);
            }
        }
    }

    public void onError(Throwable th, boolean z) {
        this.f = true;
        if (!a(false)) {
            return;
        }
        if (th instanceof FileLockedException) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ImageFileLocked: ");
            stringBuilder.append(this.a.a);
            LogUtil.d(stringBuilder.toString());
            x.task().postDelayed(new Runnable() {
                public void run() {
                    a.a((ImageView) a.this.c.get(), a.this.a.a, a.this.b, a.this.i);
                }
            }, 10);
            return;
        }
        LogUtil.e(this.a.a, th);
        c();
        if (this.i != null) {
            this.i.onError(th, z);
        }
    }

    public void onCancelled(CancelledException cancelledException) {
        this.f = true;
        if (a(false) && this.i != null) {
            this.i.onCancelled(cancelledException);
        }
    }

    public void onFinished() {
        this.f = true;
        if (((ImageView) this.c.get()) instanceof a) {
            synchronized (o) {
                o.remove(this.a.a);
            }
        }
        if (a(false) && this.i != null) {
            this.i.onFinished();
        }
    }

    private static RequestParams a(String str, ImageOptions imageOptions) {
        RequestParams requestParams = new RequestParams(str);
        requestParams.setCacheDirName("xUtils_img");
        requestParams.setConnectTimeout(TXRecordCommon.AUDIO_SAMPLERATE_8000);
        requestParams.setPriority(Priority.BG_LOW);
        requestParams.setExecutor(m);
        requestParams.setCancelFast(true);
        requestParams.setUseCookie(false);
        if (imageOptions == null) {
            return requestParams;
        }
        ParamsBuilder paramsBuilder = imageOptions.getParamsBuilder();
        return paramsBuilder != null ? paramsBuilder.buildParams(requestParams, imageOptions) : requestParams;
    }

    private boolean a(boolean z) {
        ImageView imageView = (ImageView) this.c.get();
        if (imageView == null) {
            return false;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof AsyncDrawable) {
            a imageLoader = ((AsyncDrawable) drawable).getImageLoader();
            if (imageLoader != null) {
                if (imageLoader == this) {
                    if (imageView.getVisibility() == 0) {
                        return true;
                    }
                    imageLoader.cancel();
                    return false;
                } else if (this.e > imageLoader.e) {
                    imageLoader.cancel();
                    return true;
                } else {
                    cancel();
                    return false;
                }
            }
        } else if (z) {
            cancel();
            return false;
        }
        return true;
    }

    private void a(Drawable drawable) {
        ImageView imageView = (ImageView) this.c.get();
        if (imageView != null) {
            imageView.setScaleType(this.b.getImageScaleType());
            if (drawable instanceof GifDrawable) {
                if (imageView.getScaleType() == ScaleType.CENTER) {
                    imageView.setScaleType(ScaleType.CENTER_INSIDE);
                }
                imageView.setLayerType(1, null);
            }
            if (this.b.getAnimation() != null) {
                ImageAnimationHelper.animationDisplay(imageView, drawable, this.b.getAnimation());
            } else if (this.b.isFadeIn()) {
                ImageAnimationHelper.fadeInDisplay(imageView, drawable);
            } else {
                imageView.setImageDrawable(drawable);
            }
        }
    }

    private void c() {
        ImageView imageView = (ImageView) this.c.get();
        if (imageView != null) {
            Drawable failureDrawable = this.b.getFailureDrawable(imageView);
            imageView.setScaleType(this.b.getPlaceholderScaleType());
            imageView.setImageDrawable(failureDrawable);
        }
    }

    private static void a(final ImageView imageView, final ImageOptions imageOptions, final String str, final CommonCallback<?> commonCallback) {
        x.task().autoPost(new Runnable() {
            public void run() {
                try {
                    if (commonCallback instanceof ProgressCallback) {
                        ((ProgressCallback) commonCallback).onWaiting();
                    }
                    if (!(imageView == null || imageOptions == null)) {
                        imageView.setScaleType(imageOptions.getPlaceholderScaleType());
                        imageView.setImageDrawable(imageOptions.getFailureDrawable(imageView));
                    }
                    if (commonCallback != null) {
                        commonCallback.onError(new IllegalArgumentException(str), false);
                    }
                    if (commonCallback != null) {
                        commonCallback.onFinished();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    LogUtil.e(th.getMessage(), th);
                    return;
                }
                if (commonCallback != null) {
                    commonCallback.onFinished();
                }
            }
        });
    }
}

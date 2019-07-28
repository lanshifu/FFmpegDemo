package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.luck.picture.lib.photoview.PhotoView;
import com.one.tomato.entity.ImageBean;
import com.one.tomato.utils.b;
import com.one.tomato.utils.k;
import com.one.tomato.utils.o;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.util.Arrays;

/* compiled from: ImageLoaderUtil */
/* renamed from: pz */
public class pz {

    /* compiled from: ImageLoaderUtil */
    /* renamed from: pz$3 */
    static /* synthetic */ class 3 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        static {
            /*
            r0 = android.widget.ImageView.ScaleType.values();
            r0 = r0.length;
            r0 = new int[r0];
            a = r0;
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = android.widget.ImageView.ScaleType.CENTER_INSIDE;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = android.widget.ImageView.ScaleType.FIT_CENTER;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = android.widget.ImageView.ScaleType.CENTER_CROP;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r2 = 3;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.pz$3.<clinit>():void");
        }
    }

    /* compiled from: ImageLoaderUtil */
    /* renamed from: pz$1 */
    static class 1 implements RequestListener<Drawable> {
        /* renamed from: a */
        public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
            return false;
        }

        1() {
        }

        public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
            pz.b();
            return false;
        }
    }

    /* compiled from: ImageLoaderUtil */
    /* renamed from: pz$4 */
    static class 4 implements RequestListener<Drawable> {
        /* renamed from: a */
        public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
            return false;
        }

        4() {
        }

        public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
            pz.b();
            return false;
        }
    }

    public static void a(Context context, ImageView imageView, String str, RequestOptions requestOptions) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("loadNormalRelativeImage: ");
                stringBuilder.append(pd.a().c());
                stringBuilder.append(str);
                o.b(stringBuilder.toString());
                Glide.with(context).load(new py(pd.a().c(), str)).apply(requestOptions).listener(new 1()).into(imageView);
            }
        }
    }

    public static void b(Context context, ImageView imageView, String str, RequestOptions requestOptions) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("loadNormalAbsoluteImage: ");
                stringBuilder.append(str);
                o.b(stringBuilder.toString());
                Glide.with(context).load(new py("", str)).apply(requestOptions).listener(new 4()).into(imageView);
            }
        }
    }

    public static void c(Context context, ImageView imageView, String str, RequestOptions requestOptions) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                if (str.endsWith("GIF") || str.endsWith("gif")) {
                    Glide.with(context).asGif().load(str).apply(requestOptions).into(imageView);
                } else {
                    Glide.with(context).asBitmap().load(str).apply(requestOptions).into(imageView);
                }
            }
        }
    }

    public static void a(Context context, ImageView imageView, int i) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                Glide.with(context).asBitmap().load(Integer.valueOf(i)).into(imageView);
            }
        }
    }

    public static void b(Context context, ImageView imageView, int i) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                Glide.with(context).asGif().load(Integer.valueOf(i)).into(imageView);
            }
        }
    }

    public static void a(Context context, ImageView imageView, ImageBean imageBean) {
        if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
            String a;
            String str;
            boolean a2 = pz.a(imageBean.getImage());
            boolean isGif = imageBean.isGif();
            if (a2) {
                String l = pd.a().l();
                a = pz.a(imageBean.getImage(), isGif);
                str = l;
            } else {
                str = pd.a().c();
                a = imageBean.getImage();
            }
            pz.a(context, imageView, imageBean, str, a, a2);
        }
    }

    public static void a(Context context, ImageView imageView, ImageBean imageBean, int i, int i2) {
        if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
            String a;
            String str;
            boolean a2 = pz.a(imageBean.getImage());
            boolean isGif = imageBean.isGif();
            if (a2) {
                String l = pd.a().l();
                a = pz.a(imageBean.getImage(), isGif, i, i2);
                str = l;
            } else {
                str = pd.a().c();
                a = imageBean.getImage();
            }
            pz.a(context, imageView, imageBean, str, a, a2);
        }
    }

    private static void a(Context context, ImageView imageView, ImageBean imageBean, String str, String str2, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("loadHeadImage: ");
        stringBuilder.append(str);
        stringBuilder.append(str2);
        o.b(stringBuilder.toString());
        imageView.setTag(2131296612, imageBean.getImage());
        RequestBuilder load = Glide.with(context).asFile().load(new py(str, str2));
        final ImageView imageView2 = imageView;
        final Context context2 = context;
        final ImageBean imageBean2 = imageBean;
        final String str3 = str2;
        final boolean z2 = z;
        load.into(new SimpleTarget<File>() {
            public void onLoadStarted(@Nullable Drawable drawable) {
                super.onLoadStarted(drawable);
                imageView2.setImageResource(2131231040);
            }

            /* renamed from: a */
            public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                pz.b(context2, imageView2, imageBean2, str3, z2, file, pz.b(imageView2));
            }

            public void onLoadFailed(@Nullable Drawable drawable) {
                super.onLoadFailed(drawable);
                pz.b();
            }
        });
    }

    public static void b(Context context, ImageView imageView, ImageBean imageBean) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                String l;
                String b;
                imageView.setTag(2131296612, imageBean.getImage());
                final boolean a = pz.a(imageBean.getImage());
                boolean isGif = imageBean.isGif();
                if (a) {
                    l = pd.a().l();
                    b = pz.b(imageBean.getImage(), isGif);
                } else {
                    l = pd.a().c();
                    b = imageBean.getImage();
                }
                final String str = b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("loadRecyclerThumbImage: ");
                stringBuilder.append(l);
                stringBuilder.append(str);
                o.b(stringBuilder.toString());
                RequestBuilder load = Glide.with(context).asFile().load(new py(l, str));
                final ImageView imageView2 = imageView;
                final Context context2 = context;
                final ImageBean imageBean2 = imageBean;
                load.into(new SimpleTarget<File>() {
                    public void onLoadStarted(@Nullable Drawable drawable) {
                        super.onLoadStarted(drawable);
                        imageView2.setImageResource(2131231039);
                    }

                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        pz.b(context2, imageView2, imageBean2, str, a, file, pz.a(imageView2));
                    }

                    public void onLoadFailed(@Nullable Drawable drawable) {
                        super.onLoadFailed(drawable);
                        pz.b();
                    }
                });
            }
        }
    }

    public static void a(Context context, ImageView imageView, ProgressBar progressBar, ImageBean imageBean, int i) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                String l;
                String b;
                imageView.setTag(2131296612, imageBean.getImage());
                final boolean a = pz.a(imageBean.getImage());
                boolean isGif = imageBean.isGif();
                if (a) {
                    l = pd.a().l();
                    b = pz.b(imageBean.getImage(), isGif);
                } else {
                    l = pd.a().c();
                    b = imageBean.getImage();
                }
                final String str = b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("loadViewPagerOriginImage: ");
                stringBuilder.append(l);
                stringBuilder.append(str);
                o.b(stringBuilder.toString());
                RequestBuilder load = Glide.with(context).asFile().load(new py(l, str));
                final ProgressBar progressBar2 = progressBar;
                final Context context2 = context;
                final ImageView imageView2 = imageView;
                final ImageBean imageBean2 = imageBean;
                load.into(new SimpleTarget<File>() {
                    public void onLoadStarted(@Nullable Drawable drawable) {
                        super.onLoadStarted(drawable);
                        if (progressBar2 != null) {
                            progressBar2.setVisibility(0);
                        }
                    }

                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        pz.b(context2, imageView2, imageBean2, str, a, file, pz.e(imageView2));
                        if (progressBar2 != null) {
                            progressBar2.setVisibility(8);
                        }
                    }

                    public void onLoadFailed(@Nullable Drawable drawable) {
                        super.onLoadFailed(drawable);
                        if (progressBar2 != null) {
                            progressBar2.setVisibility(8);
                        }
                        pz.b();
                    }
                });
            }
        }
    }

    public static void a(Context context, SubsamplingScaleImageView subsamplingScaleImageView, ProgressBar progressBar, ImageBean imageBean, boolean z) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                String l;
                String b;
                final boolean a = pz.a(imageBean.getImage());
                boolean isGif = imageBean.isGif();
                if (a) {
                    l = pd.a().l();
                    b = pz.b(imageBean.getImage(), isGif);
                } else {
                    l = pd.a().c();
                    b = imageBean.getImage();
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("loadViewPagerLongImage: ");
                stringBuilder.append(l);
                stringBuilder.append(imageBean.getImage());
                o.b(stringBuilder.toString());
                RequestBuilder load = Glide.with(context).asFile().load(new py(l, b));
                final ProgressBar progressBar2 = progressBar;
                final SubsamplingScaleImageView subsamplingScaleImageView2 = subsamplingScaleImageView;
                final boolean z2 = z;
                final ImageBean imageBean2 = imageBean;
                load.into(new SimpleTarget<File>() {
                    public void onLoadStarted(@Nullable Drawable drawable) {
                        super.onLoadStarted(drawable);
                        if (progressBar2 != null) {
                            progressBar2.setVisibility(0);
                        }
                    }

                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        subsamplingScaleImageView2.setQuickScaleEnabled(z2);
                        subsamplingScaleImageView2.setZoomEnabled(z2);
                        subsamplingScaleImageView2.setPanEnabled(z2);
                        subsamplingScaleImageView2.setDoubleTapZoomDuration(100);
                        subsamplingScaleImageView2.setMinimumScaleType(2);
                        subsamplingScaleImageView2.setDoubleTapZoomDpi(2);
                        File e = k.e();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(pv.a(imageBean2.getImage()));
                        stringBuilder.append("");
                        File file2 = new File(e, stringBuilder.toString());
                        if (!file2.exists()) {
                            if (a) {
                                b.b(file, file2);
                            } else {
                                b.a(file, file2);
                            }
                        }
                        subsamplingScaleImageView2.setImage(ImageSource.uri(file2.getAbsolutePath()), new ImageViewState(1.0f, new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO), 0));
                        if (progressBar2 != null) {
                            progressBar2.setVisibility(8);
                        }
                    }

                    public void onLoadFailed(@Nullable Drawable drawable) {
                        super.onLoadFailed(drawable);
                        if (progressBar2 != null) {
                            progressBar2.setVisibility(8);
                        }
                        pz.b();
                    }
                });
            }
        }
    }

    public static void a(Context context, PhotoView photoView, SubsamplingScaleImageView subsamplingScaleImageView, ImageBean imageBean) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("loadViewPagerImageByLocal: ");
                stringBuilder.append(imageBean.getImage());
                o.b(stringBuilder.toString());
                final SubsamplingScaleImageView subsamplingScaleImageView2 = subsamplingScaleImageView;
                final PhotoView photoView2 = photoView;
                final ImageBean imageBean2 = imageBean;
                Glide.with(context).asBitmap().load(imageBean.getImage()).into(new SimpleTarget<Bitmap>(480, 800) {
                    /* renamed from: a */
                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        if (bitmap.getHeight() > bitmap.getWidth() * 3) {
                            subsamplingScaleImageView2.setVisibility(0);
                            photoView2.setVisibility(8);
                            subsamplingScaleImageView2.setQuickScaleEnabled(true);
                            subsamplingScaleImageView2.setZoomEnabled(true);
                            subsamplingScaleImageView2.setPanEnabled(true);
                            subsamplingScaleImageView2.setDoubleTapZoomDuration(100);
                            subsamplingScaleImageView2.setMinimumScaleType(2);
                            subsamplingScaleImageView2.setDoubleTapZoomDpi(2);
                            subsamplingScaleImageView2.setImage(ImageSource.uri(imageBean2.getImage()), new ImageViewState(1.0f, new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO), 0));
                            return;
                        }
                        photoView2.setVisibility(0);
                        subsamplingScaleImageView2.setVisibility(8);
                        photoView2.setImageBitmap(bitmap);
                    }
                });
            }
        }
    }

    public static void a(final Context context, final ImageView imageView, String str, final ImageBean imageBean, RequestOptions requestOptions) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("loadVerifySecImage: ");
                stringBuilder.append(str);
                stringBuilder.append(imageBean.getImage());
                o.b(stringBuilder.toString());
                final boolean a = pz.a(imageBean.getImage());
                imageView.setTag(2131296612, imageBean.getImage());
                Glide.with(context).asFile().load(new py(str, imageBean.getImage())).apply(requestOptions).into(new SimpleTarget<File>() {
                    public void onLoadStarted(@Nullable Drawable drawable) {
                        super.onLoadStarted(drawable);
                        imageView.setImageResource(2131231039);
                    }

                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        pz.b(context, imageView, imageBean, imageBean.getImage(), a, file, pz.a(imageView));
                    }

                    public void onLoadFailed(@Nullable Drawable drawable) {
                        super.onLoadFailed(drawable);
                        pz.b();
                    }
                });
            }
        }
    }

    public static void a(Context context, ImageView imageView, ImageBean imageBean, RequestOptions requestOptions) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                String l;
                final boolean a = pz.a(imageBean.getImage());
                if (a) {
                    l = pd.a().l();
                } else {
                    l = pd.a().c();
                }
                imageView.setTag(2131296612, imageBean.getImage());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("loadSecImage: ");
                stringBuilder.append(l);
                stringBuilder.append(imageBean.getImage());
                o.b(stringBuilder.toString());
                final ImageView imageView2 = imageView;
                final Context context2 = context;
                final ImageBean imageBean2 = imageBean;
                final RequestOptions requestOptions2 = requestOptions;
                Glide.with(context).asFile().load(new py(l, imageBean.getImage())).apply(requestOptions).into(new SimpleTarget<File>() {
                    public void onLoadStarted(@Nullable Drawable drawable) {
                        super.onLoadStarted(drawable);
                        imageView2.setImageResource(2131231039);
                    }

                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        pz.b(context2, imageView2, imageBean2, imageBean2.getImage(), a, file, requestOptions2);
                    }

                    public void onLoadFailed(@Nullable Drawable drawable) {
                        super.onLoadFailed(drawable);
                        pz.b();
                    }
                });
            }
        }
    }

    private static void b(Context context, ImageView imageView, ImageBean imageBean, String str, boolean z, File file, RequestOptions requestOptions) {
        if (context != null) {
            if (!(context instanceof Activity) || !((Activity) context).isDestroyed()) {
                try {
                    boolean isGif = imageBean.isGif();
                    File e = k.e();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(pv.a(str));
                    stringBuilder.append("");
                    File file2 = new File(e, stringBuilder.toString());
                    if (!file2.exists()) {
                        if (z) {
                            b.b(file, file2);
                        } else {
                            b.a(file, file2);
                        }
                    }
                    String str2;
                    if (imageBean.isGif()) {
                        if (imageView != null) {
                            str2 = (String) imageView.getTag(2131296612);
                            if (!TextUtils.isEmpty(str2) && str2.equals(imageBean.getImage())) {
                                Glide.with(context).asGif().load(file2).apply(requestOptions).into(imageView);
                            }
                        }
                    } else if (imageView != null) {
                        str2 = (String) imageView.getTag(2131296612);
                        if (!TextUtils.isEmpty(str2) && str2.equals(imageBean.getImage())) {
                            Glide.with(context).asBitmap().load(file2).apply(requestOptions).into(imageView);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("图片url：");
                    stringBuilder2.append(str);
                    stringBuilder2.append(",下载成功，加载异常");
                    o.c(stringBuilder2.toString());
                }
            }
        }
    }

    private static void b() {
        pb.b("image");
        pb.b("ttViewPicture");
    }

    public static RequestOptions a(ImageView imageView) {
        return pz.a(imageView, ScaleType.CENTER_CROP, 2131231039, 2131231039, false);
    }

    public static RequestOptions b(ImageView imageView) {
        return pz.a(imageView, ScaleType.CENTER_CROP, 2131231040, 2131231040, true);
    }

    public static RequestOptions c(ImageView imageView) {
        return pz.a(imageView, ScaleType.CENTER_CROP, 2131231038, 2131231038, true);
    }

    public static RequestOptions d(ImageView imageView) {
        return pz.a(imageView, ScaleType.CENTER_CROP, 2131231039, 2131231039, false).transform(new RoundedCorners(imageView.getResources().getDimensionPixelSize(2131165372)));
    }

    public static RequestOptions e(ImageView imageView) {
        return pz.a(imageView, ScaleType.CENTER_INSIDE, 2131231039, 2131231039, false);
    }

    public static RequestOptions f(ImageView imageView) {
        return pz.a(imageView, ScaleType.FIT_CENTER, 2131231039, 2131231039, false);
    }

    public static RequestOptions a(ImageView imageView, ScaleType scaleType, int i, int i2, boolean z) {
        RequestOptions requestOptions = new RequestOptions();
        switch (3.a[scaleType.ordinal()]) {
            case 1:
                requestOptions.centerInside();
                break;
            case 2:
                requestOptions.fitCenter();
                break;
            case 3:
                requestOptions.centerCrop();
                break;
            default:
                requestOptions.centerCrop();
                break;
        }
        if (i > 0) {
            requestOptions.placeholder(i);
        }
        if (i2 > 0) {
            requestOptions.error(i2);
        }
        if (z) {
            requestOptions.circleCrop();
        }
        requestOptions.diskCacheStrategy(DiskCacheStrategy.DATA);
        return requestOptions;
    }

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str) && Arrays.asList(str.split("/")).contains("_s3")) {
            return true;
        }
        return false;
    }

    public static String a(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (i == 0 || i2 == 0) {
            return str;
        }
        String[] split = str.split("\\.");
        String str2 = split[0];
        str = split[1];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append("_");
        stringBuilder.append(i);
        stringBuilder.append("x");
        stringBuilder.append(i2);
        stringBuilder.append(".");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static String a(String str, boolean z) {
        return z ? str : pz.a(str, 64, 64);
    }

    public static String a(String str, boolean z, int i, int i2) {
        return z ? str : pz.a(str, i, i2);
    }

    public static String b(String str, boolean z) {
        return z ? str : pz.a(str, 540, 960);
    }
}

package com.tomatolive.library.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.widget.ImageView;
import com.blankj.utilcode.util.b;
import com.blankj.utilcode.util.e;
import com.blankj.utilcode.util.f;
import com.blankj.utilcode.util.j;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.opensource.svgaplayer.c;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.matisse.GlideApp;
import defpackage.wd;
import defpackage.wm;
import defpackage.xl;
import io.reactivex.k;
import io.reactivex.r;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation.CornerType;

/* compiled from: GlideUtils */
public class i {
    private static Map<String, String> a = new HashMap();

    public static void a(Fragment fragment, ImageView imageView, String str) {
        a(fragment, imageView, str, R.drawable.fq_ic_placeholder_avatar);
    }

    public static void a(Context context, ImageView imageView, String str) {
        a(context, imageView, str, R.drawable.fq_ic_placeholder_avatar);
    }

    public static void a(final Fragment fragment, final ImageView imageView, final String str, @DrawableRes final int i) {
        if (!a(fragment)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(i);
            return;
        }
        imageView.setImageResource(i);
        if (!a(str)) {
            b(fragment, imageView, str, i);
        } else if (d(str)) {
            b(fragment, imageView, e(str), i);
        } else {
            try {
                GlideApp.with(fragment).asFile().load(str).skipMemoryCache(false).dontAnimate().placeholder(i).into(new SimpleTarget<File>() {
                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        i.a(fragment, imageView, file, i, str);
                    }
                });
            } catch (Exception unused) {
                imageView.setImageResource(i);
            } catch (OutOfMemoryError unused2) {
                imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar);
            }
        }
    }

    public static void a(final Context context, final ImageView imageView, final String str, @DrawableRes final int i) {
        if (!a(context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(i);
            return;
        }
        imageView.setImageResource(i);
        if (!a(str)) {
            f(context, imageView, str, i);
        } else if (d(str)) {
            f(context, imageView, e(str), i);
        } else {
            try {
                GlideApp.with(context).asFile().load(str).skipMemoryCache(false).dontAnimate().placeholder(i).into(new SimpleTarget<File>() {
                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        i.b(context, imageView, file, i, str);
                    }
                });
            } catch (Exception unused) {
                imageView.setImageResource(i);
            } catch (OutOfMemoryError unused2) {
                imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar);
            }
        }
    }

    public static void a(Context context, ImageView imageView, String str, int i, int i2) {
        if (!a(context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar_white);
        } else if (!a(str)) {
            a(context, imageView, str, i, i2, R.drawable.fq_ic_placeholder_avatar_white);
        } else if (d(str)) {
            a(context, imageView, e(str), i, i2, R.drawable.fq_ic_placeholder_avatar_white);
        } else {
            try {
                final Context context2 = context;
                final ImageView imageView2 = imageView;
                final int i3 = i;
                final int i4 = i2;
                final String str2 = str;
                GlideApp.with(context).asFile().load(str).skipMemoryCache(false).dontAnimate().into(new SimpleTarget<File>() {
                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        i.a(context2, imageView2, file, R.drawable.fq_ic_placeholder_avatar_white, i3, i4, str2);
                    }
                });
            } catch (Exception unused) {
                imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar);
            } catch (OutOfMemoryError unused2) {
                imageView.setImageResource(R.drawable.fq_ic_placeholder_avatar);
            }
        }
    }

    public static void a(Context context, ImageView imageView) {
        if (a(context)) {
            GlideApp.with(context).asGif().load(Integer.valueOf(R.drawable.fq_ic_living_icon_circle_gif_2)).placeholder(R.drawable.fq_ic_living_icon_circle).error(R.drawable.fq_ic_living_icon_circle).into(imageView);
        }
    }

    public static void a(Context context, ImageView imageView, @DrawableRes int i) {
        if (a(context)) {
            GlideApp.with(context).load(Integer.valueOf(i)).centerCrop().into(imageView);
        }
    }

    public static void a(Context context, ImageView imageView, File file, @DrawableRes int i) {
        if (a(context)) {
            GlideApp.with(context).asBitmap().load(file).centerCrop().placeholder(i).into(imageView);
        }
    }

    public static void a(Context context, ImageView imageView, Object obj, @DrawableRes int i) {
        if (a(context)) {
            String str = "";
            if (obj instanceof String) {
                str = c((String) obj);
            }
            if (TextUtils.isEmpty(str)) {
                imageView.setImageResource(i);
            } else {
                GlideApp.with(context).asBitmap().load(str).centerCrop().placeholder(i).into(imageView);
            }
        }
    }

    public static void b(final Context context, final ImageView imageView, String str, @DrawableRes final int i) {
        if (!a(context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(i);
            return;
        }
        str = c(str);
        if (!a(str)) {
            e(context, imageView, str, i);
        } else if (d(str)) {
            e(context, imageView, e(str), i);
        } else {
            try {
                GlideApp.with(context).asFile().load(str).into(new SimpleTarget<File>() {
                    public void onLoadStarted(@Nullable Drawable drawable) {
                        super.onLoadStarted(drawable);
                        imageView.setImageResource(i);
                    }

                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        i.a(context, imageView, file, i, str);
                    }

                    public void onLoadFailed(@Nullable Drawable drawable) {
                        super.onLoadFailed(drawable);
                        imageView.setImageResource(i);
                    }
                });
            } catch (Exception unused) {
                imageView.setImageResource(i);
            } catch (OutOfMemoryError unused2) {
                imageView.setImageResource(i);
            }
        }
    }

    public static void b(Context context, ImageView imageView, String str) {
        if (a(context)) {
            GlideApp.with(context).asBitmap().load(c(str)).into(imageView);
        }
    }

    public static void c(Context context, ImageView imageView, String str) {
        if (a(context)) {
            GlideApp.with(context).asBitmap().load(c(str)).centerCrop().into(imageView);
        }
    }

    public static void b(Context context, ImageView imageView, String str, int i, int i2) {
        if (a(context)) {
            GlideApp.with(context).asBitmap().load(c(str)).centerCrop().override(i, i2).into(imageView);
        }
    }

    public static void c(final Context context, final ImageView imageView, String str, @DrawableRes final int i) {
        if (!a(context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(i);
            return;
        }
        str = c(str);
        if (a(str)) {
            try {
                GlideApp.with(context).asFile().load(str).into(new SimpleTarget<File>() {
                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        i.c(context, imageView, file, i, str);
                    }
                });
            } catch (Exception unused) {
                imageView.setImageResource(i);
            } catch (OutOfMemoryError unused2) {
                imageView.setImageResource(i);
            }
            return;
        }
        GlideApp.with(context).asBitmap().load(str).transforms(new CenterCrop(), new BlurTransformation(25, 6)).placeholder(i).into(imageView);
    }

    public static void b(Context context, ImageView imageView, @DrawableRes int i) {
        if (a(context)) {
            GlideApp.with(context).load(Integer.valueOf(i)).transforms(new CenterCrop(), new BlurTransformation(25, 6)).into(imageView);
        }
    }

    public static void d(Context context, ImageView imageView, String str, int i) {
        if (a(context)) {
            GlideApp.with(context).asBitmap().load(c(str)).skipMemoryCache(false).dontAnimate().transforms(new CenterCrop(), new RoundedCornersTransformation(b.a((float) i), 0)).into(imageView);
        }
    }

    public static void b(Context context, ImageView imageView, File file, int i) {
        if (a(context)) {
            GlideApp.with(context).asBitmap().load(file).transforms(new CenterCrop(), new RoundedCornersTransformation(b.a((float) i), 0)).into(imageView);
        }
    }

    public static void a(Fragment fragment, ImageView imageView, String str, int i, @DrawableRes int i2) {
        if (!a(fragment.getContext())) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(i2);
            return;
        }
        imageView.setImageResource(i2);
        final String c = c(str);
        if (!a(c)) {
            c(fragment, imageView, c, i, i2);
        } else if (d(c)) {
            c(fragment, imageView, e(c), i, i2);
        } else {
            try {
                final Fragment fragment2 = fragment;
                final ImageView imageView2 = imageView;
                final int i3 = i;
                final int i4 = i2;
                GlideApp.with(fragment).asFile().load(c).into(new SimpleTarget<File>() {
                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        i.a(fragment2, imageView2, file, i3, i4, c);
                    }

                    public void onLoadFailed(@Nullable Drawable drawable) {
                        super.onLoadFailed(drawable);
                        imageView2.setImageResource(i4);
                    }
                });
            } catch (Exception unused) {
                imageView.setImageResource(i2);
            } catch (OutOfMemoryError unused2) {
                imageView.setImageResource(i2);
            }
        }
    }

    public static void c(Context context, ImageView imageView, String str, int i, @DrawableRes int i2) {
        if (!a(context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(i2);
            return;
        }
        imageView.setImageResource(i2);
        final String c = c(str);
        if (!a(c)) {
            e(context, imageView, c, i, i2);
        } else if (d(c)) {
            e(context, imageView, e(c), i, i2);
        } else {
            try {
                final Context context2 = context;
                final ImageView imageView2 = imageView;
                final int i3 = i;
                final int i4 = i2;
                GlideApp.with(context).asFile().load(c).into(new SimpleTarget<File>() {
                    /* renamed from: a */
                    public void onResourceReady(@NonNull File file, @Nullable Transition<? super File> transition) {
                        i.a(context2, imageView2, file, i3, i4, c);
                    }
                });
            } catch (Exception unused) {
                imageView.setImageResource(i2);
            } catch (OutOfMemoryError unused2) {
                imageView.setImageResource(i2);
            }
        }
    }

    public static void a(Context context, ImageView imageView, Object obj, int i, @DrawableRes int i2) {
        if (a(context)) {
            String str = "";
            if (obj instanceof String) {
                str = c((String) obj);
            }
            if (TextUtils.isEmpty(str)) {
                imageView.setImageResource(i2);
                return;
            }
            GlideApp.with(context).asBitmap().load(str).transforms(new CenterCrop(), new RoundedCornersTransformation(b.a((float) i), 0)).skipMemoryCache(false).dontAnimate().placeholder(i2).into(imageView);
        }
    }

    public static void a(Context context, ImageView imageView, @DrawableRes int i, int i2, CornerType cornerType) {
        GlideApp.with(context).asBitmap().load(Integer.valueOf(i)).transforms(new CenterCrop(), new RoundedCornersTransformation(b.a((float) i2), 0, cornerType)).into(imageView);
    }

    public static void a(Context context, ImageView imageView, String str, int i, CornerType cornerType, @DrawableRes int i2) {
        GlideApp.with(context).asBitmap().load(c(str)).placeholder(i2).transforms(new CenterCrop(), new RoundedCornersTransformation(b.a((float) i), 0, cornerType)).into(imageView);
    }

    public static File a(String str, File file, File file2) {
        if (str.contains("_s1")) {
            return a(file, file2);
        }
        return str.contains("_s3") ? b(file, file2) : null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x004c A:{SYNTHETIC, Splitter:B:33:0x004c} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0056 A:{SYNTHETIC, Splitter:B:38:0x0056} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0062 A:{SYNTHETIC, Splitter:B:45:0x0062} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x006c A:{SYNTHETIC, Splitter:B:50:0x006c} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x004c A:{SYNTHETIC, Splitter:B:33:0x004c} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0056 A:{SYNTHETIC, Splitter:B:38:0x0056} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0062 A:{SYNTHETIC, Splitter:B:45:0x0062} */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x006c A:{SYNTHETIC, Splitter:B:50:0x006c} */
    public static java.io.File a(java.io.File r7, java.io.File r8) {
        /*
        r0 = 0;
        r1 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0044, all -> 0x0040 }
        r1.<init>(r7);	 Catch:{ Exception -> 0x0044, all -> 0x0040 }
        r7 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x003d, all -> 0x003a }
        r7.<init>(r8);	 Catch:{ Exception -> 0x003d, all -> 0x003a }
        r2 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r2 = new byte[r2];	 Catch:{ Exception -> 0x0038 }
    L_0x000f:
        r3 = r1.read(r2);	 Catch:{ Exception -> 0x0038 }
        r4 = -1;
        if (r3 == r4) goto L_0x0024;
    L_0x0016:
        r3 = 0;
        r4 = r2[r3];	 Catch:{ Exception -> 0x0038 }
        r5 = 1;
        r6 = r2[r5];	 Catch:{ Exception -> 0x0038 }
        r2[r3] = r6;	 Catch:{ Exception -> 0x0038 }
        r2[r5] = r4;	 Catch:{ Exception -> 0x0038 }
        r7.write(r2);	 Catch:{ Exception -> 0x0038 }
        goto L_0x000f;
    L_0x0024:
        r7.flush();	 Catch:{ Exception -> 0x0038 }
        r1.close();	 Catch:{ Exception -> 0x002b }
        goto L_0x002f;
    L_0x002b:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x002f:
        r7.close();	 Catch:{ Exception -> 0x0033 }
        goto L_0x0037;
    L_0x0033:
        r7 = move-exception;
        r7.printStackTrace();
    L_0x0037:
        return r8;
    L_0x0038:
        r8 = move-exception;
        goto L_0x0047;
    L_0x003a:
        r8 = move-exception;
        r7 = r0;
        goto L_0x0060;
    L_0x003d:
        r8 = move-exception;
        r7 = r0;
        goto L_0x0047;
    L_0x0040:
        r8 = move-exception;
        r7 = r0;
        r1 = r7;
        goto L_0x0060;
    L_0x0044:
        r8 = move-exception;
        r7 = r0;
        r1 = r7;
    L_0x0047:
        r8.printStackTrace();	 Catch:{ all -> 0x005f }
        if (r1 == 0) goto L_0x0054;
    L_0x004c:
        r1.close();	 Catch:{ Exception -> 0x0050 }
        goto L_0x0054;
    L_0x0050:
        r8 = move-exception;
        r8.printStackTrace();
    L_0x0054:
        if (r7 == 0) goto L_0x005e;
    L_0x0056:
        r7.close();	 Catch:{ Exception -> 0x005a }
        goto L_0x005e;
    L_0x005a:
        r7 = move-exception;
        r7.printStackTrace();
    L_0x005e:
        return r0;
    L_0x005f:
        r8 = move-exception;
    L_0x0060:
        if (r1 == 0) goto L_0x006a;
    L_0x0062:
        r1.close();	 Catch:{ Exception -> 0x0066 }
        goto L_0x006a;
    L_0x0066:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x006a:
        if (r7 == 0) goto L_0x0074;
    L_0x006c:
        r7.close();	 Catch:{ Exception -> 0x0070 }
        goto L_0x0074;
    L_0x0070:
        r7 = move-exception;
        r7.printStackTrace();
    L_0x0074:
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.i.a(java.io.File, java.io.File):java.io.File");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0059 A:{SYNTHETIC, Splitter:B:39:0x0059} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0063 A:{SYNTHETIC, Splitter:B:44:0x0063} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x006f A:{SYNTHETIC, Splitter:B:51:0x006f} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0079 A:{SYNTHETIC, Splitter:B:56:0x0079} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x006f A:{SYNTHETIC, Splitter:B:51:0x006f} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0079 A:{SYNTHETIC, Splitter:B:56:0x0079} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0059 A:{SYNTHETIC, Splitter:B:39:0x0059} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0063 A:{SYNTHETIC, Splitter:B:44:0x0063} */
    public static java.io.File b(java.io.File r9, java.io.File r10) {
        /*
        r0 = 0;
        r1 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0051, all -> 0x004d }
        r1.<init>(r9);	 Catch:{ Exception -> 0x0051, all -> 0x004d }
        r9 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x004a, all -> 0x0047 }
        r9.<init>(r10);	 Catch:{ Exception -> 0x004a, all -> 0x0047 }
        r2 = 1;
        r2 = new byte[r2];	 Catch:{ Exception -> 0x0045 }
        r3 = -1;
        r4 = -1;
    L_0x0010:
        r5 = r1.read(r2);	 Catch:{ Exception -> 0x0045 }
        if (r5 == r3) goto L_0x0031;
    L_0x0016:
        r6 = 0;
        if (r4 != r3) goto L_0x0020;
    L_0x0019:
        r4 = r2[r6];	 Catch:{ Exception -> 0x0045 }
        r2 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r2 = new byte[r2];	 Catch:{ Exception -> 0x0045 }
        goto L_0x0010;
    L_0x0020:
        r7 = new byte[r5];	 Catch:{ Exception -> 0x0045 }
    L_0x0022:
        if (r6 >= r5) goto L_0x002d;
    L_0x0024:
        r8 = r2[r6];	 Catch:{ Exception -> 0x0045 }
        r8 = r8 ^ r4;
        r8 = (byte) r8;	 Catch:{ Exception -> 0x0045 }
        r7[r6] = r8;	 Catch:{ Exception -> 0x0045 }
        r6 = r6 + 1;
        goto L_0x0022;
    L_0x002d:
        r9.write(r7);	 Catch:{ Exception -> 0x0045 }
        goto L_0x0010;
    L_0x0031:
        r9.flush();	 Catch:{ Exception -> 0x0045 }
        r1.close();	 Catch:{ Exception -> 0x0038 }
        goto L_0x003c;
    L_0x0038:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x003c:
        r9.close();	 Catch:{ Exception -> 0x0040 }
        goto L_0x0044;
    L_0x0040:
        r9 = move-exception;
        r9.printStackTrace();
    L_0x0044:
        return r10;
    L_0x0045:
        r10 = move-exception;
        goto L_0x0054;
    L_0x0047:
        r10 = move-exception;
        r9 = r0;
        goto L_0x006d;
    L_0x004a:
        r10 = move-exception;
        r9 = r0;
        goto L_0x0054;
    L_0x004d:
        r10 = move-exception;
        r9 = r0;
        r1 = r9;
        goto L_0x006d;
    L_0x0051:
        r10 = move-exception;
        r9 = r0;
        r1 = r9;
    L_0x0054:
        r10.printStackTrace();	 Catch:{ all -> 0x006c }
        if (r1 == 0) goto L_0x0061;
    L_0x0059:
        r1.close();	 Catch:{ Exception -> 0x005d }
        goto L_0x0061;
    L_0x005d:
        r10 = move-exception;
        r10.printStackTrace();
    L_0x0061:
        if (r9 == 0) goto L_0x006b;
    L_0x0063:
        r9.close();	 Catch:{ Exception -> 0x0067 }
        goto L_0x006b;
    L_0x0067:
        r9 = move-exception;
        r9.printStackTrace();
    L_0x006b:
        return r0;
    L_0x006c:
        r10 = move-exception;
    L_0x006d:
        if (r1 == 0) goto L_0x0077;
    L_0x006f:
        r1.close();	 Catch:{ Exception -> 0x0073 }
        goto L_0x0077;
    L_0x0073:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0077:
        if (r9 == 0) goto L_0x0081;
    L_0x0079:
        r9.close();	 Catch:{ Exception -> 0x007d }
        goto L_0x0081;
    L_0x007d:
        r9 = move-exception;
        r9.printStackTrace();
    L_0x0081:
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.i.b(java.io.File, java.io.File):java.io.File");
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && (str.contains("_s1") || str.contains("_s3"));
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            return ((Activity) context).isFinishing() ^ 1;
        }
        return true;
    }

    public static boolean a(Fragment fragment) {
        return (fragment == null || fragment.getActivity() == null || fragment.getActivity().isFinishing()) ? false : true;
    }

    public static void a(final Context context, final ImageView imageView, final File file, @DrawableRes final int i, final String str) {
        if (a(context)) {
            k.just(Boolean.valueOf(true)).map(new wm<Boolean, File>() {
                /* renamed from: a */
                public File apply(Boolean bool) {
                    return i.a(file, str);
                }
            }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<File>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(io.reactivex.disposables.b bVar) {
                }

                /* renamed from: a */
                public void onNext(File file) {
                    if (file == null) {
                        imageView.setImageResource(i);
                        return;
                    }
                    i.b(str, file);
                    i.e(context, imageView, file.getAbsolutePath(), i);
                }
            });
        }
    }

    public static void a(final Fragment fragment, final ImageView imageView, final File file, @DrawableRes final int i, final String str) {
        if (a(fragment)) {
            k.just(Boolean.valueOf(true)).map(new wm<Boolean, File>() {
                /* renamed from: a */
                public File apply(Boolean bool) throws Exception {
                    return i.a(file, str);
                }
            }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<File>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(io.reactivex.disposables.b bVar) {
                }

                /* renamed from: a */
                public void onNext(File file) {
                    if (file == null) {
                        imageView.setImageResource(i);
                        return;
                    }
                    i.b(str, file);
                    i.b(fragment, imageView, file.getAbsolutePath(), i);
                }
            });
        }
    }

    public static void b(final Context context, final ImageView imageView, final File file, @DrawableRes final int i, final String str) {
        if (a(context)) {
            k.just(Boolean.valueOf(true)).map(new wm<Boolean, File>() {
                /* renamed from: a */
                public File apply(Boolean bool) {
                    return i.a(file, str);
                }
            }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<File>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(io.reactivex.disposables.b bVar) {
                }

                /* renamed from: a */
                public void onNext(File file) {
                    if (file == null) {
                        imageView.setImageResource(i);
                        return;
                    }
                    i.b(str, file);
                    i.f(context, imageView, file.getAbsolutePath(), i);
                }
            });
        }
    }

    public static void a(Context context, ImageView imageView, File file, @DrawableRes int i, int i2, int i3, String str) {
        if (a(context)) {
            final ImageView imageView2 = imageView;
            final int i4 = i;
            final String str2 = str;
            final Context context2 = context;
            final int i5 = i2;
            final int i6 = i3;
            k.just(Boolean.valueOf(true)).map(new -$$Lambda$i$4C1gFA5It-uDzBun0P6Tu4bWnSY(file, str)).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<File>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(io.reactivex.disposables.b bVar) {
                }

                /* renamed from: a */
                public void onNext(File file) {
                    if (file == null) {
                        imageView2.setImageResource(i4);
                        return;
                    }
                    i.b(str2, file);
                    i.a(context2, imageView2, file.getAbsolutePath(), i5, i6, R.drawable.fq_ic_placeholder_avatar_white);
                }
            });
            return;
        }
        imageView.setImageResource(i);
    }

    public static void a(Fragment fragment, ImageView imageView, final File file, int i, @DrawableRes int i2, final String str) {
        if (a(fragment)) {
            final ImageView imageView2 = imageView;
            final int i3 = i2;
            final String str2 = str;
            final Fragment fragment2 = fragment;
            final int i4 = i;
            k.just(Boolean.valueOf(true)).map(new wm<Boolean, File>() {
                /* renamed from: a */
                public File apply(Boolean bool) {
                    return i.a(file, str);
                }
            }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<File>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(io.reactivex.disposables.b bVar) {
                }

                /* renamed from: a */
                public void onNext(File file) {
                    if (file == null) {
                        imageView2.setImageResource(i3);
                        return;
                    }
                    i.b(str2, file);
                    i.c(fragment2, imageView2, file.getAbsolutePath(), i4, i3);
                }
            });
            return;
        }
        imageView.setImageResource(i2);
    }

    public static void a(Context context, ImageView imageView, final File file, int i, @DrawableRes int i2, final String str) {
        if (a(context)) {
            final ImageView imageView2 = imageView;
            final int i3 = i2;
            final String str2 = str;
            final Context context2 = context;
            final int i4 = i;
            k.just(Boolean.valueOf(true)).map(new wm<Boolean, File>() {
                /* renamed from: a */
                public File apply(Boolean bool) {
                    return i.a(file, str);
                }
            }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<File>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(io.reactivex.disposables.b bVar) {
                }

                /* renamed from: a */
                public void onNext(File file) {
                    if (file == null) {
                        imageView2.setImageResource(i3);
                        return;
                    }
                    i.b(str2, file);
                    i.e(context2, imageView2, file.getAbsolutePath(), i4, i3);
                }
            });
            return;
        }
        imageView.setImageResource(i2);
    }

    public static void c(final Context context, final ImageView imageView, final File file, @DrawableRes final int i, final String str) {
        if (a(context)) {
            k.just(Boolean.valueOf(true)).map(new wm<Boolean, File>() {
                /* renamed from: a */
                public File apply(Boolean bool) {
                    return i.a(file, str);
                }
            }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<File>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(io.reactivex.disposables.b bVar) {
                }

                /* renamed from: a */
                public void onNext(File file) {
                    if (file == null) {
                        imageView.setImageResource(i);
                        return;
                    }
                    GlideApp.with(context).asBitmap().load(file).skipMemoryCache(false).dontAnimate().transforms(new CenterCrop(), new BlurTransformation(25, 6)).placeholder(i).into(imageView);
                }
            });
        } else {
            imageView.setImageResource(i);
        }
    }

    public static File a(File file, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(com.blankj.utilcode.util.i.a());
        stringBuilder.append(File.separator);
        stringBuilder.append("imgCache");
        String stringBuilder2 = stringBuilder.toString();
        e.b(stringBuilder2);
        File file2 = new File(stringBuilder2, b(str));
        return !file2.exists() ? a(str, file, file2) : file2;
    }

    public static String b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        stringBuilder.append("");
        String stringBuilder2 = stringBuilder.toString();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder2 = n.a(str);
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(".webp");
        return stringBuilder3.toString();
    }

    public static String c(String str) {
        if (j.a(str)) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(b.b());
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    private static boolean d(String str) {
        boolean z = false;
        if (a == null) {
            return false;
        }
        String str2 = (String) a.get(f(str));
        if (!TextUtils.isEmpty(str2) && str2.contains(b(str))) {
            z = true;
        }
        return z;
    }

    private static void b(String str, File file) {
        a.put(f(str), file.getAbsolutePath());
    }

    private static String e(String str) {
        return (String) a.get(f(str));
    }

    private static String f(String str) {
        return n.a(str);
    }

    public static void e(Context context, ImageView imageView, String str, @DrawableRes int i) {
        try {
            GlideApp.with(context).asBitmap().load(str).centerCrop().placeholder(i).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            imageView.setImageResource(i);
        }
    }

    private static void c(Fragment fragment, final ImageView imageView, String str, int i, @DrawableRes final int i2) {
        try {
            GlideApp.with(fragment).asBitmap().load(str).transforms(new CenterCrop(), new RoundedCornersTransformation(b.a((float) i), 0)).skipMemoryCache(false).dontAnimate().placeholder(i2).listener(new RequestListener<Bitmap>() {
                /* renamed from: a */
                public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
                    return false;
                }

                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
                    imageView.setImageResource(i2);
                    return true;
                }
            }).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            imageView.setImageResource(i2);
        }
    }

    private static void e(Context context, final ImageView imageView, String str, int i, @DrawableRes final int i2) {
        try {
            GlideApp.with(context).asBitmap().load(str).transforms(new CenterCrop(), new RoundedCornersTransformation(b.a((float) i), 0)).skipMemoryCache(false).dontAnimate().placeholder(i2).listener(new RequestListener<Bitmap>() {
                /* renamed from: a */
                public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
                    return false;
                }

                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
                    imageView.setImageResource(i2);
                    return true;
                }
            }).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
            imageView.setImageResource(i2);
        }
    }

    public static void b(Fragment fragment, ImageView imageView, String str, @DrawableRes int i) {
        if (!a(fragment)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(i);
            return;
        }
        GlideApp.with(fragment).asBitmap().load(str).skipMemoryCache(false).dontAnimate().transforms(new CenterCrop(), new CircleCrop()).placeholder(i).into(imageView);
    }

    public static void f(Context context, ImageView imageView, String str, @DrawableRes int i) {
        if (!a(context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(i);
            return;
        }
        GlideApp.with(context).asBitmap().load(str).skipMemoryCache(false).dontAnimate().transforms(new CenterCrop(), new CircleCrop()).placeholder(i).into(imageView);
    }

    public static void a(Context context, ImageView imageView, String str, int i, int i2, @DrawableRes int i3) {
        if (!a(context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(i3);
            return;
        }
        GlideApp.with(context).asBitmap().load(str).skipMemoryCache(false).dontAnimate().transforms(new CenterCrop(), new h((float) i, i2)).placeholder(i3).into(imageView);
    }

    public static c a(final Context context, final String str, String str2, String str3, String str4) {
        String str5 = TextUtils.equals(str4, "3") ? "#FFD171" : "#8AEEFF";
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(30.0f);
        textPaint.setFakeBoldText(true);
        textPaint.setColor(Color.parseColor(str5));
        TextPaint textPaint2 = new TextPaint();
        textPaint2.setTextSize(30.0f);
        textPaint2.setColor(-1);
        textPaint2.setFakeBoldText(true);
        final c cVar = new c();
        Bitmap a = f.a(ContextCompat.getDrawable(context, b.f(str3)));
        Bitmap a2 = f.a(ContextCompat.getDrawable(context, b.b(true, p.a(str3))));
        Bitmap a3 = f.a(ContextCompat.getDrawable(context, TextUtils.equals(str4, "3") ? R.drawable.fq_ic_live_msg_year_guard : R.drawable.fq_ic_live_msg_mouth_guard));
        cVar.a(u.a(str2, 5), textPaint, "img_1410");
        cVar.a(context.getString(R.string.fq_go_to_index), textPaint2, "img_1409");
        cVar.a(str3, textPaint2, "img_1088");
        cVar.a(a, "img_1076");
        if (TextUtils.equals(str4, "3")) {
            cVar.a(a2, "img_1607");
        } else {
            cVar.a(a2, "img_1474");
        }
        cVar.a(a3, "img_1077");
        str2 = "img_68";
        if (a(str)) {
            GlideApp.with(context).asFile().load(str).into(new SimpleTarget<File>() {
                /* renamed from: a */
                public void onResourceReady(@NonNull final File file, @Nullable Transition<? super File> transition) {
                    k.just(Boolean.valueOf(true)).map(new wm<Boolean, Bitmap>() {
                        /* renamed from: a */
                        public Bitmap apply(Boolean bool) {
                            File a = i.a(file, str);
                            if (a == null) {
                                return null;
                            }
                            return i.b(f.a(a));
                        }
                    }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<Bitmap>() {
                        public void onComplete() {
                        }

                        public void onError(Throwable th) {
                        }

                        public void onSubscribe(io.reactivex.disposables.b bVar) {
                        }

                        /* renamed from: a */
                        public void onNext(Bitmap bitmap) {
                            if (bitmap == null) {
                                cVar.a(f.a(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), str2);
                            } else {
                                cVar.a(bitmap, str2);
                            }
                        }
                    });
                }
            });
            return cVar;
        }
        GlideApp.with(context).asBitmap().load(str).into(new SimpleTarget<Bitmap>() {
            /* renamed from: a */
            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                bitmap = i.b(bitmap);
                if (bitmap == null) {
                    cVar.a(f.a(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), str2);
                } else {
                    cVar.a(bitmap, str2);
                }
            }

            public void onLoadFailed(@Nullable Drawable drawable) {
                super.onLoadFailed(drawable);
                cVar.a(f.a(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), str2);
            }
        });
        return cVar;
    }

    public static c b(final Context context, final String str, String str2, String str3, String str4) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(30.0f);
        textPaint.setFakeBoldText(true);
        textPaint.setColor(-1);
        TextPaint textPaint2 = new TextPaint();
        textPaint2.setTextSize(30.0f);
        textPaint2.setColor(-1);
        textPaint2.setFakeBoldText(true);
        final c cVar = new c();
        Bitmap a = f.a(ContextCompat.getDrawable(context, b.f(str3)));
        Bitmap a2 = f.a(ContextCompat.getDrawable(context, b.b(true, p.a(str3))));
        str2 = u.a(str2, 7);
        cVar.a(context.getString(R.string.fq_car_anim_enter_tips, new Object[]{str2, str4}), textPaint, "img_19");
        cVar.a(str3, textPaint2, "img_146");
        cVar.a(a, "img_144");
        cVar.a(a2, "img_159");
        str2 = "img_21";
        if (a(str)) {
            GlideApp.with(context).asFile().load(str).into(new SimpleTarget<File>() {
                /* renamed from: a */
                public void onResourceReady(@NonNull final File file, @Nullable Transition<? super File> transition) {
                    k.just(Boolean.valueOf(true)).map(new wm<Boolean, Bitmap>() {
                        /* renamed from: a */
                        public Bitmap apply(Boolean bool) {
                            File a = i.a(file, str);
                            if (a == null) {
                                return null;
                            }
                            return i.b(f.a(a));
                        }
                    }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<Bitmap>() {
                        public void onComplete() {
                        }

                        public void onError(Throwable th) {
                        }

                        public void onSubscribe(io.reactivex.disposables.b bVar) {
                        }

                        /* renamed from: a */
                        public void onNext(Bitmap bitmap) {
                            if (bitmap == null) {
                                cVar.a(f.a(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), str2);
                            } else {
                                cVar.a(bitmap, str2);
                            }
                        }
                    });
                }
            });
            return cVar;
        }
        GlideApp.with(context).asBitmap().load(str).into(new SimpleTarget<Bitmap>() {
            /* renamed from: a */
            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                bitmap = i.b(bitmap);
                if (bitmap == null) {
                    cVar.a(f.a(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), str2);
                } else {
                    cVar.a(bitmap, str2);
                }
            }

            public void onLoadFailed(@Nullable Drawable drawable) {
                super.onLoadFailed(drawable);
                cVar.a(f.a(ContextCompat.getDrawable(context, R.drawable.fq_ic_placeholder_avatar)), str2);
            }
        });
        return cVar;
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = ((float) i) / ((float) width);
        float f2 = ((float) i2) / ((float) height);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Rect rect = new Rect(0, 0, min, min);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        float f = (float) i;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    private static Bitmap b(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return a(a(bitmap, 426, 426), 360);
    }
}

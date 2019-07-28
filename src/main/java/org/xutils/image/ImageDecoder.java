package org.xutils.image;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.os.Build.VERSION;
import com.tencent.rtmp.TXLiveConstants;
import com.yalantis.ucrop.view.CropImageView;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.LogUtil;

public final class ImageDecoder {
    private static final int a;
    private static final AtomicInteger b = new AtomicInteger(0);
    private static final Object c = new Object();
    private static final Object d = new Object();
    private static final byte[] e = new byte[]{(byte) 71, (byte) 73, (byte) 70};
    private static final Executor f = new PriorityExecutor(1, true);
    private static final LruDiskCache g = LruDiskCache.getDiskCache("xUtils_img_thumb");
    private static final boolean h;

    static {
        boolean z = false;
        int i = 1;
        if (VERSION.SDK_INT > 16) {
            z = true;
        }
        h = z;
        if (Runtime.getRuntime().availableProcessors() > 4) {
            i = 2;
        }
        a = i;
    }

    private ImageDecoder() {
    }

    static void a() {
        g.clearCacheFiles();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x0066 */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004e A:{LOOP_START, SYNTHETIC, LOOP:1: B:29:0x004e->B:91:0x004e} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x004e */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:38|39|40) */
    /* JADX WARNING: Missing block: B:40:0x006d, code skipped:
            throw new org.xutils.common.Callback.CancelledException("cancelled during decode image");
     */
    static android.graphics.drawable.Drawable a(final java.io.File r6, final org.xutils.image.ImageOptions r7, org.xutils.common.Callback.Cancelable r8) throws java.io.IOException {
        /*
        r0 = 0;
        if (r6 == 0) goto L_0x00e0;
    L_0x0003:
        r1 = r6.exists();
        if (r1 == 0) goto L_0x00e0;
    L_0x0009:
        r1 = r6.length();
        r3 = 1;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 >= 0) goto L_0x0015;
    L_0x0013:
        goto L_0x00e0;
    L_0x0015:
        if (r8 == 0) goto L_0x0026;
    L_0x0017:
        r1 = r8.isCancelled();
        if (r1 != 0) goto L_0x001e;
    L_0x001d:
        goto L_0x0026;
    L_0x001e:
        r6 = new org.xutils.common.Callback$CancelledException;
        r7 = "cancelled during decode image";
        r6.<init>(r7);
        throw r6;
    L_0x0026:
        r1 = r7.isIgnoreGif();
        if (r1 != 0) goto L_0x004b;
    L_0x002c:
        r1 = isGif(r6);
        if (r1 == 0) goto L_0x004b;
    L_0x0032:
        r1 = d;
        monitor-enter(r1);
        r7 = decodeGif(r6, r7, r8);	 Catch:{ all -> 0x0048 }
        monitor-exit(r1);	 Catch:{ all -> 0x0048 }
        if (r7 == 0) goto L_0x00c6;
    L_0x003c:
        r0 = new org.xutils.image.GifDrawable;
        r1 = r6.length();
        r6 = (int) r1;
        r0.<init>(r7, r6);
        goto L_0x00c6;
    L_0x0048:
        r6 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0048 }
        throw r6;
    L_0x004b:
        r1 = c;	 Catch:{ all -> 0x00cd }
        monitor-enter(r1);	 Catch:{ all -> 0x00cd }
    L_0x004e:
        r2 = b;	 Catch:{ all -> 0x00ca }
        r2 = r2.get();	 Catch:{ all -> 0x00ca }
        r3 = a;	 Catch:{ all -> 0x00ca }
        if (r2 < r3) goto L_0x006e;
    L_0x0058:
        if (r8 == 0) goto L_0x0060;
    L_0x005a:
        r2 = r8.isCancelled();	 Catch:{ all -> 0x00ca }
        if (r2 != 0) goto L_0x006e;
    L_0x0060:
        r2 = c;	 Catch:{ InterruptedException -> 0x0066, Throwable -> 0x004e }
        r2.wait();	 Catch:{ InterruptedException -> 0x0066, Throwable -> 0x004e }
        goto L_0x004e;
    L_0x0066:
        r6 = new org.xutils.common.Callback$CancelledException;	 Catch:{ all -> 0x00ca }
        r7 = "cancelled during decode image";
        r6.<init>(r7);	 Catch:{ all -> 0x00ca }
        throw r6;	 Catch:{ all -> 0x00ca }
    L_0x006e:
        monitor-exit(r1);	 Catch:{ all -> 0x00ca }
        if (r8 == 0) goto L_0x0080;
    L_0x0071:
        r1 = r8.isCancelled();	 Catch:{ all -> 0x00cd }
        if (r1 != 0) goto L_0x0078;
    L_0x0077:
        goto L_0x0080;
    L_0x0078:
        r6 = new org.xutils.common.Callback$CancelledException;	 Catch:{ all -> 0x00cd }
        r7 = "cancelled during decode image";
        r6.<init>(r7);	 Catch:{ all -> 0x00cd }
        throw r6;	 Catch:{ all -> 0x00cd }
    L_0x0080:
        r1 = b;	 Catch:{ all -> 0x00cd }
        r1.incrementAndGet();	 Catch:{ all -> 0x00cd }
        r1 = r7.isCompress();	 Catch:{ all -> 0x00cd }
        if (r1 == 0) goto L_0x0090;
    L_0x008b:
        r1 = a(r6, r7);	 Catch:{ all -> 0x00cd }
        goto L_0x0091;
    L_0x0090:
        r1 = r0;
    L_0x0091:
        if (r1 != 0) goto L_0x00a9;
    L_0x0093:
        r1 = decodeBitmap(r6, r7, r8);	 Catch:{ all -> 0x00cd }
        if (r1 == 0) goto L_0x00a9;
    L_0x0099:
        r8 = r7.isCompress();	 Catch:{ all -> 0x00cd }
        if (r8 == 0) goto L_0x00a9;
    L_0x009f:
        r8 = f;	 Catch:{ all -> 0x00cd }
        r2 = new org.xutils.image.ImageDecoder$1;	 Catch:{ all -> 0x00cd }
        r2.<init>(r6, r7, r1);	 Catch:{ all -> 0x00cd }
        r8.execute(r2);	 Catch:{ all -> 0x00cd }
    L_0x00a9:
        r6 = b;
        r6.decrementAndGet();
        r6 = c;
        monitor-enter(r6);
        r7 = c;	 Catch:{ all -> 0x00c7 }
        r7.notifyAll();	 Catch:{ all -> 0x00c7 }
        monitor-exit(r6);	 Catch:{ all -> 0x00c7 }
        if (r1 == 0) goto L_0x00c6;
    L_0x00b9:
        r0 = new org.xutils.image.c;
        r6 = org.xutils.x.app();
        r6 = r6.getResources();
        r0.<init>(r6, r1);
    L_0x00c6:
        return r0;
    L_0x00c7:
        r7 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00c7 }
        throw r7;
    L_0x00ca:
        r6 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00ca }
        throw r6;	 Catch:{ all -> 0x00cd }
    L_0x00cd:
        r6 = move-exception;
        r7 = b;
        r7.decrementAndGet();
        r7 = c;
        monitor-enter(r7);
        r8 = c;	 Catch:{ all -> 0x00dd }
        r8.notifyAll();	 Catch:{ all -> 0x00dd }
        monitor-exit(r7);	 Catch:{ all -> 0x00dd }
        throw r6;
    L_0x00dd:
        r6 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x00dd }
        throw r6;
    L_0x00e0:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.image.ImageDecoder.a(java.io.File, org.xutils.image.ImageOptions, org.xutils.common.Callback$Cancelable):android.graphics.drawable.Drawable");
    }

    public static boolean isGif(File file) {
        Throwable th;
        Closeable closeable = null;
        try {
            Closeable fileInputStream = new FileInputStream(file);
            try {
                boolean equals = Arrays.equals(e, IOUtil.readBytes(fileInputStream, 0, 3));
                IOUtil.closeQuietly(fileInputStream);
                return equals;
            } catch (Throwable th2) {
                th = th2;
                closeable = fileInputStream;
                IOUtil.closeQuietly(closeable);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            LogUtil.e(th.getMessage(), th);
            IOUtil.closeQuietly(closeable);
            return false;
        }
    }

    public static Bitmap decodeBitmap(File file, ImageOptions imageOptions, Cancelable cancelable) throws IOException {
        if (file == null || !file.exists() || file.length() < 1) {
            return null;
        }
        Bitmap bitmap;
        if (imageOptions == null) {
            imageOptions = ImageOptions.DEFAULT;
        }
        if (imageOptions.getMaxWidth() <= 0 || imageOptions.getMaxHeight() <= 0) {
            imageOptions.a(null);
        }
        if (cancelable != null) {
            try {
                if (cancelable.isCancelled()) {
                    throw new CancelledException("cancelled during decode image");
                }
            } catch (IOException e) {
                throw e;
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
                bitmap = null;
            }
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        options.inInputShareable = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        int i = 0;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = imageOptions.getConfig();
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        int width = imageOptions.getWidth();
        int height = imageOptions.getHeight();
        if (imageOptions.isAutoRotate()) {
            i = getRotateAngle(file.getAbsolutePath());
            if ((i / 90) % 2 == 1) {
                i2 = options.outHeight;
                i3 = options.outWidth;
            }
        }
        if (!imageOptions.isCrop() && width > 0 && height > 0) {
            if ((i / 90) % 2 == 1) {
                options.outWidth = height;
                options.outHeight = width;
            } else {
                options.outWidth = width;
                options.outHeight = height;
            }
        }
        options.inSampleSize = calculateSampleSize(i2, i3, imageOptions.getMaxWidth(), imageOptions.getMaxHeight());
        if (cancelable != null) {
            if (cancelable.isCancelled()) {
                throw new CancelledException("cancelled during decode image");
            }
        }
        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        if (bitmap != null) {
            if (cancelable != null) {
                if (cancelable.isCancelled()) {
                    throw new CancelledException("cancelled during decode image");
                }
            }
            if (i != 0) {
                bitmap = rotate(bitmap, i, true);
            }
            if (cancelable != null) {
                if (cancelable.isCancelled()) {
                    throw new CancelledException("cancelled during decode image");
                }
            }
            if (imageOptions.isCrop() && width > 0 && height > 0) {
                bitmap = cut2ScaleSize(bitmap, width, height, true);
            }
            if (bitmap != null) {
                if (cancelable != null) {
                    if (cancelable.isCancelled()) {
                        throw new CancelledException("cancelled during decode image");
                    }
                }
                if (imageOptions.isCircular()) {
                    bitmap = cut2Circular(bitmap, true);
                } else if (imageOptions.getRadius() > 0) {
                    bitmap = cut2RoundCorner(bitmap, imageOptions.getRadius(), imageOptions.isSquare(), true);
                } else if (imageOptions.isSquare()) {
                    bitmap = cut2Square(bitmap, true);
                }
                if (bitmap != null) {
                    return bitmap;
                }
                throw new IOException("decode image error");
            }
            throw new IOException("decode image error");
        }
        throw new IOException("decode image error");
    }

    public static Movie decodeGif(File file, ImageOptions imageOptions, Cancelable cancelable) throws IOException {
        IOException e;
        Throwable th;
        Closeable closeable;
        Closeable closeable2 = null;
        if (file == null || !file.exists() || file.length() < 1) {
            return null;
        }
        if (cancelable != null) {
            try {
                if (cancelable.isCancelled()) {
                    throw new CancelledException("cancelled during decode image");
                }
            } catch (IOException e2) {
                e = e2;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                closeable = null;
                try {
                    LogUtil.e(th.getMessage(), th);
                    IOUtil.closeQuietly(closeable);
                    return null;
                } catch (Throwable th4) {
                    th = th4;
                    closeable2 = closeable;
                    IOUtil.closeQuietly(closeable2);
                    throw th;
                }
            }
        }
        closeable = new BufferedInputStream(new FileInputStream(file), 16384);
        try {
            closeable.mark(16384);
            Movie decodeStream = Movie.decodeStream(closeable);
            if (decodeStream != null) {
                IOUtil.closeQuietly(closeable);
                return decodeStream;
            }
            throw new IOException("decode image error");
        } catch (IOException e3) {
            e = e3;
            closeable2 = closeable;
            throw e;
        } catch (Throwable th5) {
            th = th5;
            LogUtil.e(th.getMessage(), th);
            IOUtil.closeQuietly(closeable);
            return null;
        }
    }

    public static int calculateSampleSize(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i > i3 || i2 > i4) {
            int round;
            if (i > i2) {
                round = Math.round(((float) i2) / ((float) i4));
            } else {
                round = Math.round(((float) i) / ((float) i3));
            }
            if (round >= 1) {
                i5 = round;
            }
            float f = (float) (i * i2);
            while (f / ((float) (i5 * i5)) > ((float) ((i3 * i4) * 2))) {
                i5++;
            }
        }
        return i5;
    }

    public static Bitmap cut2Square(Bitmap bitmap, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == height) {
            return bitmap;
        }
        int min = Math.min(width, height);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, (width - min) / 2, (height - min) / 2, min, min);
        if (createBitmap != null) {
            if (z && createBitmap != bitmap) {
                bitmap.recycle();
            }
            bitmap = createBitmap;
        }
        return bitmap;
    }

    public static Bitmap cut2Circular(Bitmap bitmap, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = Math.min(width, height);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Config.ARGB_8888);
        if (createBitmap == null) {
            return bitmap;
        }
        Canvas canvas = new Canvas(createBitmap);
        float f = (float) (min / 2);
        canvas.drawCircle(f, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (float) ((min - width) / 2), (float) ((min - height) / 2), paint);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap cut2RoundCorner(Bitmap bitmap, int i, boolean z, boolean z2) {
        if (i <= 0) {
            return bitmap;
        }
        int min;
        int i2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (z) {
            min = Math.min(width, height);
            i2 = min;
        } else {
            min = width;
            i2 = height;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap createBitmap = Bitmap.createBitmap(min, i2, Config.ARGB_8888);
        if (createBitmap != null) {
            Canvas canvas = new Canvas(createBitmap);
            float f = (float) i;
            canvas.drawRoundRect(new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) min, (float) i2), f, f, paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, (float) ((min - width) / 2), (float) ((i2 - height) / 2), paint);
            if (z2) {
                bitmap.recycle();
            }
            bitmap = createBitmap;
        }
        return bitmap;
    }

    public static Bitmap cut2ScaleSize(Bitmap bitmap, int i, int i2, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == i && height == i2) {
            return bitmap;
        }
        int i3;
        Matrix matrix = new Matrix();
        float f = (float) i;
        float f2 = (float) width;
        float f3 = f / f2;
        float f4 = (float) i2;
        float f5 = (float) height;
        float f6 = f4 / f5;
        if (f3 > f6) {
            f4 /= f3;
            height = (int) ((f5 + f4) / 2.0f);
            i3 = (int) ((f5 - f4) / 2.0f);
            i2 = 0;
        } else {
            f /= f6;
            i2 = (int) ((f2 - f) / 2.0f);
            width = (int) ((f2 + f) / 2.0f);
            f3 = f6;
            i3 = 0;
        }
        matrix.setScale(f3, f3);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i2, i3, width - i2, height - i3, matrix, true);
        if (createBitmap != null) {
            if (z && createBitmap != bitmap) {
                bitmap.recycle();
            }
            bitmap = createBitmap;
        }
        return bitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027 A:{SKIP} */
    public static android.graphics.Bitmap rotate(android.graphics.Bitmap r7, int r8, boolean r9) {
        /*
        if (r8 == 0) goto L_0x0024;
    L_0x0002:
        r5 = new android.graphics.Matrix;
        r5.<init>();
        r8 = (float) r8;
        r5.setRotate(r8);
        r1 = 0;
        r2 = 0;
        r3 = r7.getWidth();	 Catch:{ Throwable -> 0x001c }
        r4 = r7.getHeight();	 Catch:{ Throwable -> 0x001c }
        r6 = 1;
        r0 = r7;
        r8 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r3, r4, r5, r6);	 Catch:{ Throwable -> 0x001c }
        goto L_0x0025;
    L_0x001c:
        r8 = move-exception;
        r0 = r8.getMessage();
        org.xutils.common.util.LogUtil.e(r0, r8);
    L_0x0024:
        r8 = 0;
    L_0x0025:
        if (r8 == 0) goto L_0x002f;
    L_0x0027:
        if (r9 == 0) goto L_0x002e;
    L_0x0029:
        if (r8 == r7) goto L_0x002e;
    L_0x002b:
        r7.recycle();
    L_0x002e:
        r7 = r8;
    L_0x002f:
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.image.ImageDecoder.rotate(android.graphics.Bitmap, int, boolean):android.graphics.Bitmap");
    }

    public static int getRotateAngle(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 0);
            if (attributeInt == 3) {
                return TXLiveConstants.RENDER_ROTATION_180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return 270;
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
            return 0;
        }
    }

    private static void b(File file, ImageOptions imageOptions, Bitmap bitmap) {
        Closeable fileOutputStream;
        Throwable th;
        DiskCacheEntity diskCacheEntity = new DiskCacheEntity();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(file.getAbsolutePath());
        stringBuilder.append("@");
        stringBuilder.append(file.lastModified());
        stringBuilder.append(imageOptions.toString());
        diskCacheEntity.setKey(stringBuilder.toString());
        Closeable createDiskCacheFile;
        try {
            Closeable commit;
            createDiskCacheFile = g.createDiskCacheFile(diskCacheEntity);
            if (createDiskCacheFile != null) {
                try {
                    fileOutputStream = new FileOutputStream(createDiskCacheFile);
                } catch (Throwable th2) {
                    fileOutputStream = null;
                    th = th2;
                    IOUtil.closeQuietly(createDiskCacheFile);
                    IOUtil.closeQuietly(fileOutputStream);
                    throw th;
                }
                try {
                    bitmap.compress(h ? CompressFormat.WEBP : CompressFormat.PNG, 80, fileOutputStream);
                    fileOutputStream.flush();
                    commit = createDiskCacheFile.commit();
                } catch (Throwable th3) {
                    th = th3;
                    IOUtil.deleteFileOrDir(createDiskCacheFile);
                    LogUtil.w(th.getMessage(), th);
                    IOUtil.closeQuietly(createDiskCacheFile);
                    IOUtil.closeQuietly(fileOutputStream);
                }
            }
            fileOutputStream = null;
            commit = createDiskCacheFile;
            IOUtil.closeQuietly(commit);
        } catch (Throwable th4) {
            fileOutputStream = null;
            th = th4;
            createDiskCacheFile = fileOutputStream;
            IOUtil.closeQuietly(createDiskCacheFile);
            IOUtil.closeQuietly(fileOutputStream);
            throw th;
        }
        IOUtil.closeQuietly(fileOutputStream);
    }

    private static Bitmap a(File file, ImageOptions imageOptions) {
        Closeable diskCacheFile;
        Throwable th;
        Closeable closeable = null;
        try {
            LruDiskCache lruDiskCache = g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append("@");
            stringBuilder.append(file.lastModified());
            stringBuilder.append(imageOptions.toString());
            diskCacheFile = lruDiskCache.getDiskCacheFile(stringBuilder.toString());
            if (diskCacheFile != null) {
                try {
                    if (diskCacheFile.exists()) {
                        Options options = new Options();
                        options.inJustDecodeBounds = false;
                        options.inPurgeable = true;
                        options.inInputShareable = true;
                        options.inPreferredConfig = Config.ARGB_8888;
                        Bitmap decodeFile = BitmapFactory.decodeFile(diskCacheFile.getAbsolutePath(), options);
                        IOUtil.closeQuietly(diskCacheFile);
                        return decodeFile;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        LogUtil.w(th.getMessage(), th);
                        IOUtil.closeQuietly(diskCacheFile);
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        closeable = diskCacheFile;
                        IOUtil.closeQuietly(closeable);
                        throw th;
                    }
                }
            }
        } catch (Throwable th4) {
            th = th4;
            IOUtil.closeQuietly(closeable);
            throw th;
        }
        IOUtil.closeQuietly(diskCacheFile);
        return null;
    }
}

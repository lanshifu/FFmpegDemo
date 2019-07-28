package com.yalantis.ucrop.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;
import com.tencent.rtmp.TXLiveConstants;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.task.BitmapLoadTask;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class BitmapLoadUtils {
    private static final String TAG = "BitmapLoadUtils";

    public static int exifToDegrees(int i) {
        switch (i) {
            case 3:
            case 4:
                return TXLiveConstants.RENDER_ROTATION_180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public static int exifToTranslation(int i) {
        if (!(i == 2 || i == 7)) {
            switch (i) {
                case 4:
                case 5:
                    break;
                default:
                    return 1;
            }
        }
        return -1;
    }

    public static void decodeBitmapInBackground(@NonNull Context context, @NonNull Uri uri, @Nullable Uri uri2, int i, int i2, BitmapLoadCallback bitmapLoadCallback) {
        new BitmapLoadTask(context, uri, uri2, i, i2, bitmapLoadCallback).execute(new Void[0]);
    }

    public static Bitmap transformBitmap(@NonNull Bitmap bitmap, @NonNull Matrix matrix) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (bitmap.sameAs(createBitmap)) {
                return bitmap;
            }
            return createBitmap;
        } catch (OutOfMemoryError e) {
            Log.e(TAG, "transformBitmap: ", e);
            return bitmap;
        }
    }

    public static int calculateInSampleSize(@NonNull Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            while (true) {
                if (i3 / i5 <= i2 && i4 / i5 <= i) {
                    break;
                }
                i5 *= 2;
            }
        }
        return i5;
    }

    public static int getExifOrientation(@NonNull Context context, @NonNull Uri uri) {
        Throwable e;
        int orientation;
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                return 0;
            }
            orientation = new ImageHeaderParser(openInputStream).getOrientation();
            try {
                close(openInputStream);
            } catch (IOException e2) {
                e = e2;
            }
            return orientation;
        } catch (IOException e3) {
            e = e3;
            orientation = 0;
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getExifOrientation: ");
            stringBuilder.append(uri.toString());
            Log.e(str, stringBuilder.toString(), e);
            return orientation;
        }
    }

    public static int calculateMaxBitmapSize(@NonNull Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getSize(point);
        }
        int sqrt = (int) Math.sqrt(Math.pow((double) point.x, 2.0d) + Math.pow((double) point.y, 2.0d));
        Canvas canvas = new Canvas();
        int min = Math.min(canvas.getMaximumBitmapWidth(), canvas.getMaximumBitmapHeight());
        if (min > 0) {
            sqrt = Math.min(sqrt, min);
        }
        min = EglUtils.getMaxTextureSize();
        if (min > 0) {
            sqrt = Math.min(sqrt, min);
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("maxBitmapSize: ");
        stringBuilder.append(sqrt);
        Log.d(str, stringBuilder.toString());
        return sqrt;
    }

    public static void close(@Nullable Closeable closeable) {
        if (closeable != null && (closeable instanceof Closeable)) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}

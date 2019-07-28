package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.rtmp.TXLiveConstants;
import java.io.Closeable;
import java.io.IOException;

/* compiled from: BitmapLoadUtils */
/* renamed from: ru */
public class ru {
    public static int a(int i) {
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

    public static int b(int i) {
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

    public static void a(@NonNull Context context, @NonNull Uri uri, @Nullable Uri uri2, int i, int i2, rp rpVar) {
        new rt(context, uri, uri2, i, i2, rpVar).execute(new Void[0]);
    }

    public static Bitmap a(@NonNull Bitmap bitmap, @NonNull Matrix matrix) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (bitmap.sameAs(createBitmap)) {
                return bitmap;
            }
            return createBitmap;
        } catch (OutOfMemoryError e) {
            Log.e("BitmapLoadUtils", "transformBitmap: ", e);
            return bitmap;
        }
    }

    public static int a(@NonNull Options options, int i, int i2) {
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

    public static int a(@NonNull Context context, @NonNull Uri uri) {
        Throwable e;
        int a;
        try {
            Closeable openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                return 0;
            }
            a = new rz(openInputStream).a();
            try {
                ru.a(openInputStream);
            } catch (IOException e2) {
                e = e2;
            }
            return a;
        } catch (IOException e3) {
            e = e3;
            a = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getExifOrientation: ");
            stringBuilder.append(uri.toString());
            Log.e("BitmapLoadUtils", stringBuilder.toString(), e);
            return a;
        }
    }

    public static int a(@NonNull Context context) {
        int i;
        int i2;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
            i = point.x;
            i2 = point.y;
        } else {
            int width = defaultDisplay.getWidth();
            i2 = defaultDisplay.getHeight();
            i = width;
        }
        i = (int) Math.sqrt(Math.pow((double) i, 2.0d) + Math.pow((double) i2, 2.0d));
        Canvas canvas = new Canvas();
        i2 = Math.min(canvas.getMaximumBitmapWidth(), canvas.getMaximumBitmapHeight());
        if (i2 > 0) {
            i = Math.min(i, i2);
        }
        i2 = rw.a();
        if (i2 > 0) {
            i = Math.min(i, i2);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("maxBitmapSize: ");
        stringBuilder.append(i);
        Log.d("BitmapLoadUtils", stringBuilder.toString());
        return i;
    }

    public static void a(@Nullable Closeable closeable) {
        if (closeable != null && (closeable instanceof Closeable)) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}

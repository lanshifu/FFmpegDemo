package com.tomatolive.library.ui.view.widget.matisse.internal.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.matisse.MimeType;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.IncapableCause;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.Item;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.SelectionSpec;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public final class PhotoMetadataUtils {
    private static final int MAX_WIDTH = 1600;
    private static final String SCHEME_CONTENT = "content";
    private static final String TAG = "PhotoMetadataUtils";

    private PhotoMetadataUtils() {
        throw new AssertionError("oops! the utility class is about to be instantiated...");
    }

    public static int getPixelsCount(ContentResolver contentResolver, Uri uri) {
        Point bitmapBound = getBitmapBound(contentResolver, uri);
        return bitmapBound.x * bitmapBound.y;
    }

    public static Point getBitmapSize(Uri uri, Activity activity) {
        ContentResolver contentResolver = activity.getContentResolver();
        Point bitmapBound = getBitmapBound(contentResolver, uri);
        int i = bitmapBound.x;
        int i2 = bitmapBound.y;
        if (shouldRotate(contentResolver, uri)) {
            i = bitmapBound.y;
            i2 = bitmapBound.x;
        }
        if (i2 == 0) {
            return new Point(MAX_WIDTH, MAX_WIDTH);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f = (float) i;
        float f2 = ((float) displayMetrics.widthPixels) / f;
        float f3 = (float) i2;
        float f4 = ((float) displayMetrics.heightPixels) / f3;
        if (f2 > f4) {
            return new Point((int) (f * f2), (int) (f3 * f4));
        }
        return new Point((int) (f * f2), (int) (f3 * f4));
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x002b */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0033 A:{SYNTHETIC, Splitter:B:19:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003e A:{SYNTHETIC, Splitter:B:25:0x003e} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:20:?, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:21:0x0037, code skipped:
            r4 = move-exception;
     */
    /* JADX WARNING: Missing block: B:22:0x0038, code skipped:
            r4.printStackTrace();
     */
    public static android.graphics.Point getBitmapBound(android.content.ContentResolver r3, android.net.Uri r4) {
        /*
        r0 = 0;
        r1 = new android.graphics.BitmapFactory$Options;	 Catch:{ FileNotFoundException -> 0x002b }
        r1.<init>();	 Catch:{ FileNotFoundException -> 0x002b }
        r2 = 1;
        r1.inJustDecodeBounds = r2;	 Catch:{ FileNotFoundException -> 0x002b }
        r3 = r3.openInputStream(r4);	 Catch:{ FileNotFoundException -> 0x002b }
        android.graphics.BitmapFactory.decodeStream(r3, r0, r1);	 Catch:{ FileNotFoundException -> 0x0027, all -> 0x0024 }
        r4 = r1.outWidth;	 Catch:{ FileNotFoundException -> 0x0027, all -> 0x0024 }
        r0 = r1.outHeight;	 Catch:{ FileNotFoundException -> 0x0027, all -> 0x0024 }
        r1 = new android.graphics.Point;	 Catch:{ FileNotFoundException -> 0x0027, all -> 0x0024 }
        r1.<init>(r4, r0);	 Catch:{ FileNotFoundException -> 0x0027, all -> 0x0024 }
        if (r3 == 0) goto L_0x0023;
    L_0x001b:
        r3.close();	 Catch:{ IOException -> 0x001f }
        goto L_0x0023;
    L_0x001f:
        r3 = move-exception;
        r3.printStackTrace();
    L_0x0023:
        return r1;
    L_0x0024:
        r4 = move-exception;
        r0 = r3;
        goto L_0x003c;
    L_0x0027:
        r0 = r3;
        goto L_0x002b;
    L_0x0029:
        r4 = move-exception;
        goto L_0x003c;
    L_0x002b:
        r3 = new android.graphics.Point;	 Catch:{ all -> 0x0029 }
        r4 = 0;
        r3.<init>(r4, r4);	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x003b;
    L_0x0033:
        r0.close();	 Catch:{ IOException -> 0x0037 }
        goto L_0x003b;
    L_0x0037:
        r4 = move-exception;
        r4.printStackTrace();
    L_0x003b:
        return r3;
    L_0x003c:
        if (r0 == 0) goto L_0x0046;
    L_0x003e:
        r0.close();	 Catch:{ IOException -> 0x0042 }
        goto L_0x0046;
    L_0x0042:
        r3 = move-exception;
        r3.printStackTrace();
    L_0x0046:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.widget.matisse.internal.utils.PhotoMetadataUtils.getBitmapBound(android.content.ContentResolver, android.net.Uri):android.graphics.Point");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046  */
    public static java.lang.String getPath(android.content.ContentResolver r8, android.net.Uri r9) {
        /*
        r0 = 0;
        if (r9 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = "content";
        r2 = r9.getScheme();
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x004a;
    L_0x0010:
        r1 = 1;
        r4 = new java.lang.String[r1];	 Catch:{ all -> 0x0042 }
        r1 = 0;
        r2 = "_data";
        r4[r1] = r2;	 Catch:{ all -> 0x0042 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r2 = r8;
        r3 = r9;
        r8 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0042 }
        if (r8 == 0) goto L_0x003c;
    L_0x0023:
        r9 = r8.moveToFirst();	 Catch:{ all -> 0x003a }
        if (r9 != 0) goto L_0x002a;
    L_0x0029:
        goto L_0x003c;
    L_0x002a:
        r9 = "_data";
        r9 = r8.getColumnIndex(r9);	 Catch:{ all -> 0x003a }
        r9 = r8.getString(r9);	 Catch:{ all -> 0x003a }
        if (r8 == 0) goto L_0x0039;
    L_0x0036:
        r8.close();
    L_0x0039:
        return r9;
    L_0x003a:
        r9 = move-exception;
        goto L_0x0044;
    L_0x003c:
        if (r8 == 0) goto L_0x0041;
    L_0x003e:
        r8.close();
    L_0x0041:
        return r0;
    L_0x0042:
        r9 = move-exception;
        r8 = r0;
    L_0x0044:
        if (r8 == 0) goto L_0x0049;
    L_0x0046:
        r8.close();
    L_0x0049:
        throw r9;
    L_0x004a:
        r8 = r9.getPath();
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.widget.matisse.internal.utils.PhotoMetadataUtils.getPath(android.content.ContentResolver, android.net.Uri):java.lang.String");
    }

    public static IncapableCause isAcceptable(Context context, Item item) {
        if (!isSelectableType(context, item)) {
            return new IncapableCause(context.getString(R.string.fq_matisse_error_file_type));
        }
        if (SelectionSpec.getInstance().filters != null) {
            for (Filter filter : SelectionSpec.getInstance().filters) {
                IncapableCause filter2 = filter.filter(context, item);
                if (filter2 != null) {
                    return filter2;
                }
            }
        }
        return null;
    }

    private static boolean isSelectableType(Context context, Item item) {
        if (context == null) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        for (MimeType checkType : SelectionSpec.getInstance().mimeTypeSet) {
            if (checkType.checkType(contentResolver, item.getContentUri())) {
                return true;
            }
        }
        return false;
    }

    private static boolean shouldRotate(ContentResolver contentResolver, Uri uri) {
        boolean z = false;
        try {
            int attributeInt = ExifInterfaceCompat.newInstance(getPath(contentResolver, uri)).getAttributeInt("Orientation", -1);
            if (attributeInt == 6 || attributeInt == 8) {
                z = true;
            }
            return z;
        } catch (IOException unused) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("could not read exif info of the image: ");
            stringBuilder.append(uri);
            Log.e(str, stringBuilder.toString());
            return false;
        }
    }

    public static float getSizeInMB(long j) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        decimalFormat.applyPattern("0.0");
        String format = decimalFormat.format((double) ((((float) j) / 1024.0f) / 1024.0f));
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getSizeInMB: ");
        stringBuilder.append(format);
        Log.e(str, stringBuilder.toString());
        return Float.valueOf(format.replaceAll(",", ".")).floatValue();
    }
}

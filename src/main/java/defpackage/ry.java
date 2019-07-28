package defpackage;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.text.TextUtils;
import java.io.File;

/* compiled from: FileUtils */
/* renamed from: ry */
public class ry {
    public static boolean a(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean b(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean c(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean d(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0058  */
    /* JADX WARNING: Missing block: B:12:0x002f, code skipped:
            if (r9 != null) goto L_0x0031;
     */
    /* JADX WARNING: Missing block: B:13:0x0031, code skipped:
            r9.close();
     */
    /* JADX WARNING: Missing block: B:20:0x0051, code skipped:
            if (r9 != null) goto L_0x0031;
     */
    /* JADX WARNING: Missing block: B:21:0x0054, code skipped:
            return null;
     */
    public static java.lang.String a(android.content.Context r9, android.net.Uri r10, java.lang.String r11, java.lang.String[] r12) {
        /*
        r0 = 1;
        r3 = new java.lang.String[r0];
        r1 = "_data";
        r7 = 0;
        r3[r7] = r1;
        r8 = 0;
        r1 = r9.getContentResolver();	 Catch:{ IllegalArgumentException -> 0x0038, all -> 0x0035 }
        r6 = 0;
        r2 = r10;
        r4 = r11;
        r5 = r12;
        r9 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ IllegalArgumentException -> 0x0038, all -> 0x0035 }
        if (r9 == 0) goto L_0x002f;
    L_0x0017:
        r10 = r9.moveToFirst();	 Catch:{ IllegalArgumentException -> 0x002d }
        if (r10 == 0) goto L_0x002f;
    L_0x001d:
        r10 = "_data";
        r10 = r9.getColumnIndexOrThrow(r10);	 Catch:{ IllegalArgumentException -> 0x002d }
        r10 = r9.getString(r10);	 Catch:{ IllegalArgumentException -> 0x002d }
        if (r9 == 0) goto L_0x002c;
    L_0x0029:
        r9.close();
    L_0x002c:
        return r10;
    L_0x002d:
        r10 = move-exception;
        goto L_0x003a;
    L_0x002f:
        if (r9 == 0) goto L_0x0054;
    L_0x0031:
        r9.close();
        goto L_0x0054;
    L_0x0035:
        r10 = move-exception;
        r9 = r8;
        goto L_0x0056;
    L_0x0038:
        r10 = move-exception;
        r9 = r8;
    L_0x003a:
        r11 = "FileUtils";
        r12 = java.util.Locale.getDefault();	 Catch:{ all -> 0x0055 }
        r1 = "getDataColumn: _data - [%s]";
        r0 = new java.lang.Object[r0];	 Catch:{ all -> 0x0055 }
        r10 = r10.getMessage();	 Catch:{ all -> 0x0055 }
        r0[r7] = r10;	 Catch:{ all -> 0x0055 }
        r10 = java.lang.String.format(r12, r1, r0);	 Catch:{ all -> 0x0055 }
        android.util.Log.i(r11, r10);	 Catch:{ all -> 0x0055 }
        if (r9 == 0) goto L_0x0054;
    L_0x0053:
        goto L_0x0031;
    L_0x0054:
        return r8;
    L_0x0055:
        r10 = move-exception;
    L_0x0056:
        if (r9 == 0) goto L_0x005b;
    L_0x0058:
        r9.close();
    L_0x005b:
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ry.a(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    @SuppressLint({"NewApi"})
    public static String a(Context context, Uri uri) {
        Uri uri2 = null;
        if ((VERSION.SDK_INT >= 19 ? 1 : null) == null || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (ry.d(uri)) {
                    return uri.getLastPathSegment();
                }
                return ry.a(context, uri, null, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (ry.a(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Environment.getExternalStorageDirectory());
                stringBuilder.append("/");
                stringBuilder.append(split[1]);
                return stringBuilder.toString();
            }
        } else if (ry.b(uri)) {
            return ry.a(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else if (ry.c(uri)) {
            Object obj = DocumentsContract.getDocumentId(uri).split(":")[0];
            if ("image".equals(obj)) {
                uri2 = Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(obj)) {
                uri2 = Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(obj)) {
                uri2 = Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return ry.a(context, uri2, "_id=?", new String[]{r6[1]});
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0051  */
    public static void a(@android.support.annotation.NonNull java.lang.String r10, @android.support.annotation.NonNull java.lang.String r11) throws java.io.IOException {
        /*
        r0 = r10.equalsIgnoreCase(r11);
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = 0;
        r1 = new java.io.FileInputStream;	 Catch:{ all -> 0x0048 }
        r2 = new java.io.File;	 Catch:{ all -> 0x0048 }
        r2.<init>(r10);	 Catch:{ all -> 0x0048 }
        r1.<init>(r2);	 Catch:{ all -> 0x0048 }
        r10 = r1.getChannel();	 Catch:{ all -> 0x0048 }
        r1 = new java.io.FileOutputStream;	 Catch:{ all -> 0x0042 }
        r2 = new java.io.File;	 Catch:{ all -> 0x0042 }
        r2.<init>(r11);	 Catch:{ all -> 0x0042 }
        r1.<init>(r2);	 Catch:{ all -> 0x0042 }
        r11 = r1.getChannel();	 Catch:{ all -> 0x0042 }
        r4 = 0;
        r6 = r10.size();	 Catch:{ all -> 0x003d }
        r3 = r10;
        r8 = r11;
        r3.transferTo(r4, r6, r8);	 Catch:{ all -> 0x003d }
        r10.close();	 Catch:{ all -> 0x003d }
        if (r10 == 0) goto L_0x0037;
    L_0x0034:
        r10.close();
    L_0x0037:
        if (r11 == 0) goto L_0x003c;
    L_0x0039:
        r11.close();
    L_0x003c:
        return;
    L_0x003d:
        r0 = move-exception;
        r9 = r0;
        r0 = r10;
        r10 = r9;
        goto L_0x004a;
    L_0x0042:
        r11 = move-exception;
        r9 = r0;
        r0 = r10;
        r10 = r11;
        r11 = r9;
        goto L_0x004a;
    L_0x0048:
        r10 = move-exception;
        r11 = r0;
    L_0x004a:
        if (r0 == 0) goto L_0x004f;
    L_0x004c:
        r0.close();
    L_0x004f:
        if (r11 == 0) goto L_0x0054;
    L_0x0051:
        r11.close();
    L_0x0054:
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ry.a(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e A:{RETURN} */
    public static boolean a(java.lang.String r4) {
        /*
        r4 = defpackage.ry.b(r4);
        r0 = r4.hashCode();
        r1 = -879299344; // 0xffffffffcb96f4f0 float:-1.9786208E7 double:NaN;
        r2 = 1;
        r3 = 0;
        if (r0 == r1) goto L_0x001f;
    L_0x000f:
        r1 = -879267568; // 0xffffffffcb977110 float:-1.984976E7 double:NaN;
        if (r0 == r1) goto L_0x0015;
    L_0x0014:
        goto L_0x0029;
    L_0x0015:
        r0 = "image/gif";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0029;
    L_0x001d:
        r4 = 0;
        goto L_0x002a;
    L_0x001f:
        r0 = "image/GIF";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0029;
    L_0x0027:
        r4 = 1;
        goto L_0x002a;
    L_0x0029:
        r4 = -1;
    L_0x002a:
        switch(r4) {
            case 0: goto L_0x002e;
            case 1: goto L_0x002e;
            default: goto L_0x002d;
        };
    L_0x002d:
        return r3;
    L_0x002e:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ry.a(java.lang.String):boolean");
    }

    public static String b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "image/jpeg";
            }
            str = new File(str).getName();
            str = str.substring(str.lastIndexOf(".") + 1, str.length());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("image/");
            stringBuilder.append(str);
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "image/jpeg";
        }
    }

    public static boolean c(String str) {
        return !TextUtils.isEmpty(str) && (str.startsWith("http") || str.startsWith("https"));
    }
}

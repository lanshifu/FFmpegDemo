package com.yalantis.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.util.FileUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapLoadTask extends AsyncTask<Void, Void, BitmapWorkerResult> {
    private static final String TAG = "BitmapWorkerTask";
    private final BitmapLoadCallback mBitmapLoadCallback;
    private final Context mContext;
    private Uri mInputUri;
    private Uri mOutputUri;
    private final int mRequiredHeight;
    private final int mRequiredWidth;

    public static class BitmapWorkerResult {
        Bitmap mBitmapResult;
        Exception mBitmapWorkerException;
        ExifInfo mExifInfo;

        public BitmapWorkerResult(@NonNull Bitmap bitmap, @NonNull ExifInfo exifInfo) {
            this.mBitmapResult = bitmap;
            this.mExifInfo = exifInfo;
        }

        public BitmapWorkerResult(@NonNull Exception exception) {
            this.mBitmapWorkerException = exception;
        }
    }

    public BitmapLoadTask(@NonNull Context context, @NonNull Uri uri, @Nullable Uri uri2, int i, int i2, BitmapLoadCallback bitmapLoadCallback) {
        this.mContext = context;
        this.mInputUri = uri;
        this.mOutputUri = uri2;
        this.mRequiredWidth = i;
        this.mRequiredHeight = i2;
        this.mBitmapLoadCallback = bitmapLoadCallback;
    }

    /* Access modifiers changed, original: protected|varargs */
    @NonNull
    public BitmapWorkerResult doInBackground(Void... voidArr) {
        if (this.mInputUri == null) {
            return new BitmapWorkerResult(new NullPointerException("Input Uri cannot be null"));
        }
        try {
            processInputUri();
            try {
                ParcelFileDescriptor openFileDescriptor = this.mContext.getContentResolver().openFileDescriptor(this.mInputUri, "r");
                StringBuilder stringBuilder;
                if (openFileDescriptor != null) {
                    FileDescriptor fileDescriptor = openFileDescriptor.getFileDescriptor();
                    Options options = new Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                    if (options.outWidth == -1 || options.outHeight == -1) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Bounds for bitmap could not be retrieved from the Uri: [");
                        stringBuilder.append(this.mInputUri);
                        stringBuilder.append("]");
                        return new BitmapWorkerResult(new IllegalArgumentException(stringBuilder.toString()));
                    }
                    options.inSampleSize = BitmapLoadUtils.calculateInSampleSize(options, this.mRequiredWidth, this.mRequiredHeight);
                    boolean z = false;
                    options.inJustDecodeBounds = false;
                    Bitmap bitmap = null;
                    while (!z) {
                        try {
                            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                            z = true;
                        } catch (OutOfMemoryError e) {
                            Log.e(TAG, "doInBackground: BitmapFactory.decodeFileDescriptor: ", e);
                            options.inSampleSize *= 2;
                        }
                    }
                    if (bitmap == null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Bitmap could not be decoded from the Uri: [");
                        stringBuilder.append(this.mInputUri);
                        stringBuilder.append("]");
                        return new BitmapWorkerResult(new IllegalArgumentException(stringBuilder.toString()));
                    }
                    if (VERSION.SDK_INT >= 16) {
                        BitmapLoadUtils.close(openFileDescriptor);
                    }
                    int exifOrientation = BitmapLoadUtils.getExifOrientation(this.mContext, this.mInputUri);
                    int exifToDegrees = BitmapLoadUtils.exifToDegrees(exifOrientation);
                    int exifToTranslation = BitmapLoadUtils.exifToTranslation(exifOrientation);
                    ExifInfo exifInfo = new ExifInfo(exifOrientation, exifToDegrees, exifToTranslation);
                    Matrix matrix = new Matrix();
                    if (exifToDegrees != 0) {
                        matrix.preRotate((float) exifToDegrees);
                    }
                    if (exifToTranslation != 1) {
                        matrix.postScale((float) exifToTranslation, 1.0f);
                    }
                    if (matrix.isIdentity()) {
                        return new BitmapWorkerResult(bitmap, exifInfo);
                    }
                    return new BitmapWorkerResult(BitmapLoadUtils.transformBitmap(bitmap, matrix), exifInfo);
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append("ParcelFileDescriptor was null for given Uri: [");
                stringBuilder.append(this.mInputUri);
                stringBuilder.append("]");
                return new BitmapWorkerResult(new NullPointerException(stringBuilder.toString()));
            } catch (FileNotFoundException e2) {
                return new BitmapWorkerResult(e2);
            }
        } catch (IOException | NullPointerException e3) {
            return new BitmapWorkerResult(e3);
        }
    }

    private void processInputUri() throws NullPointerException, IOException {
        String scheme = this.mInputUri.getScheme();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Uri scheme: ");
        stringBuilder.append(scheme);
        Log.d(str, stringBuilder.toString());
        if ("http".equals(scheme) || "https".equals(scheme)) {
            try {
                downloadFile(this.mInputUri, this.mOutputUri);
            } catch (IOException | NullPointerException e) {
                Log.e(TAG, "Downloading failed", e);
                throw e;
            }
        } else if ("content".equals(scheme)) {
            scheme = getFilePath();
            if (TextUtils.isEmpty(scheme) || !new File(scheme).exists()) {
                try {
                    copyFile(this.mInputUri, this.mOutputUri);
                    return;
                } catch (IOException | NullPointerException e2) {
                    Log.e(TAG, "Copying failed", e2);
                    throw e2;
                }
            }
            this.mInputUri = Uri.fromFile(new File(scheme));
        } else if (!"file".equals(scheme)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Invalid Uri scheme ");
            stringBuilder2.append(scheme);
            Log.e(TAG, stringBuilder2.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid Uri scheme");
            stringBuilder.append(scheme);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private String getFilePath() {
        return ContextCompat.checkSelfPermission(this.mContext, "android.permission.READ_EXTERNAL_STORAGE") == 0 ? FileUtils.getPath(this.mContext, this.mInputUri) : null;
    }

    private void copyFile(@NonNull Uri uri, @Nullable Uri uri2) throws NullPointerException, IOException {
        Throwable th;
        Log.d(TAG, "copyFile");
        if (uri2 != null) {
            Closeable closeable = null;
            Closeable openInputStream;
            try {
                openInputStream = this.mContext.getContentResolver().openInputStream(uri);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(uri2.getPath()));
                    if (openInputStream != null) {
                        try {
                            byte[] bArr = new byte[Filter.K];
                            while (true) {
                                int read = openInputStream.read(bArr);
                                if (read > 0) {
                                    fileOutputStream.write(bArr, 0, read);
                                } else {
                                    BitmapLoadUtils.close(fileOutputStream);
                                    BitmapLoadUtils.close(openInputStream);
                                    this.mInputUri = this.mOutputUri;
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            closeable = fileOutputStream;
                            BitmapLoadUtils.close(closeable);
                            BitmapLoadUtils.close(openInputStream);
                            this.mInputUri = this.mOutputUri;
                            throw th;
                        }
                    }
                    throw new NullPointerException("InputStream for given input Uri is null");
                } catch (Throwable th3) {
                    th = th3;
                    BitmapLoadUtils.close(closeable);
                    BitmapLoadUtils.close(openInputStream);
                    this.mInputUri = this.mOutputUri;
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                openInputStream = null;
                BitmapLoadUtils.close(closeable);
                BitmapLoadUtils.close(openInputStream);
                this.mInputUri = this.mOutputUri;
                throw th;
            }
        }
        throw new NullPointerException("Output Uri is null - cannot copy image");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0082  */
    private void downloadFile(@android.support.annotation.NonNull android.net.Uri r6, @android.support.annotation.Nullable android.net.Uri r7) throws java.lang.NullPointerException, java.io.IOException {
        /*
        r5 = this;
        r0 = "BitmapWorkerTask";
        r1 = "downloadFile";
        android.util.Log.d(r0, r1);
        if (r7 == 0) goto L_0x0095;
    L_0x0009:
        r0 = new okhttp3.OkHttpClient;
        r0.<init>();
        r1 = 0;
        r2 = new okhttp3.Request$Builder;	 Catch:{ all -> 0x0076 }
        r2.<init>();	 Catch:{ all -> 0x0076 }
        r6 = r6.toString();	 Catch:{ all -> 0x0076 }
        r6 = r2.url(r6);	 Catch:{ all -> 0x0076 }
        r6 = r6.build();	 Catch:{ all -> 0x0076 }
        r6 = r0.newCall(r6);	 Catch:{ all -> 0x0076 }
        r6 = r6.execute();	 Catch:{ all -> 0x0076 }
        r2 = r6.body();	 Catch:{ all -> 0x0072 }
        r2 = r2.source();	 Catch:{ all -> 0x0072 }
        r3 = r5.mContext;	 Catch:{ all -> 0x006c }
        r3 = r3.getContentResolver();	 Catch:{ all -> 0x006c }
        r7 = r3.openOutputStream(r7);	 Catch:{ all -> 0x006c }
        if (r7 == 0) goto L_0x0064;
    L_0x003c:
        r7 = okio.l.a(r7);	 Catch:{ all -> 0x006c }
        r2.a(r7);	 Catch:{ all -> 0x005e }
        com.yalantis.ucrop.util.BitmapLoadUtils.close(r2);
        com.yalantis.ucrop.util.BitmapLoadUtils.close(r7);
        if (r6 == 0) goto L_0x0052;
    L_0x004b:
        r6 = r6.body();
        com.yalantis.ucrop.util.BitmapLoadUtils.close(r6);
    L_0x0052:
        r6 = r0.dispatcher();
        r6.cancelAll();
        r6 = r5.mOutputUri;
        r5.mInputUri = r6;
        return;
    L_0x005e:
        r1 = move-exception;
        r4 = r2;
        r2 = r6;
        r6 = r7;
        r7 = r1;
        goto L_0x0070;
    L_0x0064:
        r7 = new java.lang.NullPointerException;	 Catch:{ all -> 0x006c }
        r3 = "OutputStream for given output Uri is null";
        r7.<init>(r3);	 Catch:{ all -> 0x006c }
        throw r7;	 Catch:{ all -> 0x006c }
    L_0x006c:
        r7 = move-exception;
        r4 = r2;
        r2 = r6;
        r6 = r1;
    L_0x0070:
        r1 = r4;
        goto L_0x007a;
    L_0x0072:
        r7 = move-exception;
        r2 = r6;
        r6 = r1;
        goto L_0x007a;
    L_0x0076:
        r6 = move-exception;
        r7 = r6;
        r6 = r1;
        r2 = r6;
    L_0x007a:
        com.yalantis.ucrop.util.BitmapLoadUtils.close(r1);
        com.yalantis.ucrop.util.BitmapLoadUtils.close(r6);
        if (r2 == 0) goto L_0x0089;
    L_0x0082:
        r6 = r2.body();
        com.yalantis.ucrop.util.BitmapLoadUtils.close(r6);
    L_0x0089:
        r6 = r0.dispatcher();
        r6.cancelAll();
        r6 = r5.mOutputUri;
        r5.mInputUri = r6;
        throw r7;
    L_0x0095:
        r6 = new java.lang.NullPointerException;
        r7 = "Output Uri is null - cannot download image";
        r6.<init>(r7);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yalantis.ucrop.task.BitmapLoadTask.downloadFile(android.net.Uri, android.net.Uri):void");
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(@NonNull BitmapWorkerResult bitmapWorkerResult) {
        if (bitmapWorkerResult.mBitmapWorkerException == null) {
            this.mBitmapLoadCallback.onBitmapLoaded(bitmapWorkerResult.mBitmapResult, bitmapWorkerResult.mExifInfo, this.mInputUri.getPath(), this.mOutputUri == null ? null : this.mOutputUri.getPath());
        } else {
            this.mBitmapLoadCallback.onFailure(bitmapWorkerResult.mBitmapWorkerException);
        }
    }
}

package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.tomato.ucrop.model.b;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/* compiled from: BitmapLoadTask */
/* renamed from: rt */
public class rt extends AsyncTask<Void, Void, a> {
    private final Context a;
    private Uri b;
    private Uri c;
    private final int d;
    private final int e;
    private final rp f;

    /* compiled from: BitmapLoadTask */
    /* renamed from: rt$a */
    public static class a {
        Bitmap a;
        b b;
        Exception c;

        public a(@NonNull Bitmap bitmap, @NonNull b bVar) {
            this.a = bitmap;
            this.b = bVar;
        }

        public a(@NonNull Exception exception) {
            this.c = exception;
        }
    }

    public rt(@NonNull Context context, @NonNull Uri uri, @Nullable Uri uri2, int i, int i2, rp rpVar) {
        this.a = context;
        this.b = uri;
        this.c = uri2;
        this.d = i;
        this.e = i2;
        this.f = rpVar;
    }

    /* Access modifiers changed, original: protected|varargs */
    @NonNull
    /* renamed from: a */
    public a doInBackground(Void... voidArr) {
        if (this.b == null) {
            return new a(new NullPointerException("Input Uri cannot be null"));
        }
        try {
            a();
            try {
                Closeable openFileDescriptor = this.a.getContentResolver().openFileDescriptor(this.b, "r");
                StringBuilder stringBuilder;
                if (openFileDescriptor != null) {
                    FileDescriptor fileDescriptor = openFileDescriptor.getFileDescriptor();
                    Options options = new Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                    if (options.outWidth == -1 || options.outHeight == -1) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Bounds for bitmap could not be retrieved from the Uri: [");
                        stringBuilder.append(this.b);
                        stringBuilder.append("]");
                        return new a(new IllegalArgumentException(stringBuilder.toString()));
                    }
                    options.inSampleSize = ru.a(options, this.d, this.e);
                    boolean z = false;
                    options.inJustDecodeBounds = false;
                    Bitmap bitmap = null;
                    while (!z) {
                        try {
                            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                            z = true;
                        } catch (OutOfMemoryError e) {
                            Log.e("BitmapWorkerTask", "doInBackground: BitmapFactory.decodeFileDescriptor: ", e);
                            options.inSampleSize *= 2;
                        }
                    }
                    if (bitmap == null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Bitmap could not be decoded from the Uri: [");
                        stringBuilder.append(this.b);
                        stringBuilder.append("]");
                        return new a(new IllegalArgumentException(stringBuilder.toString()));
                    }
                    if (VERSION.SDK_INT >= 16) {
                        ru.a(openFileDescriptor);
                    }
                    int a = ru.a(this.a, this.b);
                    int a2 = ru.a(a);
                    int b = ru.b(a);
                    b bVar = new b(a, a2, b);
                    Matrix matrix = new Matrix();
                    if (a2 != 0) {
                        matrix.preRotate((float) a2);
                    }
                    if (b != 1) {
                        matrix.postScale((float) b, 1.0f);
                    }
                    if (matrix.isIdentity()) {
                        return new a(bitmap, bVar);
                    }
                    return new a(ru.a(bitmap, matrix), bVar);
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append("ParcelFileDescriptor was null for given Uri: [");
                stringBuilder.append(this.b);
                stringBuilder.append("]");
                return new a(new NullPointerException(stringBuilder.toString()));
            } catch (FileNotFoundException e2) {
                return new a(e2);
            }
        } catch (IOException | NullPointerException e3) {
            return new a(e3);
        }
    }

    private void a() throws NullPointerException, IOException {
        String uri = this.b.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Uri scheme: ");
        stringBuilder.append(uri);
        Log.d("BitmapWorkerTask", stringBuilder.toString());
        if (uri.startsWith("http") || uri.startsWith("https")) {
            try {
                b(this.b, this.c);
                return;
            } catch (IOException | NullPointerException e) {
                Log.e("BitmapWorkerTask", "Downloading failed", e);
                throw e;
            }
        }
        uri = b();
        if (TextUtils.isEmpty(uri) || !new File(uri).exists()) {
            try {
                a(this.b, this.c);
                return;
            } catch (IOException | NullPointerException e2) {
                Log.e("BitmapWorkerTask", "Copying failed", e2);
                throw e2;
            }
        }
        this.b = Uri.fromFile(new File(uri));
    }

    private String b() {
        return ContextCompat.checkSelfPermission(this.a, "android.permission.READ_EXTERNAL_STORAGE") == 0 ? ry.a(this.a, this.b) : null;
    }

    private void a(@NonNull Uri uri, @Nullable Uri uri2) throws NullPointerException, IOException {
        Throwable th;
        Log.d("BitmapWorkerTask", "copyFile");
        if (uri2 != null) {
            Closeable closeable = null;
            Closeable openInputStream;
            try {
                openInputStream = this.a.getContentResolver().openInputStream(uri);
                try {
                    Closeable fileOutputStream = new FileOutputStream(new File(uri2.getPath()));
                    if (openInputStream != null) {
                        try {
                            byte[] bArr = new byte[Filter.K];
                            while (true) {
                                int read = openInputStream.read(bArr);
                                if (read > 0) {
                                    fileOutputStream.write(bArr, 0, read);
                                } else {
                                    ru.a(fileOutputStream);
                                    ru.a(openInputStream);
                                    this.b = this.c;
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            closeable = fileOutputStream;
                            ru.a(closeable);
                            ru.a(openInputStream);
                            this.b = this.c;
                            throw th;
                        }
                    }
                    throw new NullPointerException("InputStream for given input Uri is null");
                } catch (Throwable th3) {
                    th = th3;
                    ru.a(closeable);
                    ru.a(openInputStream);
                    this.b = this.c;
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                openInputStream = null;
                ru.a(closeable);
                ru.a(openInputStream);
                this.b = this.c;
                throw th;
            }
        }
        throw new NullPointerException("Output Uri is null - cannot copy image");
    }

    private void b(@NonNull Uri uri, @Nullable Uri uri2) throws NullPointerException, IOException {
        Log.d("BitmapWorkerTask", "downloadFile");
        if (uri2 != null) {
            try {
                URL url = new URL(uri.toString());
                byte[] bArr = new byte[Filter.K];
                BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
                OutputStream openOutputStream = this.a.getContentResolver().openOutputStream(uri2);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(openOutputStream);
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read <= -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                bufferedInputStream.close();
                openOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.b = this.c;
            return;
        }
        throw new NullPointerException("Output Uri is null - cannot download image");
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(@NonNull a aVar) {
        if (aVar.c == null) {
            this.f.a(aVar.a, aVar.b, this.b.getPath(), this.c == null ? null : this.c.getPath());
        } else {
            this.f.a(aVar.c);
        }
    }
}

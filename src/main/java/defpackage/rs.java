package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.tomato.ucrop.model.a;
import com.tomato.ucrop.model.b;
import com.tomato.ucrop.model.c;
import com.yalantis.ucrop.view.CropImageView;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* compiled from: BitmapCropTask */
/* renamed from: rs */
public class rs extends AsyncTask<Void, Void, Throwable> {
    private final WeakReference<Context> a;
    private Bitmap b;
    private final RectF c;
    private final RectF d;
    private float e;
    private float f;
    private final int g;
    private final int h;
    private final CompressFormat i;
    private final int j;
    private final String k;
    private final String l;
    private final b m;
    private final ro n;
    private int o;
    private int p;
    private int q;
    private int r;

    public rs(@NonNull Context context, @Nullable Bitmap bitmap, @NonNull c cVar, @NonNull a aVar, @Nullable ro roVar) {
        this.a = new WeakReference(context);
        this.b = bitmap;
        this.c = cVar.a();
        this.d = cVar.b();
        this.e = cVar.c();
        this.f = cVar.d();
        this.g = aVar.a();
        this.h = aVar.b();
        this.i = aVar.c();
        this.j = aVar.d();
        this.k = aVar.e();
        this.l = aVar.f();
        this.m = aVar.g();
        this.n = roVar;
    }

    /* Access modifiers changed, original: protected|varargs */
    @Nullable
    /* renamed from: a */
    public Throwable doInBackground(Void... voidArr) {
        if (this.b == null) {
            return new NullPointerException("ViewBitmap is null");
        }
        if (this.b.isRecycled()) {
            return new NullPointerException("ViewBitmap is recycled");
        }
        if (this.d.isEmpty()) {
            return new NullPointerException("CurrentImageRect is empty");
        }
        try {
            a();
            this.b = null;
            return null;
        } catch (Throwable th) {
            return th;
        }
    }

    private boolean a() throws IOException {
        if (this.g > 0 && this.h > 0) {
            float width = this.c.width() / this.e;
            float height = this.c.height() / this.e;
            if (width > ((float) this.g) || height > ((float) this.h)) {
                width = Math.min(((float) this.g) / width, ((float) this.h) / height);
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(this.b, Math.round(((float) this.b.getWidth()) * width), Math.round(((float) this.b.getHeight()) * width), false);
                if (this.b != createScaledBitmap) {
                    this.b.recycle();
                }
                this.b = createScaledBitmap;
                this.e /= width;
            }
        }
        if (this.f != CropImageView.DEFAULT_ASPECT_RATIO) {
            Matrix matrix = new Matrix();
            matrix.setRotate(this.f, (float) (this.b.getWidth() / 2), (float) (this.b.getHeight() / 2));
            Bitmap createBitmap = Bitmap.createBitmap(this.b, 0, 0, this.b.getWidth(), this.b.getHeight(), matrix, true);
            if (this.b != createBitmap) {
                this.b.recycle();
            }
            this.b = createBitmap;
        }
        this.q = Math.round((this.c.left - this.d.left) / this.e);
        this.r = Math.round((this.c.top - this.d.top) / this.e);
        this.o = Math.round(this.c.width() / this.e);
        this.p = Math.round(this.c.height() / this.e);
        boolean a = a(this.o, this.p);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Should crop: ");
        stringBuilder.append(a);
        Log.i("BitmapCropTask", stringBuilder.toString());
        if (a) {
            ExifInterface exifInterface = new ExifInterface(this.k);
            a(Bitmap.createBitmap(this.b, this.q, this.r, this.o, this.p));
            if (this.i.equals(CompressFormat.JPEG)) {
                rz.a(exifInterface, this.o, this.p, this.l);
            }
            return true;
        }
        ry.a(this.k, this.l);
        return false;
    }

    private void a(@NonNull Bitmap bitmap) throws FileNotFoundException {
        Throwable th;
        Context context = (Context) this.a.get();
        if (context != null) {
            Closeable closeable = null;
            try {
                Closeable openOutputStream = context.getContentResolver().openOutputStream(Uri.fromFile(new File(this.l)));
                try {
                    bitmap.compress(this.i, this.j, openOutputStream);
                    bitmap.recycle();
                    ru.a(openOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    closeable = openOutputStream;
                    ru.a(closeable);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                ru.a(closeable);
                throw th;
            }
        }
    }

    private boolean a(int i, int i2) {
        i = Math.round(((float) Math.max(i, i2)) / 1000.0f) + 1;
        if (this.g > 0 && this.h > 0) {
            return true;
        }
        float f = (float) i;
        if (Math.abs(this.c.left - this.d.left) > f || Math.abs(this.c.top - this.d.top) > f || Math.abs(this.c.bottom - this.d.bottom) > f || Math.abs(this.c.right - this.d.right) > f) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(@Nullable Throwable th) {
        if (this.n == null) {
            return;
        }
        if (th == null) {
            this.n.a(Uri.fromFile(new File(this.l)), this.q, this.r, this.o, this.p);
            return;
        }
        this.n.a(th);
    }
}

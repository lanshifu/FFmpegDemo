package top.zibin.luban;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: Engine */
class b {
    private c a;
    private File b;
    private int c;
    private int d;
    private boolean e;

    b(c cVar, File file, boolean z) throws IOException {
        this.b = file;
        this.a = cVar;
        this.e = z;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeStream(cVar.a(), null, options);
        this.c = options.outWidth;
        this.d = options.outHeight;
    }

    private int b() {
        this.c = this.c % 2 == 1 ? this.c + 1 : this.c;
        this.d = this.d % 2 == 1 ? this.d + 1 : this.d;
        int max = Math.max(this.c, this.d);
        float min = ((float) Math.min(this.c, this.d)) / ((float) max);
        if (min > 1.0f || ((double) min) <= 0.5625d) {
            double d = (double) min;
            if (d > 0.5625d || d <= 0.5d) {
                double d2 = (double) max;
                Double.isNaN(d);
                double d3 = 1280.0d / d;
                Double.isNaN(d2);
                return (int) Math.ceil(d2 / d3);
            }
            max /= 1280;
            if (max == 0) {
                max = 1;
            }
            return max;
        } else if (max < 1664) {
            return 1;
        } else {
            if (max < 4990) {
                return 2;
            }
            if (max > 4990 && max < 10240) {
                return 4;
            }
            max /= 1280;
            if (max == 0) {
                max = 1;
            }
            return max;
        }
    }

    private Bitmap a(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* Access modifiers changed, original: 0000 */
    public File a() throws IOException {
        Options options = new Options();
        options.inSampleSize = b();
        Bitmap decodeStream = BitmapFactory.decodeStream(this.a.a(), null, options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (Checker.SINGLE.isJPG(this.a.a())) {
            decodeStream = a(decodeStream, Checker.SINGLE.getOrientation(this.a.a()));
        }
        decodeStream.compress(this.e ? CompressFormat.PNG : CompressFormat.JPEG, 60, byteArrayOutputStream);
        decodeStream.recycle();
        FileOutputStream fileOutputStream = new FileOutputStream(this.b);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.flush();
        fileOutputStream.close();
        byteArrayOutputStream.close();
        return this.b;
    }
}

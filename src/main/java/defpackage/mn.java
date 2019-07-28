package defpackage;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.tencent.rtmp.TXLiveConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: Engine */
/* renamed from: mn */
class mn {
    private ExifInterface a;
    private String b;
    private File c;
    private int d;
    private int e;

    mn(String str, File file) throws IOException {
        if (mm.b(str)) {
            this.a = new ExifInterface(str);
        }
        this.c = file;
        this.b = str;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeFile(str, options);
        this.d = options.outWidth;
        this.e = options.outHeight;
    }

    private int b() {
        this.d = this.d % 2 == 1 ? this.d + 1 : this.d;
        this.e = this.e % 2 == 1 ? this.e + 1 : this.e;
        int max = Math.max(this.d, this.e);
        float min = ((float) Math.min(this.d, this.e)) / ((float) max);
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
            if (max >= 1664 && max < 4990) {
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

    private Bitmap a(Bitmap bitmap) {
        if (this.a == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        int i = 0;
        int attributeInt = this.a.getAttributeInt("Orientation", 1);
        if (attributeInt == 3) {
            i = TXLiveConstants.RENDER_ROTATION_180;
        } else if (attributeInt == 6) {
            i = 90;
        } else if (attributeInt == 8) {
            i = 270;
        }
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* Access modifiers changed, original: 0000 */
    public File a() throws IOException {
        Options options = new Options();
        options.inSampleSize = b();
        Bitmap decodeFile = BitmapFactory.decodeFile(this.b, options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeFile = a(decodeFile);
        decodeFile.compress(CompressFormat.JPEG, 90, byteArrayOutputStream);
        decodeFile.recycle();
        FileOutputStream fileOutputStream = new FileOutputStream(this.c);
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.flush();
        fileOutputStream.close();
        byteArrayOutputStream.close();
        return this.c;
    }
}

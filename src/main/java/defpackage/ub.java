package defpackage;

import android.graphics.RectF;
import com.yalantis.ucrop.view.CropImageView;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.ImageHolder.ScaleType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: DrawableSizeHolder */
/* renamed from: ub */
public class ub {
    RectF a;
    ScaleType b;
    ua c;
    private String d;

    private ub(String str, RectF rectF, ScaleType scaleType, ua uaVar) {
        this.a = rectF;
        this.b = scaleType;
        this.d = str;
        this.c = uaVar;
    }

    ub(ImageHolder imageHolder) {
        this(imageHolder.a(), new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) imageHolder.c(), (float) imageHolder.b()), imageHolder.e(), new ua(imageHolder.i()));
    }

    public void a(OutputStream outputStream) {
        try {
            ub.a(outputStream, this.a.left);
            ub.a(outputStream, this.a.top);
            ub.a(outputStream, this.a.right);
            ub.a(outputStream, this.a.bottom);
            ub.a(outputStream, this.b.intValue());
            ub.a(outputStream, this.c.a());
            ub.a(outputStream, this.c.c());
            ub.a(outputStream, this.c.b());
            ub.a(outputStream, this.c.d());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            ug.a(e);
        }
    }

    public static ub a(InputStream inputStream, String str) {
        try {
            float c = ub.c(inputStream);
            float c2 = ub.c(inputStream);
            float c3 = ub.c(inputStream);
            float c4 = ub.c(inputStream);
            int b = ub.b(inputStream);
            boolean a = ub.a(inputStream);
            int b2 = ub.b(inputStream);
            float c5 = ub.c(inputStream);
            float c6 = ub.c(inputStream);
            inputStream.close();
            return new ub(str, new RectF(c, c2, c3, c4), ScaleType.valueOf(b), new ua(a, c5, b2, c6));
        } catch (IOException e) {
            ug.a(e);
            return null;
        }
    }

    private static void a(OutputStream outputStream, boolean z) throws IOException {
        outputStream.write(z);
    }

    private static boolean a(InputStream inputStream) throws IOException {
        return inputStream.read() != 0;
    }

    private static int b(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4];
        inputStream.read(bArr);
        return ub.a(bArr);
    }

    private static void a(OutputStream outputStream, int i) throws IOException {
        outputStream.write(ub.a(i));
    }

    private static void a(OutputStream outputStream, float f) throws IOException {
        outputStream.write(ub.a(Float.floatToIntBits(f)));
    }

    private static float c(InputStream inputStream) throws IOException {
        return Float.intBitsToFloat(ub.b(inputStream));
    }

    private static byte[] a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
    }

    private static int a(byte[] bArr) {
        return (bArr[3] << 24) | (((bArr[0] & 255) | ((bArr[1] << 8) & 65280)) | ((bArr[2] << 24) >>> 8));
    }
}

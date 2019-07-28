package defpackage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.b;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Map;

/* compiled from: QRCodeWriter */
/* renamed from: lw */
public final class lw {
    public b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.length() == 0) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.QR_CODE) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Can only encode QR_CODE, but got ");
            stringBuilder.append(barcodeFormat);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i < 0 || i2 < 0) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Requested dimensions are too small: ");
            stringBuilder2.append(i);
            stringBuilder2.append('x');
            stringBuilder2.append(i2);
            throw new IllegalArgumentException(stringBuilder2.toString());
        } else {
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            if (map != null) {
                ErrorCorrectionLevel errorCorrectionLevel2 = (ErrorCorrectionLevel) map.get(EncodeHintType.ERROR_CORRECTION);
                if (errorCorrectionLevel2 != null) {
                    errorCorrectionLevel = errorCorrectionLevel2;
                }
            }
            mc mcVar = new mc();
            lz.a(str, errorCorrectionLevel, (Map) map, mcVar);
            return lw.a(mcVar, i, i2);
        }
    }

    private static b a(mc mcVar, int i, int i2) {
        ly g = mcVar.g();
        if (g != null) {
            int b = g.b();
            int a = g.a();
            int i3 = b + 8;
            int i4 = a + 8;
            i = Math.max(i, i3);
            i2 = Math.max(i2, i4);
            i3 = Math.min(i / i3, i2 / i4);
            i4 = (i - (b * i3)) / 2;
            int i5 = (i2 - (a * i3)) / 2;
            b bVar = new b(i, i2);
            i2 = 0;
            while (i2 < a) {
                int i6 = i4;
                int i7 = 0;
                while (i7 < b) {
                    if (g.a(i7, i2) == (byte) 1) {
                        bVar.a(i6, i5, i3, i3);
                    }
                    i7++;
                    i6 += i3;
                }
                i2++;
                i5 += i3;
            }
            return bVar;
        }
        throw new IllegalStateException();
    }
}

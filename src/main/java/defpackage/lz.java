package defpackage;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.a;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.a.b;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: Encoder */
/* renamed from: lz */
public final class lz {
    private static final int[] a = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    /* compiled from: Encoder */
    /* renamed from: lz$1 */
    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a = new int[Mode.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        static {
            /*
            r0 = com.google.zxing.qrcode.decoder.Mode.values();
            r0 = r0.length;
            r0 = new int[r0];
            a = r0;
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = com.google.zxing.qrcode.decoder.Mode.NUMERIC;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = com.google.zxing.qrcode.decoder.Mode.ALPHANUMERIC;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = com.google.zxing.qrcode.decoder.Mode.BYTE;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r2 = 3;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r1 = com.google.zxing.qrcode.decoder.Mode.KANJI;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0035 }
            r2 = 4;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0035 }
        L_0x0035:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.lz$1.<clinit>():void");
        }
    }

    private static int a(ly lyVar) {
        return (((ma.a(lyVar) + 0) + ma.b(lyVar)) + ma.c(lyVar)) + ma.d(lyVar);
    }

    public static void a(String str, ErrorCorrectionLevel errorCorrectionLevel, Map<EncodeHintType, ?> map, mc mcVar) throws WriterException {
        String str2 = map == null ? null : (String) map.get(EncodeHintType.CHARACTER_SET);
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        Mode a = lz.a(str, str2);
        a aVar = new a();
        lz.a(str, a, aVar, str2);
        lz.a(aVar.a(), errorCorrectionLevel, a, mcVar);
        a aVar2 = new a();
        if (a == Mode.BYTE && !"ISO-8859-1".equals(str2)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(str2);
            if (characterSetECIByName != null) {
                lz.a(characterSetECIByName, aVar2);
            }
        }
        lz.a(a, aVar2);
        lz.a(a == Mode.BYTE ? aVar.b() : str.length(), mcVar.a(), a, aVar2);
        aVar2.a(aVar);
        lz.a(mcVar.e(), aVar2);
        a aVar3 = new a();
        lz.a(aVar2, mcVar.d(), mcVar.e(), mcVar.f(), aVar3);
        ly lyVar = new ly(mcVar.b(), mcVar.b());
        mcVar.c(lz.a(aVar3, errorCorrectionLevel, mcVar.a(), lyVar));
        mb.a(aVar3, errorCorrectionLevel, mcVar.a(), mcVar.c(), lyVar);
        mcVar.a(lyVar);
        if (!mcVar.h()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid QR code: ");
            stringBuilder.append(mcVar.toString());
            throw new WriterException(stringBuilder.toString());
        }
    }

    static int a(int i) {
        return i < a.length ? a[i] : -1;
    }

    private static Mode a(String str, String str2) {
        if ("Shift_JIS".equals(str2)) {
            return lz.a(str) ? Mode.KANJI : Mode.BYTE;
        }
        Object obj = null;
        Object obj2 = null;
        for (int i = 0; i < str.length(); i++) {
            int charAt = str.charAt(i);
            if (charAt >= '0' && charAt <= '9') {
                obj2 = 1;
            } else if (lz.a(charAt) == -1) {
                return Mode.BYTE;
            } else {
                obj = 1;
            }
        }
        if (obj != null) {
            return Mode.ALPHANUMERIC;
        }
        if (obj2 != null) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    private static boolean a(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int i2 = bytes[i] & 255;
                if ((i2 < 129 || i2 > 159) && (i2 < 224 || i2 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    private static int a(a aVar, ErrorCorrectionLevel errorCorrectionLevel, int i, ly lyVar) throws WriterException {
        int i2 = Filter.MAX;
        int i3 = -1;
        for (int i4 = 0; i4 < 8; i4++) {
            mb.a(aVar, errorCorrectionLevel, i, i4, lyVar);
            int a = lz.a(lyVar);
            if (a < i2) {
                i3 = i4;
                i2 = a;
            }
        }
        return i3;
    }

    private static void a(int i, ErrorCorrectionLevel errorCorrectionLevel, Mode mode, mc mcVar) throws WriterException {
        mcVar.a(errorCorrectionLevel);
        mcVar.a(mode);
        for (int i2 = 1; i2 <= 40; i2++) {
            com.google.zxing.qrcode.decoder.a a = com.google.zxing.qrcode.decoder.a.a(i2);
            int b = a.b();
            b a2 = a.a(errorCorrectionLevel);
            int c = a2.c();
            int b2 = a2.b();
            int i3 = b - c;
            if (i3 >= lz.a(i, a, mode)) {
                mcVar.a(i2);
                mcVar.d(b);
                mcVar.e(i3);
                mcVar.g(b2);
                mcVar.f(c);
                mcVar.b(a.c());
                return;
            }
        }
        throw new WriterException("Cannot find proper rs block info (input data too big?)");
    }

    private static int a(int i, com.google.zxing.qrcode.decoder.a aVar, Mode mode) {
        return ((i + (mode.getCharacterCountBits(aVar) + 4)) + 7) / 8;
    }

    static void a(int i, a aVar) throws WriterException {
        int i2 = i << 3;
        if (aVar.a() <= i2) {
            int i3;
            int i4 = 0;
            for (i3 = 0; i3 < 4 && aVar.a() < i2; i3++) {
                aVar.a(false);
            }
            i3 = aVar.a() & 7;
            if (i3 > 0) {
                while (i3 < 8) {
                    aVar.a(false);
                    i3++;
                }
            }
            i -= aVar.b();
            while (i4 < i) {
                aVar.a((i4 & 1) == 0 ? 236 : 17, 8);
                i4++;
            }
            if (aVar.a() != i2) {
                throw new WriterException("Bits size does not equal capacity");
            }
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("data bits cannot fit in the QR Code");
        stringBuilder.append(aVar.a());
        stringBuilder.append(" > ");
        stringBuilder.append(i2);
        throw new WriterException(stringBuilder.toString());
    }

    static void a(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 < i3) {
            int i5 = i % i3;
            int i6 = i3 - i5;
            int i7 = i / i3;
            int i8 = i7 + 1;
            i2 /= i3;
            int i9 = i2 + 1;
            i7 -= i2;
            i8 -= i9;
            if (i7 != i8) {
                throw new WriterException("EC bytes mismatch");
            } else if (i3 != i6 + i5) {
                throw new WriterException("RS blocks mismatch");
            } else if (i != ((i2 + i7) * i6) + ((i9 + i8) * i5)) {
                throw new WriterException("Total bytes mismatch");
            } else if (i4 < i6) {
                iArr[0] = i2;
                iArr2[0] = i7;
                return;
            } else {
                iArr[0] = i9;
                iArr2[0] = i8;
                return;
            }
        }
        throw new WriterException("Block ID too large");
    }

    static void a(a aVar, int i, int i2, int i3, a aVar2) throws WriterException {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        a aVar3 = aVar2;
        if (aVar.b() == i5) {
            int i7;
            byte[] a;
            ArrayList<lx> arrayList = new ArrayList(i6);
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            for (int i12 = 0; i12 < i6; i12++) {
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                int[] iArr3 = iArr;
                lz.a(i, i2, i3, i12, iArr, iArr2);
                i7 = iArr3[0];
                byte[] bArr = new byte[i7];
                aVar.a(i9 * 8, bArr, 0, i7);
                a = lz.a(bArr, iArr2[0]);
                arrayList.add(new lx(bArr, a));
                i10 = Math.max(i10, i7);
                i11 = Math.max(i11, a.length);
                i9 += iArr3[0];
            }
            if (i5 == i9) {
                for (i7 = 0; i7 < i10; i7++) {
                    for (lx a2 : arrayList) {
                        byte[] a3 = a2.a();
                        if (i7 < a3.length) {
                            aVar3.a(a3[i7], 8);
                        }
                    }
                }
                while (i8 < i11) {
                    for (lx b : arrayList) {
                        a = b.b();
                        if (i8 < a.length) {
                            aVar3.a(a[i8], 8);
                        }
                    }
                    i8++;
                }
                if (i4 != aVar2.b()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Interleaving error: ");
                    stringBuilder.append(i4);
                    stringBuilder.append(" and ");
                    stringBuilder.append(aVar2.b());
                    stringBuilder.append(" differ.");
                    throw new WriterException(stringBuilder.toString());
                }
                return;
            }
            throw new WriterException("Data bytes does not match offset");
        }
        throw new WriterException("Number of bits and data bytes does not match");
    }

    static byte[] a(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[(length + i)];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        new lv(lt.e).a(iArr, i);
        bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) iArr[length + i3];
        }
        return bArr;
    }

    static void a(Mode mode, a aVar) {
        aVar.a(mode.getBits(), 4);
    }

    static void a(int i, int i2, Mode mode, a aVar) throws WriterException {
        i2 = mode.getCharacterCountBits(com.google.zxing.qrcode.decoder.a.a(i2));
        int i3 = (1 << i2) - 1;
        if (i <= i3) {
            aVar.a(i, i2);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append("is bigger than");
        stringBuilder.append(i3);
        throw new WriterException(stringBuilder.toString());
    }

    static void a(String str, Mode mode, a aVar, String str2) throws WriterException {
        switch (1.a[mode.ordinal()]) {
            case 1:
                lz.a((CharSequence) str, aVar);
                return;
            case 2:
                lz.b(str, aVar);
                return;
            case 3:
                lz.a(str, aVar, str2);
                return;
            case 4:
                lz.a(str, aVar);
                return;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Invalid mode: ");
                stringBuilder.append(mode);
                throw new WriterException(stringBuilder.toString());
        }
    }

    static void a(CharSequence charSequence, a aVar) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - 48;
            int i2 = i + 2;
            if (i2 < length) {
                aVar.a(((charAt * 100) + ((charSequence.charAt(i + 1) - 48) * 10)) + (charSequence.charAt(i2) - 48), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    aVar.a((charAt * 10) + (charSequence.charAt(i) - 48), 7);
                    i = i2;
                } else {
                    aVar.a(charAt, 4);
                }
            }
        }
    }

    static void b(CharSequence charSequence, a aVar) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int a = lz.a(charSequence.charAt(i));
            if (a != -1) {
                int i2 = i + 1;
                if (i2 < length) {
                    i2 = lz.a(charSequence.charAt(i2));
                    if (i2 != -1) {
                        aVar.a((a * 45) + i2, 11);
                        i += 2;
                    } else {
                        throw new WriterException();
                    }
                }
                aVar.a(a, 6);
                i = i2;
            } else {
                throw new WriterException();
            }
        }
    }

    static void a(String str, a aVar, String str2) throws WriterException {
        try {
            for (byte a : str.getBytes(str2)) {
                aVar.a(a, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e.toString());
        }
    }

    static void a(String str, a aVar) throws WriterException {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            int i = 0;
            while (i < length) {
                int i2 = ((bytes[i] & 255) << 8) | (bytes[i + 1] & 255);
                i2 = (i2 < 33088 || i2 > 40956) ? (i2 < 57408 || i2 > 60351) ? -1 : i2 - 49472 : i2 - 33088;
                if (i2 != -1) {
                    aVar.a(((i2 >> 8) * 192) + (i2 & 255), 13);
                    i += 2;
                } else {
                    throw new WriterException("Invalid byte sequence");
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e.toString());
        }
    }

    private static void a(CharacterSetECI characterSetECI, a aVar) {
        aVar.a(Mode.ECI.getBits(), 4);
        aVar.a(characterSetECI.getValue(), 8);
    }
}

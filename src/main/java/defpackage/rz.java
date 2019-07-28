package defpackage;

import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* compiled from: ImageHeaderParser */
/* renamed from: rz */
public class rz {
    private static final byte[] a = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));
    private static final int[] b = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    private final b c;

    /* compiled from: ImageHeaderParser */
    /* renamed from: rz$a */
    private static class a {
        private final ByteBuffer a;

        public a(byte[] bArr, int i) {
            this.a = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
        }

        public void a(ByteOrder byteOrder) {
            this.a.order(byteOrder);
        }

        public int a() {
            return this.a.remaining();
        }

        public int a(int i) {
            return this.a.getInt(i);
        }

        public short b(int i) {
            return this.a.getShort(i);
        }
    }

    /* compiled from: ImageHeaderParser */
    /* renamed from: rz$b */
    private interface b {
        int a() throws IOException;

        int a(byte[] bArr, int i) throws IOException;

        long a(long j) throws IOException;

        short b() throws IOException;
    }

    /* compiled from: ImageHeaderParser */
    /* renamed from: rz$c */
    private static class c implements b {
        private final InputStream a;

        public c(InputStream inputStream) {
            this.a = inputStream;
        }

        public int a() throws IOException {
            return ((this.a.read() << 8) & 65280) | (this.a.read() & 255);
        }

        public short b() throws IOException {
            return (short) (this.a.read() & 255);
        }

        public long a(long j) throws IOException {
            if (j < 0) {
                return 0;
            }
            long j2 = j;
            while (j2 > 0) {
                long skip = this.a.skip(j2);
                if (skip > 0) {
                    j2 -= skip;
                } else if (this.a.read() == -1) {
                    break;
                } else {
                    j2--;
                }
            }
            return j - j2;
        }

        public int a(byte[] bArr, int i) throws IOException {
            int i2 = i;
            while (i2 > 0) {
                int read = this.a.read(bArr, i - i2, i2);
                if (read == -1) {
                    break;
                }
                i2 -= read;
            }
            return i - i2;
        }
    }

    private static int a(int i, int i2) {
        return (i + 2) + (i2 * 12);
    }

    private static boolean a(int i) {
        return (i & 65496) == 65496 || i == 19789 || i == 18761;
    }

    public rz(InputStream inputStream) {
        this.c = new c(inputStream);
    }

    public int a() throws IOException {
        int a = this.c.a();
        if (rz.a(a)) {
            a = b();
            if (a != -1) {
                return a(new byte[a], a);
            }
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                Log.d("ImageHeaderParser", "Failed to parse exif segment length, or exif segment not found");
            }
            return -1;
        }
        if (Log.isLoggable("ImageHeaderParser", 3)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Parser doesn't handle magic number: ");
            stringBuilder.append(a);
            Log.d("ImageHeaderParser", stringBuilder.toString());
        }
        return -1;
    }

    private int a(byte[] bArr, int i) throws IOException {
        int a = this.c.a(bArr, i);
        if (a != i) {
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unable to read exif segment data, length: ");
                stringBuilder.append(i);
                stringBuilder.append(", actually read: ");
                stringBuilder.append(a);
                Log.d("ImageHeaderParser", stringBuilder.toString());
            }
            return -1;
        } else if (b(bArr, i)) {
            return rz.a(new a(bArr, i));
        } else {
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                Log.d("ImageHeaderParser", "Missing jpeg exif preamble");
            }
            return -1;
        }
    }

    private boolean b(byte[] bArr, int i) {
        boolean z = bArr != null && i > a.length;
        if (!z) {
            return z;
        }
        for (int i2 = 0; i2 < a.length; i2++) {
            if (bArr[i2] != a[i2]) {
                return false;
            }
        }
        return z;
    }

    private int b() throws IOException {
        long a;
        short b;
        int a2;
        long j;
        do {
            b = this.c.b();
            if (b != (short) 255) {
                if (Log.isLoggable("ImageHeaderParser", 3)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unknown segmentId=");
                    stringBuilder.append(b);
                    Log.d("ImageHeaderParser", stringBuilder.toString());
                }
                return -1;
            }
            b = this.c.b();
            if (b == (short) 218) {
                return -1;
            }
            if (b == (short) 217) {
                if (Log.isLoggable("ImageHeaderParser", 3)) {
                    Log.d("ImageHeaderParser", "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            a2 = this.c.a() - 2;
            if (b == (short) 225) {
                return a2;
            }
            j = (long) a2;
            a = this.c.a(j);
        } while (a == j);
        if (Log.isLoggable("ImageHeaderParser", 3)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Unable to skip enough data, type: ");
            stringBuilder2.append(b);
            stringBuilder2.append(", wanted to skip: ");
            stringBuilder2.append(a2);
            stringBuilder2.append(", but actually skipped: ");
            stringBuilder2.append(a);
            Log.d("ImageHeaderParser", stringBuilder2.toString());
        }
        return -1;
    }

    private static int a(a aVar) {
        ByteOrder byteOrder;
        int length = "Exif\u0000\u0000".length();
        short b = aVar.b(length);
        if (b == (short) 19789) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (b == (short) 18761) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unknown endianness = ");
                stringBuilder.append(b);
                Log.d("ImageHeaderParser", stringBuilder.toString());
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        aVar.a(byteOrder);
        int a = aVar.a(length + 4) + length;
        short b2 = aVar.b(a);
        for (int i = 0; i < b2; i++) {
            int a2 = rz.a(a, i);
            short b3 = aVar.b(a2);
            if (b3 == (short) 274) {
                short b4 = aVar.b(a2 + 2);
                StringBuilder stringBuilder2;
                if (b4 >= (short) 1 && b4 <= (short) 12) {
                    int a3 = aVar.a(a2 + 4);
                    if (a3 >= 0) {
                        if (Log.isLoggable("ImageHeaderParser", 3)) {
                            StringBuilder stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("Got tagIndex=");
                            stringBuilder3.append(i);
                            stringBuilder3.append(" tagType=");
                            stringBuilder3.append(b3);
                            stringBuilder3.append(" formatCode=");
                            stringBuilder3.append(b4);
                            stringBuilder3.append(" componentCount=");
                            stringBuilder3.append(a3);
                            Log.d("ImageHeaderParser", stringBuilder3.toString());
                        }
                        a3 += b[b4];
                        if (a3 <= 4) {
                            a2 += 8;
                            if (a2 < 0 || a2 > aVar.a()) {
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    StringBuilder stringBuilder4 = new StringBuilder();
                                    stringBuilder4.append("Illegal tagValueOffset=");
                                    stringBuilder4.append(a2);
                                    stringBuilder4.append(" tagType=");
                                    stringBuilder4.append(b3);
                                    Log.d("ImageHeaderParser", stringBuilder4.toString());
                                }
                            } else if (a3 >= 0 && a3 + a2 <= aVar.a()) {
                                return aVar.b(a2);
                            } else {
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    StringBuilder stringBuilder5 = new StringBuilder();
                                    stringBuilder5.append("Illegal number of bytes for TI tag data tagType=");
                                    stringBuilder5.append(b3);
                                    Log.d("ImageHeaderParser", stringBuilder5.toString());
                                }
                            }
                        } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Got byte count > 4, not orientation, continuing, formatCode=");
                            stringBuilder2.append(b4);
                            Log.d("ImageHeaderParser", stringBuilder2.toString());
                        }
                    } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                        Log.d("ImageHeaderParser", "Negative tiff component count");
                    }
                } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Got invalid format code = ");
                    stringBuilder2.append(b4);
                    Log.d("ImageHeaderParser", stringBuilder2.toString());
                }
            }
        }
        return -1;
    }

    public static void a(ExifInterface exifInterface, int i, int i2, String str) {
        r0 = new String[22];
        int i3 = 0;
        r0[0] = "FNumber";
        r0[1] = "DateTime";
        r0[2] = "DateTimeDigitized";
        r0[3] = "ExposureTime";
        r0[4] = "Flash";
        r0[5] = "FocalLength";
        r0[6] = "GPSAltitude";
        r0[7] = "GPSAltitudeRef";
        r0[8] = "GPSDateStamp";
        r0[9] = "GPSLatitude";
        r0[10] = "GPSLatitudeRef";
        r0[11] = "GPSLongitude";
        r0[12] = "GPSLongitudeRef";
        r0[13] = "GPSProcessingMethod";
        r0[14] = "GPSTimeStamp";
        r0[15] = "ISOSpeedRatings";
        r0[16] = "Make";
        r0[17] = "Model";
        r0[18] = "SubSecTime";
        r0[19] = "SubSecTimeDigitized";
        r0[20] = "SubSecTimeOriginal";
        r0[21] = "WhiteBalance";
        try {
            ExifInterface exifInterface2 = new ExifInterface(str);
            int length = r0.length;
            while (i3 < length) {
                String str2 = r0[i3];
                String attribute = exifInterface.getAttribute(str2);
                if (!TextUtils.isEmpty(attribute)) {
                    exifInterface2.setAttribute(str2, attribute);
                }
                i3++;
            }
            exifInterface2.setAttribute("ImageWidth", String.valueOf(i));
            exifInterface2.setAttribute("ImageLength", String.valueOf(i2));
            exifInterface2.setAttribute("Orientation", "0");
            exifInterface2.saveAttributes();
        } catch (IOException e) {
            Log.d("ImageHeaderParser", e.getMessage());
        }
    }
}

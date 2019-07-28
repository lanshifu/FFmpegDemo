package top.zibin.luban;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import com.tencent.rtmp.TXLiveConstants;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum Checker {
    SINGLE;
    
    private static List<String> a;
    private final byte[] JPEG_SIGNATURE;

    static {
        a = new ArrayList();
        a.add(".jpg");
        a.add(".jpeg");
        a.add(".png");
        a.add(".webp");
        a.add(".gif");
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isJPG(InputStream inputStream) {
        return a(a(inputStream));
    }

    /* Access modifiers changed, original: 0000 */
    public int getOrientation(InputStream inputStream) {
        return b(a(inputStream));
    }

    private boolean a(byte[] bArr) {
        if (bArr == null || bArr.length < 3) {
            return false;
        }
        return Arrays.equals(this.JPEG_SIGNATURE, new byte[]{bArr[0], bArr[1], bArr[2]});
    }

    private int b(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        int i;
        int a;
        int i2;
        int i3 = 0;
        while (i3 + 3 < bArr.length) {
            i = i3 + 1;
            if ((bArr[i3] & 255) == 255) {
                i3 = bArr[i] & 255;
                if (i3 != 255) {
                    i++;
                    if (!(i3 == 216 || i3 == 1)) {
                        if (i3 != 217 && i3 != 218) {
                            a = a(bArr, i, 2, false);
                            if (a >= 2) {
                                i2 = i + a;
                                if (i2 <= bArr.length) {
                                    if (i3 == 225 && a >= 8 && a(bArr, i + 2, 4, false) == 1165519206 && a(bArr, i + 6, 2, false) == 0) {
                                        i3 = i + 8;
                                        i = a - 8;
                                        break;
                                    }
                                    i3 = i2;
                                }
                            }
                            Log.e("Luban", "Invalid length");
                            return 0;
                        }
                    }
                }
                i3 = i;
            }
            i3 = i;
        }
        i = 0;
        if (i > 8) {
            a = a(bArr, i3, 4, false);
            if (a == 1229531648 || a == 1296891946) {
                boolean z = a == 1229531648;
                int a2 = a(bArr, i3 + 4, 4, z) + 2;
                if (a2 >= 10 && a2 <= i) {
                    i3 += a2;
                    i -= a2;
                    a2 = a(bArr, i3 - 2, 2, z);
                    while (true) {
                        i2 = a2 - 1;
                        if (a2 <= 0 || i < 12) {
                            break;
                        } else if (a(bArr, i3, 2, z) == 274) {
                            int a3 = a(bArr, i3 + 8, 2, z);
                            if (a3 == 1) {
                                return 0;
                            }
                            if (a3 == 3) {
                                return TXLiveConstants.RENDER_ROTATION_180;
                            }
                            if (a3 == 6) {
                                return 90;
                            }
                            if (a3 == 8) {
                                return 270;
                            }
                            Log.e("Luban", "Unsupported orientation");
                            return 0;
                        } else {
                            i3 += 12;
                            i -= 12;
                            a2 = i2;
                        }
                    }
                } else {
                    Log.e("Luban", "Invalid offset");
                    return 0;
                }
            }
            Log.e("Luban", "Invalid byte order");
            return 0;
        }
        Log.e("Luban", "Orientation not found");
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public String extSuffix(c cVar) {
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(cVar.a(), null, options);
            return options.outMimeType.replace("image/", ".");
        } catch (Exception unused) {
            return ".jpg";
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean needCompress(int i, String str) {
        boolean z = true;
        if (i <= 0) {
            return true;
        }
        File file = new File(str);
        if (!file.exists() || file.length() <= ((long) (i << 10))) {
            z = false;
        }
        return z;
    }

    private int a(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        if (z) {
            i += i2 - 1;
            i3 = -1;
        } else {
            i3 = 1;
        }
        int i4 = 0;
        while (true) {
            int i5 = i2 - 1;
            if (i2 <= 0) {
                return i4;
            }
            i4 = (bArr[i] & 255) | (i4 << 8);
            i += i3;
            i2 = i5;
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0025 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:16|17|18|24|19) */
    /* JADX WARNING: Missing block: B:17:?, code skipped:
            r6 = new byte[0];
     */
    /* JADX WARNING: Missing block: B:19:0x002a, code skipped:
            return r6;
     */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            r1.close();
     */
    private byte[] a(java.io.InputStream r6) {
        /*
        r5 = this;
        r0 = 0;
        if (r6 != 0) goto L_0x0006;
    L_0x0003:
        r6 = new byte[r0];
        return r6;
    L_0x0006:
        r1 = new java.io.ByteArrayOutputStream;
        r1.<init>();
        r2 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r2 = new byte[r2];
    L_0x000f:
        r3 = r2.length;	 Catch:{ Exception -> 0x0025 }
        r3 = r6.read(r2, r0, r3);	 Catch:{ Exception -> 0x0025 }
        r4 = -1;
        if (r3 == r4) goto L_0x001b;
    L_0x0017:
        r1.write(r2, r0, r3);	 Catch:{ Exception -> 0x0025 }
        goto L_0x000f;
    L_0x001b:
        r1.close();	 Catch:{ IOException -> 0x001e }
    L_0x001e:
        r6 = r1.toByteArray();
        return r6;
    L_0x0023:
        r6 = move-exception;
        goto L_0x002b;
    L_0x0025:
        r6 = new byte[r0];	 Catch:{ all -> 0x0023 }
        r1.close();	 Catch:{ IOException -> 0x002a }
    L_0x002a:
        return r6;
    L_0x002b:
        r1.close();	 Catch:{ IOException -> 0x002e }
    L_0x002e:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: top.zibin.luban.Checker.a(java.io.InputStream):byte[]");
    }
}

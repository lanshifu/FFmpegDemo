package tv.cjump.jni;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.Field;

public class NativeBitmapFactory {
    static Field a = null;
    static boolean b = false;
    static boolean c = false;

    private static native Bitmap createBitmap(int i, int i2, int i3, boolean z);

    private static native Bitmap createBitmap19(int i, int i2, int i3, boolean z);

    private static native boolean init();

    private static native boolean release();

    public static void a() {
        if (!c) {
            if (!DeviceUtils.g() && !DeviceUtils.h()) {
                c = true;
                b = false;
            } else if (!b) {
                try {
                    if (VERSION.SDK_INT < 11 || VERSION.SDK_INT >= 23) {
                        c = true;
                        b = false;
                    } else {
                        System.loadLibrary("ndkbitmap");
                        b = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    c = true;
                    b = false;
                } catch (Error e2) {
                    e2.printStackTrace();
                    c = true;
                    b = false;
                }
                if (b) {
                    if (init()) {
                        c();
                        if (!d()) {
                            release();
                            c = true;
                            b = false;
                        }
                    } else {
                        release();
                        c = true;
                        b = false;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("loaded");
                stringBuilder.append(b);
                Log.e("NativeBitmapFactory", stringBuilder.toString());
            }
        }
    }

    public static synchronized void b() {
        synchronized (NativeBitmapFactory.class) {
            boolean z = b;
            a = null;
            b = false;
            if (z) {
                release();
            }
        }
    }

    static void c() {
        try {
            a = Config.class.getDeclaredField("nativeInt");
            a.setAccessible(true);
        } catch (NoSuchFieldException e) {
            a = null;
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0024 A:{Catch:{ Exception -> 0x001f, Error -> 0x0071 }} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009e  */
    @android.annotation.SuppressLint({"NewApi"})
    private static boolean d() {
        /*
        r0 = a;
        r1 = 0;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = 0;
        r2 = android.graphics.Bitmap.Config.ARGB_8888;	 Catch:{ Exception -> 0x0077, Error -> 0x0070, all -> 0x006c }
        r3 = 1;
        r4 = 2;
        r2 = b(r4, r4, r2, r3);	 Catch:{ Exception -> 0x0077, Error -> 0x0070, all -> 0x006c }
        if (r2 == 0) goto L_0x0021;
    L_0x0011:
        r0 = r2.getWidth();	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        if (r0 != r4) goto L_0x0021;
    L_0x0017:
        r0 = r2.getHeight();	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        if (r0 != r4) goto L_0x0021;
    L_0x001d:
        r0 = 1;
        goto L_0x0022;
    L_0x001f:
        r0 = move-exception;
        goto L_0x007b;
    L_0x0021:
        r0 = 0;
    L_0x0022:
        if (r0 == 0) goto L_0x0066;
    L_0x0024:
        r4 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r5 = 17;
        if (r4 < r5) goto L_0x0033;
    L_0x002a:
        r4 = r2.isPremultiplied();	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        if (r4 != 0) goto L_0x0033;
    L_0x0030:
        r2.setPremultiplied(r3);	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
    L_0x0033:
        r3 = new android.graphics.Canvas;	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r3.<init>(r2);	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r4 = new android.graphics.Paint;	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r4.<init>();	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r6 = -65536; // 0xffffffffffff0000 float:NaN double:NaN;
        r4.setColor(r6);	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r6 = 1101004800; // 0x41a00000 float:20.0 double:5.439686476E-315;
        r4.setTextSize(r6);	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r7 = 0;
        r8 = 0;
        r6 = r2.getWidth();	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r9 = (float) r6;	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r6 = r2.getHeight();	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r10 = (float) r6;	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r6 = r3;
        r11 = r4;
        r6.drawRect(r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r6 = "TestLib";
        r7 = 0;
        r3.drawText(r6, r7, r7, r4);	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
        if (r3 < r5) goto L_0x0066;
    L_0x0062:
        r0 = r2.isPremultiplied();	 Catch:{ Exception -> 0x001f, Error -> 0x0071 }
    L_0x0066:
        if (r2 == 0) goto L_0x006b;
    L_0x0068:
        r2.recycle();
    L_0x006b:
        return r0;
    L_0x006c:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x009c;
    L_0x0070:
        r2 = r0;
    L_0x0071:
        if (r2 == 0) goto L_0x0076;
    L_0x0073:
        r2.recycle();
    L_0x0076:
        return r1;
    L_0x0077:
        r2 = move-exception;
        r12 = r2;
        r2 = r0;
        r0 = r12;
    L_0x007b:
        r3 = "NativeBitmapFactory";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x009b }
        r4.<init>();	 Catch:{ all -> 0x009b }
        r5 = "exception:";
        r4.append(r5);	 Catch:{ all -> 0x009b }
        r0 = r0.toString();	 Catch:{ all -> 0x009b }
        r4.append(r0);	 Catch:{ all -> 0x009b }
        r0 = r4.toString();	 Catch:{ all -> 0x009b }
        android.util.Log.e(r3, r0);	 Catch:{ all -> 0x009b }
        if (r2 == 0) goto L_0x009a;
    L_0x0097:
        r2.recycle();
    L_0x009a:
        return r1;
    L_0x009b:
        r0 = move-exception;
    L_0x009c:
        if (r2 == 0) goto L_0x00a1;
    L_0x009e:
        r2.recycle();
    L_0x00a1:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.cjump.jni.NativeBitmapFactory.d():boolean");
    }

    public static int a(Config config) {
        try {
            if (a == null) {
                return 0;
            }
            return a.getInt(config);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return 0;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static Bitmap a(int i, int i2, Config config) {
        boolean z = config.equals(Config.ARGB_4444) || config.equals(Config.ARGB_8888);
        return a(i, i2, config, z);
    }

    public static synchronized Bitmap a(int i, int i2, Config config, boolean z) {
        synchronized (NativeBitmapFactory.class) {
            Bitmap b;
            if (b) {
                if (a != null) {
                    b = b(i, i2, config, z);
                    return b;
                }
            }
            b = Bitmap.createBitmap(i, i2, config);
            return b;
        }
    }

    private static Bitmap b(int i, int i2, Config config, boolean z) {
        int a = a(config);
        if (VERSION.SDK_INT == 19) {
            return createBitmap19(i, i2, a, z);
        }
        return createBitmap(i, i2, a, z);
    }
}

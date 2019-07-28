package tv.cjump.jni;

import android.os.Build;
import android.text.TextUtils;
import java.lang.reflect.Field;

public class DeviceUtils {
    private static ARCH a = ARCH.Unknown;

    public enum ARCH {
        Unknown,
        ARM,
        X86,
        MIPS,
        ARM64
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:42:0x008b=Splitter:B:42:0x008b, B:35:0x007f=Splitter:B:35:0x007f} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0098 A:{SYNTHETIC, Splitter:B:49:0x0098} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0090 A:{SYNTHETIC, Splitter:B:45:0x0090} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0084 A:{SYNTHETIC, Splitter:B:38:0x0084} */
    public static synchronized tv.cjump.jni.DeviceUtils.ARCH a() {
        /*
        r0 = tv.cjump.jni.DeviceUtils.class;
        monitor-enter(r0);
        r1 = 20;
        r1 = new byte[r1];	 Catch:{ all -> 0x00a5 }
        r2 = new java.io.File;	 Catch:{ all -> 0x00a5 }
        r3 = android.os.Environment.getRootDirectory();	 Catch:{ all -> 0x00a5 }
        r4 = "lib/libc.so";
        r2.<init>(r3, r4);	 Catch:{ all -> 0x00a5 }
        r3 = r2.canRead();	 Catch:{ all -> 0x00a5 }
        if (r3 == 0) goto L_0x00a1;
    L_0x0018:
        r3 = 0;
        r4 = new java.io.RandomAccessFile;	 Catch:{ FileNotFoundException -> 0x008a, IOException -> 0x007e }
        r5 = "r";
        r4.<init>(r2, r5);	 Catch:{ FileNotFoundException -> 0x008a, IOException -> 0x007e }
        r4.readFully(r1);	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        r2 = 19;
        r2 = r1[r2];	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        r3 = 8;
        r2 = r2 << r3;
        r5 = 18;
        r1 = r1[r5];	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        r1 = r1 | r2;
        r2 = 3;
        if (r1 == r2) goto L_0x0066;
    L_0x0032:
        if (r1 == r3) goto L_0x0061;
    L_0x0034:
        r2 = 40;
        if (r1 == r2) goto L_0x005c;
    L_0x0038:
        r2 = 183; // 0xb7 float:2.56E-43 double:9.04E-322;
        if (r1 == r2) goto L_0x0057;
    L_0x003c:
        r2 = "NativeBitmapFactory";
        r3 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        r3.<init>();	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        r5 = "libc.so is unknown arch: ";
        r3.append(r5);	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        r1 = java.lang.Integer.toHexString(r1);	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        r3.append(r1);	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        r1 = r3.toString();	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        android.util.Log.e(r2, r1);	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        goto L_0x006a;
    L_0x0057:
        r1 = tv.cjump.jni.DeviceUtils.ARCH.ARM64;	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        a = r1;	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        goto L_0x006a;
    L_0x005c:
        r1 = tv.cjump.jni.DeviceUtils.ARCH.ARM;	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        a = r1;	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        goto L_0x006a;
    L_0x0061:
        r1 = tv.cjump.jni.DeviceUtils.ARCH.MIPS;	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        a = r1;	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        goto L_0x006a;
    L_0x0066:
        r1 = tv.cjump.jni.DeviceUtils.ARCH.X86;	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
        a = r1;	 Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0073 }
    L_0x006a:
        r4.close();	 Catch:{ IOException -> 0x006e }
        goto L_0x00a1;
    L_0x006e:
        r1 = move-exception;
    L_0x006f:
        r1.printStackTrace();	 Catch:{ all -> 0x00a5 }
        goto L_0x00a1;
    L_0x0073:
        r1 = move-exception;
        goto L_0x0096;
    L_0x0075:
        r1 = move-exception;
        r3 = r4;
        goto L_0x007f;
    L_0x0078:
        r1 = move-exception;
        r3 = r4;
        goto L_0x008b;
    L_0x007b:
        r1 = move-exception;
        r4 = r3;
        goto L_0x0096;
    L_0x007e:
        r1 = move-exception;
    L_0x007f:
        r1.printStackTrace();	 Catch:{ all -> 0x007b }
        if (r3 == 0) goto L_0x00a1;
    L_0x0084:
        r3.close();	 Catch:{ IOException -> 0x0088 }
        goto L_0x00a1;
    L_0x0088:
        r1 = move-exception;
        goto L_0x006f;
    L_0x008a:
        r1 = move-exception;
    L_0x008b:
        r1.printStackTrace();	 Catch:{ all -> 0x007b }
        if (r3 == 0) goto L_0x00a1;
    L_0x0090:
        r3.close();	 Catch:{ IOException -> 0x0094 }
        goto L_0x00a1;
    L_0x0094:
        r1 = move-exception;
        goto L_0x006f;
    L_0x0096:
        if (r4 == 0) goto L_0x00a0;
    L_0x0098:
        r4.close();	 Catch:{ IOException -> 0x009c }
        goto L_0x00a0;
    L_0x009c:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x00a5 }
    L_0x00a0:
        throw r1;	 Catch:{ all -> 0x00a5 }
    L_0x00a1:
        r1 = a;	 Catch:{ all -> 0x00a5 }
        monitor-exit(r0);
        return r1;
    L_0x00a5:
        r1 = move-exception;
        monitor-exit(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.cjump.jni.DeviceUtils.a():tv.cjump.jni.DeviceUtils$ARCH");
    }

    public static String b() {
        return Build.CPU_ABI;
    }

    public static String c() {
        try {
            Field declaredField = Build.class.getDeclaredField("CPU_ABI2");
            if (declaredField == null) {
                return null;
            }
            Object obj = declaredField.get(null);
            if (obj instanceof String) {
                return (String) obj;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean a(String str) {
        String b = b();
        boolean z = true;
        if (!TextUtils.isEmpty(b) && b.equalsIgnoreCase(str)) {
            return true;
        }
        if (TextUtils.isEmpty(c()) || !b.equalsIgnoreCase(str)) {
            z = false;
        }
        return z;
    }

    public static boolean d() {
        return Build.MANUFACTURER.equalsIgnoreCase("Xiaomi") && Build.PRODUCT.equalsIgnoreCase("dredd");
    }

    public static boolean e() {
        return Build.MANUFACTURER.equalsIgnoreCase("MagicBox") && Build.PRODUCT.equalsIgnoreCase("MagicBox");
    }

    public static boolean f() {
        return d() || e();
    }

    public static boolean g() {
        return (a("armeabi-v7a") || a("armeabi")) && ARCH.ARM.equals(a());
    }

    public static boolean h() {
        return a("x86") || ARCH.X86.equals(a());
    }
}

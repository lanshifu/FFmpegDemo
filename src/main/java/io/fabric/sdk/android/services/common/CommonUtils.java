package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.tencent.rtmp.sharp.jni.QLog;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import io.fabric.sdk.android.c;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommonUtils {
    public static final Comparator<File> a = new Comparator<File>() {
        /* renamed from: a */
        public int compare(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    };
    private static Boolean b;
    private static final char[] c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static long d = -1;

    enum Architecture {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;
        
        private static final Map<String, Architecture> a = null;

        static {
            a = new HashMap(4);
            a.put("armeabi-v7a", ARMV7);
            a.put("armeabi", ARMV6);
            a.put("arm64-v8a", ARM64);
            a.put("x86", X86_32);
        }

        static Architecture getValue() {
            String str = Build.CPU_ABI;
            if (TextUtils.isEmpty(str)) {
                c.g().a("Fabric", "Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            Architecture architecture = (Architecture) a.get(str.toLowerCase(Locale.US));
            if (architecture == null) {
                architecture = UNKNOWN;
            }
            return architecture;
        }
    }

    public static String a(int i) {
        switch (i) {
            case 2:
                return "V";
            case 3:
                return QLog.TAG_REPORTLEVEL_DEVELOPER;
            case 4:
                return "I";
            case 5:
                return QLog.TAG_REPORTLEVEL_COLORUSER;
            case 6:
                return QLog.TAG_REPORTLEVEL_USER;
            case 7:
                return "A";
            default:
                return "?";
        }
    }

    public static SharedPreferences a(Context context) {
        return context.getSharedPreferences("com.crashlytics.prefs", 0);
    }

    public static String a(File file, String str) {
        Throwable e;
        StringBuilder stringBuilder;
        Throwable th;
        String str2 = null;
        if (file.exists()) {
            Closeable bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(file), Filter.K);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String[] split = Pattern.compile("\\s*:\\s*").split(readLine, 2);
                        if (split.length > 1 && split[0].equals(str)) {
                            str2 = split[1];
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Error parsing ");
                            stringBuilder.append(file);
                            c.g().e("Fabric", stringBuilder.toString(), e);
                            a(bufferedReader, "Failed to close system file reader.");
                            return str2;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
            } catch (Exception e3) {
                e = e3;
                bufferedReader = null;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Error parsing ");
                stringBuilder.append(file);
                c.g().e("Fabric", stringBuilder.toString(), e);
                a(bufferedReader, "Failed to close system file reader.");
                return str2;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                a(bufferedReader, "Failed to close system file reader.");
                throw th;
            }
            a(bufferedReader, "Failed to close system file reader.");
        }
        return str2;
    }

    public static int a() {
        return Architecture.getValue().ordinal();
    }

    public static synchronized long b() {
        long j;
        synchronized (CommonUtils.class) {
            if (d == -1) {
                j = 0;
                String a = a(new File("/proc/meminfo"), "MemTotal");
                if (!TextUtils.isEmpty(a)) {
                    a = a.toUpperCase(Locale.US);
                    try {
                        long a2;
                        if (a.endsWith("KB")) {
                            a2 = a(a, "KB", (int) Filter.K);
                        } else if (a.endsWith("MB")) {
                            a2 = a(a, "MB", 1048576);
                        } else if (a.endsWith("GB")) {
                            a2 = a(a, "GB", 1073741824);
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Unexpected meminfo format while computing RAM: ");
                            stringBuilder.append(a);
                            c.g().a("Fabric", stringBuilder.toString());
                        }
                        j = a2;
                    } catch (NumberFormatException e) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Unexpected meminfo format while computing RAM: ");
                        stringBuilder2.append(a);
                        c.g().e("Fabric", stringBuilder2.toString(), e);
                    }
                }
                d = j;
            }
            j = d;
        }
        return j;
    }

    static long a(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[0].trim()) * ((long) i);
    }

    public static RunningAppProcessInfo a(String str, Context context) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    return runningAppProcessInfo;
                }
            }
        }
        return null;
    }

    public static String a(InputStream inputStream) throws IOException {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    public static String a(String str) {
        return a(str, "SHA-1");
    }

    public static String b(String str) {
        return a(str, "SHA-256");
    }

    public static String b(InputStream inputStream) {
        return a(inputStream, "SHA-1");
    }

    private static String a(String str, String str2) {
        return a(str.getBytes(), str2);
    }

    private static String a(InputStream inputStream, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            byte[] bArr = new byte[Filter.K];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return a(instance.digest());
                }
                instance.update(bArr, 0, read);
            }
        } catch (Exception e) {
            c.g().e("Fabric", "Could not calculate hash for app icon.", e);
            return "";
        }
    }

    private static String a(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not create hashing algorithm: ");
            stringBuilder.append(str);
            stringBuilder.append(", returning empty string.");
            c.g().e("Fabric", stringBuilder.toString(), e);
            return "";
        }
    }

    public static String a(String... strArr) {
        String str = null;
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList();
        for (String str2 : strArr) {
            if (str2 != null) {
                arrayList.add(str2.replace("-", "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : arrayList) {
            stringBuilder.append(append);
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.length() > 0) {
            str = a(stringBuilder2);
        }
        return str;
    }

    public static long b(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long c(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = (long) statFs.getBlockSize();
        return (((long) statFs.getBlockCount()) * blockSize) - (blockSize * ((long) statFs.getAvailableBlocks()));
    }

    public static Float c(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return null;
        }
        return Float.valueOf(((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1)));
    }

    public static boolean d(Context context) {
        boolean z = false;
        if (f(context)) {
            return false;
        }
        if (((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) != null) {
            z = true;
        }
        return z;
    }

    public static void a(Context context, String str) {
        if (e(context)) {
            c.g().a("Fabric", str);
        }
    }

    public static void a(Context context, String str, Throwable th) {
        if (e(context)) {
            c.g().e("Fabric", str);
        }
    }

    public static void a(Context context, int i, String str, String str2) {
        if (e(context)) {
            c.g().a(i, "Fabric", str2);
        }
    }

    public static boolean e(Context context) {
        if (b == null) {
            b = Boolean.valueOf(a(context, "com.crashlytics.Trace", false));
        }
        return b.booleanValue();
    }

    public static boolean a(Context context, String str, boolean z) {
        if (context != null) {
            Resources resources = context.getResources();
            if (resources != null) {
                int a = a(context, str, "bool");
                if (a > 0) {
                    return resources.getBoolean(a);
                }
                int a2 = a(context, str, "string");
                if (a2 > 0) {
                    return Boolean.parseBoolean(context.getString(a2));
                }
            }
        }
        return z;
    }

    public static int a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, j(context));
    }

    public static boolean f(Context context) {
        return "sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || Secure.getString(context.getContentResolver(), "android_id") == null;
    }

    public static boolean g(Context context) {
        boolean f = f(context);
        String str = Build.TAGS;
        if ((!f && str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File file = new File("/system/xbin/su");
        if (f || !file.exists()) {
            return false;
        }
        return true;
    }

    public static boolean c() {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger();
    }

    public static int h(Context context) {
        int i = f(context) ? 1 : 0;
        if (g(context)) {
            i |= 2;
        }
        return c() ? i | 4 : i;
    }

    public static int a(Context context, boolean z) {
        Float c = c(context);
        if (!z || c == null) {
            return 1;
        }
        if (((double) c.floatValue()) >= 99.0d) {
            return 3;
        }
        return ((double) c.floatValue()) < 99.0d ? 2 : 0;
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr[i3] = c[i2 >>> 4];
            cArr[i3 + 1] = c[i2 & 15];
        }
        return new String(cArr);
    }

    public static boolean i(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static String b(Context context, String str) {
        int a = a(context, str, "string");
        return a > 0 ? context.getString(a) : "";
    }

    public static void a(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                c.g().e("Fabric", str, e);
            }
        }
    }

    public static void a(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (IOException e) {
                c.g().e("Fabric", str, e);
            }
        }
    }

    public static boolean d(String str) {
        return str == null || str.length() == 0;
    }

    public static String j(Context context) {
        int i = context.getApplicationContext().getApplicationInfo().icon;
        if (i <= 0) {
            return context.getPackageName();
        }
        try {
            return context.getResources().getResourcePackageName(i);
        } catch (NotFoundException unused) {
            return context.getPackageName();
        }
    }

    public static void a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static String k(Context context) {
        Exception e;
        StringBuilder stringBuilder;
        Throwable th;
        String str = null;
        Closeable openRawResource;
        try {
            openRawResource = context.getResources().openRawResource(l(context));
            try {
                String b = b((InputStream) openRawResource);
                if (!d(b)) {
                    str = b;
                }
                a(openRawResource, "Failed to close icon input stream.");
                return str;
            } catch (Exception e2) {
                e = e2;
                try {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Could not calculate hash for app icon:");
                    stringBuilder.append(e.getMessage());
                    c.g().d("Fabric", stringBuilder.toString());
                    a(openRawResource, "Failed to close icon input stream.");
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    a(openRawResource, "Failed to close icon input stream.");
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            openRawResource = null;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Could not calculate hash for app icon:");
            stringBuilder.append(e.getMessage());
            c.g().d("Fabric", stringBuilder.toString());
            a(openRawResource, "Failed to close icon input stream.");
            return null;
        } catch (Throwable th3) {
            th = th3;
            openRawResource = null;
            a(openRawResource, "Failed to close icon input stream.");
            throw th;
        }
    }

    public static int l(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String m(Context context) {
        int a = a(context, "io.fabric.android.build_id", "string");
        if (a == 0) {
            a = a(context, "com.crashlytics.android.build_id", "string");
        }
        if (a == 0) {
            return null;
        }
        String string = context.getResources().getString(a);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Build ID is: ");
        stringBuilder.append(string);
        c.g().a("Fabric", stringBuilder.toString());
        return string;
    }

    public static String n(Context context) {
        int a = a(context, "com.google.firebase.crashlytics.unity_version", "string");
        if (a == 0) {
            return null;
        }
        String string = context.getResources().getString(a);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unity Editor version is: ");
        stringBuilder.append(string);
        c.g().a("Fabric", stringBuilder.toString());
        return string;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public static boolean c(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean o(Context context) {
        boolean z = true;
        if (!c(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            z = false;
        }
        return z;
    }
}

package com.tencent.liteav.basic.datareport;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCCommonUtil;
import com.tencent.liteav.basic.util.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.UUID;

public class TXCDRApi {
    private static final char[] DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    static final int NETWORK_TYPE_2G = 4;
    static final int NETWORK_TYPE_3G = 3;
    static final int NETWORK_TYPE_4G = 2;
    static final int NETWORK_TYPE_UNKNOWN = 255;
    static final int NETWORK_TYPE_WIFI = 1;
    static boolean initRpt = false;
    private static String mAppName = "";
    private static String mDevId = "";
    private static String mDevType = "";
    private static String mDevUUID = "";
    private static String mMacAddr = "";
    private static String mNetType = "";
    private static String mSysVersion = "";

    public static native int nativeGetStatusReportInterval();

    private static native void nativeInitDataReport();

    private static native void nativeInitEventInternal(String str, int i, int i2, TXCDRExtInfo tXCDRExtInfo);

    private static native void nativeReportDAUInterval(int i, int i2, String str);

    public static native void nativeReportEvent(String str, int i);

    public static native void nativeReportEvent40003(String str, int i, String str2, String str3);

    public static native void nativeSetCommonValue(String str, String str2);

    private static native void nativeSetEventValueInterval(String str, int i, String str2, String str3);

    private static native void nativeUninitDataReport();

    public static void InitEvent(Context context, String str, int i, int i2, TXCDRExtInfo tXCDRExtInfo) {
        setCommonInfo(context);
        if (str != null) {
            nativeInitEventInternal(str, i, i2, tXCDRExtInfo);
        }
    }

    public static void txSetEventValue(String str, int i, String str2, String str3) {
        nativeSetEventValueInterval(str, i, str2, str3);
    }

    public static void txSetEventIntValue(String str, int i, String str2, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(j);
        nativeSetEventValueInterval(str, i, str2, stringBuilder.toString());
    }

    public static void txReportDAU(Context context, int i) {
        if (context != null) {
            setCommonInfo(context);
        }
        nativeReportDAUInterval(i, 0, "");
    }

    public static void txReportDAU(Context context, int i, int i2, String str) {
        if (context != null) {
            setCommonInfo(context);
        }
        nativeReportDAUInterval(i, i2, str);
    }

    public static void reportEvent40003(String str, int i, String str2, String str3) {
        nativeReportEvent40003(str, i, str2, str3);
    }

    public static int getStatusReportInterval() {
        return nativeGetStatusReportInterval();
    }

    public static void setCommonInfo(Context context) {
        mDevType = Build.MODEL;
        mNetType = Integer.toString(getNetworkType(context));
        if (mDevId.isEmpty()) {
            mDevId = getSimulateIDFA(context);
        }
        if (mDevUUID.isEmpty()) {
            mDevUUID = getDevUUID(context, mDevId);
        }
        String packageName = getPackageName(context);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getApplicationNameByPackageName(context, packageName));
        stringBuilder.append(":");
        stringBuilder.append(packageName);
        mAppName = stringBuilder.toString();
        mSysVersion = String.valueOf(VERSION.SDK_INT);
        mMacAddr = getOrigMacAddr(context);
        txSetCommonInfo();
    }

    public static void txSetCommonInfo() {
        if (mDevType != null) {
            nativeSetCommonValue(a.f, mDevType);
        }
        if (mNetType != null) {
            nativeSetCommonValue(a.g, mNetType);
        }
        if (mDevId != null) {
            nativeSetCommonValue(a.h, mDevId);
        }
        if (mDevUUID != null) {
            nativeSetCommonValue(a.i, mDevUUID);
        }
        if (mAppName != null) {
            nativeSetCommonValue(a.j, mAppName);
        }
        if (mSysVersion != null) {
            nativeSetCommonValue(a.l, mSysVersion);
        }
        if (mMacAddr != null) {
            nativeSetCommonValue(a.m, mMacAddr);
        }
    }

    public static void txSetAppVersion(String str) {
        if (str != null) {
            nativeSetCommonValue(a.k, str);
        }
    }

    public static String txCreateToken() {
        return UUID.randomUUID().toString();
    }

    private static String byteArrayToHexString(byte[] bArr) {
        char[] cArr = new char[(bArr.length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = i + 1;
            cArr[i] = DIGITS_LOWER[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr[i3] = DIGITS_LOWER[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    static {
        b.e();
        nativeInitDataReport();
    }

    public static String string2Md5(String str) {
        String str2 = "";
        if (str == null) {
            return str2;
        }
        try {
            str = byteArrayToHexString(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            str = str2;
        }
        if (str == null) {
            str = "";
        }
        return str;
    }

    public static String doRead(Context context) {
        String str;
        StringBuilder stringBuilder;
        String str2 = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                str2 = telephonyManager.getDeviceId();
            }
            if (str2 == null) {
                str = "";
                stringBuilder = new StringBuilder();
                stringBuilder.append("deviceinfo:");
                stringBuilder.append(str);
                TXCLog.d("rtmpsdk", stringBuilder.toString());
                return string2Md5(str);
            }
        } catch (Exception unused) {
        }
        str = str2;
        stringBuilder = new StringBuilder();
        stringBuilder.append("deviceinfo:");
        stringBuilder.append(str);
        TXCLog.d("rtmpsdk", stringBuilder.toString());
        return string2Md5(str);
    }

    public static String getOrigAndroidID(Context context) {
        String string;
        String str = "";
        try {
            string = Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
            string = str;
        }
        return string2Md5(string);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0032  */
    public static java.lang.String getOrigMacAddr(android.content.Context r2) {
        /*
        r0 = "";
        r1 = "wifi";
        r2 = r2.getSystemService(r1);	 Catch:{ Exception -> 0x002f }
        r2 = (android.net.wifi.WifiManager) r2;	 Catch:{ Exception -> 0x002f }
        r1 = 0;
        if (r2 == 0) goto L_0x0012;
    L_0x000d:
        r2 = r2.getConnectionInfo();	 Catch:{ Exception -> 0x002f }
        goto L_0x0013;
    L_0x0012:
        r2 = r1;
    L_0x0013:
        if (r2 == 0) goto L_0x001b;
    L_0x0015:
        r2 = r2.getMacAddress();	 Catch:{ Exception -> 0x002f }
        r0 = r2;
        goto L_0x001c;
    L_0x001b:
        r0 = r1;
    L_0x001c:
        if (r0 == 0) goto L_0x002f;
    L_0x001e:
        r2 = ":";
        r1 = "";
        r2 = r0.replaceAll(r2, r1);	 Catch:{ Exception -> 0x002f }
        r2 = r2.toUpperCase();	 Catch:{ Exception -> 0x002f }
        r2 = string2Md5(r2);	 Catch:{ Exception -> 0x002f }
        goto L_0x0030;
    L_0x002f:
        r2 = r0;
    L_0x0030:
        if (r2 != 0) goto L_0x0034;
    L_0x0032:
        r2 = "";
    L_0x0034:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.basic.datareport.TXCDRApi.getOrigMacAddr(android.content.Context):java.lang.String");
    }

    public static String getSimulateIDFA(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(doRead(context));
        stringBuilder.append(";");
        stringBuilder.append(getOrigMacAddr(context));
        stringBuilder.append(";");
        stringBuilder.append(getOrigAndroidID(context));
        return stringBuilder.toString();
    }

    public static String getDevUUID(Context context, String str) {
        StringBuilder stringBuilder;
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.tencent.liteav.dev_uuid", 0);
        String string = sharedPreferences.getString("com.tencent.liteav.key_dev_uuid", "");
        Object obj = "";
        try {
            stringBuilder = new StringBuilder();
            stringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            stringBuilder.append("/txrtmp/spuid");
            File file = new File(stringBuilder.toString());
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                int available = fileInputStream.available();
                if (available > 0) {
                    byte[] bArr = new byte[available];
                    fileInputStream.read(bArr);
                    obj = new String(bArr, "UTF-8");
                }
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str2 = "";
        if (!string.isEmpty()) {
            str2 = string;
        }
        if (!obj.isEmpty()) {
            str2 = obj;
        }
        if (str2.isEmpty()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(UUID.randomUUID().toString());
            str = string2Md5(stringBuilder.toString());
        } else {
            str = str2;
        }
        if (obj.isEmpty()) {
            try {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(Environment.getExternalStorageDirectory().getAbsolutePath());
                stringBuilder2.append("/txrtmp");
                File file2 = new File(stringBuilder2.toString());
                if (!file2.exists()) {
                    file2.mkdir();
                }
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(Environment.getExternalStorageDirectory().getAbsolutePath());
                stringBuilder2.append("/txrtmp/spuid");
                file2 = new File(stringBuilder2.toString());
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                fileOutputStream.write(str.getBytes());
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            obj = str;
        }
        if (!string.equals(obj)) {
            Editor edit = sharedPreferences.edit();
            edit.putString("key_user_id", str);
            edit.commit();
        }
        return str;
    }

    public static int getNetworkType(Context context) {
        if (context == null) {
            return NETWORK_TYPE_UNKNOWN;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return NETWORK_TYPE_UNKNOWN;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 1;
        }
        if (activeNetworkInfo.getType() != 0) {
            return NETWORK_TYPE_UNKNOWN;
        }
        switch (telephonyManager.getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 4;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 3;
            case 13:
                return 2;
            default:
                return 2;
        }
    }

    private static String getPackageName(Context context) {
        String str = "";
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String getApplicationNameByPackageName(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128)).toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static void initCrashReport(Context context) {
        try {
            synchronized (TXCDRApi.class) {
                if (!(initRpt || context == null)) {
                    String sDKVersionStr = TXCCommonUtil.getSDKVersionStr();
                    if (sDKVersionStr != null) {
                        Editor edit = context.getSharedPreferences("BuglySdkInfos", 0).edit();
                        edit.putString("8e50744bf0", sDKVersionStr);
                        edit.commit();
                        initRpt = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

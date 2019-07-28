package com.tencent.rtmp.sharp.jni;

import android.content.Context;
import android.util.Log;
import com.tencent.liteav.basic.log.TXCLog;

public class QLog {
    public static final int CLR = 2;
    public static final int DEV = 4;
    public static final String ERR_KEY = "qq_error|";
    public static final int LOG_ITEM_MAX_CACHE_SIZE = 50;
    public static final String TAG_REPORTLEVEL_COLORUSER = "W";
    public static final String TAG_REPORTLEVEL_DEVELOPER = "D";
    public static final String TAG_REPORTLEVEL_USER = "E";
    public static final int USR = 1;
    public static String sBuildNumber = "";

    public static void dumpCacheToFile() {
    }

    public static String getReportLevel(int i) {
        if (i == 4) {
            return TAG_REPORTLEVEL_DEVELOPER;
        }
        switch (i) {
            case 1:
                return TAG_REPORTLEVEL_USER;
            case 2:
                return TAG_REPORTLEVEL_COLORUSER;
            default:
                return TAG_REPORTLEVEL_USER;
        }
    }

    public static void init(Context context) {
    }

    public static boolean isColorLevel() {
        return true;
    }

    public static boolean isDevelopLevel() {
        return true;
    }

    public static void p(String str, String str2) {
    }

    public static String getStackTraceString(Throwable th) {
        return Log.getStackTraceString(th);
    }

    public static void e(String str, int i, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(getReportLevel(i));
        stringBuilder.append("]");
        stringBuilder.append(str2);
        TXCLog.e(str, stringBuilder.toString());
    }

    public static void e(String str, int i, String str2, Throwable th) {
        e(str, i, str2);
    }

    public static void w(String str, int i, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(getReportLevel(i));
        stringBuilder.append("]");
        stringBuilder.append(str2);
        TXCLog.w(str, stringBuilder.toString());
    }

    public static void w(String str, int i, String str2, Throwable th) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(getReportLevel(i));
        stringBuilder.append("]");
        stringBuilder.append(str2);
        TXCLog.w(str, stringBuilder.toString());
    }

    public static void i(String str, int i, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(getReportLevel(i));
        stringBuilder.append("]");
        stringBuilder.append(str2);
        TXCLog.i(str, stringBuilder.toString());
    }

    public static void i(String str, int i, String str2, Throwable th) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(getReportLevel(i));
        stringBuilder.append("]");
        stringBuilder.append(str2);
        TXCLog.i(str, stringBuilder.toString());
    }

    public static void d(String str, int i, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(getReportLevel(i));
        stringBuilder.append("]");
        stringBuilder.append(str2);
        TXCLog.d(str, stringBuilder.toString());
    }

    public static void d(String str, int i, String str2, Throwable th) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(getReportLevel(i));
        stringBuilder.append("]");
        stringBuilder.append(str2);
        TXCLog.d(str, stringBuilder.toString());
    }
}

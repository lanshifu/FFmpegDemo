package com.tencent.liteav.basic.module;

public class TXCStatus {
    private static native double nativeStatusGetDoubleValue(String str, int i);

    private static native long nativeStatusGetIntValue(String str, int i);

    private static native String nativeStatusGetStrValue(String str, int i);

    private static native boolean nativeStatusSetDoubleValue(String str, int i, double d);

    private static native boolean nativeStatusSetIntValue(String str, int i, long j);

    private static native boolean nativeStatusSetStrValue(String str, int i, String str2);

    private static native void nativeStatusStartRecord(String str);

    private static native void nativeStatusStopRecord(String str);

    public static void a(String str) {
        nativeStatusStartRecord(str);
    }

    public static void b(String str) {
        nativeStatusStopRecord(str);
    }

    /* JADX WARNING: Missing block: B:17:0x002c, code skipped:
            if ((r4 instanceof java.lang.Long) == false) goto L_0x0062;
     */
    /* JADX WARNING: Missing block: B:19:0x0038, code skipped:
            return nativeStatusSetIntValue(r2, r3, ((java.lang.Long) r4).longValue());
     */
    /* JADX WARNING: Missing block: B:21:0x003b, code skipped:
            if ((r4 instanceof java.lang.String) == false) goto L_0x0062;
     */
    /* JADX WARNING: Missing block: B:23:0x0043, code skipped:
            return nativeStatusSetStrValue(r2, r3, (java.lang.String) r4);
     */
    /* JADX WARNING: Missing block: B:25:0x0046, code skipped:
            if ((r4 instanceof java.lang.Long) == false) goto L_0x0062;
     */
    /* JADX WARNING: Missing block: B:27:0x0052, code skipped:
            return nativeStatusSetIntValue(r2, r3, ((java.lang.Long) r4).longValue());
     */
    /* JADX WARNING: Missing block: B:33:0x0063, code skipped:
            return false;
     */
    public static boolean a(java.lang.String r2, int r3, java.lang.Object r4) {
        /*
        r0 = 0;
        if (r2 == 0) goto L_0x0063;
    L_0x0003:
        r1 = r2.length();
        if (r1 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x0063;
    L_0x000a:
        if (r4 != 0) goto L_0x000d;
    L_0x000c:
        return r0;
    L_0x000d:
        r1 = 1001; // 0x3e9 float:1.403E-42 double:4.946E-321;
        if (r3 == r1) goto L_0x0053;
    L_0x0011:
        switch(r3) {
            case 2001: goto L_0x0044;
            case 2002: goto L_0x0044;
            case 2003: goto L_0x0044;
            case 2004: goto L_0x0044;
            case 2005: goto L_0x0044;
            case 2006: goto L_0x0044;
            case 2007: goto L_0x0044;
            case 2008: goto L_0x0044;
            default: goto L_0x0014;
        };
    L_0x0014:
        switch(r3) {
            case 2010: goto L_0x0044;
            case 2011: goto L_0x0044;
            case 2012: goto L_0x0044;
            case 2013: goto L_0x0044;
            case 2014: goto L_0x0044;
            case 2015: goto L_0x0044;
            default: goto L_0x0017;
        };
    L_0x0017:
        switch(r3) {
            case 3001: goto L_0x0039;
            case 3002: goto L_0x0044;
            case 3003: goto L_0x0053;
            default: goto L_0x001a;
        };
    L_0x001a:
        switch(r3) {
            case 4001: goto L_0x0053;
            case 4002: goto L_0x0044;
            case 4003: goto L_0x0044;
            case 4004: goto L_0x0044;
            default: goto L_0x001d;
        };
    L_0x001d:
        switch(r3) {
            case 5001: goto L_0x0053;
            case 5002: goto L_0x0044;
            default: goto L_0x0020;
        };
    L_0x0020:
        switch(r3) {
            case 6001: goto L_0x0044;
            case 6002: goto L_0x0053;
            case 6003: goto L_0x0044;
            case 6004: goto L_0x0044;
            case 6005: goto L_0x0044;
            case 6006: goto L_0x0044;
            case 6007: goto L_0x0044;
            case 6008: goto L_0x0044;
            case 6009: goto L_0x0044;
            default: goto L_0x0023;
        };
    L_0x0023:
        switch(r3) {
            case 7001: goto L_0x002a;
            case 7002: goto L_0x002a;
            case 7003: goto L_0x002a;
            case 7004: goto L_0x002a;
            case 7005: goto L_0x002a;
            case 7006: goto L_0x002a;
            case 7007: goto L_0x002a;
            case 7008: goto L_0x002a;
            case 7009: goto L_0x002a;
            case 7010: goto L_0x002a;
            case 7011: goto L_0x002a;
            case 7012: goto L_0x0039;
            case 7013: goto L_0x002a;
            case 7014: goto L_0x0039;
            case 7015: goto L_0x0039;
            case 7016: goto L_0x002a;
            case 7017: goto L_0x002a;
            case 7018: goto L_0x002a;
            case 7019: goto L_0x0039;
            case 7020: goto L_0x002a;
            default: goto L_0x0026;
        };
    L_0x0026:
        switch(r3) {
            case 7101: goto L_0x002a;
            case 7102: goto L_0x002a;
            case 7103: goto L_0x002a;
            case 7104: goto L_0x002a;
            case 7105: goto L_0x002a;
            case 7106: goto L_0x0039;
            case 7107: goto L_0x002a;
            case 7108: goto L_0x002a;
            case 7109: goto L_0x002a;
            case 7110: goto L_0x0039;
            case 7111: goto L_0x002a;
            case 7112: goto L_0x0044;
            case 7113: goto L_0x0039;
            case 7114: goto L_0x0039;
            case 7115: goto L_0x0039;
            case 7116: goto L_0x002a;
            case 7117: goto L_0x002a;
            case 7118: goto L_0x002a;
            case 7119: goto L_0x0039;
            default: goto L_0x0029;
        };
    L_0x0029:
        goto L_0x0062;
    L_0x002a:
        r1 = r4 instanceof java.lang.Long;
        if (r1 == 0) goto L_0x0062;
    L_0x002e:
        r4 = (java.lang.Long) r4;
        r0 = r4.longValue();
        r2 = nativeStatusSetIntValue(r2, r3, r0);
        return r2;
    L_0x0039:
        r1 = r4 instanceof java.lang.String;
        if (r1 == 0) goto L_0x0062;
    L_0x003d:
        r4 = (java.lang.String) r4;
        r2 = nativeStatusSetStrValue(r2, r3, r4);
        return r2;
    L_0x0044:
        r1 = r4 instanceof java.lang.Long;
        if (r1 == 0) goto L_0x0062;
    L_0x0048:
        r4 = (java.lang.Long) r4;
        r0 = r4.longValue();
        r2 = nativeStatusSetIntValue(r2, r3, r0);
        return r2;
    L_0x0053:
        r1 = r4 instanceof java.lang.Double;
        if (r1 == 0) goto L_0x0062;
    L_0x0057:
        r4 = (java.lang.Double) r4;
        r0 = r4.doubleValue();
        r2 = nativeStatusSetDoubleValue(r2, r3, r0);
        return r2;
    L_0x0062:
        return r0;
    L_0x0063:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.basic.module.TXCStatus.a(java.lang.String, int, java.lang.Object):boolean");
    }

    /* JADX WARNING: Missing block: B:16:0x002f, code skipped:
            return java.lang.Long.valueOf(nativeStatusGetIntValue(r2, r3));
     */
    /* JADX WARNING: Missing block: B:18:0x0034, code skipped:
            return nativeStatusGetStrValue(r2, r3);
     */
    /* JADX WARNING: Missing block: B:20:0x003d, code skipped:
            return java.lang.Long.valueOf(nativeStatusGetIntValue(r2, r3));
     */
    public static java.lang.Object a(java.lang.String r2, int r3) {
        /*
        r0 = 0;
        if (r2 == 0) goto L_0x0047;
    L_0x0003:
        r1 = r2.length();
        if (r1 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x0047;
    L_0x000a:
        r1 = 1001; // 0x3e9 float:1.403E-42 double:4.946E-321;
        if (r3 == r1) goto L_0x003e;
    L_0x000e:
        switch(r3) {
            case 2001: goto L_0x0035;
            case 2002: goto L_0x0035;
            case 2003: goto L_0x0035;
            case 2004: goto L_0x0035;
            case 2005: goto L_0x0035;
            case 2006: goto L_0x0035;
            case 2007: goto L_0x0035;
            case 2008: goto L_0x0035;
            default: goto L_0x0011;
        };
    L_0x0011:
        switch(r3) {
            case 2010: goto L_0x0035;
            case 2011: goto L_0x0035;
            case 2012: goto L_0x0035;
            case 2013: goto L_0x0035;
            case 2014: goto L_0x0035;
            case 2015: goto L_0x0035;
            default: goto L_0x0014;
        };
    L_0x0014:
        switch(r3) {
            case 3001: goto L_0x0030;
            case 3002: goto L_0x0035;
            case 3003: goto L_0x003e;
            default: goto L_0x0017;
        };
    L_0x0017:
        switch(r3) {
            case 4001: goto L_0x003e;
            case 4002: goto L_0x0035;
            case 4003: goto L_0x0035;
            case 4004: goto L_0x0035;
            default: goto L_0x001a;
        };
    L_0x001a:
        switch(r3) {
            case 5001: goto L_0x003e;
            case 5002: goto L_0x0035;
            default: goto L_0x001d;
        };
    L_0x001d:
        switch(r3) {
            case 6001: goto L_0x0035;
            case 6002: goto L_0x003e;
            case 6003: goto L_0x0035;
            case 6004: goto L_0x0035;
            case 6005: goto L_0x0035;
            case 6006: goto L_0x0035;
            case 6007: goto L_0x0035;
            case 6008: goto L_0x0035;
            case 6009: goto L_0x0035;
            default: goto L_0x0020;
        };
    L_0x0020:
        switch(r3) {
            case 7001: goto L_0x0027;
            case 7002: goto L_0x0027;
            case 7003: goto L_0x0027;
            case 7004: goto L_0x0027;
            case 7005: goto L_0x0027;
            case 7006: goto L_0x0027;
            case 7007: goto L_0x0027;
            case 7008: goto L_0x0027;
            case 7009: goto L_0x0027;
            case 7010: goto L_0x0027;
            case 7011: goto L_0x0027;
            case 7012: goto L_0x0030;
            case 7013: goto L_0x0027;
            case 7014: goto L_0x0030;
            case 7015: goto L_0x0030;
            case 7016: goto L_0x0027;
            case 7017: goto L_0x0027;
            case 7018: goto L_0x0027;
            case 7019: goto L_0x0030;
            case 7020: goto L_0x0027;
            default: goto L_0x0023;
        };
    L_0x0023:
        switch(r3) {
            case 7101: goto L_0x0027;
            case 7102: goto L_0x0027;
            case 7103: goto L_0x0027;
            case 7104: goto L_0x0027;
            case 7105: goto L_0x0027;
            case 7106: goto L_0x0030;
            case 7107: goto L_0x0027;
            case 7108: goto L_0x0027;
            case 7109: goto L_0x0027;
            case 7110: goto L_0x0030;
            case 7111: goto L_0x0027;
            case 7112: goto L_0x0035;
            case 7113: goto L_0x0030;
            case 7114: goto L_0x0030;
            case 7115: goto L_0x0030;
            case 7116: goto L_0x0027;
            case 7117: goto L_0x0027;
            case 7118: goto L_0x0027;
            case 7119: goto L_0x0030;
            default: goto L_0x0026;
        };
    L_0x0026:
        return r0;
    L_0x0027:
        r2 = nativeStatusGetIntValue(r2, r3);
        r2 = java.lang.Long.valueOf(r2);
        return r2;
    L_0x0030:
        r2 = nativeStatusGetStrValue(r2, r3);
        return r2;
    L_0x0035:
        r2 = nativeStatusGetIntValue(r2, r3);
        r2 = java.lang.Long.valueOf(r2);
        return r2;
    L_0x003e:
        r2 = nativeStatusGetDoubleValue(r2, r3);
        r2 = java.lang.Double.valueOf(r2);
        return r2;
    L_0x0047:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.basic.module.TXCStatus.a(java.lang.String, int):java.lang.Object");
    }

    public static long b(String str, int i) {
        Object a = a(str, i);
        return (a == null || !(a instanceof Long)) ? 0 : ((Long) a).longValue();
    }

    public static String c(String str, int i) {
        String str2 = "";
        Object a = a(str, i);
        return (a == null || !(a instanceof String)) ? str2 : (String) a;
    }

    public static int d(String str, int i) {
        Object a = a(str, i);
        return (a == null || !(a instanceof Long)) ? 0 : ((Long) a).intValue();
    }

    public static double e(String str, int i) {
        Object a = a(str, i);
        return (a == null || !(a instanceof Double)) ? 0.0d : ((Double) a).doubleValue();
    }
}

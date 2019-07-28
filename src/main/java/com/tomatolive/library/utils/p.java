package com.tomatolive.library.utils;

import android.text.TextUtils;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/* compiled from: NumberUtils */
public class p {
    public static int a(int i) {
        return i * 1000;
    }

    public static int a(String str) {
        return a(str, 0);
    }

    public static int a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public static long b(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static double c(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    public static double a(double d, double d2) {
        return new BigDecimal(Double.toString(d)).multiply(new BigDecimal(Double.toString(d2))).doubleValue();
    }

    public static String d(String str) {
        return a(b(str));
    }

    public static String a(long j) {
        return new DecimalFormat("#,###").format(j);
    }
}

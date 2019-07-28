package com.youdao.sdk.app.other;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class n {
    protected static n a = new n();

    public static TimeZone a() {
        return a.d();
    }

    public static Date b() {
        return a.e();
    }

    public static String c() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("Z");
        simpleDateFormat.setTimeZone(a());
        return simpleDateFormat.format(b());
    }

    public TimeZone d() {
        return TimeZone.getDefault();
    }

    public Date e() {
        return new Date();
    }
}

package com.tomatolive.library.utils;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;
import com.tomatolive.library.R;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/* compiled from: DateUtils */
public class d {
    private static String a = "yyyy-MM-dd";
    private static String b = "yyyy-MM-dd HH:mm:ss";
    private static StringBuilder c = new StringBuilder();
    private static Formatter d = new Formatter(c, Locale.getDefault());

    public static String a(long j) {
        j /= 1000;
        long j2 = j % 60;
        long j3 = (j / 60) % 60;
        j /= 3600;
        c.setLength(0);
        if (j > 0) {
            return d.format("%d:%02d:%02d", new Object[]{Long.valueOf(j), Long.valueOf(j3), Long.valueOf(j2)}).toString();
        }
        return d.format("%02d:%02d", new Object[]{Long.valueOf(j3), Long.valueOf(j2)}).toString();
    }

    public static String b(long j) {
        long j2 = j % 60;
        long j3 = (j / 60) % 60;
        j /= 3600;
        c.setLength(0);
        if (j > 0) {
            return d.format("%d:%02d:%02d", new Object[]{Long.valueOf(j), Long.valueOf(j3), Long.valueOf(j2)}).toString();
        }
        return d.format("%02d:%02d", new Object[]{Long.valueOf(j3), Long.valueOf(j2)}).toString();
    }

    public static String a(String str) {
        long b = p.b(str) * 1000;
        if (b == 0) {
            return "";
        }
        return new SimpleDateFormat(b).format(new Date(b));
    }

    public static String b(String str) {
        return new SimpleDateFormat(str).format(new Date());
    }

    public static String a(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    public static String c(String str) {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(b);
        instance.add(13, p.a(str));
        return simpleDateFormat.format(instance.getTime());
    }

    public static String c(long j) {
        if (j == 0) {
            return "";
        }
        String a;
        Date date = new Date(j);
        Date date2 = new Date();
        long time = date2.getTime() - date.getTime();
        int b = b(date, new Date());
        StringBuilder stringBuilder;
        if (time <= 600000) {
            a = w.a(R.string.fq_just_now);
        } else if (time < 3600000) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(time / 60000);
            stringBuilder.append(w.a(R.string.fq_minute_ago));
            a = stringBuilder.toString();
        } else if (b == 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(time / 3600000);
            stringBuilder.append(w.a(R.string.fq_hour_ago));
            a = stringBuilder.toString();
        } else if (b == -1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(time / 3600000);
            stringBuilder.append(w.a(R.string.fq_hour_ago));
            a = stringBuilder.toString();
        } else if (!a(date, date2) || b >= -1) {
            a = DateFormat.format("yyyy-MM-dd", date).toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(time / 86400000);
            stringBuilder.append(w.a(R.string.fq_day_ago));
            a = stringBuilder.toString();
        }
        return a;
    }

    public static String d(long j) {
        String stringBuilder;
        long j2 = j / 86400;
        long j3 = (j % 86400) / 3600;
        long j4 = (j % 3600) / 60;
        j %= 60;
        StringBuilder stringBuilder2;
        if (j2 > 0) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(j2);
            stringBuilder2.append("天");
            stringBuilder = stringBuilder2.toString();
        } else if (j3 > 0) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(j3);
            stringBuilder2.append("小时");
            stringBuilder = stringBuilder2.toString();
        } else if (j4 > 0) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(j4);
            stringBuilder2.append("分钟");
            stringBuilder = stringBuilder2.toString();
        } else {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(j);
            stringBuilder3.append("秒");
            stringBuilder = stringBuilder3.toString();
        }
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("剩余");
        stringBuilder4.append(stringBuilder);
        return stringBuilder4.toString();
    }

    public static boolean a(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(1);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        if (i == instance2.get(1)) {
            return true;
        }
        return false;
    }

    public static int b(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(6);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return i - instance2.get(6);
    }

    public static Date a(String str, String str2) {
        if (str == null) {
            return null;
        }
        if (str2 == null || str2.equals("")) {
            str2 = "yyyy-MM-dd HH:mm:ss";
        }
        return d(str2).parse(str, new ParsePosition(0));
    }

    @SuppressLint({"SimpleDateFormat"})
    public static SimpleDateFormat d(String str) {
        if (str == null) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return new SimpleDateFormat(str);
    }

    public static Calendar e(String str) {
        Date a = a(str, "yyyy-MM-dd");
        Calendar instance = Calendar.getInstance();
        instance.setTime(a);
        return instance;
    }
}

package defpackage;

import java.text.SimpleDateFormat;

/* compiled from: DateUtils */
/* renamed from: mw */
public class mw {
    private static SimpleDateFormat a = new SimpleDateFormat("mm:ss");

    public static String a(long j) {
        String str = "";
        if (j > 1000) {
            return mw.b(j);
        }
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        long j2 = j / 60000;
        j = (long) Math.round(((float) (j % 60000)) / 1000.0f);
        if (j2 < 10) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("0");
            str = stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(j2);
        stringBuilder.append(":");
        str = stringBuilder.toString();
        if (j < 10) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append("0");
            str = stringBuilder2.toString();
        }
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(j);
        return stringBuilder2.toString();
    }

    public static String b(long j) {
        try {
            return a.format(Long.valueOf(j));
        } catch (Exception e) {
            e.printStackTrace();
            return "0:00";
        }
    }

    public static int c(long j) {
        try {
            return (int) Math.abs(Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10)) - j);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

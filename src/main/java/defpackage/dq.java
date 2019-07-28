package defpackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: ChinaDate */
/* renamed from: dq */
public class dq {
    private static final long[] a = new long[]{19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984, 28309, 23248, 11104, 100067, 37600, 116951, 51536, 54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 27808, 46416, 86869, 19872, 42416, 83315, 21168, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46752, 103846, 38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 21952, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19195, 19152, 42192, 118966, 53840, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448, 84835, 37744, 18936, 18800, 25776, 92326, 59984, 27424, 108228, 43744, 41696, 53987, 51552, 54615, 54432, 55888, 23893, 22176, 42704, 21972, 21200, 43448, 43344, 46240, 46758, 44368, 21920, 43940, 42416, 21168, 45683, 26928, 29495, 27296, 44368, 84821, 19296, 42352, 21732, 53600, 59752, 54560, 55968, 92838, 22224, 19168, 43476, 41680, 53584, 62034, 54560};
    private static final String[] b = new String[]{"", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊"};
    private static final String[] c = new String[]{"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] d = new String[]{"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    private static final String[] e = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    private static SimpleDateFormat f = new SimpleDateFormat("yyyy年M月d日 EEEEE");

    public static final int a(int i) {
        if (dq.b(i) != 0) {
            return (a[i + -1900] & IjkMediaMeta.AV_CH_TOP_BACK_CENTER) != 0 ? 30 : 29;
        } else {
            return 0;
        }
    }

    public static final int b(int i) {
        return (int) (a[i - 1900] & 15);
    }

    public static final int a(int i, int i2) {
        return (((long) (65536 >> i2)) & a[i + -1900]) == 0 ? 29 : 30;
    }

    public static final String c(int i) {
        String str = "";
        if (i == 10) {
            return "初十";
        }
        if (i == 20) {
            return "二十";
        }
        if (i == 30) {
            return "三十";
        }
        int i2 = i / 10;
        if (i2 == 0) {
            str = "初";
        }
        if (i2 == 1) {
            str = "十";
        }
        if (i2 == 2) {
            str = "廿";
        }
        if (i2 == 3) {
            str = "三";
        }
        StringBuilder stringBuilder;
        switch (i % 10) {
            case 1:
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("一");
                str = stringBuilder.toString();
                break;
            case 2:
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("二");
                str = stringBuilder.toString();
                break;
            case 3:
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("三");
                str = stringBuilder.toString();
                break;
            case 4:
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("四");
                str = stringBuilder.toString();
                break;
            case 5:
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("五");
                str = stringBuilder.toString();
                break;
            case 6:
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("六");
                str = stringBuilder.toString();
                break;
            case 7:
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("七");
                str = stringBuilder.toString();
                break;
            case 8:
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("八");
                str = stringBuilder.toString();
                break;
            case 9:
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("九");
                str = stringBuilder.toString();
                break;
        }
        return str;
    }

    public static String d(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        i -= 4;
        stringBuilder.append(c[i % 10]);
        stringBuilder.append(d[i % 12]);
        stringBuilder.append("年");
        return stringBuilder.toString();
    }

    public static ArrayList<String> b(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        while (i < i2) {
            arrayList.add(String.format("%s(%d)", new Object[]{dq.d(i), Integer.valueOf(i)}));
            i++;
        }
        return arrayList;
    }

    public static ArrayList<String> e(int i) {
        int i2;
        StringBuilder stringBuilder;
        ArrayList arrayList = new ArrayList();
        for (i2 = 1; i2 < b.length; i2++) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(b[i2]);
            stringBuilder.append("月");
            arrayList.add(stringBuilder.toString());
        }
        if (dq.b(i) != 0) {
            i2 = dq.b(i);
            stringBuilder = new StringBuilder();
            stringBuilder.append("闰");
            stringBuilder.append(b[dq.b(i)]);
            stringBuilder.append("月");
            arrayList.add(i2, stringBuilder.toString());
        }
        return arrayList;
    }

    public static ArrayList<String> f(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 1; i2 <= i; i2++) {
            arrayList.add(dq.c(i2));
        }
        return arrayList;
    }
}

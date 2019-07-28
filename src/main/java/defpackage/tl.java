package defpackage;

/* compiled from: NumberKit */
/* renamed from: tl */
public class tl {
    private static final String[] a = new String[]{"", "i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix"};
    private static final String[] b = new String[]{"", "x", "xx", "xxx", "xl", "l", "lx", "lxx", "lxxx", "xc"};
    private static final String[] c = new String[]{"", "c", "cc", "ccc", "cd", "d", "dc", "dcc", "dccc", "cm"};
    private static final String[] d = new String[]{"", "m", "mm", "mmm"};

    public static String a(int i) {
        while (i > 4996) {
            i -= 4996;
        }
        Object obj = d[i / 1000];
        i %= 1000;
        Object obj2 = c[i / 100];
        i %= 100;
        Object obj3 = b[i / 10];
        Object obj4 = a[i % 10];
        return String.format("%s%s%s%s", new Object[]{obj, obj2, obj3, obj4});
    }

    public static String b(int i) {
        int i2 = i / 26;
        i %= 26;
        StringBuilder stringBuilder = new StringBuilder();
        if (i2 > 26) {
            stringBuilder.append(tl.b(i2 - 1));
            stringBuilder.append((char) (i + 97));
        } else if (i2 == 0) {
            stringBuilder.append((char) (i + 97));
        } else {
            stringBuilder.append((char) (i2 + 97));
            stringBuilder.append((char) (i + 97));
        }
        return stringBuilder.toString();
    }
}

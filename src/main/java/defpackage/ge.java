package defpackage;

/* compiled from: Pinyin */
/* renamed from: ge */
public final class ge {
    public static String a(char c) {
        if (!ge.b(c)) {
            return String.valueOf(c);
        }
        if (c == 12295) {
            return "LING";
        }
        return gi.b[ge.c(c)];
    }

    public static boolean b(char c) {
        return (19968 <= c && c <= 40869 && ge.c(c) > 0) || 12295 == c;
    }

    private static int c(char c) {
        int i = c - 19968;
        if (i >= 0 && i < 7000) {
            return ge.a(gf.a, gf.b, i);
        }
        if (7000 > i || i >= 14000) {
            return ge.a(gh.a, gh.b, i - 14000);
        }
        return ge.a(gg.a, gg.b, i - 7000);
    }

    private static short a(byte[] bArr, byte[] bArr2, int i) {
        short s = (short) (bArr2[i] & 255);
        return (bArr[i / 8] & gi.a[i % 8]) != 0 ? (short) (s | 256) : s;
    }
}

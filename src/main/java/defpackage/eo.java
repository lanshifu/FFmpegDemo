package defpackage;

import com.danikula.videocache.l;

/* compiled from: Md5FileNameGenerator */
/* renamed from: eo */
public class eo implements ek {
    public String a(String str) {
        String c = c(str);
        str = b(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c);
        stringBuilder.append("/");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public String b(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(47);
        return (lastIndexOf == -1 || lastIndexOf <= lastIndexOf2 || (lastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(lastIndexOf2 + 1, str.length());
    }

    public String c(String str) {
        return l.d(str);
    }
}

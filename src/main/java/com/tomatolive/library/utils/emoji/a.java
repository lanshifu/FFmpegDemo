package com.tomatolive.library.utils.emoji;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

/* compiled from: Emoji */
public class a {
    private final String a;
    private final boolean b;
    private final List<String> c;
    private final List<String> d;
    private final String e;
    private final String f;
    private final String g;

    protected a(String str, boolean z, List<String> list, List<String> list2, byte... bArr) {
        this.a = str;
        this.b = z;
        this.c = Collections.unmodifiableList(list);
        this.d = Collections.unmodifiableList(list2);
        try {
            this.e = new String(bArr, "UTF-8");
            int length = c().length();
            String[] strArr = new String[length];
            String[] strArr2 = new String[length];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                strArr[i2] = String.format("&#%d;", new Object[]{Integer.valueOf(c().codePointAt(i))});
                int i3 = i2 + 1;
                strArr2[i2] = String.format("&#x%x;", new Object[]{Integer.valueOf(r1)});
                i += Character.charCount(r1);
                i2 = i3;
            }
            this.f = a(strArr, i2);
            this.g = a(strArr2, i2);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String a(String[] strArr, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(strArr[i2]);
            str = stringBuilder.toString();
        }
        return str;
    }

    public List<String> a() {
        return this.c;
    }

    public List<String> b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof a) && ((a) obj).c().equals(c());
    }

    public int hashCode() {
        return this.e.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Emoji{description='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", supportsFitzpatrick=");
        stringBuilder.append(this.b);
        stringBuilder.append(", aliases=");
        stringBuilder.append(this.c);
        stringBuilder.append(", tags=");
        stringBuilder.append(this.d);
        stringBuilder.append(", unicode='");
        stringBuilder.append(this.e);
        stringBuilder.append('\'');
        stringBuilder.append(", htmlDec='");
        stringBuilder.append(this.f);
        stringBuilder.append('\'');
        stringBuilder.append(", htmlHex='");
        stringBuilder.append(this.g);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}

package org.slf4j.helpers;

/* compiled from: FormattingTuple */
public class a {
    public static a a = new a(null);
    private String b;
    private Throwable c;
    private Object[] d;

    public a(String str) {
        this(str, null, null);
    }

    public a(String str, Object[] objArr, Throwable th) {
        this.b = str;
        this.c = th;
        this.d = objArr;
    }

    public String a() {
        return this.b;
    }

    public Throwable b() {
        return this.c;
    }
}

package io.reactivex.internal.functions;

import defpackage.wi;

/* compiled from: ObjectHelper */
public final class a {
    static final wi<Object, Object> a = new a();

    /* compiled from: ObjectHelper */
    static final class a implements wi<Object, Object> {
        a() {
        }

        public boolean a(Object obj, Object obj2) {
            return a.a(obj, obj2);
        }
    }

    public static int a(int i, int i2) {
        return i < i2 ? -1 : i > i2 ? 1 : 0;
    }

    public static int a(long j, long j2) {
        return j < j2 ? -1 : j > j2 ? 1 : 0;
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static <T> wi<T, T> a() {
        return a;
    }

    public static int a(int i, String str) {
        if (i > 0) {
            return i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" > 0 required but it was ");
        stringBuilder.append(i);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public static long a(long j, String str) {
        if (j > 0) {
            return j;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" > 0 required but it was ");
        stringBuilder.append(j);
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}

package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;

/* compiled from: MessageFormatter */
public final class b {
    static final Throwable a(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        Object obj = objArr[objArr.length - 1];
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        return null;
    }

    public static final a a(String str, Object[] objArr) {
        Throwable a = a(objArr);
        if (a != null) {
            objArr = b(objArr);
        }
        return a(str, objArr, a);
    }

    private static Object[] b(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            throw new IllegalStateException("non-sensical empty or null argument array");
        }
        int length = objArr.length - 1;
        Object[] objArr2 = new Object[length];
        System.arraycopy(objArr, 0, objArr2, 0, length);
        return objArr2;
    }

    public static final a a(String str, Object[] objArr, Throwable th) {
        if (str == null) {
            return new a(null, objArr, th);
        }
        if (objArr == null) {
            return new a(str);
        }
        StringBuilder stringBuilder = new StringBuilder(str.length() + 50);
        int i = 0;
        int i2 = 0;
        while (i < objArr.length) {
            int indexOf = str.indexOf("{}", i2);
            if (indexOf != -1) {
                if (!a(str, indexOf)) {
                    stringBuilder.append(str, i2, indexOf);
                    a(stringBuilder, objArr[i], new HashMap());
                    indexOf += 2;
                } else if (b(str, indexOf)) {
                    stringBuilder.append(str, i2, indexOf - 1);
                    a(stringBuilder, objArr[i], new HashMap());
                    indexOf += 2;
                } else {
                    i--;
                    stringBuilder.append(str, i2, indexOf - 1);
                    stringBuilder.append('{');
                    indexOf++;
                }
                i2 = indexOf;
                i++;
            } else if (i2 == 0) {
                return new a(str, objArr, th);
            } else {
                stringBuilder.append(str, i2, str.length());
                return new a(stringBuilder.toString(), objArr, th);
            }
        }
        stringBuilder.append(str, i2, str.length());
        return new a(stringBuilder.toString(), objArr, th);
    }

    static final boolean a(String str, int i) {
        return i != 0 && str.charAt(i - 1) == '\\';
    }

    static final boolean b(String str, int i) {
        return i >= 2 && str.charAt(i - 2) == '\\';
    }

    private static void a(StringBuilder stringBuilder, Object obj, Map<Object[], Object> map) {
        if (obj == null) {
            stringBuilder.append("null");
            return;
        }
        if (!obj.getClass().isArray()) {
            a(stringBuilder, obj);
        } else if (obj instanceof boolean[]) {
            a(stringBuilder, (boolean[]) obj);
        } else if (obj instanceof byte[]) {
            a(stringBuilder, (byte[]) obj);
        } else if (obj instanceof char[]) {
            a(stringBuilder, (char[]) obj);
        } else if (obj instanceof short[]) {
            a(stringBuilder, (short[]) obj);
        } else if (obj instanceof int[]) {
            a(stringBuilder, (int[]) obj);
        } else if (obj instanceof long[]) {
            a(stringBuilder, (long[]) obj);
        } else if (obj instanceof float[]) {
            a(stringBuilder, (float[]) obj);
        } else if (obj instanceof double[]) {
            a(stringBuilder, (double[]) obj);
        } else {
            a(stringBuilder, (Object[]) obj, (Map) map);
        }
    }

    private static void a(StringBuilder stringBuilder, Object obj) {
        try {
            stringBuilder.append(obj.toString());
        } catch (Throwable th) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("SLF4J: Failed toString() invocation on an object of type [");
            stringBuilder2.append(obj.getClass().getName());
            stringBuilder2.append("]");
            f.a(stringBuilder2.toString(), th);
            stringBuilder.append("[FAILED toString()]");
        }
    }

    private static void a(StringBuilder stringBuilder, Object[] objArr, Map<Object[], Object> map) {
        stringBuilder.append('[');
        if (map.containsKey(objArr)) {
            stringBuilder.append("...");
        } else {
            map.put(objArr, null);
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                a(stringBuilder, objArr[i], (Map) map);
                if (i != length - 1) {
                    stringBuilder.append(", ");
                }
            }
            map.remove(objArr);
        }
        stringBuilder.append(']');
    }

    private static void a(StringBuilder stringBuilder, boolean[] zArr) {
        stringBuilder.append('[');
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(zArr[i]);
            if (i != length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']');
    }

    private static void a(StringBuilder stringBuilder, byte[] bArr) {
        stringBuilder.append('[');
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(bArr[i]);
            if (i != length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']');
    }

    private static void a(StringBuilder stringBuilder, char[] cArr) {
        stringBuilder.append('[');
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(cArr[i]);
            if (i != length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']');
    }

    private static void a(StringBuilder stringBuilder, short[] sArr) {
        stringBuilder.append('[');
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(sArr[i]);
            if (i != length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']');
    }

    private static void a(StringBuilder stringBuilder, int[] iArr) {
        stringBuilder.append('[');
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(iArr[i]);
            if (i != length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']');
    }

    private static void a(StringBuilder stringBuilder, long[] jArr) {
        stringBuilder.append('[');
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(jArr[i]);
            if (i != length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']');
    }

    private static void a(StringBuilder stringBuilder, float[] fArr) {
        stringBuilder.append('[');
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(fArr[i]);
            if (i != length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']');
    }

    private static void a(StringBuilder stringBuilder, double[] dArr) {
        stringBuilder.append('[');
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(dArr[i]);
            if (i != length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']');
    }
}

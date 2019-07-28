package org.xutils.db.table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import org.xutils.common.util.LogUtil;
import org.xutils.db.converter.ColumnConverterFactory;

public final class ColumnUtils {
    private static final HashSet<Class<?>> a = new HashSet(2);
    private static final HashSet<Class<?>> b = new HashSet(2);
    private static final HashSet<Class<?>> c = new HashSet(4);

    private ColumnUtils() {
    }

    static {
        a.add(Boolean.TYPE);
        a.add(Boolean.class);
        b.add(Integer.TYPE);
        b.add(Integer.class);
        c.addAll(b);
        c.add(Long.TYPE);
        c.add(Long.class);
    }

    public static boolean isAutoIdType(Class<?> cls) {
        return c.contains(cls);
    }

    public static boolean isInteger(Class<?> cls) {
        return b.contains(cls);
    }

    public static boolean isBoolean(Class<?> cls) {
        return a.contains(cls);
    }

    public static Object convert2DbValueIfNeeded(Object obj) {
        return obj != null ? ColumnConverterFactory.getColumnConverter(obj.getClass()).fieldValue2DbValue(obj) : obj;
    }

    static Method a(Class<?> cls, Field field) {
        Method method = null;
        if (Object.class.equals(cls)) {
            return null;
        }
        String name = field.getName();
        if (isBoolean(field.getType())) {
            method = a((Class) cls, name);
        }
        if (method == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("get");
            stringBuilder.append(name.substring(0, 1).toUpperCase());
            stringBuilder.append(name.substring(1));
            name = stringBuilder.toString();
            try {
                method = cls.getDeclaredMethod(name, new Class[0]);
            } catch (NoSuchMethodException unused) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(cls.getName());
                stringBuilder.append("#");
                stringBuilder.append(name);
                stringBuilder.append(" not exist");
                LogUtil.d(stringBuilder.toString());
            }
        }
        return method == null ? a(cls.getSuperclass(), field) : method;
    }

    static Method b(Class<?> cls, Field field) {
        Method method = null;
        if (Object.class.equals(cls)) {
            return null;
        }
        String name = field.getName();
        Class type = field.getType();
        if (isBoolean(type)) {
            method = a(cls, name, type);
        }
        if (method == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("set");
            stringBuilder.append(name.substring(0, 1).toUpperCase());
            stringBuilder.append(name.substring(1));
            name = stringBuilder.toString();
            try {
                method = cls.getDeclaredMethod(name, new Class[]{type});
            } catch (NoSuchMethodException unused) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(cls.getName());
                stringBuilder2.append("#");
                stringBuilder2.append(name);
                stringBuilder2.append(" not exist");
                LogUtil.d(stringBuilder2.toString());
            }
        }
        return method == null ? b(cls.getSuperclass(), field) : method;
    }

    private static Method a(Class<?> cls, String str) {
        StringBuilder stringBuilder;
        if (!str.startsWith("is")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("is");
            stringBuilder.append(str.substring(0, 1).toUpperCase());
            stringBuilder.append(str.substring(1));
            str = stringBuilder.toString();
        }
        try {
            return cls.getDeclaredMethod(str, new Class[0]);
        } catch (NoSuchMethodException unused) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(cls.getName());
            stringBuilder.append("#");
            stringBuilder.append(str);
            stringBuilder.append(" not exist");
            LogUtil.d(stringBuilder.toString());
            return null;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?> cls2) {
        StringBuilder stringBuilder;
        if (str.startsWith("is")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("set");
            stringBuilder.append(str.substring(2, 3).toUpperCase());
            stringBuilder.append(str.substring(3));
            str = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("set");
            stringBuilder.append(str.substring(0, 1).toUpperCase());
            stringBuilder.append(str.substring(1));
            str = stringBuilder.toString();
        }
        try {
            return cls.getDeclaredMethod(str, new Class[]{cls2});
        } catch (NoSuchMethodException unused) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(cls.getName());
            stringBuilder2.append("#");
            stringBuilder2.append(str);
            stringBuilder2.append(" not exist");
            LogUtil.d(stringBuilder2.toString());
            return null;
        }
    }
}

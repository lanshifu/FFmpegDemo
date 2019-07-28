package org.xutils.db.converter;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import org.xutils.common.util.LogUtil;
import org.xutils.db.sqlite.ColumnDbType;

public final class ColumnConverterFactory {
    private static final ConcurrentHashMap<String, ColumnConverter> a = new ConcurrentHashMap();

    private ColumnConverterFactory() {
    }

    public static ColumnConverter getColumnConverter(Class cls) {
        ColumnConverter columnConverter;
        if (a.containsKey(cls.getName())) {
            columnConverter = (ColumnConverter) a.get(cls.getName());
        } else {
            if (ColumnConverter.class.isAssignableFrom(cls)) {
                try {
                    columnConverter = (ColumnConverter) cls.newInstance();
                    if (columnConverter != null) {
                        a.put(cls.getName(), columnConverter);
                    }
                } catch (Throwable th) {
                    LogUtil.e(th.getMessage(), th);
                }
            }
            columnConverter = null;
        }
        if (columnConverter != null) {
            return columnConverter;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Database Column Not Support: ");
        stringBuilder.append(cls.getName());
        stringBuilder.append(", please impl ColumnConverter or use ColumnConverterFactory#registerColumnConverter(...)");
        throw new RuntimeException(stringBuilder.toString());
    }

    public static ColumnDbType getDbColumnType(Class cls) {
        return getColumnConverter(cls).getColumnDbType();
    }

    public static void registerColumnConverter(Class cls, ColumnConverter columnConverter) {
        a.put(cls.getName(), columnConverter);
    }

    public static boolean isSupportColumnConverter(Class cls) {
        boolean z = true;
        if (a.containsKey(cls.getName())) {
            return true;
        }
        if (ColumnConverter.class.isAssignableFrom(cls)) {
            try {
                ColumnConverter columnConverter = (ColumnConverter) cls.newInstance();
                if (columnConverter != null) {
                    a.put(cls.getName(), columnConverter);
                }
                if (columnConverter != null) {
                    z = false;
                }
                return z;
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    static {
        BooleanColumnConverter booleanColumnConverter = new BooleanColumnConverter();
        a.put(Boolean.TYPE.getName(), booleanColumnConverter);
        a.put(Boolean.class.getName(), booleanColumnConverter);
        a.put(byte[].class.getName(), new ByteArrayColumnConverter());
        ByteColumnConverter byteColumnConverter = new ByteColumnConverter();
        a.put(Byte.TYPE.getName(), byteColumnConverter);
        a.put(Byte.class.getName(), byteColumnConverter);
        CharColumnConverter charColumnConverter = new CharColumnConverter();
        a.put(Character.TYPE.getName(), charColumnConverter);
        a.put(Character.class.getName(), charColumnConverter);
        a.put(Date.class.getName(), new DateColumnConverter());
        DoubleColumnConverter doubleColumnConverter = new DoubleColumnConverter();
        a.put(Double.TYPE.getName(), doubleColumnConverter);
        a.put(Double.class.getName(), doubleColumnConverter);
        FloatColumnConverter floatColumnConverter = new FloatColumnConverter();
        a.put(Float.TYPE.getName(), floatColumnConverter);
        a.put(Float.class.getName(), floatColumnConverter);
        IntegerColumnConverter integerColumnConverter = new IntegerColumnConverter();
        a.put(Integer.TYPE.getName(), integerColumnConverter);
        a.put(Integer.class.getName(), integerColumnConverter);
        LongColumnConverter longColumnConverter = new LongColumnConverter();
        a.put(Long.TYPE.getName(), longColumnConverter);
        a.put(Long.class.getName(), longColumnConverter);
        ShortColumnConverter shortColumnConverter = new ShortColumnConverter();
        a.put(Short.TYPE.getName(), shortColumnConverter);
        a.put(Short.class.getName(), shortColumnConverter);
        a.put(java.sql.Date.class.getName(), new SqlDateColumnConverter());
        a.put(String.class.getName(), new StringColumnConverter());
    }
}

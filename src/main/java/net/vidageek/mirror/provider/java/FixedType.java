package net.vidageek.mirror.provider.java;

enum FixedType {
    VOID(Void.TYPE),
    BOOLEAN(Boolean.TYPE),
    BYTE(Byte.TYPE),
    SHORT(Short.TYPE),
    CHAR(Character.TYPE),
    INT(Integer.TYPE),
    LONG(Long.TYPE),
    FLOAT(Float.TYPE),
    DOUBLE(Double.TYPE);
    
    private final Class<?> clazz;

    private FixedType(Class<?> cls) {
        this.clazz = cls;
    }

    public static Class<?> fromValue(String str) {
        for (FixedType fixedType : values()) {
            if (fixedType.clazz.toString().equals(str)) {
                return fixedType.clazz;
            }
        }
        return null;
    }
}

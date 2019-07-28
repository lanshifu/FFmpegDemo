package net.vidageek.mirror.matcher;

import java.util.HashMap;
import java.util.Map;

public final class ClassArrayMatcher {
    private static Map<Class<?>, Class<?>> primitiveToWrapper = new HashMap();
    private final Class<?>[] baseClasses;

    static {
        primitiveToWrapper.put(Boolean.TYPE, Boolean.class);
        primitiveToWrapper.put(Byte.TYPE, Byte.class);
        primitiveToWrapper.put(Character.TYPE, Character.class);
        primitiveToWrapper.put(Short.TYPE, Short.class);
        primitiveToWrapper.put(Integer.TYPE, Integer.class);
        primitiveToWrapper.put(Long.TYPE, Long.class);
        primitiveToWrapper.put(Float.TYPE, Float.class);
        primitiveToWrapper.put(Double.TYPE, Double.class);
    }

    public ClassArrayMatcher(Class<?>... clsArr) {
        if (clsArr != null) {
            this.baseClasses = clsArr;
            return;
        }
        throw new IllegalArgumentException("argument baseClasses cannot be null.");
    }

    public MatchType match(Class<?>... clsArr) {
        if (clsArr == null) {
            throw new IllegalArgumentException("argument classes cannot be null.");
        } else if (this.baseClasses.length != clsArr.length) {
            return MatchType.DONT_MATCH;
        } else {
            if (isPerfectMatch(clsArr)) {
                return MatchType.PERFECT;
            }
            if (isMatch(clsArr)) {
                return MatchType.MATCH;
            }
            return MatchType.DONT_MATCH;
        }
    }

    private boolean isMatch(Class<?>[] clsArr) {
        for (int i = 0; i < this.baseClasses.length; i++) {
            if (!areClassesAssignable(clsArr[i], this.baseClasses[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isPerfectMatch(Class<?>[] clsArr) {
        for (int i = 0; i < this.baseClasses.length; i++) {
            if (!areClassesEqual(this.baseClasses[i], clsArr[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean areClassesAssignable(Class<?> cls, Class<?> cls2) {
        if ((cls.isPrimitive() ^ cls2.isPrimitive()) == 0) {
            return cls.isAssignableFrom(cls2);
        }
        if (cls.isPrimitive()) {
            return ((Class) primitiveToWrapper.get(cls)).isAssignableFrom(cls2);
        }
        return ((Class) primitiveToWrapper.get(cls2)).isAssignableFrom(cls);
    }

    private boolean areClassesEqual(Class<?> cls, Class<?> cls2) {
        if ((cls.isPrimitive() ^ cls2.isPrimitive()) == 0) {
            return cls.equals(cls2);
        }
        if (cls.isPrimitive()) {
            return ((Class) primitiveToWrapper.get(cls)).equals(cls2);
        }
        return ((Class) primitiveToWrapper.get(cls2)).equals(cls);
    }
}

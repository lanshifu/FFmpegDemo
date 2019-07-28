package net.vidageek.mirror;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import net.vidageek.mirror.dsl.AccessorsController;
import net.vidageek.mirror.dsl.ClassController;
import net.vidageek.mirror.dsl.FieldController;
import net.vidageek.mirror.dsl.MemberController;

@Deprecated
public final class Mirror {
    private static final net.vidageek.mirror.dsl.Mirror mirror = new net.vidageek.mirror.dsl.Mirror();

    private Mirror() {
    }

    public static Class<?> reflectClass(String str) {
        return mirror.reflectClass(str);
    }

    public static <T> ClassController<T> on(Class<T> cls) {
        return mirror.on((Class) cls);
    }

    public static AccessorsController on(Object obj) {
        return mirror.on(obj);
    }

    public static ClassController<?> on(String str) {
        return mirror.on(str);
    }

    public static MemberController on(AnnotatedElement annotatedElement) {
        return mirror.on(annotatedElement);
    }

    public static FieldController on(Field field) {
        return mirror.on(field);
    }
}

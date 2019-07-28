package net.vidageek.mirror.reflect.dsl;

import java.lang.reflect.Field;

public interface FieldReflector {
    Field onClass(Class cls);
}

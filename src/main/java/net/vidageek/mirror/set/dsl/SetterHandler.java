package net.vidageek.mirror.set.dsl;

import java.lang.reflect.Field;

public interface SetterHandler {
    FieldSetter field(String str);

    FieldSetter field(Field field);
}

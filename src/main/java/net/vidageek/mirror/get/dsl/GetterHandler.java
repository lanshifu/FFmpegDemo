package net.vidageek.mirror.get.dsl;

import java.lang.reflect.Field;

public interface GetterHandler {
    Object field(String str);

    Object field(Field field);
}

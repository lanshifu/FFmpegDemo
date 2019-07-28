package net.vidageek.mirror.dsl;

import net.vidageek.mirror.reflect.dsl.AllMemberHandler;
import net.vidageek.mirror.reflect.dsl.FieldHandler;

public interface FieldController {
    FieldHandler reflect();

    AllMemberHandler reflectAll();
}

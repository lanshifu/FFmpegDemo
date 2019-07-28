package net.vidageek.mirror.reflect.dsl;

import java.lang.reflect.Method;

public interface MethodReflector {
    Method withAnyArgs();

    Method withArgs(Class<?>... clsArr);

    Method withoutArgs();
}

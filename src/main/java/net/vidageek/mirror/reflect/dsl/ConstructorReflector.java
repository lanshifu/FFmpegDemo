package net.vidageek.mirror.reflect.dsl;

import java.lang.reflect.Constructor;

public interface ConstructorReflector<T> {
    Constructor<T> withAnyArgs();

    Constructor<T> withArgs(Class<?>... clsArr);

    Constructor<T> withoutArgs();
}

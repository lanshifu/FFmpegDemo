package net.vidageek.mirror.invoke.dsl;

public interface MethodHandler {
    Object withArgs(Object... objArr);

    Object withoutArgs();
}

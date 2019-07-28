package net.vidageek.mirror.matcher;

import java.lang.reflect.Method;
import net.vidageek.mirror.list.dsl.Matcher;

public final class SetterMatcher implements Matcher<Method> {
    public boolean accepts(Method method) {
        return method.getName().startsWith("set") && method.getParameterTypes().length == 1 && method.getReturnType().equals(Void.TYPE);
    }
}

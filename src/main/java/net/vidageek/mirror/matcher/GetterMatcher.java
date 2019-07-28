package net.vidageek.mirror.matcher;

import java.lang.reflect.Method;
import net.vidageek.mirror.list.dsl.Matcher;

public final class GetterMatcher implements Matcher<Method> {
    public boolean accepts(Method method) {
        return method.getName().startsWith("get") && method.getParameterTypes().length == 0 && !method.getReturnType().equals(Void.TYPE);
    }
}

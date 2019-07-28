package net.vidageek.mirror.list;

import java.lang.reflect.Method;
import net.vidageek.mirror.list.dsl.Matcher;

public final class SameNameMatcher implements Matcher<Method> {
    private final String methodName;

    public SameNameMatcher(String str) {
        this.methodName = str;
    }

    public boolean accepts(Method method) {
        return method.getName().equals(this.methodName);
    }
}

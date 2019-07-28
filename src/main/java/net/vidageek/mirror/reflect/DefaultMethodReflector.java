package net.vidageek.mirror.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.list.EqualMethodRemover;
import net.vidageek.mirror.list.SameNameMatcher;
import net.vidageek.mirror.list.dsl.MirrorList;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.MethodReflector;

public final class DefaultMethodReflector implements MethodReflector {
    private final Class<?> clazz;
    private final String methodName;
    private final ReflectionProvider provider;

    public DefaultMethodReflector(ReflectionProvider reflectionProvider, String str, Class<?> cls) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("methodName cannot be null or empty");
        } else if (cls != null) {
            this.provider = reflectionProvider;
            this.methodName = str.trim();
            this.clazz = cls;
        } else {
            throw new IllegalArgumentException("clazz cannnot be null");
        }
    }

    public Method withoutArgs() {
        return withArgs(new Class[0]);
    }

    public Method withArgs(Class<?>... clsArr) {
        if (clsArr != null) {
            return this.provider.getClassReflectionProvider(this.clazz).reflectMethod(this.methodName, clsArr);
        }
        throw new IllegalArgumentException("classes cannot be null");
    }

    public Method withAnyArgs() {
        MirrorList matching = new Mirror(this.provider).on(this.clazz).reflectAll().methods().matching(new SameNameMatcher(this.methodName));
        if (matching.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(matching.matching(new EqualMethodRemover((Method) matching.get(0))));
        arrayList.add(matching.get(0));
        if (arrayList.size() == 1) {
            return (Method) arrayList.get(0);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("more than one method named ");
        stringBuilder.append(this.methodName);
        stringBuilder.append(" was found on class ");
        stringBuilder.append(this.clazz.getName());
        stringBuilder.append(" while attempting to find a uniquely named method. Methods are: ");
        stringBuilder.append(arrayList);
        throw new MirrorException(stringBuilder.toString());
    }
}

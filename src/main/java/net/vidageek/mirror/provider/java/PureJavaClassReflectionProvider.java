package net.vidageek.mirror.provider.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.matcher.ClassArrayMatcher;
import net.vidageek.mirror.matcher.MatchType;
import net.vidageek.mirror.provider.ClassReflectionProvider;

public final class PureJavaClassReflectionProvider<T> implements ClassReflectionProvider<T> {
    private Class<T> clazz;

    public PureJavaClassReflectionProvider(String str) {
        try {
            this.clazz = Class.forName(str, false, PureJavaClassReflectionProvider.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            this.clazz = FixedType.fromValue(str);
            if (this.clazz == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("class ");
                stringBuilder.append(str);
                stringBuilder.append(" could not be found.");
                throw new ReflectionProviderException(stringBuilder.toString(), e);
            }
        }
    }

    public PureJavaClassReflectionProvider(Class<T> cls) {
        this.clazz = cls;
    }

    public Class<T> reflectClass() {
        return this.clazz;
    }

    public List<Field> reflectAllFields() {
        ArrayList arrayList = new ArrayList();
        for (Class cls = this.clazz; cls != null; cls = cls.getSuperclass()) {
            arrayList.addAll(Arrays.asList(cls.getDeclaredFields()));
            for (Class fields : cls.getInterfaces()) {
                arrayList.addAll(Arrays.asList(fields.getFields()));
            }
        }
        return arrayList;
    }

    public List<Method> reflectAllMethods() {
        ArrayList arrayList = new ArrayList();
        for (Class cls = this.clazz; cls != null; cls = cls.getSuperclass()) {
            arrayList.addAll(Arrays.asList(cls.getDeclaredMethods()));
        }
        return arrayList;
    }

    public List<Constructor<T>> reflectAllConstructors() {
        return Arrays.asList(this.clazz.getDeclaredConstructors());
    }

    public Constructor<T> reflectConstructor(Class<?>[] clsArr) {
        ClassArrayMatcher classArrayMatcher = new ClassArrayMatcher(clsArr);
        Constructor<T> constructor = null;
        for (Constructor<T> constructor2 : reflectAllConstructors()) {
            MatchType match = classArrayMatcher.match(constructor2.getParameterTypes());
            if (MatchType.PERFECT.equals(match)) {
                return constructor2;
            }
            if (MatchType.MATCH.equals(match)) {
                constructor = constructor2;
            }
        }
        return constructor;
    }

    public Field reflectField(String str) {
        for (Field field : reflectAllFields()) {
            if (field.getName().equals(str)) {
                return field;
            }
        }
        return null;
    }

    public Method reflectMethod(String str, Class<?>[] clsArr) {
        ClassArrayMatcher classArrayMatcher = new ClassArrayMatcher(clsArr);
        Method method = null;
        for (Method method2 : reflectAllMethods()) {
            if (method2.getName().equals(str)) {
                MatchType match = classArrayMatcher.match(method2.getParameterTypes());
                if (MatchType.PERFECT.equals(match)) {
                    return method2;
                }
                if (MatchType.MATCH.equals(match)) {
                    method = method2;
                }
            }
        }
        return method;
    }
}

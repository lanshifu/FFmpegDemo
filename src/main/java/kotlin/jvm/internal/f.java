package kotlin.jvm.internal;

import java.util.Arrays;
import java.util.List;
import kotlin.KotlinNullPointerException;
import kotlin.UninitializedPropertyAccessException;

/* compiled from: Intrinsics */
public class f {
    private f() {
    }

    public static void a() {
        throw ((KotlinNullPointerException) a(new KotlinNullPointerException()));
    }

    public static void a(String str) {
        throw ((UninitializedPropertyAccessException) a(new UninitializedPropertyAccessException(str)));
    }

    public static void b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("lateinit property ");
        stringBuilder.append(str);
        stringBuilder.append(" has not been initialized");
        a(stringBuilder.toString());
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" must not be null");
            throw ((IllegalStateException) a(new IllegalStateException(stringBuilder.toString())));
        }
    }

    public static void b(Object obj, String str) {
        if (obj == null) {
            c(str);
        }
    }

    private static void c(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Parameter specified as non-null is null: method ");
        stringBuilder.append(className);
        stringBuilder.append(".");
        stringBuilder.append(methodName);
        stringBuilder.append(", parameter ");
        stringBuilder.append(str);
        throw ((IllegalArgumentException) a(new IllegalArgumentException(stringBuilder.toString())));
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    private static <T extends Throwable> T a(T t) {
        return a((Throwable) t, f.class.getName());
    }

    static <T extends Throwable> T a(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        List subList = Arrays.asList(stackTrace).subList(i + 1, length);
        t.setStackTrace((StackTraceElement[]) subList.toArray(new StackTraceElement[subList.size()]));
        return t;
    }
}

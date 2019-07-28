package kotlin.jvm.internal;

/* compiled from: ReflectionFactory */
public class h {
    public String a(Lambda lambda) {
        return a((e) lambda);
    }

    public String a(e eVar) {
        String obj = eVar.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith("kotlin.jvm.functions.") ? obj.substring("kotlin.jvm.functions.".length()) : obj;
    }
}

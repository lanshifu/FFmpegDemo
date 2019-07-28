package defpackage;

/* renamed from: sw */
public final class sw {
    public static String a(su suVar) {
        if (suVar == null) {
            return null;
        }
        try {
            return new String(suVar.a(), "UTF-8");
        } catch (Exception unused) {
            return null;
        }
    }
}
